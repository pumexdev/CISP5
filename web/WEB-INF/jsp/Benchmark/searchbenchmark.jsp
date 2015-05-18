<%-- 
    Document   : searchbenchmark
    Created on : Aug 7, 2012, 11:40:44 AM
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/Benchmark/searchbenchmark.js"></script>
<script type="text/javascript" src="js/Benchmark/benchmarkMajor.js"></script>
<!DOCTYPE html>

<form:form modelAttribute="SearchBenchmark">
    <fieldset  class="forms" style="width: 55%">
        <legend>Search Benchmarks</legend>   
        <table>
        <tr>
            <td></td>
            <td id="invalid-paramtypecode"></td>
        </tr>
            <tr>
                <td>
                    <font class="text">Benchmark of</font>
                </td>
                <td>
                    <form:select path="paramtypecode" id="paramtypecode" onchange="UpdateForms()">
                        <form:option value="0">Select</form:option>
                        <form:options items="${paramlist}"></form:options>
                    </form:select>                
            </tr>
            <tr>
                <td></td>
                <td id="invalid-business"></td>
                <td></td>
                <td id="invalid-businessunit"></td>
            </tr>
            <tr>
                <td><font class="text">Business</font></td>
                <td>
                    <form:select path="business" id="business">
                        <form:option value="0">Select</form:option>
                        <form:options items="${businesslist}"></form:options>
                    </form:select>
                </td>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <font class="text">Client</font></td>
                <td>
                    <form:select path="businessunit" id="businessunit" >
                        <form:option value="0">Select</form:option>
                        <form:options items="${businessunitnames}"></form:options>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td id="invalid-businesscenter"></td>
             </tr>            
            <tr>
                <td><font class="text">Business Center</font></td>
                <td>
                    <form:select path="businesscenter" id="businesscenter">
                        <form:option value="0">Select</form:option>
                        <form:options items="${locationlist}"></form:options>
                    </form:select>
                </td>
            </tr> 
            <tr>
                <td></td>
                <td id="invalid-fromdate"></td>
                <td></td>
                <td id="invalid-todate"></td>
             </tr>
            <tr>                   
                <td>
                    <font class="text">Year</font>
                </td>
                <td>                    
                    <form:select path="fromdate" id="fromdate">
                        <form:option value="0">Select</form:option>   
                        <form:options items="${yearlist}"/>
                    </form:select>
                </td> 
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <font class="text">Month</font>
                </td> 
                <td>
                    <form:select path="todate" id="todate">
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
            </tr>

            <tr><td></td>
                <td>
                    <input type="submit"  value="Search"/>  
                    &nbsp;&nbsp;
                    <input type="button" onclick="window.location.href='addbenchmark.htm'" value="Add New"/>
                </td>
            </tr>
        </table>
    </fieldset>
</form:form>


<c:if test="${pageContext.request.method=='GET' && param.edit=='true'}">
    <div id="message" class="success">Benchmark updated successfully</div>
</c:if>
<c:if test="${pageContext.request.method=='GET' && param.edit=='false'}">
    <div id="message" class="failed">Benchmark Updation Failed</div>
</c:if>

<c:if test="${pageContext.request.method=='POST'}">
    <fieldset class="forms" style="width: 50%">
        <legend>Search Results</legend>
        <c:if test="${fn:length(benchmarkList)<=0 && pageContext.request.method=='POST'}">
            <div style="color: red;font-size: medium">
                No Results found
            </div>
        </c:if>
        <c:if test="${fn:length(benchmarkList)>0}">
            <table cellspacing="1px" cellpadding="8px" class="tableclass" id="bmListTable" style="width: 90%;">
                <thead>
                <th>SL No.</th>
                <th>Benchmark
                <th>Business</th>
                <th>Business Unit</th>
                <th>Location</th>
                <th>Edit</th>
                </thead>
                <tbody>
                    <c:forEach var="recentUL" items="${benchmarkList}" varStatus="loop" >
                        <tr>
                            <td>${loop.index+1}</td>
                            <td>${recentUL.Description}
                            <td>${recentUL.BusinessName}</td>
                            <td>${recentUL.BusinessUnitName}</td>
                            <td>${recentUL.BusinessCenterName}</td>
                            <td>
                                <a id="useredit${loop.index+1}" href="editbenchmark.htm?bmid=${recentUL.BenchMarkId}"><img src="images/edit.png" alt="Edit" title="Edit"/></a>
                            </td>
                        </tr>
                    </c:forEach>            
                </tbody>   
            </table>       
        </c:if>
    </fieldset>
</c:if>
