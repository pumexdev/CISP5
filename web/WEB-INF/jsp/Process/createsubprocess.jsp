<%-- 
    Document   : CreateUser
    Created on : Jun 27, 2012, 9:46:21 AM
    Author     : user
--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/Process/addsubprocess.js"></script>

<form:form modelAttribute="SubProcess">
    <fieldset class="forms">
        <legend> Create SubProcess </legend>
        <table align="center">
            <tr>
                <td><font class="text">Name</font></td>
                <td><form:input path="name" type="text" id="name" maxlength="30"/></td>
                <td><div id="businesscheck" style="font-size: medium;color: red"></div></td>
            </tr>
            <tr>
                <td><font class="text">Short Name</font></td>
                <td><form:input path="shortName" type="text" id="shortName" maxlength="30"/></td>
                <td></td>
            </tr>
            <tr>
                <td><font class="text">Process</font></td>
                <td>
                    <form:select path="process" id="process">
                        <form:option value="0">Select</form:option>
                        <form:options items="${process}"></form:options>
                    </form:select>
                </td>
                <td></td>
            </tr>
            <tr>
                <td><font class="text">Status</font></td>
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
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </fieldset>
</form:form>