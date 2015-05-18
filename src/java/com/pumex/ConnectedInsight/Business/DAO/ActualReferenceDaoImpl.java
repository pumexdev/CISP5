/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import com.pumex.ConnectedInsight.Business.beans.BenchmarkFormBean;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**Class for the Volume/Revenue add/edit
 * @author user
 */
@Repository
public class ActualReferenceDaoImpl implements ActualReferenceDaoInterface
{
    @Autowired
    DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    /**Save/Update the volume/revenue
     * @param benchmarkFormBean Containing the values needed for the volume/revenue saving
     * @param userBean Containing the organization id
     */
    @Override
    public void addActualReference(BenchmarkFormBean benchmarkFormBean, UserBean userBean)
    {
        String deleteQuery=SQLSelector.getQuery("actualreference.delete");
        jdbcTemplate.update(deleteQuery, benchmarkFormBean.getBusinesscenter(),benchmarkFormBean.getFromdate(),benchmarkFormBean.getParamtypecode());
        Map clientMap = new LinkedHashMap();
        String clientSelectQuery=SQLSelector.getQuery("businessunit.getbylocation");
        clientMap = commonDaoInterface.queryForMap(clientSelectQuery,benchmarkFormBean.getBusinesscenter());
        String query = SQLSelector.getQuery("actualreference.insert");
        
        switch (benchmarkFormBean.getParamtypecode())
        {
            case 1://Revenue
                String[] clientRevenue = benchmarkFormBean.getClientRevenue();
                Set clientSet = clientMap.entrySet();
                Iterator clientIterator = clientSet.iterator();
                Integer client=0;
                while(clientIterator.hasNext())
                {
                    Map.Entry entry = (Map.Entry)clientIterator.next();
                    jdbcTemplate.update(query, new Object[]{userBean.getOrganizationid(), entry.getKey(), benchmarkFormBean.getBusinesscenter(), benchmarkFormBean.getSubprocess(), benchmarkFormBean.getFromdate(), benchmarkFormBean.getParamtypecode(), clientRevenue[client].replaceAll(",", "") , 1,0});
                    client++;
                }
                break;
            case 2://Volume
                String[] clientVolume = benchmarkFormBean.getClientVolume();
                Integer [] volume_description=benchmarkFormBean.getDescription();
                /*subprocess map query change*/
                Map subProcessMap = commonDaoInterface.queryForMap(SQLSelector.getQuery("subprocess.getbyvolume"));
                subProcessMap = new LinkedHashMap();
                subProcessMap = commonDaoInterface.queryForMap(SQLSelector.getQuery("actualref.subprocesses"));
                Set subProcessSet = subProcessMap.entrySet();
                Iterator subprocessIterator = subProcessSet.iterator();
                Integer clientVol=0;
                Integer subprocessCount=0;
                while(subprocessIterator.hasNext())
                {
                    Map.Entry entry = (Map.Entry)subprocessIterator.next();
                    Set clientSet1 = clientMap.entrySet();
                    Iterator clientIterator1 = clientSet1.iterator();
                    while(clientIterator1.hasNext())
                    {
                        String volumeInsertQuery = SQLSelector.getQuery("actualreference.insert");
                        Map.Entry entry1 = (Map.Entry)clientIterator1.next();
                        jdbcTemplate.update(volumeInsertQuery,userBean.getOrganizationid(), entry1.getKey(), benchmarkFormBean.getBusinesscenter(), entry.getKey(), benchmarkFormBean.getFromdate(), benchmarkFormBean.getParamtypecode(), clientVolume[clientVol].replaceAll(",", ""), 1 ,volume_description[subprocessCount]);//
                        clientVol++;
                    }
                    subprocessCount++;
                }
        }
    }

    /**Search for the volume/revenue to check whether its an add/edit operation
     * @param benchmarkFormBean
     * @return BenchmarkFormBean
     */
    @Override
    public BenchmarkFormBean getSearchresult(BenchmarkFormBean benchmarkFormBean)
    {
        String query = SQLSelector.getQuery("actualreference.searchcount");
        Long count = (Long) commonDaoInterface.queryForObject(query, benchmarkFormBean.getBusinesscenter(), benchmarkFormBean.getFromdate(),benchmarkFormBean.getParamtypecode());
        if (count == 0)
            benchmarkFormBean.setOperation(3);
        else
            benchmarkFormBean.setOperation(2);
        return benchmarkFormBean;
    }

