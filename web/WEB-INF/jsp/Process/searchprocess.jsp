<%-- 
    Document   : searchprocess
    Created on : Jul 18, 2012, 3:36:48 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<script type="text/javascript" src="js/Process/addprocess.js"></script>
<script type="text/javascript" src="js/Process/editprocess.js"></script>
<script type="text/javascript" src="js/Process/searchprocess.js"></script>

<div id="scroll" style="overflow-y:auto;height: 80%;"><!-- class="Jscroll-pane"-->
    <div class="content_slide" style="width: 65%">
        <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
            <form:form modelAttribute="SearchProcess">                
                <div class="addcentr_top" id="divhead">Process</div>
                <c:if test="${fn:length(processlist)<=0}">
                    <div style="color: red;font-size: medium">
                        No Process found
                    </div>
                </c:if>
                <c:if test="${fn:length(processlist)>0}">
                    <table id="processlist">
                        <thead>
                        <th align="left">Serial No</th>
                        <th align="left">Process</th>
                        <th align="left">Status</th>
                        <c:if test="${UserBean.writepermission=='1'}">
                        <th align="left">Edit</th>
                        </c:if>
                        </thead>
                        <tbody>
                            <c:forEach var="process" items="${processList}" varStatus="loop">
                                <tr>
                                    <td align="left">${loop.index+1}</td>
                                    <td align="left">${process.ProcessName}</td>
                                    <td align="left">
                                        <c:if test="${process.ProcessStatus=='1'}">Active</c:if>
                                        <c:if test="${process.ProcessStatus=='2'}">Inactive</c:if>
                                        </td>
                                        <c:if test="${UserBean.writepermission=='1'}">
                                        <td align="left">
                                            <img id="processedit${loop.index+1}" onclick="load('editprocess.htm?pid=${process.ProcessId}')"src="images/edit.png" alt="Edit" title="Edit"  style="cursor: pointer"/>
                                    </td>
                                        </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </form:form>
        </div>
    </div>
    <div class="admin_right" style="margin-top: 7px;">
        <div>
            <c:if test="${UserBean.writepermission=='1'}">
            <input type="button" value="Add New" onclick="load('addprocess.htm')"/>   
            </c:if>
            <input type="button" value="Export Process" onclick="window.location.href='processexport.htm'"/>
        </div>
        <div class="quote">
            <div class="q_inner_top">
                <p class="q_text">Help</p>
                <p>The process listing can be customized to fit your organization.</p></br>
                <p>If the process listing is changed, the new listing will have to be transferred to the excel  resource data survey, under "Step 1"</br> in the Instructions and Set Up worksheet.</p></br>
                <p>Changing the process listing in future statement dates can impact comparability.</p>
            </div>
            <div class="q_bottom"></div>
        </div>
    </div>
</div>


