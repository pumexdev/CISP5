/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Bean;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Vishnu AU
 */
public class DataEntryBean {
    private Integer entry_id;
    private Integer organization_id;
    private Integer business_center_id;
    private String business_center_name;
    private Integer business_id;
    private String business_name;
    private String entry_date;
    private List<EmployeeDetailsBean> employeeDetailsBean;
    private  EmployeeDetailsBean edb;

    public EmployeeDetailsBean getEdb() {
        return edb;
    }

    public void setEdb(EmployeeDetailsBean edb) {
        this.edb = edb;
    }

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

    public List<EmployeeDetailsBean> getEmployeeDetailsBean() {
        return employeeDetailsBean;
    }

    public void setEmployeeDetailsBean(List<EmployeeDetailsBean> employeeDetailsBean) {
        this.employeeDetailsBean = employeeDetailsBean;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    

    public Integer getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(Integer entry_id) {
        this.entry_id = entry_id;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

        
}
