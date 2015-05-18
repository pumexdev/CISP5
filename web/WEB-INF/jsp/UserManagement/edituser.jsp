<%-- 
    Document   : CreateUser
    Created on : Jun 27, 2012, 9:46:21 AM
    Author     : user
--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/UserManagement/edituser.js"></script>

<form:form modelAttribute="EditUser">
    <div class="content_slide" id="locdiv">
        <div class="addcentr_area" style="width: 45%">
            <c:if test="${param.edit=='true'}">
                <div class="success_div" id="message">                      
                    <div id="message" class="success">User details updated Successfully</div>
                </div>
            </c:if>
            <c:if test="${param.edit=='false'}">
                <div class="failed_div" id="message">                       
                    <div id="message" class="failed">Error while updating User details</div>                        
                </div>
            </c:if>
            <div class="addcentr_top">Edit User </div>
            <div class="addcentr_fldarea">
                <div class="row">
                    <div id="invalid-username" class="errordiv"></div>
                    <div class="left">Username&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="username" type="text" id="username" maxlength="60" onchange="checkUser(this.value)" onfocus="$('#usercheck').html('');$('.success_div').hide()" readonly="true"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-password" class="errordiv"></div>
                    <div class="left">Password&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="password" type="password" id="password" maxlength="60"/>
                    </div>
                    <div class="password-meter" style="width:60.7940%;float:left;min-height:24px;">
                        <div class="password-meter-message" style="width: 340px;">&nbsp;</div>
                        <div class="password-meter-bg"  style="float: left;margin-left: 150px;min-height: 3px;width: 68.79%;">
                            <div class="password-meter-bar"></div>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row" style="display: none">
                    <div id="invalid-password1" class="errordiv"></div>
                    <div class="left">Confirm password&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <input name="cpassword" type="password" id="password1" maxlength="60"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-userlevel" class="errordiv"></div>
                    <div class="left">User Level&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="userlevel" id="userlevel" onchange="showDropDowns(this.value)">
                            <form:option value="0">Select</form:option>
                            <form:options items="${userlevels}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row" id="locationtr" style="display: none">
                    <div id="invalid-locationid" class="errordiv"></div>
                    <div class="left">Center&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="businesscenterid" id="locationid">
                            <form:option value="0">Select</form:option>
                            <form:options items="${locationlist}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row" id="processtr" style="display: none">
                    <div id="invalid-processid" class="errordiv"></div>
                    <div class="left">Process&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="processid" id="processid">
                            <form:option value="0">Select</form:option>
                            <form:options items="${processlist}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-userstatus" class="errordiv"></div>
                    <div class="left">Status&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="userstatus" id="userstatus">
                            <form:option value="0">Select</form:option>
                            <form:options items="${userstatuslist}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="addbottom_fldarea">
                <div class="addbottom_left">
                    <input type="submit" value="Update" />
                </div>
                <div class="addbottom_left">
                    <input type="reset" value="Cancel" onclick="cancel('searchuser.htm')"/>
                </div>
            </div>
        </div>
    </div>
</form:form>