/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.controller;

import com.pumex.ConnectedInsight.Excel.Bean.DataEntryBean;
import com.pumex.ConnectedInsight.Excel.Dao.ExcelExporterDao;
import com.pumex.ConnectedInsight.HRData.DAO.HRDataEntryDAOInterface;
import com.pumex.ConnectedInsight.Property.PropertyFile;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

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
 * Created on:10 July 2012
 * Updated On:4 April 2013
 * Updated by: Vishnu AU
 */
@Controller
public class ExcelExportController {

    @Autowired
    ExcelExporterDao excel_exporter;
    @Autowired
    private HRDataEntryDAOInterface dataEntryDAOInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;

    @RequestMapping(value = "draw.htm", method = RequestMethod.GET)
    public ModelAndView getDraw(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException, InterruptedException {

        ModelAndView model = new ModelAndView();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/GlobalDashBoard11febv3.xlsx");
        String date = request.getParameter("d1");

        String date1 = request.getParameter("d2");
        Workbook wb = WorkbookFactory.create(is);
        wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("globaldashboard.excel.export.graphG1.1"), 0, "Business Center", new Object[]{date});
        wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("globaldashboard.excel.export.graphG1.2"), 2, "Business Center", new Object[]{});
        wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("globaldashboard.excel.export.graphG1.3"), 5, "Business Center", new Object[]{date});
        wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("globaldashboard.excel.export.graphG1.4"), 7, "Business Center", new Object[]{date});
        wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("globaldashboard.excel.export.graphG1.5"), 9, "Business Center", new Object[]{});
        
        wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("globaldashboard.excel.export.graphG2.1"), 12, "Tier", new Object[]{date});
        //wb = excel_exporter.writeToExcel(wb, date, date1, "SELECT CONCAT('Tier',' ',HRDataEmployeeEntry.Tier,' ',TierDesignation.Designation) AS TierDesignation, CASE HRDataEntry.EntryDate WHEN 'date1' THEN COUNT(EmployeeName)  ELSE 0 END  AS 'date1', CASE HRDataEntry.EntryDate WHEN 'date2' THEN COUNT(EmployeeName)  ELSE 0 END  AS 'date2' FROM HRDataEmployeeEntry,HRDataEntry,TierDesignation WHERE HRDataEntry.EntryId=HRDataEmployeeEntry.EntryId AND TierDesignation.Tier=HRDataEmployeeEntry.Tier AND HRDataEntry.EntryDate IN ( 'date1','date2') GROUP BY HRDataEmployeeEntry.Tier,Designation ORDER BY HRDataEmployeeEntry.Tier", 14, "location", new Object[]{});
        wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("globaldashboard.excel.export.graphG2.2"), 14, "Tier", new Object[]{});
        wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("globaldashboard.excel.export.graphG2.3"), 17, "Tier", new Object[]{date, date});
        wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("globaldashboard.excel.export.graphG2.4"), 19, "Tier", new Object[]{});
        wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("globaldashboard.excel.export.graphG2.5"), 22, "Tier", new Object[]{date, 3, date});
        
        wb=excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("globaldashboard.excel.export.graphG3.1"), 24, "Process",new Object[]{date});
        wb=excel_exporter.writeToExcel(wb, date,date1, SQLSelector.getQuery("globaldashboard.excel.export.graphG3.2"), 26, "Process",new Object[]{});
        wb=excel_exporter.writeToExcel(wb, date,SQLSelector.getQuery("globaldashboard.excel.export.graphG3.3"), 29,"Process",new Object[]{date});
