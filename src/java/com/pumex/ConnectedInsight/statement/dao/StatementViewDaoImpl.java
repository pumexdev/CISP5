/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.statement.dao;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class StatementViewDaoImpl implements StatementViewDao
{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List getRecentStatementDate()
    {
        String query=SQLSelector.getQuery("statement.rencent4dates");
        List recentDateList=jdbcTemplate.queryForList(query);
        return recentDateList;
    }
    
}
