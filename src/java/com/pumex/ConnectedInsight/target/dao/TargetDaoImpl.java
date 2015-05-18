/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.target.dao;

import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import com.pumex.ConnectedInsight.target.beans.GlobalTotalBean;
import com.pumex.ConnectedInsight.target.beans.TargetEditFormBean;
import com.pumex.ConnectedInsight.target.beans.TargetFormBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu AU
 */
@Repository
public class TargetDaoImpl implements TargetDaoInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    ChartsDaoInterface chartsDaoInterface;

    @Override
    public void saveTarget(TargetFormBean targetBean) {
        try {
            for (int i = 0; i < targetBean.getSubprocessId().length; i++) {
                String query = SQLSelector.getQuery("target.count");
                System.out.println("Saving record" + i + " Traget:" + targetBean.getTarget()[i] + " high:" + targetBean.getHigh()[i] + " medium:" + targetBean.getMedium()[i] + " low:" + targetBean.getLow()[i] + " subprocessid:" + targetBean.getSubprocessId()[i] + " MetricId:" + targetBean.getMetricId()[i]);
                Integer count = jdbcTemplate.queryForInt(query, new Object[]{targetBean.getSubprocessId()[i], targetBean.getMetricId()[i]});
                if (count > 0) {
                    query = SQLSelector.getQuery("target.update.spandmetr");
                    jdbcTemplate.update(query, new Object[]{targetBean.getTarget()[i], targetBean.getHigh()[i], targetBean.getMedium()[i], targetBean.getLow()[i], targetBean.getSubprocessId()[i], targetBean.getMetricId()[i]});
                } else {
                    query = SQLSelector.getQuery("target.insert");
                    jdbcTemplate.update(query, new Object[]{targetBean.getSubprocessId()[i], targetBean.getTarget()[i], targetBean.getHigh()[i], targetBean.getMedium()[i], targetBean.getLow()[i], targetBean.getMetricId()[i]});
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateTarget(TargetEditFormBean targetBean) {
        String query = SQLSelector.getQuery("target.update");
        jdbcTemplate.update(query, new Object[]{targetBean.getSubprocessid(), targetBean.getTarget(), targetBean.getHigh(), targetBean.getMedium(), targetBean.getLow(), targetBean.getMetric(), targetBean.getTid()});
    }

    @Override
    public List getTargetList(String processId) {
        String query = SQLSelector.getQuery("target.list");
        return jdbcTemplate.queryForList(query, new Object[]{processId});
    }

    @Override
    public Map getSubprocessList(String processId) {
        String query = SQLSelector.getQuery("target.subprocesslist");
        return commonDaoInterface.queryForMap(query, new Object[]{processId});
    }

    @Override
    public TargetEditFormBean getTarget(final TargetEditFormBean targetEditFormBean, String tid) {
        String query = SQLSelector.getQuery("target.gettarget");
        return (TargetEditFormBean) jdbcTemplate.queryForObject(query, new RowMapper() {
            @Override
            public TargetEditFormBean mapRow(ResultSet rs, int i) throws SQLException {
                targetEditFormBean.setSubprocessid(rs.getInt("SubprocessId"));
                targetEditFormBean.setMetric(rs.getInt("MetricId"));
                targetEditFormBean.setTarget(rs.getString("Target"));
                targetEditFormBean.setHigh(rs.getString("BenchmarkHigh"));
                targetEditFormBean.setMedium(rs.getString("BenchmarkMedium"));
                targetEditFormBean.setLow(rs.getString("BenchmarkLow"));
                targetEditFormBean.setTid(rs.getInt("TargetBMId"));
                return targetEditFormBean;
            }
        }, new Object[]{tid});
    }

    @Override
    public Map subprocessMap(UserBean user) {
        String query = SQLSelector.getQuery("target.subprocessall");
        return commonDaoInterface.queryForMap(query, new Object[]{});
    }

    @Override
    public List getActionDetails(String subProcessId) {
        String query = SQLSelector.getQuery("actions.getbysubprocessid");
        return jdbcTemplate.queryForList(query, new Object[]{subProcessId});
    }

    @Override
    public TargetEditFormBean getTargetMetric(String subprocessId, String metric) {
        String query = SQLSelector.getQuery("target.gettaarget");
        TargetEditFormBean targetEditFormBean = null;
        try {
            targetEditFormBean = (TargetEditFormBean) jdbcTemplate.queryForObject(query, new Object[]{subprocessId, metric}, new RowMapper() {

                @Override
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    TargetEditFormBean targetEditFormBean = new TargetEditFormBean();
                    targetEditFormBean.setTid(rs.getInt("TargetBMId"));
                    targetEditFormBean.setSubprocessid(rs.getInt("SubprocessId"));
                    targetEditFormBean.setMetric(rs.getInt("MetricId"));
                    targetEditFormBean.setTarget(rs.getString("Target"));
                    targetEditFormBean.setHigh(rs.getString("BenchmarkHigh"));
                    targetEditFormBean.setMedium(rs.getString("BenchmarkMedium"));
                    targetEditFormBean.setLow(rs.getString("BenchmarkLow"));
                    return targetEditFormBean;
                }
            });
        } catch (Exception ex) {
            targetEditFormBean = new TargetEditFormBean();
            targetEditFormBean.setTid(0);
            targetEditFormBean.setSubprocessid(Integer.parseInt(subprocessId));
            targetEditFormBean.setMetric(0);
            targetEditFormBean.setTarget("0");
            targetEditFormBean.setHigh("0");
            targetEditFormBean.setMedium("0");
            targetEditFormBean.setLow("0");
            return targetEditFormBean;
        }
        return targetEditFormBean;
    }

    @Override
    public GlobalTotalBean getGlobalTotalLevel3G1(String subprocessId, String date) {
        String query = SQLSelector.getQuery("T3.1VT");
        GlobalTotalBean globalTotalBean = (GlobalTotalBean) jdbcTemplate.queryForObject(query, new Object[]{date, subprocessId, date, subprocessId, date, subprocessId}, new RowMapper() {

            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                GlobalTotalBean globalTotalBean = new GlobalTotalBean();
                globalTotalBean.setX(rs.getString("volume"));
                globalTotalBean.setY(rs.getString("FTE"));
                globalTotalBean.setXpery(rs.getString("AvgVolPerFTE"));
                return globalTotalBean;
            }
        });
        return globalTotalBean;
    }

    @Override
    public GlobalTotalBean getGlobalTotalLevel3G2(String subprocessId, String date) {
        String query = SQLSelector.getQuery("T3.3VT");
        GlobalTotalBean globalTotalBean = (GlobalTotalBean) jdbcTemplate.queryForObject(query, new Object[]{subprocessId, date, subprocessId, date, subprocessId, date}, new RowMapper() {

            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                GlobalTotalBean globalTotalBean = new GlobalTotalBean();
                globalTotalBean.setX(rs.getString("lcost"));
                globalTotalBean.setY(rs.getString("volume"));
                globalTotalBean.setXpery(rs.getString("Avgpersonnelcost"));
                return globalTotalBean;
            }
        });
        return globalTotalBean;
    }

    @Override
    public GlobalTotalBean getGlobalTotalLevel1G1(String subprocessId, String date) {
        String query = SQLSelector.getQuery("T1.3VT");
        GlobalTotalBean globalTotalBean = (GlobalTotalBean) jdbcTemplate.queryForObject(query, new Object[]{date, subprocessId}, new RowMapper() {

            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                GlobalTotalBean globalTotalBean = new GlobalTotalBean();
                globalTotalBean.setX(rs.getString("TotalComp"));
                globalTotalBean.setY(rs.getString("totalFte"));
                globalTotalBean.setXpery(rs.getString("AvgLabourCost"));
                return globalTotalBean;
            }
        });
        return globalTotalBean;
    }

    @Override
    public void saveTargetAjax(TargetEditFormBean targetEditFormBean) {
        String query = SQLSelector.getQuery("target.count");
        System.out.println("Saving record0 Traget:" + targetEditFormBean.getTarget() + " high:" + targetEditFormBean.getHigh()+ " medium:" + targetEditFormBean.getMedium() + " low:" + targetEditFormBean.getLow() + " subprocessid:" + targetEditFormBean.getSubprocessid() + " MetricId:" + targetEditFormBean.getMetric());
        Integer count = jdbcTemplate.queryForInt(query, new Object[]{targetEditFormBean.getSubprocessid(), targetEditFormBean.getMetric()});
        if (count > 0) {
            query = SQLSelector.getQuery("target.update.spandmetr");
            jdbcTemplate.update(query, new Object[]{targetEditFormBean.getTarget(), targetEditFormBean.getHigh(), targetEditFormBean.getMedium(), targetEditFormBean.getLow(), targetEditFormBean.getSubprocessid(), targetEditFormBean.getMetric()});
        } else {
            query = SQLSelector.getQuery("target.insert");
            jdbcTemplate.update(query, new Object[]{targetEditFormBean.getSubprocessid(), targetEditFormBean.getTarget(), targetEditFormBean.getHigh(), targetEditFormBean.getMedium(), targetEditFormBean.getLow(), targetEditFormBean.getMetric()});
        }
    }

    @Override
    public GlobalTotalBean getGlobalTotalLevel2G4(String subprocessId, String date) {
        String query=SQLSelector.getQuery("T2.4VT");
        List<ChartDataBean> chartData=new ArrayList<ChartDataBean>();
                chartData=chartsDaoInterface.getChartsData(query,subprocessId,date);
                GlobalTotalBean globalTotalBean = new GlobalTotalBean();
                Iterator<ChartDataBean> it=chartData.iterator();
                while(it.hasNext()){
                    ChartDataBean chartDataBean=it.next();
                    if(chartDataBean.getxValue().equals("Tier 1 Manager")){
                        globalTotalBean.setX(chartDataBean.getyValue().toString());
                    }else if(chartDataBean.getxValue().equals("Tier 2 Supervisor")){
                        globalTotalBean.setY(chartDataBean.getyValue().toString());
                    }else if(chartDataBean.getxValue().equals("Tier 3 Analyst")){
                        globalTotalBean.setXpery(chartDataBean.getyValue().toString());
                    }
                }
                if(globalTotalBean.getX()==null){
                    globalTotalBean.setX("0");;
                }
                if(globalTotalBean.getY()==null){
                    globalTotalBean.setY("0");
                }
                if(globalTotalBean.getXpery()==null){
                    globalTotalBean.setXpery("0");
                }
        return globalTotalBean;
    }
}
