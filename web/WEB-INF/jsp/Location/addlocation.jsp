<%-- 
    Document   : CreateUser
    Created on : Jun 27, 2012, 9:46:21 AM
    Author     : user
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/Location/addlocation.js"></script>
<script type="text/javascript" src="js/Organization/PopUp.js"></script>

<form:form modelAttribute="Location" method="post" onsubmit="return checkLocation($('#name').val(),'1');">
    <div class="content_slide" id="locdiv">
        <div class="addcentr_area">
            <div id="message">
                <c:if test="${param.add=='true'}">                
                    <div class="success_div" >                      
                        <div id="message" class="success">Business Center has been successfully added</div>
                    </div>
                </c:if>
                <c:if test="${param.add=='false'}">
                    <div class="failed_div">                       
                        <div id="message" class="failed">Business Center addition failed</div>                        
                    </div>
                </c:if>
            </div>
            <div class="addcentr_top">Add Business Center</div>
            <div class="addcentr_fldarea">
                <div class="row">
                    <div id="invalid-name" class="errordiv"></div>
                    <div class="left">Name&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="name" type="text" id="name" maxlength="60" onclick="messagedelete()" onchange="checkLocation(this.value,'0')" onfocus="$('#locationcheck').html('')"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-shortName" class="errordiv"></div>
                    <div class="left">Short Name&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="shortName" type="text" id="shortName" maxlength="10"/>
                    </div>
                    <div class="clear"></div>
                </div>               
                <div class="row">
                    <div id="invalid-center" class="errordiv"></div>
                    <div class="left">Client</div>
                    <div class="right">
                        <form:select path="client" id="client" multiple="multiple" style="height :100px;width:99%">
                            <form:options items="${centerlist}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-city" class="errordiv"></div>
                    <div class="left">City&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="city" type="text" id="city" maxlength="50"/>
                    </div>
                    <div class="clear"></div>
                </div> 
            </div>
            <div class="addbottom_fldarea">
                <div class="addbottom_left">
                    <input type="submit" value="Save" />
                </div>
                <div class="addbottom_left">
                    <input type="reset" value="Cancel" onclick="cancel('searchlocation.htm')"/>
                </div>
            </div>
        </div>
    </div>   
    <input type="hidden" id="locationname"/>
</form:form>