<%-- 
    Document   : hrdataentryedit
    Created on : Jul 25, 2012, 11:35:42 AM
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<form:form modelAttribute="Edithrdataentry">
    <fieldset class="forms">
        <legend> Edit HRData Entry </legend>
        <table align="center">
            
            <tr>
                <td><font class="text">Business Unit</font></td>
                <td><form:select path="businessunit" id="businessunit" onchange="getsubbusinessunit(this.value,'subbusinessunit');">
                        <form:option value="0">Select</form:option>
                        <form:options items="${businessunitnames}"></form:options>
                    </form:select></td>
                <td></td>
            </tr>
            
            
            
           
            <tr>
                <td><font class="text">FTE</font></td>
                <td><form:input path="fte" type="text" id="fte" maxlength="30"/></td>
                <td></td>
            </tr>
            <tr>
                <td><font class="text">Cost</font></td>
                <td><form:input path="cost" type="text" id="cost" maxlength="30"/></td>
                <td></td>
            </tr>
            
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Update"/>
                </td>
            </tr>
        </table>
                <form:input type="hidden" path="detailid"/>
             <form:input type="hidden" path="empid"/>
    </fieldset>
</form:form>
