/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Bean;

import java.util.Date;

/**
 *
 * @author user
 */
public class EntryBean {
    
    private Integer organization_id;
    private Integer business_center_id;
    private String business_center_name;
    private Integer business_id;
    private String business_name;
    private String entry_date;
    private EmployeeBean employeeBean;

    public Integer getBusiness_center_id() {
        return business_center_id;
    }

    public void setBusiness_center_id(Integer business_center_id) {
        this.business_center_id = business_center_id;
    }

    public String getBusiness_center_name() {
        return business_center_name;
    }

    public void setBusiness_center_name(String business_center_name) {
        this.business_center_name = business_center_name;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public EmployeeBean getEmployeeBean() {
        return employeeBean;
    }

    public void setEmployeeBean(EmployeeBean employeeBean) {
        this.employeeBean = employeeBean;
    }
    
    
    
}
