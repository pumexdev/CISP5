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

/**Location Dashboard Efficiency by Profile Charts Controller
 * @author mjons
 */
@Controller
public class LocationProfileGoogleController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/ajaxlocationprofile1.htm",method= RequestMethod.POST)
    public ModelAndView locationprofile1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L1.1and1.2");
        Map params=request.getParameterMap();
        String businesscenterid=request.getParameter("bcid");
        String[] dates=(String[]) params.get("date[]");
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
    @RequestMapping(value="/ajaxlocationprofile2.htm",method= RequestMethod.POST)
    public ModelAndView locationprofile2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L1.3and1.4");
        Map params=request.getParameterMap();
        String businesscenterid=request.getParameter("bcid");
        String[] dates=(String[]) params.get("date[]");
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
    @RequestMapping(value="/ajaxlocationprofile3.htm",method= RequestMethod.POST)
    public ModelAndView locationprofile3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L1.5and1.6");
        Map params=request.getParameterMap();
        String businesscenterid=request.getParameter("bcid");
        String[] dates=(String[]) params.get("date[]");
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
    @RequestMapping(value="/ajaxlocationprofile4.htm",method= RequestMethod.POST)
    public ModelAndView locationprofile4(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L1.7and1.8");
        Map params=request.getParameterMap();
        String businesscenterid=request.getParameter("bcid");
        String[] dates=(String[]) params.get("date[]");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,dates[i],businesscenterid);
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
}
