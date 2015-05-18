<%-- 
    Document   : StatementHRData
    Created on : 21 Mar, 2013, 11:35:21 AM
    Author     : VISHNU A U
    Mail       : vishnu.au@pumexinfotech.com
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link type="text/css" href="css/tab2.css" rel="stylesheet" />
<script type="text/javascript" src="js/statement/statement.js"/>
<script type="text/javascript" src="js/HRData/searchhrdata.js"></script>

<script type="text/javascript">
    $(document).ready(function()
    {
        initialiseTabs();
    });
    function initialiseTabs()
    {
        $('#tabs a').click(function(e) {
            e.preventDefault();
            $("#content div.tabcontent").hide();
            $("#tabs li").attr("id","");
            $(this).parent().attr("id","current");
            $('#' + $(this).attr('title')).fadeIn();
            if($(this).attr('title')=="tab1")
                $('#' + $(this).attr('title')).jScrollPane({autoReinitialise: true});
        });
        
        $(".d_top_head").after($("#tabdiv"));
        $("#add").load('addhrdata.htm');
        $('#tabs a:last').click();
    }
</script>

<!--Tab initialise starts-->
<div id="tabdiv" class="tabdiv">
    <input type="hidden" id="datelength" value="${fn:length(recentdateList)}"/>
    <ul id="tabs">
        <li><a title="tab1" href="#">Statement</a></li>
        <c:set var="count1" value="2"/>
        <c:forEach var="date" items="${recentdateList}" varStatus="loop">
            <li><a title="tab${loop.index+2}" href="#">${date.EntryDate}</a></li>
            <c:set var="count1" value="${loop.index+3}"/>
        </c:forEach>
        <li><a title="tab${count1}" href="#">Search</a></li>
    </ul>
</div>
<!--Tab initialise ends-->

<!--Tab content starts here-->
<div id="carea">
    <!--First tab-->
    <div id="tab1" class="tabcontent" style="display: block;height: 80%;overflow-y:auto">
        <div id="add"><!--  style="height: 200px;overflow-y:auto"-->
        </div>
        <div id="edit" style="display: none">
        </div>
    </div>
    <!--First tab-->
    <!--Dynamic tabs-->
    <c:set var="count2" value="2"/><!--Starting from 2 and ends with 5-->
    <c:forEach var="date" items="${recentdateList}" varStatus="loop1">
        <div id="tab${loop1.index+2}" class="tabcontent" style="display: none;height: 80%;overflow-y:auto">
            <!--Form starts -->
            <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 63%">
                <div class="addcentr_top"> Search Data Entry </div>
                <div class="addcentr_fldarea">
                    <input type="hidden" name="entry_date${loop1.index+2}" id="entry_date${loop1.index+2}"  value="${date.EntryDate}"/>
                    <div class="row">
                        <div id="invalid-business_center_id${loop1.index+2}" class="errordiv"></div>
                        <div class="left">Center</div>
                        <div class="right">
                            <select name="business_center_id${loop1.index+2}" id="business_center_id${loop1.index+2}">
                                <option value="">Select</option>
                                <c:forEach var="center" items="${businesscenterlist}">
                                    <c:if test="${centerid==center.key}">
                                        <option value="${center.key}" selected="selected">${center.value}</option>
                                    </c:if>
                                </c:forEach>
                                <c:forEach var="center" items="${businesscenterlist}">
                                    <c:if test="${centerid!=center.key}">
                                        <option value="${center.key}">${center.value}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="addbottom_fldarea">
                    <div class="addbottom_left">
                        <input type="button" value="Search" onclick="validateform('${loop1.index+2}',this.form)"/>&nbsp;
                    </div>
                </div>
            </div>
            <div class="admin_right" style="width: 32%;margin-top: 0px;">
                <div>
                    <c:if test="${user.writepermission=='1'}">
                    <input type="button" value="Add New" class="im_ex_button"  onclick="$('#edit').empty();$('#add').show();$('#add').load('addhrdata.htm');$('#tabs li a:first').click()"/>
                    <input type="button" value="Export Excel" class="im_ex_button" name="" onclick="validateExcel('${loop1.index+2}')">
                    <input type="button" value="Export All" class="im_ex_button" name="" onclick="validateExcelAll('${loop1.index+2}')">
                    <input type="button" value="Upload" class="im_ex_button" name="" onclick="uploadbutton()">
                    </c:if>
                </div>
                <div class="clear"></div>        
                <div class="quote">
                    <div class="q_inner_top">
                        <p class="q_text">Help</p>
                    </div>
                    <div class="q_bottom"></div>
                </div>
            </div>    
            <!--Form ends-->
            <!--Search results starts-->
            <div id="resultdiv${loop1.index+2}" style=""></div>
            <!--Search results ends-->
        </div>                
        <c:set var="count2" value="${loop1.index+3}"/>
    </c:forEach>
    <!--Dynamic tabs-->

    <!--Last Tab(Empty) content starts-->
    <div id="tab${count2}" class="tabcontent" style="display: none;height: 80%;overflow-y:auto ">
        <!--Form starts -->
        <div style="width:100%">
            <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 58%">
                <div class="addcentr_top"> Search Data Entry </div>
                <div class="addcentr_fldarea">
                    <div class="row">
                        <div id="invalid-entry_date${count2}" class="errordiv"></div>
                        <div class="left">Date</div>
                        <div class="right">
                            <select name="entry_date6" id="entry_date${count2}">
