/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Bean;

/**
 *
 * @author Vishnu AU
 */
public class EmployeeBUBean {
    private Integer detail_id;
    private Integer business_unit_id;
    private String business_unit;
    private Double fte;
    private Double cost;

    public Integer getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(Integer detail_id) {
        this.detail_id = detail_id;
    }

    public String getBusiness_unit() {
        return business_unit;
    }

    public void setBusiness_unit(String business_unit) {
        this.business_unit = business_unit;
    }

    public Integer getBusiness_unit_id() {
        return business_unit_id;
    }

    public void setBusiness_unit_id(Integer business_unit_id) {
        this.business_unit_id = business_unit_id;
    }

       
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getFte() {
        return fte;
    }

    public void setFte(Double fte) {
        this.fte = fte;
    }

}
