package com.pumex.ConnectedInsight.common.Dao;

import java.util.ResourceBundle;

/**
 *
 * @author Vishnu
 */
public class SQLSelector
{
    public static String getQuery(String queryName)
    {
        String query="";
        try
        {
            ResourceBundle  queries= ResourceBundle.getBundle("/sqlqueries");
            query=queries.getString(queryName);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return query;
    }
    
    public static String getMessage(String messageName)
    {
        String message="";
        try
        {
            ResourceBundle  messages= ResourceBundle.getBundle("/message");
            message=messages.getString(messageName);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return message;
    }
}
