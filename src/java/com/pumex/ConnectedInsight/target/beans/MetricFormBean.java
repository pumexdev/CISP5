/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pumex.ConnectedInsight.target.beans;

/**
 *
 * @author Vishnu AU
 */
public class MetricFormBean {
    
    private Integer metricId;
    
    private String metric;
    
    private Integer status;

    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
