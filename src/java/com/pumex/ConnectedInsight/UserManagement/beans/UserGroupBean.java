/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.UserManagement.beans;

/**
 *
 * @author user
 */
public class UserGroupBean {

    private String userGroupDesc;
    private Integer userGroupStatus;
    private Integer usergroupid;

    public Integer getUsergroupid() {
        return usergroupid;
    }

    public void setUsergroupid(Integer usergroupid) {
        this.usergroupid = usergroupid;
    }
    

    public String getUserGroupDesc() {
        return userGroupDesc;
    }

    public void setUserGroupDesc(String userGroupDesc) {
        this.userGroupDesc = userGroupDesc;
    }

    public Integer getUserGroupStatus() {
        return userGroupStatus;
    }

    public void setUserGroupStatus(Integer userGroupStatus) {
        this.userGroupStatus = userGroupStatus;
    }
}
