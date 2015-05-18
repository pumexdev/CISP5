/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.actions.beans;

/**
 *
 * @author user
 */
public class ActionsBean {

    String actionType;
    String status;
    Integer actionTypeId;
    Integer OrgId;
    Integer subprocess;
    Integer percentage;
    Integer actionId;
    Integer process;
    String action;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(Integer actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    public Integer getOrgId() {
        return OrgId;
    }

    public void setOrgId(Integer OrgId) {
        this.OrgId = OrgId;
    }

    public Integer getSubprocess() {
        return subprocess;
    }

    public void setSubprocess(Integer subprocess) {
        this.subprocess = subprocess;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
