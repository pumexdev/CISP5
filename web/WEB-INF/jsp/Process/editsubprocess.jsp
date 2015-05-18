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

<script type="text/javascript" src="js/Process/editsubprocess.js"></script>

<div class="content_slide">
    <div class="addcentr_area">
        <form:form modelAttribute="EditSubProcess" action="editsubprocess.htm" method="post" onsubmit="return checkSubProcessName($('#name2').val(),'subprocesscheck1','name2','1')">
            <div id="message">
                <c:if test="${param.edit=='true'}">
                    <div class="success_div">                      
                        <div id="message" class="success">Sub-Process has been successfully updated</div>
                    </div>
                </c:if>
                <c:if test="${param.edit=='false'}">
                    <div class="failed_div">                       
                        <div id="message" class="failed">Sub-Process updation failed</div>                        
                    </div>
                </c:if>
            </div>
            <div class="addcentr_top" id="divhead">Edit Sub-Process</div>
            <div class="addcentr_fldarea">
                <div class="row">
                    <div class="left">Process</div>
                    <div class="right">
                        <form:input path="processname" type="text" id="processname" maxlength="60" readonly="true"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-name2" class="errordiv"></div>
                    <div class="left">Sub-Process</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="name" type="text" id="name2" maxlength="60" onchange="checkSubProcessName(this.value,'invalid-name2',this.id,'0')" onfocus="$('#subprocesscheck1').html('')"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-usedforvolume2" class="errordiv"></div>
                    <div class="left"></div>
                    <div class="right">
                        <div class="checck_style" style="padding-top: 5px;margin-right: 25px;">
                            <form:checkbox  path="usedforvolume" id="usedforvolume2"/>
                        </div>
                        <div class="checck_content" style="cursor: pointer" onclick="checkUsedforvolume('usedforvolume2');">Use for Volume</div>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row" style="min-height: 44px;">
                    <div id="invalid-status2" class="errordiv"></div>
                    <div class="left">Status</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right" >
                        <form:select path="status" id="status2">
                            <form:option value="0">Select</form:option>
                            <form:options items="${status}"></form:options>
                        </form:select>
                    </div>
                </div>
            </div>
            <div class="addbottom_fldarea">
                <div class="addbottom_left">
                    <input type="submit" value="Update" />
                </div>
                <div class="addbottom_left">
                    <input type="reset" value="Cancel" onclick="cancel('searchsubprocess.htm')"/>
                </div> 
                <div class="clear"></div>
            </div>  
            <form:hidden  path="subprocess" id="subprocess2"/>
            <form:hidden path="process" id="process2"/>
            <input type="hidden" id="subprocessname" value="${EditSubProcess.name}"/>
        </div>        
    </form:form>
</div>
</div>
