<%-- 
    Document   : searchtiertarget
    Created on : Jul 25, 2012, 12:05:16 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<script type="text/javascript" src="js/Tier/searchtiertarget.js"></script>

<form:form modelAttribute="SearchTierTarget"> 
    <fieldset class="forms">
        <legend>Search Tier Target</legend>
        <div>
            <table width="100%">
                <tr>
                 <tr>
                    <td></td>
                    <td id="invalid-fromdate"></td>
                     <td></td>
                    <td id="invalid-month"></td>
                </tr>
                    <td><font class="text">Year</font>
                    </td>
                    <td>                    
                        <form:select path="fromdate" id="fromdate" onchange="messageHide();">
                            <form:option value="0">Select</form:option>   
                            <form:options items="${yearlist}"/>
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
                        <input type="submit" value="" class="searchbutton">   
                    </td>                        
                    <td>
                        <input type="button" value="" class="addnewbutton" onclick="location.href='addtiertarget.htm'">
                    </td> 


                </tr>                       
            </table>
        </div>
    </fieldset>
</form:form>   

<c:if test="${param.add=='true'}">
    <div id="message" class="success">New Tier Target successfully Added</div>
</c:if>
<c:if test="${param.add=='false'}">
    <div id="message" class="failed">Tier Target addition failed</div>
</c:if>
<c:if test="${param.edit=='true'}">
    <div id="message" class="success">Tier Target updated successfully</div>
</c:if>
<c:if test="${param.edit=='false'}">
    <div id="message" class="failed">Tier Target Updation Failed</div>
</c:if>

<c:if test="${pageContext.request.method=='POST'}">
    <fieldset class="forms">
        <legend>Search Results</legend>
        <c:if test="${fn:length(tiertargetlist)<=0 && pageContext.request.method=='POST'}">
            <div style="color: red;font-size: medium">
                No Results found
            </div>
        </c:if>
        <c:if test="${fn:length(tiertargetlist)>0}">

            <%--<div id="TierTarget" style="display: none">
                 / /  <form:form modelAttribute="EditTierTarget">
            <fieldset class="forms">
            <legend>Edit Tier</legend>--%>
            <table cellspacing="1px" cellpadding="8px" class="tableclass">
                <thead>
                <thead>
                <th width="11%">From Date</th>
                <th width="12%">To Date</th>
                <th width="12%">Tier
                <th width="9%">Target%</th>    
                <th width="10%">Edit</th>    
                </thead>
                <tbody>
                    <c:forEach var="recentUL" items="${tiertargetlist}" varStatus="loop">
                        <tr>             
                            <td>${recentUL.FromDate}</td>
                            <td>${recentUL.ToDate}</td> 
                            <td>Tier${recentUL.Tier} / ${recentUL.Designation}</td>                     
                            <td>${recentUL.Target}</td> 
                            <td>
                                <a id="useredit${loop.index+1}" href="addtiertarget.htm?uid=${recentUL.TierTargetId}"><img src="images/edit.png" alt="Edit" title="Edit"/></a>
                            </td>                    
                        </tr> 
                    </c:forEach> 
                </tbody>       
            </table>       
        </c:if>
    </fieldset>
</c:if>
