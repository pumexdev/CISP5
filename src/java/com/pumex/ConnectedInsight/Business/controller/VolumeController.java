/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.controller;

import com.pumex.ConnectedInsight.Business.beans.VolumeFormBean;
import com.pumex.ConnectedInsight.Business.DAO.BenchMarkDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.UserManagement.DAO.UserManagementDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class VolumeController {

    @Autowired
    BusinessInterface businessDaoInterface;
    @Autowired
    BenchMarkDaoInterface benchMarkDaoInterface;
    @Autowired
    UserManagementDaoInterface userManagementDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;

    @RequestMapping(value = "/addvolume.htm", method = RequestMethod.GET)
    public ModelAndView getAddVolume(@ModelAttribute("AddVolume") VolumeFormBean volumeFormBean, HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("addvolume");

        return model;
    }

    @RequestMapping(value = "/editvolume.htm", method = RequestMethod.GET)
    public ModelAndView getEditVolume(@ModelAttribute("EditVolume") VolumeFormBean volumeFormBean, HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("editvolume");

        return model;
    }

    @RequestMapping(value = "/searchvolume.htm", method = RequestMethod.GET)
    public ModelAndView getListVolume(@ModelAttribute("SearchVolume") VolumeFormBean volumeFormBean, HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("searchvolume");

        return model;
    }

    @RequestMapping(value = "/searchvolumedescription.htm", method = RequestMethod.GET)
    public ModelAndView getVolumedescription(@ModelAttribute("SearchVolumeDescription") VolumeFormBean volumeFormBean,@ModelAttribute("AddDescription")VolumeFormBean volumeFormBean1,@ModelAttribute("EditDescription")VolumeFormBean volumeFormBean2) {
        ModelAndView model = new ModelAndView("searchvolumedescription");
        return model;
    }
    @RequestMapping(value="/addvolumedescription.htm",method= RequestMethod.POST)
    public ModelAndView volumeDescriptionPost(@ModelAttribute("AddDescription")VolumeFormBean volumeFormBean)
    {
        ModelAndView model=new ModelAndView(new RedirectView("searchvolumedescription.htm"));
        try
        {
           benchMarkDaoInterface.addVolumedescription(volumeFormBean);
            model.addObject("add",true);
        }catch(Exception e)
        {
            e.printStackTrace();
            model.addObject("add", false);
                    
        }
        return model;
    }
    @RequestMapping(value="/editvolumedescription.htm",method= RequestMethod.POST)
    public ModelAndView editVolumeDescriptionPost(@ModelAttribute("EditDescription")VolumeFormBean volumeFormBean)
    {
        ModelAndView model=new ModelAndView(new RedirectView("searchvolumedescription.htm"));
        try
        {
           benchMarkDaoInterface.editVolumedescription(volumeFormBean);
            model.addObject("edit",true);
        }catch(Exception e)
        {
            e.printStackTrace();
            model.addObject("edit", false);
                    
        }
        return model;
    }
    @ModelAttribute("businesslist")
    public Map getBusiness(HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        return dropdownDaoInterface.getBusinessMap(user);
    }

    /*@ModelAttribute("businessunitnames")
    public Map getBusinessUnitName() {
    return userManagementDaoInterface.getBusinessunit();
    }*/
    @ModelAttribute("businessCenter")
    public Map getBusinessCenter(HttpServletRequest request) {
        return dropdownDaoInterface.getBusinessCenterMap(CommonUtil.getUserBean(request));
    }

    @ModelAttribute("process")
    public Map getProcessList(HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        return dropdownDaoInterface.getProcessMap(user);
    }

    @ModelAttribute("statuslist")
    public Map getStatuslist() {
        return dropdownDaoInterface.getStatus();
    }
    @ModelAttribute("volumelist")
    public Map getVolumedesclist() {
        return benchMarkDaoInterface.getVlolumedescriptionlist();
    }
    
    @ModelAttribute("subprocesslist")
    public Map getSubProcessList() {
        return businessDaoInterface.getSubProcessList();
    }
}
