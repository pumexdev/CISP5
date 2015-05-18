/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.controller;

import com.pumex.ConnectedInsight.Excel.Bean.DataEntryBean;
import com.pumex.ConnectedInsight.Excel.Bean.EmployeeDetailsBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExcelProcessingBean;
import com.pumex.ConnectedInsight.Excel.Dao.BasicExcelOperation;
import com.pumex.ConnectedInsight.Excel.Dao.ExcelMasterDao;
import com.pumex.ConnectedInsight.Excel.Dao.ExcelOperationDao;
import com.pumex.ConnectedInsight.Excel.Dao.ExcelValidator;
import com.pumex.ConnectedInsight.Excel.Dao.IDataExcelTemplateValidatorDao;
import com.pumex.ConnectedInsight.Excel.Dao.IExcelVolAndRevDao;
import com.pumex.ConnectedInsight.Property.PropertyFile;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Vishnu AU
 * Created on:16 July 2012
 * Updated On:4 April 2013
 * Updated by: Vishnu AU
 */
@Controller
public class ExcelProcessController {

    @Autowired
    ExcelOperationDao exelOperation;
    @Autowired
    ExcelMasterDao excelMaster;
    @Autowired
    IExcelVolAndRevDao iExcelVolAndRevDao;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    IDataExcelTemplateValidatorDao iDataExcelTemplateValidatorDao;

    @RequestMapping(value = "resourceip.htm", method = RequestMethod.GET)
    public ModelAndView getResourceIpProcess() {
        ModelAndView model = null;
        return model;
    }

    @RequestMapping(value = "processing.htm", method = RequestMethod.GET)
    public ModelAndView getexcelSheetProcessing(@ModelAttribute("processingBean") ExcelProcessingBean excelprocessingFormBean, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView("processing");
        try {
            Workbook wb = (Workbook) session.getAttribute("workbook");
            UserBean user = (UserBean) session.getAttribute("user");
            String statementDate=session.getAttribute("statementdate").toString();
            String userMesage = "";
            if (wb != null) {
                excelprocessingFormBean.setStatus("Success");
                excelprocessingFormBean.setDate(statementDate);
                Map<String, Integer> processMap = excelMaster.getProcessMap(user);
                excelprocessingFormBean = getExcelDetails(wb, excelprocessingFormBean, user);
//                if (!excelprocessingFormBean.getStatus().equals("Failed")) {
//                    if (wb.getSheetAt(2) != null) {
//                        excelprocessingFormBean = iExcelVolAndRevDao.validateVolAndRevSheet(excelprocessingFormBean, wb, user);
//                        session.setAttribute("revenue", excelprocessingFormBean.getAnnual_revenue());
//                        session.setAttribute("volume", excelprocessingFormBean.getVolume_list());
//                    } else {
//                        excelprocessingFormBean.setStatus("Failed");
//                        excelprocessingFormBean.setError_message("Volume and Revenue worksheet missing in WorkBook");
//                    }
//                }
            } else {
                userMesage = "Please upload excel file";
                excelprocessingFormBean.setStatus("Failed");
            }

            model.addObject("usermessage", userMesage);
            model.addObject("processingBean", excelprocessingFormBean);
        } catch (Exception ex) {
            System.out.println("Error in processing controller" + ex);
            excelprocessingFormBean.setStatus("Failed");
            excelprocessingFormBean.setError_message("Uploaded file is corrupted. Please contact administrator.");
            return model;
        }
        return model;
    }

