/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.GraphReports.ValueBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vishnu AU
 * Created on:02 August 2012
 * Updated On:02 August 2012
 * Updated by: Vishnu AU
 */
public interface ExcelMasterDao {
    
    public Map<String,Integer> getProcessMap(UserBean user);
    
    public Map<String,Integer> getSubProcessMap(Integer processId);
    
    public Map<String,Integer> convertListToMap(List inputList);
    
    public Map<String, Integer> getEmployeeTypeMap();
    
    public Map<String,Integer> getBusinesMap(Integer businesscenter_id,Integer businessId);
    
    public List<ValueBean> getData(String date1,String date2);
    
    public Map<String,String> interChangeKV(Map<String,String> inputMap);
}
