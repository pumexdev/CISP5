/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.actions.controller;

import com.pumex.ConnectedInsight.actions.beans.ActionsBean;
import com.pumex.ConnectedInsight.actions.dao.ActionDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ActionsAjaxController {
    
    @Autowired
    ActionDaoInterface actionDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    
    @RequestMapping(value = "/checkactiontype.htm", method = RequestMethod.POST)
    public ModelAndView checkActionType(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        Boolean check = Boolean.FALSE;
        try {
            check = actionDaoInterface.checkActionTypeExists(request.getParameter("actiontype"));
            model.addObject("check", check);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("check", false);
        }
        return model;
    }
    
    @RequestMapping(value = "/checkactiontypeedit.htm", method = RequestMethod.POST)
    public ModelAndView checkActionTypeEdit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        Boolean check = Boolean.FALSE;
        try {
            check = actionDaoInterface.checkActionTypeExistsEdit(request.getParameter("actiontype"), request.getParameter("actiontype"));
            model.addObject("check", check);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("check", false);
        }
        return model;
    }
    
    @RequestMapping(value = "/checkactiontypeactions.htm", method = RequestMethod.POST)
    public ModelAndView checkActionTypeAction(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        Boolean check = Boolean.FALSE;
        try {
            check = actionDaoInterface.checkActionTypeActionExists(request.getParameter("actiontypeid"));
            model.addObject("check", check);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("check", false);
        }
        return model;
    }
    
    @RequestMapping(value = "/getsubprocessfromprocess.htm", method = RequestMethod.POST)
    public ModelAndView getSubprocessfromProcess(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        String processId = request.getParameter("processId");
        model.addObject("subprocesslist", actionDaoInterface.getsubProcessfromProcess(processId));
        return model;
    }
    
    @RequestMapping(value = "/getactiontypemap.htm", method = RequestMethod.POST)
    public ModelAndView getactiontypemap(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        model.addObject("actiontypelist", actionDaoInterface.getActionTypeMapList());
        return model;
    }
    
    @RequestMapping(value = "/updateactionajax.htm", method = RequestMethod.POST)
    public ModelAndView updateactionajax(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        ActionsBean actionsBean = new ActionsBean();
        try {
            actionsBean.setSubprocess(Integer.parseInt(request.getParameter("subprocessid")));
            actionsBean.setActionId(Integer.parseInt(request.getParameter("actionId")));
            actionsBean.setAction(request.getParameter("action"));
            actionsBean.setActionTypeId(Integer.parseInt(request.getParameter("actiontype")));
            actionsBean.setStatus("1");
            if (actionsBean.getActionTypeId() == 0) {
                UserBean userBean = (UserBean) request.getSession().getAttribute("user");
                actionsBean.setOrgId(userBean.getOrganizationid());
                actionsBean.setActionType(request.getParameter("actiontypetext"));
                actionDaoInterface.addActionType(actionsBean);
                actionsBean.setActionTypeId(actionDaoInterface.getLastActioType());
            }
            actionsBean.setPercentage(Integer.parseInt(request.getParameter("percentage")));
            
            actionDaoInterface.updateActions(actionsBean);
            model.addObject("check", true);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("check", false);
        }
        return model;
    }
    
    @RequestMapping(value = "/deleteactionajax.htm", method = RequestMethod.POST)
    public ModelAndView deleteactionajax(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        try {
            actionDaoInterface.deleteActions(request.getParameter("actionId"));
            model.addObject("check", true);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("check", false);
        }
        return model;
        
    }
    
    @RequestMapping(value = "/saveactionajax.htm", method = RequestMethod.POST)
    public ModelAndView saveactionajax(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        ActionsBean actionsBean = new ActionsBean();
        try {
            actionsBean.setSubprocess(Integer.parseInt(request.getParameter("subprocessid")));
            actionsBean.setAction(request.getParameter("action"));
            actionsBean.setActionTypeId(Integer.parseInt(request.getParameter("actiontype")));
            actionsBean.setPercentage(Integer.parseInt(request.getParameter("percentage")));
            actionsBean.setStatus("1");
            if (actionsBean.getActionTypeId() == 0) {
                UserBean userBean = (UserBean) request.getSession().getAttribute("user");
                actionsBean.setOrgId(userBean.getOrganizationid());
                actionsBean.setActionType(request.getParameter("actiontypetext"));
                actionDaoInterface.addActionType(actionsBean);
                actionsBean.setActionTypeId(actionDaoInterface.getLastActioType());
            }
            actionDaoInterface.addActions(actionsBean);
            model.addObject("check", true);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("check", false);
        }
        return model;
        
    }
    
    @RequestMapping(value = "/checkactions.htm", method = RequestMethod.POST)
    public ModelAndView checkactions(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        Boolean check = Boolean.FALSE;
        try {
            check = actionDaoInterface.checkActionExists(request.getParameter("action"), request.getParameter("actiontype"));
            model.addObject("check", check);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("check", false);
        }
        return model;
    }
    
    @RequestMapping(value = "/checkactionsedit.htm", method = RequestMethod.POST)
    public ModelAndView checkactionsedit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        Boolean check = Boolean.FALSE;
        try {
            check = actionDaoInterface.checkActionExistsEdit(request.getParameter("action"), request.getParameter("actiontype"), request.getParameter("actionid") );
            model.addObject("check", check);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("check", false);
        }
        return model;
    }
}
