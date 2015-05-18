<%-- 
    Document   : ExcelUploader.jsp
    Created on : 10 Jul, 2012, 11:01:20 AM
    Author     : Vishnu AU
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/Excel/exceluploader.js"></script>
<div class="content_slide"  style="width: 67%">
    <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
        <form:form modelAttribute="Multipart" enctype="multipart/form-data">
            <c:if test="${pageContext.request.method=='POST' && not empty usermessage}">
                <div class="failed_div">
                    <div class="failed">${usermessage}</div>
                </div>
            </c:if>
            <c:if test="${pageContext.request.method=='POST' && empty usermessage}">
                <div class="success_div">
                    <div class="success">File uploaded successfully</div>
                </div>
            </c:if>
            <div class="addcentr_top" id="divhead">Volume And KPI Data Upload</div>
            <div class="addcentr_fldarea">
                <div class="row">
                    <c:if test="${pageContext.request.method=='POST' && empty usermessage}">
                        Step 2 of 2
                    </c:if>
                    <c:if test="${pageContext.request.method=='GET'}">
                        Step 1 of 2
                    </c:if>
                </div>
                <div class="row">
                    <div class="errordiv"></div>
                    <div class="left">Statement date</div>
                    <div class="right"><form:select path="statement_date" style="width:38%"><form:options items="${dateMap}"></form:options></form:select></div>
                        </div>

                        <div class="row">
                            <div id="invalid-file" class="errordiv"></div>
                            <div class="left">File</div>
                            <div class="right">
                        <form:input path="file" name="file" type="file" id="file"></form:input>
                        </div>
                    </div>
                <c:if test="${pageContext.request.method=='POST' && empty usermessage}">
                    <div class="row">
                        <div class="errordiv"></div>
                        <div class="left">Entry Date</div>
                        <div class="right">
                            <input type="text" name="t1" readonly="true" value="${location}" style="width:0%"/>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div id="helpdiv" style="display: none">
                        1. Ensure that the statement date which is used in the sheet is created in the application and is open.<br>
                        2. Ensure that Business Unit, Business Center, Process , SubProcess etc exists in the settings.<br>
                        3. Delete unused rows before uploading the file.<br>
                        4. Select the file using browse button, click upload. You will get a message 'Uploaded successfully' in the same screen.<br>
                        5. Now Click button 'Confirm'  which takes you to another screen where summary of the file will be shown.<br>
                        6. Press 'Upload' button to upload all the data in the excel file to the database.<br> 
                    </div>
                </div>
            </div>
            <div class="addbottom_fldarea">
                <div class="addbottom_left">
                    <input type="submit" value="Upload" name="upload" id="upid"/>
                    <c:if test="${pageContext.request.method=='POST' && empty usermessage}">
                        <input type="button" value="Confirm" name="confirm" onclick="processKPI()" <%--onclick="window:location.href='volkpiprocessing.htm'"--%>/>
                    </c:if>
                </div>
            </div>
        </form:form>
    </div>
</div>
<div class="admin_right" style="padding-top: 4px !important;">
    <div class="quote" style="margin: 0px">
        <div class="q_inner_top" style="height: 200px;overflow-y :scroll ">
            <p class="q_text">Help</p>
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
