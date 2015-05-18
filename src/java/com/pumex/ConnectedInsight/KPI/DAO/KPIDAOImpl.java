/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.KPI.DAO;

import com.pumex.ConnectedInsight.Business.DAO.LocationDaoInterface;
import com.pumex.ConnectedInsight.Business.beans.KPIBean;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**Class for the KPI related Operations
 * @author Vishnu
 */
@Repository
public class KPIDAOImpl implements KPIDAOInterface
{
    @Autowired
    DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    LocationDaoInterface locationDaoInterface;
    
    /**Returns the list of kpis
     * @return List
     */
    @Override
    public List getKpiList()
    {
        List kpiList = new ArrayList();
        String query = SQLSelector.getQuery("kpiselect");
        kpiList = jdbcTemplate.queryForList(query);
        return kpiList;
    }
    
    /** Returns a map of kpiscore for the current search
     * @param kPIBean Businesscenter,StatementDate,Kpiid
     * @return Map
     */
    @Override
    public Map getKPIList(KPIBean kPIBean)
    {
        Map kpiMap = new LinkedHashMap();
        List kpiList = new ArrayList();
        String query = SQLSelector.getQuery("kpiselect");
        kpiList = jdbcTemplate.queryForList(query);
        Iterator kpiListIterator = kpiList.iterator();
        
        while(kpiListIterator.hasNext())
        {
            Map map = new LinkedHashMap();
            map = (Map)kpiListIterator.next();
            String kpiId = String.valueOf(map.get("KPIId"));
            String kpiScoreSelect = SQLSelector.getQuery("kpi.getkpiscores");
            Map clientScoresMap = new LinkedHashMap();
            clientScoresMap = commonDaoInterface.queryForMap(kpiScoreSelect, kPIBean.getBusinesscenter(),kPIBean.getFromdate(),kpiId);
            String centerAvgQuery = SQLSelector.getQuery("kpi.centeravg");
            Float centerAvg = 0.0f;
            try
            {
                centerAvg = (Float)commonDaoInterface.queryForObject(centerAvgQuery,kPIBean.getBusinesscenter(),kPIBean.getFromdate(),kpiId);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            clientScoresMap.put("centerAvg", centerAvg);
            kpiMap.put(kpiId, clientScoresMap);
        }
        return kpiMap;
    }
    
    /**Saves the Kpiscore,KpiCenter average
     * @param kPIBean Businesscenter,StatementDate,Kpiid
     * @param userBean Organizationid
     */
    @Override
    public void addKpiData(KPIBean kPIBean,UserBean userBean)
    {
        Map clientMap = new LinkedHashMap();
        String clientSelectQuery=SQLSelector.getQuery("businessunit.getbylocation");
        clientMap = commonDaoInterface.queryForMap(clientSelectQuery,kPIBean.getBusinesscenter());
        
        String[] centerAvg = kPIBean.getCenterAverage();
        String[] clientScore = kPIBean.getClientScore();
        
        Integer centerAvgCount=0;
        Integer clientScoreCount=0;
        
        if(centerAvg!=null && clientScore!=null)
        {
            List kpiList = new ArrayList();
            String query = SQLSelector.getQuery("kpiselect");
            kpiList = jdbcTemplate.queryForList(query);
            Iterator kpiListIterator = kpiList.iterator();
         
            /*Delete Kpi avg score for the center,subprocess,date*/
            String kpideleteQuery = SQLSelector.getQuery("kpi.deleteAvg");
            jdbcTemplate.update(kpideleteQuery,kPIBean.getBusinesscenter(),kPIBean.getFromdate());
            /*Delete Kpi score for the center,subprocess,date*/
            kpideleteQuery = SQLSelector.getQuery("kpi.deleteScore");
            jdbcTemplate.update(kpideleteQuery,kPIBean.getBusinesscenter(),kPIBean.getFromdate());
            
            while(kpiListIterator.hasNext())
            {
                Map map = new LinkedHashMap();
                map = (Map)kpiListIterator.next();
                String kpiId = String.valueOf(map.get("KPIId"));
                String subprocessId = String.valueOf(map.get("SubProcessId"));
                
                String centerAverageInsert = SQLSelector.getQuery("kpicenterAverageInsert");
                jdbcTemplate.update(centerAverageInsert, userBean.getOrganizationid(),kPIBean.getBusinesscenter(),subprocessId,kpiId,kPIBean.getFromdate(),centerAvg[centerAvgCount]);
                centerAvgCount++;

                Iterator clientMapIterator = clientMap.entrySet().iterator();
                Integer clientScoreCount1=0;
                while(clientScoreCount1<clientMap.size() && clientMapIterator.hasNext())
                {
                    Map.Entry entry = (Map.Entry)clientMapIterator.next();
                    
                    String kpiScoreInsert = SQLSelector.getQuery("kpiScoreInsert");
                    jdbcTemplate.update(kpiScoreInsert, userBean.getOrganizationid(),entry.getKey(),kPIBean.getBusinesscenter(),subprocessId,kpiId,kPIBean.getFromdate(),clientScore[clientScoreCount]);
                    clientScoreCount1++;
                    clientScoreCount++;
                }
            }
        }
    }
    
    /**Returns the Map of kpi associated with the Subprocess
     * @param subProcessId Subprocess
     * @return map
     */
    @Override
    public Map getKpis(String subProcessId)
    {
        Map kpiMap = new LinkedHashMap();
        String query=SQLSelector.getQuery("kpibysubprocess");
        kpiMap = commonDaoInterface.queryForMap(query,subProcessId);
        return kpiMap;
    }
}
