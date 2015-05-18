package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import com.pumex.ConnectedInsight.dashboard.DAO.Charts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
 * @author mjons
 */
@Controller
public class GlobalProfileController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/globalprofile1.htm",method= RequestMethod.GET)
    public ModelAndView globalProfile1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("G1.1and1.2");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean();
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query, date1);
            chartMap.put(date1, chartData);
            chartDataBean=new ChartDataBean("Resource Per Center","Center","Resources",450,600);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query, date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query, date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Resource Per Center (What has changed)","Center","Resources",450,600);
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/globalprofile2.htm",method= RequestMethod.GET)
    public ModelAndView globalProfile2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String date1=request.getParameter("d1");
        String query=SQLSelector.getQuery("G1.3and1.4");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        chartData=chartsDaoInterface.getChartsData(query, date1);
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        chartMap.put(date1, chartData);
        ChartDataBean chartDataBean=new ChartDataBean("Fully loaded labor cost per Center","Center","Cost $",450,600);
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/globalprofile3.htm",method= RequestMethod.GET)
    public ModelAndView globalProfile3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String query=SQLSelector.getQuery("G1.5and1.6");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Average cost per resource per Center","Center","Cost $",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query, date1);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query, date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query, date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Average cost per resource per Center (What has changed)","Center","Thousands",450,600);
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
}
