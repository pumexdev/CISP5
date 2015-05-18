/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Bean;

/**
 *
 * @author user
 */
public class BUActualBean {
    
    private Integer business_unit_id;
    private Double actual_value;
    private String bisiness_unit_name;

    public Double getActual_value() {
        return actual_value;
    }

    public void setActual_value(Double actual_value) {
        this.actual_value = actual_value;
    }

    public Integer getBusiness_unit_id() {
        return business_unit_id;
    }

    public void setBusiness_unit_id(Integer business_unit_id) {
        this.business_unit_id = business_unit_id;
    }

    public String getBisiness_unit_name() {
        return bisiness_unit_name;
    }

    public void setBisiness_unit_name(String bisiness_unit_name) {
        this.bisiness_unit_name = bisiness_unit_name;
    }
    
        
}
