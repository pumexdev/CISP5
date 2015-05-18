package com.pumex.ConnectedInsight.Business.controller;

import com.pumex.ConnectedInsight.Business.DAO.ProcessDaoInterface;
import com.pumex.ConnectedInsight.Business.beans.ProcessFormBean;
import com.pumex.ConnectedInsight.Business.beans.ProcessListBean;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Vishnu
 */
@Controller
public class ProcessController
{
    @Autowired
    ProcessDaoInterface processDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    
   @RequestMapping(value = "/addprocess.htm", method = RequestMethod.GET)
   public ModelAndView saveProcessGet(@ModelAttribute("Process") ProcessFormBean processFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("addprocess");
        return model;
    }
    @RequestMapping(value = "/addprocess.htm", method = RequestMethod.POST)
    public ModelAndView saveProcess(@ModelAttribute("Process") ProcessFormBean processFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView(new RedirectView("addprocess.htm"));
        try
        {
            processDaoInterface.saveProcess(processFormBean, CommonUtil.getUserBean(request));
            model.addObject("add", true);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }
    
    @RequestMapping(value = "/editprocess.htm", method = RequestMethod.GET)
    public ModelAndView updateProcessGet(@ModelAttribute("EditProcess") ProcessFormBean processFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("editprocess");
        processDaoInterface.getProcess(processFormBean,request.getParameter("pid"));
        return model;
    }

    @RequestMapping(value = "/editprocess.htm", method = RequestMethod.POST)
    public ModelAndView updateProcess(@ModelAttribute("EditProcess") ProcessFormBean processFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView(new RedirectView("editprocess.htm?pid="+processFormBean.getProcess()));
        try
        {
            processDaoInterface.updateProcess(processFormBean, CommonUtil.getUserBean(request));
            model.addObject("edit", true);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            model.addObject("edit", false);
        }
        return model;
    }

    @RequestMapping(value = "/searchprocess.htm", method = RequestMethod.GET)
    public ModelAndView searchProcess(@ModelAttribute("SearchProcess") ProcessFormBean processFormBean1, @ModelAttribute("Process") ProcessFormBean processFormBean2, @ModelAttribute("EditProcess") ProcessFormBean processFormBean3, HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        ModelAndView model = new ModelAndView("searchprocess");
        model.addObject("processList", processDaoInterface.getAllProcessList(CommonUtil.getUserBean(request)));
        model.addObject("UserBean", user);
        return model;
    }

    @RequestMapping(value="/listprocess.htm",method= RequestMethod.GET)
    public ModelAndView getProcessAndSubProcessList(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("listprocess");
        model.addObject("processandsubprocess",getProcessAndSubProcess(CommonUtil.getUserBean(request)));
        return model;
    }
    
    @ModelAttribute("status")
    public Map getUserStatus()
    {
        return dropdownDaoInterface.getStatus();
    }

    @ModelAttribute("processlist")
    public Map getProcessList(HttpServletRequest request)
    {
        return dropdownDaoInterface.getProcessMap(CommonUtil.getUserBean(request));
    }
    
    public List getProcessAndSubProcess(UserBean userBean)
    {
        List processAndSubProcessList=new ArrayList();
        Map activeProcessMap=new HashMap();
        activeProcessMap=dropdownDaoInterface.getActiveProcessMap(userBean);
        Set processes=activeProcessMap.entrySet();
        Iterator iterator=processes.iterator();
        while(iterator.hasNext())
        {
            Map.Entry entry=(Map.Entry)iterator.next();
            Integer key=Integer.parseInt((String)entry.getKey());
            String value=(String)entry.getValue();
            ProcessListBean processListBean=new ProcessListBean();
            processListBean.setProcess(value);
            Map subprocess=new HashMap();
            subprocess=processDaoInterface.getSubProcessList(key);
            processListBean.setSubProcess(getListFromMap(subprocess));
            processAndSubProcessList.add(processListBean);
        }
        return processAndSubProcessList;
    }
    
    public List getListFromMap(Map map)
    {
        List list=new ArrayList();
        Set entryset=map.entrySet();
        Iterator iterator=entryset.iterator();
        while(iterator.hasNext())
        {
            Map.Entry entry=(Map.Entry)iterator.next();
            String value=(String)entry.getValue();
            list.add(value);
        }
        return list;
    }
}
