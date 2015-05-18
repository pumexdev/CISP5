package com.pumex.ConnectedInsight.UserManagement.controller;

import com.pumex.ConnectedInsight.UserManagement.DAO.UserManagementDaoInterface;
import com.pumex.ConnectedInsight.login.DAO.LoginDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Vishnu
 */
@Controller
public class UserAjaxController
{
    @Autowired
    UserManagementDaoInterface userManagementDaoInterface;
    @Autowired
    LoginDaoInterface loginDaoInterface;
    
    @RequestMapping(value="/checkuser.htm",method= RequestMethod.POST)
    public ModelAndView checkUser(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("jsonView");
        Boolean check=Boolean.FALSE;
        try
        {
            check=userManagementDaoInterface.checkUserExists(request.getParameter("user"));
            model.addObject("check", check);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            model.addObject("check", false);
        }
        return model;
    }
    @RequestMapping(value = "/getuserdetails.htm", method = RequestMethod.POST)
    public ModelAndView getUserDetails(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("jsonView");
        UserBean userBean = new UserBean();
        List list = new ArrayList();
        list = userManagementDaoInterface.getUserData(Integer.parseInt(request.getParameter("ulid")),userBean);
        list.add(list);
        model.addObject("userlist", list);
        return model;
    }
    
    @RequestMapping(value="/getsubmenu.htm",method= RequestMethod.POST)
    public ModelAndView getSubMenuList(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model=new ModelAndView("jsonView");
        HttpSession session = request.getSession();
        UserBean userBean=(UserBean)session.getAttribute("user");
        Integer menuId=Integer.parseInt(request.getParameter("menuid"));
        List subMenuList=loginDaoInterface.getSubMenuList(userBean, menuId);
        //String homePage=loginDaoInterface.getSubMenuHome(userBean, menuId);
        model.addObject("subMenuList", subMenuList);
 //       model.addObject("homePage", homePage);
        return model;
    }
    
    @RequestMapping(value="getsubmenuhome.htm",method= RequestMethod.GET)
    public ModelAndView getSubMenuHome(HttpServletRequest request,HttpServletResponse response){
        ModelAndView model=null;
        HttpSession session = request.getSession();
        UserBean userBean=(UserBean)session.getAttribute("user");
        Integer menuId=Integer.parseInt(request.getParameter("menuid"));
        String homePage=loginDaoInterface.getSubMenuHome(userBean, menuId);
        if(!homePage.equals("")){
        model=new ModelAndView(new RedirectView(homePage));
        }
        return model;
    }
}
