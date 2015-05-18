package com.pumex.ConnectedInsight.Business.controller;
import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.Business.beans.BusinessFormBean;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.Map;
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
public class BusinessController
{
    @Autowired
    BusinessInterface businessDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;

    @RequestMapping(value = "/addbusiness.htm",method = RequestMethod.POST)
    public ModelAndView saveBusiness(@ModelAttribute("Business") BusinessFormBean businessFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session=request.getSession();
        UserBean userBean=(UserBean)session.getAttribute("user");
        businessFormBean.setOrganization(userBean.getOrganizationid());
        ModelAndView model = new ModelAndView(new RedirectView("searchbusiness.htm"));
        try
        {
            businessDaoInterface.saveBusiness(businessFormBean);
            model.addObject("add",true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            model.addObject("add",false);
        }
        return model;
    }

    @RequestMapping(value = "/searchbusiness.htm", method = RequestMethod.GET)
    public ModelAndView searchBusiness(@ModelAttribute("EditBusiness") BusinessFormBean businessFormBean1,@ModelAttribute("Business") BusinessFormBean businessFormBean2,@ModelAttribute("SearchBusiness")BusinessFormBean businessFormBean3, HttpServletResponse response, HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("searchbusiness");
        return model;
    }

    @RequestMapping(value="/editbusiness.htm",method = RequestMethod.POST)
    public ModelAndView updateBusiness(@ModelAttribute("EditBusiness") BusinessFormBean businessFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session=request.getSession();
        UserBean userBean=(UserBean)session.getAttribute("user");
        businessFormBean.setOrganization(userBean.getOrganizationid());
        ModelAndView model = new ModelAndView(new RedirectView("searchbusiness.htm"));
        try
        {
            businessDaoInterface.editBusiness(businessFormBean);
            model.addObject("edit",true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            model.addObject("edit",false);
        }
        return model;
    }    

    @ModelAttribute("status")
    public Map getUserStatus()
    {
        return dropdownDaoInterface.getStatus();
    }

    @ModelAttribute("businesslist")
    public Map getBusiness(HttpServletRequest request)
    {
        return dropdownDaoInterface.getBusinessMap(CommonUtil.getUserBean(request));
    }
}
