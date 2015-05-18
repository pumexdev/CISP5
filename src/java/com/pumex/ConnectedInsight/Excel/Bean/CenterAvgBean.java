/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Bean;

/**
 *
 * @author user
 */
public class CenterAvgBean {
    
    private Integer subProcess_id;
    private String subProcess_name;
    private Integer kpi_Id;
    private String kpi_desc;
    private Double center_average;

    public Double getCenter_average() {
        return center_average;
    }

    public void setCenter_average(Double center_average) {
        this.center_average = center_average;
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
