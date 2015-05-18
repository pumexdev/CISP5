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
public class KPIScoreBean {
    
    private Integer orag_id;
    private Integer busCernter_id;
    private Integer subProcess_id;
    private String subProcess_name;
    private String statement_date;
    private Integer kpi_Id;
    private String kpi_desc;
    
    private List<BUActualBean> bussiness_actualBean_list;

    public Integer getBusCernter_id() {
        return busCernter_id;
    }

    public void setBusCernter_id(Integer busCernter_id) {
        this.busCernter_id = busCernter_id;
    }

    public List<BUActualBean> getBussiness_actualBean_list() {
        return bussiness_actualBean_list;
    }

    public void setBussiness_actualBean_list(List<BUActualBean> bussiness_actualBean_list) {
        this.bussiness_actualBean_list = bussiness_actualBean_list;
    }

    public Integer getKpi_Id() {
        return kpi_Id;
    }

    public void setKpi_Id(Integer kpi_Id) {
        this.kpi_Id = kpi_Id;
    }

    public String getKpi_desc() {
        return kpi_desc;
    }

    public void setKpi_desc(String kpi_desc) {
        this.kpi_desc = kpi_desc;
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

    public Integer getSubProcess_id() {
        return subProcess_id;
    }

    public void setSubProcess_id(Integer subProcess_id) {
        this.subProcess_id = subProcess_id;
    }

    public String getSubProcess_name() {
        return subProcess_name;
    }

    public void setSubProcess_name(String subProcess_name) {
        this.subProcess_name = subProcess_name;
    }
    
    
    
}
