/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author user
 */
public class RequestWrapper extends HttpServletRequestWrapper
{
    public RequestWrapper(HttpServletRequest servletRequest)
    {
        super(servletRequest);
    }
}
