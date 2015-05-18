package com.pumex.ConnectedInsight.googlechart.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import com.pumex.ConnectedInsight.dashboard.DAO.Charts;
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

/**Location Dashboard Efficiency by Organization Charts Controller
 * @author mjons
 */
@Controller
public class LocationOrgGoogleController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/ajaxlocationorgefficiency1.htm",method= RequestMethod.POST)
    public ModelAndView locationorgefficiency1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L2.1and2.2");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String businesscenterid=request.getParameter("bcid");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,businesscenterid,dates[i]);
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/ajaxlocationorgefficiency2.htm",method= RequestMethod.POST)
    public ModelAndView locationorgefficiency2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L2.3");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String businesscenterid=request.getParameter("bcid");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,businesscenterid,dates[i],businesscenterid,dates[i]);
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/ajaxlocationorgefficiency3.htm",method= RequestMethod.POST)
    public ModelAndView locationorgefficiency3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L2.4");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String businesscenterid=request.getParameter("bcid");
        Integer targetStatus=1;
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,dates[i],dates[i]);
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/ajaxlocationorgefficiency4.htm",method= RequestMethod.POST)
    public ModelAndView locationorgefficiency4(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L2.5");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String businesscenterid=request.getParameter("bcid");
        String processid=request.getParameter("pid");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,businesscenterid,dates[i],businesscenterid,dates[i]);
                chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/ajaxlocationorgefficiency5.htm",method= RequestMethod.POST)
    public ModelAndView locationorgefficiency5(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L2.6");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String processId=request.getParameter("pid");
        String targetStatus="1";
        String businesscenterid=request.getParameter("bcid");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,dates[i],dates[i]);
                chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
}
