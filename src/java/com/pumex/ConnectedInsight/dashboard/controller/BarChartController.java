/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.dashboard.controller;

import com.pumex.ConnectedInsight.dashboard.DAO.Charts;
import com.pumex.ConnectedInsight.dashboard.beans.BenchMarkDataBean;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.plot.PlotOrientation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vishnu
 */
@Controller
public class BarChartController
{    
    @RequestMapping(value="/getchartReports.htm",method= RequestMethod.GET)
    public ModelAndView getBarCharts(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        Map chartMap=new HashMap();
        List<ChartDataBean> chartList=new ArrayList<ChartDataBean>();
        ChartDataBean chartDataBean=new ChartDataBean();
        chartDataBean.setTitle("Resource per Location");
        chartDataBean.setxAxisLabel("Location");
        chartDataBean.setyAxisLabel("Resources");
        chartDataBean.setPlotOrientation(PlotOrientation.VERTICAL);
        
        ChartDataBean chartDataBean1=new ChartDataBean();
        chartDataBean1.setxValue("Location1");
        chartDataBean1.setyValue(0.5);
        chartList.add(chartDataBean1);
        
        chartDataBean1=new ChartDataBean();
        chartDataBean1.setxValue("Location2");
        chartDataBean1.setyValue(1.0);
        chartList.add(chartDataBean1);
        
        chartDataBean1=new ChartDataBean();
        chartDataBean1.setxValue("Location3");
        chartDataBean1.setyValue(1.5);
        chartList.add(chartDataBean1);
        
        chartDataBean1=new ChartDataBean();
        chartDataBean1.setxValue("Location4");
        chartDataBean1.setyValue(Double.valueOf("2"));
        chartList.add(chartDataBean1);
        
        
        
        chartMap.put("27/May/2012",chartList);
        
        Map map1=new HashMap();
        List<BenchMarkDataBean> benchmarkList=new ArrayList<BenchMarkDataBean>();
        BenchMarkDataBean benchMarkDataBean=new BenchMarkDataBean();
        benchMarkDataBean.setxBenchMarkValue("Location1");
        benchMarkDataBean.setyBenchMarkValue(1.0);
        benchmarkList.add(benchMarkDataBean);
        
        benchMarkDataBean=new BenchMarkDataBean();
        benchMarkDataBean.setxBenchMarkValue("Location2");
        benchMarkDataBean.setyBenchMarkValue(1.0);
        benchmarkList.add(benchMarkDataBean);
        
        benchMarkDataBean=new BenchMarkDataBean();
        benchMarkDataBean.setxBenchMarkValue("Location3");
        benchMarkDataBean.setyBenchMarkValue(1.0);
        benchmarkList.add(benchMarkDataBean);
        
        benchMarkDataBean=new BenchMarkDataBean();
        benchMarkDataBean.setxBenchMarkValue("Location4");
        benchMarkDataBean.setyBenchMarkValue(1.0);
        benchmarkList.add(benchMarkDataBean);
        
        map1.put("b1", benchmarkList);
        
        benchmarkList=new ArrayList<BenchMarkDataBean>();
        benchMarkDataBean=new BenchMarkDataBean();
        benchMarkDataBean.setxBenchMarkValue("Location1");
        benchMarkDataBean.setyBenchMarkValue(2.0);
        benchmarkList.add(benchMarkDataBean);
        
        benchMarkDataBean=new BenchMarkDataBean();
        benchMarkDataBean.setxBenchMarkValue("Location2");
        benchMarkDataBean.setyBenchMarkValue(2.0);
        benchmarkList.add(benchMarkDataBean);
        
        benchMarkDataBean=new BenchMarkDataBean();
        benchMarkDataBean.setxBenchMarkValue("Location3");
        benchMarkDataBean.setyBenchMarkValue(2.0);
        benchmarkList.add(benchMarkDataBean);
        
        benchMarkDataBean=new BenchMarkDataBean();
        benchMarkDataBean.setxBenchMarkValue("Location4");
        benchMarkDataBean.setyBenchMarkValue(2.0);
        benchmarkList.add(benchMarkDataBean);
        
        new Charts().getReports(response, chartMap, map1, chartDataBean);        
        
        return null;
    }
}
