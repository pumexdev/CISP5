/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.actions.dao;

import com.pumex.ConnectedInsight.actions.beans.ActionsBean;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class ActionDaoImpl implements ActionDaoInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    @Override
    public void addActionType(ActionsBean actionsBean) {
        String query = SQLSelector.getQuery("actiontype.insert");
        jdbcTemplate.update(query, new Object[]{actionsBean.getActionType(), actionsBean.getStatus(), actionsBean.getOrgId()});
    }

    @Override
    public Boolean checkActionTypeExists(String actyionType) {
        String query = SQLSelector.getQuery("actiontype.checkexists");
        List users = new ArrayList();
        users = jdbcTemplate.queryForList(query, actyionType);
        Boolean check = Boolean.FALSE;
        if (users != null && users.size() == 0) {
            check = Boolean.TRUE;
        }
        return check;
    }

    @Override
    public Boolean checkActionExists(String action,String actionType) {
        String query = SQLSelector.getQuery("action.checkexists");
        List users = new ArrayList();
        users = jdbcTemplate.queryForList(query, new Object[]{action, actionType});
        Boolean check = Boolean.FALSE;
        if (users != null && users.size() == 0) {
            check = Boolean.TRUE;
        }
        return check;
    }

    @Override
    public Boolean checkActionExistsEdit(String action, String actionTypeId, String actionid) {
        String query = SQLSelector.getQuery("action.checkexistsedit");
        List users = new ArrayList();
        users = jdbcTemplate.queryForList(query, new Object[]{action,actionTypeId, actionid});
        Boolean check = Boolean.FALSE;
        if (users != null && users.size() == 0) {
            check = Boolean.TRUE;
        }
        return check;
    }

    @Override
    public Boolean checkActionTypeExistsEdit(String actionType, String actionTypeId) {
        String query = SQLSelector.getQuery("actiontype.checkexistsedit");
        List users = new ArrayList();
        users = jdbcTemplate.queryForList(query, new Object[]{actionType, actionTypeId});
        Boolean check = Boolean.FALSE;
        if (users != null && users.size() == 0) {
            check = Boolean.TRUE;
        }
        return check;
    }

    @Override
    public ActionsBean getActionTypeDetails(final ActionsBean actionsBean, Integer actionTypeId) {
        String query = SQLSelector.getQuery("actiontype.getactiontype");
        return (ActionsBean) jdbcTemplate.queryForObject(query, new Object[]{actionTypeId}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                actionsBean.setActionType(rs.getString("ActionType"));
                actionsBean.setStatus(rs.getString("Status"));
                actionsBean.setActionTypeId(rs.getInt("ActionTypeId"));
                return actionsBean;
            }
        });
    }

    @Override
    public void updateActionType(ActionsBean actionsBean) {
        String query = SQLSelector.getQuery("actiontype.update");
        jdbcTemplate.update(query, new Object[]{actionsBean.getActionType(), actionsBean.getStatus(), actionsBean.getActionTypeId()});
    }

    @Override
    public List getAllActionTypes() {
        String query = SQLSelector.getQuery("actiontype.getall");
        return jdbcTemplate.queryForList(query);
    }

    @Override
    public Map getActionTypeList() {
        String query = SQLSelector.getQuery("actiontype.getmap");
        return commonDaoInterface.queryForMap(query);
    }

    @Override
    public void addActions(ActionsBean actionsBean) {
        String query = SQLSelector.getQuery("actions.insert");
        jdbcTemplate.update(query, new Object[]{actionsBean.getSubprocess(), actionsBean.getActionTypeId(), actionsBean.getPercentage(), actionsBean.getAction(), actionsBean.getStatus()});
    }

    @Override
    public void updateActions(ActionsBean actionsBean) {
        String query = SQLSelector.getQuery("actions.update");
        jdbcTemplate.update(query, new Object[]{actionsBean.getSubprocess(), actionsBean.getActionTypeId(), actionsBean.getPercentage(), actionsBean.getAction(), actionsBean.getStatus(), actionsBean.getActionId()});
    }

    @Override
    public ActionsBean getActionsDetails(final ActionsBean actionsBean, Integer actionId) {
        String query = SQLSelector.getQuery("actions.getbyid");
        return (ActionsBean) jdbcTemplate.queryForObject(query, new Object[]{actionId}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                actionsBean.setActionTypeId(rs.getInt("ActionTypeId"));
                actionsBean.setSubprocess(rs.getInt("SubprocessId"));
                actionsBean.setPercentage(rs.getInt("Percentage"));
                actionsBean.setActionId(rs.getInt("ActionsId"));
                actionsBean.setProcess(rs.getInt("ProcessId"));
                actionsBean.setAction(rs.getString("ActionName"));
                actionsBean.setStatus(rs.getString("Status"));
                return actionsBean;
            }
        });
    }

    @Override
    public List getAllActions() {
        String query = SQLSelector.getQuery("actions.getall");
        return jdbcTemplate.queryForList(query);
    }

    @Override
    public List getAllActions(ActionsBean actionsBean) {
        String query = SQLSelector.getQuery("actions.getallbysubprocess");
        return jdbcTemplate.queryForList(query, new Object[]{actionsBean.getSubprocess()});
    }

    @Override
    public Boolean checkActionTypeActionExists(String actionTypeId) {
        String query = SQLSelector.getQuery("actiontype.checkexistaction");
        List users = new ArrayList();
        users = jdbcTemplate.queryForList(query, new Object[]{actionTypeId});
        Boolean check = Boolean.FALSE;
        if (users != null && users.size() == 0) {
            check = Boolean.TRUE;
        }
        return check;
    }

    public List getsubProcessfromProcess(String processid) {
        String query = SQLSelector.getQuery("target.subprocesslist");
        return jdbcTemplate.queryForList(query, new Object[]{processid});
    }

    @Override
    public List getActionTypeMapList() {
        String query = SQLSelector.getQuery("actiontype.getmap");
        return jdbcTemplate.queryForList(query);
    }

    @Override
    public void deleteActions(String actionid) {
        String query = SQLSelector.getQuery("action.delete");
        jdbcTemplate.update(query, new Object[]{actionid});
    }

    @Override
    public Integer getLastActioType() {
        String query = SQLSelector.getQuery("actiontype.getmax");
        return jdbcTemplate.queryForInt(query);
    }
}
