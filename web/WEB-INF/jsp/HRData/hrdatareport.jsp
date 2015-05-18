<%-- 
    Document   : hrdatareport
    Created on : Sep 12, 2012, 2:21:04 PM
    Author     : Vishnu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="js/HRData/hrdatareport.js"></script>


<div id="scrollbar1">
    <div class="scrollbar">
        <div class="thumb">
            <div class="end"></div>
        </div>
    </div>
    <div class="viewport">
        <div class="overview">

            <form id="SearchHRDataEntry" method="POST">
                <div class="content_slide" style="padding:0px !important;padding-top: 10px !important;height: auto !important;width: 67% !important;">
                    <div class="addcentr_area" style="width: 100% !important">
                        <div class="addcentr_top">Search Data Entry</div>
                        <div class="addcentr_fldarea">
                            <div class="row">
                                <div id="invalid-entry_date" class="errordiv"></div>
                                <div class="left">Date&nbsp;<font class="error" style="color: red;">*</font></div>
                                <div class="right">
                                    <select name="entry_date" id="entry_date" onchange="$('#message').hide()">
                                        <option value="">Select</option>
                                        <c:forEach var="entryDate" items="${entryDates}">
                                            <c:if test="${entryDate.key==entry_date}">
                                                <option value="${entryDate.key}" selected="selected">${entryDate.value}</option>
                                            </c:if>
                                            <c:if test="${entryDate.key!=entry_date}">
                                                <option value="${entryDate.key}">${entryDate.value}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-business_center_id" class="errordiv"></div>
                                <div class="left">Center&nbsp;<font class="error" style="color: red;">*</font></div>
                                <div class="right">
                                    <select name="business_center_id" id="business_center_id">
                                        <option value="">Select</option>
                                        <c:forEach var="location" items="${businesscenterlist}">
                                            <c:if test="${location.key==business_center_id}">
                                                <option value="${location.key}" selected="selected">${location.value}</option>
                                            </c:if>
                                            <c:if test="${location.key!=business_center_id}">
                                                <option value="${location.key}">${location.value}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="addbottom_fldarea">
                            <div class="addbottom_left">
                                <input type="submit" value="Search" />
                            </div>
                            <div class="addbottom_left">
                                <input type="reset" value="Cancel" onclick="cancel('statementview.htm')"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="admin_right">
                    <div class="quote">
                        <div class="q_inner_top" style="height:200px;overflow-y:scroll ">
                            <p class="q_text">New</p>
                            <p style="line-height: 20px">
                                1. Ensure that the statement date which is used in the sheet is created in the application and is open.<br>
                                2. Ensure that Business Unit, Business Center, Process , SubProcess etc exists in the settings.<br>
                                3. Delete unused rows before uploading the file.<br>
                                4. Select the file using browse button, click upload. You will get a message 'Uploaded successfully' in the same screen.<br>
                                5. Now Click button 'Confirm'  which takes you to another screen where summary of the file will be shown.<br>
                                6. Press 'Upload' button to upload all the data in the excel file to the database.
                            </p>
                        </div>
                        <div class="q_bottom"></div>
                    </div>
                </div>
                <c:if test="${pageContext.request.method=='POST'}">
                    <div class="content_slide" style="padding:0px !important;padding-top: 10px !important;height: auto !important;width: 98% !important;">
                        <div class="addcentr_area" style="width: 100% !important">
                            <div class="addcentr_top">Employee Details</div>
                            <div class="addcentr_fldarea">
                                <c:if test="${fn:length(HRData)==0}">
                                    <div style="color: red;font-size: medium">
                                        No Results found
                                    </div>
                                </c:if>
                                <c:if test="${fn:length(HRData)>0}">
                                    <table cellspacing="1px" cellpadding="8px" id="hrlist">
                                        <thead>
                                        <th align="left">No.</th>
                                        <th align="left">Name</th>
                                        <th align="left">Process</th>
                                        <th align="left">SubProcess</th>
                                        <th align="left">Client</th>
                                        <th align="left">Grade</th>
                                        <th align="left">Tier</th>
                                        <th align="left">FTE</th>
                                        <th align="left">Labor Cost</th>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="recentUL" items="${HRData}" varStatus="loop" >
                                                <tr>
                                                    <td>${loop.index+1}</td>
                                                    <td>${recentUL.EmployeeName}</td>
                                                    <td>${recentUL.ProcessName}</td>
                                                    <td>${recentUL.SubProcessName}</td>
                                                    <td>${recentUL.BusinessUnitName}</td>
                                                    <td>${recentUL.Grade}</td>
                                                    <td>${recentUL.Tier}</td>
                                                    <td>${recentUL.FTE}</td>
                                                    <td>
                                                        <fmt:parseNumber var="compensationvalue" type='number' value='${recentUL.Cost}' integerOnly="true"/>
                                                        <c:out value="${compensationvalue}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function()
    {
        $("#scrollbar1").tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update();
        $(".dataTables_length select").change(function()
        {
            $('#scrollbar1').tinyscrollbar();
            $("#scrollbar1").tinyscrollbar_update();
        });
    });
</script>