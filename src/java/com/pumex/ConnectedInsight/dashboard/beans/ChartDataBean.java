/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.dashboard.beans;

import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author mjons
 */
public class ChartDataBean
{
    String title;
    String xAxisLabel;
    String yAxisLabel;
    Integer height=400;
    Integer width=400;
    Comparable xValue;
    Double yValue;
    Comparable graphParam;
    PlotOrientation plotOrientation;
    
    public ChartDataBean()
    {}
    
    public ChartDataBean(String title,String xLabel,String yLabel,Integer height,Integer width)
    {
        this.title=title;
        this.xAxisLabel=xLabel;
        this.yAxisLabel=yLabel;
        this.plotOrientation=PlotOrientation.VERTICAL;
        this.height=height;
        this.width=width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
    
    public PlotOrientation getPlotOrientation() {
        return plotOrientation;
    }

    public void setPlotOrientation(PlotOrientation plotOrientation) {
        this.plotOrientation = plotOrientation;
    }

    public Comparable getGraphParam() {
        return graphParam;
    }

    public void setGraphParam(Comparable graphParam) {
        this.graphParam = graphParam;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getxAxisLabel() {
        return xAxisLabel;
    }

    public void setxAxisLabel(String xAxisLabel) {
        this.xAxisLabel = xAxisLabel;
    }

    public Comparable getxValue() {
        return xValue;
    }

    public void setxValue(Comparable xValue) {
        this.xValue = xValue;
    }

    public String getyAxisLabel() {
        return yAxisLabel;
    }

    public void setyAxisLabel(String yAxisLabel) {
        this.yAxisLabel = yAxisLabel;
    }

    public Double getyValue() {
        return yValue;
    }

    public void setyValue(Double yValue) {
        this.yValue = yValue;
    }
}
