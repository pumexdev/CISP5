<%-- 
    Document   : searchvolume
    Created on : Aug 7, 2012, 10:38:18 AM
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript" src="js/Benchmark/benchmarkMajor.js"></script>
<form:form modelAttribute="SearchVolume">
    <fieldset  class="forms">
        <legend>Search Volume</legend>   
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
            <tr>
                <td colspan="1">
                    <input type="button" onclick="if(bmsearchvalidate()){this.form.submit()};" value="Search"/>
                </td>
                <td colspan="1">
                    <input type="button" onclick="window.location.href='addvolume.htm'" value="Add New Volume"/>
                </td>
            </tr>
        </table>

        <h3>Volume</h3>
        <table cellspacing="1px" cellpadding="8px" class="tableclass" id="bmListTable" style="width: 90%;">
            <thead>
            <th>Serial No.</th>
            <th>Business Name</th>
            <th>Business Unit Name</th>
            <th>Process</th>
            <th>Edit</th>
            <th>Delete</th>
            </thead>
            <tbody>
                <c:forEach var="recentUL" items="${bmeditList}" varStatus="loop" >
                    <tr>
                        <td>${loop.index+1}</td>
                        <td>${recentUL.BusinessName}</td>
                        <td>${recentUL.BusinessUnitName}</td>
                        <td>${recentUL.ProcessName}</td>
                        <td>
                            <a id="useredit${loop.index+1}" href="editbenchmark.htm?bmid=${recentUL.BenchMarkId}"><img src="images/edit.png" alt="Edit" title="Edit"/></a>
                        </td>
                        <td>
                            <img src="images/cross.png" alt="Delete" title="Delete" onclick="delbm(${loop.index+1},${recentUL.BenchMarkId},'bmListTable')"/>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty bmeditList && pageContext.request.method=='POST'}" >
                    <tr>
                        <td colspan="6" style="color: red;font-size: small;">
                            No records Found!!!
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </fieldset>
</form:form>
<script>

</script>
