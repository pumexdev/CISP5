/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.login.beans;

/**
 *
 * @author user
 */
public class LoginFormBean {

    /*@NotNull(message="UserName cannot be null")
    @NotEmpty(message="UserName cannot be null")*/
    private String username;
    /*@NotNull(message="Password cannot be null")
    @NotEmpty(message="Password cannot be null")*/
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
