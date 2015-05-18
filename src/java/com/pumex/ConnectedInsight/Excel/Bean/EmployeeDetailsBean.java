/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Bean;

import java.util.List;

/**
 *
 * @author Vishnu AU
 */
public class EmployeeDetailsBean {
    private Integer employee_entry_id;
    private String employee_name;
    private Integer employee_type;
    private String employee_type_s;
    private Integer process_id;
    private String process;
    private Integer sub_process_id;
    private String sub_process;
    private Integer tier;
    private Integer grade;
    private Double total_fte;
    private Double total_cost;
    private List<EmployeeBUBean> employee_bu_bean;
    private  EmployeeBUBean ebub;
    private String xl_row_number;

    public EmployeeBUBean getEbub() {
        return ebub;
    }

    public void setEbub(EmployeeBUBean ebub) {
        this.ebub = ebub;
    }
    public Integer getEmployee_entry_id() {
        return employee_entry_id;
    }

    public void setEmployee_entry_id(Integer employee_entry_id) {
        this.employee_entry_id = employee_entry_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Integer getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(Integer employee_type) {
        this.employee_type = employee_type;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getProcess_id() {
        return process_id;
    }

    public void setProcess_id(Integer process_id) {
        this.process_id = process_id;
    }

    public Integer getSub_process_id() {
        return sub_process_id;
    }

    public void setSub_process_id(Integer sub_process_id) {
        this.sub_process_id = sub_process_id;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Double total_cost) {
        this.total_cost = total_cost;
    }

    public Double getTotal_fte() {
        return total_fte;
    }

    public void setTotal_fte(Double total_fte) {
        this.total_fte = total_fte;
    }

    public List<EmployeeBUBean> getEmployee_bu_bean() {
        return employee_bu_bean;
    }

    public void setEmployee_bu_bean(List<EmployeeBUBean> employee_bu_bean) {
        this.employee_bu_bean = employee_bu_bean;
    }

    public String getEmployee_type_s() {
        return employee_type_s;
    }

    public void setEmployee_type_s(String employee_type_s) {
        this.employee_type_s = employee_type_s;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getSub_process() {
        return sub_process;
    }

    public void setSub_process(String sub_process) {
        this.sub_process = sub_process;
    }

    public String getXl_row_number() {
        return xl_row_number;
    }

    public void setXl_row_number(String xl_row_number) {
        this.xl_row_number = xl_row_number;
    }

    
}