//        FileOutputStream fout=new FileOutputStream("D:/ConnectedInsight/src/java/chart_output11.xlsx");
        OutputStream SOS = response.getOutputStream();
        response.setContentType("application/xlsx");
        response.setHeader("content-disposition", "attachment; filename=\"Reports.xlsx\"");
        wb.write(SOS);
        return null;
    }

    @RequestMapping(value = "locdashboard.htm", method = RequestMethod.GET)
    public ModelAndView getLocationDashBoard(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        try {
            String date = request.getParameter("d1");
            String date1 = request.getParameter("d2");
            String buscenterID = request.getParameter("bcid");
            String pid = request.getParameter("pid");
            String spid= request.getParameter("spid");
            //InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/LocationDashboardnew2.xlsx");
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/LocationDashboardV3.xlsx");
            Workbook wb = WorkbookFactory.create(is);
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL1.1"), 0, "Process", new Object[]{buscenterID, date});
            wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("locationdashboard.excel.export.graphL1.2"), 2, "Process", new Object[]{buscenterID});
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL1.3"), 5, "Client", new Object[]{buscenterID, date});
            wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("locationdashboard.excel.export.graphL1.4"), 7, "Client", new Object[]{buscenterID});
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL1.5"), 10, "CostCenter", new Object[]{buscenterID, date});
            wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("locationdashboard.excel.export.graphL1.6"), 12, "CostCenter", new Object[]{buscenterID});
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL1.7"), 15, "Grade", new Object[]{date,buscenterID});
            wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("locationdashboard.excel.export.graphL1.8"), 17, "Grade", new Object[]{buscenterID});
            
            
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL2.1"), 20, "Tier", new Object[]{buscenterID, date});
            wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("locationdashboard.excel.export.graphL2.2"), 22, "Tier", new Object[]{buscenterID});
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL2.3"), 25, "location", new Object[]{buscenterID, date, buscenterID, date});
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL2.4"), 27, "Center", new Object[]{date,3,date});
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL2.5"), 29, "Process", new Object[]{buscenterID,date,buscenterID,3,date});
            /*Query change in graph 2.6*/
            //wb = excel_exporter.writeToExcel(wb, date, "SELECT ProcessName,IFNULL(ROUND(SUM(TotalFTE) /(SELECT SUM(TotalFTE)  FROM HRDataEmployeeEntry,HRDataEntry WHERE HRDataEntry.EntryId=HRDataEmployeeEntry.EntryId  AND HRDataEntry.BusinessCenterId=? AND HRDataEntry.EntryDate=? AND HRDataEmployeeEntry.ProcessId=? ) *100,2),0) AS FTEPERC FROM HRDataEmployeeEntry,HRDataEntry,Process WHERE HRDataEntry.EntryId=HRDataEmployeeEntry.EntryId AND HRDataEmployeeEntry.ProcessId=Process.ProcessId AND HRDataEntry.BusinessCenterId=? AND HRDataEntry.EntryDate=? AND HRDataEmployeeEntry.ProcessId=? AND Tier=? GROUP BY ProcessName ORDER BY ProcessName", 21, "location", new Object[]{buscenterID, date, pid, buscenterID, date, pid, 3});
            //wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graph2.6"), 21, "location", new Object[]{date,3,date});
            
            
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL3.1"), 31, "Cleint", new Object[]{2,1,spid, date, buscenterID});
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL3.2"), 33, "Center", new Object[]{date,spid,1, 2, date, spid});
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL3.3"), 35, "Client", new Object[]{2, 1, spid, date,buscenterID});
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL3.4"), 37, "Center", new Object[]{date, spid, 1, 2, date, spid});
            
            
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL4.1"), 39, "Client", new Object[]{date,buscenterID});
            wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("locationdashboard.excel.export.graphL4.2"), 41, "Client", new Object[]{buscenterID});
            wb = excel_exporter.writeToExcel(wb, date, SQLSelector.getQuery("locationdashboard.excel.export.graphL4.3"), 44, "Client", new Object[]{1, 1, date, buscenterID});
            wb = excel_exporter.writeToExcel(wb, date, date1, SQLSelector.getQuery("locationdashboard.excel.export.graphL4.4"), 46, "Client", new Object[]{1,1,1,1,buscenterID});
            
            OutputStream SOS = response.getOutputStream();
            response.setContentType("application/xlsx");
            response.setHeader("content-disposition", "attachment; filename=\"Reports.xlsx\"");
            wb.write(SOS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "procdashboard.htm", method = RequestMethod.GET)
    public ModelAndView getProcessDashBoard(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        String date1 = request.getParameter("d1");
        String date2 = request.getParameter("d2");
        String processid = request.getParameter("pid");
        String bcid = request.getParameter("bcid");
        String tier = request.getParameter("tier");
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/process.xlsx");
        Workbook wb = WorkbookFactory.create(is);
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("processdashboard.excel.export.graphP1.1"), 0, "location", new Object[]{processid, date1});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("processdashboard.excel.export.graphP1.2"), 2, "location", new Object[]{processid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("processdashboard.excel.export.graphP1.3"), 5, "location", new Object[]{processid, date1});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("processdashboard.excel.export.graphP1.4"), 7, "location", new Object[]{processid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("processdashboard.excel.export.graphP1.5"), 10, "location", new Object[]{processid,date1});
        
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("processdashboard.excel.export.graphP2.1"), 13, "location", new Object[]{processid, date1});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("processdashboard.excel.export.graphP2.2"), 15, "location", new Object[]{processid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("processdashboard.excel.export.graphP2.3"), 18, "location", new Object[]{processid, date1, processid, date1});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("processdashboard.excel.export.graphP2.4"), 25, "location", new Object[]{processid,processid,processid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("processdashboard.excel.export.graphP2.5"), 23, "location", new Object[]{date1, processid,date1, tier,processid});
        OutputStream SOS = response.getOutputStream();
        response.setContentType("application/xlsx");
        response.setHeader("content-disposition", "attachment; filename=\"Reports.xlsx\"");
        wb.write(SOS);
        return null;
    }

    @RequestMapping(value = "subprocdashboard.htm", method = RequestMethod.GET)
    public ModelAndView getSubprocessDashBoard(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        String date1 = request.getParameter("d1");
        String date2 = request.getParameter("d2");
        String subprocessid = request.getParameter("spid");
        String bcid = request.getParameter("bcid");
        String tier = request.getParameter("tier");
        String procid = "1";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/subprocess2503.xlsx");
        Workbook wb = WorkbookFactory.create(is);
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS1.1"), 0, "Location", new Object[]{subprocessid, date1});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS1.2"), 2, "Location", new Object[]{subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS2.1"), 5, "Tier", new Object[]{subprocessid, date1});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS2.2"), 7, "Tier", new Object[]{subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS2.3"), 10, "Tier", new Object[]{subprocessid, date1, subprocessid, date1});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS3.1"), 12, "Location", new Object[]{2, 1, subprocessid, date1});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS1.3"), 14, "Location", new Object[]{date1, subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS1.4"), 16, "BusinessCenter", new Object[]{subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS1.5"), 19, "BusinessCenter", new Object[]{date1, subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS2.5"), 21, "BusinessCenter", new Object[]{date1, subprocessid,date1, subprocessid,3});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS2.4"), 23, "Tier", new Object[]{subprocessid,subprocessid,subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS3.2"), 26, "Center", new Object[]{subprocessid,subprocessid,1,2,subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS3.3"), 29, "Center", new Object[]{subprocessid,date1,subprocessid,date1});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS3.4"), 31, "Client", new Object[]{subprocessid,subprocessid,subprocessid});
        OutputStream SOS = response.getOutputStream();
        response.setContentType("application/xlsx");
        response.setHeader("content-disposition", "attachment; filename=\"Reports.xlsx\"");
        wb.write(SOS);
        return null;
    }

    @RequestMapping(value = "excelexportview.htm", method = RequestMethod.GET)
    public ModelAndView getExcelExportData(@ModelAttribute("ExcelExportView") DataEntryBean dataEntryBean, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("excelexportview");
        return model;
    }

    @RequestMapping(value = "excelexportview.htm", method = RequestMethod.POST)
    public ModelAndView getExcelExportData(@ModelAttribute("ExcelExportView") DataEntryBean dataEntryBean, HttpServletRequest request) {
        List hrData = new ArrayList();
        hrData = dataEntryDAOInterface.searchHRData(dataEntryBean, CommonUtil.getUserBean(request));
        ModelAndView model = new ModelAndView("excelexportview");
        model.addObject("hrlist", hrData);
        hrData = new ArrayList();
        hrData.add("Hello");
        hrData.add("Hello1");
        hrData.add("Hello2");
        model.addObject("test", hrData);
        return model;
    }

    @RequestMapping(value = "excelexport.htm", method = RequestMethod.GET)
    public ModelAndView getExcelFromDB(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        if (excel_exporter.hasEntry(request.getParameter("e_date"), request.getParameter("bc_id")) > 0) {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(PropertyFile.excel("exportexcel.filename"));
            Workbook wb = WorkbookFactory.create(is);
            String entry_date = request.getParameter("e_date");
            String bcid = request.getParameter("bc_id");
            excel_exporter.setExcelSheet(wb, bcid, entry_date);
            OutputStream SOS = response.getOutputStream();
            response.setContentType("application/xlsx");
            response.setHeader("content-disposition", "attachment; filename=\"Excel_data.xlsx\"");
            wb.write(SOS);
            return null;
        } else {
            ModelAndView model = new ModelAndView(new RedirectView("excelexportview.htm"));
            model.addObject("message", "No Results found");
            return null;
        }
    }

    @RequestMapping(value = "excelexportall.htm", method = RequestMethod.GET)
    public ModelAndView getExcelExportAll(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        if (excel_exporter.hasEntry(request.getParameter("e_date")) > 0) {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(PropertyFile.excel("exportallexcel.filename"));
            String entry_date = request.getParameter("e_date");
            Workbook wb = WorkbookFactory.create(is);
            wb = excel_exporter.setAllDataToExcel(wb, entry_date);
            OutputStream SOS = response.getOutputStream();
            response.setContentType("application/xlsx");
            response.setHeader("content-disposition", "attachment; filename=\"Excel_data_all.xlsx\"");
            wb.write(SOS);
            return null;
        } else {
            ModelAndView model = new ModelAndView(new RedirectView("excelexportview.htm"));
            model.addObject("message", "No Results found");
            return null;
        }
    }

    @RequestMapping(value = "processexport.htm", method = RequestMethod.GET)
    public ModelAndView getExcelProcess(HttpServletResponse response) throws IOException, InvalidFormatException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("Process_template.xlsx");
        Workbook wb = WorkbookFactory.create(is);
        wb = excel_exporter.setProcess(wb, PropertyFile.excelInt("exportexcel.process.sheetno"), PropertyFile.excelInt("exportexcel.process.columcount"), PropertyFile.excelInt("exportexcel.process.rowcount"));
        OutputStream SOS = response.getOutputStream();
        response.setContentType("application/xlsx");
        response.setHeader("content-disposition", "attachment; filename=\"Excel_Process.xlsx\"");
        wb.write(SOS);
        return null;
    }

    @RequestMapping(value = "subprocessexport.htm", method = RequestMethod.GET)
    public ModelAndView getExcelSubprocess(HttpServletResponse response) throws IOException, InvalidFormatException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("Subprocess_template.xlsx");
        Workbook wb = WorkbookFactory.create(is);
        wb = excel_exporter.setSubProcess(wb, PropertyFile.excelInt("exportexcel.subprocess.sheetno"), PropertyFile.excelInt("exportexcel.subprocess.columcount"), PropertyFile.excelInt("exportexcel.subprocess.rowcount"));
        OutputStream SOS = response.getOutputStream();
        response.setContentType("application/xlsx");
        response.setHeader("content-disposition", "attachment; filename=\"Excel_subprocess.xlsx\"");
        wb.write(SOS);
        return null;
    }
    
    @RequestMapping(value="businessunitexport.htm",method= RequestMethod.GET)
    public ModelAndView getExcelBusinessUnit(HttpServletResponse response) throws IOException, InvalidFormatException{
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("BusinessUnit_template.xlsx");
        Workbook wb = WorkbookFactory.create(is);
        Map<String, List<String>> dataMap=excel_exporter.getBusinessUnitData();
        wb=excel_exporter.getBusinessUnit(wb, dataMap);
        OutputStream SOS = response.getOutputStream();
        response.setContentType("application/xlsx");
        response.setHeader("content-disposition", "attachment; filename=\"Excel_BusinessUnit.xlsx\"");
        wb.write(SOS);
        return null;
    }
    
    @RequestMapping(value="excelexportajax.htm",method = RequestMethod.POST)
    public ModelAndView getExcelAjax(HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView("jsonView");
        if (excel_exporter.hasEntry(request.getParameter("e_date"), request.getParameter("bc_id")) > 0) {
            model.addObject("check", true);
        }
        return model;
    }
    
    @RequestMapping(value="excelexportallajax.htm",method = RequestMethod.POST)
    public ModelAndView getExcelAllAjax(HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView("jsonView");
        if (excel_exporter.hasEntry(request.getParameter("e_date")) > 0) {
            model.addObject("check", true);
        }
        return model;
    }
    
    @RequestMapping(value = "targetdashboardexcel.htm", method = RequestMethod.GET)
    public ModelAndView getTargetDashBoard(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        String date1 = request.getParameter("d1");
        String date2 = request.getParameter("d2");
        String subprocessid = request.getParameter("spid");
        String bcid = request.getParameter("bcid");
        String tier = request.getParameter("tier");
        String procid = "1";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/targetdashboard2503.xlsx");
        Workbook wb = WorkbookFactory.create(is);
        //wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS1.1"), 0, "Location", new Object[]{subprocessid, date1});
        //wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS1.2"), 2, "Location", new Object[]{subprocessid});
        //wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS2.1"), 5, "Tier", new Object[]{subprocessid, date1});
        //wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS2.2"), 7, "Tier", new Object[]{subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("targetdashboard.excel.export.graphT2.3"), 10, "Tier", new Object[]{subprocessid, date1, subprocessid, date1});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("targetdashboard.excel.export.graphT3.1"), 12, "Location", new Object[]{2, 1, subprocessid, date1});
        //wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS1.3"), 14, "Location", new Object[]{date1, subprocessid});
        //wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS1.4"), 16, "BusinessCenter", new Object[]{subprocessid});
        //wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("subprocessdashboard.excel.export.graphS1.5"), 19, "BusinessCenter", new Object[]{date1, subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("targetdashboard.excel.export.graphT2.5"), 21, "BusinessCenter", new Object[]{date1, subprocessid,date1, subprocessid,3});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("targetdashboard.excel.export.graphT2.4"), 23, "Tier", new Object[]{subprocessid,subprocessid,subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("targetdashboard.excel.export.graphT3.2"), 26, "Center", new Object[]{subprocessid,subprocessid,1,2,subprocessid});
        wb = excel_exporter.writeToExcel(wb, date1, SQLSelector.getQuery("targetdashboard.excel.export.graphT3.3"), 29, "Center", new Object[]{subprocessid,date1,subprocessid,date1});
        wb = excel_exporter.writeToExcel(wb, date1, date2, SQLSelector.getQuery("targetdashboard.excel.export.graphT3.4"), 31, "Client", new Object[]{subprocessid,subprocessid,subprocessid});
        OutputStream SOS = response.getOutputStream();
        response.setContentType("application/xlsx");
        response.setHeader("content-disposition", "attachment; filename=\"Reports.xlsx\"");
        wb.write(SOS);
        return null;
    }
    
    @RequestMapping(value = "actionexport.htm", method = RequestMethod.GET)
    public ModelAndView actionexport(HttpServletResponse response) throws IOException, InvalidFormatException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("Actions_template.xlsx");
        Workbook wb = WorkbookFactory.create(is);
        wb = excel_exporter.setAction(wb, PropertyFile.excelInt("exportexcel.subprocess.sheetno"), PropertyFile.excelInt("exportexcel.subprocess.columcount"), PropertyFile.excelInt("exportexcel.subprocess.rowcount"));
        OutputStream SOS = response.getOutputStream();
        response.setContentType("application/xlsx");
        response.setHeader("content-disposition", "attachment; filename=\"Excel_action.xlsx\"");
        wb.write(SOS);
        return null;
    }

    @ModelAttribute("entryDates")
    public Map getEntryDates(HttpServletRequest request) {
        return dataEntryDAOInterface.getEntryDates();

    }

    @ModelAttribute("businesscenterlist")
    public Map getUserStatus(HttpServletRequest request) {
        return dropdownDaoInterface.getBusinessCenterMap(CommonUtil.getUserBean(request));
    }
}
