/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.beans;

/**
 *
 * @author Vishnu
 */
public class KPIBean
{
    String fromdate;
    Integer businesscenter;
    String[] centerAverage;
    String[] clientScore;
    Integer operation;

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String[] getCenterAverage() {
        return centerAverage;
    }

    public void setCenterAverage(String[] centerAverage) {
        this.centerAverage = centerAverage;
    }

    public String[] getClientScore() {
        return clientScore;
    }

    public void setClientScore(String[] clientScore) {
        this.clientScore = clientScore;
    }

    public Integer getBusinesscenter() {
        return businesscenter;
    }

    public void setBusinesscenter(Integer businesscenter) {
        this.businesscenter = businesscenter;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }
}
