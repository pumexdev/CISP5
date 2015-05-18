/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.Excel.Bean.ExcelProcessingBean;
import com.pumex.ConnectedInsight.Excel.Bean.KPICenterAvgBean;
import com.pumex.ConnectedInsight.Excel.Bean.KPIScoreBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Vishnu
 */
public interface IKPIDao {

    public ExcelProcessingBean validateKPIWSheet(Workbook wb, ExcelProcessingBean excelProcessingBean, UserBean user);

    public ExcelProcessingBean validateKPITemplate(Workbook wb, ExcelProcessingBean excelProcessingBean);

    public void saveKPICenterAvgTemp(KPICenterAvgBean kPICenterAvgBean,UserBean user);
    
    public void saveKPIScoreTemp(List<KPIScoreBean> kPIScoreBeans,UserBean user);
    
    public void saveToPermenentTable(ExcelProcessingBean excelProcessingBean,UserBean user);
    
    public ExcelProcessingBean setID(ExcelProcessingBean excelProcessingBean);
    
    public void deleteKPI(String date, String bcid);
    
}
