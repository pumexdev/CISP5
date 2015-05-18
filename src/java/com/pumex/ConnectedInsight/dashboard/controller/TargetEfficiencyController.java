/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.DAO.DashBoardPdf;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import com.pumex.ConnectedInsight.target.dao.TargetDaoInterface;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vishnu AU
 */
@Controller
public class TargetEfficiencyController {

    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    @Autowired
    TargetDaoInterface targetDaoInterface;

    /**
     * Method to return the json string with the data needed for plotting the
     * graphs
     *
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value = "/ajaxtargeteffgraph2.htm", method = RequestMethod.POST)
    public ModelAndView subprocessgraph1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = SQLSelector.getQuery("T3.3and3.4");
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String subprocessid = request.getParameter("spid");
        Map<Comparable, List<ChartDataBean>> chartMap = new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if (dates != null) {
            for (Integer i = 0; i < dates.length; ++i) {
                List<ChartDataBean> chartData = new ArrayList<ChartDataBean>();
                chartData = chartsDaoInterface.getChartsData(query, subprocessid, dates[i], subprocessid, dates[i]);
                if (!dates[i].equals("0")) {
                    chartMap.put(dates[i], chartData);
                }
            }
        }
        model.addObject("Data", chartMap);
        //model.addObject("target",targetDaoInterface.getTargetMetric(subprocessid, "Test"));
        return model;
    }

    @RequestMapping(value = "/ajaxtargeteffgraph1.htm", method = RequestMethod.POST)
    public ModelAndView subprocessgraph2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = SQLSelector.getQuery("T3.1and3.2");
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        String subprocessid = request.getParameter("spid");
        Integer targetStatus = 1;
        Map<Comparable, List<ChartDataBean>> chartMap = new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if (dates != null) {
            for (Integer i = 0; i < dates.length; ++i) {
                List<ChartDataBean> chartData = new ArrayList<ChartDataBean>();
                chartData = chartsDaoInterface.getChartsData(query, dates[i], subprocessid, dates[i], subprocessid);
                if (!dates[i].equals("0")) {
                    chartMap.put(dates[i], chartData);
                }
            }
        }
        model.addObject("Data", chartMap);
        //model.addObject("target",targetDaoInterface.getTargetMetric(subprocessid, "Test"));
        return model;
    }

    /**
     * Method to return the json string with the data needed for plotting the
     * graphs
     *
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value = "/targetpdf.htm", method = RequestMethod.GET)
    public ModelAndView getSubProcessDashboardPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String date1 = request.getParameter("d1");
        String date2 = request.getParameter("d2");
        String tier = request.getParameter("tier");
        String spid = request.getParameter("spid");
        String name = request.getParameter("name");
        String location = request.getParameter("bcid");
        String[] urlList = new String[]{"subprocessgraph1.htm?d1=" + date1 + "&spid=" + spid + "&name=" + URLEncoder.encode(name),
            "subprocessgraph1.htm?d1=" + date1 + "&d2=" + date2 + "&spid=" + spid + "&name=" + URLEncoder.encode(name),
            "subprocessgraph2.htm?d1=" + date1 + "&spid=" + spid,
            "subprocessgraph2.htm?d1=" + date1 + "&d2=" + date2 + "&spid=" + spid,
            "subprocessgraph3.htm?d1=" + date1 + "&spid=" + spid + "&bcid=" + location + "&tier=" + tier,
            "subprocesseffgraph1.htm?d1=" + date1 + "&spid=" + spid,
            "subprocesseffgraph1.htm?d1=" + date1 + "&d2=" + date2 + "&spid=" + spid,
            "subprocesseffgraph2.htm?d1=" + date1 + "&spid=" + spid,
            "subprocesseffgraph2.htm?d1=" + date1 + "&d2=" + date2 + "&spid=" + spid,
            "subprocesseffgraph3.htm?d1=" + date1 + "&spid=" + spid + "&bcid=" + location + "&tier=" + tier,
            "targetgraph3a.htm?d1=" + date1 + "&spid=" + spid + "&bcid=" + location,
            "targetgraph3a.htm?d1=" + date1 + "&spid=" + spid + "&bcid=" + location + "&d2=" + date2,
            "targetgraph3b.htm?d1=" + date1 + "&spid=" + spid + "&bcid=" + location,
            "targetgraph3b.htm?d1=" + date1 + "&d2=" + date2 + "&spid=" + spid + "&bcid=" + location
        };
        new DashBoardPdf().generateTargetDashboard(request, response, urlList);
        return null;
    }

    /**
     * Method to return the json string with the data needed for plotting the
     * graphs
     *
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value = "/ajaxtargetsubprocessorggraph2.htm", method = RequestMethod.POST)
    public ModelAndView subprocessgraph3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = SQLSelector.getQuery("S2.3and2.4");
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        String subprocessid = request.getParameter("spid");
        Integer targetStatus = 1;
        Map<Comparable, List<ChartDataBean>> chartMap = new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if (dates != null) {
            for (Integer i = 0; i < dates.length; ++i) {
                List<ChartDataBean> chartData = new ArrayList<ChartDataBean>();
                chartData = chartsDaoInterface.getChartsData(query, subprocessid, dates[i], subprocessid, dates[i]);
                if (!dates[i].equals("0")) {
                    chartMap.put(dates[i], chartData);
                }
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
    
    @RequestMapping(value = "/ajaxtargeteffgraphT31.htm", method = RequestMethod.POST)
    public ModelAndView targetT31(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        String subprocessid = request.getParameter("spid");
        Integer targetStatus = 1;
        model.addObject("target",targetDaoInterface.getTargetMetric(subprocessid, "1"));//"Volume per FTE"
        return model;
    }
    
    @RequestMapping(value = "/ajaxtargeteffgraphT33.htm", method = RequestMethod.POST)
    public ModelAndView targetT33(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        String subprocessid = request.getParameter("spid");
        Integer targetStatus = 1;
        model.addObject("target",targetDaoInterface.getTargetMetric(subprocessid, "2"));//"Labor cost per transaction"
        return model;
    }
    
    @RequestMapping(value = "/ajaxtargeteffgraphT13.htm", method = RequestMethod.POST)
    public ModelAndView targetT13(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        String subprocessid = request.getParameter("spid");
        Integer targetStatus = 1;
        model.addObject("target",targetDaoInterface.getTargetMetric(subprocessid, "3")); //"Span of Control"
        return model;
    }
    
    @RequestMapping(value = "/ajaxtargeteffgraphL3G1tab.htm", method = RequestMethod.POST)
    public ModelAndView targetL3G1Tab(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        String subprocessid = request.getParameter("spid");
        Integer targetStatus = 1;
        model.addObject("target",targetDaoInterface.getGlobalTotalLevel3G1(subprocessid, dates[0]));
        return model;
    }
    
    @RequestMapping(value = "/ajaxtargeteffgraphL3G2tab.htm", method = RequestMethod.POST)
    public ModelAndView targetL3G2Tab(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        String subprocessid = request.getParameter("spid");
        Integer targetStatus = 1;
        model.addObject("target",targetDaoInterface.getGlobalTotalLevel3G2(subprocessid, dates[0]));
        return model;
    }
    
    @RequestMapping(value = "/ajaxtargeteffgraphL1G1tab.htm", method = RequestMethod.POST)
    public ModelAndView targetL1G1Tab(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        String subprocessid = request.getParameter("spid");
        Integer targetStatus = 1;
        model.addObject("target",targetDaoInterface.getGlobalTotalLevel2G4(subprocessid, dates[0]));
        return model;
    }
}
