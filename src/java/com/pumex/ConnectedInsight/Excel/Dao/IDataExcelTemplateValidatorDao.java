/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.Excel.Bean.ExcelProcessingBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Vishnu
 */
public interface IDataExcelTemplateValidatorDao {
    
    public ExcelProcessingBean validateBusinessCenter(Workbook wb,ExcelProcessingBean excelProcessingBean);
    
    public ExcelProcessingBean validateStatementDate(Workbook wb,ExcelProcessingBean excelProcessingBean);
    
    public ExcelProcessingBean validateExcelHeader(Workbook wb,ExcelProcessingBean excelProcessingBean);
    
    public ExcelProcessingBean validateTotalLabel(Workbook wb,ExcelProcessingBean excelProcessingBean);
    
    public Map<String,List<String>> validateExcelData(Workbook wb,ExcelProcessingBean excelProcessingBean,UserBean user);
}
