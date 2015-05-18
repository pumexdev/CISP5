<%-- 
    Document   : searchactions
    Created on : 22 Apr, 2015, 10:44:16 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript" src="js/Actions/searchactions.js"></script>

<div id="scrollbar1">
    <div class="scrollbar">
        <div class="thumb">
            <div class="end"></div>
        </div>
    </div>
    <div class="viewport">
        <div class="overview">
            <form:form modelAttribute="SearchActions" onsubmit="return validateform();">
                <div class="admin_left" style="padding: 10px;">
                    <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                        <div class="addcentr_top">Search Actions</div>
                        <div class="addcentr_fldarea" style="padding: 10px !important">
                            <div class="row">
                                <div class="errordiv"></div>
                                <div class="left" style="width: 10%">Process</div>
                                <div class="right">
                                    <form:select path="process" id="process" onchange="getSubprocess()">
                                        <form:option value="">Select</form:option>
                                        <form:options  items="${processlist}"></form:options>                        
                                    </form:select>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div class="errordiv"></div>
                                <div class="left" style="width: 10%">Sub-Process</div>
                                <div class="right" id="subprocessdiv">
                                    <form:select path="subprocess" id="subprocess" style="min-width: 20px;width:200px;"><!--onchange="callForm(this.value)"-->
                                        <form:option value="">Select</form:option>                           
                                    </form:select>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="addbottom_fldarea">
                            <div class="addbottom_left">
                                <input type="button" value="Search" onclick="callForm(subprocess.value);"/>&nbsp;
                            </div>
                        </div>
                    </div>
                    <c:if test="${pageContext.request.method=='POST'}">
                        <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                            <div style="margin-top: 3px;display: block;position: relative;">


                                <div class="addcentr_top">Actions</div>

                                <table cellspacing="1px" cellpadding="8px" id="actionstable" >
                                    <thead>
<!--                                    <th align="left">Serial No</th>-->
                                    <th align="left">Sub-process</th>
                                    <th align="left" style="width: 40%">Action</th>
                                    <th align="left">Action Type</th>
                                    <th align="left">Percentage</th>
                                    <th align="left">Edit</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="recentUL" items="${actiontslist}" varStatus="loop" >
                                            <tr>
<!--                                                <td>${loop.index+1}</td>-->
                                                <td>${recentUL.SubProcessName}</td>  
                                                <td style="width: 40%;">${recentUL.ActionName}</td>
                                                <td>${recentUL.ActionType}</td>
                                                <td>${recentUL.Percentage}</td>
                                                <td>
                                                    <img style="cursor: pointer" onclick="load('editactions.htm?actionid=${recentUL.ActionsId}')" title="Edit" alt="Edit" src="images/edit.png">
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div class="admin_right">
                    <div>
                        <c:if test="${UserBean.writepermission=='1'}">
                            <input type="button" value="Add New" onclick="load('addactions.htm')"/>
                        </c:if>
                        <input type="button" value="Export Actions" onclick="window.location.href = 'actionexport.htm'"/>
                    </div>
                    <div class="quote">
                        <div class="q_inner_top">
                            <p class="q_text">Help</p>
                            <p>Action items are added for each sub process and action item list is maintained here.</p>
                        </div>
                        <div class="q_bottom"></div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

