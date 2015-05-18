/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.actions.controller;

import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.Property.PropertyFile;
import com.pumex.ConnectedInsight.actions.beans.ActionsBean;
import com.pumex.ConnectedInsight.actions.dao.ActionDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class ActionsController {

    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    @Autowired
    ActionDaoInterface actionDaoInterface;
    @Autowired
    BusinessInterface businessDaoInterface;

    @RequestMapping(value = "/addactiontype.htm", method = RequestMethod.GET)
    public ModelAndView addactiontype(@ModelAttribute("AddActionType") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("addactiontype");
        return model;
    }

    @RequestMapping(value = "/addactiontype.htm", method = RequestMethod.POST)
    public ModelAndView addactiontypePost(@ModelAttribute("AddActionType") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("listactiontype.htm"));
        HttpSession session = request.getSession();
        try {
            UserBean userBean = (UserBean) session.getAttribute("user");
            actionsBean.setOrgId(userBean.getOrganizationid());
            actionDaoInterface.addActionType(actionsBean);
            model.addObject("add", true);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }

    @RequestMapping(value = "/editactiontype.htm", method = RequestMethod.GET)
    public ModelAndView editactiontype(@ModelAttribute("EditActionType") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("editactiontype");
        Integer actiontypeid = Integer.parseInt(request.getParameter("actiontypeid"));
        try {
            actionsBean = actionDaoInterface.getActionTypeDetails(actionsBean, actiontypeid);
            model.addObject("EditActionType", actionsBean);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return model;
    }

    @RequestMapping(value = "/editactiontype.htm", method = RequestMethod.POST)
    public ModelAndView editactiontypePost(@ModelAttribute("EditActionType") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("editactiontype.htm?actiontypeid=" + actionsBean.getActionTypeId()));
        try {
            actionDaoInterface.updateActionType(actionsBean);
            model.addObject("edit", true);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("edit", false);
        }
        return model;
    }

    @RequestMapping(value = "/listactiontype.htm", method = RequestMethod.GET)
    public ModelAndView listactiontypee(@ModelAttribute("ListActionType") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("listactiontype");
        try {
            model.addObject("actiontypelist", actionDaoInterface.getAllActionTypes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @RequestMapping(value = "/addactions.htm", method = RequestMethod.GET)
    public ModelAndView addactions(@ModelAttribute("AddActions") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("addactions");
        return model;
    }

    @RequestMapping(value = "/addactions.htm", method = RequestMethod.POST)
    public ModelAndView addactionsePost(@ModelAttribute("AddActionType") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("addactions.htm"));
        HttpSession session = request.getSession();
        try {

            if (actionsBean.getActionType() != null) {
                UserBean userBean = (UserBean) request.getSession().getAttribute("user");
                actionsBean.setOrgId(userBean.getOrganizationid());
                actionsBean.setStatus("1");
                actionDaoInterface.addActionType(actionsBean);
                actionsBean.setActionTypeId(actionDaoInterface.getLastActioType());
            }
            actionDaoInterface.addActions(actionsBean);
            model.addObject("add", true);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }

    @RequestMapping(value = "/editactions.htm", method = RequestMethod.GET)
    public ModelAndView editactions(@ModelAttribute("Editactions") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("editactions");
        Integer actionid = Integer.parseInt(request.getParameter("actionid"));
        try {
            actionsBean = actionDaoInterface.getActionsDetails(actionsBean, actionid);
            model.addObject("EditActionType", actionsBean);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return model;
    }

    @RequestMapping(value = "/editactions.htm", method = RequestMethod.POST)
    public ModelAndView editactionsPost(@ModelAttribute("EditActionType") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("editactions.htm?actionid=" + actionsBean.getActionId()));
        try {
            if (actionsBean.getActionType() != null) {
                UserBean userBean = (UserBean) request.getSession().getAttribute("user");
                actionsBean.setOrgId(userBean.getOrganizationid());
                actionsBean.setStatus("1");
                actionDaoInterface.addActionType(actionsBean);
                actionsBean.setActionTypeId(actionDaoInterface.getLastActioType());
            }
            actionDaoInterface.updateActions(actionsBean);
            model.addObject("edit", true);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addObject("edit", false);
        }
        return model;
    }

    @RequestMapping(value = "/listactions.htm", method = RequestMethod.GET)
    public ModelAndView listactions(@ModelAttribute("ListActions") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("listactions");
        try {
            model.addObject("actiontslist", actionDaoInterface.getAllActions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @RequestMapping(value = "/searchactions.htm", method = RequestMethod.GET)
    public ModelAndView searchActions(@ModelAttribute("SearchActions") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("searchactions");
        return model;
    }

    @RequestMapping(value = "/searchactions.htm", method = RequestMethod.POST)
    public ModelAndView searchActionsPost(@ModelAttribute("SearchActions") ActionsBean actionsBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("searchactions");
        try {
            model.addObject("actiontslist", actionDaoInterface.getAllActions(actionsBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @ModelAttribute("statuslist")
    public Map getUserStatus() {
        return dropdownDaoInterface.getStatus();
    }

    @ModelAttribute("processlist")
    public Map getProcessList(HttpServletRequest request) {
        return dropdownDaoInterface.getActiveProcessMap(CommonUtil.getUserBean(request));
    }

    @ModelAttribute("subprocesslist")
    public Map gtesubprocesslist() {
        return businessDaoInterface.getSubProcessList();
    }

    @ModelAttribute("actiontypelist")
    public Map gteactiontypelist() {
        return actionDaoInterface.getActionTypeList();
    }
}
