<%-- 
    Document   : searchsubprocess
    Created on : Jul 18, 2012, 3:37:05 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<!--<script type="text/javascript" src="js/Process/addsubprocess.js"></script>-->
<script type="text/javascript" src="js/Process/editsubprocess.js"></script>
<script type="text/javascript" src="js/Process/searchsubprocess.js"></script>

<div id="scroll" style="overflow-y:auto;height: 80%;">
    <div class="content_slide" style="width: 65%">
        <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%;">
            <form:form modelAttribute="SearchSubprocess">
                <div id="message">                
                    <c:if test="${param.add=='true'}">
                        <div class="success_div">                      
                            <div id="message" class="success">New Sub-Process successfully added</div>
                        </div>
                    </c:if>
                    <c:if test="${param.add=='false'}">
                        <div class="failed_div">                       
                            <div id="message" class="failed">Sub-Process addition failed</div>                        
                        </div>
                    </c:if>
                </div>
                <div class="addcentr_top" id="divhead">Sub-Process</div>
                <c:if test="${fn:length(subprocesslist)<=0}">
                    <div style="color: red;font-size: medium">
                        No Sub-Process found
                    </div>
                </c:if>
                <c:if test="${fn:length(subprocesslist)>0}">
                    <table id="subprocesslist">
                        <thead>
                        <th align="left">Serial No</th>
<!--                        <th align="left">Process</th>-->
                        <th align="left">Sub-Process</th>
                        <%--th align="left">Process</th--%>
                        <th align="left">Status</th>
                        <c:if test="${UserBean.writepermission=='1'}">
                        <th align="left">Edit</th>
                        </c:if>
                        </thead>
                        <tbody>
                            <c:forEach var="subprocess" items="${subprocesslist}" varStatus="loop">
                                <tr>
                                    <td align="left">${loop.index+1}</td>
<!--                                    <td align="left">${subprocess.ProcessName}</td>-->
                                    <td align="left">${subprocess.SubProcessName}</td>
                                    <%--td align="left">${subprocess.ProcessName}</td--%>
                                    <td align="left">
                                        <c:if test="${subprocess.SubProcessStatus=='1'}">Active</c:if>
                                        <c:if test="${subprocess.SubProcessStatus=='2'}">Inactive</c:if>
                                        </td>
                                        <c:if test="${UserBean.writepermission=='1'}">
                                        <td align="left">
                                            <img id="processedit${loop.index+1}" onclick="load('editsubprocess.htm?subid=${subprocess.SubProcessId}')" src="images/edit.png" alt="Edit" title="Edit"  style="cursor: pointer"/>
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
            <input type="button" value="Add New" onclick="load('addsubprocess.htm')"/>    
            </c:if>
            <input type="button" value="Export Sub-Process" onclick="window.location.href='subprocessexport.htm'"/> 
        </div>

        <div class="quote">
            <div class="q_inner_top">
                <p class="q_text">Help</p>
                <p>The default sub-process listing is based on the standards used by the benchmarking organization APQC.</p></br>
                <p>Why?  A common  listing allows for easier comparison to APQC benchmarks.</p></br>
                <p>The sub-process listing can be customized to fit your organization.</p></br>
                <p>If the sub-process listing is changed, the new listing will have to be transferred to the excel  resource data survey, under "Step 2" in the Instructions and Set Up worksheet.</p></br>
                <p>Changing the process listing in future statement dates can impact comparability.</p></br>
            </div>
            <div class="q_bottom"></div>
        </div>
    </div>
</div>
