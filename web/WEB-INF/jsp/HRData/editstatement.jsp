<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/HRData/statement.js"></script>
<form:form modelAttribute="Statement" onsubmit="return checkDate($('#sdate').val(),'1','sdate')">
    <div class="content_slide" id="locdiv" style="width: 67%">
        <div class="addcentr_area" style="width: 100%">
            <div class="addcentr_top">Edit Statement Date</div>
            <div class="addcentr_fldarea">
                <div id="open" class="row">
                    <div id="invalid-sdate" class="errordiv"></div>
                    <div class="left">Statement date</div>
                    <div class="right">
                        <c:if test="${param.status==1}">
                            <form:input type="text" readonly="true" path="opendate" id="sdate1" onchange="checkDate(this.value,'1',this.id)" cssStyle="width:50%;padding-right:10px;margin-right: 7px;" value="${Statement.date}"/>
                            <form:hidden path="statementStatus" value="2"/>
                        </c:if>
                        <c:if test="${param.status==2}">
                            <form:input type="text" readonly="true" path="closedate" id="sdate1" onchange="checkDate(this.value,'1',this.id)" cssStyle="width:50%;padding-right:10px;margin-right: 7px;" value="${Statement.date}"/>
                            <form:hidden path="statementStatus" value="3"/>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="addcentr_fldarea" style="padding:0px;height:50px;padding-right:20px">
                <div class="addbottom_left" style="float: right">
                    <c:if test="${param.status==1}">
                        <input type="submit" value="Close" id="save"/>
                    </c:if>
                    <c:if test="${param.status==2}">
                        <input type="submit" value="Reopen" id="save"/>
                    </c:if>
                    <input type="reset" value="Cancel" onclick="cancel('statement.htm')"/>
                </div>
            </div>
        </div>
    </div>
</form:form>