/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.HRData.controller;

import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.Business.DAO.LocationDaoInterface;
import com.pumex.ConnectedInsight.Excel.Bean.DataEntryBean;
import com.pumex.ConnectedInsight.HRData.DAO.HRDataEntryDAOInterface;
import com.pumex.ConnectedInsight.HRData.beans.HRDataEntryBean;
import com.pumex.ConnectedInsight.HRData.beans.HRFormBean;
import com.pumex.ConnectedInsight.Tier.DAO.TierProcessDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class HRDataEntryController {

    @Autowired
    private HRDataEntryDAOInterface dataEntryDAOInterface;
    @Autowired
    private BusinessInterface businessDaoInterface;
    @Autowired
    private TierProcessDaoInterface tierProcessDaoInterface;
    @Autowired
    DropdownDaoInterface dropdownDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    LocationDaoInterface locationDaoInterface;

    @RequestMapping(value = "/addhrdata.htm", method = RequestMethod.GET)
    public ModelAndView addHRData(@ModelAttribute("AddnewHRdata") HRFormBean hRFormBean, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();
        UserBean user=(UserBean)session.getAttribute("user");
        ModelAndView model = new ModelAndView("addhrdata");
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value = "/addhrdata.htm", method = RequestMethod.POST)
    public ModelAndView addHRDataPost(@ModelAttribute("AddnewHRdata") HRFormBean hRFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("addhrdata.htm"));
        try {
            String[] ftevalues = request.getParameterValues("fte");
            hRFormBean.setFtevalues(ftevalues);
            dataEntryDAOInterface.addHRDate(hRFormBean, CommonUtil.getUserBean(request));
            model.addObject("add", true);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("add", false);
        }
        return model;
    }

    @RequestMapping(value = "/edithrdata.htm", method = RequestMethod.GET)
    public ModelAndView editHRData(@ModelAttribute("EditHRdata") HRFormBean hRFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("edithrdata");
        Integer hrid = Integer.parseInt(request.getParameter("hrid"));
        Integer enid = Integer.parseInt(request.getParameter("enid"));
        hRFormBean = dataEntryDAOInterface.getEmpHRDetails(hRFormBean, enid);
        model.addObject("EditHRdata", hRFormBean);
        hRFormBean = dataEntryDAOInterface.getHRDetails(hRFormBean, hrid);
        model.addObject("EditHRdata", hRFormBean);
        List empdetails_list = new ArrayList();
        empdetails_list = dataEntryDAOInterface.getemployeedetails(hRFormBean, hrid);
        model.addObject("EmpDetails", empdetails_list);
        return model;
    }

    @RequestMapping(value = "/edithrdata.htm", method = RequestMethod.POST)
    public ModelAndView editHRDataPost(@ModelAttribute("EditHRdata") HRFormBean hRFormBean, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView(new RedirectView("edithrdata.htm"));
        Integer hrid = Integer.parseInt(request.getParameter("hrid"));
        Integer enid = Integer.parseInt(request.getParameter("enid"));
        hRFormBean.setEmpentryid(hrid);
        try {
            String[] ftevalues = request.getParameterValues("fte");
            hRFormBean.setFtevalues(ftevalues);
            dataEntryDAOInterface.editHRdata(hRFormBean, CommonUtil.getUserBean(request));
            model.addObject("add", true);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("add", false);
        }
        model.addObject("hrid", hrid);
        model.addObject("enid", enid);
        return model;
    }

    @RequestMapping(value = "/searchhrdataentry.htm", method = RequestMethod.GET)
    public ModelAndView searchHrdata(/*@ModelAttribute("SearchHRDataEntry") DataEntryBean dataEntryBean*/) {
        ModelAndView model = new ModelAndView("searchhrdataentry");
        return model;
    }

    @RequestMapping(value = "/searchhrdataentry.htm", method = RequestMethod.POST)
    public ModelAndView searchHrdatapost(/*@ModelAttribute("SearchHRDataEntry") DataEntryBean dataEntryBean,*/HttpServletRequest request) {
        ModelAndView model = new ModelAndView("searchhrdataentry");
        List hrData = new ArrayList();
        DataEntryBean dataEntryBean = new DataEntryBean();
        String entry_date = request.getParameter("entry_date");
        String business_center_id = request.getParameter("business_center_id");
        /*if (no == null || no.equals("")) {
            entry_date = request.getParameter("entry_date");
            business_center_id = request.getParameter("business_center_id");
            model.addObject("check", "true");
        } else {
            model.addObject("check", "false");
        }*/
        dataEntryBean.setBusiness_center_id(Integer.parseInt(business_center_id));
        dataEntryBean.setEntry_date(entry_date);
        hrData = dataEntryDAOInterface.searchHRData(dataEntryBean, CommonUtil.getUserBean(request));
        model.addObject("hrlist", hrData);
        return model;
    }

    @RequestMapping(value = "/hrdatareport.htm", method = RequestMethod.GET)
    public ModelAndView getHrDataReport(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("hrdatareport");
        return model;
    }

    @RequestMapping(value = "/hrdatareport.htm", method = RequestMethod.POST)
    public ModelAndView postHrDataReport(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("hrdatareport");
        DataEntryBean dataEntryBean = new DataEntryBean();
        dataEntryBean.setBusiness_center_id(Integer.parseInt(request.getParameter("business_center_id")));
        dataEntryBean.setEntry_date(request.getParameter("entry_date"));
        //model.addObject("HRData", dataEntryDAOInterface.getHRDataReport(CommonUtil.getUserBean(request)));
        model.addObject("business_center_id", request.getParameter("business_center_id"));
        model.addObject("entry_date", request.getParameter("entry_date"));
        model.addObject("HRData", dataEntryDAOInterface.searchHRData(dataEntryBean, CommonUtil.getUserBean(request)));
        return model;
    }

    @RequestMapping(value = "/edithrdataperson.htm", method = RequestMethod.POST)
    public ModelAndView edithrdatapersonpost(@ModelAttribute("Edithrdataentryperson") HRDataEntryBean hRDataEntryBean) {
        dataEntryDAOInterface.updateHRdataentryperson(hRDataEntryBean);
        return new ModelAndView(new RedirectView("listBU.htm?buid=" + hRDataEntryBean.getEmpid()));
    }

    @ModelAttribute("businesscenterlist")
    public Map getUserStatus(HttpServletRequest request) {
        return dropdownDaoInterface.getBusinessCenterMap(CommonUtil.getUserBean(request));
    }

    @ModelAttribute("employeetype")
    public Map getEmployeeType(HttpServletRequest request) {
        return dropdownDaoInterface.getemployeeType();
    }

    @ModelAttribute("tierlist")
    public Map getTierList() {
        return tierProcessDaoInterface.getTierList();
    }

    @ModelAttribute("processlist")
    public Map getProcessList(HttpServletRequest request) {
        return dropdownDaoInterface.getActiveProcessMap(CommonUtil.getUserBean(request));
    }

    @ModelAttribute("subprocesslist")
    public Map getSubProcessList(HttpServletRequest request) {
        return dataEntryDAOInterface.getsubprocesslist();
    }

    @ModelAttribute("datelist")
    public Map getDateList() {
        return dataEntryDAOInterface.getdatelist();
    }

    @ModelAttribute("clientlist")
    public Map getClientList(HttpServletRequest request) {
        UserBean userBean = CommonUtil.getUserBean(request);
        if (userBean.getUserlevel() == 4) {
            return locationDaoInterface.getBusinessUnitByLocation(userBean.getBusinesscenterid());
        }
        return dataEntryDAOInterface.getclientlist();
    }

    @ModelAttribute("entryDates")
    public Map getEntryDates(HttpServletRequest request) {
        return dataEntryDAOInterface.getEntryDates();
    }

    @ModelAttribute("ftelist")
    public Map getftelist() {
        Map ftelist = new HashMap();
        ftelist.put(0.1, 0.1);
        ftelist.put(0.2, 0.2);
        ftelist.put(0.3, 0.3);
        ftelist.put(0.4, 0.4);
        ftelist.put(0.5, 0.5);
        ftelist.put(0.6, 0.6);
        ftelist.put(0.7, 0.7);
        ftelist.put(0.8, 0.8);
        ftelist.put(0.9, 0.9);
        ftelist.put(1.0, 1.0);
        return commonDaoInterface.sortMapByKey(ftelist);
    }
}
