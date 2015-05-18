/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.GraphReports.ValueBean;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.common.bean.MapBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author Vishnu AU
 * Created on:02 August 2012
 * Updated On:02 August 2012
 * Updated by: Vishnu AU
 */
@Repository
public class ExcelMasterDaoImpl implements ExcelMasterDao {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Integer> getProcessMap(UserBean user) {
        String query_for_Map = SQLSelector.getQuery("excel.process.map.select");
        List<MapBean> mapList =getJdbcList(query_for_Map,  new Object[]{user.getOrganizationid(), SQLSelector.getQuery("active.status")});
        Map<String, Integer> map = convertListToMap(mapList);
        return map;
    }

    @Override
    public Map<String, Integer> convertListToMap(List inputList) {
        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        Iterator it = inputList.iterator();
        while (it.hasNext()) {
            MapBean mapBean = (MapBean) it.next();
            resultMap.put(mapBean.getKey(), mapBean.getValue());
        }
        return resultMap;
    }

    @Override
    public Map<String, Integer> getEmployeeTypeMap() {
        String query_for_map = SQLSelector.getQuery("excel.employeetype.select");
        List<MapBean> mapList = getJdbcList(query_for_map, new Object[]{});
        Map<String, Integer> map = convertListToMap(mapList);
        return map;
    }

    public List<MapBean> getJdbcList(String query, Object[] obj) {
        List<MapBean> mapList = jdbcTemplate.query(query, new RowMapper<MapBean>() {

            @Override
            public MapBean mapRow(ResultSet rs, int i) throws SQLException {
                MapBean mapBean = new MapBean();
                mapBean.setKey(rs.getString(2));
                mapBean.setValue(rs.getInt(1));
                return mapBean;
            }
        }, obj);

        return mapList;
    }

    @Override
    public Map<String, Integer> getBusinesMap(Integer businessunitcenter_id,Integer businessId) {
         String query_for_map = SQLSelector.getQuery("excel.businessunit.map.select");
        List<MapBean> mapList = getJdbcList(query_for_map, new Object[]{});
        Map<String, Integer> map = convertListToMap(mapList);
        return map;
    }

    @Override
    public Map<String, Integer> getSubProcessMap(Integer processId) {
        String query_for_Map = SQLSelector.getQuery("excel.subprocess.map.select");
        List<MapBean> mapList =getJdbcList(query_for_Map,  new Object[]{processId, SQLSelector.getQuery("active.status")});
        Map<String, Integer> map = convertListToMap(mapList);
        return map;
   
    }

    @Override
    public List<ValueBean> getData(String date1, String date2) {
        String sql_query="SELECT BusinessCenterName,  SUM( CASE HRDataEntry.EntryDate WHEN 'date1' THEN 1 ELSE 0 END ) AS 'date1',SUM( CASE HRDataEntry.EntryDate WHEN 'date2' THEN 1 ELSE 0 END ) AS 'date2' FROM HRDataEmployeeEntry,BusinessCenter ,HRDataEntry WHERE HRDataEntry.EntryId=HRDataEmployeeEntry.EntryId AND HRDataEntry.BusinessCenterId=BusinessCenter.BusinessCenterId AND HRDataEntry.EntryDate IN ('date1', 'date2') GROUP BY BusinessCenterName";
        sql_query=sql_query.replaceAll("date1", date1);
        sql_query=sql_query.replaceAll("date2", date2);
        String xtitle="Location";
        List<ValueBean> valList=jdbcTemplate.query(sql_query, new RowMapper() {

            @Override
            public ValueBean mapRow(ResultSet rs, int i) throws SQLException {
               ValueBean valBean=new ValueBean();
               valBean.setXvalue(rs.getString(1));
               valBean.setY1value(rs.getDouble(2));
               valBean.setY2value(rs.getDouble(3));
               return valBean;
            }
        }, new Object[]{});
        if(!valList.isEmpty()){
            ValueBean val=(ValueBean)valList.get(0);
            val.setXtitle(xtitle);
            val.setY1title(date1);
            val.setY2title(date2);
            valList.set(0, val);
        }
        return valList;
    }

    @Override
    public Map<String, String> interChangeKV(Map<String, String> inputMap) {
        Map<String,String> outputMap=new HashMap<String, String>();
        Iterator mapIt=inputMap.entrySet().iterator();
        while(mapIt.hasNext()){
            Map.Entry entry=(Map.Entry)mapIt.next();
            outputMap.put(entry.getValue().toString(), entry.getKey().toString());
        }
        return outputMap;
    }
}
