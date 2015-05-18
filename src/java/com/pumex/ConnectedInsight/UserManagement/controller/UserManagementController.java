/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.UserManagement.controller;

import com.pumex.ConnectedInsight.UserManagement.DAO.UserManagementDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author user
 */
@Controller
public class UserManagementController
{
    @Autowired
    UserManagementDaoInterface userManagementDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;

    @RequestMapping(value = "/adduser.htm", method = RequestMethod.GET)
    public ModelAndView addUser(@ModelAttribute("User") UserBean userBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("adduser");
        return model;
    }

    @RequestMapping(value = "/adduser.htm", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute("User") UserBean userBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView(new RedirectView("adduser.htm"));
        try
        {
            userBean.setOrganizationid(CommonUtil.getUserBean(request).getOrganizationid());
            userManagementDaoInterface.saveUser(userBean);
            model.addObject("add", true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }
    
    @RequestMapping(value = "/edituser.htm", method = RequestMethod.GET)
    public ModelAndView getUser(@ModelAttribute("EditUser") UserBean userBean, HttpServletRequest request, HttpServletResponse response)
    {        
        ModelAndView model = new ModelAndView("edituser");
        Integer uid=Integer.parseInt(request.getParameter("uid"));
        try
        {
            userBean=userManagementDaoInterface.getUserDetails(userBean, uid);
            model.addObject("EditUser",userBean);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return model;
    }
    
    @RequestMapping(value = "/edituser.htm", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("EditUser") UserBean userBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView(new RedirectView("edituser.htm"));
        Integer uid=Integer.parseInt(request.getParameter("uid"));
        model.addObject("uid", uid);
        try
        {
            userBean.setOrganizationid(CommonUtil.getUserBean(request).getOrganizationid());
            userManagementDaoInterface.updateUser(userBean,uid);
            model.addObject("edit", true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            model.addObject("edit", false);
        }
        return model;
    }

    @RequestMapping(value="searchuser.htm",method= RequestMethod.GET)
    public ModelAndView searchUser(@ModelAttribute("SearchUser") UserBean userBean, HttpServletResponse response,HttpServletRequest request)
    {
        userBean.setUserlevel(3);
        userBean.setUsername("");
        List userlist=new ArrayList();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        ModelAndView model=new ModelAndView("searchuser");
        userlist=userManagementDaoInterface.getUsersForOrg(CommonUtil.getUserBean(request));
        model.addObject("userlist",userlist );
        model.addObject("UserBean", user);
        return model;
    }       
    @RequestMapping(value="searchuser.htm",method= RequestMethod.POST)
    public ModelAndView searchUserPost(@ModelAttribute("SearchUser") UserBean userBean,HttpServletResponse response,HttpServletRequest request)
    {
        List userlist=new ArrayList();
        ModelAndView model=new ModelAndView("searchuser");        
        userlist=userManagementDaoInterface.searchuser(userBean,CommonUtil.getUserBean(request));
        model.addObject("userlist",userlist );
        return model;
        
    }       
    @ModelAttribute("userstatuslist")
    public Map getUserStatus()
    {
        return dropdownDaoInterface.getStatus();
    }
    
    @ModelAttribute("userlevels")
    public Map getUserLevels()
    {
        return userManagementDaoInterface.getUserLevels();
    }
    
    @ModelAttribute("processlist")
    public Map getProcessess(HttpServletRequest request)
    {
        return dropdownDaoInterface.getProcessMap(CommonUtil.getUserBean(request));
    }
    
    @ModelAttribute("locationlist")
    public Map getLocations(HttpServletRequest request)
    {
        return dropdownDaoInterface.getBusinessCenterMap(CommonUtil.getUserBean(request));
    }
}
