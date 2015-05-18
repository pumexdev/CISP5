package com.pumex.ConnectedInsight.UserManagement.controller;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import com.pumex.ConnectedInsight.login.beans.LoginFormBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import com.pumex.ConnectedInsight.login.beans.LoginFormBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mjons
 */
@Controller
public class TestController
{
    @Autowired
    ChartsDaoInterface chartsDaoInterface;
    
    @RequestMapping(value="/test.htm",method= RequestMethod.GET)
    public ModelAndView test(@ModelAttribute("test")LoginFormBean loginFormBean)
    {
        ModelAndView model=new ModelAndView("test");
        return model;
    }
    
    /*@RequestMapping(value="ajaxglobalcost1.htm",method= RequestMethod.POST)
    public ModelAndView get(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("dashboard.globalresourceefficiency1");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        System.out.println(request.getParameterMap().get("d1"));
        System.out.println(request.getParameterValues("d1"));
        String[] values=request.getParameterValues("d1");
        for(Integer i=0;i<values.length;++i)
        {
            System.out.println("---"+values[i]);
        }
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        chartData=chartsDaoInterface.getChartsData(query,date1);
        chartMap.put(date1, chartData);
        chartData=new ArrayList<ChartDataBean>();
        chartData=chartsDaoInterface.getChartsData(query,date2);
        chartMap.put(date2, chartData);
        model.addObject("Data", chartMap);
        return model;
    }
    
    @RequestMapping(value="ajaxglobalcost1.htm",method= RequestMethod.POST)
    public ModelAndView get(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        String query=SQLSelector.getQuery("dashboard.globalresourceefficiency1");
        String date1=request.getParameter("d1");
        String date2=request.getParameter("d2");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
        Map<Comparable,List<ChartDataBean>> chartMap=new HashMap<Comparable, List<ChartDataBean>>();
        chartData=chartsDaoInterface.getChartsData(query,date1);
        chartMap.put(date1, chartData);
        chartData=new ArrayList<ChartDataBean>();
        chartData=chartsDaoInterface.getChartsData(query,date2);
        chartMap.put(date2, chartData);
        model.addObject("Data", chartMap);
        return model;
    }*/
}
