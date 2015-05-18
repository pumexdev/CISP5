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
public class TargetEditFormBean {
    
    private Integer subprocessid;
    
    private Integer metric;
    
    private String target;
    
    private String high;
    
    private String medium;
    
    private String low;
    
    private Integer tid;

    public Integer getSubprocessid() {
        return subprocessid;
    }

    public void setSubprocessid(Integer subprocessid) {
        this.subprocessid = subprocessid;
    }

    public Integer getMetric() {
        return metric;
    }

    public void setMetric(Integer metric) {
        this.metric = metric;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
    
    
}
