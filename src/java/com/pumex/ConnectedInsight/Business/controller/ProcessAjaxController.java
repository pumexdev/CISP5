/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.controller;

import com.pumex.ConnectedInsight.Business.DAO.BenchMarkDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.ProcessDaoInterface;
import com.pumex.ConnectedInsight.Business.beans.ProcessFormBean;
import com.pumex.ConnectedInsight.Business.beans.SubProcessFormBean;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ProcessAjaxController
{
    @Autowired
    ProcessDaoInterface processDaoInterface;
    @Autowired
    BenchMarkDaoInterface benchMarkDaoInterface;
    
//    @RequestMapping(value = "/getprocessdetails.htm", method = RequestMethod.POST)
//    public ModelAndView getProcessSearchDetails(HttpServletRequest request, HttpServletResponse response)
//    {
//        ModelAndView model = new ModelAndView("jsonView");
//        ProcessFormBean processFormBean = new ProcessFormBean();
//        List list = new ArrayList();
//        processFormBean = processDaoInterface.getProcess(request.getParameter("processid"));
//        list.add(processFormBean);
//        model.addObject("EditProcess", list);
//        return model;
//    } 
    @RequestMapping(value = "/getvolumedescriptiondata.htm", method = RequestMethod.POST)
    public ModelAndView getVolumeDescriptionDetails(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("jsonView");        
        model.addObject("EditDescription",benchMarkDaoInterface.getDescriptionlist(request.getParameter("descriptionid")));
        return model;
    } 

    @RequestMapping(value = "/getsubprocess.htm", method = RequestMethod.POST)
    public ModelAndView getsubprocessList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView model=new ModelAndView("jsonView");
        String processid = request.getParameter("pid");
        model.addObject("SubProcessList", processDaoInterface.getsubProcessList(processid));
        return model;
    }
//    @RequestMapping(value = "/getsubprocessdetails.htm", method = RequestMethod.POST)
//    public ModelAndView getSubProcessDetails(HttpServletRequest request, HttpServletResponse response)
//    {
//        ModelAndView model = new ModelAndView("jsonView");
//        String subprocessid = request.getParameter("spid");
//        SubProcessFormBean subProcessFormBean=new SubProcessFormBean();
//        subProcessFormBean=processDaoInterface.getSubProcess(subprocessid);
//        List list=new ArrayList();
//        list.add(subProcessFormBean);
//        model.addObject("SubProcessList",list);       
//        return model;        
//    }
    
    @RequestMapping(value="/checkprocess.htm",method= RequestMethod.POST)
    public ModelAndView checkProcessName(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("check", processDaoInterface.checkProcessName(request.getParameter("name")));
        return model;
    }
    
    @RequestMapping(value="/checksubprocess.htm",method= RequestMethod.POST)
    public ModelAndView checkSubProcessName(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("check", processDaoInterface.checkSubProcessName(request.getParameter("name")));
        return model;
    }
}
