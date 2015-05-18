/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import com.pumex.ConnectedInsight.Business.beans.SubProcessFormBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface SubProcessDaoInterface {

    public void editSubProcess(SubProcessFormBean subProcessFormBean);
    
    public void addSubprocess(SubProcessFormBean subProcessFormBean);
    
    public Map getSubProcessForVolume();
    
    public Map getSubProcessForVolume1();
    
    public Map subProcessList();
    
    public List getAllSubProcessList(UserBean userBean);   
    
}
