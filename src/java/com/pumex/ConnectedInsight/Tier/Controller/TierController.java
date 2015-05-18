package com.pumex.ConnectedInsight.Tier.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pumex.ConnectedInsight.Tier.beans.TierBean;
import java.util.ArrayList;
import java.util.List;
import com.pumex.ConnectedInsight.Tier.DAO.TierDaoInterface;
import com.pumex.ConnectedInsight.Tier.DAO.TierProcessDaoInterface;
import java.util.Map;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TierController
{

    @Autowired
    TierDaoInterface tierDaoInterface;
    @Autowired
    TierProcessDaoInterface tierProcessDaoInterface;

    @RequestMapping(value = "/searchtier.htm", method = RequestMethod.POST)
    public ModelAndView gettierList(@ModelAttribute("Tier") TierBean tierBean, HttpServletResponse response, HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("searchtier");
        List tierlist = new ArrayList();
        tierlist = tierDaoInterface.getTierList(tierBean);
        model.addObject("tierlist", tierlist);
        return model;
    }

    @RequestMapping(value = "/searchtier.htm", method = RequestMethod.GET)
    public ModelAndView getTierListPost(@ModelAttribute("Tier") TierBean tierBean1, @ModelAttribute("AddNewTier") TierBean tierBean2, @ModelAttribute("EditTier") TierBean tierBean3, HttpServletResponse response, HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("searchtier");
        return model;
    }

    @RequestMapping(value = "/addtier.htm", method = RequestMethod.POST)
    public ModelAndView addTier(@ModelAttribute("AddNewTier") TierBean tierBean, HttpServletResponse response, HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView(new RedirectView("searchtier.htm"));
        try 
        {
            tierDaoInterface.addTier(tierBean);
            model.addObject("add", true);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }

    @RequestMapping(value = "/edittier.htm", method = RequestMethod.POST)
    public ModelAndView editTier(@ModelAttribute("EditTier") TierBean tierBean, HttpServletResponse response, HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView(new RedirectView("searchtier.htm"));
        try
        {
            tierDaoInterface.editTier(tierBean);
            model.addObject("edit", true);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            model.addObject("edit", false);
        }
        return model;
    }
    /*@RequestMapping(value = "/tieroperation.htm", method = RequestMethod.GET)
    public ModelAndView tieropnGet(@ModelAttribute("AddNewTier") TierBean tierBean, HttpServletResponse response, HttpServletRequest request)
    {
    ModelAndView model = new ModelAndView("tieroperation");
    String uid = null;
    uid = request.getParameter("uid");
    if (uid != null)
    {
    tierBean = tierDaoInterface.getTierList(Integer.parseInt(uid), tierBean);
    model.addObject("AddNewTier", tierBean);
    model.addObject("legendmsg", "Edit Tier");
    }
    else
    {
    model.addObject("legendmsg", "Add Tier");
    }
    return model;
    }
    
    @RequestMapping(value = "/tieroperation.htm", method = RequestMethod.POST)
    public ModelAndView tieropnPost(@ModelAttribute("AddNewTier") TierBean tierBean, HttpServletResponse response, HttpServletRequest request)
    {
    ModelAndView model = new ModelAndView(new RedirectView("searchtier.htm"));
    String uid = null;
    uid = request.getParameter("uid");
    if (uid == null)
    {
    tierDaoInterface.addTier(tierBean);
    }
    else
    {
    tierDaoInterface.editTier(tierBean, Integer.parseInt(uid));
    }
    return model;
    }*/

    @ModelAttribute("tierlist")
    public Map getTierList()
    {
        return tierProcessDaoInterface.getTierList();
    }
}
