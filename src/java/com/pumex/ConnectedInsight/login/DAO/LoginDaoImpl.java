/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.login.DAO;

import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.LoginFormBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class LoginDaoImpl implements LoginDaoInterface
{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List checkLogin(LoginFormBean loginFormBean)
    {
        String query = SQLSelector.getQuery("user.checkLogin");
        List<UserBean> userDetails = jdbcTemplate.query(query, ParameterizedBeanPropertyRowMapper.newInstance(UserBean.class), new Object[]{loginFormBean.getUsername(), loginFormBean.getPassword(),1});
        return userDetails;
    }

    @Override
    public List getMenuList(UserBean user)
    {
        String query = SQLSelector.getQuery("user.getMenuList");
        List menuList = jdbcTemplate.queryForList(query, new Object[]{user.getUserid(),0});
        return menuList;
    }

    @Override
    public List getSubMenuList(UserBean user, Integer menuId) {
        String query=SQLSelector.getQuery("user.getSubMenuList");
        List subMenuList=jdbcTemplate.queryForList(query, new Object[]{user.getUserid(),menuId});
        return subMenuList;
    }

    @Override
    public Integer getMenuId(List menuList) {
        Integer count=0;
        Integer menuId=0;
        Iterator it=menuList.iterator();
        while(it.hasNext()){
            if(count<1){
                Map item=(Map)it.next();
                menuId=Integer.parseInt(item.get("MenuId").toString());
                break;
            }
            count++;
        }
        return menuId;
    }

    @Override
    public String getSubMenuHome(UserBean user, Integer menuId) {
        String homePage="";
        String query=SQLSelector.getQuery("user.getSubMenuHome");
        List subMenuList=jdbcTemplate.queryForList(query, new Object[]{user.getUserid(),menuId});
        Iterator it=subMenuList.iterator();
        if(it.hasNext()){
            Map item=(Map)it.next();
            homePage=item.get("MenuUrl").toString();
        }
        return homePage;
        
    }
}
