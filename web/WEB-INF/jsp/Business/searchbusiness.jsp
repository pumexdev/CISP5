<%-- 
    Document   : searchbusiness
    Created on : Jul 19, 2012, 2:02:59 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript" src="js/Business/addbusiness.js"></script>
<script type="text/javascript" src="js/Business/editbusiness.js"></script>
<script type="text/javascript" src="js/Business/searchbusiness.js"></script>
<form:form modelAttribute="SearchBusiness">
    <fieldset class="forms">
        <legend>Add/Edit Business</legend>
        <div>
            <table>         
                <tr>
                    <td><font class="text">Business Name</font></td>
                    <td>
                        <form:select path="business" id="status" onchange="updateForms(this.value)">
                            <form:option value="0">Create New</form:option>
                            <form:options items="${businesslist}"></form:options>
                        </form:select>
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>
</form:form>

<c:if test="${param.add=='true'}">
    <div id="message" class="success">New Business has been successfully Added</div>
</c:if>
<c:if test="${param.add=='false'}">
    <div id="message" class="failed">Error occured while adding Business</div>
</c:if>
<c:if test="${param.edit=='true'}">
    <div id="message" class="success">Business updated successfully</div>
</c:if>
<c:if test="${param.edit=='false'}">
    <div id="message" class="failed">Error occured while updating Business</div>
</c:if>

<div id="Business1" style="display: none">
    <form:form modelAttribute="Business" action="addbusiness.htm" method="post" onsubmit="return checkBusiness($('#name').val(),'businesscheck','name','1')">        
        <fieldset class="forms">
            <legend> Add Business </legend>
            <table>
                <tr>
                    <td></td>
                    <td id="invalid-name"></td>
                </tr>
                <tr>
                    <td><font class="text">Name</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                    <td><form:input path="name" type="text" id="name" maxlength="60" onchange="checkBusiness(this.value,'businesscheck',this.id,'0')" onfocus="$('#businesscheck').html('')"/></td>
                    <td><div id="businesscheck" style="font-size: 11px;color: red"></div></td>
                </tr>
                <tr>
                    <td></td>
                    <td id="invalid-shortName"></td>
                </tr>
                <tr>
                    <td><font class="text">Short Name</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                    <td><form:input path="shortName" type="text" id="shortName" maxlength="10"/></td>
                    <td></td>
                </tr> 
                <tr>
                    <td></td>
                    <td id="invalid-status"></td>
                </tr>
                <tr>
                    <td><font class="text">Status</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                    <td>
                        <form:select path="status" id="status">
                            <form:option value="0">Select</form:option>
                            <form:options items="${status}"></form:options>
                        </form:select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Save"/><!--this.form.submit() onclick="$('#Business').submit() onclick="location.href='searchbusiness.htm'"-->
                        &nbsp;
                        <input type="reset" value="Cancel" onclick="cancel('userhome.htm')"/>
                    </td>
                </tr>
            </table>
            <form:hidden path="organization"/>
        </fieldset>
    </form:form>
</div>

<div id="EditBusiness1" style="display: none">
    <form:form modelAttribute="EditBusiness" action="editbusiness.htm" method="post" onsubmit="return checkBusiness($('#name1').val(),'businesscheck1','name1','1')">
        <fieldset class="forms">
            <legend> Edit Business </legend>
            <table>
                <tr>
                    <td></td>
                    <td id="invalid-bname"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><label for="name" id="bname"></label></td>
                </tr>
                <tr>
                    <td><font class="text">Name</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                    <td><form:input path="name" type="text" id="name1" maxlength="60"  onchange="checkBusiness(this.value,'businesscheck1',this.id,'0')" onfocus="$('#businesscheck1').html('')"/></td>
                    <td><div id="businesscheck1" style="font-size: 11px;color: red"></div></td>
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
                        <input type="submit" value="Update"/><!-- onclick="$('#EditBusiness').submit()-->
                         &nbsp;&nbsp;
                        <input type="reset" value="Cancel" onclick="updateForms('0')"/>
                    </td>
                </tr>
            </table>
        </fieldset>
        <input type="hidden" id="businessname"/>     
        <input type="hidden" name="business" id="businessid"/>
    </form:form>
</div>