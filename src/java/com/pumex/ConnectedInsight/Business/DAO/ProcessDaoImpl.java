/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.pumex.ConnectedInsight.Business.beans.*;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Vishnu
 */
@Repository
public class ProcessDaoImpl implements ProcessDaoInterface {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    public void saveProcess(ProcessFormBean processFormBean, UserBean userBean) {
        String query = SQLSelector.getQuery("process.saveProcess");
        jdbcTemplate.update(query, new Object[]{processFormBean.getName().trim(),
                    processFormBean.getShortName(), userBean.getOrganizationid(),
                    processFormBean.getStatus()});
    }

    @Override
    public ProcessFormBean getProcess(final ProcessFormBean processFormBean, String pid) {
        String query = SQLSelector.getQuery("process.getprocessview");
        return (ProcessFormBean) jdbcTemplate.queryForObject(query, new Object[]{pid}, new RowMapper() {

            public Object mapRow(ResultSet rs, int i) throws SQLException {
                processFormBean.setName(rs.getString("ProcessName"));
                processFormBean.setOrganization(rs.getInt("OrganizationId"));
                processFormBean.setShortName(rs.getString("ShortName"));
                processFormBean.setStatus(rs.getInt("ProcessStatus"));
                processFormBean.setProcess(rs.getInt("ProcessId"));
                return processFormBean;
            }
        });
    }

    @Override
    public void updateProcess(ProcessFormBean processFormBean, UserBean userBean) {
        String query = SQLSelector.getQuery("process.updateProcess");
        jdbcTemplate.update(query, new Object[]{processFormBean.getName().trim(),
                    processFormBean.getStatus(), processFormBean.getProcess()});
    }

    @Override
    public void saveSubprocess(SubProcessFormBean subProcessFormBean) {
        String query = SQLSelector.getQuery("process.saveSubProcess");
        jdbcTemplate.update(query, new Object[]{subProcessFormBean.getProcess(),
                    subProcessFormBean.getName(), subProcessFormBean.getShortName(),
                    subProcessFormBean.getStatus()});
    }

    @Override
    public SubProcessFormBean getSubProcess(final SubProcessFormBean subProcessFormBean, String spid) {
        String query = SQLSelector.getQuery("process.getSubProcess");
        return (SubProcessFormBean) jdbcTemplate.queryForObject(query, new Object[]{spid}, new RowMapper() {

            public Object mapRow(ResultSet rs, int i) throws SQLException {
                subProcessFormBean.setName(rs.getString("SubProcessName"));
                subProcessFormBean.setShortName(rs.getString("ShortName"));
                subProcessFormBean.setStatus(rs.getInt("SubProcessStatus"));
                subProcessFormBean.setProcess(rs.getInt("ProcessId"));
                subProcessFormBean.setSubprocess(rs.getInt("SubProcessId"));
                subProcessFormBean.setUsedforvolume(rs.getBoolean("UsedForVolume"));
                subProcessFormBean.setProcessname(rs.getString("ProcessName"));
                return subProcessFormBean;
            }
        });
    }

    public void updateSubprocess(SubProcessFormBean subProcessFormBean) {
        String query = SQLSelector.getQuery("process.updateSubProcess");
        jdbcTemplate.update(query, new Object[]{subProcessFormBean.getProcess(),
                    subProcessFormBean.getName(), subProcessFormBean.getShortName(),
                    subProcessFormBean.getStatus(), subProcessFormBean.getSubprocess()});
    }

    public Map getSubProcessList(Integer processid) {
        String query = SQLSelector.getQuery("process.getsubprocesses");
        return commonDaoInterface.queryForMap(query, processid);
    }

    @Override
    public Map getsubProcessList(String val) {
        String query = SQLSelector.getQuery("subprocess.getnames");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query, val));
    }

    public Boolean checkProcessName(String processName) {
        String query = SQLSelector.getQuery("process.getByName");
        List businessunitList = new ArrayList();
        businessunitList = jdbcTemplate.queryForList(query, processName);
        if (businessunitList != null && businessunitList.size() > 0) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public Boolean checkSubProcessName(String subProcessName) {
        String query = SQLSelector.getQuery("subprocess.getByname");
        List businessunitList = new ArrayList();
        businessunitList = jdbcTemplate.queryForList(query, subProcessName);
        if (businessunitList != null && businessunitList.size() > 0) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    @Override
    public List getAllProcessList(UserBean userBean) {
        String query = SQLSelector.getQuery("process.getAllProcess");
        return jdbcTemplate.queryForList(query, userBean.getOrganizationid());
    }
}
