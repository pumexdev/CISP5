package com.pumex.ConnectedInsight.googlechart.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.Charts;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import com.pumex.ConnectedInsight.target.dao.TargetDaoInterface;
import java.io.IOException;
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
 * Process Dashboard Efficiency Charts Controller
 *
 * @author mjons
 */
@Controller
public class ProcessEfficiencyGoogleController {

    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    @Autowired
    TargetDaoInterface targetDaoInterface;
    Charts charts = new Charts();

    /**
     * Method to return the json string with the data needed for plotting the
     * graphs
     *
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value = "/ajaxprocessefficiency1.htm", method = RequestMethod.POST)
    public ModelAndView processefficiency1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = SQLSelector.getQuery("P2.1and2.2");
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        Map<Comparable, List<ChartDataBean>> chartMap = new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if (dates != null) {
            for (Integer i = 0; i < dates.length; ++i) {
                List<ChartDataBean> chartData = new ArrayList<ChartDataBean>();
                chartData = chartsDaoInterface.getChartsData(query, processid, dates[i]);
                if (!dates[i].equals("0")) {
                    chartMap.put(dates[i], chartData);
                }
            }
        }
        model.addObject("Data", chartMap);
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
    @RequestMapping(value = "/ajaxprocessefficiency2.htm", method = RequestMethod.POST)
    public ModelAndView processefficiency2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = SQLSelector.getQuery("P2.3and2.4");
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        String businesscenterid = request.getParameter("bcid");
        Map<Comparable, List<ChartDataBean>> chartMap = new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if (dates != null) {
            for (Integer i = 0; i < dates.length; ++i) {
                List<ChartDataBean> chartData = new ArrayList<ChartDataBean>();
                chartData = chartsDaoInterface.getChartsData(query, processid, dates[i], processid, dates[i]);
                if (!dates[i].equals("0")) {
                    chartMap.put(dates[i], chartData);
                }
            }
        }
        model.addObject("Data", chartMap);
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
    @RequestMapping(value = "/ajaxprocessefficiency3.htm", method = RequestMethod.POST)
    public ModelAndView processefficiency3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = SQLSelector.getQuery("P2.5");
        ModelAndView model = new ModelAndView("jsonView");
        Map params = request.getParameterMap();
        String[] dates = (String[]) params.get("date[]");
        String processid = request.getParameter("pid");
        Integer targetstatus = 1;
        Map<Comparable, List<ChartDataBean>> chartMap = new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if (dates != null) {
            for (Integer i = 0; i < dates.length; ++i) {
                List<ChartDataBean> chartData = new ArrayList<ChartDataBean>();
                chartData = chartsDaoInterface.getChartsData(query, dates[i], processid, dates[i], processid);
                if (!dates[i].equals("0")) {
                    chartMap.put(dates[i], chartData);
                }
            }
        }
        model.addObject("Data", chartMap);
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
    @RequestMapping(value = "/ajaxsubprocessgraphtarget2.htm", method = RequestMethod.POST)
    public ModelAndView subprocessgraph2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = SQLSelector.getQuery("S1.3and1.4");
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
                chartData = chartsDaoInterface.getChartsData(query, dates[i], subprocessid);
                if (!dates[i].equals("0")) {
                    chartMap.put(dates[i], chartData);
                }
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }

    @RequestMapping(value = "/getactiondetailsprocess.htm", method = RequestMethod.POST)
    public ModelAndView getactiondetailsprocess(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("jsonView");
        try {
            model.addObject("actionlist", targetDaoInterface.getActionDetails(request.getParameter("pid")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}