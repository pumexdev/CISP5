/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.HRData.beans;

/**
 *
 * @author user
 */
public class TestBean {
    Integer client;
    Double fte,compensation;

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Double getCompensation() {
        return compensation;
    }

    public void setCompensation(Double compensation) {
        this.compensation = compensation;
    }

    public Double getFte() {
        return fte;
    }

    public void setFte(Double fte) {
        this.fte = fte;
    }
}
