/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.HRData.DAO;

import com.pumex.ConnectedInsight.Excel.Bean.DataEntryBean;
import com.pumex.ConnectedInsight.HRData.beans.HRDataEntryBean;
import com.pumex.ConnectedInsight.HRData.beans.HRFormBean;
import com.pumex.ConnectedInsight.HRData.beans.StatementBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public interface HRDataEntryDAOInterface {
    
    public HRFormBean getEmpHRDetails(HRFormBean hRFormBean,Integer empid);
    
    public HRFormBean getHRDetails(HRFormBean hRFormBean,Integer hrid);
    
    public List getemployeedetails(HRFormBean RFormBean,Integer hrid);
    
    public Map getclientlist();
    
    public void addHRDate(HRFormBean hRFormBean,UserBean userBean);
    
    public void editHRdata(HRFormBean hRFormBean,UserBean userBean);

    public List searchHRData(DataEntryBean dataEntryBean,UserBean userBean);

    public Map getbusiness(String val);
    
    public Map getdatelist();
    
    public Map getsubprocesslist();

    public HRDataEntryBean getHrdataentry(String detailid);

    public void updateHRdataentry(HRDataEntryBean hRDataEntryBean);

    public List searchBU(String buid);

    public List searchPerson(String buid);

    public HRDataEntryBean getHrdataentryperson(String detailid);

    public void updateHRdataentryperson(HRDataEntryBean hRDataEntryBean);
    
    public Map getEntryDates();
    
    public List getAllEntryDates();
    
    public Map getCloseDates();
    
    public Map getOpenDates();
    
    public void postStatement(StatementBean statementBean);
    
    public Boolean checkDate(String date);
    
    public Boolean deleteEntry(String entryId);
    
    public List getHRDataReport(UserBean userBean);
    
    public Map getVolumedescription();
    
}
