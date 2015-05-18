<%-- 
    Document   : searchlocation
    Created on : Jul 18, 2012, 10:05:38 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>

<script type="text/javascript" src="js/Location/searchlocation.js"></script>

<form:form modelAttribute="SearchLocation">
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
                        <div class="addcentr_top">Centers</div>
                        <c:if test="${fn:length(locationlist)<=0 && pageContext.request.method=='GET'}">
                            <div style="color: red;font-size: medium">
                                No Results found
                            </div>
                        </c:if>
                        <c:if test="${fn:length(locationlist)>0}">
                            <table id="locationtable" cellspacing="1px" cellpadding="8px">
                                <thead>
                                <th align="left">Serial No</th>
                                <th align="left">Business Center</th> 
                                <c:if test="${UserBean.writepermission=='1'}">
                                <th align="left">Edit</th>
                                </c:if>
                                </thead>
                                <tbody>
                                    <c:forEach var="recentUL" items="${locationlist}" varStatus="loop" >
                                        <tr>
                                            <td>${loop.index+1}</td>
                                            <td>${recentUL.BusinessCenterName}</td>
                                            <c:if test="${UserBean.writepermission=='1'}">
                                            <td>
                                                <img id="useredit${loop.index+1}" src="images/edit.png" alt="Edit" title="Edit" onclick="load('editlocation.htm?lid=${recentUL.BusinessCenterId}')" style="cursor: pointer"/>
                                            </td>  
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
            <%-- <div class="adminlist_area">
                    <div class="tablehead">Centers</div>
                    <div class="ad_list_area">
                        <div class="row">
                            <c:if test="${fn:length(locationlist)<=0 && pageContext.request.method=='POST'}">
                                <div style="color: red;font-size: medium">
                                    No Results found
                                </div>
                            </c:if>
                            <c:if test="${fn:length(locationlist)>0}">
                                <table id="locationtable" cellspacing="1px" cellpadding="8px">
                                    <thead>
                                    <th align="left">Serial No</th>
                                    <th align="left">Business Center</th> 
                                    <th align="left">Edit</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="recentUL" items="${locationlist}" varStatus="loop" >
                                            <tr>
                                                <td>${loop.index+1}</td>
                                                <td>${recentUL.BusinessCenterName}</td>
                                                <td>
                                                    <img id="useredit${loop.index+1}" src="images/edit.png" alt="Edit" title="Edit" onclick="load('editlocation.htm?lid=${recentUL.BusinessCenterId}')" style="cursor: pointer"/>
                                                </td>               
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>--%>
            <div class="admin_right">
                <div>
                    <c:if test="${UserBean.writepermission=='1'}">
                    <input type="button" value="Add New" onclick="load('addlocation.htm')"/>
                    </c:if>
                    <%--<input type="button" value="Export to Excel" onclick="window.location.href='businessunitexport.htm'"/>--%>
                </div>
                <div class="quote">
                    <div class="q_inner_top">
                        <p class="q_text">Help</p>
                        <p>A Center must be set up in the system prior to importing the excel data survey.</p></br>
                        <p>Each Center also requires Clients to be set up prior to importing data.   Go to _____ for client display and set up.</p>
                    </div>
                    <div class="q_bottom"></div>
                </div>
            </div>
        </div>
    </div>
</div>