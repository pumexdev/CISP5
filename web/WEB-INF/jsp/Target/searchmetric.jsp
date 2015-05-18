<%-- 
    Document   : searchmetric
    Created on : Mar 11, 2015, 10:56:33 AM
    Author     : Vishnu AU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript" src="js/Target/searchmetric.js"></script>

<div id="scroll" style="overflow-y:auto;height: 80%;"><!-- class="Jscroll-pane"-->
    <div class="content_slide" style="width: 65%">
        <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
            <form:form modelAttribute="SearchMetric">                
                <div class="addcentr_top" id="divhead">Metric</div>
                <c:if test="${fn:length(metriclist)<=0}">
                    <div style="color: red;font-size: medium">
                        No Process found
                    </div>
                </c:if>
                <c:if test="${fn:length(metriclist)>0}">
                    <table id="metriclist">
                        <thead>
                        <th align="left">Serial No</th>
                        <th align="left">Metric</th>
                        <th align="left">Status</th>
                        <c:if test="${UserBean.writepermission=='1'}">
                        <th align="left">Edit</th>
                        </c:if>
                        </thead>
                        <tbody>
                            <c:forEach var="metric" items="${metriclist}" varStatus="loop">
                                <tr>
                                    <td align="left">${loop.index+1}</td>
                                    <td align="left">${metric.Metric}</td>
                                    <td align="left">
                                        <c:if test="${metric.Status=='1'}">Active</c:if>
                                        <c:if test="${metric.Status=='2'}">Inactive</c:if>
                                        </td>
                                        <c:if test="${UserBean.writepermission=='1'}">
                                        <td align="left">
                                            <img id="metricedit${loop.index+1}" onclick="load('editmetric.htm?mid=${metric.MetricId}')"src="images/edit.png" alt="Edit" title="Edit"  style="cursor: pointer"/>
                                    </td>
                                        </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </form:form>
        </div>
    </div>
    <div class="admin_right" style="margin-top: 7px;">
        <div>
            <c:if test="${UserBean.writepermission=='1'}">
            <input type="button" value="Add New" onclick="load('addmetric.htm')"/>   
            </c:if>
        </div>
        <div class="quote">
            <div class="q_inner_top">
                <p class="q_text">Help</p>
                
            </div>
            <div class="q_bottom"></div>
        </div>
    </div>
</div>
