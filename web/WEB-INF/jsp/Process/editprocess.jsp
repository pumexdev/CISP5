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

<script type="text/javascript" src="js/Process/editprocess.js"></script>
<script type="text/javascript" src="js/Process/searchprocess.js"></script>

<div class="content_slide">
    <div class="addcentr_area">
        <form:form modelAttribute="EditProcess" action="editprocess.htm" method="post" onsubmit="return checkProcessName($('#name1').val(),'processcheck1','name1','1')">
            <div id="message">
                <c:if test="${param.edit=='true'}">
                    <div class="success_div" id="message">                      
                        <div id="message" class="success">Process has been successfully updated</div>
                    </div>
                </c:if>
                <c:if test="${param.edit=='false'}">
                    <div class="failed_div" id="message">                       
                        <div id="message" class="failed">Process updation failed</div>                        
                    </div>
                </c:if>
            </div>
             <div class="addcentr_top" id="divhead">Edit Process</div>
            <div class="addcentr_fldarea">
                <div class="row"> 
                    <div id="invalid-name1" class="errordiv"></div>
                    <div class="left">Name</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="name" type="text" id="name1" maxlength="60" onchange="checkProcessName(this.value,'processcheck1',this.id,'0')" onfocus="$('#processcheck1').html('')"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row"> 
                    <div id="invalid-status1" class="errordiv"></div>
                    <div class="left">Status</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="status" id="status1">
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
                        <input type="submit" value="Update" />
                    </div>
                    <div class="addbottom_left">
                        <input type="reset" value="Cancel" onclick="cancel('searchprocess.htm')"/>
                    </div> 
                </div>
            </div>
            <form:hidden path="process" id="processid"/>      
            <input type="hidden" id="processname" value="${EditProcess.name}"/>
        </form:form>     
    </div>
</div>