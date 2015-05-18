<%-- 
    Document   : addactiontype
    Created on : 10 Mar, 2015, 3:44:47 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<script type="text/javascript" src="js/Actions/addactiontype.js"></script>
<form:form modelAttribute="AddActionType" onsubmit="return checkActionType($('#actionType').val());">
    <div class="content_slide" id="locdiv">
        <div class="addcentr_area" style="width: 45%">
            <div id="message">
                <c:if test="${param.add=='true'}">
                    <div class="success_div" id="message">                      
                        <div id="message" class="success">Action Type saved Successfully</div>
                    </div>
                </c:if>
                <c:if test="${param.add=='false'}">
                    <div class="failed_div" id="message">                       
                        <div id="message" class="failed">Error while saving Action Type</div>                        
                    </div>
                </c:if>
            </div>
            <div class="addcentr_top">Add Action Type</div>
            <div class="addcentr_fldarea">
                <div class="row">
                    <div id="invalid-actionType" class="errordiv"></div>
                    <div class="left">Action Type&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="actionType" type="text" id="actionType" maxlength="30" onchange="checkActionType(this.value)"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-status" class="errordiv"></div>
                    <div class="left">Status&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="status" id="status">
                            <form:option value="">Select</form:option>
                            <form:options items="${statuslist}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>  
            </div>
            <div class="addbottom_fldarea">
                <div class="addbottom_left">
                    <input type="submit" value="Save" />
                </div>
                <div class="addbottom_left">
                    <input type="reset" value="Cancel" onclick="cancel('listactiontype.htm')"/>
                </div>
            </div>
        </div>
    </div>
</form:form>
