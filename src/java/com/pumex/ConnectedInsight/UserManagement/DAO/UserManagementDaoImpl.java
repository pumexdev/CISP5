/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.UserManagement.DAO;

import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.UserBean;
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

/**
 *
 * @author user
 */
@Repository
public class UserManagementDaoImpl implements UserManagementDaoInterface
{

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    
    public List getUserData(Integer ulid,final  UserBean userBean)
    {
        List userlist = new ArrayList();
        userlist = jdbcTemplate.queryForList(SQLSelector.getQuery("user.getdata_by_userlevel"),ulid);
        return userlist;
    }

    public void saveUser(UserBean userBean)
    {
        String query = SQLSelector.getQuery("user.insertquery");
        Integer success=jdbcTemplate.update(query, new Object[]{userBean.getUsername(), userBean.getPassword(), userBean.getUserlevel(),
                    userBean.getOrganizationid(), userBean.getProcessid(), userBean.getBusinesscenterid(), userBean.getUserstatus()});
        if(success!=null && success.equals(1))
        {
            Integer newUserId=(Integer)commonDaoInterface.queryForObject("SELECT MAX(UserId) AS UserId FROM User");
            jdbcTemplate.update(SQLSelector.getQuery("usergroup.insertusers"), userBean.getUserlevel(),newUserId);
        }
    }
    
    public Boolean checkUserExists(String userName)
    {
        String query = SQLSelector.getQuery("user.checkuserexists");
        List users=new ArrayList();
        users=jdbcTemplate.queryForList(query, userName);
        Boolean check=Boolean.FALSE;
        if(users!=null && users.size()==0)
        {
            check=Boolean.TRUE;
        }
        return check;
    }

    public Map getGroupList()
    {
        String query = SQLSelector.getQuery("usergroup.getdesc");
        return commonDaoInterface.queryForMap(query);
    }

    public void updateUser(UserBean userBean, Integer uid)
    {
        String query = SQLSelector.getQuery("user.updatequery");
        jdbcTemplate.update(query, new Object[]{userBean.getUsername(), userBean.getPassword(), userBean.getUserlevel(), userBean.getOrganizationid(), userBean.getProcessid(), userBean.getBusinesscenterid(), userBean.getUserstatus(), uid});
        query = SQLSelector.getQuery("user.usergroupuser");
        jdbcTemplate.update(query, new Object[]{userBean.getUserlevel(),uid});
    }

    public Map getUserLevels()
    {
        String query = SQLSelector.getQuery("user.getlevels");
        return commonDaoInterface.queryForMap(query);
    }

    public List searchuser(UserBean userBean,UserBean userBean1)
    {
        List userlist = new ArrayList();
        if(userBean.getUsername()!=null && !userBean.getUsername().equals("") && !userBean.getUsername().equals("Type first letter/name"))
            userlist = jdbcTemplate.queryForList(SQLSelector.getQuery("search.userlist"), userBean1.getOrganizationid(),"%" + userBean.getUsername() + "%");
        else
            userlist = jdbcTemplate.queryForList(SQLSelector.getQuery("search.userlist_byuserlevel"), userBean1.getOrganizationid(),userBean.getUserlevel());
        return userlist;
    }

    @Override
    public Map getCountry() {
        String query = SQLSelector.getQuery("organization.getcountry");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query));
    }

    @Override
    public Map getUserGroupStatus() {
        String query = SQLSelector.getQuery("usergroup.getstatus");
        return commonDaoInterface.queryForMap(query);
    }

    @Override
    public UserBean getUserDetails(final UserBean userBean, Integer uid)
    {
        String query = SQLSelector.getQuery("useredit.getdata");
        return (UserBean) jdbcTemplate.queryForObject(query, new Object[]{uid}, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                userBean.setUsername(rs.getString(2));
                userBean.setPassword(rs.getString(3));
                userBean.setUserlevel(rs.getInt(4));
                userBean.setOrganizationid(rs.getInt(5));
                userBean.setProcessid(rs.getInt(6));
                userBean.setBusinesscenterid(rs.getInt(7));
                userBean.setUserstatus(rs.getInt(8));
                return userBean;
            }
        });
    }

    @Override
    public List getsubBusiness(String val)
    {
        String query = "SELECT SubBusinessUnitId,SubBusinessUnitName FROM SubBusinessUnit WHERE BusinessUnitId=?";
        return jdbcTemplate.queryForList(query, val);
    }
    
    public List getUsersForOrg(UserBean userBean)
    {
        String query=SQLSelector.getQuery("search.alluserlist");
        return jdbcTemplate.queryForList(query,userBean.getOrganizationid());
    }
}
