/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.Excel.Bean.BUActualBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExcelProcessingBean;
import com.pumex.ConnectedInsight.Excel.Bean.VolumeAndRevBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author user
 */
public interface IExcelVolAndRevDao {
    public void testVolume(Workbook wb);
    
    public ExcelProcessingBean getExcelDetails(Workbook wb, ExcelProcessingBean excelProcessingBean, UserBean user);
    
    public ExcelProcessingBean validateVolAndRevSheet(ExcelProcessingBean excelProcessingBean,Workbook wb,UserBean user);
    
    public VolumeAndRevBean getAnnualRevenue(ExcelProcessingBean excelProcessingBean,Sheet sheet,Integer rowNo,Integer colNo,UserBean user,Map<Integer,String> clientColumMap,Integer paramType);
    
    public Map<String,List<String>>  validateBussinessUnit(List<BUActualBean> bu_actual_list);
    
    public List<VolumeAndRevBean> getVolumePerSubProccess(Sheet sheet,Integer rowNo,Integer colNo,ExcelProcessingBean excelProcessingBean,Integer volDescColNo,Map<Integer,String> subProcessRowMap,UserBean user,Integer paramType,Map<Integer,String> clientColumMap);
    
    public List<VolumeAndRevBean> removeNullBeanFromList(List<VolumeAndRevBean> volumeAndRevBeans);
    
    public Map<String,Map<String, List<String>>> validateVolumeData(List<VolumeAndRevBean> volumeAndRevBeans);
    
    public Map<String,Map<String, List<String>>> sortVolumeDataByFalseList(Map<String,Map<String, List<String>>> inputMap);
    
    public String getVolumedataErrorMessage(Map<String,Map<String, List<String>>> inputMap, String message);
    
    public void deleteVolumeAndRevenue(String date,String bcid);
    
    public void saveVolumeBean(VolumeAndRevBean volumeAndRevBean);
    
    public void saveToPermenent(ExcelProcessingBean excelProcessingBean);
    
    public void saveToTemperory(ExcelProcessingBean excelProcessingBean);
    
    public ExcelProcessingBean setVandRId(ExcelProcessingBean excelProcessingBean);
}
