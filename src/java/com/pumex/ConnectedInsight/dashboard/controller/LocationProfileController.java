package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import com.pumex.ConnectedInsight.dashboard.DAO.Charts;
import com.pumex.ConnectedInsight.dashboard.DAO.DashBoardPdf;
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
public class LocationProfileController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/locationprofile1.htm",method= RequestMethod.GET)
    public ModelAndView locationprofile1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("L1.1and1.2");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String businesscenterid=request.getParameter("bcid");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Resource Per Process","Process","Resources",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,businesscenterid,date1);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,businesscenterid,date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,businesscenterid,date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Resource Per Process (What has changed)","Process","Resources",450,600);
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/locationprofile2.htm",method= RequestMethod.GET)
    public ModelAndView locationprofile2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("L1.3and1.4");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String businesscenterid=request.getParameter("bcid");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Resources per Client","Client","Resources",450,600);
        
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,businesscenterid,date1);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,businesscenterid,date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,businesscenterid,date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Resources per Client (What has changed)","Client","Resources",450,600);
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/locationprofile3.htm",method= RequestMethod.GET)
    public ModelAndView locationprofile3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("L1.5and1.6");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String businesscenterid=request.getParameter("bcid");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Resources per cost Center","Cost Center","Resources",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,businesscenterid,date1);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,businesscenterid,date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,businesscenterid,date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Resources per cost Center (What has changed)","CostCenter","Resources",450,600);
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/locationprofile4.htm",method= RequestMethod.GET)
    public ModelAndView locationprofile4(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("L1.7and1.8");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String businesscenterid=request.getParameter("bcid");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Employees per Grade","Cost Center","Resources",450,600);
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
            chartDataBean=new ChartDataBean("Employees per Grade (What has changed)","Cost Center","Resources",450,600);
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/locationpdf.htm",method= RequestMethod.GET)
    public ModelAndView downloadLocationPdf(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String tier=request.getParameter("tier");
        String location=request.getParameter("bcid");
        String pid=request.getParameter("pid");
        String[] urlList=new String[]{"locationprofile1.htm?d1="+date1+"&bcid="+location,
                                      "locationprofile1.htm?d1="+date1+"&d2="+date2+"&bcid="+location,
                                      "locationprofile2.htm?d1="+date1+"&bcid="+location,
                                      "locationprofile2.htm?d1="+date1+"&d2="+date2+"&bcid="+location,
                                      "locationprofile3.htm?d1="+date1+"&bcid="+location,
                                      "locationprofile3.htm?d1="+date1+"&d2="+date2+"&bcid="+location,
                                      "locationprofile4.htm?d1="+date1+"&bcid="+location,
                                      "locationprofile4.htm?d1="+date1+"&d2="+date2+"&bcid="+location,
                                      "locationorgefficiency1.htm?d1="+date1+"&bcid="+location,
                                      "locationorgefficiency1.htm?d1="+date1+"&d2="+date2+"&bcid="+location,
                                      "locationorgefficiency2.htm?d1="+date1+"&bcid="+location,
                                      "locationorgefficiency3.htm?d1="+date1+"&d2="+date2+"&bcid="+location+"&tier="+tier,
                                      "locationorgefficiency4.htm?d1="+date1+"&bcid="+location+"&tier="+tier,
                                      "locationprocess1.htm?d1="+date1+"&bcid="+location+"&pid="+pid,
                                      "locationprocess2.htm?d1="+date1+"&pid="+pid+"&bcid="+location,
                                      "locationprocess3.htm?d1="+date1+"&bcid="+location+"&pid="+pid,
                                      "locationprocess4.htm?d1="+date1+"&pid="+pid+"&bcid="+location,
                                      "businessassignment1.htm?d1="+date1+"&bcid="+location,
                                      "businessassignment1.htm?d1="+date1+"&d2="+date2+"&bcid="+location,
                                      "businessassignment2.htm?d1="+date1+"&bcid="+location,
                                      "businessassignment2.htm?d1="+date1+"&d2="+date2+"&bcid="+location};
        
        new DashBoardPdf().generateLocationDashboard(request, response, urlList);
        return null;
    }
}
