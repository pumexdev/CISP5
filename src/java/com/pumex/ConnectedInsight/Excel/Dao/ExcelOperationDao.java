/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.Excel.Bean.DataEntryBean;
import com.pumex.ConnectedInsight.Excel.Bean.EmployeeBUBean;
import com.pumex.ConnectedInsight.Excel.Bean.EmployeeDetailsBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExcelProcessingBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Vishnu AU
 * Created on:09 July 2012
 * Updated On:31 July 2012
 * Updated by: Vishnu AU
 */
public interface ExcelOperationDao {
    public DataEntryBean excelPreprocessing(Workbook wb)throws IOException,InvalidFormatException;
    
    public String validateBussinessCenter(Workbook wb,Integer rowCount,Integer columCount)throws IOException,InvalidFormatException;
    
    public Date validateDate(Workbook wb,Integer rowCount,Integer columCount)throws IOException,InvalidFormatException;
    
    public Date validateDate(Workbook wb,Integer sheetNo,Integer rowCount,Integer columCount)throws IOException,InvalidFormatException;
    
    public String validateLocCordinator(Workbook wb,Integer rowCount,Integer columCount)throws IOException,InvalidFormatException;
    
    public String validateBussiness(Workbook wb,Integer rowCount,Integer columCount)throws IOException,InvalidFormatException;
    
    public Map<Integer,String> validateSubBU(Workbook wb,Integer rowCount,Integer columCount)throws IOException,InvalidFormatException;
    
    public Boolean checkSubBU(String subBUName);
    
    public Map<Integer,String> validateHeader(Workbook wb,Integer rowCount,Integer columCount)throws IOException,InvalidFormatException;
    
    public Boolean checkHeader(String headerName,Integer headerCount);
    
    public DataEntryBean readEmployeeDetails(Workbook wb,Integer rowCount,Integer columCount,Map<Integer,String> mapHeader,Map<Integer,String>mapSBU,Integer totalEntries,ExcelProcessingBean excelProcessingBean)throws IOException,InvalidFormatException;
    
    public List<EmployeeDetailsBean> getEmployeeDetails(Workbook wb,Integer rowCount,Integer columCount,Map<Integer,String> headerMap,Map<Integer,String> subMap,Integer totalEntries)throws IOException,InvalidFormatException;
    
    public List<EmployeeBUBean> getEmpBUDetails(Row row,Map<Integer,String> subMap,Integer startColCount)throws IOException,InvalidFormatException;
    
    public String readCell(Workbook wb,Integer rowCount,Integer columCount,Integer sheetno) throws IOException,InvalidFormatException;
    
    public Double readDoubleCell(Workbook wb,Integer rowIndex,Integer columIndex,Integer sheetno) throws IOException,InvalidFormatException;
    
    public Boolean checkBURowLimit(Workbook wb,Integer rowIndex,Integer columIndex) throws IOException,InvalidFormatException;
    
    public Integer checkBusinessCenter(String businessCenter_Name,UserBean user) throws IOException,InvalidFormatException;
    
    public Integer checkBusiness(String businessName,UserBean user) throws IOException,InvalidFormatException;
    
    public Boolean checkDate(String date,Integer bcid);
    
    public Integer[] getEmployeeEndingIndex(Workbook wb);
    
    public Double getTotalCompensation(List<EmployeeDetailsBean> empDetailedFormBean);
    
    public Boolean insertProcessToHashTab(Workbook wb,Integer totalEntries,UserBean user);
    
    public String readCell(Row row,Integer columIndex);
    
    public Boolean insertSubProcessToHashTab(Workbook wb,Integer totalEntries,UserBean user,Map<String,Integer> processMap);
    
    public Boolean insertEmployeeTypeToHashTab(Workbook wb,Integer totalEntries,UserBean user);
    
    public Boolean insertBUToHashTab(Workbook wb,Integer busCenterId,Integer busId,UserBean user);
    
    public void saveToHRdataTab(Workbook wb,UserBean user,ExcelProcessingBean excelProcessingBean)throws IOException,InvalidFormatException;
    
    public void savePermenentTable(UserBean user,String entryDate,Integer bcid);
    
    public void deleteData(String date,String bcid);
    
    public List<EmployeeDetailsBean> setBusinessUnitCost(List<EmployeeDetailsBean> emplBeanList);
    
    public ExcelProcessingBean validateDataSheetTemplate(ExcelProcessingBean excelProcessingBean,Workbook wb);
}
