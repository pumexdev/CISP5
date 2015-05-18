/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import com.pumex.ConnectedInsight.Business.beans.SubProcessFormBean;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author user
 */
@Repository
public class SubProcessDaoImpl implements SubProcessDaoInterface {

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    public void editSubProcess(SubProcessFormBean subProcessFormBean) {
        String userfrovolume = "0";
        if (subProcessFormBean.getUsedforvolume() == true) {
            userfrovolume = "1";
        }
        String query = SQLSelector.getQuery("subprocess.edit");
        jdbcTemplate.update(query, new Object[]{subProcessFormBean.getProcess(), subProcessFormBean.getName().trim(), subProcessFormBean.getShortName(), subProcessFormBean.getStatus(), userfrovolume, subProcessFormBean.getSubprocess()});
    }

    public void addSubprocess(SubProcessFormBean subProcessFormBean) {
        String userfrovolume = "0";
        if (subProcessFormBean.getUsedforvolume() == true) {
            userfrovolume = "1";
        }
        String query = SQLSelector.getQuery("subprocess.insert");
        jdbcTemplate.update(query, new Object[]{subProcessFormBean.getProcess(), subProcessFormBean.getName().trim(), subProcessFormBean.getShortName(), subProcessFormBean.getStatus(), userfrovolume});
    }

    public Map getSubProcessForVolume() {
        String query = SQLSelector.getQuery("subprocess.getlist_forvolume");
        return commonDaoInterface.queryForMap(query);
    }

    public Map getSubProcessForVolume1() {
        String query = SQLSelector.getQuery("subprocess.getbyvolume");
        return commonDaoInterface.queryForMap(query);
    }

    public Map subProcessList() {
        String query = SQLSelector.getQuery("subprocess.getlist");
        return commonDaoInterface.queryForMap(query);
    }

    @Override
    public List getAllSubProcessList(UserBean userBean) {
        String query = SQLSelector.getQuery("subprocess.getAllSubProcess");
        return jdbcTemplate.queryForList(query);
    }
    
}
