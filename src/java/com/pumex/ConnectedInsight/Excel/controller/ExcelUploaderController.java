/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.controller;

import com.pumex.ConnectedInsight.Excel.Dao.ExcelMasterDao;
import com.pumex.ConnectedInsight.Excel.Dao.ExcelOperationDao;
import com.pumex.ConnectedInsight.Excel.Dao.ExcelUploaderDao;
import com.pumex.ConnectedInsight.Excel.Dao.ExcelUploaderDaoImpl;
import com.pumex.ConnectedInsight.Property.DateConvertion;
import com.pumex.ConnectedInsight.Property.PropertyFile;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.common.bean.FileUploadBean;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vishnu AU
 * Created on:10 July 2012
 * Updated On:4 April 2013
 * Updated by: Vishnu AU
 */
@Controller
public class ExcelUploaderController {

    @Autowired
    ExcelOperationDao excel_operation;
    @Autowired
    ExcelMasterDao excel_master;
    @Autowired
    DropdownDaoInterface dropdownDao;

    @RequestMapping(value = "exceluploader.htm", method = RequestMethod.GET)
    public ModelAndView getExcelUploader(@ModelAttribute("Multipart") FileUploadBean fileUploadBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("exceluploader");
        model.addObject("dateMap", dropdownDao.getStatementDateMap());
        return model;
    }

    @RequestMapping(value = "exceluploader.htm", method = RequestMethod.POST)
    public ModelAndView doExcelUploader(@ModelAttribute("Multipart") FileUploadBean fileUploadBean, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        ModelAndView model = new ModelAndView("exceluploader");
        model.addObject("dateMap", dropdownDao.getStatementDateMap());
        ExcelUploaderDao excelUploaderObject = new ExcelUploaderDaoImpl();
        String error_message = "";
        try {
            InputStream in = excelUploaderObject.uploadFile(fileUploadBean);

            if (in.available() > 0) {
                Workbook wb = WorkbookFactory.create(in);
                HttpSession session = request.getSession();
                session.setAttribute("workbook", wb);
                
                String entry_date = DateConvertion.convertToMMddYYDate(excel_operation.validateDate(wb, PropertyFile.excelInt("resource.input.date.rowIndex"), PropertyFile.excelInt("resource.input.date.columIndex")));
                String sesDate=DateConvertion.convertToMysqlFormat(excel_operation.validateDate(wb, PropertyFile.excelInt("resource.input.date.rowIndex"), PropertyFile.excelInt("resource.input.date.columIndex")));
                String st_date=fileUploadBean.getStatement_date();
                session.setAttribute("statementdate",sesDate);
                Boolean st_flag=excelUploaderObject.checkStatementDate(st_date, entry_date);
                if(st_flag==true){
                    model.addObject("location", sesDate);
                model.addObject("usermessage", error_message);
                }
                else{
                error_message = "Date mismatch with Statement Date.";  
                session.setAttribute("workbook", "");
                model.addObject("usermessage", error_message);
                model.addObject("location", "");
                }
            } else {
                error_message = "File validation failed. Please contact administrator.";
                model.addObject("usermessage", error_message);
                model.addObject("location", "");
            }
            return model;
        } catch (Exception ex) {
            error_message = "File validation failed. Please contact administrator.";
            model.addObject("usermessage", error_message);
            model.addObject("location", "");
            return model;
        }
    }
    
    @RequestMapping(value = "volkpiuploader.htm", method = RequestMethod.GET)
    public ModelAndView getVolKPIExcelUploader(@ModelAttribute("Multipart") FileUploadBean fileUploadBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("volkpiuploader");
        model.addObject("dateMap", dropdownDao.getStatementDateMap());
        return model;
    }
    
    @RequestMapping(value = "volkpiuploader.htm", method = RequestMethod.POST)
    public ModelAndView doVolKPIExcelUploader(@ModelAttribute("Multipart") FileUploadBean fileUploadBean, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        ModelAndView model = new ModelAndView("volkpiuploader");
        Integer volumeSheetno=PropertyFile.excelInt("volumerevenue.sheetno");
        Integer kpiSheetno=PropertyFile.excelInt("kpi.sheetno");
        model.addObject("dateMap", dropdownDao.getStatementDateMap());
        ExcelUploaderDao excelUploaderObject = new ExcelUploaderDaoImpl();
        String error_message = "";
        try {
            InputStream in = excelUploaderObject.uploadFile(fileUploadBean);

            if (in.available() > 0) {
                Workbook wb = WorkbookFactory.create(in);
                HttpSession session = request.getSession();
                session.setAttribute("workbook", wb);
                String entry_date = DateConvertion.convertToMMddYYDate(excel_operation.validateDate(wb, volumeSheetno, 6, 4));
                String ses_date=DateConvertion.convertToMysqlFormat(excel_operation.validateDate(wb, volumeSheetno, 6, 4));
                String st_date=fileUploadBean.getStatement_date();
                
                Boolean st_flag=excelUploaderObject.checkStatementDate(st_date, entry_date);
                if(st_flag==true){
                    model.addObject("location", ses_date);
                model.addObject("usermessage", error_message);
                }
                else{
                error_message = "Date mismatch with Statement Date.";  
                session.setAttribute("workbook", "");
                model.addObject("usermessage", error_message);
                model.addObject("location", "");
                }
            } else {
                error_message = "File validation failed. Please contact administrator.";
                model.addObject("usermessage", error_message);
                model.addObject("location", "");
            }
            return model;
        } catch (Exception ex) {
            error_message = "File validation failed. Please contact administrator.";
            model.addObject("usermessage", error_message);
            model.addObject("location", "");
            return model;
        }
    }
}
