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
public class LocationBusinessController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/businessassignment1.htm",method= RequestMethod.GET)
    public ModelAndView businessassignment1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("L4.1and4.2");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String businesscenterid=request.getParameter("bcid");
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("FTEs per Client","Client","FTE",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,date1,businesscenterid);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,date1,businesscenterid);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,date2,businesscenterid);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("FTEs per Client (What has changed?)","Client","FTE",450,600);
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
    @RequestMapping(value="/businessassignment2.htm",method= RequestMethod.GET)
    public ModelAndView businessassignment2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("L4.3and4.4");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String businesscenterid=request.getParameter("bcid");
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("FTE's per $1Billion Revenue","Client","FTE",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,date1,businesscenterid);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,date1,businesscenterid);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,date2,businesscenterid);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("FTE's per $1Billion Revenue (How has that changed?)","Client","FTE",450,600);
        }
        chartDataBean.setPlotOrientation(PlotOrientation.HORIZONTAL);
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
}
