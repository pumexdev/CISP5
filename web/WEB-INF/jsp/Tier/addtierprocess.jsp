<%-- 
    Document   : addtierprocess
    Created on : Jul 23, 2012, 12:06:46 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>

<script type="text/javascript" src="js/Tier/tierprocess.js"></script>

<form:form modelAttribute="AddTierProcess">
    <fieldset class="forms">
        <legend>${legentname}</legend>
        <table width="100%">
            <tr>
                <td></td>
                <td id="invalid-from"></td>
            </tr>
            <tr>
                <td>
                    <font class="text">From Date</font>&nbsp;<font class="error" style="color: red;">(*)</font></td>
                </td>
                <td>
                    <form:input path="fromdate" type="text" name="fromdate"  id="from" maxlength="20" />
                </td>
            </tr>
            <tr>
                <td></td>
                <td id="invalid-to"></td>
            </tr>
            <tr>
                <td>
                    <font class="text">To Date</font>&nbsp;<font class="error" style="color: red;">(*)</font></td>
                </td>
                <td>
                    <form:input type="text" path="todate" name="todate"  id="to"  maxlength="20"/>
                </td>
            </tr> 
            <tr>
                <td></td>
                <td id="invalid-tier"></td>
            </tr>
            <tr>
                <td>
                    <font class="text">Tier</font>&nbsp;<font class="error" style="color: red;">(*)</font></td>
                </td>
                <td>
                    <form:select path="tier" id="tier">
                        <form:option value="0">select</form:option>
                        <form:options items="${tierlist}"></form:options>
                    </form:select>
                </td>
            </tr>  
            <tr>
                <td></td>
                <td id="invalid-process"></td>
            </tr>
            <tr>
                <td>
                    <font class="text">Process</font>&nbsp;<font class="error" style="color: red;">(*)</font></td>
                </td>
                <td>
                    <form:select path="process" id="process">
                        <form:option value="0">select</form:option>
                        <form:options items="${processlist}"></form:options>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td id="invalid-status"></td>
            </tr>
            <tr>
                <td>
                    <font class="text">Status</font>&nbsp;<font class="error" style="color: red;">(*)</font></td>
                </td>
                <td>
                    <form:select path="status" id="status">
                        <form:option value="0">select</form:option>
                        <form:options items="${statuslist}"></form:options>
                    </form:select>
                </td>
            </tr> 
            <tr>
                <td></td>
                <td id="invalid-target"></td>
            </tr>
            <tr>
                <td>
                    <font class="text">Target Percentage</font>&nbsp;<font class="error" style="color: red;">(*)</font></td>
                </td>
                <td>
                    <form:input type="text" path="target" id="target" />
                </td>
            </tr>  
            <tr>
                <td></td><td>
                    <input type="submit" value="" class="savebutton" />
                    <input type="button" value="" class="cancelbutton" onclick="location.href='searchtierprocess.htm'">
                </td>
            </tr>
        </table>
    </fieldset>
</form:form>
