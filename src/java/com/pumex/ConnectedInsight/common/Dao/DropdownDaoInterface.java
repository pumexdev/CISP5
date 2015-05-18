/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.common.Dao;

import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface DropdownDaoInterface
{

    public Map getBusinessMap(UserBean user);

    public Map getBusinessUnitMap(String businessId);

    public Map getBusinessCenterMap(UserBean user, String businessId);
    
    public Map getBusinessCenterMap(UserBean user);

    public Map getProcessMap(UserBean user);
    
    public Map getStatus();
    
    public Map getActiveBusinessMap(UserBean user);

    public Map getActiveBusinessUnitMap(String businessId);

    public Map getActiveProcessMap(UserBean user);
    
    public Map getemployeeType();   
    
    public Map getStatementDateMap();
    
    public Map getMetricMap(UserBean user);
}