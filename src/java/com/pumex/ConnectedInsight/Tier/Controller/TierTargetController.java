/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Tier.Controller;

/**
 *
 * @author user
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pumex.ConnectedInsight.Tier.DAO.TierProcessDaoInterface;
import com.pumex.ConnectedInsight.Tier.beans.TierProcessBean;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TierTargetController {

    @Autowired
    TierProcessDaoInterface tierProcessDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;

    @RequestMapping(value = "/addtiertarget.htm", method = RequestMethod.GET)
    public ModelAndView addTierTarget(@ModelAttribute("TierTarget") TierProcessBean tierProcessBean, HttpServletResponse response, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("addtiertarget");
        String uid = null;
        uid = request.getParameter("uid");
        if (uid != null) {
            tierProcessBean = tierProcessDaoInterface.getTierProcessList(tierProcessBean, Integer.parseInt(uid));
            model.addObject("TierTarget", tierProcessBean);
            model.addObject("legentname", "Edit Tier Target");
        } else {
            model.addObject("legentname", "Add Tier Target");
        }
        return model;
    }

    @RequestMapping(value = "/addtiertarget.htm", method = RequestMethod.POST)
    public ModelAndView addTierTaregtPost(@ModelAttribute("TierTarget") TierProcessBean tierProcessBean, HttpServletResponse response, HttpServletRequest request) {
        String uid = null;
        uid = request.getParameter("uid");
        ModelAndView model = new ModelAndView(new RedirectView("searchtiertarget.htm"));
        if (uid != null) {
            try {
                tierProcessDaoInterface.editTierTarget(tierProcessBean, Integer.parseInt(uid));
                model.addObject("edit", true);
            } catch (Exception ex) {
                ex.printStackTrace();
                model.addObject("edit", false);
            }
        } else {

            try {
                tierProcessDaoInterface.addTierTarget(tierProcessBean);
                model.addObject("add", true);
            } catch (Exception ex) {
                ex.printStackTrace();
                model.addObject("add", false);
            }
        }

        return model;
    }

    @RequestMapping(value = "/searchtiertarget.htm", method = RequestMethod.GET)
    public ModelAndView searchTierTarget(@ModelAttribute("SearchTierTarget") TierProcessBean tierProcessBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("searchtiertarget");
        return model;
    }

    @RequestMapping(value = "/searchtiertarget.htm", method = RequestMethod.POST)
    public ModelAndView searchTierTargetPost(@ModelAttribute("SearchTierTarget") TierProcessBean tierProcessBean, HttpServletResponse response, HttpServletRequest request) {
        List tiertargetlist = new ArrayList();
        String date;
        date = tierProcessBean.getFromdate();
        date += tierProcessBean.getMonth();
        tiertargetlist = tierProcessDaoInterface.getTierTarget(date);
        ModelAndView model = new ModelAndView("searchtiertarget");
        model.addObject("tiertargetlist", tiertargetlist);
        return model;
    }

    @ModelAttribute("tierlist")
    public Map getTierList() {
        return tierProcessDaoInterface.getTierList();
    }

    @ModelAttribute("statuslist")
    public Map getStataus() {
        return dropdownDaoInterface.getStatus();
    }

    @ModelAttribute("yearlist")
    public Map getFromDate() {
        return tierProcessDaoInterface.getYear();
    }
}
