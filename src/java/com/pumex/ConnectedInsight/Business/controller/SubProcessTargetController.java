package com.pumex.ConnectedInsight.Business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.Tier.DAO.TierDaoInterface;
import com.pumex.ConnectedInsight.Tier.beans.TierProcessBean;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SubProcessTargetController
{
    @Autowired
    TierDaoInterface tierDaoInterface;
    @Autowired
    BusinessInterface businessDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    

    @RequestMapping(value = "/addsubprocesstarget.htm", method = RequestMethod.GET)
    public ModelAndView addSubProcessTargetGet(@ModelAttribute("SubProcessTarget") TierProcessBean tierProcessBean, HttpServletResponse response, HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("addsubprocesstarget");
        String uid = null;
        uid = request.getParameter("uid");
        if (uid != null)
        {
            tierProcessBean = tierDaoInterface.getSubProcessData(tierProcessBean, Integer.parseInt(uid));
            model.addObject("SubProcessTarget", tierProcessBean);
            model.addObject("legentname", "Edit Sup Process Target");
        }
        else
        {
            model.addObject("legentname", "Add Sup Process Target");
        }
        return model;
    }
    /* @RequestMapping(value="/addsubprocesstarget.htm",method= RequestMethod.POST)
    public  ModelAndView subProcessTargetPoet(@ModelAttribute("SubProcessTarget")TierProcessBean tierProcessBean,HttpServletResponse response,HttpServletRequest request)
    {
    
    String uid=null;
    uid=request.getParameter("uid");
    if(uid!=null)
    businessDaoInterface.updatesubprocesstarget(tierProcessBean,Integer.parseInt(uid));
    else
    businessDaoInterface.addSupProcesstarget(tierProcessBean);
    
    ModelAndView model=new ModelAndView(new RedirectView("searchsubprocesstarget.htm"));
    return model;
    } */

    @RequestMapping(value = "/searchsubprocesstarget.htm", method = RequestMethod.GET)
    public ModelAndView searcuSubProcessTarget(@ModelAttribute("SearchTierProcessTarget") TierProcessBean tierProcessBean, HttpServletResponse response, HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("searchsubprocesstarget");
        return model;
    }

    @RequestMapping(value = "/searchsubprocesstarget.htm", method = RequestMethod.POST)
    public ModelAndView searchSubPost(@ModelAttribute("SearchTierProcessTarget") TierProcessBean tierProcessBean, HttpServletResponse response, HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("searchsubprocesstarget");
        List subprotargetlist = new ArrayList();
        subprotargetlist = tierDaoInterface.getSubProcessTarget(tierProcessBean);
        model.addObject("subprotargetlist", subprotargetlist);
        return model;
    }

    @ModelAttribute("statuslist")
    public Map getStataus()
    {
        return dropdownDaoInterface.getStatus();
    }

    /*@ModelAttribute("subprocesslist")
    public Map getSubProcessList()
    {
        return businessDaoInterface.getSubProcessList();
    }*/
}
