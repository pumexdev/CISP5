<%-- 
    Document   : addvolume
    Created on : Aug 7, 2012, 10:10:52 AM
    Author     : user
--%>


<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript" src="js/Benchmark/benchmarkMajor.js"></script>
<form:form modelAttribute="AddVolume">
    <fieldset  class="forms">
        <legend> Add Volume </legend>      
        <table>
            <tr>
                <td><font class="text">Business</font></td>
                <td><form:select path="business" id="business" onchange="getbunit(this.value,'businessunit');getbcenter(this.value,'businesscenter');">
                        <form:option value="0">Select</form:option>
                        <form:options items="${businesslist}"></form:options>
                    </form:select>
                </td>
                <td></td>
            </tr>
            <tr>
                <td><font class="text">Business Unit</font></td>
                <td><form:select path="businessunit" id="businessunit" >
                        <form:option value="0">Select</form:option>
                        <form:options items="${businessunitnames}"></form:options>
                    </form:select></td>
                <td></td>
            </tr>
            <tr>
                <td><font class="text">Business Center</font></td>
                <td><form:select path="businesscenter" id="businesscenter" >
                        <form:option value="0">Select</form:option>
                        <form:options items="${businessCenter}"></form:options>
                    </form:select></td>
                <td></td>
            </tr>
            <tr>
                <td><font class="text">Process</font></td>
                <td><form:select path="process" id="process" onchange="getsubprocess(this.value,'subprocess');">
                        <form:option value="0">Select</form:option>
                        <form:options items="${process}"></form:options>
                    </form:select></td>
                <td></td>
            </tr>
            <tr>
                <td><font class="text">Sub Process</font></td>
                <td><form:select path="subprocess" id="subprocess">
                        <form:option value="0">Select</form:option>
                        <form:options items="${subprocesslist}"></form:options>
                    </form:select></td>
                <td></td>
            </tr>
        </table>
    </fieldset>
</form:form>