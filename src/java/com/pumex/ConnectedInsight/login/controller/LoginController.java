 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.login.controller;

import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.common.bean.FileUploadBean;
import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.login.DAO.LoginDaoInterface;
import com.pumex.ConnectedInsight.login.beans.LoginFormBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author user
 */
@Controller
public class LoginController
{
    @Autowired
    LoginDaoInterface loginDaoInterface;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public ModelAndView getLoginAction(@ModelAttribute("login") LoginFormBean loginFormBean,HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView("login");
        UserBean user = (UserBean) session.getAttribute("user");
        if(user!=null && user.getUserid()!=null)
            model=new ModelAndView(new RedirectView("userhome.htm"));
        return model;
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    public ModelAndView postLoginAction(@ModelAttribute("login") LoginFormBean loginFormBean, HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        List userDetails = loginDaoInterface.checkLogin(loginFormBean);
        ModelAndView model;
        if (userDetails.size() > 0)
        {
            UserBean user = (UserBean) userDetails.get(0);
            session.setAttribute("user", user);
            model = new ModelAndView(new RedirectView("userhome.htm"));
            List menuList=loginDaoInterface.getMenuList(user);
            Integer firstMenuId=loginDaoInterface.getMenuId(menuList);
            List subMenuList=loginDaoInterface.getSubMenuList(user, firstMenuId);
            session.setAttribute("menuList", menuList);
            session.setAttribute("subMenuList", subMenuList);
        }
        else
        {
            model=new ModelAndView("login");
            model.addObject("error",true);
        }
        return model;
    }

    @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public ModelAndView getLogoutAction(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        session.invalidate();
        ModelAndView model = new ModelAndView(new RedirectView("login.htm"));
        return model;
    }
    
    @RequestMapping(value = "/getorganisationlogo.htm", method = RequestMethod.GET)
    public ModelAndView getOrgLogo(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        UserBean userBean=(UserBean)session.getAttribute("user");
        String query=SQLSelector.getQuery("organization.getlogo");
        byte[] buffer=(byte[])commonDaoInterface.queryForObject(query, userBean.getOrganizationid());
        OutputStream os=response.getOutputStream();
        os.write(buffer);
        return null;
    }
}