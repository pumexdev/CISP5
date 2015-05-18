/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.HRData.controller;

import com.pumex.ConnectedInsight.HRData.DAO.HRDataEntryDAOInterface;
import com.pumex.ConnectedInsight.HRData.beans.StatementBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
 * @author user
 */
@Controller
public class StatementController
{
    @Autowired
    HRDataEntryDAOInterface hRDataEntryDAOInterface;
    
    @RequestMapping(value="/statement.htm",method= RequestMethod.GET)
    public ModelAndView getStatement(@ModelAttribute("Statement") StatementBean statementBean,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        ModelAndView model = new ModelAndView("statement");
        List statementList=hRDataEntryDAOInterface.getAllEntryDates();
        model.addObject("statementList", statementList);
        model.addObject("UserBean", user);
        return model;
    }
    
    @RequestMapping(value="/openstatement.htm",method= RequestMethod.GET)
    public ModelAndView addStatement(@ModelAttribute("Statement") StatementBean statementBean)
    {
        ModelAndView model = new ModelAndView("openstatement");
        return model;
    }
    
    @RequestMapping(value="/openstatement.htm",method= RequestMethod.POST)
    public ModelAndView postStatement(@ModelAttribute("Statement") StatementBean statementBean)
    {
        ModelAndView model = new ModelAndView(new RedirectView("openstatement.htm"));
        try
        {
            hRDataEntryDAOInterface.postStatement(statementBean);
            model.addObject("open", true);
        }
        catch(Exception ex)
        {
            model.addObject("open", false);
        }
        return model;
    }
    
    @RequestMapping(value="/editstatement.htm",method= RequestMethod.GET)
    public ModelAndView editStatement(@ModelAttribute("Statement") StatementBean statementBean,HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("editstatement");
        String date = request.getParameter("date");
        statementBean.setDate(date);
        String status = request.getParameter("status");
        statementBean.setStatementStatus(Integer.parseInt(status));
        return model;
    }
    
    @RequestMapping(value="/editstatement.htm",method= RequestMethod.POST)
    public ModelAndView postEditStatement(@ModelAttribute("Statement") StatementBean statementBean,HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView(new RedirectView("statement.htm"));
        String status1="";
        try
        {
            switch(statementBean.getStatementStatus())
            {
                case 1:
                        status1="open";
                        break;
                case 2:
                        status1="close";
                        break;
                case 3:
                        status1="reopen";
                        break;
            }
            hRDataEntryDAOInterface.postStatement(statementBean);
            String date = request.getParameter("date");
            statementBean.setDate(date);
            String status = request.getParameter("status");
            statementBean.setStatementStatus(Integer.parseInt(status));
            model.addObject("date", date);
            model.addObject("status", status);
            model.addObject(status1,true);
        }
        catch(Exception ex)
        {
            model.addObject(status1, false);
        }
        return model;
    }
    
    @ModelAttribute("opendates")
    public Map getOpenDates()
    {
        return hRDataEntryDAOInterface.getOpenDates();
    }
    
    @ModelAttribute("closedates")
    public Map getCloseDates()
    {
        return hRDataEntryDAOInterface.getCloseDates();
    }
}
