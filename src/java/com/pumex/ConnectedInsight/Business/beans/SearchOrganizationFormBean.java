/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.beans;

/**
 *
 * @author user
 */
public class SearchOrganizationFormBean {

    Integer status;
    String orgname;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer Status) {
        this.status = Status;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
}
