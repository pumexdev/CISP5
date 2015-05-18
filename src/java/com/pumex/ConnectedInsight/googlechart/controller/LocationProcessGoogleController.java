package com.pumex.ConnectedInsight.googlechart.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.Charts;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
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

/**Location Dashboard Efficiency by Process Charts Controller
 * @author mjons
 */
@Controller
public class LocationProcessGoogleController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/ajaxlocationprocess1.htm",method= RequestMethod.POST)
    public ModelAndView locationprocess1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L3.1");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,subprocessid,dates[i],businesscenterid);
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
    @RequestMapping(value="/ajaxlocationprocess2.htm",method= RequestMethod.POST)
    public ModelAndView locationprocess2(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L3.2");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,dates[i],subprocessid,dates[i],subprocessid);
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
    @RequestMapping(value="/ajaxlocationprocess3.htm",method= RequestMethod.POST)
    public ModelAndView locationprocess3(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L3.3");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,subprocessid,dates[i],businesscenterid);
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
    @RequestMapping(value="/ajaxlocationprocess4.htm",method= RequestMethod.POST)
    public ModelAndView locationprocess4(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("L3.4");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,dates[i],subprocessid,dates[i],subprocessid);
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
}
