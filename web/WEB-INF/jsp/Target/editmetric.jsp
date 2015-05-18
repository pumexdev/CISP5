<%-- 
    Document   : EditMetric
    Created on : Mar 11, 2015, 5:16:44 PM
    Author     : Vishnu AU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript" src="js/Target/editmetric.js"></script>

<div class="content_slide">
    <div class="addcentr_area">
        <form:form modelAttribute="EditMetric" action="editmetric.htm" method="post" > 
            <div id="message">
                <c:if test="${param.edit=='true'}">
                    <div class="success_div" id="message">                      
                        <div id="message" class="success">Metric has been successfully updated</div>
                    </div>
                </c:if>
                <c:if test="${param.edit=='false'}">
                    <div class="failed_div" id="message">                       
                        <div id="message" class="failed">Metric updation failed</div>                        
                    </div>
                </c:if>
            </div>
            <div class="addcentr_top" id="divhead">Edit Metric</div>
            <div class="addcentr_fldarea">
                <div class="row"> 
                    <div id="invalid-metric" class="errordiv"></div>
                    <div class="left">Metric</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="metric" type="text" id="metric" maxlength="60" />
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row"> 
                    <div id="invalid-status" class="errordiv"></div>
                    <div class="left">Status</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select path="status" id="status">
                            <form:option value="0">Select</form:option>
                            <form:options items="${status}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="addbottom_fldarea">
                <div class="row">
                    <div class="addbottom_left">
                        <input type="submit" value="Save" />
                    </div>
                    <div class="addbottom_left">
                        <input type="reset" value="Cancel" onclick="cancel('searchmetric.htm')"/>
                    </div> 
                </div>
            </div>
                    <form:hidden path="metricId" id="metricId"/> 
        </form:form>
    </div>
</div>
