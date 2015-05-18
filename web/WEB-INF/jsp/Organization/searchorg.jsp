<%-- 
    Document   : searchorg
    Created on : Jul 18, 2012, 10:05:38 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript" src="js/Organization/editorg.js"></script>
<script type="text/javascript" src="js/Organization/PopUp.js"></script>
<script type="text/javascript" src="js/Organization/searchorganization.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="scrollbar1">
    <div class="scrollbar">
        <div class="thumb">
            <div class="end"></div>
        </div>
    </div>
    <div class="viewport">
        <div class="overview">
            <form:form modelAttribute="EditOrganization" enctype="multipart/form-data"><!-- onsubmit="formsubmit()"-->
                <c:choose>
                <c:when test="${UserBean.writepermission=='1'}">
                <div class="content_slide" style="width: 67%">
                    <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                        <c:if test="${success=='true'}">
                            <div class="success_div">                      
                                <div id="message" class="success">Organization has been successfully updated</div>
                            </div>
                        </c:if>
                        <c:if test="${success=='false'}">
                            <div class="failed_div">                       
                                <div id="message" class="failed">Organization updation failed</div>                        
                            </div>
                        </c:if>

                        <div class="addcentr_top" id="divhead">Organization Profile</div>
                        <div class="addcentr_fldarea">
                            <div class="row">
                                <div id="invalid-name" class="errordiv"></div>
                                <div class="left">
                                    Name<font class="error" style="color: red;">*</font>
                                </div>
                                <div class="right">
                                    <form:input path="name" type="text" id="name" maxlength="60"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-shortName" class="errordiv"></div>
                                <div class="left">
                                    Short Name
                                </div>
                                <div class="right">
                                    <form:input path="shortName" type="text" id="shortName" maxlength="10"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-address" class="errordiv"></div>
                                <div class="left">
                                    Address
                                </div>
                                <div class="right">
                                    <form:textarea path="address" rows="2" cols="24" id="address" maxlength="255" style="max-width: 430px;width:100%"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-contactPerson" class="errordiv"></div>
                                <div class="left">
                                    Contact Person
                                </div>
                                <div class="right">
                                    <form:input path="contactPerson" type="text" id="contactPerson" maxlength="30"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-phno1" class="errordiv"></div>
                                <div class="left">
                                    Phone Number 1
                                </div>
                                <div class="right">
                                    <form:input path="phno1" type="text" id="phno1" maxlength="30"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-phno2" class="errordiv"></div>
                                <div class="left">
                                    Phone Number 2
                                </div>
                                <div class="right">
                                    <form:input path="phno2" type="text" id="phno2" maxlength="30"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-emailid" class="errordiv"></div>
                                <div class="left">
                                    Email ID
                                </div>
                                <div class="right">
                                    <form:input path="emailid" type="text" id="emailid" maxlength="30"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-website" class="errordiv"></div>
                                <div class="left">
                                    Web Site
                                </div>
                                <div class="right">
                                    <form:input path="website" type="text" id="website" maxlength="30"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-file" class="errordiv"></div>
                                <div class="left">
                                    Change Logo
                                </div>
                                <div class="right">
                                    <form:input path="file" type="file" id="file" cssStyle="width:100%"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="addbottom_fldarea">
                            <c:if test="${UserBean.writepermission=='1'}">
                            <div class="addbottom_left">
                                <input type="submit" value="Update" />
                            </div>
                            <div class="addbottom_left">
                                <input type="reset" value="Cancel" onclick="cancel()"/>
                            </div>
                            </c:if>
                        </div>    
                    </div>
                </div>
                </c:when>
                    <c:otherwise>
                        <div class="content_slide" style="width: 67%">
                        <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 100%">
                        
                        <div class="addcentr_top" id="divhead">Organization Profile</div>
                        <div class="addcentr_fldarea">
                            <div class="row">
                                <div id="invalid-name" class="errordiv"></div>
                                <div class="left">
                                    Name<font class="error" style="color: red;">*</font>
                                </div>
                                <div class="right">
                                    <form:input path="name" type="text" id="name" maxlength="60" readonly="true"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-shortName" class="errordiv"></div>
                                <div class="left">
                                    Short Name
                                </div>
                                <div class="right">
                                    <form:input path="shortName" type="text" id="shortName" maxlength="10" readonly="true"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-address" class="errordiv"></div>
                                <div class="left">
                                    Address
                                </div>
                                <div class="right">
                                    <form:textarea path="address" rows="2" cols="24" id="address" maxlength="255" style="max-width: 430px;width:100%" readonly="true"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-contactPerson" class="errordiv"></div>
                                <div class="left">
                                    Contact Person
                                </div>
                                <div class="right">
                                    <form:input path="contactPerson" type="text" id="contactPerson" maxlength="30" readonly="true"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-phno1" class="errordiv"></div>
                                <div class="left">
                                    Phone Number 1
                                </div>
                                <div class="right">
                                    <form:input path="phno1" type="text" id="phno1" maxlength="30" readonly="true"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-phno2" class="errordiv"></div>
                                <div class="left">
                                    Phone Number 2
                                </div>
                                <div class="right">
                                    <form:input path="phno2" type="text" id="phno2" maxlength="30" readonly="true"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-emailid" class="errordiv"></div>
                                <div class="left">
                                    Email ID
                                </div>
                                <div class="right">
                                    <form:input path="emailid" type="text" id="emailid" maxlength="30" readonly="true"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="row">
                                <div id="invalid-website" class="errordiv"></div>
                                <div class="left">
                                    Web Site
                                </div>
                                <div class="right">
                                    <form:input path="website" type="text" id="website" maxlength="30" readonly="true"/>
                                </div>
                                <div class="clear"></div>
                            </div>
                            
                        </div>
                        <div class="addbottom_fldarea">
                            
                        </div>    
                    </div>
                </div>
                    </c:otherwise>
                </c:choose>
                <div class="admin_right" style="margin-top: 7px">
                    
                    <div class="quote">
                        <div class="q_inner_top">
                            <p class="q_text">Help</p>
                            <p></p>
                        </div>
                        <div class="q_bottom"></div>
                    </div>
                </div>
                <form:hidden path="orgid"/>
            </form:form>
        </div>
    </div>
</div>
