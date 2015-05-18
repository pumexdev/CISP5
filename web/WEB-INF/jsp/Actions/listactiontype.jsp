<%-- 
    Document   : listactiontype
    Created on : 11 Mar, 2015, 10:55:57 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>

<script type="text/javascript" src="js/Actions/listactiontype.js"></script>

<form:form modelAttribute="ListActionType">
    <div id="message" style="width: 68%;">
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
                        <div class="addcentr_top">Action Types</div>
                        <table cellspacing="1px" cellpadding="8px" id="actionstypetable" >
                            <thead>
                            <th align="left">Serial No</th>
                            <th align="left">Action Type</th>
                            <th align="left">Status</th>
                            <th align="left">Edit</th>
                            </thead>
                            <tbody>
                                <c:forEach var="recentUL" items="${actiontypelist}" varStatus="loop" >
                                    <tr>
                                        <td>${loop.index+1}</td>
                                        <td>${recentUL.ActionType}</td>
                                        <td>
                                            <c:if test="${recentUL.Status==1}">
                                                Active
                                            </c:if>
                                            <c:if test="${recentUL.Status== 2}">
                                                Inactive
                                            </c:if>
                                        </td>
                                        <td>
                                            <img style="cursor: pointer" onclick="load('editactiontype.htm?actiontypeid=${recentUL.ActionTypeId}')" title="Edit" alt="Edit" src="images/edit.png" id="useredit1">
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
                        <input type="button" value="Add New" onclick="load('addactiontype.htm')"/>
                    </c:if>
                    <%--<input type="button" value="Export to Excel" onclick="window.location.href='businessunitexport.htm'"/>--%>
                </div>
                <div class="quote">
                    <div class="q_inner_top">
                        <p class="q_text">Help</p>
                        <p>Each action item should have an action type setup, for action type to appear in the target dashboard.</p>
                    </div>
                    <div class="q_bottom"></div>
                </div>
            </div>
        </div>
    </div>
</div>
