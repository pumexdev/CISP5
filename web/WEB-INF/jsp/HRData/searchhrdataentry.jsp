<%-- 
    Document   : searchhrdataentry
    Created on : Jul 24, 2012, 10:38:25 AM
    Author     : user
--%>
<%
String businesscenterid = request.getParameter("business_center_id"+request.getParameter("no"));
String entry_date = request.getParameter("entry_date"+request.getParameter("no"));
request.setAttribute("centerid",businesscenterid);
request.setAttribute("selected_date",entry_date);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="">
    <c:if test="${pageContext.request.method=='POST'}">
        <div class="admin_left" style="width: 98%">
            <div class="adminlist_area">
                <div class="top_searchbar">
                    <span>HR Data</span>
                </div>
                <div class="ad_list_area">
                    <table cellspacing="1px" cellpadding="8px" id="hrlist${param.no}">
                        <thead>
                        <th align="left">No.</th><th align="left">Name</th><th align="left">Sub-Process</th><th align="left">Client</th><th align="left">FTE</th><th align="left">Labor Cost</th><th align="left">Edit</th><th align="left">Delete</th>
                        </thead>
                        <tbody>
                            <c:forEach var="recentUL" items="${hrlist}" varStatus="loop" >
                                <tr>
                                    <td>${loop.index+1}</td>
                                    <td>${recentUL.EmployeeName}</td>
                                    <td>${recentUL.SubProcessName}</td>
                                    <td>${recentUL.BusinessUnitName}</td>
                                    <td>${recentUL.FTE}</td>
                                    <td>
                                        <fmt:formatNumber value="${recentUL.Cost}" type="number" groupingUsed="TRUE"></fmt:formatNumber>
                                        <c:out value="${compensationvalue}"/>
                                    </td>
                                    <td>
                                        <img src="images/edit.png" alt="Edit" title="Edit" onclick="$('#add').empty();$('#edit').show();$('#edit').load('edithrdata.htm?hrid=${recentUL.EmployeeEntryId}&enid=${recentUL.EntryId}');$('#tabs li a:first').click()"/>
                                    </td>
                                    <td><img src="images/cross.png" alt="Delete" onclick="deleteEntry('${recentUL.EmployeeEntryId}')"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
</div>
