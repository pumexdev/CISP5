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
public class TargetFormBean {
    
    private Integer targetId;
    
    private Integer processId;
    
    private Integer[] metricId;
    
    private Integer[] subprocessId;
    
    private String[] target;
    
    private String[] high;
    
    private String[] medium;
    
    private String[] low;

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer[] getSubprocessId() {
        return subprocessId;
    }

    public void setSubprocessId(Integer[] subprocessId) {
        this.subprocessId = subprocessId;
    }

    public String[] getTarget() {
        return target;
    }

    public void setTarget(String[] target) {
        this.target = target;
    }

    public String[] getHigh() {
        return high;
    }

    public void setHigh(String[] high) {
        this.high = high;
    }

    public String[] getMedium() {
        return medium;
    }

    public void setMedium(String[] medium) {
        this.medium = medium;
    }

    public String[] getLow() {
        return low;
    }

    public void setLow(String[] low) {
        this.low = low;
    }

    public Integer[] getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer[] metricId) {
        this.metricId = metricId;
    }

 
}
