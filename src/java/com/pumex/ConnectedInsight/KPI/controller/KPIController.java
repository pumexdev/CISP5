/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.KPI.controller;

import com.pumex.ConnectedInsight.Business.DAO.ActualReferenceDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.BenchMarkDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.Business.DAO.LocationDaoInterface;
import com.pumex.ConnectedInsight.Business.DAO.SubProcessDaoInterface;
import com.pumex.ConnectedInsight.Business.beans.KPIBean;
import com.pumex.ConnectedInsight.HRData.DAO.HRDataEntryDAOInterface;
import com.pumex.ConnectedInsight.KPI.DAO.KPIDAOInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonUtil;
import com.pumex.ConnectedInsight.common.Dao.DropdownDaoInterface;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Vishnu
 */
@Controller
public class KPIController
{
    @Autowired
    KPIDAOInterface kPIDaoInterface;
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
    
    
    @RequestMapping(value="/kpi.htm",method= RequestMethod.GET)
    public ModelAndView getKpi(@ModelAttribute("KPI") KPIBean kPIBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("kpi");
        return model;
    }
    
    @RequestMapping(value="/kpi.htm",method= RequestMethod.POST)
    public ModelAndView postKpi(@ModelAttribute("KPI") KPIBean kPIBean, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("kpi");
        List kpiList = kPIDaoInterface.getKpiList();
        model.addObject("businessunitnames", getbusinessunitlist(kPIBean.getBusinesscenter()));
        model.addObject("kpiList", kpiList);
        Map map = new LinkedHashMap();
        map = kPIDaoInterface.getKPIList(kPIBean);
        model.addObject("KPIs", map);
        if(kPIBean.getOperation().equals(2))
        {
            try
            {
                String[] clientAverageScore = request.getParameterValues("centeravg");
                String[] clientScore = request.getParameterValues("clientkpi");
                kPIBean.setCenterAverage(clientAverageScore);
                kPIBean.setClientScore(clientScore);
                kPIDaoInterface.addKpiData(kPIBean, CommonUtil.getUserBean(request));
                model = new ModelAndView(new RedirectView("kpi.htm"));
                model.addObject("add", true);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return model;
    }
    
    @ModelAttribute("locationlist")
    public Map getLocationlist()
    {
        return benchMarkDaoInterface.getLocationlist();
    }
    
    @ModelAttribute("opendates")
    public Map getOpenDates()
    {
        return hRDataEntryDAOInterface.getOpenDates();
    }
    
    public Map getbusinessunitlist(Integer businesscenter) 
    {
        return locationDaoInterface.getBusinessUnitByLocation(businesscenter);
    }
}

