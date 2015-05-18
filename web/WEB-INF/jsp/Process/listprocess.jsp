<%-- 
    Document   : listprocess
    Created on : Sep 9, 2012, 10:36:33 AM
    Author     : Vishnu
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $("#scrollbar1").tinyscrollbar();
</script>

<div id="scrollbar1">
    <div class="scrollbar">
        <div class="thumb">
            <div class="end"></div>
        </div>
    </div>
    <div class="viewport">
        <div class="overview">
            <div class="admin_left" style="padding: 10px;">
                <div class="addcentr_area" style="float: left;margin-left: 100px;box-shadow: 0 0 3px 3px #EEEEEE;width: 85%">
                    <c:if test="${fn:length(processandsubprocess)==0}">
                        <div style="color: red;font-size: medium">
                            No Results found
                        </div>
                    </c:if>
                    <div class="datagrid">
                        <c:if test="${fn:length(processandsubprocess)>0}">
                            <table cellspacing="1px" cellpadding="8px" id="processandsubprocess" >
                                <thead>
                                <th>No.</th>
                                <th>Process</th>
                                <th>SubProcesses</th>
                                </thead>
                                <tbody>
                                    <c:forEach var="recentUL" items="${processandsubprocess}" varStatus="loop" >
                                        <tr>
                                            <td>${loop.index+1}</td>
                                            <td>${recentUL.process}</td>
                                            <td>
                                                <ul style="list-style: none">
                                                    <c:if test="${fn:length(recentUL.subProcess)>0}">
                                                        <c:forEach var="subProcess" items="${recentUL.subProcess}" varStatus="loop" >
                                                            <li style="color: black;font-weight: normal">${subProcess}</li>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${fn:length(recentUL.subProcess)==0}">
                                                        <li style="color: black;font-weight: normal">No Subprocesses</li>
                                                    </c:if>
                                                </ul>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
