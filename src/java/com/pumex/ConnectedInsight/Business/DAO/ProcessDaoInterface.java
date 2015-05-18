/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import org.springframework.stereotype.Repository;
import com.pumex.ConnectedInsight.Business.beans.*;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vishnu
 */
@Repository
public interface ProcessDaoInterface {

    public void saveProcess(ProcessFormBean processFormBean, UserBean userBean);

    public ProcessFormBean getProcess(ProcessFormBean processFormBean,String pid);

    public void updateProcess(ProcessFormBean processFormBean, UserBean userBean);

    public void saveSubprocess(SubProcessFormBean subProcessFormBean);

    public SubProcessFormBean getSubProcess(SubProcessFormBean subProcessFormBean,String spid);

    public void updateSubprocess(SubProcessFormBean subProcessFormBean);

    /*public List searchprocess(ProcessFormBean processFormBean);

    public List searchsubprocess(SearchSubProcessFormBean searchSubProcessFormBean);

    public Map getProcessList();

    public Map getProcessList(Integer organizationid);*/

    public Map getSubProcessList(Integer processid);

    public Map getsubProcessList(String val);
    
    public Boolean checkProcessName(String processName);
    
    public Boolean checkSubProcessName(String subProcessName);
    
    public List getAllProcessList(UserBean userBean);
}
