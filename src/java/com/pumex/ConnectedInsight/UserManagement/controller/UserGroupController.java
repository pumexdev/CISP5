//package com.pumex.ConnectedInsight.UserManagement.controller;
//
//import org.springframework.stereotype.Controller;
//import com.pumex.ConnectedInsight.UserManagement.DAO.UserManagementDaoInterface;
//import com.pumex.ConnectedInsight.UserManagement.beans.UserGroupBean;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// *
// * @author Vishnu
// */
//@Controller
//public class UserGroupController
//{
//    @Autowired
//    private UserManagementDaoInterface iUserManagementDAO;
//
//    /*  @RequestMapping(value = "/addusergroup.htm", method = RequestMethod.GET)
//    public ModelAndView addUserGroup(@ModelAttribute("UserGroup") UserGroupBean userGroupBean, HttpServletRequest request, HttpServletResponse response)
//    {
//    ModelAndView model = new ModelAndView("addusergroup");
//    return model;
//    }
//    
//    @RequestMapping(value = "/addusergroup.htm", method = RequestMethod.POST)
//    public ModelAndView saveUserGroup(@ModelAttribute("EditUserGroup") UserGroupBean userGroupBean, HttpServletRequest request, HttpServletResponse response)
//    {
//    iUserManagementDAO.saveUserGroup(userGroupBean);
//    ModelAndView model = new ModelAndView(new RedirectView("searchusergroup.htm"));
//    return model;
//    }*/
//    @RequestMapping(value = "/searchusergroup.htm", method = RequestMethod.GET)
//    public ModelAndView searchUserGroup(@ModelAttribute("SearchUserGroup") UserGroupBean userGroupBean, @ModelAttribute("EditUserGroup") UserGroupBean userGroupBean1, HttpServletResponse response, HttpServletRequest request)
//    {
//        ModelAndView model = new ModelAndView("searchusergroup");
//        return model;
//    }
//
//    /*@RequestMapping(value = "/searchusergroup.htm", method = RequestMethod.POST)
//    public ModelAndView searchUserGroupPost(@ModelAttribute("SearchUserGroup") UserGroupBean userGroupBean, HttpServletResponse response, HttpServletRequest request)
//    {
//        List usergrouplist = new ArrayList();
//        ModelAndView model = new ModelAndView("searchusergroup");
//        usergrouplist = iUserManagementDAO.searchusergroup(userGroupBean);
//        model.addObject("usergrouplist", usergrouplist);
//        return model;
//    }*/
//    
//    /* @RequestMapping(value = "/editusergroup.htm", method = RequestMethod.GET)
//    public ModelAndView getUserGroup(@ModelAttribute("EditUserGroup") UserGroupBean userGroupBean, HttpServletRequest request, HttpServletResponse response)
//    {
//    userGroupBean=iUserManagementDAO.getUserGroupData(Integer.parseInt(request.getParameter("uid")), userGroupBean);
//    ModelAndView model = new ModelAndView("editusergroup");
//    model.addObject("EditUserGroup", userGroupBean);
//    return model;
//    }*/
//
//    /*@RequestMapping(value = "/editusergroup.htm", method = RequestMethod.POST)
//    public ModelAndView updateUserGroup(@ModelAttribute("EditUserGroup") UserGroupBean userGroupBean, HttpServletRequest request, HttpServletResponse response)
//    {
//    
//    ModelAndView model = new ModelAndView(new RedirectView("searchusergroup.htm"));
//    try {
//    iUserManagementDAO.editUserGroup(userGroupBean);
//    model.addObject("edit", true);
//    } catch (Exception ex) {
//    ex.printStackTrace();
//    model.addObject("edit", false);
//    }
//    return model;
//    }*/
//    /*@ModelAttribute("statuslist")
//    public Map getUserStatus()
//    {
//    return iUserManagementDAO.getUserStatuses();
//    }
//    @ModelAttribute("grouplist")
//    public Map getGroupList()
//    {
//    return iUserManagementDAO.getGroupList();
//    } */
//}
