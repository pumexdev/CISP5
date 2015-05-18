/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.Business.DAO.LocationDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.ProcessDaoInterface;
import com.pumex.ConnectedInsight.KPI.DAO.KPIDAOInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.DashBoardDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.HashMap;
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
 * @author mjons
 */
@Controller
public class GraphAjaxController
{
    @Autowired
    ProcessDaoInterface processDaoInterface;
    @Autowired
    LocationDaoInterface locationDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    @Autowired
    KPIDAOInterface kPIDAOInterface;
    @Autowired
    DashBoardDaoInterface dashBoardDaoInterface;
    
    
    @RequestMapping(method= RequestMethod.POST,value="/getlocations.htm")
    public ModelAndView getBusinessList(HttpServletRequest request,HttpServletResponse response)
    {
        HttpSession session=request.getSession();
        Integer organization=null;
        try
        {
            UserBean userBean=(UserBean)session.getAttribute("user");
            organization=userBean.getOrganizationid();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("LocationList",locationDaoInterface.getBusinessCenter(organization));
        return model;
    }
    
    @RequestMapping(method= RequestMethod.POST,value="/getsubprocesslist.htm")
    public ModelAndView getSubProcessList(HttpServletRequest request,HttpServletResponse response)
    {
        HttpSession session=request.getSession();
        Integer organization=null;
        Integer processId=null;
        try
        {
            UserBean userBean=(UserBean)session.getAttribute("user");
            organization=userBean.getOrganizationid();
            processId=Integer.parseInt(request.getParameter("pid"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("subProcessList", processDaoInterface.getSubProcessList(processId));
        return model;
    }
    
    @RequestMapping(method= RequestMethod.POST,value="/getprocesslist.htm")
    public ModelAndView getProcessList(HttpServletRequest request,HttpServletResponse response)
    {
        HttpSession session=request.getSession();
        Integer organization=null;
        try
        {
            UserBean userBean=(UserBean)session.getAttribute("user");
            organization=userBean.getOrganizationid();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("ProcessList", dropdownDaoInterface.getProcessMap(CommonUtil.getUserBean(request)));
        return model;
    }
    
    @RequestMapping(value="/getdate1.htm",method= RequestMethod.POST)
    public ModelAndView getDates1(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        Integer organization=null;
        UserBean userBean=(UserBean)session.getAttribute("user");
        try
        {
            organization=userBean.getOrganizationid();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("hrdataentry.getmindate");
        Object lastUploadDate=commonDaoInterface.queryForObject(query, organization);
        query=SQLSelector.getQuery("hrdataentry.getdates1");
        Map map=new HashMap();
        map=commonDaoInterface.queryForDates(query, organization);
        model.addObject("dates1", commonDaoInterface.sortMapByValue(map,0));
        return model;
    }
    
    @RequestMapping(value="/getdate2.htm",method= RequestMethod.POST)
    public ModelAndView getDates2(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        Integer organization=null;
        String date1=request.getParameter("d1");
        UserBean userBean=(UserBean)session.getAttribute("user");
        try
        {
            organization=userBean.getOrganizationid();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("hrdataentry.getdates2");
        Map map=new HashMap();
        map=commonDaoInterface.queryForDates(query, organization,date1);
        model.addObject("dates2", commonDaoInterface.sortMapByValue(map,0));
        return model;
    }
    
    @RequestMapping(value="/getkpis.htm",method= RequestMethod.POST)
    public ModelAndView getKpis(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("jsonView");
        String subprocessid = request.getParameter("spid");
        model.addObject("KPIS", kPIDAOInterface.getKpis(subprocessid));
        return model;
    }
    
    @RequestMapping(value="/landingdata.htm",method= RequestMethod.POST)
    public ModelAndView getLandingData(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("jsonView");
        String date1 = request.getParameter("date1");
        model.addObject("ptpAnnualizedinvoice", dashBoardDaoInterface.ptpAnnualisedInVoice(date1));
        model.addObject("costperinvoice", dashBoardDaoInterface.ptpCostPerInVoice(date1));
        model.addObject("otcannualizedcost", dashBoardDaoInterface.otcAnnualizedCostPerCashReceipt(date1));
        model.addObject("otccostpercash", dashBoardDaoInterface.otcCostPerCashReceipt(date1));
        model.addObject("rtrannualizedmanual", dashBoardDaoInterface.rtrAnnualizedCostPerCashReceipt(date1));
        model.addObject("rtrcostpermanualje", dashBoardDaoInterface.rtrCostPerCashReceipt(date1));
        model.addObject("totalemployees", dashBoardDaoInterface.totalEmployees(date1));
        model.addObject("avgcostperfte", dashBoardDaoInterface.averageCostPerFTE(date1));
        return model;
    }
}
