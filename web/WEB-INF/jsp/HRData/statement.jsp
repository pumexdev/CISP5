<%-- 
    Document   : addstatement
    Created on : Aug 27, 2012, 2:31:34 PM
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/HRData/statement.js"></script>

<div id="scrollbar1">
    <div class="scrollbar">
        <div class="thumb">
            <div class="end"></div>
        </div>
    </div>
    <div class="viewport">
        <div class="overview">
            <div class="content_slide" id="locdiv" style="width:67%">
                <div class="addcentr_area" style="width: 100%;border: 1px solid #BEBDC6;">
                    <c:if test="${param.reopen=='true'}">
                        <div class="success_div" id="message">
                            <div id="message" class="success">Statement reopened successfully</div>
                        </div>
                    </c:if>
                    <c:if test="${param.reopen=='false'}">
                        <div class="failed_div" id="message">
                            <div id="message" class="failed">Error occured while reopening Statement</div>
                        </div>
                    </c:if>
                    <c:if test="${param.close=='true'}">
                        <div class="success_div">
                            <div id="message" class="success">Statement closed successfully</div>
                        </div>
                    </c:if>
                    <c:if test="${param.close=='false'}">
                        <div class="failed_div" id="message">
                            <div id="message" class="failed">Error occured while closing Statement</div>
                        </div>
                    </c:if>
                    <div class="addcentr_top">Statement dates</div>
                    <table id="statementlist">
                        <thead>
                        <th align="left">#</th>
                        <th align="left">Statement Date</th>
                        <th align="left">Status</th>
                        <c:if test="${UserBean.writepermission=='1'}">
                        <th align="left">Edit</th>
                        </c:if>
                        </thead>
                        <tbody>
                            <c:forEach var="statement" items="${statementList}" varStatus="loop" >
                                <tr>
                                    <td width="20px">${loop.index+1}</td>
                                    <td>${statement.date}</td>
                                    <c:if test="${statement.statementStatus==1}">
                                        <td>Open</td>
                                    </c:if>
                                    <c:if test="${statement.statementStatus==2}">
                                        <td>Closed</td>
                                    </c:if>
                                    <c:if test="${statement.statementStatus==3}">
                                        <td>Reopened</td>
                                    </c:if>
                                        <c:if test="${UserBean.writepermission=='1'}">
                                    <td>
                                        <img src="images/edit.png" onclick="editStatement('${statement.date}', '${statement.statementStatus}')"/>
                                    </td>
                                        </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="admin_right">
                <div>
                    <c:if test="${UserBean.writepermission=='1'}">
                    <input type="button" value="Add New" onclick="load('openstatement.htm')"/>
                    </c:if>
                </div>
                <div class="quote">
                    <div class="q_inner_top">
                        <p class="q_text">Help</p>
                        <p>Statements cannot be deleted from the system once they are created.</p>
                        <p>When creating a statement date, all centers should submit updated data, to preserve dashboard statement comparability.</p>
                        <p>When the statement data is finalized, close the statement for editing at Statement Close</p>
                    </div>
                    <div class="q_bottom"></div>
                </div>
            </div>
        </div>
    </div>
</div>