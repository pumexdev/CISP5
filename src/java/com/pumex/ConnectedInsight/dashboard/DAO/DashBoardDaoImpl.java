/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.dashboard.DAO;

import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**Class for getting the data for the Dashboarding landing page for the date selected
 * spanOfControll,averageCostPerFTE etc...
 * @author Vishnu
 */
@Repository
public class DashBoardDaoImpl implements DashBoardDaoInterface
{
    @Autowired
    CommonDaoInterface commonDaoInterface;
    
    @Override
    public String spanOfControll()
    {
        String query1 = SQLSelector.getQuery("landingdashboard.data1");
        String spanofcontrol = String.valueOf(commonDaoInterface.queryForObject(query1));
        return spanofcontrol+"%";
    }
    
    @Override
    public String averageCostPerFTE(String date)
    {
        String query2 = SQLSelector.getQuery("landingdashboard.data2");
        String averageCostPerFTE = String.valueOf(commonDaoInterface.queryForObject(query2,date));
        return averageCostPerFTE;
    }
    
    @Override
    public String ptpAnnualisedInVoice(String date)
    {
        String query = SQLSelector.getQuery("landingdashboard.data3");
        String ptpAnnualisedInVoice = String.valueOf(commonDaoInterface.queryForObject(query,date));
        return ptpAnnualisedInVoice;
    }
    
    @Override
    public String ptpCostPerInVoice(String date)
    {
        String query = SQLSelector.getQuery("landingdashboard.data4");
        String ptpCostPerInVoice = String.valueOf(commonDaoInterface.queryForObject(query,date,date));
        return ptpCostPerInVoice;
    }
    
    @Override
    public String otcAnnualizedCostPerCashReceipt(String date)
    {
        String query = SQLSelector.getQuery("landingdashboard.data5");
        String otcAnnualizedCostPerCashReceipt = String.valueOf(commonDaoInterface.queryForObject(query,date));
        return otcAnnualizedCostPerCashReceipt;
    }
    
    @Override
    public String otcCostPerCashReceipt(String date)
    {
        String query = SQLSelector.getQuery("landingdashboard.data6");
        String otcCostPerCashReceipt = String.valueOf(commonDaoInterface.queryForObject(query,date,date));
        return otcCostPerCashReceipt;
    }
    
    @Override
    public String rtrAnnualizedCostPerCashReceipt(String date)
    {
        String query = SQLSelector.getQuery("landingdashboard.data7");
        String rtrAnnualizedCostPerCashReceipt = String.valueOf(commonDaoInterface.queryForObject(query,date));
        return rtrAnnualizedCostPerCashReceipt;
    }
    
    @Override
    public String rtrCostPerCashReceipt(String date)
    {
        String query = SQLSelector.getQuery("landingdashboard.data8");
        String rtrCostPerCashReceipt = String.valueOf(commonDaoInterface.queryForObject(query,date,date));
        return rtrCostPerCashReceipt;
    }
    
    @Override
    public String totalEmployees(String date)
    {
        String query = SQLSelector.getQuery("landingdashboard.data9");
        String rtrCostPerCashReceipt = String.valueOf(commonDaoInterface.queryForObject(query,date));
        return rtrCostPerCashReceipt;
    }
}