    @RequestMapping(value = "processing.htm", method = RequestMethod.POST)
    public ModelAndView postExcelSheetProcessing(@ModelAttribute("processingBean") ExcelProcessingBean excelprocessingFormBean, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        ModelAndView model = new ModelAndView("processing");
        HttpSession session = request.getSession();
        try {
            String usermessage = "";
            model.addObject("usermessage", usermessage);
            Workbook wb = (Workbook) session.getAttribute("workbook");
            UserBean user = (UserBean) session.getAttribute("user");
//            excelprocessingFormBean.setAnnual_revenue((VolumeAndRevBean) session.getAttribute("revenue"));
//            excelprocessingFormBean.setVolume_list((List<VolumeAndRevBean>) session.getAttribute("volume"));
            exelOperation.saveToHRdataTab(wb, user, excelprocessingFormBean);
            exelOperation.savePermenentTable(user, excelprocessingFormBean.getDate(), excelprocessingFormBean.getBusiness_center_id());
//            excelprocessingFormBean = iExcelVolAndRevDao.setVandRId(excelprocessingFormBean);
//            iExcelVolAndRevDao.saveToTemperory(excelprocessingFormBean);
//            iExcelVolAndRevDao.saveToPermenent(excelprocessingFormBean);

        } catch (Exception ex) {
            System.out.println("ERRROR! post processing failed!!");
            model.addObject("usermessage", "Issues detected  in parsing file");
            ex.printStackTrace();
            return model;
        }
        return new ModelAndView(new RedirectView("hrdatareport.htm"));
    }

    @RequestMapping(value = "overwrite.htm", method = RequestMethod.GET)
    public ModelAndView getOverWriteData(HttpServletRequest request) {

        String date = request.getParameter("d1");
        String bcid = request.getParameter("bcid");
        exelOperation.deleteData(date, bcid);
        //iExcelVolAndRevDao.deleteVolumeAndRevenue(date, bcid);
        return new ModelAndView(new RedirectView("processing.htm"));
    }

