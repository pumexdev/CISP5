<%-- 
    Document   : searchuser
    Created on : Jul 19, 2012, 9:52:45 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script src="js/UserManagement/searchuser.js"></script>

<div id="scrollbar1">
    <div class="scrollbar">
        <div class="thumb">
            <div class="end"></div>
        </div>
    </div>
    <div class="viewport">
        <div class="overview">
            <form:form modelAttribute="SearchUser" onsubmit="return validateform();">
                <div class="admin_left" style="padding: 10px;">
                    <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">           
                        <c:if test="${pageContext.request.method=='GET'}">               
                            <div class="addcentr_top">Users</div>                
                            <c:if test="${fn:length(userlist)<=0 && pageContext.request.method=='GET'}">
                                <div style="color: red;font-size: medium">
                                    No Users found
                                </div>
                            </c:if>
                            <c:if test="${fn:length(userlist)>0}">
                                <table id="usertable" cellspacing="1px" cellpadding="8px">
                                    <thead>
                                    <th align="left">Serial No</th>
                                    <th align="left">User Name</th>
                                    <th align="left">User Status</th>
                                    <th align="left">User Level</th>
                                    <c:if test="${UserBean.writepermission=='1'}">
                                    <th align="left">Edit</th>
                                    </c:if>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="recentUL" items="${userlist}" varStatus="loop">
                                            <tr>
                                                <td align="left">${loop.index+1}</td>
                                                <td align="left">${recentUL.UserName}</td>
                                                <td align="left">${recentUL.StatusName}</td>
                                                <td align="left">${recentUL.Description}</td> 
                                                <c:if test="${UserBean.writepermission=='1'}">
                                                <td align="left">                                     
                                                    <img id="useredit${loop.index+1}" onclick="load('edituser.htm?uid=${recentUL.UserId}')"src="images/edit.png" alt="Edit" title="Edit"  style="cursor: pointer"/>                                      
                                                </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>               
                        </c:if> 
                    </div> 
                </div>
                <div class="admin_right">
                    <div>
                        <c:if test="${UserBean.writepermission=='1'}">
                        <input type="button" value="Add New" onclick="load('adduser.htm')"/>
                        </c:if>
                    </div>
                    <div class="quote">
                        <div class="q_inner_top">
                            <p class="q_text">Help</p>
                            <p>When granting access, consider whether the role requires access to edit or display the detailed resource data.</p>
                        </div>
                        <div class="q_bottom"></div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

