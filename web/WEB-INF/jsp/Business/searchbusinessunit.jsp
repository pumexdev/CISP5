<%-- 
    Document   : searchbusinessunit
    Created on : Jul 19, 2012, 2:58:32 PM
    Author     : user
--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/Business/editbusinessunit.js"></script>
<script type="text/javascript" src="js/Business/addbusinessunit.js"></script>
<script type="text/javascript" src="js/Business/searchbusinessunit.js"></script>



<div id="BusinessUnit1">
    <form:form modelAttribute="BusinessUnit" action="addbusinessunit.htm" method="post" onsubmit="return checkBusinessUnit($('#name1').val(),'invalid-name1','name1','1')">
        <div class="content_slide">
            <div class="addcentr_area" style="width: 45%;">
                 <c:if test="${param.add=='true'}">
                    <div class="success_div">                      
                        <div id="message" class="success">Client has been successfully added</div>
                    </div>
                 </c:if>
                 <c:if test="${param.add=='false'}">
                    <div class="failed_div">
                        <div id="message" class="failed">Client addition failed</div>                        
                    </div>
                </c:if>
            <div class="addcentr_top">Add Client</div>
            <div class="addcentr_fldarea">
                <div class="row">
                    <div id="invalid-name1" class="errordiv"></div>
                    <div class="left">Name&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="name" type="text" id="name1" maxlength="60" onchange="checkBusinessUnit(this.value,'invalid-name1',this.id,'0')" onfocus="$('#businessunitcheck').html('')"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-shortName1" class="errordiv"></div>
                    <div class="left">Short Name&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="shortName" type="text" id="shortName1" maxlength="10"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-location" class="errordiv"></div>
                    <div class="left">Business Center&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select multiple="multiple" path="locations" id="location" style="height :100px;width:99%">
                            <form:options items="${locationlist}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row">
                    <div id="invalid-status1" class="errordiv"></div>
                    <div class="left">Status&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="status" id="status1">
                            <form:option value="0">Select</form:option>
                            <form:options items="${status}"></form:options>
                        </form:select>
                    </div> 
                    <div class="clear"></div>
                </div>
            </div>
            <div class="addbottom_fldarea">
                <div class="addbottom_left">
                    <input type="submit" value="Save" />
                </div>
                <div class="addbottom_left">
                    <input type="reset" value="Cancel" onclick="cancel('searchclient.htm')"/>
                </div>
            </div> 
        </div>                      
    </div>             
    <form:hidden path="business" id="businessid1"/>
</form:form>
</div>
<div id="EditBusinessUnit1" style="display: none">
    <form:form modelAttribute="EditBusinessUnit" action="editbusinessunit.htm?buid=${param.buid}" method="post" onsubmit="return checkBusinessUnit($('#name2').val(),'invalid-name2','name2','1')">
        <div class="content_slide">
            <div class="addcentr_area" style="width: 45%;">
                <c:if test="${param.edit=='true'}">
                    <div class="success_div">                      
                        <div id="message" class="success">Client has been successfully updated</div>
                    </div>
                 </c:if>
                 <c:if test="${param.edit=='false'}">
                    <div class="failed_div">
                        <div id="message" class="failed">Client updation failed</div>                        
                    </div>
                </c:if>
                <div class="addcentr_top">Edit Client</div>
                <div class="addcentr_fldarea">
                    <div class="row">
                        <div id="invalid-name2" class="errordiv"></div>
                        <div class="left">Name&nbsp;<font class="error" style="color: red;">*</font></div>
                        <div class="right">
                            <form:input path="name" type="text" id="name2" maxlength="60" onchange="checkBusinessUnit(this.value,'invalid-name2',this.id,'0')" onfocus="$('#businessunitcheck').html('')"/>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="row">
                        <div id="invalid-shortName2" class="errordiv"></div>
                        <div class="left">Short Name&nbsp;<font class="error" style="color: red;">*</font></div>
                        <div class="right">
                            <form:input path="shortName" type="text" id="shortName2" maxlength="10"/>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="row">
                        <div id="invalid-location2" class="errordiv"></div>
                        <div class="left">Business Center&nbsp;<font class="error" style="color: red;">*</font></div>
                        <div class="right">
                            <form:select multiple="multiple" path="locations" id="location2" style="height :100px;width:99%">
                                <form:options items="${locationlist}"></form:options>
                            </form:select>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="row">
                        <div id="invalid-status2" class="errordiv"></div>
                        <div class="left">Status&nbsp;<font class="error" style="color: red;">*</font></div>
                        <div class="right">
                            <form:select path="status" id="status2">
                                <form:option value="0">Select</form:option>
                                <form:options items="${status}"></form:options>
                            </form:select>
                        </div> 
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="addbottom_fldarea">
                    <div class="addbottom_left">
                        <input type="submit" value="Update" />
                    </div>
                    <div class="addbottom_left">
                        <input type="reset" value="Cancel" onclick="cancel('searchclient.htm')"/>
                    </div>
                </div> 
            </div>                      
        </div>        
        <form:input type="hidden" path="businessunit" id="businessunit2"/>
        <form:hidden path="business" id="businessid2"/>
    </form:form>
    <input type="hidden" id="businessunitname" value="${EditBusinessUnit.name}"/>
