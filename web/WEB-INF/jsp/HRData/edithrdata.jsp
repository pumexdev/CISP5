<%-- 
    Document   : addnewBU
    Created on : Jul 26, 2012, 10:21:35 AM
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="js/HRData/edithrdata.js" ></script>
<script src="js/HRData/hrscripts.js" ></script>

<div class="">
    <form:form modelAttribute="EditHRdata" method="post" action="edithrdata.htm?hrid=${param.hrid}&enid=${param.enid}">
        <div class="addcentr_area" style="width: 100%">
            <c:if test="${pageContext.request.method=='GET' && param.add=='true'}">
                <div class="success_div">
                    <div style="width: 45%" id="message" class="success">HR Data successfully Edited</div>
                </div>
            </c:if>
            <c:if test="${pageContext.request.method=='GET' && param.add=='false'}">
                <div class="failed_div">
                    <div style="width: 45%" id="message" class="failed">HR Data updation failed</div>
                </div>
            </c:if>
            <div class="addcentr_top"> Edit Employee HR Data </div>
            <div class="addcentr_fldarea" style="height: 300px">
                <div class="row">
                    <div class="left" style="margin-right: 100px;padding: 5px;">
                        <div class="row">
                            <div id="invalid-date1" class="errordiv"></div>
                            <div class="left">
                                Date<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <form:select path="date" id="date1" onchange="$('#message').hide()">
                                    <form:option value="">Select</form:option>
                                    <form:options items="${datelist}"></form:options>
                                </form:select>
                            </div>
                        </div>
                    </div>
                    <div class="right">
                        <div class="row">
                            <div id="invalid-location1" class="errordiv"></div>
                            <div class="left" >
                                Center<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <c:if test="${UserBean.userlevel!='4'}">
                                    <form:select path="location" id="location1" onchange="getClients(this.value)">
                                        <form:option value="">Select</form:option>
                                        <form:options items="${businesscenterlist}"></form:options>
                                    </form:select>
                                </c:if>
                                <c:if test="${UserBean.userlevel=='4'}">
                                    <form:hidden path="location" value="${UserBean.userlevel}"/>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="left" class="left" style="margin-right: 100px;padding: 5px;">
                        <div class="row">
                            <div class="errordiv" id="invalid-name1"></div>
                            <div class="left">
                                Name<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <form:input type="text" path="name" id="name1" />
                            </div>
                        </div>
                    </div>
                    <div class="right">
                        <div class="row">
                            <div id="invalid-emp_type1" class="errordiv"></div>
                            <div class="left">
                                Employee Type<font class="error" style="color: red;">*</font>
                            </div>                
                            <div class="right">
                                <form:select path="emp_type" id="emp_type1">
                                    <form:option value="">Select</form:option>
                                    <form:options items="${employeetype}"></form:options>
                                </form:select>                        
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="left" style="margin-right: 100px;padding: 5px;">
                        <div class="row">
                            <div id="invalid-grade1" class="errordiv"></div>
                            <div class="left">
                                Grade<font class="error" style="color: red;">*</font>
                            </div>       
                            <div class="right">
                                <form:input type="text" id="grade1" path="grade" maxlength="20"/>
                            </div>
                        </div>
                    </div>
                    <div class="right">
                        <div class="row">
                            <div id="invalid-tier1" class="errordiv"></div>
                            <div class="left">
                                Tier&nbsp;<font class="error" style="color: red;">*</font>
                            </div>             
                            <div class="right">
                                <form:select path="tier" id="tier1">
                                    <form:option value="">Select</form:option>
                                    <form:options items="${tierlist}"></form:options>
                                </form:select>                        
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="left" style="margin-right: 100px;padding: 5px;">
                        <div class="row">
                            <div class="errordiv" id="invalid-process1"></div>
                            <div class="left">
                                Process<font class="error" style="color: red;">*</font>
                            </div>              
                            <div class="right">
                                <form:select path="process" id="process1" onchange="updateSubprocess(this.value,'subprocess')">
                                    <form:option value="">Select</form:option>
                                    <form:options items="${processlist}"></form:options>
                                </form:select>                        
                            </div>
                        </div>
                    </div>
                    <div class="right">
                        <div class="row">
                            <div class="errordiv" id="invalid-subprocess1"></div>
                            <div class="left">
                                Sub-Process<font class="error" style="color: red;">*</font>
                            </div>         
                            <div class="right">
                                <form:select path="subprocess" id="subprocess1">
                                    <form:option value="">Select</form:option>
                                    <form:options items="${subprocesslist}"></form:options>
                                </form:select>                        
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="left" style="padding: 5px; width: 34%; margin-right: -2px;">
                        <div class="row">
                            <div id="invalid-costcenter1" class="errordiv"></div>
                            <div class="left" style="width:auto">
                                Cost Center<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <form:input type="text" maxlength="100" size="30" style="width:200px" path="costcenter" id="costcenter1"/>
                            </div>
                        </div>
                    </div>
                    <div class="right" style="margin-left: -46px">
                        <div class="row">
                            <div id="invalid-total_compensation2" class="errordiv"></div>
                            <div class="left">
                                Labor Cost<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <form:input type="text" path="total_compensation" style="width:200px;"  id="total_compensation2" onchange="compensationUpdate()" onblur="compensationUpdate()"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="addcentr_top">Add Client Assignment</div>

            <div class="addcentr_fldarea">
                <table id="empdet1" width="100%">
                    <tr>
                        <td></td>
                        <td><div class="errordiv" id='invalid-sum' style="width: 100%;color: red"></div></td>
                    </tr>
                    <c:forEach var="empdetails" items="${EmpDetails}" varStatus="loop" >
                        <tr id="validationrow${loop.index+1}">
                            <td></td>
                            <td>
                                <div class="errordiv" id="invalid-client${loop.index+1}" style="width: 100%;color: red"></div>
                            </td>
                            <td></td>
                            <td>
                                <div class="errordiv" id='invalid-fte${loop.index+1}' style="width: 100%;color: red"></div>
                            </td>
                        </tr>
                        <tr>
                            <td><font class="text" style="font-size:12px">Client</font>&nbsp;<font class="error" style="color: red;">*</font>
                            </td>
                            <td>
                                <form:select name='businessunits${loop.index+1}' path='' id='client${loop.index+1}' onchange="onChange(this.id,this.value,'clientvalue${loop.index+1}')">
                                    <form:option value="">Select</form:option>
                                    <c:forEach var="client" items="${clientlist}">
                                        <c:if test="${client.key==empdetails.BusinessUnitId}">
                                            <form:option value="${client.key}" selected="selected">${client.value}</form:option>
                                        </c:if>
                                        <c:if test="${client.key!=empdetails.BusinessUnitId}">
                                            <form:option value="${client.key}">${client.value}</form:option>
                                        </c:if>
                                    </c:forEach>
                                </form:select>
                                <form:hidden path='businessunit1' id='clientvalue1' value="${empdetails.BusinessUnitId}"/>
                            </td>
                            <td>
                                <font class="text" style="font-size:12px">FTE</font>&nbsp;<font class="error" style="color: red;">*</font>
                            </td>
                            <td>
                                <form:select path='' name='fte${loop.index+1}' id='fte${loop.index+1}' onchange="onChange(this.id,this.value,'ftevalue${loop.index+1}');updateCompensation(this.value,'compensation${loop.index+1}')">
                                    <form:option value="">Select</form:option>
                                    <c:forEach var="fte" items="${ftelist}">
                                        <c:if test="${fte.key==empdetails.FTE}">
                                            <form:option value="${fte.key}" selected="selected">${fte.value}</form:option>
                                        </c:if>
                                        <c:if test="${fte.key!=empdetails.FTE}">
                                            <form:option value="${fte.key}">${fte.value}</form:option>
                                        </c:if>
                                    </c:forEach>
                                </form:select>
                                <form:hidden path='fte' id='ftevalue${loop.index+1}' name="ftevalue${loop.index+1}" value="${empdetails.FTE}"/>
                            </td>
                            <td>
                                <font class="text" style="font-size:12px">Compensation : </font>&nbsp <label id="compensation${loop.index+1}">${EditHRdata.total_compensation*empdetails.FTE}</label>
                            </td>
                            <td>
                                <img src="images/plus.png" onclick="addRow(this,'${loop.index+1}')"/>&nbsp;
                                <img src="images/cross.png" onclick="delRow(this,'${loop.index+1}')"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="addbottom_fldarea">
                <div class="addbottom_left">
                    <input type="submit" value="Save"/>
                    &nbsp;                
                    <input type="reset" value="Cancel" onclick="cancelEditHr()"/>
                </div>
            </div>
            <input type="hidden" id="count" value="${fn:length(EmpDetails)}"/>
        </div>
    </form:form>
</div>
