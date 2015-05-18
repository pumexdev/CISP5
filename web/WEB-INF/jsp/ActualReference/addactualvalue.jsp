<%--
    Document   : addActualReference
    Created on : Aug 16, 2012, 10:43:53 AM
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/ActualReference/addactualvalue.js"></script>
<script type="text/javascript" src="js/ActualReference/actualreference.js"></script>

<div id="scrollbar1">
    <div class="scrollbar">
        <div class="thumb">
            <div class="end"></div>
        </div>
    </div>
    <div class="viewport">
        <div class="overview">
            <form:form modelAttribute="ActualValue">

                <div class="content_slide" style="width: 67%">
                    <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                        <c:if test="${pageContext.request.method=='POST' && fn:length(ActualValue)==0 && fn:length(businessunitnames)>0}">
                            <div class="failed_div">                      
                                <div id="message" class="failed">No results found</div>
                            </div>
                        </c:if>
                        <c:if test="${pageContext.request.method=='POST' && fn:length(ActualValue)>0}">
                            <div class="failed_div">                      
                                <div id="message" class="failed">Edit Volume/Revenue data</div>
                            </div>
                        </c:if>
                        <c:if test="${pageContext.request.method=='POST' && fn:length(businessunitnames)==0}">
                            </br>
                            <div class="failed_div">                      
                                <div id="message" class="failed">No Clients added for this Business Center</div>
                            </div>
                        </c:if>
                        <c:if test="${pageContext.request.method=='GET' && param.add=='true'}">
                            <div class="success_div">                      
                                <div id="message" class="success">Actual Reference successfully Added</div>
                            </div>
                        </c:if>
                        <c:if test="${pageContext.request.method=='GET' && param.add=='false'}">
                            <div class="failed_div">                       
                                <div id="message" class="failed">Actual Reference addition failed</div>
                            </div>
                        </c:if>

                        <c:if test="${pageContext.request.method=='GET' && param.edit=='true'}">
                            <div class="success_div">                      
                                <div id="message" class="success">Actual Reference successfully edited</div>
                            </div>
                        </c:if>
                        <c:if test="${pageContext.request.method=='GET' && param.edit=='false'}">
                            <div class="failed_div">                       
                                <div id="message" class="failed">Actual Reference updation failed</div>
                            </div>
                        </c:if>

                        <div class="addcentr_top">Search Volume/Revenue/KPI</div>
                        <div class="addcentr_fldarea">
                            <div class="row">
                                <div id="invalid-paramtypecode" class="errordiv"></div>
                                <div class="left">Parameter<font class="error" style="color: red;">*</font></div>
                                <div class="right">
                                    <form:select path="paramtypecode" id="paramtypecode" cssClass="required"  onclick="kpiload(this.value)">
                                        <form:option value="">Select</form:option>
                                        <form:options items="${paramlist}"></form:options>
                                    </form:select>
                                </div>
                                <div class="clear"></div>
                            </div>
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
                                    <!--input type="reset" value="Cancel" onclick="cancel()"/-->
                                    <input type="button" value="Search" id="save" onclick="$('#ActualValue').submit()"/>
                                </c:if>
                                <input type="button" value="Reset" onclick="load('addactualvalue.htm')"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="admin_right">
                    <div>
                        <c:if test="${UserBean.writepermission=='1'}">
                        <input type="button" value="Upload" class="im_ex_button" name="" onclick="upload('volkpiuploader.htm')">
                        </c:if>
                    </div>
                    <div class="clear"></div>
                    <div class="quote">
                        <div class="q_inner_top" style="height: 200px;overflow-y: scroll">
                            <p class="q_text">Help</p>
                            <p style="font-size: 12px;">Revenue is annualized</p>
                            <p style="font-size: 12px;">Why?  Annualizing volumes allows for easier comparison to APQC benchmarks.  </p></br>
                            <p style="font-size: 12px;">Revenue is used in the Center dashboards, to measure the size of the client service team relative to the size of the Client.</p></br>
                            <p style="font-size: 12px;">To view the dashboard output, go to the Location dashboard, and select "Efficiency by Client assignment"</p></br>
                            <p style="font-size: 12px;">Revenue should include intercompany sales</p>
                            <p style="font-size: 12px;">Why?  Revenue is measuring the size of the organization relative to the production, whether that is for external or internal sales   </p></br>

                            <p class="q_text">New Volume</p>
                            <p style="font-size: 12px;">Volumes should be annualized</p>
                            <p style="font-size: 12px;">Why?  Annualizing volumes allows for easier comparison to APQC benchmarks.  However, annualizing is not a system requirement.  The Global Administrator should determine the standard.</p></br>
                            <p style="font-size: 12px;">Volumes are used in the Location and Process dashboards, at the sub-process level .    To see the output of volume per FTE, generate a sub-process level dashboard.  </p></br>
                            <p style="font-size: 12px;">Not all sub-processes have a volume data field.</p>
                            <p style="font-size: 12px;">Why?  Some processes are not volume driven. The sub-processes which do have volume fields have corresponding APQC benchmarks  </p></br>
                        </div>
                        <div class="q_bottom"></div>
                    </div>
                </div>

                <c:if test="${pageContext.request.method=='POST' && param.paramtypecode==1}">
                    <c:set var="paramvalue" value="Revenue"/>
                </c:if>
                <c:if test="${pageContext.request.method=='POST' && param.paramtypecode==2}">
                    <c:set var="paramvalue" value="Volume"/>
                </c:if>
                <c:if test="${pageContext.request.method=='POST' && fn:length(businessunitnames)>0}">
                    <c:if test="${pageContext.request.method=='POST' && fn:length(businessunitnames)>0}">
                        <div class="content_slide" style="padding-top: 10px !important;width: 67%"><!--height: auto !important;width: auto !important-->
                            <div class="addcentr_area" style="width: auto !important;">
                                <div class="addcentr_top">${paramvalue} <span style="color: red;float: right;font-weight: normal">* Required</span></div>
                                <div class="addcentr_fldarea" id="actualdiv" style="overflow:auto;min-height: 130px;"><!--width:1000px;overflow:scroll;-->
                                    <c:if test="${param.paramtypecode==1}">
                                        <!--Revenue-->
                                        <table id="revenue">
                                            <tr>
                                                <td>#</td>
                                                <c:forEach var="client" items="${businessunitnames}">
                                                    <td><font class="text" style="width: auto;font-weight: bold">${client.value}</font></td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <c:forEach var="client" items="${businessunitnames}"  varStatus="loop">
                                                    <td id="invalid-clientrevenue${loop.index+1}"></td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <td><font class="text" style="width: auto;font-weight: bold">Revenue</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                                                <c:if test="${fn:length(ActualValue)==0}">
                                                    <c:forEach var="client" items="${businessunitnames}" varStatus="loop">
                                                        <td>
                                                            <input type="text" name="clientrevenue1" style="min-width:5px !important;text-align: right;" size="18" maxlength="17" id="clientrevenue${loop.index+1}" onkeypress="onChange(this.id, this.value, 'cclientrevenue${loop.index+1}');" onblur="onChange(this.id, this.value, 'cclientrevenue${loop.index+1}')" onchange="onChange(this.id, this.value, 'cclientrevenue${loop.index+1}')"/><!--formsubmit(event)-->
                                                            <input type="hidden" name="clientrevenue" style="min-width:5px" size="8" id="cclientrevenue${loop.index+1}" />
                                                        </td>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${fn:length(ActualValue)>0}">
                                                    <c:forEach var="client" items="${businessunitnames}" varStatus="loop">
                                                        <td>
                                                            <fmt:formatNumber var="revvalue" value="${ActualValue[client.key]}" type="number" groupingUsed="TRUE" maxFractionDigits="2"></fmt:formatNumber>
                                                            <%--input type="text" value="${ActualValue[client.key]}" name="clientrevenue" style="min-width:5px" size="8" id="clientrevenue${loop.index+1}" onkeypress="formsubmit(event)"/><--class="required digits" --%>
                                                            <input type="text" value="${revvalue}" name="clientrevenue1" style="min-width:5px !important;text-align: right;" size="18" maxlength="17" id="clientrevenue${loop.index+1}" onkeypress="onChange(this.id, this.value, 'cclientrevenue${loop.index+1}');" onblur="onChange(this.id, this.value, 'cclientrevenue${loop.index+1}')"  onchange="onChange(this.id, this.value, 'cclientrevenue${loop.index+1}')"/>
                                                            <input type="hidden" value="${revvalue}" name="clientrevenue" style="min-width:5px" size="8" id="cclientrevenue${loop.index+1}" />
                                                        </td>
                                                    </c:forEach>
                                                </c:if>
                                            </tr>
                                        </table>
                                    </c:if>
                                    <c:if test="${param.paramtypecode==2}">
                                        <!--Volume-->
                                        <table id="volume">
                                            <tr>
                                                <td><font class="text" style="font-weight: bold">Sub-Process</font></td>
                                                <td><font class="text" style="font-weight: bold">Volume description</font></td>
                                                <c:forEach var="client" items="${businessunitnames}">
                                                    <td><font class="text" style="width: auto;font-weight: bold">${client.value}</font><form:hidden path="clientsvolume" value="${client.key}"/></td>
                                                </c:forEach>
                                            </tr>
                                            <c:set var="flag" value="${0}"/>
                                            <c:forEach var="subprocess" items="${subprocesslist}">
                                                <tr>
                                                    <td></td>
                                                    <td id="invalid-description${flag+1}"></td>
                                                    <c:forEach var="client" items="${businessunitnames}">
                                                        <td id="invalid-clientvolume${volumecount1+1}"></td>
                                                        <c:set var="volumecount1" value="${volumecount1+1}"/>
                                                    </c:forEach>
                                                </tr>
                                                <tr>
                                                    <td width="20%">
                                                        <font class="text">${subprocess.value}</font>
                                                    </td>

                                                    <c:set var="individualVolume" value="${ActualValue[subprocess.key]}"/>

                                                <input type="hidden" value="${individualVolume}"/>
                                                <c:set var="voldescid" value="${individualVolume['VolDescId']}"/>

                                                <td>
                                                    <c:forEach var="descriptiondata" items="${descriptionlist}">
                                                        <c:if test="${descriptiondata.key==voldescid}">
                                                            ${descriptiondata.value}
                                                            <form:hidden path="description" value="${descriptiondata.key}"/>
                                                        </c:if>                                        
                                                    </c:forEach>
                                                </td>                        
                                                <%--form:hidden path="description" id="descriptionvalue${flag+1}" value="${voldescid}"/--%> 
                                                <c:if test="${fn:length(individualVolume)==0}">
                                                    <c:forEach var="client" items="${businessunitnames}"  varStatus="volloop">
                                                        <td>
                                                            <input type="text" style="min-width:5px !important;text-align: right;" size="18" maxlength="17" name="clientvolume1" id="clientvolume${volumecount1}" onkeypress="onChange(this.id, this.value, 'cclientvolume${volumecount1}');" onblur="onChange(this.id, this.value, 'cclientvolume${volumecount1}')"  onchange="onChange(this.id, this.value, 'cclientvolume${volumecount1}')"/><!-- class="required digits"-->
                                                            <input type="hidden" style="min-width:5px" size="8" name="clientvolume" id="cclientvolume${volumecount1}"/>
                                                        </td>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${fn:length(individualVolume)>0}">
                                                    <c:set var="volcount" value="0"/>
                                                    <c:forEach var="businessunit" items="${businessunitnames}"  varStatus="volloop1">
                                                        <td>
                                                            <fmt:formatNumber var="volvalue" value="${individualVolume[businessunit.key]}" type="number" groupingUsed="TRUE" maxFractionDigits="2"></fmt:formatNumber>
                                                            <input type="text" style="min-width:5px !important;text-align: right;" size="18" maxlength="17" name="clientvolume1" id="clientvolume${volumecount1}" value="${volvalue}"  onkeypress="onChange(this.id, this.value, 'cclientvolume${volumecount1}');" onblur="onChange(this.id, this.value, 'cclientvolume${volumecount1}')"  onchange="onChange(this.id, this.value, 'cclientvolume${volumecount1}')"/><!-- class="required digits"-->
                                                            <input type="hidden" value="${volvalue}" style="min-width:5px" size="8" name="clientvolume" id="cclientvolume${volumecount1}"/>
                                                        </td>
                                                    </c:forEach>
                                                    <c:set var="flag" value="${flag+1}"/>
                                                </c:if>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                </div>
                                <div class="addbottom_fldarea" ><!--style="border-top:none;background: none"-->
                                    <c:if test="${pageContext.request.method=='POST'}">
                                        <c:if test="${fn:length(businessunitnames)>0}">
                                            <c:if test="${UserBean.writepermission=='1'}">
                                            <input type="button" value="Save" id="save" style="margin-left:10px " onclick="$('#ActualValue').submit()"/>
                                            </c:if>
                                        </c:if>
                                            <input type="reset" value="Cancel" onclick="volrevcancel()"/>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:if>
                <form:hidden path="operation" id="operation"/>
                <input type="hidden" id="volrev" value="${param.paramtypecode}"/>
                <input type="hidden" id="actualvalues"/><%-- value="${ActualValue}"--%>
                <input type="hidden" id="clientlength" value="${fn:length(businessunitnames)}"/>
            </form:form>
        </div>
    </div>
</div>