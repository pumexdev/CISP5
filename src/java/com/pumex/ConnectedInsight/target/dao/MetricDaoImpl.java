/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pumex.ConnectedInsight.target.dao;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import com.pumex.ConnectedInsight.target.beans.MetricFormBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu AU
 */
@Repository
public class MetricDaoImpl implements MetricDaoInterface{

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void saveMetric(MetricFormBean metricFormBean,UserBean user) {
        String query=SQLSelector.getQuery("metric.add");
        jdbcTemplate.update(query, new Object[]{metricFormBean.getMetric(),metricFormBean.getStatus(),user.getOrganizationid()});
    }

    @Override
    public List getMetricList(Integer organizationId) {
        String query=SQLSelector.getQuery("metric.select.all");
        return jdbcTemplate.queryForList(query,new Object[]{organizationId});
    }

    @Override
    public void updateMetric(MetricFormBean metricFormBean,UserBean user) {
        String query=SQLSelector.getQuery("metric.update");
        jdbcTemplate.update(query, new Object[]{metricFormBean.getMetric(),metricFormBean.getStatus(),metricFormBean.getMetricId(),user.getOrganizationid()});
    }

    @Override
    public MetricFormBean getMetric(final MetricFormBean metricFormBean, String mid) {
        String query=SQLSelector.getQuery("metric.select");
        return (MetricFormBean)jdbcTemplate.queryForObject(query, new RowMapper() {

            @Override
            public MetricFormBean mapRow(ResultSet rs, int i) throws SQLException {
                metricFormBean.setMetricId(rs.getInt("MetricId"));
                metricFormBean.setMetric(rs.getString("Metric"));
                metricFormBean.setStatus(rs.getInt("Status"));
                return metricFormBean;
            }
        }, new Object[]{mid});
    }
    
    
}
