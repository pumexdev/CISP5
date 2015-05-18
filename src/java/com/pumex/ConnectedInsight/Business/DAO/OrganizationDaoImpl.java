/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import com.pumex.ConnectedInsight.Business.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Vishnu
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDaoInterface
{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    public List searchorg(SearchOrganizationFormBean searchOrganizationFormBean)
    {
        List orglist = new ArrayList();
        orglist = jdbcTemplate.queryForList(SQLSelector.getQuery("search.organizationlist"), searchOrganizationFormBean.getOrgname(), searchOrganizationFormBean.getStatus());
        return orglist;
    }

    public void saveOrganization(OrganizationFormBean organizationFormBean)
    {
        String query = SQLSelector.getQuery("organization.insertorg");
        jdbcTemplate.update(query, new Object[]{organizationFormBean.getName(), organizationFormBean.getShortName(),
                    organizationFormBean.getAddress(), organizationFormBean.getContactPerson(), organizationFormBean.getPhno1(),
                    organizationFormBean.getPhno2(), organizationFormBean.getEmailid(), organizationFormBean.getWebsite(),
                    organizationFormBean.getStatus()});
    }

    public OrganizationFormBean getOrganization(Integer oid, final OrganizationFormBean organizationFormBean) {
        String query = SQLSelector.getQuery("organization.getorg");
        return (OrganizationFormBean) jdbcTemplate.queryForObject(query, new Object[]{oid}, new RowMapper() {
        public Object mapRow(ResultSet rs, int i) throws SQLException
        {
                organizationFormBean.setOrgid(rs.getInt("OrganizationId"));
                organizationFormBean.setShortName(rs.getString("ShortName"));
                organizationFormBean.setAddress(rs.getString("Address"));
                organizationFormBean.setContactPerson(rs.getString("ContactPerson"));
                organizationFormBean.setPhno1(rs.getString("PhoneNumber1"));
                organizationFormBean.setPhno2(rs.getString("PhoneNumber2"));
                organizationFormBean.setEmailid(rs.getString("EMailId"));
                organizationFormBean.setWebsite(rs.getString("WebSite"));
                organizationFormBean.setName(rs.getString("OrganizationName"));
                organizationFormBean.setStatus(rs.getInt("OrganizationStatus"));
                return organizationFormBean;
        }});
    }

    public void updateOrganization(OrganizationFormBean organizationFormBean)
    {
        CommonsMultipartFile file = null;
        file = organizationFormBean.getFile();
        String query = SQLSelector.getQuery("organization.updateorg");
        
        if(file!=null && file.getBytes().length>0)
        {
            jdbcTemplate.update(query, new Object[]{organizationFormBean.getName(), organizationFormBean.getShortName(),
                    organizationFormBean.getAddress(), organizationFormBean.getContactPerson(), organizationFormBean.getPhno1(),
                    organizationFormBean.getPhno2(), organizationFormBean.getEmailid(), organizationFormBean.getWebsite(),
                    file.getBytes(), organizationFormBean.getOrgid()});
        }
        else
        {
            query = SQLSelector.getQuery("organization.updateorg1");
            /*String query1=SQLSelector.getQuery("organization.getlogo");
            byte[] buffer=(byte[])commonDaoInterface.queryForObject(query1, organizationFormBean.getOrgid());*/
            jdbcTemplate.update(query, new Object[]{organizationFormBean.getName(), organizationFormBean.getShortName(),
                    organizationFormBean.getAddress(), organizationFormBean.getContactPerson(), organizationFormBean.getPhno1(),
                    organizationFormBean.getPhno2(), organizationFormBean.getEmailid(), organizationFormBean.getWebsite(),
                    organizationFormBean.getOrgid()});
        }
    }
}
