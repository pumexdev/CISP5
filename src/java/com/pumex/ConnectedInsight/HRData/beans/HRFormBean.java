/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.HRData.beans;

/**
 *
 * @author user
 */
public class HRFormBean {
    
    String date;
    Integer location;
    String name;
    Integer emp_type;
    Integer grade;
    Integer tier;
    Integer process;
    Integer subprocess;
    Double total_compensation;
    Double total_fte;
    
    Integer [] businessunit1;
    Double [] fte;
    Double [] compensation1;
    
    String[]ftevalues;
    String costcenter;

    public String getCostcenter() {
        return costcenter;
    }

    public void setCostcenter(String costcenter) {
        this.costcenter = costcenter;
    }        

    public String[] getFtevalues() {
        return ftevalues;
    }

    public void setFtevalues(String[] ftevalues) {
        this.ftevalues = ftevalues;
    }
    
    Integer entryid;
    Integer Empentryid;

    public Double getTotal_fte() {
        return total_fte;
    }

    public void setTotal_fte(Double total_fte) {
        this.total_fte = total_fte;
    }    
    
    public Integer getEmpentryid() {
        return Empentryid;
    }

    public void setEmpentryid(Integer Empentryid) {
        this.Empentryid = Empentryid;
    }    
    
    public Integer getEntryid() {
        return entryid;
    }

    public void setEntryid(Integer entryid) {
        this.entryid = entryid;
    }

    public Integer[] getBusinessunit1() {
        return businessunit1;
    }

    public void setBusinessunit1(Integer[] businessunit1) {
        this.businessunit1 = businessunit1;
    }

    public Double[] getFte() {
        return fte;
    }

    public void setFte(Double[] fte) {
        this.fte = fte;
    }
    
    public Double[] getCompensation1() {
        return compensation1;
    }

    public void setCompensation1(Double [] compensation1) {
        this.compensation1 = compensation1;
    }

    public Double[] getFte1() {
        return fte;
    }

    public void setFte1(Double [] fte1) {
        this.fte = fte1;
    }    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getEmp_type() {
        return emp_type;
    }

    public void setEmp_type(Integer emp_type) {
        this.emp_type = emp_type;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Integer getSubprocess() {
        return subprocess;
    }

    public void setSubprocess(Integer subprocess) {
        this.subprocess = subprocess;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Double getTotal_compensation() {
        return total_compensation;
    }

    public void setTotal_compensation(Double total_compensation) {
        this.total_compensation = total_compensation;
    }
    
    
    
}
