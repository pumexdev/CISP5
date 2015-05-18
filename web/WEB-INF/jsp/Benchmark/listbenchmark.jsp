<%-- 
    Document   : listbenchmark
    Created on : Jul 23, 2012, 12:29:52 PM
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<div class="forms">
    <h1>Benchmarks</h1>
    <table cellspacing="1px" cellpadding="8px" class="tableclass">
        <thead>
            <th>Serial No.</th>
            <th>Business Name</th>
            <th>Client Name</th>
            <th>Process</th>
            <th>Edit</th>
            <th>Delete</th>
        </thead>
        <tbody>
            <c:forEach var="recentUL" items="${bmList}" varStatus="loop" >
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${recentUL.BusinessName}</td>
                    <td>${recentUL.BusinessUnitName}</td>

                    <td>${recentUL.ProcessName}</td>

                    
                    <td>
                        <a id="useredit${loop.index+1}" href="editbenchmark.htm?bmid=${recentUL.BenchMarkId}"><img src="images/edit.png" alt="Edit" title="Edit"/></a>
                    </td>
                    <td>
                        <img src="images/cross.png" alt="Delete" title="Delete" onclick="delUser(this,${userlist.id})"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
