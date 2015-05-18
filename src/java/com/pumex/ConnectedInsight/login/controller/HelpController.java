package com.pumex.ConnectedInsight.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vishnu
 */
@Controller
public class HelpController
{
    @RequestMapping(value="/clienthelp.htm",method= RequestMethod.GET)
    public ModelAndView getClientHelp()
    {
        return new ModelAndView("clienthelp");
    }
    
    @RequestMapping(value="/userhelp.htm",method= RequestMethod.GET)
    public ModelAndView getUsersHelp()
    {
        return new ModelAndView("userhelp");
    }
    
    @RequestMapping(value="/locationhelp.htm",method= RequestMethod.GET)
    public ModelAndView getLocationHelp()
    {
        return new ModelAndView("locationhelp");
    }
    
    @RequestMapping(value="/processhelp.htm",method= RequestMethod.GET)
    public ModelAndView getProcessHelp()
    {
        return new ModelAndView("processhelp");
    }
    
    @RequestMapping(value="/subprocesshelp.htm",method= RequestMethod.GET)
    public ModelAndView getSubProcessHelp()
    {
        return new ModelAndView("subprocesshelp");
    }
    
    @RequestMapping(value="/statementhelp.htm",method= RequestMethod.GET)
    public ModelAndView getStatementHelp()
    {
        return new ModelAndView("statementhelp");
    }
    
    @RequestMapping(value="/hrdatahelp.htm",method= RequestMethod.GET)
    public ModelAndView getHRDataHelp()
    {
        return new ModelAndView("hrdatahelp");
    }
    
    @RequestMapping(value="/exporthelp.htm",method= RequestMethod.GET)
    public ModelAndView getExportHelp()
    {
        return new ModelAndView("exporthelp");
    }
    
    @RequestMapping(value="/revenuehelp.htm",method= RequestMethod.GET)
    public ModelAndView getRevenuehelp()
    {
        return new ModelAndView("revenuehelp");
    }
    
    @RequestMapping(value="/volumehelp.htm",method= RequestMethod.GET)
    public ModelAndView getVolumehelp()
    {
        return new ModelAndView("volumehelp");
    }
}
