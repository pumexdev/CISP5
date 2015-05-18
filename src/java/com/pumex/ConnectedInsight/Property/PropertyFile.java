/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Property;

import java.util.ResourceBundle;

/**
 *
 * @author user
 */
public class PropertyFile {
    public static String excel(String key){
        String value="";
        value=ResourceBundle.getBundle("excelconfig").getString(key);
        return value;
    }
    public static Integer excelInt(String key){
        String string_value=ResourceBundle.getBundle("excelconfig").getString(key);
        Integer value=Integer.parseInt(string_value);
        return value;
    }
}
