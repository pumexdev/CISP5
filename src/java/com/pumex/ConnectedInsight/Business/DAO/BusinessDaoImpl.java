/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import com.pumex.ConnectedInsight.Business.beans.*;

import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class BusinessDaoImpl implements BusinessInterface
{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    public void saveBusinessUnit(BusinessFormBean businessFormBean)
    {
        String query = SQLSelector.getQuery("businessunit.insertquery");
        Integer result = jdbcTemplate.update(query, new Object[]{businessFormBean.getName().trim(), businessFormBean.getShortName(), businessFormBean.getStatus()});
        if (result.equals(1))
        {
            Integer[] location = businessFormBean.getLocations();
            Integer businessunitId = (Integer) commonDaoInterface.queryForObject(SQLSelector.getQuery("businessunit.getmaxid"));
            if (location != null && location.length > 0)
            {
                for (Integer i = 0; i < location.length; ++i)
                {
                    String query1 = SQLSelector.getQuery("businesscenterbusinessunit.insert");
                    jdbcTemplate.update(query1, new Object[]{location[i], businessunitId});
                }
            }
        }
    }

    public void editBusinessUnit(BusinessFormBean businessFormBean)
    {
        String query = SQLSelector.getQuery("businessunit.editquery");
        jdbcTemplate.update(query, new Object[]{businessFormBean.getName(), businessFormBean.getShortName().trim(), businessFormBean.getStatus(), businessFormBean.getBusinessunit()});
        query = SQLSelector.getQuery("BusinessCenterBusinessUnit.delete");
        jdbcTemplate.update(query, businessFormBean.getBusinessunit());
        Integer[] location = businessFormBean.getLocations();
        if (location != null && location.length > 0)
        {
            for (Integer i = 0; i < location.length; ++i)
            {
                String query1 = SQLSelector.getQuery("businesscenterbusinessunit.insert");
                jdbcTemplate.update(query1, new Object[]{location[i], businessFormBean.getBusinessunit()});
            }
        }
    }

    public void saveBusiness(BusinessFormBean businessFormBean)
    {
        String query = SQLSelector.getQuery("business.insertquery");
        jdbcTemplate.update(query, new Object[]{businessFormBean.getName(), businessFormBean.getShortName(), businessFormBean.getOrganization(), businessFormBean.getStatus()});
    }

    public void editBusiness(BusinessFormBean businessFormBean)
    {
        String query = SQLSelector.getQuery("business.editquery");
        jdbcTemplate.update(query, new Object[]{businessFormBean.getName(), businessFormBean.getShortName(), businessFormBean.getOrganization(), businessFormBean.getStatus(), businessFormBean.getBusiness()});
    }

    public List searchBusiness(BusinessFormBean businessFormBean)
    {
        List businesslist = new ArrayList();
        businesslist = jdbcTemplate.queryForList(SQLSelector.getQuery("search.businessdata"), businessFormBean.getBusiness());
        return businesslist;
    }

    @Override
    public BusinessFormBean getBusinessData(Integer businessId, final BusinessFormBean businessFormBean)
    {
        String query = SQLSelector.getQuery("business.getdetails");
        return (BusinessFormBean) jdbcTemplate.queryForObject(query, new Object[]{businessId}, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                businessFormBean.setName(rs.getString(2));
                businessFormBean.setShortName(rs.getString(3));
                businessFormBean.setOrganization(rs.getInt(4));
                businessFormBean.setStatus(rs.getInt(5));
                return businessFormBean;
            }
        });
    }

    @Override
    public BusinessFormBean getBusinessUnitData(final BusinessFormBean businessFormBean, Integer buid)
    {
        String query = SQLSelector.getQuery("businessunit.getquery");
        return (BusinessFormBean) jdbcTemplate.queryForObject(query, new Object[]{buid}, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                businessFormBean.setBusinessunit(rs.getInt(1));
                businessFormBean.setName(rs.getString(2));
                businessFormBean.setShortName(rs.getString(3));
                businessFormBean.setStatus(rs.getInt(4));
                String query = SQLSelector.getQuery("businessunitcenter.getlist");
                List locationlist = new ArrayList();
                locationlist = jdbcTemplate.queryForList(query, rs.getInt(1));
                Iterator iterator = locationlist.iterator();
                Integer j = 0;
                Integer[] locationarray = null;
                if (locationlist != null && locationlist.size() > 0)
                {
                    locationarray = new Integer[locationlist.size()];
                    while (iterator.hasNext())
                    {
                        Map locations = new HashMap();
                        locations = (Map) iterator.next();
                        locationarray[j] = (Integer) locations.get("BusinessCenterId");
                        j++;
                    }
                    businessFormBean.setLocations(locationarray);
                }
                return businessFormBean;
            }
        });
    }

    public Map getLocationLIst()
    {
        String query = SQLSelector.getQuery("location.getlocations");
        return commonDaoInterface.queryForMap(query);
    }

    @Override
    public Map getBusiness()
    {
        String query = SQLSelector.getQuery("location.getbusiness");
        return commonDaoInterface.queryForMap(query);
    }

    public Map getBusinessUnitsNames()
    {
        String query = SQLSelector.getQuery("businessunit.getlist");
        return commonDaoInterface.queryForMap(query);
    }

    public Map getsubProcessMap(Integer val)
    {
        String query = "SELECT `SubProcessId`,`SubProcessName` FROM `SubProcess` WHERE `ProcessId`=";
        return commonDaoInterface.queryForMap(query + val);
    }

    public Map getsubBusinessMap(Integer val)
    {
        String query = "SELECT `SubBusinessUnitId`,`SubBusinessUnitName` FROM `SubBusinessUnit` WHERE `BusinessUnitId`=";
        return commonDaoInterface.queryForMap(query + val);
    }

    public Map getBusinessUnits(Integer businessid)
    {
        String query = SQLSelector.getQuery("businessunit.getnames");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query, businessid));
    }

    public Map getLocations(Integer businessid)
    {
        String query = SQLSelector.getQuery("businessunit.getlocationnames");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query, businessid));
    }

    public Boolean checkBusinessName(String businessName)
    {
        String query = SQLSelector.getQuery("business.getbusinessByName");
        List businessList = new ArrayList();
        businessList = jdbcTemplate.queryForList(query, businessName);
        if (businessList != null && businessList.size() > 0)
            return Boolean.FALSE;
        else
            return Boolean.TRUE;
    }

    public Boolean checkBusinessUnitName(String businessUnitName)
    {
        String query = SQLSelector.getQuery("businessunit.getbusinessunitByName");
        List businessunitList = new ArrayList();
        businessunitList = jdbcTemplate.queryForList(query, businessUnitName);
        if (businessunitList != null && businessunitList.size() > 0)
            return Boolean.FALSE;
        else
            return Boolean.TRUE;
    }
    
    public List getclientlist(BusinessFormBean businessFormBean)
    {
        String query=SQLSelector.getQuery("businessunit.getlistbylocation");
        return  jdbcTemplate.queryForList(query,new Object[]{businessFormBean.getLocation()});
    } 
    public Map getSubProcessList()
    {
        return commonDaoInterface.queryForMap(SQLSelector.getQuery("subprocess.getlist"));
    }
}
