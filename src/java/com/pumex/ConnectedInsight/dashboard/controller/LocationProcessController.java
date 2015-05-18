package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.Charts;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.BenchMarkDataBean;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
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
public class LocationProcessController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/locationprocess1.htm",method= RequestMethod.GET)
    public ModelAndView locationprocess1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("L3.1");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        ChartDataBean chartDataBean=new ChartDataBean("AP Invoice Volume per FTE","Client","Volume",450,600);
        
        if(date2==null)
        {
            chartData=chartsDaoInterface.getChartsData(query,subprocessid,date1,businesscenterid);
            chartMap.put(date1, chartData);
        }
        else
        {
            chartData=chartsDaoInterface.getChartsData(query,subprocessid,date1,businesscenterid);
            chartMap.put(date1, chartData);
            chartData=new ArrayList<ChartDataBean>();
            chartData=chartsDaoInterface.getChartsData(query,subprocessid,date2,businesscenterid);
            chartMap.put(date2, chartData);
            chartDataBean=new ChartDataBean("AP Invoice Volume per FTE (What has changed)","Client","Volume",450,600);
        }
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/locationprocess2.htm",method= RequestMethod.GET)
    public ModelAndView locationprocess2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("L3.2");
        String date1=request.getParameter("d1");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        Integer targetstatus=1;
        Integer paramTypeCode=2;
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        List<BenchMarkDataBean> benchMarkData=new ArrayList<BenchMarkDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        Map<Comparable,List<BenchMarkDataBean>>benchMarkMap=new HashMap<Comparable, List<BenchMarkDataBean>>();
        
        chartData=chartsDaoInterface.getChartsData(query,date1,subprocessid,date1,subprocessid);
        chartMap.put(date1, chartData);
        ChartDataBean chartDataBean=new ChartDataBean("Average Volume per FTE","Center","Volume per FTE",450,600);
              
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/locationprocess3.htm",method= RequestMethod.GET)
    public ModelAndView locationprocess3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("L3.3");
        String date1=request.getParameter("d1");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        chartData=chartsDaoInterface.getChartsData(query,subprocessid,date1,businesscenterid);
        chartMap.put(date1, chartData);
        ChartDataBean chartDataBean=new ChartDataBean("Personnel Cost per Transaction","Client","Personnel Cost $",450,600);
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/locationprocess4.htm",method= RequestMethod.GET)
    public ModelAndView locationprocess4(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String query=SQLSelector.getQuery("L3.4");
        String date1=request.getParameter("d1");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        Integer paramTypeCode=3;
        
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        List<BenchMarkDataBean> benchMarkData=new ArrayList<BenchMarkDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        Map<Comparable,List<BenchMarkDataBean>>benchMarkMap=new HashMap<Comparable, List<BenchMarkDataBean>>();
        
        chartData=chartsDaoInterface.getChartsData(query,date1,subprocessid,date1,subprocessid);
        chartMap.put(date1, chartData);
        ChartDataBean chartDataBean=new ChartDataBean("Personnel cost per transaction (How does it compare?)","Center","Personnel Cost $",450,600);
        charts.getReports(response, chartMap, chartDataBean);
        return null;
    }
}
