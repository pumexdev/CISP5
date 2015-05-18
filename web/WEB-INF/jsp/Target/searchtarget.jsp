<%-- 
    Document   : searchtarget
    Created on : Mar 16, 2015, 10:19:20 AM
    Author     : Vishnu AU
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/Target/searchtarget.js"></script>

<form:form modelAttribute="SearchTarget">
    <div id="scrollbar1">
    <div class="scrollbar">
        <div class="thumb">
            <div class="end"></div>
        </div>
    </div>
 <div class="viewport">
        <div class="overview">
            <div class="content_slide" style="width: 67%">
                    <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                <div class="addcentr_top"> Search Target / Benchmark Data </div>
                <div class="addcentr_fldarea">
                    <div class="row">
                        <div id="invalid-processId" class="errordiv"></div>
                        <div class="left">
                            Process<font class="error" style="color: red;">*</font>
                        </div>
                        <div class="right">
                            <form:select path="processId" id="processId" onchange="getSubprocess(this.value)">
                                <form:option value="">Select</form:option>
                                <form:options items="${processlist}"></form:options>
                            </form:select>
                        </div>
                    </div>
                </div>
                <div class="addbottom_fldarea">
                    <div class="addbottom_left">
                        <input type="button" value="Search" onclick="searchTarget()"/>&nbsp;
                    </div>
                </div>
            </div>
            </div>

        <div class="admin_right">
            <div>
                <c:if test="${UserBean.writepermission=='1'}">
                    <input type="button" value="Add New" onclick="load('addtarget.htm')"/>
                </c:if>

            </div>
            <div class="quote">
                <div class="q_inner_top">
                    <p class="q_text">Help</p>
                    <p>Target and Benchmark are used in the Target dashboard and can be compared with the actuals. Target and Benchmark values are input for each subprocess and is not constraint to a statement date.</p>
                </div>
                <div class="q_bottom"></div>
            </div>
        </div>

        <c:if test="${pageContext.request.method=='POST'}">
        <div class="content_slide" style="width: 65%">
            <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                <div style="margin-top: 3px;display: block;position: relative;">
                    
                    
                        <div class="addcentr_top">Target and Benchmark</div>
                                
                            <table cellspacing="1px" cellpadding="8px" id="targetlist">
                                <thead>
                                <th align="left">Serial No</th>
                                <th align="left">Subprocess</th>
                                <th align="left">Metric</th>
                                <th align="left">Target</th>
                                <th align="left">High</th>
                                <th align="left">Medium</th>
                                <th align="left">Low</th>
                                    <c:if test="${UserBean.writepermission=='1'}">
                                    <th align="left">Edit</th>
                                    </c:if>
                                </thead>
                                <tbody>
                                    <c:forEach var="recentUL" items="${targetlist}" varStatus="loop" >
                                        <tr>
                                            <td align="left">${loop.index+1}</td>
                                            <td align="left">${recentUL.SubProcessName}</td>
                                            <td align="left">${recentUL.Metric}</td>
                                            <td align="left">${recentUL.Target}</td> 
                                            <td align="left">${recentUL.BenchmarkHigh}</td>
                                            <td align="left">${recentUL.BenchmarkMedium}</td>
                                            <td align="left">${recentUL.BenchmarkLow}</td> 
                                            <c:if test="${UserBean.writepermission=='1'}">
                                                <td align="left">
                                                    <img src="images/edit.png" alt="Edit" title="Edit" onclick="load('edittarget.htm?tid=${recentUL.TargetBMId}')" style="cursor: pointer"/>          
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </fieldset>

                    
                </div>
            </div>

</c:if>
</div>
    </div>
</form:form>
