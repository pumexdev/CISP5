/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.login.controller;

import com.pumex.ConnectedInsight.login.beans.LoginFormBean;
import com.pumex.ConnectedInsight.login.beans.TestBean1;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class TestingController
{
    @RequestMapping(method= RequestMethod.GET,value="/testing.htm")
    public ModelAndView test(@ModelAttribute("test")LoginFormBean loginFormBean)
    {
        ModelAndView model=new ModelAndView("test");
        return model;
    }
    
    @RequestMapping(method= RequestMethod.POST,value="/testing.htm")
    public ModelAndView test1(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        model.addObject("success", true);
        return model;
    }
    
    @RequestMapping(method= RequestMethod.GET,value="/ajaxtest.htm")
    public ModelAndView test2(@ModelAttribute("ajaxtest")TestBean1 loginFormBean,HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("ajaxtest");
        return model;
    }
    
    @RequestMapping(method= RequestMethod.POST,value="/ajaxtest.htm")
    public ModelAndView test3(@ModelAttribute("ajaxtest")TestBean1 testBean1,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("ajaxtest");
        System.out.println("pass"+testBean1.getPassword());
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        model.addObject("success", true);
        return model;
    }
}
