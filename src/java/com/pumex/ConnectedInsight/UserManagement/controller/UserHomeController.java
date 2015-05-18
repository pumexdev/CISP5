/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.UserManagement.controller;

import com.pumex.ConnectedInsight.Business.DAO.ProcessDaoInterface;
import com.pumex.ConnectedInsight.Business.beans.OrganizationFormBean;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.DAO.LoginDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Vishnu
 */
@Controller
public class UserHomeController
{
    @Autowired
    LoginDaoInterface loginDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    @Autowired
    ProcessDaoInterface processDaoInterface;

    @RequestMapping(value = "/userhome.htm", method = RequestMethod.GET)
    public ModelAndView getUserHome(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView("userhome");
        UserBean user = (UserBean) session.getAttribute("user");
        if (user != null)
        {
            //List menuList = loginDaoInterface.getMenuList(user);
            //model.addObject("menuList", menuList);
            initialiseSettings(user.getUserlevel(), request);
            //session.setAttribute("menuList", menuList);
        }        
        return model;
       
    }
    
    public String getDefaultUrl(Integer userLevel)
    {
        String query=SQLSelector.getQuery("user.getdefaulturl");
        String defaultUrl="";
        defaultUrl=(String)commonDaoInterface.queryForObject(query, userLevel);
        if(defaultUrl==null || "".equals(defaultUrl))
            defaultUrl="searchuser.htm";
        return defaultUrl;
    }
    
    public void initialiseSettings(Integer userLevel,HttpServletRequest request)
    {
       HttpSession session=request.getSession();
       UserBean userBean=CommonUtil.getUserBean(request);
       /*---Common for all the leaders---
       session.setAttribute("businesscenters", dropdownDaoInterface.getBusinessCenterMap(userBean));
       session.setAttribute("process", dropdownDaoInterface.getProcessMap(userBean));*/
       Map defaultSubProcessMap=new HashMap();
       /*---Common for all the leaders---*/
       OrganizationFormBean organizationFormBean=new OrganizationFormBean();
       
       Integer defaultLocation=null;
       Integer defaultProcess=null;
       Integer defaultSubProcess=null;
       String query="";
       
       switch(userLevel)
       {
           case 3:  /*For the Global Leader*/
               query=SQLSelector.getQuery("usersettings.getdefaultLocationglobal");
               defaultLocation=(Integer)commonDaoInterface.queryForObject(query, userBean.getOrganizationid());
               query=SQLSelector.getQuery("usersettings.getdefaultProcessglobal");
               defaultProcess=(Integer)commonDaoInterface.queryForObject(query, userBean.getOrganizationid());
               query=SQLSelector.getQuery("usersettings.getdefaultSubProcessglobal");
               defaultSubProcess=(Integer)commonDaoInterface.queryForObject(query, userBean.getOrganizationid());
               break;
           case 4:  /*For the Location Leader*/
               defaultLocation=userBean.getBusinesscenterid();
               query=SQLSelector.getQuery("usersettings.getdefaultProcessglobal");
               defaultProcess=(Integer)commonDaoInterface.queryForObject(query, userBean.getOrganizationid());
               query=SQLSelector.getQuery("usersettings.getdefaultSubProcessglobal");
               defaultSubProcess=(Integer)commonDaoInterface.queryForObject(query, userBean.getOrganizationid());
               break;
           case 5:  /*For the Process Leader*/
               defaultProcess=userBean.getProcessid();
               query=SQLSelector.getQuery("usersettings.getdefaultSubProcess");
               defaultSubProcess=(Integer)commonDaoInterface.queryForObject(query, userBean.getProcessid());
               break;
           case 6:  /*For the Global Admin*/
               query=SQLSelector.getQuery("usersettings.getdefaultLocationglobal");
               defaultLocation=(Integer)commonDaoInterface.queryForObject(query, userBean.getOrganizationid());
               query=SQLSelector.getQuery("usersettings.getdefaultProcessglobal");
               defaultProcess=(Integer)commonDaoInterface.queryForObject(query, userBean.getOrganizationid());
               query=SQLSelector.getQuery("usersettings.getdefaultSubProcessglobal");
               defaultSubProcess=(Integer)commonDaoInterface.queryForObject(query, userBean.getOrganizationid());
       }
       session.setAttribute("defaultLocation", defaultLocation);
       session.setAttribute("defaultProcess", defaultProcess);
       session.setAttribute("defaultSubProcess", defaultSubProcess);
       session.setAttribute("subprocess", processDaoInterface.getSubProcessList(defaultProcess));
       session.setAttribute("Organization", getOrganizationDetails(userBean.getOrganizationid()));
    }
    
    public OrganizationFormBean getOrganizationDetails(Integer organizationId)
    {
        String query=SQLSelector.getQuery("organization.getName");
        OrganizationFormBean organizationFormBean=new OrganizationFormBean();
        organizationFormBean.setName(String.valueOf(commonDaoInterface.queryForObject(query, organizationId)));
        query=SQLSelector.getQuery("organization.getURL");
        organizationFormBean.setWebsite(String.valueOf(commonDaoInterface.queryForObject(query, organizationId)));
        return organizationFormBean;
    }
    
    @RequestMapping(value="home.htm",method= RequestMethod.GET)
    public ModelAndView getAdminHome(HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView("home");
        return model;
    }
}
