package com.pumex.ConnectedInsight.HRData.controller;

import com.pumex.ConnectedInsight.HRData.DAO.HRDataEntryDAOInterface;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author mjons
 */
@Controller
public class HRAjaxController
{
    @Autowired
    HRDataEntryDAOInterface hRDataEntryDAOInterface;
    
    @RequestMapping(method= RequestMethod.POST,value="/checkDate.htm")
    public ModelAndView checkDate(HttpServletRequest request)
    {
        String date=request.getParameter("date");
        ModelAndView model=new ModelAndView("jsonView");
        model.addObject("check",hRDataEntryDAOInterface.checkDate(date));
        return model;
    }
    
    @RequestMapping(method= RequestMethod.GET,value="/deleteentry.htm")
    public ModelAndView deleteHREntry(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView(new RedirectView("searchhrdataentry.htm"));
        model.addObject("delete", hRDataEntryDAOInterface.deleteEntry(request.getParameter("entryId")));
        return model;
    }
}
