<%-- 
    Document   : addbenchmark
    Created on : Jul 21, 2012, 10:28:33 AM
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/Benchmark/benchmark.js"></script>
<script type="text/javascript" src="js/Benchmark/benchmarkMajor.js"></script>

<script type="text/javascript">
    $(document).ready(function()
    {
        cisfuturedate("from");
    });
    $(document).ready(function()
    {
        cisfuturedate("to");
    });
</script>
<c:if test="${pageContext.request.method=='GET' && param.add=='true'}">
    <div id="message" class="success">New Benchmark successfully Added</div>
</c:if>
<c:if test="${pageContext.request.method=='GET' && param.add=='false'}">
    <div id="message" class="failed">Benchmark addition failed</div>
</c:if>

<form:form modelAttribute="AddBenchmark">
    <fieldset class="forms" style="width: 55%">
        <legend> Add Benchmark </legend>
        <table width="102%">
            <tr>
                <td></td>
                <td id="invalid-paramtypecode"></td>
            </tr>
            <tr>
                <td>
                    <font class="text">Benchmark of</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                </td>
                <td>
                    <form:select path="paramtypecode" id="paramtypecode" onchange="getProcessValidate(this.value);messageHide()">
                        <form:option value="0">Select</form:option>
                        <form:options items="${paramlist}"></form:options>
                    </form:select>
                </td>               
            </tr>
            <tr>
                <td></td>
                <td id="invalid-business"></td>
                <td></td>
                <td id="invalid-businessunit"></td>
            </tr>
            <tr>
                <td><font class="text">Business</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                <td>
                    <form:select path="business" id="business">
                        <form:option value="0">Select</form:option>
                        <form:options items="${businesslist}"></form:options>
                    </form:select>
                </td>           
                <td><font class="text">Client</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                <td>
                    <form:select path="businessunit" id="businessunit">
                        <form:option value="0">Select</form:option>
                        <form:options items="${businessunitnames}"></form:options>
                    </form:select>
                </td>
            </tr>

            <%--<tr>
                <td><font class="text">Business Center</font></td>
                <td>
                    <form:select path="businesscenter" id="businesscenter" >
                        <form:option value="0">Select</form:option>
                    </form:select>
                </td>
            </tr>0--%>
            <tr>
                <td></td>
                <td id="invalid-process"></td>
                <td></td>
                <td id="invalid-subprocess"></td>
            </tr>
            <tr id="processtr">
                <td><font class="text">Process</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                <td>
                    <form:select path="process" id="process" onchange="getSubProcess(this.value,'subprocess');">
                        <form:option value="0">Select</form:option>
                        <form:options items="${process}"></form:options>
                    </form:select>
                </td>
                <td><font class="text">Sub Process</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                <td>
                    <form:select path="subprocess" id="subprocess">
                        <form:option value="0">Select</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td id="invalid-businesscenter"></td>
            </tr>           
            <tr>
                <td><font class="text">Business Center</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                <td>
                    <form:select path="businesscenter" id="businesscenter">
                        <form:option value="0">Select</form:option>
                        <form:options items="${locationlist}"></form:options>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td id="invalid-from"></td>
                <td></td>
                <td id="invalid-to"></td>
            </tr>

            <tr>
                <td><font class="text">From Date</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                <td>
                    <form:input path="fromdate" type="text" name="fromdate"  id="from" maxlength="20" />
                </td>
                <td>
                    <font class="text">To Date</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                </td>
                <td>
                    <form:input path="todate" type="text" name="todate"  id="to" maxlength="20" />
                </td>
            </tr>
            <tr>
                <td></td>
                <td id="invalid-top"></td>
            </tr>
            <tr>
                <td>
                    <font class="text">Top Value</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                </td>
                <td><form:input type="text" path="top" name="top"  maxlength="20"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td id="invalid-median"></td>
            </tr>

            <tr>
                <td>
                    <font class="text">Medium Value</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                </td>

                <td><form:input type="text" path="median" name="median"  maxlength="20" />
                </td>
            </tr>
            <tr>
                <td></td>
                <td id="invalid-low"></td>
            </tr>
            <tr>
                <td>
                    <font class="text">Low Value</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                </td> 
                <td><form:input type="text" path="low" name="low" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td id="invalid-status"></td>
            </tr>
            <tr>                
                <td>
                    <font class="text">Status</font>&nbsp;<font class="error" style="color: red;">*</font></td>
                </td>  
                <td>
                    <form:select path="status" id="status">
                        <form:option value="0">Select</form:option>
                        <form:options items="${statuslist}"></form:options>
                    </form:select>
                </td>
            </tr>

            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Save"/>
                    &nbsp;&nbsp;
                    <input type="button" value="Cancel" onclick="cancel('searchbenchmark.htm')">
                </td>                
            </tr>
        </table>
    </fieldset>
</form:form>
