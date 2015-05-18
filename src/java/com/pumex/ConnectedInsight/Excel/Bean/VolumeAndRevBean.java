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
public class VolumeAndRevBean {
    
    private Integer orag_id;
    private Integer busCernter_id;
    private Integer subProcess_id;
    private String subProcess_name;
    private String statement_date;
    private Integer parameter_type;
    private Integer volume_desc_id;
    private String volume_desc;
    
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

    public Integer getOrag_id() {
        return orag_id;
    }

    public void setOrag_id(Integer orag_id) {
        this.orag_id = orag_id;
    }

    public Integer getParameter_type() {
        return parameter_type;
    }

    public void setParameter_type(Integer parameter_type) {
        this.parameter_type = parameter_type;
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

    public Integer getVolume_desc_id() {
        return volume_desc_id;
    }

    public void setVolume_desc_id(Integer volume_desc_id) {
        this.volume_desc_id = volume_desc_id;
    }

    public String getSubProcess_name() {
        return subProcess_name;
    }

    public void setSubProcess_name(String subProcess_name) {
        this.subProcess_name = subProcess_name;
    }

    public String getVolume_desc() {
        return volume_desc;
    }

    public void setVolume_desc(String volume_desc) {
        this.volume_desc = volume_desc;
    }
    
    
}
