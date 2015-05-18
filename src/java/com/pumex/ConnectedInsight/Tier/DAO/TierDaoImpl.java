package com.pumex.ConnectedInsight.Tier.DAO;

import com.pumex.ConnectedInsight.Tier.beans.TierBean;
import com.pumex.ConnectedInsight.Tier.beans.TierProcessBean;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TierDaoImpl implements TierDaoInterface
{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List getTierList(TierBean tierBean)
    {
        List tierlist = new ArrayList();
        tierlist = jdbcTemplate.queryForList(SQLSelector.getQuery("search.tierlist"));
        return tierlist;
    }

    public void addTier(TierBean tierBean)
    {
        String query = SQLSelector.getQuery("tier.addquery");
        jdbcTemplate.update(query, new Object[]{tierBean.getDesignation().trim(), tierBean.getTier()});
    }

    public void editTier(TierBean tierBean)
    {
        String query = SQLSelector.getQuery("tier.editquery");
        jdbcTemplate.update(query, new Object[]{tierBean.getDesignation(),tierBean.getTier()});
    }

    @Override
    public TierBean getTierList(Integer uid, final TierBean tierBean)
    {
        String query = SQLSelector.getQuery("tier.getdetails");
        return (TierBean) jdbcTemplate.queryForObject(query, new Object[]{uid}, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                tierBean.setDesignation(rs.getString(2));
                tierBean.setTier(rs.getInt(1));
                return tierBean;
            }
        });
    }
    
    public List getSubProcessTarget(TierProcessBean tierProcessBean)
    {
        List subprotargetlist = new ArrayList();
        subprotargetlist = jdbcTemplate.queryForList(SQLSelector.getQuery("subprocesstarget.getlist"), new Object[]{tierProcessBean.getFromdate(), tierProcessBean.getTodate(), tierProcessBean.getProcess()});
        return subprotargetlist;
    }
    
    public void addSubProcessTarget(TierProcessBean tierProcessBean)
    {
        String query = SQLSelector.getQuery("supprocesstarget.addquery");
        jdbcTemplate.update(query, new Object[]{tierProcessBean.getFromdate(), tierProcessBean.getTodate(), tierProcessBean.getProcess(), tierProcessBean.getTarget(), tierProcessBean.getStatus()});
    }

    public void updateSubProcessTarget(TierProcessBean tierProcessBean, Integer uid)
    {
        String query = SQLSelector.getQuery("subprocesstarget.update");
        jdbcTemplate.update(query, new Object[]{tierProcessBean.getFromdate(), tierProcessBean.getTodate(), tierProcessBean.getProcess(), tierProcessBean.getTarget(), tierProcessBean.getStatus(), uid});
    }

    @Override
    public TierProcessBean getSubProcessData(final TierProcessBean tierProcessBean, Integer uid)
    {
        String query = SQLSelector.getQuery("supprocess.getdata");
        return (TierProcessBean) jdbcTemplate.queryForObject(query, new Object[]{uid}, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                tierProcessBean.setFromdate(rs.getString(1));
                tierProcessBean.setTodate(rs.getString(2));
                tierProcessBean.setProcess(rs.getInt(3));
                tierProcessBean.setTarget(rs.getDouble(4));
                tierProcessBean.setStatus(rs.getInt(5));
                return tierProcessBean;
            }
        });
    }
}
