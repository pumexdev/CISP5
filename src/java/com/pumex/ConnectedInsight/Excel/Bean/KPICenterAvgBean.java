/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Bean;

import java.util.List;

/**
 *
 * @author user
 */
public class KPICenterAvgBean {

    private Integer orag_id;
    private Integer busCernter_id;
    private String statement_date;
    List<CenterAvgBean> centerAverageBeans;

    public Integer getBusCernter_id() {
        return busCernter_id;
    }

    public void setBusCernter_id(Integer busCernter_id) {
        this.busCernter_id = busCernter_id;
    }

    public List<CenterAvgBean> getCenterAverageBeans() {
        return centerAverageBeans;
    }

    public void setCenterAverageBeans(List<CenterAvgBean> centerAverageBeans) {
        this.centerAverageBeans = centerAverageBeans;
    }

    public Integer getOrag_id() {
        return orag_id;
    }

    public void setOrag_id(Integer orag_id) {
        this.orag_id = orag_id;
    }

    public String getStatement_date() {
        return statement_date;
    }

    public void setStatement_date(String statement_date) {
        this.statement_date = statement_date;
    }
    
    
}
