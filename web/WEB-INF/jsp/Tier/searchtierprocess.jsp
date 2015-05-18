<%-- 
    Document   : searchtierprocess
    Created on : Jul 26, 2012, 2:41:16 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<script type="text/javascript" src="js/Tier/searchtierprocesstarget.js"></script>>
<form:form modelAttribute="SearchTierProcessTarget"> 
    <fieldset class="forms">
        <legend>Search Tier Process Target</legend>
        <div>
            <div>
                <table width="87%">               
                    <tr>                   
                        <td><font class="text">Year</font>
                        </td>
                        <td>                    
                            <form:select path="fromdate" id="fromdate">
                                <form:option value="0">Select</form:option>   
                                <form:options items="${processyearlist}"/>
                            </form:select>
                        </td>
                        <td>
                            <font class="text">Month</font>
                        </td>
                        <td>
                            <form:select path="month" id="month">
                                <form:option value="0">Select</form:option>
                                <form:option value="01">January</form:option>
                                <form:option value="02">February</form:option>
                                <form:option value="03">March</form:option>
                                <form:option value="04">April</form:option>
                                <form:option value="05">May</form:option>
                                <form:option value="06">June</form:option>
                                <form:option value="07">July</form:option>
                                <form:option value="08">August</form:option>
                                <form:option value="09">September</form:option>
                                <form:option value="10">October</form:option>
                                <form:option value="11">November</form:option>
                                <form:option value="12">December</form:option>        
                            </form:select>   
                        </td>
                        <td>
                            <input type="submit" value="Search" class="searchbutton">   
                        </td>
                        <td>
                            <input type="button" value="" class="addnewbutton" onclick="location.href='addtierprocess.htm'">
                        </td> 
                    </tr>                       
                </table>
            </div>
    </fieldset>
</form:form>

<c:if test="${param.add=='true'}">
    <div id="message" class="success">New Tier Process Target successfully Added</div>
</c:if>
<c:if test="${param.add=='false'}">
    <div id="message" class="failed">Tier Process Target addition failed</div>
</c:if>
<c:if test="${param.edit=='true'}">
    <div id="message" class="success">Tier Process Target updated successfully</div>
</c:if>
<c:if test="${param.edit=='false'}">
    <div id="message" class="failed">Tier Process Target Updation Failed</div>
</c:if>

<c:if test="${pageContext.request.method=='POST'}">
    <fieldset class="forms">
        <legend>Search Results</legend>
        <c:if test="${fn:length(tierprocesslist)<=0 && pageContext.request.method=='POST'}">
            <div style="color: red;font-size: medium">
                No Results found
            </div>
        </c:if>
        <c:if test="${fn:length(tierprocesslist)>0}">
            <table cellspacing="1px" cellpadding="8px" class="tableclass">
                <thead>
                <thead>
                <th width="11%">From Date</th>
                <th width="12%">To Date</th>
                <th width="12%">Tier
                <th width="12%">Process
                <th width="9%">Target%</th>    
                <th width="10%">Edit</th>    
                </thead>
                <tbody>
                    <c:forEach var="recentUL" items="${tierprocesslist}" varStatus="loop">
                        <tr>             
                            <td align="center">${recentUL.FromDate}</td>
                            <td align="center">${recentUL.ToDate}</td> 
                            <td align="center">Tier${recentUL.Tier} / ${recentUL.Designation}</td>    
                            <td align="center">${recentUL.ProcessName}</td> 
                            <td align="center">${recentUL.Target}</td> 
                            <td align="center">
                                <a id="useredit${loop.index+1}" href="addtierprocess.htm?uid=${recentUL.TierProcessTargetId}"><img src="images/edit.png" alt="Edit" title="Edit"/></a>
                            </td>                    
                        </tr> 
                    </c:forEach> 
                </tbody>       
            </table>       
        </c:if>
    </fieldset>
</c:if>
