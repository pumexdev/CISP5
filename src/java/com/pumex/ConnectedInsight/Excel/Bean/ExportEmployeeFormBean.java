/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Bean;

import java.util.List;

/**
 *
 * @author Vishhhnu
 */
public class ExportEmployeeFormBean {
    
    private String employee_id;
    private String employee_name;
    private String cost_center;
    private String employee_type;
    private String process_name;
    private String sub_process_name;
    private double grade;
    private double tier;
    private double total_fte;
    private double total_compansation;
    private List<ExportEmployeeBUBean> exportEmployeeBUList;

    public List<ExportEmployeeBUBean> getExportEmployeeBUList() {
        return exportEmployeeBUList;
    }

    public void setExportEmployeeBUList(List<ExportEmployeeBUBean> exportEmployeeBUList) {
        this.exportEmployeeBUList = exportEmployeeBUList;
    }

    public String getCost_center() {
        return cost_center;
    }

    public void setCost_center(String cost_center) {
        this.cost_center = cost_center;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(String employee_type) {
        this.employee_type = employee_type;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    public String getSub_process_name() {
        return sub_process_name;
    }

    public void setSub_process_name(String sub_process_name) {
        this.sub_process_name = sub_process_name;
    }

    public double getTier() {
        return tier;
    }

    public void setTier(double tier) {
        this.tier = tier;
    }

    public double getTotal_compansation() {
        return total_compansation;
    }

    public void setTotal_compansation(double total_compansation) {
        this.total_compansation = total_compansation;
    }

    public double getTotal_fte() {
        return total_fte;
    }

    public void setTotal_fte(double total_fte) {
        this.total_fte = total_fte;
    }
    
    
}
