<%-- 
    Document   : searchsubprocesstarget
    Created on : Jul 26, 2012, 2:41:16 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
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
        
<form:form modelAttribute="SearchTierProcessTarget"> 
       <fieldset class="forms">
        <legend>Search Tier Process Target</legend>
      <div>
        <table>
            <tr><td><input type="button" value="AddNew" onclick="location.href='addsubprocesstarget.htm'"></td></tr>
         <tr><td><font class="text">From</font>
                 <form:input path="fromdate" type="text" name="fromdate"  id="from" maxlength="20" /></td>
            <td><font class="text">To</font>
                  <form:input type="text" path="todate" name="todate"  id="to"  maxlength="20"/></td></tr> 
         <tr><td width="7%"><font class="text">Sub Process</font></td>
              <td><form:select path="process" id="process">
               <form:option value="0">select</form:option>
                 <form:options items="${subprocesslist}"></form:options></form:select></td></tr>
                <tr><td><input type="submit" value="Search" /></td></tr>
        </table>
</div>
</form:form>             
              
 <div>
    <table cellspacing="1px" cellpadding="8px" class="tableclass">
        <thead>
            <thead>
            <th width="7%">From Date</th>
            <th width="9%">To Date</th>
            <th width="10%">Sub Process
            <th width="10%">Target</th>    
            
           <tbody>
           <c:forEach var="recentUL" items="${subprotargetlist}" varStatus="loop">
               <tr>             
                    <td align="center">${recentUL.FromDate}</td>
                    <td align="center">${recentUL.ToDate}</td> 
                    <td align="center">${recentUL.SubProcessName}</td>                     
                     <td align="center">${recentUL.Target}</td>  
                    <td>
                        <a id="useredit${loop.index+1}" href="addsubprocesstarget.htm?uid=${recentUL.SubProcessTargetId}"><img src="images/edit.png" alt="Edit" title="Edit"/></a>
                    </td>                    
               </tr> 
             </c:forEach> 
            </tbody>                           
        </thead>
                 
         </table>
    </div>        
</fieldset>
   