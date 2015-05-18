<%-- 
    Document   : searchclient
    Created on : Sep 12, 2012, 11:24:25 AM
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/Business/searchclient.js"></script>
<form:form modelAttribute="SearchClient">
    <div id="scrollbar1" style="height: 85%;overflow-y: auto">
        <div class="content_slide" style="width: 65%">
            <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                <div class="addcentr_top">Search Clients</div>
                <div class="addcentr_fldarea" style="padding: 10px !important">

                    <div class="row">
                        <div class="errordiv"></div>
                        <div class="left" style="width: 10%">Center</div>
                        <div class="right">
                            <form:select path="location" id="location" onchange="callForm()">
                                <form:option value="">Select</form:option>
                                <form:options  items="${locationlist}"></form:options>                        
                            </form:select>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="admin_right">
            <div>
                <c:if test="${UserBean.writepermission=='1'}">
                <input type="button" value="Add New" onclick="load('searchbusinessunit.htm')"/>
                </c:if>
                <input type="button" value="Export Client" onclick="window.location.href = 'businessunitexport.htm'"/>
            </div>
            <div class="quote">
                <div class="q_inner_top">
                    <p class="q_text">Help</p>
                    <p>A service center Client must be added priori to importing the resource data survey.</p></br>
                    <p>Why?  Maintaining a defined list of center clients controls the integrity of the data import, for resources,volumes and revenue.</p></br>
                    <p>Internal SSC  is a "client".   Each SSC should have an "internal client" set up, to capture time spent internally on SSCoperations</p>
                </div>
                <div class="q_bottom"></div>
            </div>
        </div>

        <div class="content_slide" style="width: 65%">
            <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                <div style="margin-top: 3px;display: block;position: relative;">
                    <c:if test="${pageContext.request.method=='GET'}">
                        <div class="addcentr_top">Clients</div>
                        <c:if test="${fn:length(clientlist)==0}">
                            <div style="color: red;font-size: medium;text-align: center;">
                                No Results found
                            </div>
                        </c:if>
                        <c:if test="${fn:length(clientlist)>0}">           
                            <table cellspacing="1px" cellpadding="8px" id="clientlist">
                                <thead>
                                <th align="left">Serial No</th>
                                <th align="left">Name</th>
                                <th align="left">Short Name</th>
                                <th align="left">Status</th>
                                <c:if test="${UserBean.writepermission=='1'}">
                                <th align="left">Edit</th>
                                </c:if>
                                </thead>
                                <tbody>
                                    <c:forEach var="recentUL" items="${clientlist}" varStatus="loop" >
                                        <tr>
                                            <td align="left">${loop.index+1}</td>
                                            <td align="left">${recentUL.BusinessUnitName}</td>
                                            <td align="left">${recentUL.ShortName}</td>
                                            <td align="left">${recentUL.StatusName}</td> 
                                            <c:if test="${UserBean.writepermission=='1'}">
                                            <td align="left">
                                                <img src="images/edit.png" alt="Edit" title="Edit" onclick="load('searchbusinessunit.htm?buid=${recentUL.BusinessUnitId}')" style="cursor: pointer"/>
                                            </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </c:if>
                    <c:if test="${pageContext.request.method=='POST'}">
                        <div class="addcentr_top">Clients</div>
                        <c:if test="${fn:length(clientlist)==0}">
                            <div style="color: red;font-size: medium;text-align: center;">
                                No Results found
                            </div>
                        </c:if>
                        <c:if test="${fn:length(clientlist)>0}">           
                            <table cellspacing="1px" cellpadding="8px" id="clientlist">
                                <thead>
                                <th align="left">No.</th>
                                <th align="left">Name</th>
                                <th align="left">Short Name</th>
                                <th align="left">Status</th>
                                <c:if test="${UserBean.writepermission=='1'}">
                                <th align="left">Edit</th>
                                </c:if>
                                </thead>
                                <tbody>
                                    <c:forEach var="recentUL" items="${clientlist}" varStatus="loop" >
                                        <tr>
                                            <td align="left">${loop.index+1}</td>
                                            <td align="left">${recentUL.BusinessUnitName}</td>
                                            <td align="left">${recentUL.ShortName}</td>
                                            <td align="left">${recentUL.StatusName}</td> 
                                            <c:if test="${UserBean.writepermission=='1'}">
                                            <td align="left">
                                                <img src="images/edit.png" alt="Edit" title="Edit" onclick="load('searchbusinessunit.htm?buid=${recentUL.BusinessUnitId}')" style="cursor: pointer"/>          
                                            </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </fieldset>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</form:form>