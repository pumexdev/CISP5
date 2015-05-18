<%-- 
    Document   : addnewBU
    Created on : Jul 26, 2012, 10:21:35 AM
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="js/HRData/addhrdata.js" ></script>
<script src="js/HRData/hrscripts.js" ></script>

<div id="addhrdata"><!-- style="height:560px;overflow-y:scroll "-->
    <form:form modelAttribute="AddnewHRdata" method="post" action="addhrdata.htm">
        <div class="addcentr_area" style="width: 100%;">
            <c:if test="${pageContext.request.method=='GET' && param.add=='true'}">
                <div class="success_div">                      
                    <div id="message" class="success">HR Data successfully Added</div>
                </div>
            </c:if>
            <c:if test="${pageContext.request.method=='GET' && param.add=='false'}">
                <div class="failed_div">                       
                    <div id="message" class="failed">HR Data addition failed</div>
                </div>
            </c:if>
            <div class="addcentr_top"> Add Employee HR Data </div>
            <div class="addcentr_fldarea" style="height: 300px">
                <div class="row">
                    <div class="left" style="margin-right: 100px;padding: 5px;">
                        <div class="row">
                            <div id="invalid-date" class="errordiv"></div>
                            <div class="left">
                                Date<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <form:select path="date" id="date" onchange="$('#message').hide()">
                                    <form:option value="">Select</form:option>
                                    <form:options items="${datelist}"></form:options>
                                </form:select>
                            </div>
                        </div>
                    </div>
                    <div class="right">
                        <div class="row">
                            <div id="invalid-location" class="errordiv"></div>
                            <div class="left" >
                                Center<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <c:if test="${UserBean.userlevel!='4'}">
                                    <form:select path="location" id="location" onchange="getClients(this.value)">
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
                            <div class="errordiv" id="invalid-name"></div>
                            <div class="left">
                                Name<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <form:input type="text" path="name" id="name" />
                            </div>
                        </div>
                    </div>
                    <div class="right">
                        <div class="row">
                            <div id="invalid-emp_type" class="errordiv"></div>
                            <div class="left">
                                Employee Type<font class="error" style="color: red;">*</font>
                            </div>                
                            <div class="right">
                                <form:select path="emp_type" id="emp_type">
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
                            <div id="invalid-grade" class="errordiv"></div>
                            <div class="left">
                                Grade<font class="error" style="color: red;">*</font>
                            </div>       
                            <div class="right">
                                <form:input type="text" id="grade" path="grade" maxlength="20"/>
                            </div>
                        </div>
                    </div>
                    <div class="right">
                        <div class="row">
                            <div id="invalid-tier" class="errordiv"></div>
                            <div class="left">
                                Tier<font class="error" style="color: red;">*</font>
                            </div>             
                            <div class="right">
                                <form:select path="tier" id="tier">
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
                            <div class="errordiv" id="invalid-process"></div>
                            <div class="left">
                                Process<font class="error" style="color: red;">*</font>
                            </div>              
                            <div class="right">
                                <form:select path="process" id="process" onchange="updateSubprocess(this.value,'subprocess')">
                                    <form:option value="">Select</form:option>
                                    <form:options items="${processlist}"></form:options>
                                </form:select>                        
                            </div>
                        </div>
                    </div>
                    <div class="right">
                        <div class="row">
                            <div class="errordiv" id="invalid-subprocess"></div>
                            <div class="left">
                                Sub-Process<font class="error" style="color: red;">*</font>
                            </div>         
                            <div class="right">
                                <form:select path="subprocess" id="subprocess">
                                    <form:option value="">Select</form:option>                           
                                </form:select>                        
                            </div>
                        </div>
                    </div>
                </div>
                <%--div class="row">
                    <div class="left" style="margin-right: 18px;padding: 5px;width: 31%">
                        <div class="row">
                            <div id="invalid-costcenter" class="errordiv"></div>
                            <div class="left">
                                Cost Center<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <form:input type="text" maxlength="100" size="30" path="costcenter" id="costcenter"/>
                            </div>
                        </div>
                    </div>
                    <div class="right" style="margin-left: -33px">
                        <div class="row">
                            <div id="invalid-total_compensation" class="errordiv"></div>
                            <div class="left">
                                Labor Cost<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <form:input type="text" path="total_compensation" style="width:200px;" id="total_compensation"/>
                            </div>
                        </div>
                    </div>
                </div--%>
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
                    <div class="right" style="margin-left: -48px">
                        <div class="row">
                            <div id="invalid-total_compensation1" class="errordiv"></div>
                            <div class="left">
                                Labor Cost<font class="error" style="color: red;">*</font>
                            </div>
                            <div class="right">
                                <form:input type="text" path="total_compensation" style="width:200px;"  id="total_compensation1"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="addcentr_top">Add Client Assignment</div>

            <div class="addcentr_fldarea">
                <table id="empdet" style="width: 100%">
                    <tr>
                        <td></td>
                        <td><div class="errordiv" id='invalid-sum' style="width: 100%;color: red"></div></td>
                    </tr>
                    <tr id="validationrow1">
                        <td></td>
                        <td><div  id='invalid-client1' class="errordiv" style="width: 100%"></div></td>
                        <td></td>
                        <td><div  id='invalid-fte1' class="errordiv" style="width: 100%"></div></td>
                    </tr>
                    <tr style="padding-bottom: 20px">
                        <td>
                            <font class="left" style="font-size:12px;padding-top:0px;width: auto;">Client<font class="error" style="color: red;">*</font>
                        </td>
                        <td>
                            <form:select name='businessunits1' path='' id='client1' onchange="onChange(this.id,this.value,'clientvalue1')">
                                <form:option value="">Select</form:option>
                                <c:forEach var="client" items="${clientlist}">
                                    <form:option value="${client.key}">${client.value}</form:option>
                                </c:forEach>
                            </form:select>
                            <form:hidden path='businessunit1' id='clientvalue1'/>
                        </td>
                        <td>
                            <font class="left" style="font-size:12px;padding-top:0px;width: auto;">FTE</font><font class="error" style="color: red;">*</font>
                        </td>
                        <td>
                            <form:select path='' name='fte1' id='fte1' onchange="onChange(this.id,this.value,'ftevalue1');updateCompensation(this.value,'compensation1')">
                                <form:option value="">Select</form:option>
                                <form:option value="0.1">0.1</form:option>
                                <form:option value="0.2">0.2</form:option>
                                <form:option value="0.3">0.3</form:option>
                                <form:option value="0.4">0.4</form:option>
                                <form:option value="0.5">0.5</form:option>
                                <form:option value="0.6">0.6</form:option>
                                <form:option value="0.7">0.7</form:option>
                                <form:option value="0.8">0.8</form:option>
                                <form:option value="0.9">0.9</form:option>
                                <form:option value="1.0">1.0</form:option>
                            </form:select>
                            <form:hidden path='fte' id='ftevalue1'/>
                        </td>
                        <td>
                            <font class="left" style="font-size:12px;padding-top:0px;width: auto;">Compensation : </font>&nbsp <label id="compensation1"></label>
                        </td>
                        <td>
                            <img src="images/plus.png" onclick="addRow(this,'${loop.index+1}')"/>
                            <img src="images/cross.png" onclick="delRow(this,'${loop.index+1}')"/>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="addbottom_fldarea">
                <div class="addbottom_left">
                    <c:if test="${user.writepermission=='1'}">
                    <input type="submit" value="Save"/>
                                    
                    <input type="reset" value="Cancel" onclick="cancelAddHr()"/>
                </div>
                    </c:if>
            </div>
            <input type="hidden" id="count" value="1"/>
        </div>
    </form:form>
</div>