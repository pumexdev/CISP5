/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.KPI.DAO;

import com.pumex.ConnectedInsight.Business.beans.KPIBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu
 */
@Repository
public interface KPIDAOInterface
{
    public List getKpiList();
    
    public Map getKPIList(KPIBean kPIBean);
    
    public void addKpiData(KPIBean kPIBean,UserBean userBean);
    
    public Map getKpis(String subProcessId);
}
