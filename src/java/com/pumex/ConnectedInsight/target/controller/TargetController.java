/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pumex.ConnectedInsight.target.controller;

import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.beans.DashBoardBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import com.pumex.ConnectedInsight.target.beans.TargetEditFormBean;
import com.pumex.ConnectedInsight.target.beans.TargetFormBean;
import com.pumex.ConnectedInsight.target.dao.TargetDaoInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Vishnu AU
 */
@Controller
public class TargetController {
    
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    @Autowired
    TargetDaoInterface targetDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    
    @RequestMapping(value = "/addtarget.htm",method = RequestMethod.GET)
    public ModelAndView addTargetGet(@ModelAttribute("AddTarget")TargetFormBean targetBean,HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView("addtarget");
        return model;
    }
    
    @RequestMapping(value = "/addtarget.htm",method = RequestMethod.POST)
    public ModelAndView addTargetPost(@ModelAttribute("AddTarget")TargetFormBean targetBean,HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView(new RedirectView("addtarget.htm"));
        try{
        targetDaoInterface.saveTarget(targetBean);
        commonDaoInterface.clearBean(targetBean);
        model.addObject("add", true);
        }catch(Exception ex){
            model.addObject("add", false);
        }
        return model;
    }
    
    @RequestMapping(value = "/edittarget.htm",method = RequestMethod.GET)
    public ModelAndView editTargetGet(@ModelAttribute("EditTarget")TargetEditFormBean targetEditFormBean,HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView("edittarget");
        targetDaoInterface.getTarget(targetEditFormBean, request.getParameter("tid"));
        return model;
    }
    
    @RequestMapping(value = "/edittarget.htm",method = RequestMethod.POST)
    public ModelAndView editTargetPost(@ModelAttribute("EditTarget")TargetEditFormBean targetEditFormBean,HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView(new RedirectView("searchtarget.htm"));
        targetDaoInterface.updateTarget(targetEditFormBean);
        return model;
    }
    
    @RequestMapping(value = "/searchtarget.htm",method = RequestMethod.GET)
    public ModelAndView searchTargetGet(@ModelAttribute("SearchTarget")TargetFormBean targetBean,HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView("searchtarget");
        List targetList=new ArrayList();
        model.addObject("targetlist", targetList);
        return model;
    }
    
    @RequestMapping(value = "/searchtarget.htm",method = RequestMethod.POST)
    public ModelAndView searchTargetPost(@ModelAttribute("SearchTarget")TargetFormBean targetBean,HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView("searchtarget");
        List targetList=targetDaoInterface.getTargetList(""+targetBean.getProcessId());
        model.addObject("targetlist", targetList);
        return model;
    }
    
    /**Returns the model and view of the Target Dashboard Volume Per FTE
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/tdvolumeperfte.htm",method= RequestMethod.GET)
    public ModelAndView targetVolumeDashboard(@ModelAttribute("TVolumeperFTEDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("tdvolumeperfte");
        model.addObject("tier", 3);
        return model;
    }
    
     /**Returns the model and view of the Target Dashboard Volume Per FTE
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/tdlcostpertras.htm",method= RequestMethod.GET)
    public ModelAndView targetLcostPerTransDashboard(@ModelAttribute("TLcostPerTransDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("tdlcostpertras");
        model.addObject("tier", 3);
        return model;
    }
    
     /**Returns the model and view of the Target Dashboard Volume Per FTE
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/spanofcontrol.htm",method= RequestMethod.GET)
    public ModelAndView spanofControlTransDashboard(@ModelAttribute("SpanofControlDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("spanofcontrol");
        model.addObject("tier", 3);
        return model;
    }
    
    /**Returns the model and view of the Target Dashboard Volume Per FTE
     * @param dashBoardBean
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/testtarget.htm",method= RequestMethod.GET)
    public ModelAndView targetTestDashboard(@ModelAttribute("TVolumeperFTEDashBoard")DashBoardBean dashBoardBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("testtarget");
        model.addObject("tier", 3);
        return model;
    }
    
    @ModelAttribute("processlist")
    public Map getProcessList(HttpServletRequest request) {
        return dropdownDaoInterface.getActiveProcessMap(CommonUtil.getUserBean(request));
    }
    
    @ModelAttribute("metricmap")
    public Map getMetricMap(HttpServletRequest request){
        return dropdownDaoInterface.getMetricMap(CommonUtil.getUserBean(request));
    }
    
    @ModelAttribute("subprocessmap")
    public Map getSubprocessMap(HttpServletRequest request){
        return targetDaoInterface.subprocessMap(CommonUtil.getUserBean(request));
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
    
    /**Map containing the Subprocessess comes under Volume for the Dashboard
     * @param request
     * @return Map
     */
    @ModelAttribute("volumesubprocess")
    public Map getVolumeSubProcesses(HttpServletRequest request)
    {
        return commonDaoInterface.queryForMap(SQLSelector.getQuery("actualref.subprocesses"));
    }
    
}