</div>
<%--<fieldset class="forms">
    <legend> Edit Client </legend>
    <table>
        <tr>
            <td></td>
            <td id="invalid-name2"></td>
        </tr>
        <tr>
            <td><font class="text">Name</font>&nbsp;<font class="error" style="color: red;">*</font></td>
            <td><form:input path="name" type="text" id="name2" maxlength="60" onchange="checkBusinessUnit(this.value,'businessunitcheck1',this.id,'0')" onfocus="$('#businessunitcheck1').html('')"/></td>
            <td><div id="businessunitcheck1" style="font-size: 11px;color: red"></div></td>
        </tr>
        <tr>
            <td></td>
            <td id="invalid-shortName2"></td>
        </tr>
        <tr>
            <td><font class="text">Short Name</font>&nbsp;<font class="error" style="color: red;">*</font></td>
            <td><form:input path="shortName" type="text" id="shortName2" maxlength="10"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td id="invalid-location1"></td>
        </tr>
        <tr>
            <td>
                <font class="text">Business Center</font>&nbsp;<font class="error" style="color: red;">*</font>
            </td>
            <td>
                <form:select multiple="multiple" path="locations" id="location2">                           
                    <form:options items="${locationlist}"></form:options>
                </form:select>                        
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td id="invalid-status2"></td>
        </tr>
        <tr>
            <td><font class="text">Status</font>&nbsp;<font class="error" style="color: red;">*</font></td>
            <td>
                <form:select path="status" id="status2">
                    <form:option value="0">Select</form:option>
                    <form:options items="${status}"></form:options>
                </form:select>
            </td>
            <td></td>
        </tr>                
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Update"/>
                &nbsp;&nbsp;
                <input type="reset" value="Cancel" onclick="cancel('searchclient.htm')"/>
            </td>
        </tr>
    </table>
</fieldset>--%>

<%--<fieldset class="forms">
    <legend> Add Client </legend>
    <table>
        <tr>
            <td></td>
            <td id="invalid-name1"></td>
        </tr>
        <tr>
            <td><font class="text">Name</font>&nbsp;<font class="error" style="color: red;">*</font></td>
            <td><form:input path="name" type="text" id="name1" maxlength="60" onchange="checkBusinessUnit(this.value,'businessunitcheck',this.id,'0')" onfocus="$('#businessunitcheck').html('')"/></td>
            <td><div id="businessunitcheck" style="font-size: 11px;color: red"></div></td>
        </tr>
        <tr>
            <td></td>
            <td id="invalid-shortName1"></td>
        </tr>
        <tr>
            <td><font class="text">Short Name</font>&nbsp;<font class="error" style="color: red;">*</font></td>
            <td><form:input path="shortName" type="text" id="shortName1" maxlength="10"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td id="invalid-location"></td>
        </tr>
        <tr>
            <td>
                <font class="text">Business Center</font>&nbsp;<font class="error" style="color: red;">*</font>
            </td>
            <td>
                <form:select multiple="multiple" path="locations" id="location">
                    <form:options items="${locationlist}"></form:options>
                </form:select>                        
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td id="invalid-status1"></td>
        </tr>

                <tr>
                    <td><font class="text">Status</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                    <td>
                        <form:select path="status" id="status1">
                            <form:option value="0">Select</form:option>
                            <form:options items="${status}"></form:options>
                        </form:select>
                    </td>
                    <td></td>
                </tr>                
                <tr>  
                    <td></td>
                    <td>
                        <input type="submit" value="Save" />
                        &nbsp;&nbsp;
                        <input type="reset" value="Cancel" onclick="cancel('searchclient.htm')"/>
                    </td>
                </tr>
            </table>
</fieldset>--%>



