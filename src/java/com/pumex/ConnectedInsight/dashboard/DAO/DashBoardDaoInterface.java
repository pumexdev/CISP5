/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.dashboard.DAO;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu
 */
@Repository
public interface DashBoardDaoInterface 
{
    public String spanOfControll();
    
    public String averageCostPerFTE(String date);
    
    public String ptpAnnualisedInVoice(String date);
    
    public String ptpCostPerInVoice(String date);
    
    public String otcAnnualizedCostPerCashReceipt(String date);
    
    public String otcCostPerCashReceipt(String date);
    
    public String rtrAnnualizedCostPerCashReceipt(String date);
    
    public String rtrCostPerCashReceipt(String date);
    
    public String totalEmployees(String date);
}
