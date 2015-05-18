<%-- 
    Document   : searchvolumedescription
    Created on : 8 Jan, 2013, 3:06:39 PM
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/volume/searchvolumedescription.js"></script>

<form:form modelAttribute="SearchVolumeDescription">
    <fieldset  class="forms">
        <legend>Search Volume Description</legend>   
        <table>
            <tr>
                <td>
                    <font class="text">Volume Description</font>
                </td>
                <td>
                    <form:select path="status" id="status2" onchange="updateForms(this.value)">
                        <form:option value="0">Select</form:option>
                        <form:options items="${volumelist}"></form:options>
                    </form:select>
                </td>
            </tr>
        </table>    
    </fieldset>
</form:form>
<c:if test="${pageContext.request.method=='GET' && param.add=='true'}">
    <div id="message" class="success">New Volume description successfully added</div>
</c:if>
<c:if test="${pageContext.request.method=='GET' && param.add=='false'}">
    <div id="message" class="failed">Volume description addition failed</div>
</c:if>
<c:if test="${pageContext.request.method=='GET' && param.edit=='true'}">
    <div id="message" class="success">Volume description updated sucessfully</div>
</c:if>
<c:if test="${pageContext.request.method=='GET' && param.edit=='false'}">
    <div id="message" class="failed">Volume description updation failed</div>
</c:if>
<div id="descriptionadd">
    <form:form modelAttribute="AddDescription" action="addvolumedescription.htm" method="post" > 
        <fieldset class="forms">
            <legend> Add Volume Description</legend>
            <table> 
                <tr>
                    <td></td>
                    <td id="invalid-subprocess"></td>
                </tr>
                <tr>
                    <td>
                        <font class="text">Subprocess</font>&nbsp;<font class="error" style="color: red;">*</font>
                    </td>
                    <td>
                        <form:select path="subprocess" id="subprocess">
                            <form:option value="">Select</form:option>
                            <form:options items="${subprocesslist}"></form:options>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td id="invalid-description"></td>
                </tr>
                <tr>
                    <td>
                        <font class="text">Description</font>&nbsp;<font class="error" style="color: red;">*</font>
                    </td>
                    <td>
                        <form:input type="text" path="description" id="description" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td id="invalid-status"></td>
                </tr>
                <tr>
                    <td>
                        <font class="text">Status</font>&nbsp;<font class="error" style="color: red;">*</font>
                    </td>
                    <td>
                        <form:select path="status" id="status">
                            <form:option value="">Select</form:option>
                            <form:options items="${statuslist}"></form:options>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="" class="savebutton"/>
                        &nbsp;&nbsp;                      
                        <input type="reset" value="" class="cancelbutton" onclick="cancel('userhome.htm')"/>                        
                    </td>
                </tr>
            </table>  
        </fieldset>
    </form:form>
</div>
<div id="descriptionedit" style="display: none">
    <form:form modelAttribute="EditDescription" action="editvolumedescription.htm" method="post" > 
        <fieldset class="forms">
            <legend> Edit Volume Description</legend>
            <table>
                <tr>
                    <td></td>
                    <td id="invalid-subprocess1"></td>
                </tr>
                <tr>
                    <td>
                        <font class="text">Subprocess</font>&nbsp;<font class="error" style="color: red;">*</font>
                    </td>
                    <td>
                        <form:select path="subprocess" id="subprocess1">
                            <form:option value="">Select</form:option>
                            <form:options items="${subprocesslist}"></form:options>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td id="invalid-description1"></td>
                </tr>
                <tr>
                    <td>
                        <font class="text">Description</font>&nbsp;<font class="error" style="color: red;">*</font>
                    </td>
                    <td>
                        <form:input type="text" path="description" id="description1" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td id="invalid-status1"></td>
                </tr>
                <tr>
                    <td>
                        <font class="text">Status</font>&nbsp;<font class="error" style="color: red;">*</font>
                    </td>
                    <td>
                        <form:select path="status" id="status1">
                            <form:option value="">Select</form:option>
                            <form:options items="${statuslist}"></form:options>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="" class="savebutton"/>
                        &nbsp;&nbsp;                      
                        <input type="reset" value="" class="cancelbutton" onclick="cancel('userhome.htm')"/>                        
                    </td>
                </tr>
            </table>
            <form:input type="hidden" id="descriptionid" path="descriptionid"/>
        </fieldset>
    </form:form>
</div>