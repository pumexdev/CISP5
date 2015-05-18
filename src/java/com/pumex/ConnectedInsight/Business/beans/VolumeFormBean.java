/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.beans;

/**
 *
 * @author user
 */
public class VolumeFormBean {

    Integer organisation;
    Integer business;
    Integer businessunit;
    Integer businesscenter;
    Integer process;
    Integer subprocess;
    String topcost;
    String fromdate;
    String todate;
    String volume;
    String description;
    Integer status;
    Integer descriptionid;

    public Integer getDescriptionid() {
        return descriptionid;
    }

    public void setDescriptionid(Integer descriptionid) {
        this.descriptionid = descriptionid;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getSubprocess() {
        return subprocess;
    }

    public void setSubprocess(Integer subprocess) {
        this.subprocess = subprocess;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public Integer getBusinesscenter() {
        return businesscenter;
    }

    public void setBusinesscenter(Integer businesscenter) {
        this.businesscenter = businesscenter;
    }

    public Integer getBusinessunit() {
        return businessunit;
    }

    public void setBusinessunit(Integer businessunit) {
        this.businessunit = businessunit;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public Integer getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Integer organisation) {
        this.organisation = organisation;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getTopcost() {
        return topcost;
    }

    public void setTopcost(String topcost) {
        this.topcost = topcost;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
