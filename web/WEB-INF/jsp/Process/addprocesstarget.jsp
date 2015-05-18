<%-- 
Document   : addprocesstarget
Created on : Sep 3, 2012, 2:42:12 PM
Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>

<script type="text/javascript" src="js/Business/addsubprocesstarget.js"></script>

<script>
    $(function() {  
        $( "#from" ).datepicker({
            defaultDate: "+1w",
            dateFormat: 'yy-mm-dd',
            onSelect: function( selectedDate ) {
                $( "#to" ).datepicker( "option", "minDate", selectedDate );
            }
        });
        $( "#to" ).datepicker({
            defaultDate: "+1w",
            dateFormat: 'yy-mm-dd',   
            onSelect: function( selectedDate ) {
                $( "#from" ).datepicker( "option", "maxDate", selectedDate );
            }
        });
    });
</script>

<form:form modelAttribute="SubProcessTarget">
    <fieldset class="forms">
        <legend>${legentname}</legend>
        <table>
            <tr><td><font class="text">From Date</font></td>
                <td> <form:input path="fromdate" type="text" name="fromdate"  id="from" maxlength="20" /></td><td></td></tr>
            <tr><td><font class="text">To Date</font></td>
                <td> <form:input type="text" path="todate" name="todate"  id="to"  maxlength="20"/></td></tr>              
            <tr><td><font class="text">Process</font></td>
                <td> <form:select path="process" id="process">
                        <form:option value="0">select</form:option>
                        <form:options items="${subprocesslist}"></form:options>
                    </form:select></td></tr>             
            <tr><td><font class="text">Status</font></td>
                <td> <form:select path="status" id="status">
                        <form:option value="0">select</form:option>
                        <form:options items="${statuslist}"></form:options>
                    </form:select></td></tr>       
            <tr><td><font class="text">Target</font></td>
                <td><form:input type="text" path="target" id="target" /></td>
            </tr>  
            <tr><td></td><td><input type="submit" value="Save" />
                    &nbsp;   
                    <input type="button" value="Cancel" onclick="location.href='searchsubprocesstarget.htm'">
                </td></tr>
        </table>                    
    </form:form>
