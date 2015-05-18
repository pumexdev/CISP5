<%-- 
    Document   : login1
    Created on : Apr 13, 2012, 10:49:21 PM
    Author     : Vishnu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/login.js"></script>
<div id="main_wrap" style="height: 100% !important">
    <div class="header_wrap1">
        <div class="logo_align">
            <p><img  src="images/login_logo.png" alt="" /> </p>
        </div>
        <br clear="all">
        </br>
    </div>
    <div class="loginouter" id="loginouter">
        <div class="logincontentInner">
            <c:if test="${error==true && pageContext.request.method=='POST'}">
                <div class="n_error">
                    <p>Username or password do not match our records</p>
                </div> 
            </c:if>
            <form:form modelAttribute="login">
                <label for="login" class="mrg">Username</label>
                <p id="invalid-username"></p>
                <form:input type="text" path="username" class="text" autocomplete="off"/>
                <label for="pass">Password</label>
                <p id="invalid-password"></p>
                <form:input type="password" path="password"  class="text" autocomplete="off"/>
                <input type="submit" value="Login"/>
                <%--a class="log_button" href="">Forgotten password?</a--%>
            </form:form>
        </div>
    </div>


