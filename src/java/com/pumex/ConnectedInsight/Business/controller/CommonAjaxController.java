/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.controller;

import com.pumex.ConnectedInsight.Business.DAO.ActualReferenceDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.Business.DAO.LocationDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.OrganizationDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.ProcessDaoInterface;
import com.pumex.ConnectedInsight.Business.beans.BusinessFormBean;
import com.pumex.ConnectedInsight.Tier.beans.TierBean;
import com.pumex.ConnectedInsight.Tier.DAO.TierDaoInterface;
import com.pumex.ConnectedInsight.HRData.DAO.HRDataEntryDAOInterface;
import com.pumex.ConnectedInsight.UserManagement.DAO.UserManagementDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.ArrayList;
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

/**
 *
 * @author Vishnu
 */
@Controller
public class CommonAjaxController
{
    @Autowired
    UserManagementDaoInterface userManagementDaoInterface;
    @Autowired
    HRDataEntryDAOInterface dataEntryDAOInterface;
    @Autowired
    BusinessInterface businessDaoInterface;
    @Autowired
    OrganizationDaoInterface organizationDaoInterface;
    @Autowired
    ProcessDaoInterface processDaoInterface;
    @Autowired
    TierDaoInterface tierDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    @Autowired
    LocationDaoInterface locationDaoInterface;
    @Autowired
    ActualReferenceDaoInterface actualReferenceDaoInterface;
    
    @RequestMapping(value="/checksession.htm",method= RequestMethod.POST)
    public ModelAndView checkSession(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        HttpSession session = request.getSession();
       
        UserBean user = (UserBean) session.getAttribute("user");
        if(user!=null && user.getUserid()!=null)
            model.addObject("check","true");
        else
           model.addObject("check","false");
        return model;
    }
    
    @RequestMapping(value = "/getsubbusinessunit.htm", method = RequestMethod.GET)
    public ModelAndView getsubbusinessunit(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception
    {
        String val = request.getParameter("stateCd");
        List minorList = userManagementDaoInterface.getsubBusiness(val);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("rows", minorList);
        return new ModelAndView("jsonView", modelMap);
    }
    
    @RequestMapping(value = "/getbusinesscenter.htm", method = RequestMethod.GET)
    public ModelAndView getBusinessCenter(HttpSession session, HttpServletRequest request)
    {
        String businessId = request.getParameter("bid");
        ModelAndView model = new ModelAndView("jsonView");
        Map bcenterList = dropdownDaoInterface.getBusinessCenterMap(CommonUtil.getUserBean(request), businessId);
        model.addObject("BusinessCenter", bcenterList);
        return model;
    }
    
    @RequestMapping(value = "/gettierdetails.htm", method = RequestMethod.POST)
    public ModelAndView getTierSearchDetails(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("jsonView");
        TierBean tierBean = new TierBean();
        List list = new ArrayList();
        tierBean = tierDaoInterface.getTierList(Integer.parseInt(request.getParameter("tierdid")), tierBean);
        list.add(tierBean);
        model.addObject("EditTier", list);
        return model;
    }
    
    @RequestMapping(value="/checklocation.htm",method= RequestMethod.POST)
    public ModelAndView checkLocationName(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("check", locationDaoInterface.checkLocationName(request.getParameter("name")));
        return model;
    }
    
    @RequestMapping(value="/getvolumedescriptions.htm",method= RequestMethod.POST)
    public ModelAndView getVolumedescription(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("descriptionList",actualReferenceDaoInterface.setVolumeDescription(request.getParameter("center"),request.getParameter("date"),request.getParameter("parameter"),request.getParameter("statue")));       
        return model;
    }
    
}
