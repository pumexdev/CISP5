/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.HRData.beans;

/**
 *
 * @author user
 */
public class HRDataEntryBean 
{
    String empname;
    Integer emptype;
    Integer empgrade;
    Integer emptier;
    String totalcomp;
    Integer process;
    Integer subprocess;
    Integer businessunit;
    Integer subbusinessunit;
    String fte;
    String cost;
    String detailid;
    String empid;

    public String getDetailid() {
        return detailid;
    }

    public void setDetailid(String detailid) {
        this.detailid = detailid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }
    
    

    public Integer getBusinessunit() {
        return businessunit;
    }

    public void setBusinessunit(Integer businessunit) {
        this.businessunit = businessunit;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Integer getEmpgrade() {
        return empgrade;
    }

    public void setEmpgrade(Integer empgrade) {
        this.empgrade = empgrade;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Integer getEmptier() {
        return emptier;
    }

    public void setEmptier(Integer emptier) {
        this.emptier = emptier;
    }

    public Integer getEmptype() {
        return emptype;
    }

    public void setEmptype(Integer emptype) {
        this.emptype = emptype;
    }

    public String getFte() {
        return fte;
    }

    public void setFte(String fte) {
        this.fte = fte;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Integer getSubbusinessunit() {
        return subbusinessunit;
    }

    public void setSubbusinessunit(Integer subbusinessunit) {
        this.subbusinessunit = subbusinessunit;
    }

    public Integer getSubprocess() {
        return subprocess;
    }

    public void setSubprocess(Integer subprocess) {
        this.subprocess = subprocess;
    }

    public String getTotalcomp() {
        return totalcomp;
    }

    public void setTotalcomp(String totalcomp) {
        this.totalcomp = totalcomp;
    }
    
    
    
}
