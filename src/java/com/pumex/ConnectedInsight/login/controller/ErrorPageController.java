package com.pumex.ConnectedInsight.login.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorPageController
{
    @RequestMapping(value="/404.htm",method= RequestMethod.GET)
    public ModelAndView getErrorAction()
    {
        ModelAndView model=new ModelAndView("404");
        return model;
    }
    
    @RequestMapping(value="/500.htm",method= RequestMethod.GET)
    public ModelAndView getServerErrorAction()
    {
        ModelAndView model=new ModelAndView("500");
        return model;
    }
    
    @RequestMapping(value="/403.htm",method= RequestMethod.GET)
    public ModelAndView getAccessDeniedErrorAction()
    {
        ModelAndView model=new ModelAndView("403");
        return model;
    }
}
