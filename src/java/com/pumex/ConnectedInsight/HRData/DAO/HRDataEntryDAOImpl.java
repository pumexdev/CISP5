/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.HRData.DAO;

import com.pumex.ConnectedInsight.Excel.Bean.DataEntryBean;
import com.pumex.ConnectedInsight.HRData.beans.HRDataEntryBean;
import com.pumex.ConnectedInsight.HRData.beans.HRFormBean;
import com.pumex.ConnectedInsight.HRData.beans.StatementBean;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author user
 */
@Repository
public class HRDataEntryDAOImpl implements HRDataEntryDAOInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    public HRFormBean getEmpHRDetails(final HRFormBean hRFormBean, Integer empid) {
        String query = "SELECT * FROM HRDataEntry WHERE EntryId=?";
        return (HRFormBean) jdbcTemplate.queryForObject(query, new Object[]{empid}, new RowMapper() {

            public Object mapRow(ResultSet rs, int i) throws SQLException {
                hRFormBean.setEntryid(rs.getInt("EntryId"));
                hRFormBean.setLocation(rs.getInt("BusinessCenterId"));
                hRFormBean.setDate(rs.getString("EntryDate"));
                return hRFormBean;
            }
        });
    }

    @Override
    public HRFormBean getHRDetails(final HRFormBean hRFormBean, Integer hrid) {
        String query = "SELECT * FROM HRDataEmployeeEntry WHERE EmployeeEntryId=?";
        return (HRFormBean) jdbcTemplate.queryForObject(query, new Object[]{hrid}, new RowMapper() {

            public Object mapRow(ResultSet rs, int i) throws SQLException {
                hRFormBean.setEmpentryid(rs.getInt("EmployeeEntryId"));
                hRFormBean.setName(rs.getString("EmployeeName"));
                hRFormBean.setEmp_type(rs.getInt("EmployeeTypeId"));
                hRFormBean.setGrade(rs.getInt("Grade"));
                hRFormBean.setTier(rs.getInt("Tier"));
                hRFormBean.setProcess(rs.getInt("ProcessId"));
                hRFormBean.setSubprocess(rs.getInt("SubProcessId"));
                hRFormBean.setTotal_fte(rs.getDouble("TotalFTE"));
                hRFormBean.setTotal_compensation(rs.getDouble("TotalCompensation"));
                hRFormBean.setCostcenter(rs.getString("CostCenter"));
                return hRFormBean;
            }
        });
    }

    public List getemployeedetails(HRFormBean hRFormBean, Integer hrid)
    {
        String query = "SELECT * FROM HRDataEmployeeDetail WHERE EmployeeEntryId=?";
        return jdbcTemplate.queryForList(query, new Object[]{hrid});
    }

    public void editHRdata(HRFormBean hRFormBean, UserBean userBean)
    {
         Double totalfte = 0.0;
        Double[] fte = hRFormBean.getFte1();        
        Integer[] clients = hRFormBean.getBusinessunit1();
        String[] ftevalues=hRFormBean.getFtevalues();
        Double[] compensation = new Double[ftevalues.length];
        
        String query = SQLSelector.getQuery("HRDataEmployeeEntry.update");
        jdbcTemplate.update(query, new Object[]{hRFormBean.getName(), hRFormBean.getEmp_type(), hRFormBean.getProcess(), hRFormBean.getSubprocess(), hRFormBean.getGrade(), hRFormBean.getTier(), totalfte, hRFormBean.getTotal_compensation(),hRFormBean.getCostcenter(),hRFormBean.getEmpentryid()});
        
        query=SQLSelector.getQuery("HRDataEmployeeDetail.delete");
        jdbcTemplate.update(query, new Object[]{hRFormBean.getEmpentryid()});
        
        query = SQLSelector.getQuery("HRDataEmployeeDetail.insert");
        for (Integer i = 0; i < clients.length; ++i) 
        {
            jdbcTemplate.update(query, new Object[]{hRFormBean.getEmpentryid(), clients[i], Double.parseDouble(ftevalues[i]), Double.parseDouble(ftevalues[i])*hRFormBean.getTotal_compensation()});
        }       
       
    }

    public void addHRDate(HRFormBean hRFormBean, UserBean userBean) 
    {
        //CostCenter     CostCenter
        Double totalfte = 0.0;
        Double[] fte = hRFormBean.getFte1();        
        Integer[] clients = hRFormBean.getBusinessunit1();
        String[] ftevalues=hRFormBean.getFtevalues();
        Double[] compensation = new Double[ftevalues.length];

        for (Integer i = 0; i < clients.length; ++i) {
            totalfte += Double.parseDouble(ftevalues[i]);
            compensation[i] = hRFormBean.getTotal_compensation() * Double.parseDouble(ftevalues[i]);
        }

        Long count = (Long) commonDaoInterface.queryForObject(SQLSelector.getQuery("hrdataentry.getcount"), new Object[]{userBean.getOrganizationid(), hRFormBean.getLocation(), hRFormBean.getDate()});

        if (count == 0) 
        {
            String query = SQLSelector.getQuery("hrdataentry.insert");
            Integer value = jdbcTemplate.update(query, new Object[]{userBean.getOrganizationid(), hRFormBean.getLocation(), hRFormBean.getDate()});
            if (value.equals(1)) {

                Integer hrdataid = (Integer) commonDaoInterface.queryForObject(SQLSelector.getQuery("hrdataentry.getmaxid"));
                query = SQLSelector.getQuery("hrdataemployeeentry.insert");
                Integer value1 = jdbcTemplate.update(query, new Object[]{hrdataid, hRFormBean.getName(), hRFormBean.getEmp_type(), hRFormBean.getProcess(), hRFormBean.getSubprocess(), hRFormBean.getGrade(), hRFormBean.getTier(), totalfte, hRFormBean.getTotal_compensation(),hRFormBean.getCostcenter()});

                Integer hrempid = (Integer) commonDaoInterface.queryForObject(SQLSelector.getQuery("hrdataemployeeentry.getmaxid"));
                query = SQLSelector.getQuery("HRDataEmployeeDetail.insert");
                for (Integer i = 0; i < clients.length; ++i) {
                    jdbcTemplate.update(query, new Object[]{hrempid, clients[i], Double.parseDouble(ftevalues[i]), compensation[i]});
                }
            }
        }
        else
        {
            Integer hrdataid = (Integer) commonDaoInterface.queryForObject(SQLSelector.getQuery("hrdataentry.getid"), new Object[]{userBean.getOrganizationid(), hRFormBean.getLocation(), hRFormBean.getDate()});
            String query = SQLSelector.getQuery("hrdataemployeeentry.insert");
            Integer value1 = jdbcTemplate.update(query, new Object[]{hrdataid, hRFormBean.getName(), hRFormBean.getEmp_type(), hRFormBean.getProcess(), hRFormBean.getSubprocess(), hRFormBean.getGrade(), hRFormBean.getTier(), totalfte, hRFormBean.getTotal_compensation(),hRFormBean.getCostcenter()});

            Integer hrempid = (Integer) commonDaoInterface.queryForObject(SQLSelector.getQuery("hrdataemployeeentry.getmaxid"));
            query = SQLSelector.getQuery("HRDataEmployeeDetail.insert");
            for (Integer i = 0; i < clients.length; ++i) {
                jdbcTemplate.update(query, new Object[]{hrempid, clients[i], Double.parseDouble(ftevalues[i]), compensation[i]});
            }

        }
    }

    public Map getclientlist() {
        String query = SQLSelector.getQuery("businessunitlist");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query));
    }

    public List searchHRData(DataEntryBean dataEntryBean, UserBean userBean) {
        List hrData = new ArrayList();
        String query = SQLSelector.getQuery("search.hrdatalist");
        hrData = jdbcTemplate.queryForList(query, userBean.getOrganizationid(), dataEntryBean.getBusiness_center_id(), dataEntryBean.getEntry_date());
        return hrData;
    }
    
    public List getHRDataReport(UserBean userBean) {
        List hrData = new ArrayList();
        String query = SQLSelector.getQuery("search.hrdatareport");
        hrData = jdbcTemplate.queryForList(query, userBean.getOrganizationid());
        return hrData;
    }

    public Map getbusiness(String val) {
        String query = "SELECT bus.`BusinessId`, bus.`BusinessName` FROM `Business` bus JOIN `BusinessCenterBusiness` bcb ON bcb.`BusinessId`=bus.`BusinessId` WHERE bcb.`BusinessCenterId`=?";
        return commonDaoInterface.queryForMap(query, val);
    }

    public Map getdatelist() {
        String query = SQLSelector.getQuery("hrdata.getdate");
        return commonDaoInterface.queryForMap(query);
    }

    public Map getsubprocesslist() {
        String query = SQLSelector.getQuery("subprocess.getlist");
        return commonDaoInterface.queryForMap(query);

    }
    public Map getVolumedescription()
    {
        String query=SQLSelector.getQuery("volumedescription.getlist");
        return commonDaoInterface.queryForMap(query);
    }

    public HRDataEntryBean getHrdataentry(String detailid) {
        String query = "SELECT hde.`BusinessUnitId`,hde.`SubBusinessUnitId`,hde.`FTE`,hde.`Cost`,hde.`DetailId`,hde.`EmployeeEntryId`  FROM `HRDataEmployeeDetail` hde WHERE hde.`DetailId`=?";
        HRDataEntryBean hRDataEntryBean = (HRDataEntryBean) jdbcTemplate.queryForObject(query, new Object[]{detailid}, new RowMapper() {

            public Object mapRow(ResultSet rs, int i) throws SQLException {
                HRDataEntryBean hRDataEntryBean = new HRDataEntryBean();


                hRDataEntryBean.setBusinessunit(rs.getInt("BusinessUnitId"));
                hRDataEntryBean.setSubbusinessunit(rs.getInt("SubBusinessUnitId"));
                hRDataEntryBean.setEmpid(rs.getString("EmployeeEntryId"));
                hRDataEntryBean.setFte(rs.getString("FTE"));
                hRDataEntryBean.setCost(rs.getString("Cost"));
                hRDataEntryBean.setDetailid(rs.getString("DetailId"));
                return hRDataEntryBean;
            }
        });
        return hRDataEntryBean;
    }

    public void updateHRdataentry(HRDataEntryBean hRDataEntryBean) {

        String query = "UPDATE `HRDataEmployeeDetail`  SET `BusinessUnitId` = ?  , `FTE` = ? , `Cost` = ? WHERE `DetailId` = ?";
        jdbcTemplate.update(query, new Object[]{hRDataEntryBean.getBusinessunit(), hRDataEntryBean.getFte(), hRDataEntryBean.getCost(), hRDataEntryBean.getDetailid()});
    }

    public List searchBU(String buid) {
        String query = "SELECT bu.`BusinessUnitName`,hde.`FTE`,hde.`Cost`,hde.`DetailId`  FROM `HRDataEmployeeDetail` hde JOIN `BusinessUnit` bu ON bu.`BusinessUnitId`=hde.`BusinessUnitId`  WHERE hde.`EmployeeEntryId`=?";
        return jdbcTemplate.queryForList(query, buid);
    }

    public List searchPerson(String buid) {
        String query = "SELECT hee.`EmployeeName`,hee.`EmployeeType`,hee.`Grade`,td.`Designation`,hee.`TotalCompensation`,pro.`ProcessName`,spro.`SubProcessName`,hee.`EmployeeEntryId`  FROM `HRDataEmployeeEntry` hee JOIN `Process` pro ON pro.`ProcessId`=hee.`ProcessId`   JOIN `SubProcess` spro ON spro.`SubProcessId`=hee.`SubProcessId`  JOIN `TierDesignation` td ON td.`Tier`=hee.`Tier` WHERE hee.`EmployeeEntryId`=?";
        return jdbcTemplate.queryForList(query, buid);
    }

    public HRDataEntryBean getHrdataentryperson(String detailid) {
        String query = "SELECT hee.`EmployeeName`,hee.`EmployeeType`,hee.`Grade`,hee.`Tier`,hee.`TotalCompensation`,hee.`ProcessId`,hee.`SubProcessId`,hee.`EmployeeEntryId` FROM `HRDataEmployeeEntry` hee  WHERE hee.`EmployeeEntryId`=?";
        HRDataEntryBean hRDataEntryBean = (HRDataEntryBean) jdbcTemplate.queryForObject(query, new Object[]{detailid}, new RowMapper() {

            public Object mapRow(ResultSet rs, int i) throws SQLException {
                HRDataEntryBean hRDataEntryBean = new HRDataEntryBean();

                hRDataEntryBean.setEmpname(rs.getString("EmployeeName"));

                hRDataEntryBean.setProcess(rs.getInt("ProcessId"));
                hRDataEntryBean.setSubprocess(rs.getInt("SubProcessId"));
                hRDataEntryBean.setEmpgrade(rs.getInt("Grade"));
                hRDataEntryBean.setEmptier(rs.getInt("Tier"));
                hRDataEntryBean.setTotalcomp(rs.getString("TotalCompensation"));


                hRDataEntryBean.setEmpid(rs.getString("EmployeeEntryId"));
                hRDataEntryBean.setEmptype(rs.getInt("EmployeeType"));

                return hRDataEntryBean;
            }
        });
        return hRDataEntryBean;
    }

    public void updateHRdataentryperson(HRDataEntryBean hRDataEntryBean) {
        String query = "UPDATE `HRDataEmployeeEntry` SET `EmployeeName` = ? , `EmployeeType` = ? , `ProcessId` = ? , `SubProcessId` = ? , `Grade` = ? , `Tier` = ? , `TotalCompensation` = ? WHERE `EmployeeEntryId` = ?";
        jdbcTemplate.update(query, new Object[]{hRDataEntryBean.getEmpname(), hRDataEntryBean.getEmptype(), hRDataEntryBean.getProcess(), hRDataEntryBean.getSubprocess(), hRDataEntryBean.getEmpgrade(), hRDataEntryBean.getEmptier(), hRDataEntryBean.getTotalcomp(), hRDataEntryBean.getEmpid()});
    }

    public Map getEntryDates() {
        String query = SQLSelector.getQuery("statemententry.getDates");
        return commonDaoInterface.queryForDates(query);
    }
    
    public List getAllEntryDates()
    {
        String query = SQLSelector.getQuery("statemententry.getAllDates");
        List statementList=new ArrayList();
        statementList=(List)jdbcTemplate.query(query, new RowMapper()
        {
            public StatementBean mapRow(ResultSet rs, int i) throws SQLException
            {
                StatementBean statementBean=new StatementBean();
                statementBean.setDate(rs.getString(1));
                statementBean.setStatementStatus(rs.getInt(2));
                return statementBean;
            }
        });
        return statementList;
    }

    public Map getCloseDates() {
        String query = SQLSelector.getQuery("statement.getclosedates");
        return commonDaoInterface.queryForDates(query);
    }

    public Map getOpenDates() {
        String query = SQLSelector.getQuery("statement.getopendates");
        return commonDaoInterface.queryForDates(query);
    }

    public void postStatement(StatementBean statementBean) {
        switch (statementBean.getStatementStatus()) {
            case 1:
                Integer status = 1;
                jdbcTemplate.update(SQLSelector.getQuery("statement.insert"), statementBean.getStatementdate(), status);
                break;
            case 2:
                jdbcTemplate.update(SQLSelector.getQuery("statement.close"), statementBean.getOpendate());
                break;
            case 3:
                jdbcTemplate.update(SQLSelector.getQuery("statement.reopen"), statementBean.getClosedate());
                break;
        }
    }

    public Boolean checkDate(String date) {
        String query = SQLSelector.getQuery("statement.checkdate");
        List dateList = new ArrayList();
        dateList = jdbcTemplate.queryForList(query, date);
        Boolean check = false;
        if (dateList != null && dateList.size() > 0) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }
    
    public Boolean deleteEntry(String entryId)
    {
        String query1=SQLSelector.getQuery("hrdata.deleteentries1");
        String query2=SQLSelector.getQuery("hrdata.deleteentries2");
        Integer result2=jdbcTemplate.update(query2,entryId);
        Integer result1=jdbcTemplate.update(query1,entryId);
        if(result1>=1 && result2>=1)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }
}
