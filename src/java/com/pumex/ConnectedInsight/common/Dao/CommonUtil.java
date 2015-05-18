package com.pumex.ConnectedInsight.common.Dao;

import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vishnu
 */
public class CommonUtil
{
    public static UserBean getUserBean(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        return (UserBean)session.getAttribute("user");
    }
    
    public static void noResultImage(HttpServletResponse response) throws IOException
    {
        InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("/noresults.png");
        byte[] buffer=new byte[is.available()];
        is.read(buffer);
        OutputStream os=response.getOutputStream();
        os.write(buffer);
        os.close();
    }
    
    public void requestFilter(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        UserBean userBean=CommonUtil.getUserBean(request);
        if(userBean==null)
            response.sendRedirect(response.encodeRedirectURL("login.htm"));
    }
    
    public String[] replaceUrl(String[] urlList)
    {
        if(urlList!=null && urlList.length>0)
        {
            String[] newUrlList=new String[urlList.length];
            for(Integer i=0;i<urlList.length;++i)
            {
                newUrlList[i]=SQLSelector.getMessage("baseurl")+urlList[i];
            }
            return urlList;
        }
        else
            return null;
    }
}
