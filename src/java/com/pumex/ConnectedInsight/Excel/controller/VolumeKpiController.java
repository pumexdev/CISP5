/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.controller;

import com.pumex.ConnectedInsight.Excel.Bean.ExcelProcessingBean;
import com.pumex.ConnectedInsight.Excel.Bean.KPICenterAvgBean;
import com.pumex.ConnectedInsight.Excel.Bean.KPIScoreBean;
import com.pumex.ConnectedInsight.Excel.Bean.VolumeAndRevBean;
import com.pumex.ConnectedInsight.Excel.Dao.ExcelMasterDao;
import com.pumex.ConnectedInsight.Excel.Dao.ExcelOperationDao;
import com.pumex.ConnectedInsight.Excel.Dao.IExcelVolAndRevDao;
import com.pumex.ConnectedInsight.Excel.Dao.IKPIDao;
import com.pumex.ConnectedInsight.Property.PropertyFile;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
 * Created on:10 Feb 2013
 * Updated On:4 April 2013
 * Updated by: Vishnu AU
 */
@Controller
public class VolumeKpiController {

    @Autowired
    ExcelOperationDao exelOperation;
    @Autowired
    ExcelMasterDao excelMaster;
    @Autowired
    IExcelVolAndRevDao iExcelVolAndRevDao;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    IKPIDao iKPIDao;

    @RequestMapping(value = "volkpiprocessing.htm", method = RequestMethod.GET)
    public ModelAndView getVolumeKPIProcessing(@ModelAttribute("processingBean") ExcelProcessingBean excelprocessingFormBean, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        HttpSession session = request.getSession();
        Integer volumeSheetno=PropertyFile.excelInt("volumerevenue.sheetno");
        Integer kpiSheetno=PropertyFile.excelInt("kpi.sheetno");
        ModelAndView model = new ModelAndView("volkpiprocessing");
        try {
            Workbook wb = (Workbook) session.getAttribute("workbook");
            UserBean user = (UserBean) session.getAttribute("user");
            String userMesage = "";
            if (wb != null) {


                if (wb.getSheetAt(volumeSheetno) != null) {
                    excelprocessingFormBean = iExcelVolAndRevDao.getExcelDetails(wb, excelprocessingFormBean, user);
                    if (excelprocessingFormBean.getBusinessc_flag() == true) {
                        excelprocessingFormBean = iExcelVolAndRevDao.validateVolAndRevSheet(excelprocessingFormBean, wb, user);
                        session.setAttribute("revenue", excelprocessingFormBean.getAnnual_revenue());
                        session.setAttribute("volume", excelprocessingFormBean.getVolume_list());
                    } else {
                        excelprocessingFormBean.setStatus("Failed");
                        excelprocessingFormBean.setError_message("Business Center:" + excelprocessingFormBean.getBussiness_center() + "not found in database.");
                    }
                } else {
                    excelprocessingFormBean.setStatus("Failed");
                    excelprocessingFormBean.setError_message("Volume and Revenue worksheet missing in Excel.");
                }
                if (!excelprocessingFormBean.getStatus().equals("Failed")) {
                    if (wb.getSheetAt(kpiSheetno) != null) {
                        excelprocessingFormBean=iKPIDao.validateKPIWSheet(wb, excelprocessingFormBean, user);
                        session.setAttribute("kpicenteravg", excelprocessingFormBean.getkPICenterAvgBean());
                        session.setAttribute("kpiscore", excelprocessingFormBean.getkPIScoreBeans());
                    } else {
                        excelprocessingFormBean.setStatus("Failed");
                        excelprocessingFormBean.setError_message("KPI worksheet missing in Excel.");
                    }
                }

            } else {
                userMesage = "Please upload excel file";
                excelprocessingFormBean.setStatus("Failed");
            }

            model.addObject("usermessage", userMesage);
            model.addObject("processingBean", excelprocessingFormBean);
        } catch (Exception ex) {
            System.out.println("Error in processing controller" + ex);
            excelprocessingFormBean.setStatus("Failed");
            excelprocessingFormBean.setError_message("File validation failed. Please contact administrator.");
            return model;
        }
        return model;
    }

    @RequestMapping(value = "volkpiprocessing.htm", method = RequestMethod.POST)
    public ModelAndView postVolumeKPIExcelProcessing(@ModelAttribute("processingBean") ExcelProcessingBean excelprocessingFormBean, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        ModelAndView model = new ModelAndView("volkpiprocessing");
        HttpSession session = request.getSession();
        try {
            String usermessage = "";
            model.addObject("usermessage", usermessage);
            Workbook wb = (Workbook) session.getAttribute("workbook");
            UserBean user = (UserBean) session.getAttribute("user");
            excelprocessingFormBean.setAnnual_revenue((VolumeAndRevBean) session.getAttribute("revenue"));
            excelprocessingFormBean.setVolume_list((List<VolumeAndRevBean>) session.getAttribute("volume"));
            excelprocessingFormBean.setkPICenterAvgBean((KPICenterAvgBean)session.getAttribute("kpicenteravg"));
            excelprocessingFormBean.setkPIScoreBeans((List<KPIScoreBean>)session.getAttribute("kpiscore"));
            excelprocessingFormBean = iExcelVolAndRevDao.setVandRId(excelprocessingFormBean);
            excelprocessingFormBean=iKPIDao.setID(excelprocessingFormBean);
            iExcelVolAndRevDao.saveToTemperory(excelprocessingFormBean);
            iExcelVolAndRevDao.saveToPermenent(excelprocessingFormBean);
            iKPIDao.saveKPICenterAvgTemp(excelprocessingFormBean.getkPICenterAvgBean(),user);
            iKPIDao.saveKPIScoreTemp(excelprocessingFormBean.getkPIScoreBeans(),user);
            iKPIDao.saveToPermenentTable(excelprocessingFormBean,user);

        } catch (Exception ex) {
            System.out.println("ERRROR! post processing failed!!");
            model.addObject("usermessage", "File validation failed. Please contact administrator.");
            ex.printStackTrace();
            return model;
        }
        return new ModelAndView(new RedirectView("addactualvalue.htm"));
    }

    @RequestMapping(value = "overwritevolkpi.htm", method = RequestMethod.GET)
    public ModelAndView getOverWriteVolKPIData(HttpServletRequest request) {

        String date = request.getParameter("d1");
        String bcid = request.getParameter("bcid");
        iExcelVolAndRevDao.deleteVolumeAndRevenue(date, bcid);
        iKPIDao.deleteKPI(date, bcid);
        return new ModelAndView(new RedirectView("volkpiprocessing.htm"));
    }
}
