/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Property;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public class DateConvertion {
    public static String pattern = "yyyy-MM-dd";
    public static String mmddyyyypattern = "MM-dd-yyyy";
    public static String convertToMysqlFormat(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(date);
        return mysqlDateString;
    }
    public static String convertToMMddYYDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(mmddyyyypattern);
        String mysqlDateString = formatter.format(date);
        return mysqlDateString;
    }
}
