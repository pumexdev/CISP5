<%--Side bar menu (Main Menu) starts--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript" src="js/menu/ajaxsubmenu.js"></script>
<div id="sidebar">    
    <div class="side_topbat" id="side_topbar">
        <div class="left_logo">
            <img src="getorganisationlogo.htm" width="117px;" height='58px'/>
        </div>
        <ul id="navMenu">
            <c:forEach items="${menuList}" var="first" varStatus="loop">
                <c:if test="${loop.index==0}">
                    <li class="selected">
                        <span id="menu${first.MenuId}" onclick="getSubMenu(${first.MenuId},event);getSubMenuHome(${first.MenuId},event);" class="${first.MenuClass}">${first.Description}</span>
                        <div style="display: none;" class="hover"></div>
                    </li>
                </c:if>
                <c:if test="${loop.index>=1}">
                    <li>
                        <span id="menu${first.MenuId}" onclick="getSubMenu(${first.MenuId},event);getSubMenuHome(${first.MenuId},event);" class="${first.MenuClass}">${first.Description}</span>
                        <div style="display: none;" class="hover"></div>
                    </li>
                </c:if>
                <input type="hidden" id="hidden${loop.index+1}" value="${first.MenuId}"/>
            </c:forEach>
            <input type="hidden" id="count" value="${fn:length(menuList)}"/>
        </ul>
    </div>
    <div id="center_leftbar"></div>
    <div>
        <div class="sidebar_footer" id="sidebar_footer">
            <p class="sidebar_text01">${UserBean.username}</p>
            <div class="sidebar_box01_out">
                <c:if test="${UserBean.userlevel=='3' || UserBean.userlevel=='6'}">
                    <a href="#" onclick="showOrg()">
                        <div class="sidebar_box01"></div>
                    </a>
                </c:if>
                <a href="logout.htm">
                    <div class="sidebar_box02"></div>
                </a>
            </div>
            <p class="sidebar_text02">POWERED BY</p>
        </div>
    </div>
</div>
<%--Side bar menu (Main Menu) starts--%>
<script type="text/javascript">
    function centerDivToggle()
    {
        var clb_height = $(window).height() - ($('#side_topbar').height()+$('#sidebar_footer').height());
        $('#center_leftbar').css("height",clb_height);
    }
    
    centerDivToggle();
    
    $(window).resize(function()
    {
        centerDivToggle(); 
    });
    
    $(document).resize(function()
    {
        centerDivToggle(); 
    });
    
    function showOrg()
    {
        $("#tabdiv").remove();
        $('#heading').html('Organization Profile');
        $('.d_menu_nav').hide();
        $('#global_area_main_admin').load('organization.htm');
        $('#menuHead').html('');
    }

</script>