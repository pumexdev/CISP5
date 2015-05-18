package com.pumex.ConnectedInsight.Business.beans;

/**
 *
 * @author user
 */
public class ProcessFormBean
{
    String name;
    String shortName;
    Integer organization;
    Integer status;
    Integer process;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrganization() {
        return organization;
    }

    public void setOrganization(Integer organization) {
        this.organization = organization;
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
