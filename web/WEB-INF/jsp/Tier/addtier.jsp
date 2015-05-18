<%-- 
    Document   : addtier
    Created on : Jul 21, 2012, 12:09:29 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>

<script type="text/javascript" src="js/Tier/addtieruser.js"></script>


<form:form modelAttribute="AddNewTier">
    <fieldset class="forms">
        <legend>${legendmsg}</legend>
        <table>
            <tr>
                <td><font class="text">Tier</font>&nbsp;<font class="error" style="color: red;">(*)</font></td>
                <td><form:input type="text" path="tier"  id="tier" maxlength="6"/>
                </td>
            </tr>
            <tr>
                <td><font class="text">Designation</font>&nbsp;<font class="error" style="color: red;">(*)</font></td>
                <td><form:input type="text" path="designation"  id="designation" maxlength="50"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="" class="savebutton">
                    <input type="button" value="" class="cancelbutton" onclick="location.href='searchtier.htm'">
                </td>
                <td></td>
            </tr>
        </table>
    </fieldset>
</form:form>