/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.statement.controller;

import com.pumex.ConnectedInsight.HRData.DAO.HRDataEntryDAOInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.statement.dao.StatementViewDao;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vishnu A U
 */
@Controller
public class StatementVIewController
{

    @Autowired
    StatementViewDao statementViewDao;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    @Autowired
    HRDataEntryDAOInterface dataEntryDAOInterface;
    
    @RequestMapping(value= "/statementview.htm", method = RequestMethod.GET)
    public ModelAndView getStatementView()
    {
        ModelAndView model=new ModelAndView("statementview");
        model.addObject("recentdateList", statementViewDao.getRecentStatementDate());
        return model;
    }
    
    @ModelAttribute("businesscenterlist")
    public Map getUserStatus(HttpServletRequest request)
    {
        return dropdownDaoInterface.getBusinessCenterMap(CommonUtil.getUserBean(request));
    }
    
    @ModelAttribute("entryDates")
    public Map getEntryDates(HttpServletRequest request)
    {
        return dataEntryDAOInterface.getEntryDates();
    }
}
