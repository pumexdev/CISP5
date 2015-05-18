<%-- 
    Document   : edittarget
    Created on : Mar 16, 2015, 3:23:07 PM
    Author     : Vishnu AU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript" src="js/Target/edittarget.js"></script>

<div class="content_slide">
    <div class="addcentr_area">
        <form:form modelAttribute="EditTarget" action="edittarget.htm" method="post" > 
            <div id="message">
                <c:if test="${param.edit=='true'}">
                    <div class="success_div" id="message">                      
                        <div id="message" class="success">Target has been successfully updated</div>
                    </div>
                </c:if>
                <c:if test="${param.edit=='false'}">
                    <div class="failed_div" id="message">                       
                        <div id="message" class="failed">Target updation failed</div>                        
                    </div>
                </c:if>
            </div>
            <div class="addcentr_top" id="divhead">Edit Target and Benchmark</div>
            <div class="addcentr_fldarea">
                <div class="row"> 
                    <div id="invalid-subprocessid" class="errordiv"></div>
                    <div class="left">Subprocess</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select name='subprocessid' path='subprocessid' id='subprocessid' disabled="true">
                            <form:option value="">Select</form:option>
                            <form:options items="${subprocessmap}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row"> 
                    <div id="invalid-metric" class="errordiv"></div>
                    <div class="left">Metric</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:select name='metric' path='metric' id='metric' disabled="true">
                            <form:option value="">Select</form:option>
                            <form:options items="${metricmap}"></form:options>
                        </form:select>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row"> 
                    <div id="invalid-target" class="errordiv"></div>
                    <div class="left">Target</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="target" type="text" id="target" maxlength="10" />
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row"> 
                    <div id="invalid-high" class="errordiv"></div>
                    <div class="left">Benchmark High</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="high" type="text" id="high" maxlength="10" />
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row"> 
                    <div id="invalid-medium" class="errordiv"></div>
                    <div class="left">Benchmark Medium</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="medium" type="text" id="medium" maxlength="10" />
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="row"> 
                    <div id="invalid-low" class="errordiv"></div>
                    <div class="left">Benchmark Low</font>&nbsp;<font class="error" style="color: red;">*</font></div>
                    <div class="right">
                        <form:input path="low" type="text" id="low" maxlength="10" />
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
                        <input type="reset" value="Cancel" onclick="cancel('searchtarget.htm')"/>
                    </div> 
                </div>
            </div>
            <form:hidden path="tid" id="tid"/>
            <form:hidden path="subprocessid" id="subprocessid1"/> 
            <form:hidden path="metric" id="metric"/> 
        </form:form>
    </div>
</div>
