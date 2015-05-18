/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.googlechart.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**Connected Dashboard Charts Controller
 * @author user
 */
@Controller
public class ConnectedGoogleController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @return json String as ModelAndView
     */
    @RequestMapping(method= RequestMethod.POST,value="/ajaxconnectedgraph1.htm")
    public ModelAndView connectedgraph1(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("jsonView");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        String iscenterorbyclient=request.getParameter("status");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        String query = "";
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean> ();
                if(iscenterorbyclient.equals("1"))
                {
                    query = SQLSelector.getQuery("C1.1byclient");
                    chartData=chartsDaoInterface.getChartsData(query,subprocessid,dates[i],businesscenterid);
                }
                else
                {
                    query = SQLSelector.getQuery("C1.1bycenter");
                    chartData=chartsDaoInterface.getChartsData(query,subprocessid,dates[i]);
                }
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @return json String as ModelAndView
     */
    @RequestMapping(method= RequestMethod.POST,value="/ajaxconnectedgraph2.htm")
    public ModelAndView connectedgraph2(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("jsonView");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        String iscenterorbyclient=request.getParameter("status");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        String query = "";
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean> ();
                if(iscenterorbyclient.equals("1"))
                {
                    query = SQLSelector.getQuery("C1.2byclient");
                    chartData=chartsDaoInterface.getChartsData(query,dates[i],subprocessid,businesscenterid);
                }
                else
                {
                    query = SQLSelector.getQuery("C1.2bycenter");
                    chartData=chartsDaoInterface.getChartsData(query,dates[i],subprocessid,dates[i],subprocessid);
                }
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @return json String as ModelAndView
     */
    @RequestMapping(method= RequestMethod.POST,value="/ajaxconnectedgraph3.htm")
    public ModelAndView connectedgraph3(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("jsonView");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        String iscenterorbyclient=request.getParameter("status");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        String query = "";
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean> ();
                if(iscenterorbyclient.equals("1"))
                {
                    query = SQLSelector.getQuery("C1.3byclient");
                    chartData=chartsDaoInterface.getChartsData(query,dates[i],subprocessid);
                }
                else
                {
                    query = SQLSelector.getQuery("C1.3bycenter");
                    chartData=chartsDaoInterface.getChartsData(query,dates[i],subprocessid);
                }
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @return json String as ModelAndView
     */
    @RequestMapping(method= RequestMethod.POST,value="/ajaxconnectedgraph4.htm")
    public ModelAndView connectedgraph4(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("jsonView");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        String iscenterorbyclient=request.getParameter("status");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        String query = "";
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean> ();
                if(iscenterorbyclient.equals("1"))
                {
                    query = SQLSelector.getQuery("C1.4byclient");
                    chartData=chartsDaoInterface.getChartsData(query,dates[i],subprocessid,dates[i],subprocessid);
                }
                else
                {
                    query = SQLSelector.getQuery("C1.4bycenter");
                    chartData=chartsDaoInterface.getChartsData(query,dates[i],subprocessid,dates[i],subprocessid);
                }
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
}
