/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pumex.ConnectedInsight.target.controller;

import com.pumex.ConnectedInsight.Business.beans.ProcessFormBean;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import com.pumex.ConnectedInsight.target.beans.MetricFormBean;
import com.pumex.ConnectedInsight.target.dao.MetricDaoInterface;
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
 * @author Vishnu AU
 */
@Controller
public class MetricController {
    
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    @Autowired
    MetricDaoInterface metricDaoInterface;
    
    @RequestMapping(value = "/searchmetric.htm", method = RequestMethod.GET)
    public ModelAndView searchProcess(@ModelAttribute("SearchMetric") MetricFormBean metricFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        ModelAndView model = new ModelAndView("searchmetric");
        model.addObject("metriclist", metricDaoInterface.getMetricList(user.getOrganizationid()));
        model.addObject("UserBean", user);
        return model;
    }
    
    @RequestMapping(value = "/addmetric.htm", method = RequestMethod.GET)
   public ModelAndView saveProcessGet(@ModelAttribute("Metric") MetricFormBean metricFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("addmetric");
        return model;
    }
   
   @RequestMapping(value = "/addmetric.htm", method = RequestMethod.POST)
    public ModelAndView saveProcess(@ModelAttribute("Metric") MetricFormBean metricFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView(new RedirectView("addmetric.htm"));
        try
        {
            metricDaoInterface.saveMetric(metricFormBean,CommonUtil.getUserBean(request));
            model.addObject("add", true);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }
    
    @RequestMapping(value = "/editmetric.htm", method = RequestMethod.GET)
   public ModelAndView updateProcessGet(@ModelAttribute("EditMetric") MetricFormBean metricFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("editmetric");
        metricDaoInterface.getMetric(metricFormBean, request.getParameter("mid"));
        return model;
    }
   
   @RequestMapping(value = "/editmetric.htm", method = RequestMethod.POST)
   public ModelAndView updateProcessPost(@ModelAttribute("EditMetric") MetricFormBean metricFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView(new RedirectView("searchmetric.htm"));
        try{
        metricDaoInterface.updateMetric(metricFormBean,CommonUtil.getUserBean(request));
        model.addObject("edit", true);
        }catch(Exception ex){
            model.addObject("edit", false);
        }
        return model;
    }
   
   @ModelAttribute("status")
    public Map getUserStatus()
    {
        return dropdownDaoInterface.getStatus();
    }
}
