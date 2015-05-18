/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Tier.beans;

/**
 *
 * @author user
 */
public class TierProcessBean {

    String fromdate;
    String todate;
    Integer status;
    Integer tier;
    Double target;
    String month;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    
    /*Tier process*/
    Integer process;

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }
}
