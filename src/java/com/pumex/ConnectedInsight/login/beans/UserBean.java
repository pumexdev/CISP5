/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.login.beans;

/**
 *
 * @author user
 */
public class UserBean
{
    String username;
    String password;
    Integer userid;
    Integer organizationid;
    Integer processid;
    Integer businesscenterid;
    Integer userstatus;
    Integer userlevel;
    Integer writepermission;

    public Integer getBusinesscenterid() {
        return businesscenterid;
    }

    public void setBusinesscenterid(Integer businesscenterid) {
        this.businesscenterid = businesscenterid;
    }
    
    public Integer getOrganizationid() {
        return organizationid;
    }

    public void setOrganizationid(Integer organizationid) {
        this.organizationid = organizationid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getProcessid() {
        return processid;
    }

    public void setProcessid(Integer processid) {
        this.processid = processid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(Integer userlevel) {
        this.userlevel = userlevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public Integer getWritepermission() {
        return writepermission;
    }

    public void setWritepermission(Integer writepermission) {
        this.writepermission = writepermission;
    }
        
}
