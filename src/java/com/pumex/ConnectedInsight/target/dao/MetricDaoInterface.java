/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pumex.ConnectedInsight.target.dao;

import com.pumex.ConnectedInsight.login.beans.UserBean;
import com.pumex.ConnectedInsight.target.beans.MetricFormBean;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu AU
 */
@Repository
public interface MetricDaoInterface {
    
    public void saveMetric(MetricFormBean metricFormBean,UserBean user);
    
    public List getMetricList(Integer organizationId);
    
    public void updateMetric(MetricFormBean metricFormBean,UserBean user);
    
    public MetricFormBean getMetric(MetricFormBean metricFormBean,String mid);
}
