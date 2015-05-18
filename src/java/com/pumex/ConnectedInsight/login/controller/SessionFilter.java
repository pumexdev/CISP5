/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.login.controller;

import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vishnu
 */
public class SessionFilter implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        try
        {
            HttpServletRequest request1 = (HttpServletRequest) request;
            HttpServletResponse response1 = (HttpServletResponse) response;
            String url = request1.getServletPath();

            HttpSession session = request1.getSession(false);
            if( !( url.contains("locationorgeff") || url.contains("locationpro") || url.contains("businessassignment") || url.contains("processeff") || url.contains("subprocessgraph") || url.contains("processpro") || url.contains("processpro") || url.contains("globalpro") || url.contains("globaleff") || url.contains("globalcost") || url.contains("login.htm") || url.contains("404.htm") || url.contains("403.htm") || url.contains("500.htm") || url.contains("/css") || url.contains("/images") || url.contains("/js") || url.contains("subprocesseff") || url.contains("subprocesssub")||url.contains("targetgraph")))
            {
                UserBean userBean=null;
                if (null==session)//null == session && 
                {
                    response1.sendRedirect("login.htm");
                }
                if(session!=null)
                    userBean = (UserBean)session.getAttribute("user");
                if(null==userBean)
                {
                    response1.sendRedirect("login.htm");
                }
                if(session!=null)
                {
                    Enumeration<String> sessionParams = session.getAttributeNames();
                    while(sessionParams.hasMoreElements())
                    {
                        String sessionVariable = sessionParams.nextElement();
                        Object object = session.getAttribute(sessionVariable);
                        if(object==null)
                        {
                            response1.sendRedirect("login.htm");
                            break;
                        }
                    }
                }
            }
            
            chain.doFilter((ServletRequest)request1,(ServletResponse)response1);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy()
    {
        
    }
    
}
