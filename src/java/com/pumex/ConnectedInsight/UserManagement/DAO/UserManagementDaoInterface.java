/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.UserManagement.DAO;

import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public interface UserManagementDaoInterface {
    
    public List getUserData(Integer ulid,UserBean userBean);

    public void saveUser(UserBean userBean);

    public UserBean getUserDetails(UserBean userBean, Integer uid);

    public void updateUser(UserBean userBean, Integer uid);

    public Map getGroupList();

    public Map getUserLevels();

    public Map getCountry();

    public List searchuser(UserBean userBean,UserBean userBean1);

    public Map getUserGroupStatus();

    public List getsubBusiness(String val);
    
    public Boolean checkUserExists(String userName);
    
    public List getUsersForOrg(UserBean userBean);
  
}
