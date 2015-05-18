/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pumex.ConnectedInsight.target.dao;

import com.pumex.ConnectedInsight.login.beans.UserBean;
import com.pumex.ConnectedInsight.target.beans.GlobalTotalBean;
import com.pumex.ConnectedInsight.target.beans.TargetEditFormBean;
import com.pumex.ConnectedInsight.target.beans.TargetFormBean;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu AU
 */
@Repository
public interface TargetDaoInterface {
    
    public void saveTarget(TargetFormBean targetBean);
    
    public void updateTarget(TargetEditFormBean targetBean);
    
    public List getTargetList(String processId);
    
    public Map getSubprocessList(String processId);
    
    public TargetEditFormBean getTarget(TargetEditFormBean targetEditFormBean,String tid);
    
    public Map subprocessMap(UserBean user);
    
    public List getActionDetails(String subProcessId);
    
    public TargetEditFormBean getTargetMetric(String subprocessId,String metric);
    
    public GlobalTotalBean getGlobalTotalLevel3G1(String subprocessId,String date);
    
    public GlobalTotalBean getGlobalTotalLevel3G2(String subprocessId,String date);
    
    public GlobalTotalBean getGlobalTotalLevel1G1(String subprocessId,String date);
    
    public GlobalTotalBean getGlobalTotalLevel2G4(String subprocessId,String date);
    
    public void saveTargetAjax(TargetEditFormBean targetEditFormBean);
}
