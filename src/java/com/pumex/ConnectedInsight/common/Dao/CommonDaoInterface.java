/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.common.Dao;

import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mjons
 */
@Repository
public interface CommonDaoInterface
{
    public Map queryForMap(String query,Object... objects);
    
    public Map queryForMap(String query);
    
    public Object queryForObject(String query);
    
    public Object queryForObject(String query,Object...objects);
    
    public Map queryForDates(String query,Object...objects);
    
    public Map queryForDates(String query);
    
    public Map sortMapByValue(Map map);
    
    public Map sortMapByValue(Map map,Integer sortorder);
    
    public Map sortMapByKey(Map map);
    
    public Map sortMapByKey(Map map,Integer sortorder);
    
    public Map sortHashMapByKey(Map map);
    
    public Map<Comparable,List<ChartDataBean>> getChartMap(HttpServletRequest request);
    
    public void clearBean(Object source);
}
