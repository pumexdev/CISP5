package com.pumex.ConnectedInsight.Business.beans;

/**
 *
 * @author user
 */
public class SubProcessFormBean {
    
    
    String name;
    String shortName;
    Integer status;
    Integer process;
    Integer subprocess;
    Boolean usedforvolume;
    String processname;

    public String getProcessname() {
        return processname;
    }

    public void setProcessname(String processname) {
        this.processname = processname;
    }
    
    public Boolean getUsedforvolume() {
        return usedforvolume;
    }

    public void setUsedforvolume(Boolean usedforvolume) {
        this.usedforvolume = usedforvolume;
    }
        
    public Integer getSubprocess() {
        return subprocess;
    }

    public void setSubprocess(Integer subprocess) {
        this.subprocess = subprocess;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}