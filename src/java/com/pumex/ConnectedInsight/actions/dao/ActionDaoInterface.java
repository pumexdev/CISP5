/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.actions.dao;

import com.pumex.ConnectedInsight.actions.beans.ActionsBean;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public interface ActionDaoInterface {

    public void addActionType(ActionsBean actionsBean);

    public Boolean checkActionTypeExists(String actionType);

    public Boolean checkActionExists(String action, String actionType);

    public Boolean checkActionExistsEdit(String action, String actionTypeId, String actionid);

    public Boolean checkActionTypeExistsEdit(String actionType, String actionTypeId);

    public ActionsBean getActionTypeDetails(ActionsBean actionsBean, Integer actionTypeId);

    public void updateActionType(ActionsBean actionsBean);

    public List getAllActionTypes();

    public Map getActionTypeList();

    public void addActions(ActionsBean actionsBean);

    public void updateActions(ActionsBean actionsBean);

    public ActionsBean getActionsDetails(ActionsBean actionsBean, Integer actionId);

    public List getAllActions();

    public List getAllActions(ActionsBean actionsBean);

    public Boolean checkActionTypeActionExists(String actionTypeId);

    public List getsubProcessfromProcess(String processid);

    public List getActionTypeMapList();

    public void deleteActions(String actionid);

    public Integer getLastActioType();
}
