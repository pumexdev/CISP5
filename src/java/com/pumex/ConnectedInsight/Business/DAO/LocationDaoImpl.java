/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

/**
 *
 * @author Vishnu
 */
import com.pumex.ConnectedInsight.Business.beans.*;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.UserBean;
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

@Repository
public class LocationDaoImpl implements LocationDaoInterface {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    public List searchlocation(LocationFormBean locationFormBean) {
        List locationlist = new ArrayList();
        locationlist = jdbcTemplate.queryForList(SQLSelector.getQuery("search.locationlist"), "%" + locationFormBean.getName() + "%");
        return locationlist;
    }

    public void saveLocation(LocationFormBean locationFormBean, UserBean userBean) {
        String query = SQLSelector.getQuery("location.insertLocation");
        Integer result = jdbcTemplate.update(query, new Object[]{locationFormBean.getCountry(), locationFormBean.getCity(), locationFormBean.getName(), locationFormBean.getShortName(), userBean.getOrganizationid()});

        Integer[] clientList = locationFormBean.getClient();
        if (result.equals(1)) {
            Integer locationId = (Integer) commonDaoInterface.queryForObject(SQLSelector.getQuery("location.getmaxid"));
            for (Integer i = 0; i < clientList.length; ++i) {
                String query1 = SQLSelector.getQuery("businesscenterbusinessunit.insert");
                jdbcTemplate.update(query1, new Object[]{locationId, clientList[i]});
            }
        }
    }

    @Override
    public void updateLocation(LocationFormBean locationFormBean, UserBean userBean) {
        String query = SQLSelector.getQuery("location.updatelocation");
        jdbcTemplate.update(query, new Object[]{locationFormBean.getCountry(), locationFormBean.getCity(), locationFormBean.getName(), locationFormBean.getShortName(), userBean.getOrganizationid(), locationFormBean.getLocationid()});
        
        query = SQLSelector.getQuery("BusinessCenterBusinessUnit.deletebylocation");
        jdbcTemplate.update(query,locationFormBean.getLocationid());
        Integer[] client =locationFormBean.getClient();
        if (client != null && client.length > 0)
        {
            for (Integer i = 0; i < client.length; ++i)
            {
                String query1 = SQLSelector.getQuery("businesscenterbusinessunit.insert");
                jdbcTemplate.update(query1, new Object[]{locationFormBean.getLocationid(), client[i]});
            }
        }
        
    }

    @Override
    public LocationFormBean getLocation(String lid) {
        String query = SQLSelector.getQuery("location.getlocationview");
        LocationFormBean locationFormBean = (LocationFormBean) jdbcTemplate.queryForObject(query, new Object[]{lid}, new RowMapper() {

            public Object mapRow(ResultSet rs, int i) throws SQLException {
                LocationFormBean locationFormBean = new LocationFormBean();
                locationFormBean.setName(rs.getString("BusinessCenterName"));
                locationFormBean.setOrganization(rs.getInt("OrganizationId"));
                locationFormBean.setShortName(rs.getString("ShortName"));
                locationFormBean.setLocationid(rs.getInt("BusinessCenterId"));
                locationFormBean.setCountry(rs.getInt("CountryId"));
                locationFormBean.setCity(rs.getString("City"));

                return locationFormBean;
            }
        });
        
         query = SQLSelector.getQuery("location.getclientlist");
        List clientlist = jdbcTemplate.queryForList(query, lid);
        Iterator iterator = clientlist.iterator();
        Integer[] client = new Integer[clientlist.size()];
        int i = 0;
        while (iterator.hasNext()) {
            Map locations = new HashMap();
            locations = (Map) iterator.next();
            client[i] = (Integer) (Integer) locations.get("BusinessUnitId");
            ++i;
        }
        locationFormBean.setClient(client);
        return locationFormBean;
    }

    public Map getBusinessCenter(Integer organizationid) {
        String query = SQLSelector.getQuery("location.getlocationsbyOrg");
        return commonDaoInterface.queryForMap(query, organizationid);
    }

    public Boolean checkLocationName(String locationName) {
        String query = SQLSelector.getQuery("location.getByName");
        List businessunitList = new ArrayList();
        businessunitList = jdbcTemplate.queryForList(query, locationName);
        if (businessunitList != null && businessunitList.size() > 0) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public Map getBusinessUnitByLocation(Integer location) {
        String query = SQLSelector.getQuery("businessunit.getbylocation");
        return commonDaoInterface.queryForMap(query, new Object[]{location});
    }

    public Map getCenterlist() {
        String query = SQLSelector.getQuery("businessunit.getlist");
        return commonDaoInterface.queryForMap(query);
    }
}
