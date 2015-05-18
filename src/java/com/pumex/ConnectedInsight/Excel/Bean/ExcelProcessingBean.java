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
public class ExcelProcessingBean {
    private String bussiness_center;
    private Integer business_center_id;
    private Boolean businessc_flag;
    private String location_cord;
    private String date;
    private Boolean date_flag;
    private String business;
    private Integer business_id;
    private Boolean business_flag;
    private Integer totalEntries;
    private Double totalComp;
    private Integer processedEntry;
    private Double processedCost;
    private String status;
    private Boolean processFlag;
    private String error_message;
    private Boolean date_check;
    private VolumeAndRevBean annual_revenue;
    private List<VolumeAndRevBean> volume_list;
    private KPICenterAvgBean kPICenterAvgBean;
    private List<KPIScoreBean> kPIScoreBeans;

    public Boolean getDate_check() {
        return date_check;
    }

    public void setDate_check(Boolean date_check) {
        this.date_check = date_check;
    }
    public String getBussiness_center() {
        return bussiness_center;
    }

    public void setBussiness_center(String bussiness_center) {
        this.bussiness_center = bussiness_center;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation_cord() {
        return location_cord;
    }

    public void setLocation_cord(String location_cord) {
        this.location_cord = location_cord;
    }

    public Integer getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(Integer totalEntries) {
        this.totalEntries = totalEntries;
    }

    public Double getTotalComp() {
        return totalComp;
    }

    public void setTotalComp(Double totalComp) {
        this.totalComp = totalComp;
    }

    public Integer getProcessedEntry() {
        return processedEntry;
    }

    public void setProcessedEntry(Integer processedEntry) {
        this.processedEntry = processedEntry;
    }

    public Double getProcessedCost() {
        return processedCost;
    }

    public void setProcessedCost(Double processedCost) {
        this.processedCost = processedCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getProcessFlag() {
        return processFlag;
    }

    public void setProcessFlag(Boolean processFlag) {
        this.processFlag = processFlag;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public Integer getBusiness_center_id() {
        return business_center_id;
    }

    public void setBusiness_center_id(Integer business_center_id) {
        this.business_center_id = business_center_id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Boolean getBusiness_flag() {
        return business_flag;
    }

    public void setBusiness_flag(Boolean business_flag) {
        this.business_flag = business_flag;
    }

    public Boolean getBusinessc_flag() {
        return businessc_flag;
    }

    public void setBusinessc_flag(Boolean businessc_flag) {
        this.businessc_flag = businessc_flag;
    }

    public Boolean getDate_flag() {
        return date_flag;
    }

    public void setDate_flag(Boolean date_flag) {
        this.date_flag = date_flag;
    }

    public VolumeAndRevBean getAnnual_revenue() {
        return annual_revenue;
    }

    public void setAnnual_revenue(VolumeAndRevBean annual_revenue) {
        this.annual_revenue = annual_revenue;
    }

    public List<VolumeAndRevBean> getVolume_list() {
        return volume_list;
    }

    public void setVolume_list(List<VolumeAndRevBean> volume_list) {
        this.volume_list = volume_list;
    }

    public KPICenterAvgBean getkPICenterAvgBean() {
        return kPICenterAvgBean;
    }

    public void setkPICenterAvgBean(KPICenterAvgBean kPICenterAvgBean) {
        this.kPICenterAvgBean = kPICenterAvgBean;
    }

    public List<KPIScoreBean> getkPIScoreBeans() {
        return kPIScoreBeans;
    }

    public void setkPIScoreBeans(List<KPIScoreBean> kPIScoreBeans) {
        this.kPIScoreBeans = kPIScoreBeans;
    }
    
    
}