    public ExcelProcessingBean getExcelDetails(Workbook wb, ExcelProcessingBean excelProcessingBean, UserBean user) throws IOException, InvalidFormatException {
        Boolean exit_condition = true;
        //excelProcessingBean = exelOperation.validateDataSheetTemplate(excelProcessingBean, wb);
        if (!excelProcessingBean.getStatus().equals("Failed")) {
            Map<Integer, String> headerMap = exelOperation.validateHeader(wb, PropertyFile.excelInt("resource.input.header.starting.rowIndex"), PropertyFile.excelInt("resource.input.header.starting.columIndex"));
            Map<Integer, String> subheaderMap = exelOperation.validateSubBU(wb, PropertyFile.excelInt("resource.input.subheader.starting.rowIndex"), PropertyFile.excelInt("resource.input.subheader.starting.columIndex"));
            Integer[] twoArray = exelOperation.getEmployeeEndingIndex(wb);
            Cell bcCell = BasicExcelOperation.getExcelCell(wb.getSheetAt(1), PropertyFile.excelInt("resource.input.businesscenter.rowIndex"), PropertyFile.excelInt("resource.input.businesscenter.columIndex"), "String");
            excelProcessingBean.setBussiness_center(bcCell.getStringCellValue());
            //excelProcessingBean.setDate(DateConvertion.convertToMysqlFormat(exelOperation.validateDate(wb, PropertyFile.excelInt("resource.input.date.rowIndex"), PropertyFile.excelInt("resource.input.date.columIndex"))));
            excelProcessingBean.setError_message("");
            excelProcessingBean.setLocation_cord(exelOperation.readCell(wb, PropertyFile.excelInt("resource.input.locationcord.rowIndex"), PropertyFile.excelInt("resource.input.locationcord.columIndex"), PropertyFile.excelInt("resource.input.resourcesheet.no")));
            excelProcessingBean.setBusiness_center_id(exelOperation.checkBusinessCenter(excelProcessingBean.getBussiness_center(), user));
            excelProcessingBean.setTotalEntries(twoArray[0]);
            double total = exelOperation.readDoubleCell(wb, twoArray[1], PropertyFile.excelInt("resource.input.employee.totalComp.columIndex"), PropertyFile.excelInt("resource.input.resourcesheet.no")).doubleValue();
            excelProcessingBean.setTotalComp(total);

            if (!excelProcessingBean.getStatus().equals("Failed")) {
                Map<String, List<String>> dataMap = iDataExcelTemplateValidatorDao.validateExcelData(wb, excelProcessingBean, user);
                if (dataMap.containsKey("false")) {
                    excelProcessingBean.setStatus("Failed");
                    excelProcessingBean.setError_message("Missing data in following rows:" + ExcelValidator.getErrorList(dataMap.get("false"), ""));
                }
            }
            if (!excelProcessingBean.getStatus().equals("Failed")) {
                DataEntryBean entryFormBean = exelOperation.readEmployeeDetails(wb, PropertyFile.excelInt("resource.input.employee.rowIndex"), PropertyFile.excelInt("resource.input.employee.columIndex"), headerMap, subheaderMap, twoArray[0],excelProcessingBean);
                entryFormBean.setOrganization_id(user.getOrganizationid());
                List<EmployeeDetailsBean> empList = entryFormBean.getEmployeeDetailsBean();
                Integer empEntryCount = empList.size();




                excelProcessingBean.setProcessedEntry(empEntryCount);
                excelProcessingBean.setProcessedCost(exelOperation.getTotalCompensation(entryFormBean.getEmployeeDetailsBean()));


                if (excelProcessingBean.getBusiness_center_id() != null) {
                    excelProcessingBean.setBusinessc_flag(Boolean.TRUE);
                } else {
                    excelProcessingBean.setBusinessc_flag(Boolean.FALSE);
                }
                Boolean bu_flag = false;
                if (excelProcessingBean.getBusinessc_flag() == true) {
                    bu_flag = exelOperation.insertBUToHashTab(wb, excelProcessingBean.getBusiness_center_id(), excelProcessingBean.getBusiness_id(), user);
                }
                if (bu_flag == false) {
                    String error_message = excelProcessingBean.getError_message();
                    error_message += "Business Unit not found in Database \n";
                    excelProcessingBean.setError_message(error_message);
                    excelProcessingBean.setStatus("Failed");
                    exit_condition = false;

                }
                if (excelProcessingBean.getBusinessc_flag() == true) {
                    if (exelOperation.checkDate(excelProcessingBean.getDate(), excelProcessingBean.getBusiness_center_id()) != false) {
                        Integer totEntries = excelProcessingBean.getTotalEntries();
                        Integer procEntries = excelProcessingBean.getProcessedEntry();
                        if ((totEntries.equals(procEntries)) && (exit_condition == true)) {
                            if (excelProcessingBean.getTotalComp().compareTo(excelProcessingBean.getProcessedCost()) == 0) {
                                if (exelOperation.insertEmployeeTypeToHashTab(wb, excelProcessingBean.getProcessedEntry(), user) == true) {
                                    excelProcessingBean.setProcessFlag(exelOperation.insertProcessToHashTab(wb, excelProcessingBean.getProcessedEntry(), user));
                                    if (excelProcessingBean.getProcessFlag() == true) {
                                        Map<String, Integer> processMap = excelMaster.getProcessMap(user);
                                        if (exelOperation.insertSubProcessToHashTab(wb, empEntryCount, user, processMap) == true) {
                                            excelProcessingBean.setStatus("Success");
                                        } else {
                                            String error_message = excelProcessingBean.getError_message();
                                            error_message += "Sub process invalid.";
                                            excelProcessingBean.setError_message(error_message);
                                            excelProcessingBean.setStatus("Failed");
                                        }
                                    } else {
                                            String error_message = excelProcessingBean.getError_message();
                                            error_message += "Process invalid.";
                                            excelProcessingBean.setError_message(error_message);
                                            excelProcessingBean.setStatus("Failed");
                                        }
                                } else {
                                    String error_message = excelProcessingBean.getError_message();
                                    error_message += "Employee type invalid.";
                                    excelProcessingBean.setError_message(error_message);
                                    excelProcessingBean.setStatus("Failed");
                                }
                            } else {
                                excelProcessingBean.setStatus("Failed");
                                String error_message = excelProcessingBean.getError_message();
                                error_message += "Total cost mismatch.";
                                excelProcessingBean.setError_message(error_message);
                            }

                        } else {
                            excelProcessingBean.setStatus("Failed");

                        }
                    } else {
                        excelProcessingBean.setStatus("Failed");
                        excelProcessingBean.setError_message(excelProcessingBean.getDate() + " Data exists in database");
                        excelProcessingBean.setDate_flag(false);
                    }
                } else {
                    excelProcessingBean.setStatus("Failed");
                    excelProcessingBean.setError_message("Business Center: " + excelProcessingBean.getBussiness_center() + " not found in database");
                }


            }
        }
        return excelProcessingBean;
    }
}
