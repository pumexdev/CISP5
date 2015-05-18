/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.Charts;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.plot.PlotOrientation;
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
public class TargetController3 {
    
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    Charts charts=new Charts();
    @Autowired
    BusinessInterface businessDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/targetgraph3a.htm",method= RequestMethod.GET)
    public ModelAndView targetgraph3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("T3.1and3.2");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String processid=request.getParameter("pid");
        String subprocessid=request.getParameter("spid");
        Integer targetStatus=1;
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Sub-Process Volume per FTE","Center","Volume",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,date1,subprocessid,date1,subprocessid);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,date1,subprocessid,date1,subprocessid);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,date2,subprocessid,date2,subprocessid);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Sub-Process Volume per FTE (What has changed)","Center","Volume",450,600);
        
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/targetgraph3b.htm",method= RequestMethod.GET)
    public ModelAndView targetgraph3b(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("T3.3and3.4");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String subprocessid=request.getParameter("spid");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Average labor cost per transaction","Center","Average Labor cost",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,subprocessid,date1,subprocessid,date1);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,subprocessid,date1,subprocessid,date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,subprocessid,date2,subprocessid,date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Average labor cost per transaction (What has changed)","Center","Average Labor cost",450,600);
        }
        chartDataBean.setPlotOrientation(PlotOrientation.HORIZONTAL);
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
}
