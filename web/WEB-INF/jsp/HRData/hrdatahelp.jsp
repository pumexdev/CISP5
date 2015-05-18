<%-- 
    Document   : hrdatahelp
    Created on : Sep 26, 2012, 3:14:28 PM
    Author     : Vishnu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="margin-left: 10%;width: 60%;line-height: 200%;font-family: Calibri ">
    <h1>Helpful hints:</h1></br>
    <p style="font-size: 14px;">
        To maintain data integrity, the data import includes validation </br>checks.For successful import, please check:
    <ul style="list-style: inherit !important">
        <li>Spelling of Centers and Clients is consistent between </br>the system and the excel resource data survey</li>
        <li>Any adjustments to the default listing for process and </br>sub-process have been transferred to Step 1 and Step </br>2 of the excel survey</li>
        <li>The statement date selected in the system is identical </br>to the date on the excel survey</li>
        <li>Each resource in the excel survey must have a client </br>assignment, including internal SSC resources</li>
    </ul>
    </p></br>
    <p style="font-size: 14px;">
        A previous excel data import can be over-ridden by a new  </br>import.  You will be prompted to over-ride.
    </p></br>
    <p style="font-size: 14px;">
        Only import one resource data sheet per Center.  A center </br>should consolidate its submission into one file.
    </p>
    <p style="font-size: 14px;">
        If minor edits are required after submission, you may edit </br>online by going to ________
   </p>
</div>