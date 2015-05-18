<%-- 
    Document   : editactions
    Created on : 12 Mar, 2015, 11:41:09 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<script type="text/javascript" src="js/Actions/editactions.js"></script>
<form:form modelAttribute="Editactions">
    <div class="content_slide" id="locdiv">
        <div class="addcentr_area" style="width: 45%">
            <div id="message">
                <c:if test="${param.edit=='true'}">
                    <div class="success_div" id="message">                      
                        <div id="message" class="success">Actions updated Successfully</div>
                    </div>
                </c:if>
                <c:if test="${param.edit=='false'}">
                    <div class="failed_div" id="message">                       
                        <div id="message" class="failed">Error while updating Actions</div>                        
                    </div>
                </c:if>
            </div>
            <div class="addcentr_top">Edit Actions</div>
            <div class="addcentr_fldarea">
                <div class="row">
                    <div id="invalid-subprocess" class="errordiv"></div>
                    <div class="left">Process&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="process" id="process" onchange="updateSubprocess(this.value,'subprocess')">
                            <form:option value="">Select</form:option>
                            <form:options items="${processlist}"></form:options>
                        </form:select>            
                    </div>
                    <div class="clear"></div>
                </div>                
                <div class="row">
                    <div id="invalid-subprocess" class="errordiv"></div>
                    <div class="left">Subprocess&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="subprocess" id="subprocess" style="min-width: 20px;width:200px;">
                            <form:option value="">Select</form:option>
                            <form:options items="${subprocesslist}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>                
                <div class="row">
                    <div id="invalid-ActionTypeId" class="errordiv"></div>
                    <div class="left">Action Type&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right" id="actiontypeid">
                        <form:select path="ActionTypeId" id="ActionTypeId" onchange="dropdownselected(this.id)">
                            <form:option value="-1">Select</form:option>
                            <form:option value="0">Add New Type</form:option>
                            <form:options items="${actiontypelist}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-action" class="errordiv"></div>
                    <div class="left">Action&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input type="text" path="action" id="action" autocomplete="off" maxlength="100" onchange="checkActionName()"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-subprocess" class="errordiv"></div>
                    <div class="left">Percentage&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="percentage" id="percentage" maxlength="30" style="min-width: 20px;width:70px;"/>
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
                    <input type="submit" value="Update" />
                </div>
                <div class="addbottom_left">
                    <input type="reset" value="Cancel" onclick="cancel('searchactions.htm')"/>
                </div>
            </div>
        </div>
    </div>
    <form:hidden path="actionId" id="actionId" />
</form:form>
