/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.googlechart.controller;

import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
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

/**Kpi Graph controller-- for the KPI charts
 * @author user
 */
@Controller
public class KPIGoogleController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @return json String as ModelAndView
     */
    @RequestMapping(method= RequestMethod.POST,value="/kpigraph.htm")
    public ModelAndView getKpis(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("jsonView");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        String subprocessid=request.getParameter("spid");
        String businesscenterid=request.getParameter("bcid");
        String kpiid=request.getParameter("kpiid");
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
                    query = SQLSelector.getQuery("C1.5to1.nbyclient");
                    chartData=chartsDaoInterface.getChartsData(query,dates[i],subprocessid,kpiid,businesscenterid);
                }
                else
                {
                    query = SQLSelector.getQuery("C1.5to1.nbycenter");
                    chartData=chartsDaoInterface.getChartsData(query,dates[i],subprocessid,kpiid);
                }
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        model.addObject("Data", chartMap);
        return model;
    }
}
