<%-- 
    Document   : addprocess
    Created on : 8 Apr, 2013, 2:03:34 PM
    Author     : user
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript" src="js/Process/addprocess.js"></script>
<script type="text/javascript" src="js/Process/searchprocess.js"></script>
<!DOCTYPE html>

<div class="content_slide">
    <div class="addcentr_area">
        <form:form modelAttribute="Process" action="addprocess.htm" method="post" onsubmit="return checkProcessName($('#name2').val(),'invalid-name2','name2','1')"> 
            <div id="message">
                <c:if test="${param.add=='true'}">
                    <div class="success_div" id="message">                      
                        <div id="message" class="success">Process has been successfully added</div>
                    </div>
                </c:if>
                <c:if test="${param.add=='false'}">
                    <div class="failed_div" id="message">                       
                        <div id="message" class="failed">Process addition failed</div>                        
                    </div>
                </c:if>
            </div>
            <div class="addcentr_top" id="divhead">Add Process</div>
            <div class="addcentr_fldarea">
                <div class="row"> 
                    <div id="invalid-name2" class="errordiv"></div>
                    <div class="left">Name</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="name" type="text" id="name2" maxlength="60" onchange="checkProcessName(this.value,'invalid-name2',this.id,'0')" onfocus="$('#processcheck').html('')"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row"> 
                    <div id="invalid-status2" class="errordiv"></div>
                    <div class="left">Status</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="status" id="status2">
                            <form:option value="0">Select</form:option>
                            <form:options items="${status}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="addbottom_fldarea">
                <div class="row">
                    <div class="addbottom_left">
                        <input type="submit" value="Save" />
                    </div>
                    <div class="addbottom_left">
                        <input type="reset" value="Cancel" onclick="cancel('searchprocess.htm')"/>
                    </div> 
                </div>
            </div>
        </form:form>
    </div>
</div>


