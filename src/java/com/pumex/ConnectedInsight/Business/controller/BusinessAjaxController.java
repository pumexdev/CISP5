/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.controller;

import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.Business.DAO.LocationDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.OrganizationDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.ProcessDaoInterface;
import com.pumex.ConnectedInsight.Business.beans.BusinessFormBean;
import com.pumex.ConnectedInsight.Tier.DAO.TierDaoInterface;
import com.pumex.ConnectedInsight.HRData.DAO.HRDataEntryDAOInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
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
 * @author user
 */
@Controller
public class BusinessAjaxController
{
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
    CommonDaoInterface commonDaoInterface;
    @Autowired
    LocationDaoInterface locationDaoInterface;

    @RequestMapping(value = "/getbusiness.htm", method = RequestMethod.GET)
    public ModelAndView getbusiness(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception
    {
        String val = request.getParameter("bid");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("rows", dataEntryDAOInterface.getbusiness(val));
        return new ModelAndView("jsonView", modelMap);
    }

    @RequestMapping(value = "/getbusinessdetails.htm", method = RequestMethod.POST)
    public ModelAndView getBusiness(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("jsonView");
        BusinessFormBean businessFormBean = new BusinessFormBean();
        List list = new ArrayList();
        businessFormBean = businessDaoInterface.getBusinessData(Integer.parseInt(request.getParameter("bid")), businessFormBean);
        list.add(businessFormBean);
        model.addObject("EditBusiness", list);
        return model;
    }

    @RequestMapping(value = "/getbusinessunits.htm", method = RequestMethod.POST)
    public ModelAndView getBusinessUnits(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("jsonView");
        Integer businessid = Integer.parseInt(request.getParameter("bid"));
        model.addObject("BusinessUnits", businessDaoInterface.getBusinessUnits(businessid));
        return model;
    }
    @RequestMapping(value="/getlocationlist.htm",method= RequestMethod.POST)
    public ModelAndView getLocationlist(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("jsonView");
        Integer businessid = Integer.parseInt(request.getParameter("bid"));
        model.addObject("BusinessUnits", businessDaoInterface.getLocations(businessid));
        return model;
    }
    @RequestMapping(value = "/getbusinessunitdetails.htm", method = RequestMethod.POST)
    public ModelAndView getBusinessUnitData(HttpServletResponse response, HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("jsonView");
        BusinessFormBean businessFormBean = new BusinessFormBean();
        List list = new ArrayList();
        businessFormBean = businessDaoInterface.getBusinessUnitData(businessFormBean, Integer.parseInt(request.getParameter("buid")));
        list.add(businessFormBean);
        model.addObject("EditBusinessUnit", list);
        return model;
    }
    
    @RequestMapping(value="/checkbusiness.htm",method= RequestMethod.POST)
    public ModelAndView checkBusinessName(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("check", businessDaoInterface.checkBusinessName(request.getParameter("name")));
        return model;
    }
    
    @RequestMapping(value="/checkbusinessunit.htm",method= RequestMethod.POST)
    public ModelAndView checkBusinessUnitName(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("check", businessDaoInterface.checkBusinessUnitName(request.getParameter("name")));
        return model;
    }
    
    @RequestMapping(value="/getclients.htm",method= RequestMethod.POST)
    public ModelAndView getClients(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("clients", commonDaoInterface.sortMapByValue(dataEntryDAOInterface.getclientlist()));
        return model;
    }
    
    @RequestMapping(value="/loadclients.htm",method= RequestMethod.POST)
    public ModelAndView getBusinnessUnitNames(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("jsonView");
        Map clientmap=new HashMap();
        clientmap=locationDaoInterface.getBusinessUnitByLocation(Integer.parseInt(request.getParameter("locationid")));
        model.addObject("Clientlist", clientmap);
        return model;
    }
}
