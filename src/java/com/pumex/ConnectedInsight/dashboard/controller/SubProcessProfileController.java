package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.Business.DAO.BusinessInterface;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
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
public class SubProcessProfileController
{
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
    @RequestMapping(value="/subprocessgraph1.htm",method= RequestMethod.GET)
    public ModelAndView subprocessgraph1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("S1.1and1.2");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String processid=request.getParameter("pid");
        String subprocessid=request.getParameter("spid");
        String name =request.getParameter("name");
        Integer targetStatus=1;
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Resources per Center for "+name,"Center","Resources",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,subprocessid,date1);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,subprocessid,date1);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,subprocessid,date2);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Resources per Center for "+name+" (What has changed)","Center","Resources",450,600);
        
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/subprocessgraph2.htm",method= RequestMethod.GET)
    public ModelAndView subprocessgraph2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("S1.3and1.4");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String subprocessid=request.getParameter("spid");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("Average labor cost per FTE","Center","Cost $",450,600);
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,date1,subprocessid);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,date1,subprocessid);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,date2,subprocessid);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("Average labor cost per FTE (What has changed)","Center","Cost $",450,600);
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
    @RequestMapping(value="/subprocessgraph3.htm",method= RequestMethod.GET)
    public ModelAndView subprocessgraph3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("S1.5");
        String date1=request.getParameter("d1");
        String tier=request.getParameter("tier");
        String businesscenterid=request.getParameter("bcid");
        String subprocessid=request.getParameter("spid");
        String processid=request.getParameter("pid");
        Integer targetstatus=1;
                
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        chartData=chartsDaoInterface.getChartsData(query,date1,subprocessid);
        chartMap.put(date1, chartData);
        ChartDataBean chartDataBean=new ChartDataBean("Average labor cost per FTE(000's)","","Thousands",450,600);
        
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/subprocesspdf.htm",method= RequestMethod.GET)
    public ModelAndView getSubProcessDashboardPdf(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String tier=request.getParameter("tier");
        String spid=request.getParameter("spid");
        String name = request.getParameter("name");
        String location=request.getParameter("bcid");
        String[] urlList=new String[]{"subprocessgraph1.htm?d1="+date1+"&spid="+spid+"&name="+URLEncoder.encode(name),
                                      "subprocessgraph1.htm?d1="+date1+"&d2="+date2+"&spid="+spid+"&name="+ URLEncoder.encode(name),
                                      "subprocessgraph2.htm?d1="+date1+"&spid="+spid,
                                      "subprocessgraph2.htm?d1="+date1+"&d2="+date2+"&spid="+spid,
                                      "subprocessgraph3.htm?d1="+date1+"&spid="+spid+"&bcid="+location+"&tier="+tier,
                                      "subprocesseffgraph1.htm?d1="+date1+"&spid="+spid,
                                      "subprocesseffgraph1.htm?d1="+date1+"&d2="+date2+"&spid="+spid,
                                      "subprocesseffgraph2.htm?d1="+date1+"&spid="+spid,
                                      "subprocesseffgraph2.htm?d1="+date1+"&d2="+date2+"&spid="+spid,
                                      "subprocesseffgraph3.htm?d1="+date1+"&spid="+spid+"&bcid="+location+"&tier="+tier,
                                      "subprocesssubgraph1.htm?d1="+date1+"&spid="+spid+"&bcid="+location,
                                      "subprocesssubgraph1.htm?d1="+date1+"&spid="+spid+"&bcid="+location+"&d2="+date2,
                                      "subprocesssubgraph2.htm?d1="+date1+"&spid="+spid+"&bcid="+location,
                                      "subprocesssubgraph2.htm?d1="+date1+"&d2="+date2+"&spid="+spid+"&bcid="+location
                                      };
        new DashBoardPdf().generateSubProcessDashboard(request,response, urlList);
        return null;
    }
}
