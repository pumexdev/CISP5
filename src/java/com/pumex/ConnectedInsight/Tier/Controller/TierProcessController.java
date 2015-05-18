package com.pumex.ConnectedInsight.Tier.Controller;

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
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TierProcessController {

    @Autowired
    TierProcessDaoInterface tierProcessDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;

    @RequestMapping(value = "/addtierprocess.htm", method = RequestMethod.GET)
    public ModelAndView tierProcessTarget(@ModelAttribute("AddTierProcess") TierProcessBean tierProcessBean, HttpServletResponse response, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("addtierprocess");
        String uid = null;
        uid = request.getParameter("uid");
        if (uid != null) {
            tierProcessBean = tierProcessDaoInterface.editTierProcessTarget(tierProcessBean, Integer.parseInt(uid));
            model.addObject("AddTierProcess", tierProcessBean);
            model.addObject("legentname", "Edit Tier Process target");
        }
        else 
        {
            model.addObject("legentname", "Add Tier Process target");
        }
        return model;
    }

    @RequestMapping(value = "/addtierprocess.htm", method = RequestMethod.POST)
    public ModelAndView tierProcessTargetPost(@ModelAttribute("AddTierProcess") TierProcessBean tierProcessBean, HttpServletResponse response, HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView(new RedirectView("searchtierprocess.htm"));
        String uid = null;
        uid = request.getParameter("uid");
        if (uid != null)
        {
           
             try {
                 tierProcessDaoInterface.editTierProcess(tierProcessBean, Integer.parseInt(uid));
                model.addObject("edit", true);
            } catch (Exception ex) {
                ex.printStackTrace();
                model.addObject("edit", false);
            }
        }
        else
        {
           
             try {
                tierProcessDaoInterface.addTierProcess(tierProcessBean);
                model.addObject("add", true);
            } catch (Exception ex) {
                ex.printStackTrace();
                model.addObject("add", false);
            }
            
        }
        return model;
    }

    @RequestMapping(value = "/searchtierprocess.htm", method = RequestMethod.GET)
    public ModelAndView searchTierProcess(@ModelAttribute("SearchTierProcessTarget") TierProcessBean tierProcessBean, HttpServletResponse response, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("searchtierprocess");
        return model;
    }

    @RequestMapping(value = "/searchtierprocess.htm", method = RequestMethod.POST)
    public ModelAndView searctTierPrecessPost(@ModelAttribute("SearchTierProcessTarget") TierProcessBean tierProcessBean, HttpServletResponse response, HttpServletRequest request) {
        List tierprocesslist = new ArrayList();
        ModelAndView model = new ModelAndView("searchtierprocess");
        tierprocesslist = tierProcessDaoInterface.getTierProcessTarget(tierProcessBean);
        model.addObject("tierprocesslist", tierprocesslist);
        return model;
    }

    @ModelAttribute("tierlist")
    public Map getTierList() {
        return tierProcessDaoInterface.getTierList();
    }

    @ModelAttribute("processlist")
    public Map getProcessList(HttpServletRequest request) {
        return dropdownDaoInterface.getProcessMap(CommonUtil.getUserBean(request));
    }

    @ModelAttribute("statuslist")
    public Map getStatus(HttpServletRequest request) {
        return dropdownDaoInterface.getStatus();
    }

    @ModelAttribute("processyearlist")
    public Map getFromDate()
    {
        return tierProcessDaoInterface.getProcessYear();
    }
}
