package com.pumex.ConnectedInsight.Business.controller;
import com.pumex.ConnectedInsight.Business.DAO.OrganizationDaoInterface;
import com.pumex.ConnectedInsight.Business.beans.OrganizationFormBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
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
public class OrganizationController
{
    @Autowired
    OrganizationDaoInterface organizationDaoInterface;
    
    /*@RequestMapping(value = "/organization.htm", method = RequestMethod.POST)
    public ModelAndView updateOrganization(@ModelAttribute("EditOrganization") OrganizationFormBean organizationFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView(new RedirectView("organization.htm"));
        try
        {
            organizationDaoInterface.updateOrganization(organizationFormBean);
            model.addObject("success", true);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            model.addObject("success", false);
        }
        return model;        
    }*/
    
    @RequestMapping(value = "/organization.htm", method = RequestMethod.POST)
    public ModelAndView updateOrganization(@ModelAttribute("EditOrganization") OrganizationFormBean organizationFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("organization");
        try
        {
            HttpSession session=request.getSession();
            UserBean userBean=(UserBean)session.getAttribute("user");
            Integer organization=userBean.getOrganizationid();
            organizationFormBean.setOrgid(organization);
            organizationDaoInterface.updateOrganization(organizationFormBean);
            model.addObject("success", true);
            model.addObject("UserBean", userBean);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            model.addObject("success", false);
        }
        
        return model;        
    }

    @RequestMapping(value = "/organization.htm", method = RequestMethod.GET)
    public ModelAndView getOrgDetails(@ModelAttribute("EditOrganization")OrganizationFormBean organizationFormBean ,HttpServletResponse response, HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        UserBean userBean=(UserBean)session.getAttribute("user");
        Integer organization=userBean.getOrganizationid();
        ModelAndView model = new ModelAndView("organization");
        organizationFormBean = organizationDaoInterface.getOrganization(organization,organizationFormBean);//put organization
        model.addObject("UserBean", userBean);
        return model;
    }
    
}
