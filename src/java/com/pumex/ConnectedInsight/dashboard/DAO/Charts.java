package com.pumex.ConnectedInsight.dashboard.DAO;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoImpl;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.BenchMarkDataBean;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;
/**
 *
 * @author mjons
 */
public class Charts
{
    CommonDaoInterface commonDaoInterface=new CommonDaoImpl();
    
    public void getReports(HttpServletResponse response,Map<Comparable,List<ChartDataBean>>chartData,Map<Comparable,List<BenchMarkDataBean>>benchMarkData,ChartDataBean chartDetails) throws IOException
    {
        Map<Comparable,List<ChartDataBean>>sortedchartData = new TreeMap<Comparable, List<ChartDataBean>> (Collections.reverseOrder());
        sortedchartData=chartData;
        chartData = sortedchartData;
        /*Extracting the BarchartDataset data*/
        chartData=commonDaoInterface.sortMapByKey(chartData, 0);
        final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, new Color(Integer.parseInt("3399FF", 16)), 0.0f, 0.0f, new Color(Integer.parseInt("3399FF", 16)));
        final GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, new Color(Integer.parseInt("00CC00", 16)), 0.0f, 0.0f, new Color(Integer.parseInt("00CC00", 16)));
        final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, new Color(Integer.parseInt("3399FF", 16)), 0.0f, 0.0f, new Color(Integer.parseInt("3399FF", 16)));
        Boolean dataexists=Boolean.FALSE;
        /*Extracting the BarchartDataset data*/
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Set chartDataSet=chartData.entrySet();
        Iterator iterator=chartDataSet.iterator();
        while(iterator.hasNext())
        {
            Map.Entry mapEntry=(Map.Entry)iterator.next();
            Comparable XLabel=(Comparable) mapEntry.getKey();
            List<ChartDataBean> chartList=(List<ChartDataBean>)mapEntry.getValue();
            Iterator it=chartList.iterator();
            while(it.hasNext())
            {
                dataexists=Boolean.TRUE;
                ChartDataBean chartDataBean=(ChartDataBean)it.next();
                dataset.addValue(chartDataBean.getyValue(), XLabel, chartDataBean.getxValue());
            }
        }
        /*Extracting the BarchartDataset data end*/
        
        /*Ploting Barchart*/
        CategoryItemRenderer renderer = new BarRenderer();
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setItemLabelsVisible(true);
        CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset);
        plot.setRenderer(renderer);
        plot.setDomainAxis(new CategoryAxis(chartDetails.getxAxisLabel()));
        plot.setRangeAxis(new NumberAxis(chartDetails.getyAxisLabel()));
        plot.setOrientation(chartDetails.getPlotOrientation());
        /*Ploting Barchart ends*/
        
        /*Extracting the Benchmark Dataset data for Linecharts*/
        if(benchMarkData!=null && benchMarkData.size()>0)
        {
            Set benchMarkDataSet = benchMarkData.entrySet();
            Iterator iterator1 = benchMarkDataSet.iterator();
            Integer i=0;
            while (iterator1.hasNext())
            {
                dataset=new DefaultCategoryDataset();
                Map.Entry mapEntry = (Map.Entry) iterator1.next();
                Comparable XLabel = (Comparable) mapEntry.getKey();
                List<BenchMarkDataBean> benchMarkList = (List<BenchMarkDataBean>) mapEntry.getValue();
                Iterator it = benchMarkList.iterator();
                while (it.hasNext())
                {
                    BenchMarkDataBean benchMarkDataBean = (BenchMarkDataBean) it.next();
                    dataset.addValue(benchMarkDataBean.getyBenchMarkValue(), XLabel, benchMarkDataBean.getxBenchMarkValue());
                }
                renderer=new LineAndShapeRenderer();
                plot.setDataset((i+1),dataset);
                plot.setRenderer((i+1),renderer);
                i++;
            }
        }
        /*Extracting the Benchmark Dataset data for Linecharts*/
        /*ValueAxis rangeAxis2 = new NumberAxis("Benchmark");
        plot.setRangeAxis(1, rangeAxis2);
        plot.mapDatasetToRangeAxis(2, 1);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);*/
        plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        
        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle(chartDetails.getTitle());
        chart.getTitle().setFont(new Font("Calibri",Font.PLAIN,14));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 600));
        BarRenderer br=(BarRenderer)chart.getCategoryPlot().getRenderer();
        br.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        br.setItemLabelFont(new Font("Calibri", Font.PLAIN, 10));
        br.setBaseItemLabelsVisible(true);
        br.setItemLabelsVisible(true);
        br.setItemMargin(0);
        br.setMaximumBarWidth(0.1);
        br.setMinimumBarLength(0.1);
        if(dataexists)
        {
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, chartDetails.getWidth(), chartDetails.getHeight());
            response.getOutputStream().close();
        }
        else
        {
            CategoryPlot plot1 = new CategoryPlot();
            JFreeChart chart1 = new JFreeChart(plot1);
            chart1.setTitle(chartDetails.getTitle());
            chart1.getTitle().setFont(new Font("Calibri",Font.PLAIN,14));
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart1, chartDetails.getWidth(), chartDetails.getHeight());
        }
    }
    
    public void getReports(HttpServletResponse response,Map<Comparable,List<ChartDataBean>>chartData,ChartDataBean chartDetails) throws IOException
    {
        Map<Comparable,List<ChartDataBean>>sortedchartData = new TreeMap<Comparable, List<ChartDataBean>> (Collections.reverseOrder());
        sortedchartData=chartData;
        chartData = sortedchartData;
        /*Extracting the BarchartDataset data*/
        chartData=commonDaoInterface.sortMapByKey(chartData, 0);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Boolean dataexists=Boolean.FALSE;
        Set chartDataSet=chartData.entrySet();
        Iterator iterator=chartDataSet.iterator();
        while(iterator.hasNext())
        {
            Map.Entry mapEntry=(Map.Entry)iterator.next();
            Comparable XLabel=(Comparable) mapEntry.getKey();
            List<ChartDataBean> chartList=(List<ChartDataBean>)mapEntry.getValue();
            Iterator it=chartList.iterator();
            if(!XLabel.equals(""))
            {
                while(it.hasNext())
                {
                    dataexists=Boolean.TRUE;
                    ChartDataBean chartDataBean=(ChartDataBean)it.next();
                    dataset.addValue(chartDataBean.getyValue(), XLabel, chartDataBean.getxValue());
                }
            }
        }
        /*Extracting the BarchartDataset data end*/
        
        /*Ploting Barchart*/
        CategoryItemRenderer renderer = new BarRenderer();
        CategoryPlot plot = new CategoryPlot();
        renderer.setItemLabelsVisible(true);
        
        final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, new Color(Integer.parseInt("3366CC", 16)), 0.0f, 0.0f, new Color(Integer.parseInt("3366CC", 16)));
        final GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, new Color(Integer.parseInt("DC3912", 16)), 0.0f, 0.0f, new Color(Integer.parseInt("DC3912", 16)));
        final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, new Color(Integer.parseInt("3399FF", 16)), 0.0f, 0.0f, new Color(Integer.parseInt("3399FF", 16)));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        
        plot.setDataset(dataset);
        plot.setRenderer(renderer);
        CategoryAxis xAxis=new CategoryAxis(chartDetails.getxAxisLabel());
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(new NumberAxis(chartDetails.getyAxisLabel()));
        plot.setOrientation(chartDetails.getPlotOrientation());
        plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        
        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle(chartDetails.getTitle());
        chart.getTitle().setFont(new Font("Calibri", Font.PLAIN, 14));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 600));
        chartPanel.setBackground(Color.WHITE);
        BarRenderer br=(BarRenderer)chart.getCategoryPlot().getRenderer();
        br.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        br.setItemLabelFont(new Font("Calibri", Font.PLAIN, 10));
        br.setBaseItemLabelsVisible(true);
        br.setItemLabelsVisible(true);
        br.setItemMargin(0);
        br.setMaximumBarWidth(0.1);
        br.setMinimumBarLength(0.1);
        
        if(dataexists)
        {
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, chartDetails.getWidth(), chartDetails.getHeight());
            response.getOutputStream().close();
        }//pixel size
        else
        {
            CategoryPlot plot1 = new CategoryPlot();
            JFreeChart chart1 = new JFreeChart(plot1);
            chart1.setTitle(chartDetails.getTitle());
            chart1.getTitle().setFont(new Font("Calibri",Font.PLAIN,14));
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart1, chartDetails.getWidth(), chartDetails.getHeight());
        }
    }
}
