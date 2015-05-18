/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Tier.DAO;

import com.pumex.ConnectedInsight.Tier.beans.TierProcessBean;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TierProcessDaoImpl implements TierProcessDaoInterface
{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    public Map getTierList()
    {
        String query = SQLSelector.getQuery("tier.getlist");
        return commonDaoInterface.queryForMap(query);
    }
    public Map getProcessYear()
    {
        String query=SQLSelector.getQuery("tierprocesstarget.getyear");
        return commonDaoInterface.queryForDates(query);
    }
    public Map getYear()
    {
        String query=SQLSelector.getQuery("tiertarget.getyear");
        return commonDaoInterface.queryForDates(query);
    }    
    
    public void addTierTarget(TierProcessBean tierProcessBean)
    {
        String query = SQLSelector.getQuery("tiretarget.add");
        jdbcTemplate.update(query, new Object[]{tierProcessBean.getFromdate(), tierProcessBean.getTodate(), tierProcessBean.getTier(), tierProcessBean.getTarget(), tierProcessBean.getStatus()});
    }

    public void editTierTarget(TierProcessBean tierProcessBean, Integer uid)
    {
        String query = SQLSelector.getQuery("tiertarget.update");
        jdbcTemplate.update(query, tierProcessBean.getFromdate(), tierProcessBean.getTodate(), tierProcessBean.getTier(), tierProcessBean.getTarget(), tierProcessBean.getStatus(), uid);
    }

    public List getTierTarget(String date)
    {
        List tiertargetlist = new ArrayList();
        tiertargetlist = jdbcTemplate.queryForList(SQLSelector.getQuery("tier.searchtarget"),date);
        return tiertargetlist;
    }

    public void addTierProcess(TierProcessBean tierProcessBean)
    {
        String query = SQLSelector.getQuery("tierprocess.addquery");
        jdbcTemplate.update(query, new Object[]{tierProcessBean.getFromdate(), tierProcessBean.getTodate(), tierProcessBean.getTier(), tierProcessBean.getProcess(), tierProcessBean.getTarget(), tierProcessBean.getStatus()});
    }

    public List getTierProcessTarget(TierProcessBean tierProcessBean)
    {
        String date=tierProcessBean.getFromdate();
        date+=tierProcessBean.getMonth();
        List tierprocesslist = new ArrayList();
        tierprocesslist = jdbcTemplate.queryForList(SQLSelector.getQuery("tierprocess.getlist"),date);
        return tierprocesslist;
    }

    public void editTierProcess(TierProcessBean tierProcessBean, Integer uid)
    {
        String query = SQLSelector.getQuery("tierprocess.update");
        jdbcTemplate.update(query, new Object[]{tierProcessBean.getFromdate(), tierProcessBean.getTodate(), tierProcessBean.getTier(), tierProcessBean.getProcess(), tierProcessBean.getTarget(), tierProcessBean.getStatus(), uid});
    }

    @Override
    public TierProcessBean editTierProcessTarget(final TierProcessBean tierProcessBean, Integer uid)
    {
        String query = SQLSelector.getQuery("tierprocess.getdetails");
        return (TierProcessBean) jdbcTemplate.queryForObject(query, new Object[]{uid}, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                tierProcessBean.setFromdate(rs.getString(1));
                tierProcessBean.setTodate(rs.getString(2));
                tierProcessBean.setTier(rs.getInt(3));
                tierProcessBean.setProcess(rs.getInt(4));
                tierProcessBean.setTarget(rs.getDouble(5));
                tierProcessBean.setStatus(rs.getInt(6));
                return tierProcessBean;
            }
        });
    }

    @Override
    public TierProcessBean getTierProcessList(final TierProcessBean tierProcessBean, Integer uid)
    {
        String query = SQLSelector.getQuery("tiertarget.getlist");
        return (TierProcessBean) jdbcTemplate.queryForObject(query, new Object[]{uid}, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                tierProcessBean.setFromdate(rs.getString(1));
                tierProcessBean.setTodate(rs.getString(2));
                tierProcessBean.setTier(rs.getInt(3));
                tierProcessBean.setTarget(rs.getDouble(4));
                tierProcessBean.setStatus(rs.getInt(5));
                return tierProcessBean;
            }
        });
    }
}
