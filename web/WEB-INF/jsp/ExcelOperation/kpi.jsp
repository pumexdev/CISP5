<%--
    Document   : addActualReference
    Created on : Aug 16, 2012, 10:43:53 AM
    Author     : user
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/kpi/kpivalidations.js"></script>
<script type="text/javascript" src="js/kpi/actualreference.js"></script>

<div id="scrollbar1">
    <div class="scrollbar">
        <div class="thumb">
            <div class="end"></div>
        </div>
    </div>
    <div class="viewport">
        <div class="overview">
            <form:form modelAttribute="KPI">
                <div class="content_slide" style="width: 67%">
                    <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                        <c:if test="${pageContext.request.method=='GET' && param.add=='true'}">
                            <div class="success_div">                      
                                <div id="message" class="success">KPI successfully saved</div>
                            </div>
                        </c:if>
                        <c:if test="${pageContext.request.method=='GET' && param.add=='false'}">
                            <div class="failed_div">                       
                                <div id="message" class="failed">KPI insertion failed</div>
                            </div>
                        </c:if>
                        <c:if test="${pageContext.request.method=='POST' && fn:length(kpiList)==0 && fn:length(businessunitnames)>0}">
                            <div class="failed_div">                       
                                <div id="message" class="failed">No results found</div>
                            </div>
                        </c:if>
                        <c:if test="${pageContext.request.method=='POST' && fn:length(kpiList)>0}">
                            <div class="failed_div">                       
                                <div id="message" class="failed">Edit KPI data</div>
                            </div>
                        </c:if>
                        <c:if test="${pageContext.request.method=='POST' && fn:length(businessunitnames)==0}">
                            <div class="failed_div">                       
                                <div id="message" class="failed">No Clients added for this Business Center</div>
                            </div>
                        </c:if>                   

                        <div class="addcentr_top">Add/Edit KPI</div>
                        <div class="addcentr_fldarea">
                            <div class="row">
                                <div id="invalid-businesscenter" class="errordiv"></div>
                                <div class="left">Center<font class="error" style="color: red;">*</font></div>
                                <div class="right">
                                    <c:if test="${UserBean.userlevel!='4'}">
                                        <form:select path="businesscenter" id="businesscenter" cssClass="required" onchange="$('.success_div').hide();$('.failed_div').hide()">
                                            <form:option value="">Select</form:option>
                                            <form:options items="${locationlist}"></form:options>
                                        </form:select>
                                    </c:if>
                                    <c:if test="${UserBean.userlevel=='4'}">
                                        <form:hidden path="businesscenter" value="${UserBean.userlevel}"/>
                                    </c:if>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-fromdate" class="errordiv"></div>
                                <div class="left">Date as of<font class="error" style="color: red;">*</font></div>
                                <div class="right">
                                    <form:select id="fromdate" path="fromdate" cssClass="required">
                                        <form:option value="">Select</form:option>
                                        <form:options items="${opendates}"></form:options>
                                    </form:select>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="addbottom_fldarea">
                            <div class="addbottom_left">
                                <c:if test="${pageContext.request.method=='GET'}">
                                    <!--input type="reset" value="Cancel" onclick="cancel('statementview.htm')"/-->
                                    <input type="button" value="Search" id="save" onclick="$('#KPI').submit()"/>                                    
                                </c:if>
                                <input type="button" value="Reset" onclick="load('kpi.htm')"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="admin_right" style="margin-top: 7px;">
                    <div>
                        <input type="button" value="Upload" class="im_ex_button" name="" onclick="upload('volkpiuploader.htm')">
                    </div>
                    <div class="clear"></div>        
                    <div class="quote">
                        <div class="q_inner_top">
                            <p class="q_text">Help</p>
                        </div>
                        <div class="q_bottom"></div>
                    </div>
                </div>

                <c:if test="${pageContext.request.method=='POST'}">
                    <form:hidden path="operation" value="2" id="operation"/>
                </c:if>
                <c:if test="${pageContext.request.method=='GET'}">
                    <form:hidden path="operation" value="1" id="operation"/>
                </c:if>


                <c:if test="${pageContext.request.method=='POST' && fn:length(businessunitnames)>0}">
                    <div class="content_slide" style="height: auto !important;width: 67%">
                        <div class="addcentr_area" style="width: auto !important">
                            <div class="addcentr_top">KPI<span style="color: red;float: right;font-weight: normal">* Required</span></div>
                            <div class="addcentr_fldarea" id="kpidiv" style="overflow:auto;">
                                <table id="volume">
                                    <tr>
                                        <td><font class="text" style="font-weight: bold">Sub-Process</font></td>
                                        <td><font class="text" style="font-weight: bold">KPI</font></td>
                                        <td><font class="text" style="font-weight: bold">Center Average</font></td>
                                        <c:forEach var="client" items="${businessunitnames}">
                                            <td>
                                                <font class="text" style="font-weight: bold">${client.value}</font>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <c:set var="flag" value="${0}"/>

                                    <c:forEach items="${kpiList}" var="kpi" varStatus="kpiindex">
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td id="invalid-centeravg${kpiindex.index+1}"></td>
                                            <c:set var="clientindex" value="${1}"/>
                                            <c:forEach var="client" items="${businessunitnames}">
                                                <td id="invalid-clientkpi${kpiindex.index+1}${clientindex}"></td>
                                                <c:set var="clientindex" value="${clientindex+1}"/>
                                            </c:forEach>
                                        </tr>
                                        <tr>
                                            <td width="120px">${kpi.SubProcessName}</td>
                                            <td width="120px">
                                                ${kpi.KPIDescription}
                                                <%
                                                    Map map = new LinkedHashMap();
                                                    map = (Map) pageContext.getAttribute("kpi");
                                                    String kpiid = String.valueOf(map.get("KPIId"));
                                                    Map kpiMap = new LinkedHashMap();
                                                    kpiMap = (Map) request.getAttribute("KPIs");
                                                    pageContext.setAttribute("individualVolume", kpiMap.get(kpiid));
                                                %>

                                            </td>
                                            <td>
                                                <input type="text" style="min-width:5px !important;text-align: right;" size="10" maxlength="12" value="${individualVolume['centerAvg']}%" name="centeravg1" id="centeravg${kpiindex.index+1}" onchange="onChange(this.id, this.value, 'ccenteravg${kpiindex.index+1}')" onblur="onChange(this.id, this.value, 'ccenteravg${kpiindex.index+1}')" />
                                                <input type="hidden" value="${individualVolume['centerAvg']}" name="centeravg" id="ccenteravg${kpiindex.index+1}"/>
                                            </td>
                                            <c:set var="clientindex1" value="${1}"/>
                                            <c:forEach var="client" items="${businessunitnames}">
                                                <td>
                                                    <fmt:formatNumber var="kpiscore" value="${individualVolume[client.key]}" type="number" groupingUsed="TRUE" maxIntegerDigits="10" maxFractionDigits="3"></fmt:formatNumber>
                                                    <%--fmt:formatNumber var="kpiscore" value="${individualVolume[client.key]}" type="PERCENT" maxIntegerDigits="3" maxFractionDigits="3"></fmt:formatNumber--%>
                                                    <input type="text" style="min-width:5px !important;text-align: right;" size="10" maxlength="12" value="${kpiscore}%" name="clientkpi1" id="clientkpi${kpiindex.index+1}${clientindex1}" onchange="onChange(this.id, this.value, 'cclientkpi${kpiindex.index+1}${clientindex1}')" onblur="onChange(this.id, this.value, 'cclientkpi${kpiindex.index+1}${clientindex1}')" />
                                                    <%--input type="hidden" value="${individualVolume[client.key]}" name="clientkpi" id="cclientkpi${kpiindex.index+1}${clientindex1}"/--%>
                                                    <input type="hidden" value="${kpiscore}" name="clientkpi" id="cclientkpi${kpiindex.index+1}${clientindex1}"/>
                                                </td>
                                                <c:set var="clientindex1" value="${clientindex1+1}"/>
                                            </c:forEach>
                                            <td></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div class="addbottom_fldarea"><!-- style="border-top:none;background: none"-->
                                <c:if test="${pageContext.request.method=='POST'}">
                                    <div class="addbottom_left">
                                        <input type="button" value="Save" id="save" onclick="$('#KPI').submit()" style="margin-left: 10px"/>
                                    </div>
                                    <div class="addbottom_left">
                                        <input type="reset" value="Cancel" onclick="kpicancel()"/>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:if>
            </form:form>
        </div>
    </div>
</div>