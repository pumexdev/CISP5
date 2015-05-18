package com.pumex.ConnectedInsight.dashboard.beans;

/**
 *
 * @author Vishnu
 */
public class DashBoardBean
{
    String date1;
    String date2;
    Integer businessCenterId;
    Integer processId;
    Integer subProcessId;

    public Integer getBusinessCenterId() {
        return businessCenterId;
    }

    public void setBusinessCenterId(Integer businessCenterId) {
        this.businessCenterId = businessCenterId;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getSubProcessId() {
        return subProcessId;
    }

    public void setSubProcessId(Integer subProcessId) {
        this.subProcessId = subProcessId;
    }
}
