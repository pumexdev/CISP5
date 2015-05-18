/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.beans;

/**
 *
 * @author user
 */
public class BenchmarkFormBean {

    Integer business;
    Integer businessunit;
    Integer paramtypecode;
    Integer process;
    Integer subprocess;
    Integer businesscenter;
    String fromdate;
    String todate;
    Double top;
    Double median;
    Double low;    
    Integer status;
    Integer benchmarkid;
    Integer operation;
    
    Integer[] clientsrevenue;
    Double[] clientrevenue;
    Integer[] clientsvolume;
    Double[] clientvolume;
    Integer[] subprocesses;
    Integer[] actualreferenceid;
    String[] clientRevenue;
    String[] clientVolume;
    Integer [] description;

    public Integer[] getDescription() {
        return description;
    }

    public void setDescription(Integer[] description) {
        this.description = description;
    }

    public String[] getClientVolume() {
        return clientVolume;
    }

    public void setClientVolume(String[] clientVolume) {
        this.clientVolume = clientVolume;
    }

    public String[] getClientRevenue() {
        return clientRevenue;
    }

    public void setClientRevenue(String[] clientRevenue) {
        this.clientRevenue = clientRevenue;
    }
    

    public Integer[] getActualreferenceid()
    {
        return actualreferenceid;
    }

    
    public void setActualreferenceid(Integer[] actualreferenceid)
    {
        this.actualreferenceid = actualreferenceid;
    }   
    
    public Integer getOperation()
    {
        return operation;
    }

    public void setOperation(Integer operation)
    {
        this.operation = operation;
    }
    
    

    public Double[] getClientrevenue() {
        return clientrevenue;
    }

    public void setClientrevenue(Double[] clientrevenue) {
        this.clientrevenue = clientrevenue;
    }

    public Integer[] getClientsrevenue() {
        return clientsrevenue;
    }

    public void setClientsrevenue(Integer[] clientsrevenue) {
        this.clientsrevenue = clientsrevenue;
    }

    public Integer[] getClientsvolume() {
        return clientsvolume;
    }

    public void setClientsvolume(Integer[] clientsvolume) {
        this.clientsvolume = clientsvolume;
    }

    public Double[] getClientvolume() {
        return clientvolume;
    }

    public void setClientvolume(Double[] clientvolume) {
        this.clientvolume = clientvolume;
    }

    public Integer[] getSubprocesses() {
        return subprocesses;
    }

    public void setSubprocesses(Integer[] subprocesses) {
        this.subprocesses = subprocesses;
    }

    
    //Actual reference
    Double actual;
    Integer organization;

    public Integer getOrganization() {
        return organization;
    }

    public void setOrganization(Integer organization) {
        this.organization = organization;
    }
    
    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    //Actual reference
    
    public Integer getBenchmarkid() {
        return benchmarkid;
    }

    public void setBenchmarkid(Integer benchmarkid) {
        this.benchmarkid = benchmarkid;
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

    public Integer getParamtypecode() {
        return paramtypecode;
    }

    public void setParamtypecode(Integer paramtypecode) {
        this.paramtypecode = paramtypecode;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        this.median = median;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }
   
    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }   
   
    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Integer getSubprocess() {
        Integer subprocessid=subprocess;
        if(subprocessid==null)
            subprocessid=0;
        return subprocessid;
    }

    public void setSubprocess(Integer subprocess) {
        this.subprocess = subprocess;
    }
}
