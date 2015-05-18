package com.pumex.ConnectedInsight.Business.controller;
import com.pumex.ConnectedInsight.Business.DAO.BenchMarkDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.Business.beans.BenchmarkFormBean;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
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
public class BenchMarkController {

    @Autowired
    BusinessInterface businessDaoInterface;
    @Autowired
    BenchMarkDaoInterface benchMarkDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;

    @RequestMapping(value = "/addbenchmark.htm", method = RequestMethod.GET)
    public ModelAndView addBenchmark(@ModelAttribute("AddBenchmark") BenchmarkFormBean benchmarkFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("addbenchmark");
        return model;
    }

    @RequestMapping(value = "/addbenchmark.htm", method = RequestMethod.POST)
    public ModelAndView addBenchmarkpost(@ModelAttribute("AddBenchmark") BenchmarkFormBean benchmarkFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView(new RedirectView("addbenchmark.htm"));
        try 
        {
            Boolean flag = benchMarkDaoInterface.saveBenchmark(benchmarkFormBean);
            model.addObject("add", true);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }

    @RequestMapping(value = "/editbenchmark.htm", method = RequestMethod.GET)
    public ModelAndView editBenchmark(HttpServletRequest request, HttpServletResponse response) {
        String processid = null;
        BenchmarkFormBean benchmarkFormBean = new BenchmarkFormBean();
        processid = request.getParameter("bmid");
        benchmarkFormBean = benchMarkDaoInterface.getBenchmarkData(benchmarkFormBean, processid);
        ModelAndView model = new ModelAndView("editbenchmark");
        model.addObject("businessunit", dropdownDaoInterface.getBusinessUnitMap(benchmarkFormBean.getBusiness().toString()));
        //model.addObject("EditBenchmark", benchmarkFormBean);
        //Map minorList = businessDaoInterface.getsubProcessMap(benchmarkFormBean.getProcess());
        model.addObject("EditBenchmark", benchmarkFormBean);
        return model;
    }

    @RequestMapping(value = "/editbenchmark.htm", method = RequestMethod.POST)
    public ModelAndView editBenchmarkPost(@ModelAttribute("EditBenchmark") BenchmarkFormBean benchmarkFormBean, HttpServletRequest request, HttpServletResponse response) {
        String processid = null;
        processid = request.getParameter("bmid");
        ModelAndView model = new ModelAndView(new RedirectView("searchbenchmark.htm"));       
        model.addObject("businessunit", dropdownDaoInterface.getBusinessUnitMap(benchmarkFormBean.getBusiness().toString()));
        try 
        {
            benchMarkDaoInterface.updateBenchmark(benchmarkFormBean, processid);
            model.addObject("edit", true);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            model.addObject("edit", false);
        }
        return model;
    }

    @RequestMapping(value = "/listbenchmark.htm", method = RequestMethod.GET)
    public ModelAndView getListbenchmark(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("listbenchmark");
        List benchmarkList = benchMarkDaoInterface.getBenchMarkList();
        model.addObject("bmList", benchmarkList);
        return model;
    }

    @RequestMapping(value = "/searchbenchmark.htm", method = RequestMethod.GET)
    public ModelAndView getSearchbenchmark(@ModelAttribute("SearchBenchmark") BenchmarkFormBean benchmarkFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("searchbenchmark");
        return model;
    }

    @RequestMapping(value = "/searchbenchmark.htm", method = RequestMethod.POST)
    public ModelAndView searchbenchmarkPost(@ModelAttribute("SearchBenchmark") BenchmarkFormBean benchmarkFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("searchbenchmark");
        List benchmarkList = benchMarkDaoInterface.getBenchMarkEditList(benchmarkFormBean);
        model.addObject("businessunit", dropdownDaoInterface.getBusinessUnitMap(benchmarkFormBean.getBusiness().toString()));
        model.addObject("benchmarkList", benchmarkList);
        return model;
    }

    @ModelAttribute("businesslist")
    public Map getBusiness(HttpServletRequest request, HttpSession session) {
        return dropdownDaoInterface.getActiveBusinessMap(CommonUtil.getUserBean(request));
    }

    @ModelAttribute("process")
    public Map getProcessList(HttpServletRequest request, HttpSession session) {
        return dropdownDaoInterface.getActiveProcessMap(CommonUtil.getUserBean(request));
    }

    @ModelAttribute("paramlist")
    public Map getParamListNames() {
        return benchMarkDaoInterface.getBecnchmarkNameList();
    }

    @ModelAttribute("locationlist")
    public Map getLocationlist() {
        return benchMarkDaoInterface.getLocationlist();
    }

    @ModelAttribute("statuslist")
    public Map getStatus() {
        return dropdownDaoInterface.getStatus();
    }

    @ModelAttribute("yearlist")
    public Map getYearlist() {
        return benchMarkDaoInterface.getYear();
    }
    @ModelAttribute("businessunitnames")
    public Map getbusinessunitlist()
    {
        return  businessDaoInterface.getBusinessUnitsNames();
    }
}
