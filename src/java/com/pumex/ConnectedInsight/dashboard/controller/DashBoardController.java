package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.Business.DAO.ProcessDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.SubProcessDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.DashBoardDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.DashBoardBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.HashMap;
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

/**
 *
 * @author Vishnu
 */
@Controller
public class DashBoardController
{
    @Autowired
    ProcessDaoInterface processDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    @Autowired
    SubProcessDaoInterface subProcessDaoInterface;
    @Autowired
    DashBoardDaoInterface dashBoardDaoInterface;
    
    /**Returns the model and view of the Global Dashboard page
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/globaldashboard.htm",method= RequestMethod.GET)
    public ModelAndView globalDashboard(@ModelAttribute("GlobalDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("globaldashboard");
        model.addObject("tier", 3);
        return model;
    }
    
    /**Returns the model and view of the Global Dashboard page
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/locationdashboard.htm",method= RequestMethod.GET)
    public ModelAndView locationDashboard(@ModelAttribute("GlobalDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("locationdashboard");
        model.addObject("tier", 3);
        return model;
    }
    
    /**Returns the model and view of the Global Dashboard page
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/processdashboard.htm",method= RequestMethod.GET)
    public ModelAndView processDashboard(@ModelAttribute("GlobalDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("processdashboard");
        model.addObject("tier", 3);
        return model;
    }
    
    /**Returns the model and view of the Global Dashboard page
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/subprocessdashboard.htm",method= RequestMethod.GET)
    public ModelAndView subprocessDashboard(@ModelAttribute("GlobalDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("subprocessdashboard");
        model.addObject("tier", 3);
        return model;
    }
    
    /**Returns the model and view of the Global Dashboard page
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/connecteddashboard.htm",method= RequestMethod.GET)
    public ModelAndView connectedDashboard(@ModelAttribute("GlobalDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("connecteddashboard");
        model.addObject("tier", 3);
        return model;
    }
    
    /**Returns the model and view of the Global Dashboard page
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/maindashboard.htm",method= RequestMethod.GET)
    public ModelAndView dashboardLanding(@ModelAttribute("GlobalDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("maindashboard");
        Map subProcessMap = commonDaoInterface.queryForMap(SQLSelector.getQuery("subprocess.getbyvolume"));
        model.addObject("subprocesses", subProcessMap);
        model.addObject("span", dashBoardDaoInterface.spanOfControll());
        return model;
    }
    
    /**Returns the model and view of the Global Dashboard page
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/targetdashboard.htm",method= RequestMethod.GET)
    public ModelAndView targetDashboard(@ModelAttribute("TargetDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("targetdashboard");
        model.addObject("tier", 3);
        return model;
    }
    
    /**Map containing the Businesscenters for the Dashboard
     * @param request
     * @return Map
     */
    @ModelAttribute("dashboardbusinesscenters")
    public Map getLocations(HttpServletRequest request)
    {
        return dropdownDaoInterface.getBusinessCenterMap(CommonUtil.getUserBean(request));
    }
    
    /**Map containing the Processes for the Dashboard
     * @param request
     * @return Map
     */
    @ModelAttribute("dashboardprocess")
    public Map getProcesses(HttpServletRequest request)
    {
        return dropdownDaoInterface.getActiveProcessMap(CommonUtil.getUserBean(request));
    }
    
    /**Map containing the Subprocesses for the Dashboard
     * @param request
     * @return Map
     */
    @ModelAttribute("dashboardsubprocess")
    public Map getSubProcesses(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        Integer defaultProcess=Integer.valueOf((Integer)session.getAttribute("defaultProcess"));
        return commonDaoInterface.sortMapByValue(subProcessDaoInterface.subProcessList());
    }
    
    /**Map containing the Subprocessess comes under Volume for the Dashboard
     * @param request
     * @return Map
     */
    @ModelAttribute("volumesubprocess")
    public Map getVolumeSubProcesses(HttpServletRequest request)
    {
        return commonDaoInterface.queryForMap(SQLSelector.getQuery("actualref.subprocesses"));
    }
    
    /**As on date for the Dashboard
     * @param request
     * @return Map
     */
    @ModelAttribute("date1")
    public Object getMaxDate(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        UserBean userBean=(UserBean)session.getAttribute("user");
        Integer organizationid=userBean.getOrganizationid();
        if(organizationid==null)
            organizationid=4;
        String query=SQLSelector.getQuery("hrdataentry.getmaxdate");
        return commonDaoInterface.queryForObject(query,organizationid);
    }
    
    /**Previous date for the Dashboard
     * @param request
     * @return Map
     */
    @ModelAttribute("date2")
    public Object getMinDate(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        UserBean userBean=(UserBean)session.getAttribute("user");
        Integer organizationid=userBean.getOrganizationid();
        if(organizationid==null)
            organizationid=4;
        String query=SQLSelector.getQuery("hrdataentry.getmindate");
        return commonDaoInterface.queryForObject(query,organizationid);
    }
    
    @ModelAttribute("orgid")
    public Integer getOrganization(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        UserBean userBean=(UserBean)session.getAttribute("user");
        Integer organization=null;
        try
        {
            organization=userBean.getOrganizationid();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return organization;
    }
    
    /**Map containing the As on dates for the Dashboard
     * @param request
     * @return Map
     */
    @ModelAttribute("dates1")
    public Map getAsOnDates(HttpServletRequest request)
    {
        UserBean userBean=CommonUtil.getUserBean(request);
        String query=SQLSelector.getQuery("hrdataentry.getmindate");
        Object lastUploadDate=commonDaoInterface.queryForObject(query, userBean.getOrganizationid());
        query=SQLSelector.getQuery("hrdataentry.getdates1");
        Map map=new HashMap();
        map=commonDaoInterface.queryForDates(query, userBean.getOrganizationid());
        return commonDaoInterface.sortMapByValue(map,0);
    }
    
    /**Map containing the Previous dates for the Dashboard
     * @param request
     * @return Map
     */
    @ModelAttribute("dates2")
    public Map getPreviousDates(HttpServletRequest request)
    {
        UserBean userBean=CommonUtil.getUserBean(request);
        String query1=SQLSelector.getQuery("hrdataentry.getdates2");
        String query2=SQLSelector.getQuery("hrdataentry.getmaxdate");
        Map map=new HashMap();
        map=commonDaoInterface.queryForDates(query1, userBean.getOrganizationid(),commonDaoInterface.queryForObject(query2,userBean.getOrganizationid()));
        return commonDaoInterface.sortMapByValue(map,0);
    }
    
}
