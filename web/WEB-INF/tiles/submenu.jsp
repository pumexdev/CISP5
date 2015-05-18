<%@page import="java.util.List"%>
<%@page import="com.pumex.ConnectedInsight.login.beans.UserBean"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--Sub-menu starts--%>
<div class="d_menu_nav_out">
    <div style="width: auto;float: right;">
        <ul class="d_menu_nav">
            <c:forEach items="${subMenuList}" var="first" varStatus="loop">
                <c:choose>
                    <c:when test="${first.Description=='Statement'}">
                        <li><div id="menuHead"><a style="cursor: text; float: left; padding-right: 0px; margin-right: 0px; " href="${first.MenuUrl}"><b> ${first.Description} </b></a><span style="float: left; font-weight: normal; padding: 0px; color: rgb(32, 32, 32); margin: 12px 0px 0px;"> | <i>Manage Data:</i></span></div></li>
                    </c:when>
                    <c:otherwise>
                        <li>
                    <a href="${first.MenuUrl}" >${first.Description}</a>
                </li> 
                    </c:otherwise>
                </c:choose>
<!--                <li>
                    <a href="${first.MenuUrl}" >${first.Description}</a>
                </li> -->
            </c:forEach>
        </ul>
    </div>
    <div style="width: 70%">
        <ul class="d_menu_head">
            <li>
                <a href="#" style="cursor: text;"><div id="menuHead"></div></a>
            </li>
        </ul>
    </div>
</div>
<%--Sub-menu ends--%>