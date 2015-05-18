/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.controller;

import com.pumex.ConnectedInsight.Business.DAO.ProcessDaoInterface;
import org.springframework.stereotype.Controller;
import com.pumex.ConnectedInsight.Business.beans.SubProcessFormBean;
import com.pumex.ConnectedInsight.Business.DAO.SubProcessDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author user
 */
@Controller
public class SubProcessController {

    @Autowired
    ProcessDaoInterface processDaoInterface;
    @Autowired
    SubProcessDaoInterface subProcessDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;

    @RequestMapping(value = "/searchsubprocess.htm", method = RequestMethod.GET)
    public ModelAndView searchSubProcess(@ModelAttribute("SearchSubprocess") SubProcessFormBean subProcessFormBean, @ModelAttribute("SubProcess") SubProcessFormBean subProcessFormBean1, @ModelAttribute("EditSubProcess") SubProcessFormBean subProcessFormBean2, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        ModelAndView model = new ModelAndView("searchsubprocess");
        model.addObject("subprocesslist", subProcessDaoInterface.getAllSubProcessList(CommonUtil.getUserBean(request)));
        model.addObject("UserBean", user);
        return model;
    }

    @RequestMapping(value = "/editsubprocess.htm", method = RequestMethod.GET)
    public ModelAndView editProcessGet(@ModelAttribute("EditSubProcess") SubProcessFormBean processFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("editsubprocess");
        processDaoInterface.getSubProcess(processFormBean,request.getParameter("subid"));
        return model;
    }

    @RequestMapping(value = "/editsubprocess.htm", method = RequestMethod.POST)
    public ModelAndView editSubProcess(@ModelAttribute("EditSubProcess") SubProcessFormBean subProcessFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("editsubprocess.htm?subid="+subProcessFormBean.getSubprocess()));
        try {
            subProcessDaoInterface.editSubProcess(subProcessFormBean);
            model.addObject("edit", true);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("edit", false);
        }
        return model;
    }

    @RequestMapping(value = "/addsubprocess.htm", method = RequestMethod.GET)
    public ModelAndView saveProcessGet(@ModelAttribute("AddSub-Process") SubProcessFormBean processFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("addsubprocess");
        return model;
    }

    @RequestMapping(value = "addsubprocess.htm", method = RequestMethod.POST)
    public ModelAndView addSubProcess(@ModelAttribute("SubProcess") SubProcessFormBean subProcessFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("addsubprocess.htm"));
        try {
            subProcessDaoInterface.addSubprocess(subProcessFormBean);
            model.addObject("add", true);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }

    @ModelAttribute("processlist")
    public Map getProcessList(HttpServletRequest request) {
        return dropdownDaoInterface.getActiveProcessMap(CommonUtil.getUserBean(request));
    }

    @ModelAttribute("status")
    public Map getStatus(HttpServletRequest request) {
        return dropdownDaoInterface.getStatus();
    }
}
