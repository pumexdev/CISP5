package com.pumex.ConnectedInsight.dashboard.controller;

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
 * @author mjons
 */
@Controller
public class ProcessEfficiencyController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/processefficiency1.htm",method= RequestMethod.GET)
    public ModelAndView processefficiency1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("P2.1and2.2");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String processid=request.getParameter("pid");
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Resources per Tier","Tier","Resources",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,processid,date1);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,processid,date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,processid,date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Resources per Tier (What has changed)","Tier","Resources",450,600);
        }
        chartDataBean.setPlotOrientation(PlotOrientation.HORIZONTAL);
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/processefficiency2.htm",method= RequestMethod.GET)
    public ModelAndView processefficiency2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("P2.3and2.4");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String processid=request.getParameter("pid");
        String businesscenterid=request.getParameter("bcid");
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();        
        ChartDataBean chartDataBean=new ChartDataBean("% Resource Per Tier","Tier","% Resources",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,processid,date1,processid,date1);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,processid,date1,processid,date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,processid,date2,processid,date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("% Resource Per Tier (What has changed)","Tier","% Resources",450,600);
        }
        chartDataBean.setPlotOrientation(PlotOrientation.HORIZONTAL);
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/processefficiency3.htm",method= RequestMethod.GET)
    public ModelAndView processefficiency3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("P2.5");
        String date1=request.getParameter("d1");
        String processid=request.getParameter("pid");
        String tier=request.getParameter("tier");
        Integer targetstatus=1;
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();        
        chartData=chartsDaoInterface.getChartsData(query,date1,processid,date1,processid);
        chartMap.put(date1, chartData);
        ChartDataBean chartDataBean=new ChartDataBean("Tier 3 Comparison","Center","(% Span of control )",450,600);
        chartDataBean.setPlotOrientation(PlotOrientation.HORIZONTAL);
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
}