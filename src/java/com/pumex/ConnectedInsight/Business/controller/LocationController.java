package com.pumex.ConnectedInsight.Business.controller;

import com.pumex.ConnectedInsight.Business.DAO.LocationDaoInterface;
import com.pumex.ConnectedInsight.Business.beans.LocationFormBean;
import com.pumex.ConnectedInsight.UserManagement.DAO.UserManagementDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.ArrayList;
import java.util.HashMap;
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
public class LocationController {

    @Autowired
    UserManagementDaoInterface userManagementDaoInterface;
    @Autowired
    LocationDaoInterface locationDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;

    @RequestMapping(value = "/addlocation.htm", method = RequestMethod.GET)
    public ModelAndView addBusiness(@ModelAttribute("Location") LocationFormBean locationFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("addlocation");
        return model;
    }

    @RequestMapping(value = "/addlocation.htm", method = RequestMethod.POST)
    public ModelAndView saveBusiness(@ModelAttribute("Location") LocationFormBean locationFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("addlocation.htm"));
        try {
            locationDaoInterface.saveLocation(locationFormBean, CommonUtil.getUserBean(request));
            model.addObject("add", true);
        } catch (Exception r) {
            r.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }

    @RequestMapping(value = "/searchlocation.htm", method = RequestMethod.GET)
    public ModelAndView searchlocation(@ModelAttribute("SearchLocation") LocationFormBean locationFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("searchlocation");
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        List locationlist = new ArrayList();
        locationFormBean.setName("%");
        locationlist = locationDaoInterface.searchlocation(locationFormBean);
        model.addObject("locationlist", locationlist);
        model.addObject("UserBean", user);
        locationFormBean.setName("");
        return model;
    }

    @RequestMapping(value = "/searchlocation.htm", method = RequestMethod.POST)
    public ModelAndView searchlocationpost(@ModelAttribute("SearchLocation") LocationFormBean locationFormBean, HttpServletRequest request, HttpServletResponse response) {
        List locationlist = new ArrayList();
        locationlist = locationDaoInterface.searchlocation(locationFormBean);
        ModelAndView model = new ModelAndView("searchlocation");
        model.addObject("locationlist", locationlist);
        return model;
    }

    @RequestMapping(value = "/editlocation.htm", method = RequestMethod.GET)
    public ModelAndView getBusiness(HttpServletRequest request, HttpServletResponse response) {
        String locationid = null;
        LocationFormBean locationFormBean = new LocationFormBean();
        locationid = request.getParameter("lid");
        locationFormBean = locationDaoInterface.getLocation(locationid);
        ModelAndView model = new ModelAndView("editlocation");
        model.addObject("EditLocation", locationFormBean);
        return model;
    }

    @RequestMapping(value = "/editlocation.htm", method = RequestMethod.POST)
    public ModelAndView updateBusiness(@ModelAttribute("EditLocation") LocationFormBean locationFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("editlocation.htm?lid=" + locationFormBean.getLocationid()));
        try {
            locationDaoInterface.updateLocation(locationFormBean, CommonUtil.getUserBean(request));
            model.addObject("edit", true);
        } catch (Exception r) {
            r.printStackTrace();
            model.addObject("edit", false);
        }
        return model;
    }

    @ModelAttribute("status")
    public Map getUserStatus() {
        return dropdownDaoInterface.getStatus();
    }

    @ModelAttribute("country")
    public Map getcountry() {
        return userManagementDaoInterface.getCountry();
    }

//    @ModelAttribute("business")
//    public Map getbusiness(HttpServletRequest request) {
//        return dropdownDaoInterface.getActiveBusinessMap(CommonUtil.getUserBean(request));
//    }

    @ModelAttribute("centerlist")
    public Map getCenterlist() {
       return  locationDaoInterface.getCenterlist(); 
    }
}