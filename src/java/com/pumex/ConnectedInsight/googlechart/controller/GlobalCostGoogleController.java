package com.pumex.ConnectedInsight.googlechart.controller;

import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
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

/**Global Dashboard Resources & Cost  Charts Controller
 * @author mjons
 */
@Controller
public class GlobalCostGoogleController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    Charts charts=new Charts();
    
    /**Method to return the json string with the data needed for plotting the graphs
     * @param request
     * @param response
     * @return json String as ModelAndView
     */
    @RequestMapping(value="/ajaxglobalcost1.htm",method= RequestMethod.POST)
    public ModelAndView globalcost1(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("G3.1and3.2");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,dates[i]);
                if(!dates[i].equals("0") && chartData.size()>0)
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
    @RequestMapping(value="/ajaxglobalcost2.htm",method= RequestMethod.POST)
    public ModelAndView getGlobalCost2(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("G3.3");
        Map params=request.getParameterMap();
        String[] dates=(String[]) params.get("date[]");
        Map<Comparable,List<ChartDataBean>> chartMap=new LinkedHashMap<Comparable, List<ChartDataBean>>();
        if(dates!=null)
        {
            for(Integer i=0;i<dates.length;++i)
            {
                List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,dates[i]);
                if(!dates[i].equals("0"))
                    chartMap.put(dates[i], chartData);
            }
        }
        /*chartMap = new LinkedHashMap<Comparable, List<ChartDataBean>>();
        chartMap = commonDaoInterface.getChartMap(request);*/
        
        model.addObject("Data", chartMap);
        return model;
    }
}
