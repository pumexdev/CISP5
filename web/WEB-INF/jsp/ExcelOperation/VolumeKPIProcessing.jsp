<%-- 
    Document   : ExcellProcessing
    Created on : 25 Jul, 2012, 12:10:42 PM
    Author     : Vishnu A U
--%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/Excel/excelprocessing.js"></script>
<form:form modelAttribute="processingBean">
    <div id="scrollbar1">
        <div class="scrollbar">
            <div class="thumb">
                <div class="end"></div>
            </div>
        </div>
        <div class="viewport">
            <div class="overview">
                <div class="content_slide" style="height: auto !important">
                    <div class="addcentr_area" style="width:70% !important">
                        <c:if test="${not empty usermessage}">
                            <div class="failed_div"><div id="message" class="failed">${usermessage}</div></div>
                        </c:if>
                        <div class="addcentr_top" id="divhead">Excel Processing Information</div>
                        <div class="addcentr_fldarea" style="padding:10px 26px 62px 26px !important">
                            <div class="row">
                                <div id="" class="errordiv"></div>
                                <div class="left">Business Center</div>
                                <div class="right">
                                    <form:input path="bussiness_center" readonly="true" style="width:0%"></form:input>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="errordiv"></div>
                                    <div class="left">Date</div>
                                    <div class="right"><form:input path="date" readonly="true" style="width:0%"></form:input>

                                    </div>
                                </div>   
                                <div class="row">
                                    <div class="errordiv"></div>
                                    <div class="left">Location Co-ordinator</div>
                                    <div class="right">
                                    <form:input path="location_cord" readonly="true" style="width:0%"></form:input>
                                </div>
                            </div>
                        </div>

                        <div class="addcentr_top" id="divhead">Summary</div>
                        <div class="addcentr_fldarea" style="height:100px">
                            <div class="row">
                                <div id="" class="errordiv"></div>
                                <div class="left">Error Messages</div>
                                <div class="right"><form:textarea path="error_message" readonly="true"/>
                                </div>
                            </div>
                            <div class="row">
                                <div id="" class="errordiv"></div>
                                <div class="left">Status</div>
                                <div class="right"><form:input path="status" readonly="true" id="status" style="width:0%"></form:input>
                                    <c:if test="${processingBean.date_flag==false}">
                                        <form:checkbox id="dateCheck" style="width:15%;height:15%" value="true" path="date_check" onchange="kpiOverWrite()"></form:checkbox>
                                            <span style="font-weight: bolder;padding-left: 5px">Accept this file</span>
                                    </c:if>
                                </div>
                            </div>

                        </div>
                        <form:hidden path="business_center_id"></form:hidden>
                        <form:hidden path="business_id"></form:hidden>
                            <div class="addbottom_fldarea">
                                <div class="addbottom_left">
                                    <input type="button" name="confirm" disabled="true" id="confirm" value="Save" onclick="saveVolumeData(this.form)"/>
                                    <input type="reset" name="Cancel" id="cancel" value="Cancel" onclick="cancelFunction('volkpiuploader.htm')"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



</form:form>
