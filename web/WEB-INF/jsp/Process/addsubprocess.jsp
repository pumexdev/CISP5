<%-- 
    Document   : addsubprocess
    Created on : 8 Apr, 2013, 10:50:27 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript" src="js/Process/addsubprocess.js"></script>

<div class="content_slide">
    <div class="addcentr_area">
        <form:form modelAttribute="AddSub-Process"  action="addsubprocess.htm" method="post" onsubmit="return checkSubProcessName($('#name1').val(),'subprocesscheck','name1','1')"><!--action="addsubprocess.htm"-->
            <div id="message">
                <c:if test="${param.add=='true'}">
                    <div class="success_div">                      
                        <div id="message" class="success">New Sub-Process successfully added</div>
                    </div>
                </c:if>
                <c:if test="${param.add=='false'}">
                    <div class="failed_div">                       
                        <div id="message" class="failed">Sub-Process addition failed</div>                        
                    </div>
                </c:if>
            </div>
            <div class="addcentr_top" id="divhead">Add Sub-Process</div>

            <div class="addcentr_fldarea">
                 <div class="row">                   
                    <div class="left">Process</div>
                    <div class="right">
                        <form:select path="process" id="process">
                            <form:option value="">Select</form:option>
                            <form:options items="${processlist}"></form:options>
                        </form:select>                       
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-name1" class="errordiv"></div>
                    <div class="left">Sub-Process</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="name" type="text" id="name1" maxlength="60" onchange="checkSubProcessName(this.value,'invalid-name1',this.id,'0')" onfocus="$('#subprocesscheck').html('')"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-usedforvolume" class="errordiv"></div>
                    <div class="left"></div>
                    <div class="right">
                        <div style="cursor: pointer;width: 150px;" onclick="checkUsedforvolume('usedforvolume');">
                            <div class="checck_style" style="padding-top: 5px;margin-right: 25px;">
                                <form:checkbox  path="usedforvolume" id="usedforvolume" onclick="checkUsedforvolume('usedforvolume');"/>
                            </div>
                            <div class="checck_content" style="cursor: pointer;">Use for Volume</div>
                        </div>
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
                <div class="addbottom_left">
                    <input type="submit" value="Save" />
                </div>
                <div class="addbottom_left">
                    <input type="reset" value="Cancel" onclick="cancel('searchsubprocess.htm')"/>
                </div> 
                <div class="clear"></div>
            </div>
        </form:form>
    </div>
</div>
