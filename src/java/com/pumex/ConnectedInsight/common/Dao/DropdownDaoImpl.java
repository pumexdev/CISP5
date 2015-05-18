/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.common.Dao;

import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class DropdownDaoImpl implements DropdownDaoInterface
{
    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Returns Business, Ids corresponding to an organization
     * @param user
     * @return 
     */
    @Override
    public Map getBusinessMap(UserBean userBean)
    {
        String query = SQLSelector.getQuery("businesslistByOrgId");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query, new Object[]{userBean.getOrganizationid()}));
    }

    /**
     * Returns Business Unit Names, Ids corresponding to a Business
     * @param user
     * @param businessId
     * @return 
     */
    @Override
    public Map getBusinessUnitMap(String businessId)
    {
        String query = SQLSelector.getQuery("businessunitlist");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query));
    }

    /**
     *  Returns Business Center Names, Ids corresponding to a Business unit
     * @param user
     * @param businessId
     * @param businessunitId
     * @return 
     */
    @Override
    public Map getBusinessCenterMap(UserBean userBean, String businessId)
    {
        String query = SQLSelector.getQuery("businesscenterlistByOrgId");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query, new Object[]{businessId,userBean.getOrganizationid()}));
    }

    /**
     * Returns process, Ids corresponding to an organization
     * @param user
     * @return 
     */
    @Override
    public Map getProcessMap(UserBean userBean)
    {
        String query = SQLSelector.getQuery("processlistByOrgId");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query, new Object[]{userBean.getOrganizationid()}));
    }
    
    @Override
    public Map getActiveBusinessMap(UserBean userBean)
    {
        String query = SQLSelector.getQuery("activebusinesslistByOrgId");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query, new Object[]{userBean.getOrganizationid(),SQLSelector.getQuery("active.status")}));
    }

    /**
     * Returns Business Unit Names, Ids corresponding to a Business
     * @param user
     * @param businessId
     * @return 
     */
    @Override
    public Map getActiveBusinessUnitMap(String businessId)
    {
        String query = SQLSelector.getQuery("activebusinessunitlistByOrgId");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query, businessId,SQLSelector.getQuery("active.status")));
    }

    /**
     * Returns process, Ids corresponding to an organization
     * @param user
     * @return 
     */
    @Override
    public Map getActiveProcessMap(UserBean userBean)
    {
        String query = SQLSelector.getQuery("activeprocesslistByOrgId");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query, new Object[]{userBean.getOrganizationid(),SQLSelector.getQuery("active.status")}));
    }
    
    @Override
    public Map getStatus()
    {
        String query=SQLSelector.getQuery("status.getstatuses");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query));
    }
    
    @Override
    public Map getBusinessCenterMap(UserBean userBean)
    {
        String query=SQLSelector.getQuery("location.getlocationsbyOrg");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query, userBean.getOrganizationid()));
    }
    
    @Override    
    public Map getemployeeType()
    {
        String query=SQLSelector.getQuery("employeetype.getnames");
        return commonDaoInterface.queryForMap(query);
    }

    public Map getStatementDateMap() {
         String query=SQLSelector.getQuery("statementdate.dropdown");
        return commonDaoInterface.queryForMap(query);
    }

    @Override
    public Map getMetricMap(UserBean user) {
        String query=SQLSelector.getQuery("metricmap");
        return commonDaoInterface.queryForMap(query, new Object[]{user.getOrganizationid()});
    }
}
