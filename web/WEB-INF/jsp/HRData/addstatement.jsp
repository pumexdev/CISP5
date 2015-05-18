<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/jQuery/jquery-ui-1.js"/>
<link rel="stylesheet" type="text/css" href="css/jQuery/jquery-ui-1.css"/>

<script type="text/javascript" src="js/HRData/statement.js"></script>
<form:form modelAttribute="Statement" onsubmit="return checkDate($('#sdate').val(),'1','sdate')">
    <div class="content_slide" id="locdiv" style="width: 67%">
        <div class="addcentr_area" style="width: 100%">
            <c:if test="${param.open=='true'}">
                <div class="success_div" id="message">                      
                    <div id="message" class="success">New Statement opened successfully</div>
                </div>                            
            </c:if>
            <c:if test="${param.open=='false'}">
                <div class="failed_div" id="message">                       
                    <div id="message" class="failed">Error occured saving Statement</div>
                </div>
            </c:if>
            <div class="addcentr_top">Add Statement Date</div>
            <div class="addcentr_fldarea">
                <div id="open" class="row">
                    <div id="invalid-sdate" class="errordiv"></div>
                    <div class="left">Statement date</div>
                    <div class="right">
                        <form:input type="text" readonly="true" path="statementdate" id="sdate" onchange="checkDate(this.value,'1',this.id)" cssStyle="width:50%;padding-right:10px;margin-right: 7px;" onfocus="$('.success_div').hide();"/><!--onfocus="$('#invalid-sdate').html('')" -->
                    </div>
                    <form:hidden path="statementStatus" value="1"/>
                </div>
            </div>
            <div class="addcentr_fldarea" style="padding:0px;height:50px;padding-right:20px">
                <div class="addbottom_left" style="float: right">
                    <input type="submit" value="Create" id="save"/> &nbsp;
                    <input type="reset" value="Cancel" onclick="cancel('statement.htm')"/>
                </div>
            </div>
        </div>
    </div>
</form:form>