/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.login.DAO;

import com.pumex.ConnectedInsight.login.beans.LoginFormBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;

/**
 *
 * @author user
 */
public interface LoginDaoInterface
{
    public List checkLogin(LoginFormBean loginFormBean);

    public List getMenuList(UserBean user);
    
    public List getSubMenuList(UserBean user,Integer menuId);
    
    public Integer getMenuId(List menuList);
    
    public String getSubMenuHome(UserBean user,Integer menuId);
}
