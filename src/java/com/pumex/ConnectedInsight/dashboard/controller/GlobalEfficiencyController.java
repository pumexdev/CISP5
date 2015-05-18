package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.Charts;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.DAO.DashBoardPdf;
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
public class GlobalEfficiencyController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/globalefficiency1.htm",method= RequestMethod.GET)
    public ModelAndView globalefficiency1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("G2.1and2.2");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean();
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query, date1);
            chartMap.put(date1, chartData);
            chartDataBean=new ChartDataBean("Resource Per Tier","Tier","Resources",450,600);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query, date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query, date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Resource Per Tier (What has changed)","Tier","Resources",450,600);
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
    @RequestMapping(value="/globalefficiency2.htm",method= RequestMethod.GET)
    public ModelAndView globalefficiency2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("G2.3and2.4");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String tier=request.getParameter("tier");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Span of control %","Tier","Resources %",450,600);
        Integer targetstatus=1;
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query, date1,date1);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query, date1,date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query, date2,date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Span of control (What has changed)","Tier","Resources %",450,600);
        
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
    @RequestMapping(value="/globalefficiency3.htm",method= RequestMethod.GET)
    public ModelAndView globalefficiency3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String date1=request.getParameter("d1");
        String query=SQLSelector.getQuery("G2.5and2.6");
        String tier=request.getParameter("tier");
        Integer targetStatus=1;
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        
        chartData=chartsDaoInterface.getChartsData(query, date1,date1);
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        chartMap.put(date1, chartData);
        ChartDataBean chartDataBean=new ChartDataBean("Tier 3 Comparison","Center","Resources %",450,600);
        chartDataBean.setPlotOrientation(PlotOrientation.HORIZONTAL);
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="globalpdf.htm",method= RequestMethod.GET)
    public ModelAndView downloadGlobalDashBoardPdf(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String tier=request.getParameter("tier");
        String[] urlList=new String[]{"globalprofile1.htm?d1="+date1,
                                      "globalprofile1.htm?d1="+date1+"&d2="+date2,
                                      "globalprofile2.htm?d1="+date1,
                                      "globalprofile3.htm?d1="+date1,
                                      "globalprofile3.htm?d1="+date1+"&d2="+date2,
                                      "globalefficiency1.htm?d1="+date1,
                                      "globalefficiency1.htm?d1="+date1+"&d2="+date2,
                                      "globalefficiency2.htm?d1="+date1+"&tier="+tier,
                                      "globalefficiency2.htm?d1="+date1+"&d2="+date2,
                                      "globalefficiency3.htm?d1="+date1,
                                      "globalcost1.htm?d1="+date1,
                                      "globalcost1.htm?d1="+date1+"&d2="+date2,
                                      "globalcost2.htm?d1="+date1};
        new DashBoardPdf().generateGlobalDashboard(request, response, urlList);
        return null;
    }
}
