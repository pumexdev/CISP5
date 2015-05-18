/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.controller;

import com.pumex.ConnectedInsight.Business.DAO.ActualReferenceDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.BenchMarkDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.Business.DAO.LocationDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.SubProcessDaoInterface;
import com.pumex.ConnectedInsight.Business.beans.BenchmarkFormBean;
import com.pumex.ConnectedInsight.HRData.DAO.HRDataEntryDAOInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.LinkedHashMap;
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
 * @author user
 */
@Controller
public class ActualReferenceController {

    @Autowired
    BusinessInterface businessDaoInterface;
    @Autowired
    ActualReferenceDaoInterface actualReferenceDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    @Autowired
    BenchMarkDaoInterface benchMarkDaoInterface;
    @Autowired
    SubProcessDaoInterface subProcessDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    LocationDaoInterface locationDaoInterface;
    @Autowired
    HRDataEntryDAOInterface hRDataEntryDAOInterface;

    @RequestMapping(value = "/addactualvalue.htm", method = RequestMethod.GET)
    public ModelAndView addActualReferenceGet(@ModelAttribute("ActualValue") BenchmarkFormBean benchmarkFormBean, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        benchmarkFormBean.setOperation(1);
        ModelAndView model = new ModelAndView("addactualvalue");
        model.addObject("UserBean", user);
        return model;
    }

    @RequestMapping(value = "/addactualvalue.htm", method = RequestMethod.POST)
    public ModelAndView addActualReferencePost(@ModelAttribute("ActualValue") BenchmarkFormBean benchmarkFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("addactualvalue");
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        try {
            
            switch (benchmarkFormBean.getOperation()) {
                case 1:
                    actualReferenceDaoInterface.getSearchresult(benchmarkFormBean);
                    Map<Integer,String> actuallist = new LinkedHashMap<Integer, String>();
                    if (benchmarkFormBean.getOperation() == 2 || benchmarkFormBean.getOperation() == 3)
                    {
                        if(benchmarkFormBean.getParamtypecode().equals(1))
                            actuallist = actualReferenceDaoInterface.getRevenueList(benchmarkFormBean);
                        else if(benchmarkFormBean.getParamtypecode().equals(2))
                            actuallist = actualReferenceDaoInterface.getVolumeList(benchmarkFormBean);
                    }
                    model.addObject("ActualValue", actuallist);
                    model.addObject("mapsize", actuallist.size());
                    break;
                case 2:
                    String[] clientrevenue=request.getParameterValues("clientrevenue");
                    benchmarkFormBean.setClientRevenue(clientrevenue);
                    String[] clientvolume=request.getParameterValues("clientvolume");
                    benchmarkFormBean.setClientVolume(clientvolume);
                    actualReferenceDaoInterface.addActualReference(benchmarkFormBean, CommonUtil.getUserBean(request));
                    //actualReferenceDaoInterface.editActualReference(benchmarkFormBean);
                    model=new ModelAndView(new RedirectView("addactualvalue.htm"));
                    model.addObject("edit", true);
                    break;
                case 3:
                    String[] clientrevenue1=request.getParameterValues("clientrevenue");
                    benchmarkFormBean.setClientRevenue(clientrevenue1);
                    String[] clientvolume1=request.getParameterValues("clientvolume");
                    benchmarkFormBean.setClientVolume(clientvolume1);
                    actualReferenceDaoInterface.addActualReference(benchmarkFormBean, CommonUtil.getUserBean(request));
                    model=new ModelAndView(new RedirectView("addactualvalue.htm"));
                    model.addObject("add", true);
                    break;
            }
            model.addObject("businessunitnames", getbusinessunitlist(benchmarkFormBean));
            model.addObject("UserBean", user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }

    @RequestMapping(value = "/editactualvalue.htm", method = RequestMethod.GET)
    public ModelAndView editActualReferenceGet(@ModelAttribute("EditActualValue") BenchmarkFormBean benchmarkFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("editactualvalue");
        Integer parameterId = Integer.parseInt(request.getParameter("avid"));
        benchmarkFormBean = actualReferenceDaoInterface.getActualReferencerDetails(benchmarkFormBean, parameterId);
        model.addObject("EditActualReference", benchmarkFormBean);
        return model;
    }

    @RequestMapping(value = "/editactualvalue.htm", method = RequestMethod.POST)
    public ModelAndView editActualReferencePost(@ModelAttribute("EditActualValue") BenchmarkFormBean benchmarkFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("searchactualvalue.htm"));
        Integer parameterId = Integer.parseInt(request.getParameter("avid"));
        try {
            model.addObject("edit", true);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("edit", false);

        }
        return model;
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

    //@ModelAttribute("businessunitnames")
    public Map getbusinessunitlist(BenchmarkFormBean benchmarkFormBean) 
    {
        return locationDaoInterface.getBusinessUnitByLocation(benchmarkFormBean.getBusinesscenter());
    }

    @ModelAttribute("datelist")
    public Map getDateList() {
        return actualReferenceDaoInterface.getdatelist();

    }

    @ModelAttribute("subprocesslist")
    public Map gtesubprocesslist() {
        /*return subProcessDaoInterface.getSubProcessForVolume1();
        subprocess map query change*/
        String query = SQLSelector.getQuery("actualref.subprocesses");
        return commonDaoInterface.queryForMap(query);
    }
    
    @ModelAttribute("opendates")
    public Map getOpenDates()
    {
        return hRDataEntryDAOInterface.getOpenDates();
    }
    
    @ModelAttribute("descriptionlist")
    public Map getVolumedescription()
    {
        return hRDataEntryDAOInterface.getVolumedescription();
    }
}