    @Override
    public BenchmarkFormBean getActualReferencerDetails(final BenchmarkFormBean benchmarkFormBean, Integer parameterId)
    {
        String query = SQLSelector.getQuery("actualreference.getdata");
        return (BenchmarkFormBean) jdbcTemplate.queryForObject(query, new Object[]{parameterId}, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                benchmarkFormBean.setOrganization(rs.getInt("OrganizationId"));
                benchmarkFormBean.setBusinessunit(rs.getInt("BusinessUnitId"));
                benchmarkFormBean.setBusinesscenter(rs.getInt("BusinessCenterId"));
                benchmarkFormBean.setProcess(rs.getInt("ProcessId"));
                benchmarkFormBean.setSubprocess(rs.getInt("SubProcessId"));
                benchmarkFormBean.setFromdate(rs.getString("FromDate"));
                benchmarkFormBean.setTodate(rs.getString("ToDate"));
                benchmarkFormBean.setParamtypecode(rs.getInt("ParamTypeCode"));
                benchmarkFormBean.setActual(rs.getDouble("ActualValue"));
                benchmarkFormBean.setStatus(rs.getInt("Status"));
                return benchmarkFormBean;
            }
        });
    }

    @Override
    public Map getYear()
    {
        String query = SQLSelector.getQuery("actualreference.getyear");
        return commonDaoInterface.queryForDates(query);
    }

    @Override
    public Map getdatelist()
    {
        String query = SQLSelector.getQuery("hrdata.getdate");
        return commonDaoInterface.queryForMap(query);
    }

    @Override
    public Map getActalValueList(BenchmarkFormBean benchmarkFormBean)
    {
        String query = SQLSelector.getQuery("actual.getlist");
        Map<Integer,String> actualList=new TreeMap<Integer, String>();
        actualList = commonDaoInterface.queryForMap(query, benchmarkFormBean.getBusinesscenter(), benchmarkFormBean.getFromdate(),benchmarkFormBean.getParamtypecode());
        return actualList;
    }
    
    /**Returns a Map of Revenue list associated with the search
     * @param benchmarkFormBean BusinessCenter,StatementDate,Paramtype
     * @return Map
     */
    @Override
    public Map getRevenueList(BenchmarkFormBean benchmarkFormBean)
    {
        String query = SQLSelector.getQuery("actual.getrevenuelist");
        Map<Integer,String> actualList=new LinkedHashMap<Integer, String>();
        actualList = commonDaoInterface.queryForMap(query, benchmarkFormBean.getBusinesscenter(), benchmarkFormBean.getFromdate(),benchmarkFormBean.getParamtypecode());
        return actualList;
    }
    
    /**Returns a Map of Volume list associated with the search
     * @param benchmarkFormBean BusinessCenter,StatementDate,Paramtype
     * @return Map
     */
    @Override
    public Map getVolumeList(BenchmarkFormBean benchmarkFormBean)
    {
        String query = SQLSelector.getQuery("actual.getvolumelist");
        Map volumeList=new LinkedHashMap();
        Map subProcessMap = commonDaoInterface.queryForMap(SQLSelector.getQuery("subprocess.getbyvolume"));
        subProcessMap = new LinkedHashMap();
        subProcessMap = commonDaoInterface.queryForMap(SQLSelector.getQuery("actualref.subprocesses"));
        Set subProcessSet = subProcessMap.entrySet();
        Iterator subProcessIterator = subProcessSet.iterator();
        Integer client=0;
        while(subProcessIterator.hasNext())
        {
            Map.Entry entry = (Map.Entry)subProcessIterator.next();
            Map clientVolumeMap = new LinkedHashMap();
            clientVolumeMap = commonDaoInterface.queryForMap(query, benchmarkFormBean.getBusinesscenter(),benchmarkFormBean.getFromdate(),benchmarkFormBean.getParamtypecode(),entry.getKey());
            String volDescQuery = SQLSelector.getQuery("getVolDescquery");
            Integer volumeDescId = (Integer)commonDaoInterface.queryForObject(volDescQuery,entry.getKey());
            clientVolumeMap.put("VolDescId", volumeDescId);
            volumeList.put(entry.getKey(), clientVolumeMap);
        }
        return volumeList;
    }
    
    @Override
    public List setVolumeDescription(String center,String date,String parameter,String status)
    {
        String query=SQLSelector.getQuery("actual.getdescription");
        return jdbcTemplate.queryForList(query,new Object[]{center,date,parameter});
    }
}
