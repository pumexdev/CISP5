/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.Excel.Bean.ExportEmployeeBUBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExportEmployeeFormBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExportEntryFormBean;
import com.pumex.ConnectedInsight.common.bean.ListBean;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Vishhhnu
 */
public interface ExcelExporterDao {
    public Workbook writeToExcel(Workbook wb,String date,String date1,String sql_Query,Integer colum_start,String xtitle,Object[] obj);
    
    public Workbook writeToExcel(Workbook wb,String date,String sql_query,Integer colum_start,String xtitle,Object[] obj);
    
    public Workbook setExcelSheet(Workbook wb,String bc_id,String date);
    
    public ExportEntryFormBean setEntryFormBean(String bc_id,String date);
    
    public Workbook writeEntryFormBean(ExportEntryFormBean exportEntryBean,Workbook wb);
    
    public Workbook writeStringData(Workbook wb,Integer row,Integer column,String data);
    
    public Workbook writeDoubleData(Workbook wb,Integer row,Integer column,Double data);
    
    public Workbook writeDateData(Workbook wb,Integer row,Integer column,Date date);
    
    public Workbook writeEmployeedata(Workbook wb,List<ExportEmployeeFormBean> exportEmpFormBeanList);
    
    public List<ExportEmployeeFormBean> setEmpoyeeFormBean(String entry_id);
    
    public List<ExportEmployeeFormBean> setEmpoyeeFormBean(String entry_id,String bcid);
    
    public Workbook writeBusinessUnit(Workbook wb,List businessList);
    
    public List<ListBean> getBUList(String bc_id);
    
    public List<ExportEmployeeBUBean> setExportBUBean(String employee_id);
    
    public List<ExportEmployeeBUBean> setExportBUBean(String employee_id,String bcid);
    
    public Double getTotal(List<ExportEmployeeFormBean> exportEmployeeBeanList);
    
    public Workbook setTotal(Workbook wb,Double total);
    
    public Workbook setProcess(Workbook wb);
    
    public Workbook setSubProcess(Workbook wb);
    
    public Workbook setProcess(Workbook wb,Integer sheetno,Integer colum_count,Integer row_count);
    
    public Workbook setSubProcess(Workbook wb,Integer sheetno,Integer colum_count,Integer row_count);
    
    public List<ExportEntryFormBean> setEntryBeanList(String entry_date);
    
    public Workbook setAllDataToExcel(Workbook wb,String entry_date);
    
    public String getClientName(String id);
    
    public Workbook getGlobalWorkBook(Workbook wb);
    
    public Workbook getLocationWorkBook(Workbook wb);
    
    public Workbook getProcessWorkBook(Workbook wb);
    
    public Workbook getSubProcessWorkBook(Workbook wb);
    
    public Integer hasEntry(String date,String bus_center);
    
    public Integer hasEntry(String date);
    
    public Workbook getBusinessUnit(Workbook wb,Map<String,List<String>> dataMap);
    
    public Map<String,List<String>> getBusinessUnitData();
    
    public Workbook setAction(Workbook wb,Integer sheetno,Integer colum_count,Integer row_count);
}
