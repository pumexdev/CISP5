<%-- 
    Document   : Tier
    Created on : Jul 21, 2012, 10:33:42 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript" src="js/Tier/addtieruser.js"></script>
<script type="text/javascript" src="js/Tier/edittieruser.js"></script>
<script type="text/javascript" src="js/Tier/searchtier.js"></script>

<form:form modelAttribute="Tier">
    <fieldset class="forms">
        <legend>Tier List</legend>
        <table style="width: 101%">
            <tr>
                <td>
                    <font class="text">Tier</font>
                </td>
                <td>
                    <form:select path="tier" id="tier" onchange="updateForms(this.value)">
                        <form:option value="0">Create New</form:option>
                        <form:options items="${tierlist}"></form:options>
                    </form:select>                        
                </td>
            </tr>
        </table>
    </fieldset>
</form:form>


<c:if test="${pageContext.request.method=='GET' && param.add=='true'}">
    <div id="message" class="success">New Tier sucessfully added</div>
</c:if>
<c:if test="${pageContext.request.method=='GET' && param.add=='false'}">
    <div id="message" class="failed">Tier addition failed</div>
</c:if>
<c:if test="${pageContext.request.method=='GET' && param.edit=='true'}">
    <div id="message" class="success">Tier updated sucessfully</div>
</c:if>
<c:if test="${pageContext.request.method=='GET' && param.edit=='false'}">
    <div id="message" class="failed">Tier updation failed</div>
</c:if>

<div id="Tier1" style="display: none">
    <form:form modelAttribute="AddNewTier" action="addtier.htm" method="post">
        <fieldset class="forms">
            <legend>Add Tier</legend>
            <table>
                <tr>
                    <td></td>
                    <td id="invalid-tier2"></td>
                </tr>
                <tr>
                    <td>
                        <font class="text">Tier</font>&nbsp;<font class="error" style="color: red;">(*)</font>
                    </td>
                    <td>
                       <form:input type="text" path="tier"  id="tier2" />
                    </td></tr>
                <tr>
                    <td></td>
                    <td id="invalid-designation"></td>
                </tr>
                <tr>
                    <td>
                        <font class="text">Designation</font>&nbsp;<font class="error" style="color: red;">(*)</font>
                    </td>
                    <td>
                        <form:input type="text" path="designation"  id="designation" />
                    </td></tr>
                <tr><td></td><td><input type="submit" value="" class="savebutton">
                        &nbsp;&nbsp;
                        <input type="button" value="" class="cancelbutton" onclick="location.href='searchtier.htm'">
                    </td></tr> 

            </table>
        </fieldset>
    </form:form>
</div>

<div id="EditTier1" style="display: none">
    <form:form modelAttribute="EditTier" action="edittier.htm" method="post">
        <fieldset class="forms">
            <legend>Edit Tier</legend>
            <table>
                <tr>
                    <td></td>
                    <td id="invalid-tier1"></td>
                </tr>
                <tr><td><font class="text">Tier</font>&nbsp;<font class="error" style="color: red;">(*)</font></td>
                    <td><form:input type="text" path="tier"  id="tier1" readonly="true"/>
                    </td></tr>
                <tr>
                    <td></td>
                    <td id="invalid-designation1"></td>
                </tr>
                <tr><td><font class="text">Designation</font>&nbsp;<font class="error" style="color: red;">(*)</font></td>
                    <td><form:input type="text" path="designation"  id="designation1" />
                    </td></tr>
                <tr><td></td><td><input type="submit" value="" class="updatebutton">
                        &nbsp;&nbsp;
                        <input type="reset" value="" class="cancelbutton" onclick="updateForms('0')"/>
                    </td>
                </tr>
            </table>
        </fieldset>
        <input type="hidden" name="tierid" id="tier"/>
    </form:form>
</div>
