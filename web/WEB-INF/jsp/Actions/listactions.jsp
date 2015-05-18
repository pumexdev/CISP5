<%-- 
    Document   : listactions
    Created on : 12 Mar, 2015, 11:41:18 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<script type="text/javascript" src="js/Actions/listactions.js"></script>
<form:form modelAttribute="ListActions">

</form:form>
<div id="scrollbar1">
    <div class="scrollbar">
        <div class="thumb">
            <div class="end"></div>
        </div>
    </div>
    <div class="viewport">
        <div class="overview">
            <div class="admin_left" style="padding: 10px">
                <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                    <div style="margin-top: 3px;display: block;position: relative;">
                        <div class="addcentr_top">Actions</div>
                        <table cellspacing="1px" cellpadding="8px" id="actionstable" >
                            <thead>
                            <th align="left">Serial No</th>
                            <th align="left">Sub-process</th>
                            <th align="left">Action</th>
                            <th align="left">Action Type</th>
                            <th align="left">Percentage</th>
                            <th align="left">Edit</th>
                            </thead>
                            <tbody>
                                <c:forEach var="recentUL" items="${actiontslist}" varStatus="loop" >
                                    <tr>
                                        <td>${loop.index+1}</td>
                                        <td>${recentUL.SubProcessName}</td>  
                                        <td>${recentUL.ActionName}</td>
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
            </div>
            <div class="admin_right">
                <div>
                    <c:if test="${UserBean.writepermission=='1'}">
                        <input type="button" value="Add New" onclick="load('addactions.htm')"/>
                    </c:if>
                    <%--<input type="button" value="Export to Excel" onclick="window.location.href='businessunitexport.htm'"/>--%>
                </div>
                <div class="quote">
                    <div class="q_inner_top">
                        <p class="q_text">Help</p>
                        <p>Action items are added for each sub process and action item list is maintained here.</p>
                    </div>
                    <div class="q_bottom"></div>
                </div>
            </div>
        </div>
    </div>
</div>