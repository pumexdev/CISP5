package com.pumex.ConnectedInsight.Business.controller;

import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.Business.beans.BusinessFormBean;
import com.pumex.ConnectedInsight.UserManagement.DAO.UserManagementDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
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
public class BusinessUnitController
{
    @Autowired
    UserManagementDaoInterface userManagementDaoInterface;
    @Autowired
    BusinessInterface businessDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    
    @RequestMapping(value = "/addbusinessunit.htm", method = RequestMethod.POST)
    public ModelAndView saveBusinessUnit(@ModelAttribute("BusinessUnit")BusinessFormBean businessFormBean,HttpServletRequest request, HttpServletResponse response)
    {      
        ModelAndView model = new ModelAndView(new RedirectView("searchbusinessunit.htm"));
        try
        {
            businessDaoInterface.saveBusinessUnit(businessFormBean);
            model.addObject("add",true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            model.addObject("add",false);
        }
        return model;
    }
    
    @RequestMapping(value="/searchbusinessunit.htm",method= RequestMethod.GET)
    public ModelAndView searchBusinessUnit(@ModelAttribute("SearchBusinessUnit") BusinessFormBean businessFormBean,@ModelAttribute("BusinessUnit")BusinessFormBean businessFormBean1,@ModelAttribute("EditBusinessUnit")BusinessFormBean businessFormBean2,HttpServletResponse response,HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("searchbusinessunit");
        if(request.getParameter("buid")!=null)
        {
            businessFormBean2=businessDaoInterface.getBusinessUnitData(businessFormBean2, Integer.parseInt(request.getParameter("buid")));
            model.addObject("EditBusinessUnit", businessFormBean2);
        }
        return model;
    }
    
    @RequestMapping(value = "/editbusinessunit.htm", method = RequestMethod.POST)
    public ModelAndView updateBusinessUnit(@ModelAttribute("EditBusinessUnit")BusinessFormBean businessFormBean,HttpServletRequest request, HttpServletResponse response)
    {  
        ModelAndView model = new ModelAndView(new RedirectView("searchbusinessunit.htm?buid="+businessFormBean.getBusinessunit()));
        try
        {
            businessDaoInterface.editBusinessUnit(businessFormBean);
            model.addObject("edit",true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            model.addObject("edit",false);
        }
        return model;       
    }
    
    @RequestMapping(value="/searchclient.htm",method= RequestMethod.GET)
    public ModelAndView searchClientByLocation(@ModelAttribute("SearchClient") BusinessFormBean businessFormBean,HttpServletRequest request,HttpServletResponse response)
    {
        HttpSession session=request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        ModelAndView model=new ModelAndView("searchclient");
        businessFormBean.setLocation((Integer)session.getAttribute("defaultLocation"));
        List clientlist=businessDaoInterface.getclientlist(businessFormBean);
        model.addObject("clientlist",clientlist);
        model.addObject("UserBean", user);
        return  model;
    }
    @RequestMapping(value="/searchclient.htm",method= RequestMethod.POST)
    public ModelAndView searchClientByLocationPost(@ModelAttribute("SearchClient")BusinessFormBean businessFormBean,HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("searchclient");
        List clientlist=businessDaoInterface.getclientlist(businessFormBean);
        model.addObject("clientlist",clientlist);
        return  model;
    }
    @ModelAttribute("status")
    public Map getUserStatus(HttpServletRequest request)
    {
        return dropdownDaoInterface.getStatus();
    }
    
    @ModelAttribute("businesslist")
    public Map getBusiness(HttpServletRequest request)
    {
        return dropdownDaoInterface.getActiveBusinessMap(CommonUtil.getUserBean(request));
    }
    
    @ModelAttribute("businessunits")
    public Map getBusinessUnits(HttpServletRequest request)
    {
        return businessDaoInterface.getBusinessUnitsNames();
    }
    
    
    @ModelAttribute("locationlist")
    public Map getLocations(HttpServletRequest request)
    {
        return dropdownDaoInterface.getBusinessCenterMap(CommonUtil.getUserBean(request));
    }
}