<!--                                <option value="">Select</option>-->
                                <c:forEach var="date" items="${entryDates}">
                                    <c:if test="${selected_date==date.key}">
                                        <option value="${date.key}" selected="selected">${date.value}</option>
                                    </c:if>
                                    <c:if test="${selected_date!=date.key}">
                                        <option value="${date.key}">${date.value}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div id="invalid-business_center_id${count2}" class="errordiv"></div>
                        <div class="left">Center</div>
                        <div class="right">
                            <select name="business_center_id${count2}" id="business_center_id${count2}">
                                <option value="">Select</option>
                                <c:forEach var="center" items="${businesscenterlist}">
                                    <c:if test="${centerid==center.key}">
                                        <option value="${center.key}" selected="selected">${center.value}</option>
                                    </c:if>
                                </c:forEach>
                                <c:forEach var="center" items="${businesscenterlist}">
                                    <c:if test="${centerid!=center.key}">
                                        <option value="${center.key}">${center.value}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="addbottom_fldarea">
                    <div class="addbottom_left">
                        <input type="button" value="Search" onclick="validateform('${count2}',this.form)"/>&nbsp;
                    </div>
                </div>
            </div>
            <div class="admin_right" style="width: 40%;margin-top: 0px;margin-right: 10px;margin-left:10px">
                <div>
                    <c:if test="${user.writepermission=='1'}">
                    <input type="button" value="Add New" class="im_ex_button"  onclick="$('#edit').empty();$('#add').show();$('#add').load('addhrdata.htm');$('#tabs li a:first').click()"/>
                    <input type="button" value="Export Excel" class="im_ex_button" name="" onclick="validateExcel('${count2}')">
                    <input type="button" value="Export All" class="im_ex_button" name="" onclick="validateExcelAll('${count2}')">
                    <input type="button" value="Upload" class="im_ex_button" name="" onclick="uploadbutton()">
                    </c:if>
                </div>
                <div class="clear"></div>        
                <div class="quote">
                    <div class="q_inner_top">
                        <p class="q_text">Help</p>
                    </div>
                    <div class="q_bottom"></div>
                </div>
            </div>
        </div>
        <!--Form ends-->

        <!--Search results starts-->
        <div id="resultdiv${count2}" style="float: left;width: 100%;">
        </div>
        <!--Search results ends-->
    </div>
    <!--Last Tab(Empty) content ends-->
</div>
<input type="hidden" id="tabno" value="${count2}"/>
<input type="hidden" id="tabno1" value="${count2}"/>
<!--Tab content ends here-->