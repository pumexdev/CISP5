<%-- 
    Document   : userhelp
    Created on : Sep 25, 2012, 10:02:11 AM
    Author     : Vishnu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="margin-left: 10%;width: 60%;line-height: 200%;font-family: Calibri ">
    <h1>Helpful hints:</h1>
    </br>
    <p style="font-size: 14px;">When granting access, consider whether the role requires access to edit or display the detailed resource data.</p>
    <div class="datagrid" style="margin-top: 4%">
        <table cellspacing="1px" cellpadding="8px">
            <thead>
            <th>Role</th>
            <th>Responsibilities</th>
            </thead>
            <tbody>
                <tr>
                    <td>Global Administrator</td>
                    <td>
                        <ul style="list-style: inherit !important">
                            <li>Edit and maintain master data</li>
                            <li>Users, Centers, Clients, Processes</li>
                            <li>Create, close, and re-open statements</li>
                            <li>Import excel resource files</li>
                            <li>Export statement data files</li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>Location Administrator</td>
                    <td>
                        <ul style="list-style: inherit !important">
                            <li>Online edit of Center resource data</li>
                            <li>Display access to center specific resource data, dashboards</li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>Global Leader</td>
                    <td>
                        <ul style="list-style: inherit !important">
                            <li>Display access to all resource data </li>
                            <li>Display access to all dashboards</li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>Location Leader</td>
                    <td>
                        <ul style="list-style: inherit !important">
                            <li>Display access to Center specific resource data</li>
                            <li>Display access to all dashboards</li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>Process Leader</td>
                    <td>
                        <ul style="list-style: inherit !important">
                            <li>Display access to all dashboards</li>
                        </ul>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>