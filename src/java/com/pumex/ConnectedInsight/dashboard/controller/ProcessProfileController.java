package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.Charts;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.DAO.DashBoardPdf;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import java.io.IOException;
import java.net.URLEncoder;
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
public class ProcessProfileController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/processprofile1.htm",method= RequestMethod.GET)
    public ModelAndView processprofile1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("P1.1and1.2");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String processid=request.getParameter("pid");
        String name =request.getParameter("name");
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Resources per Center for "+name,"Center","Resources",450,600);
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
            chartDataBean=new ChartDataBean("Resources per Center  for "+name+" (What has changed)","Center","Resources",450,600);
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/processprofile2.htm",method= RequestMethod.GET)
    public ModelAndView processprofile2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("P1.3and1.4");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String processid=request.getParameter("pid");
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Average labor cost per FTE","Center","Cost $",450,600);
        
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
            chartDataBean=new ChartDataBean("Average labor cost per FTE (What has changed)","Center","Cost $",450,600);
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/processprofile3.htm",method= RequestMethod.GET)
    public ModelAndView processprofile3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("P1.5");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String processid=request.getParameter("pid");
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        ChartDataBean chartDataBean=new ChartDataBean("Average labor cost per FTE (000's)","Average labor Cost","Cost $",450,600);
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        
        if(date2==null)
        {
            chartData=new ArrayList<ChartDataBean>();
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
            chartDataBean=new ChartDataBean("Average labor cost per FTE (000's)","Average labor Cost","Thousands",450,600);
        }
        
        chartData=chartsDaoInterface.getChartsData(query,processid,date2);
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/processpdf.htm",method= RequestMethod.GET)
    public ModelAndView downloadProcessPdf(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String tier=request.getParameter("tier");
        String location=request.getParameter("bcid");
        String pid=request.getParameter("pid");
        String name = request.getParameter("name");
        String[] urlList=new String[]{"processprofile1.htm?d1="+date1+"&pid="+pid+"&name="+ URLEncoder.encode(name),
                                      "processprofile1.htm?d1="+date1+"&d2="+date2+"&pid="+pid+"&name="+URLEncoder.encode(name),
                                      "processprofile2.htm?d1="+date1+"&pid="+pid,
                                      "processprofile2.htm?d1="+date1+"&d2="+date2+"&pid="+pid,
                                      "processprofile3.htm?d1="+date1+"&pid="+pid+"&d2="+date2,
                                      "processefficiency1.htm?d1="+date1+"&pid="+pid+"&bcid="+location,
                                      "processefficiency1.htm?d1="+date1+"&d2="+date2+"&pid="+pid+"&bcid="+location,
                                      "processefficiency2.htm?d1="+date1+"&tier="+tier+"&pid="+pid+"&bcid="+location,
                                      "processefficiency2.htm?d1="+date1+"&d2="+date2+"&tier="+tier+"&pid="+pid+"&bcid="+location,
                                      "processefficiency3.htm?d1="+date1+"&pid="+pid+"&bcid="+location+"&tier="+tier};
        
        new DashBoardPdf().generateProcessDashboard(request, response, urlList);
        return null;
    }
}
