<%-- 
    Document   : addtarget
    Created on : Mar 12, 2015, 12:37:37 PM
    Author     : Vishnu AU
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/Target/addtarget.js"></script>

<div id="addhrdata" class="jspScrollable" style="display: block;height: 80%;overflow-y:auto"><!-- style="height:560px;overflow-y:scroll "-->
    <form:form modelAttribute="AddTarget" method="post" action="addtarget.htm">
        
        <div class="addcentr_area" style="width: 100%;">
            <div id="message">
            <c:if test="${param.add=='true'}">
                <div class="success_div" id="message">                      
                    <div id="message" class="success">Target benchmark has been successfully added</div>
                </div>
            </c:if>
            <c:if test="${param.add=='false'}">
                <div class="failed_div" id="message">                       
                    <div id="message" class="failed">Target benchmark addition failed</div>                        
                </div>
            </c:if>
        </div>
            <div class="addcentr_top"> Select Process </div>
            <div class="addcentr_fldarea" style="height: 100px">
                <div class="row">
                    <div class="left" style="margin-right: 100px;padding: 5px;">
                        <div class="row">
                            <div id="invalid-processId" class="errordiv"></div>
                            <div class="left">
                                Process<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <form:select path="processId" id="processId" onchange="getSubprocess(this.value)">
                                    <form:option value="">Select</form:option>
                                    <form:options items="${processlist}"></form:options>
                                </form:select>
                            </div>
                        </div>
                    </div>
                    <div class="right">

                    </div>
                </div>
            </div>

            <div class="addcentr_top">Add Target & Benchmark Data</div>

            <div class="addcentr_fldarea">
                <table id="empdet" style="width: 100%">

                    <tr id="validationrow1">
                        <td></td>
                        <td><div  id='invalid-subprocess1' class="errordiv" style="width: 100%"></div></td>
                        <td></td>
                        <td><div  id='invalid-metric1' class="errordiv" style="width: 100%"></div></td>
                        <td></td>
                        <td><div  id='invalid-target1' class="errordiv" style="width: 100%"></div></td>
                        <td></td>
                        <td><div  id='invalid-high1' class="errordiv" style="width: 100%"></div></td>
                        <td></td>
                        <td><div  id='invalid-medium1' class="errordiv" style="width: 100%"></div></td>
                        <td></td>
                        <td><div  id='invalid-low1' class="errordiv" style="width: 100%"></div></td>
                    </tr>
                    <tr style="padding-bottom: 20px">
                        <td>
                            <font class="left" style="font-size:12px;padding-top:0px;width: auto;">Subprocess<font class="error" style="color: red;">*</font>
                        </td>
                        <td>
                            <form:select name='subprocess1' path='' id='subprocess1' onchange="onChange(this.id,this.value,'subprocessvalue1')" style="width:180px;">
                                <form:option value="">Select</form:option>
                            </form:select>
                            <form:hidden path='subprocessId' id='subprocessvalue1'/>
                        </td>
                        <td>
                            <font class="left" style="font-size:12px;padding-top:0px;width: auto;">Metric</font><font class="error" style="color: red;">*</font>
                        </td>
                        <td>
                            <form:select path='' name='metric1' id='metric1' onchange="onChange(this.id,this.value,'metricvalue1')">
                                <form:option value="">Select</form:option>
                                <form:options items="${metricmap}"></form:options>
                            </form:select>
                            <form:hidden path='metricId' id='metricvalue1'/>
                        </td>
                        <td>
                            <font class="left" style="font-size:12px;padding-top:0px;width: auto;">Target</font>
                        </td>
                        <td>
                            <form:input type="text" maxlength="10" size="30" style="width:100px;min-width:0px !important" path="target" id="target1" />
                        </td>
                        <td>
                            <font class="left" style="font-size:12px;padding-top:0px;width: auto;">High</font>
                        </td>
                        <td>
                            <form:input type="text" maxlength="10" size="30" style="width:100px;min-width:0px !important" path="high" id="high1"/>
                        </td>
                        <td>
                            <font class="left" style="font-size:12px;padding-top:0px;width: auto;">Medium</font>
                        </td>
                        <td>
                            <form:input type="text" maxlength="10" size="30" style="width:100px;min-width:0px !important" path="medium" id="medium1"/>
                        </td>
                        <td>
                            <font class="left" style="font-size:12px;padding-top:0px;width: auto;">Low</font>
                        </td>
                        <td>
                            <form:input type="text" maxlength="10" size="30" style="width:100px;min-width:0px !important" path="low" id="low1"/>
                        </td>
                        <td>
                            <input type="hidden" id="row1" value="1"/>
                            <img src="images/plus.png" onclick="addRow(this, row1.value)"/>
                            <img src="images/cross.png" onclick="delRow(this, row1.value)"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="addbottom_fldarea">
                <div class="addbottom_left">
                    <c:if test="${user.writepermission=='1'}">
                        <input type="submit" value="Save"/>

                        <input type="reset" value="Reset" onclick="cancelAddTarget()"/>
                    </div>
                </c:if>
            </div>
        </div>
    </form:form>
</div>
