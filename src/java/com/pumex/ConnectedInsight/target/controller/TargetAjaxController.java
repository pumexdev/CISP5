/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pumex.ConnectedInsight.target.controller;

import com.pumex.ConnectedInsight.target.beans.TargetEditFormBean;
import com.pumex.ConnectedInsight.target.dao.TargetDaoInterface;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vishnu AU
 */
@Controller
public class TargetAjaxController {
    
    @Autowired
    TargetDaoInterface targetDaoInterface;
    
    @RequestMapping(value = "/loadsubprocess.htm",method = RequestMethod.POST)
    public ModelAndView getSubprocess(HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView("jsonView");
        Map subprocessMap=new HashMap();
        subprocessMap=targetDaoInterface.getSubprocessList(request.getParameter("processid"));
        model.addObject("Subprocesslist", subprocessMap);
        return model;
    }
    
    @RequestMapping(value = "/savetarget.htm",method = RequestMethod.POST)
    public ModelAndView saveTarget(HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=new ModelAndView("jsonView");
        TargetEditFormBean targetEditFormBean=new TargetEditFormBean();
       String subprocessid = request.getParameter("spid");
       
       String metricId=request.getParameter("metricId");
       
       String high=request.getParameter("high");
       String medium=request.getParameter("medium");
       String low=request.getParameter("low");
       String target=request.getParameter("target");
       if(subprocessid!=null&&metricId!=null&&target!=null&&high!=null&&low!=null&&medium!=null){
       targetEditFormBean.setSubprocessid(Integer.parseInt(subprocessid));
       targetEditFormBean.setMetric(Integer.parseInt(metricId));
       targetEditFormBean.setTarget(target);
       targetEditFormBean.setHigh(high);
       targetEditFormBean.setMedium(medium);
       targetEditFormBean.setLow(low);
       targetDaoInterface.saveTargetAjax(targetEditFormBean);
       }
        System.out.println("savetarget::::"+subprocessid+" :"+metricId+" :"+target+" "+high+": "+medium+": "+low);
        return model;
    }
}
