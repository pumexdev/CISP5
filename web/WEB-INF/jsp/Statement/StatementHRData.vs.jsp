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
    <c:set var="count2" value="2"/>
    <c:forEach var="date" items="${recentdateList}" varStatus="loop1">
        <div id="tab${loop1.index+2}" class="tabcontent" style="display: none;height: 80%;overflow-y:auto">
            <form id="SearchHRDataEntry${param.no}" method="post" action="searchhrdataentry.htm?no=${count2}" onsubmit="validateform('${count2}',this.form)">
                <input type="hidden" id="no" value="${count2}"/>
                <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 63%">
                    <div class="addcentr_top"> Search Data Entry </div>
                    <div class="addcentr_fldarea">
                        <c:if test="${param.check=='true' || check=='true'}">
                            <div class="row">
                                <div id="invalid-entry_date${count2}" class="errordiv"></div>
                                <div class="left">
                                    Date
                                </div>
                                <div class="right">
                                    <select name="entry_date${count2}" id="entry_date${count2}">
                                        <option value="">Select</option>
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
                        </c:if>


                        <input type="hidden" name="entry_date${count2}" id="entry_date${count2}"  value="${date.EntryDate}"/>
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
                <div class="admin_right" style="width: 32%;margin-top: 0px;">
                    <div>
                        <input type="button" value="Add New" class="im_ex_button"  onclick="$('#edit').empty();$('#add').show();$('#add').load('addhrdata.htm');$('#tabs li a:first').click()"/>
                        <input type="button" value="Export Excel" class="im_ex_button" name="" onclick="validateExcel('${param.no}')">
                        <input type="button" value="Export All" class="im_ex_button" name="" onclick="validateExcelAll('${param.no}')">
                        <input type="button" value="Upload" class="im_ex_button" name="" onclick="uploadbutton()">
                    </div>
                    <div class="clear"></div>        
                    <div class="quote">
                        <div class="q_inner_top">
                            <p class="q_text">Help</p>
                        </div>
                        <div class="q_bottom"></div>
                    </div>
                </div>
            </form>
            <c:if test="${pageContext.request.method=='POST'}">
                <div class="admin_left" style="width: 100%" id="resultdiv${count2}">
                    <div class="adminlist_area">
                        <div class="top_searchbar">
                            <span>HR Data</span>
                        </div>
                        <div class="ad_list_area">
                            <c:if test="${fn:length(hrlist)==0}">
                                <div style="color: red;font-size: medium">
                                    No Results found
                                </div>
                            </c:if>
                            <c:if test="${fn:length(hrlist)>0}">
                                <table cellspacing="1px" cellpadding="8px" id="hrlist">
                                    <thead>
                                    <th align="left">No.</th><th align="left">Name</th><th align="left">SubProcess</th><th align="left">Client</th><th align="left">FTE</th><th align="left">Cost</th><th align="left">Edit</th><th align="left">Delete</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="recentUL" items="${hrlist}" varStatus="loop" >
                                            <tr>
                                                <td>${loop.index+1}</td>
                                                <td>${recentUL.EmployeeName}</td>
                                                <td>${recentUL.SubProcessName}</td>
                                                <td>${recentUL.BusinessUnitName}</td>
                                                <td>${recentUL.FTE}</td>
                                                <td>
                                        <fmt:formatNumber value="${recentUL.Cost}" type="number" groupingUsed="TRUE"></fmt:formatNumber>
                                        <c:out value="${compensationvalue}"/>
                                        </td>
                                        <td>
                                            <img src="images/edit.png" alt="Edit" title="Edit" onclick="$('#add').hide();$('#edit').show();$('#edit').load('edithrdata.htm?hrid=${recentUL.EmployeeEntryId}&enid=${recentUL.EntryId}');$('#tabs li a:first').click()"/>
                                        </td>
                                        <td><img src="images/cross.png" alt="Delete" onclick="deleteEntry('${recentUL.EmployeeEntryId}')"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>                
        <c:set var="count2" value="${loop1.index+3}"/>
    </c:forEach>
    <!--Dynamic tabs-->
    <!--Last Tab(Empty) content starts-->
    <div id="tab${count2}" class="tabcontent" style="display: none;height: 80%;overflow-y:auto">
        <form id="SearchHRDataEntry${param.no}" method="post" action="searchhrdataentry.htm?no=${param.no}" onsubmit="validateform('${param.no}',this.form)">
            <input type="hidden" id="no" value="${param.no}"/>
            <div class="addcentr_area" style="float: left;box-shadow: 0 0 3px 3px #EEEEEE;width: 63%">
                <div class="addcentr_top"> Search Data Entry </div>
                <div class="addcentr_fldarea">
                    <div class="row">
                            <div id="invalid-entry_date${param.no}" class="errordiv"></div>
                            <div class="left">
                                Date
                            </div>
                            <div class="right">
                                <select name="entry_date${param.no}" id="entry_date${param.no}">
                                    <option value="">Select</option>
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
                        <div id="invalid-business_center_id${param.no}" class="errordiv"></div>
                        <div class="left">Center</div>
                        <div class="right">
                            <select name="business_center_id${param.no}" id="business_center_id${param.no}">
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
                        <input type="button" value="Search" onclick="validateform('${param.no}',this.form)"/>&nbsp;
                    </div>
                </div>
            </div>
            <div class="admin_right" style="width: 32%;margin-top: 0px;">
                <div>
                    <input type="button" value="Add New" class="im_ex_button"  onclick="$('#edit').empty();$('#add').show();$('#add').load('addhrdata.htm');$('#tabs li a:first').click()"/>
                    <input type="button" value="Export Excel" class="im_ex_button" name="" onclick="validateExcel('${param.no}')">
                    <input type="button" value="Export All" class="im_ex_button" name="" onclick="validateExcelAll('${param.no}')">
                    <input type="button" value="Upload" class="im_ex_button" name="" onclick="uploadbutton()">
                </div>
                <div class="clear"></div>        
                <div class="quote">
                    <div class="q_inner_top">
                        <p class="q_text">Help</p>
                    </div>
                    <div class="q_bottom"></div>
                </div>
            </div>
        </form>
        <c:if test="${pageContext.request.method=='POST'}">
            <div id="resultdiv6">
                <div class="admin_left" style="width: 100%">
                <div class="adminlist_area">
                    <div class="top_searchbar">
                        <span>HR Data</span>
                    </div>
                    <div class="ad_list_area">
                        <c:if test="${fn:length(hrlist)==0}">
                            <div style="color: red;font-size: medium">
                                No Results found
                            </div>
                        </c:if>
                        <c:if test="${fn:length(hrlist)>0}">
                            <table cellspacing="1px" cellpadding="8px" id="hrlist6">
                                <thead>
                                <th align="left">No.</th><th align="left">Name</th><th align="left">SubProcess</th><th align="left">Client</th><th align="left">FTE</th><th align="left">Cost</th><th align="left">Edit</th><th align="left">Delete</th>
                                </thead>
                                <tbody>
                                    <c:forEach var="recentUL" items="${hrlist}" varStatus="loop" >
                                        <tr>
                                            <td>${loop.index+1}</td>
                                            <td>${recentUL.EmployeeName}</td>
                                            <td>${recentUL.SubProcessName}</td>
                                            <td>${recentUL.BusinessUnitName}</td>
                                            <td>${recentUL.FTE}</td>
                                            <td>
                                    <fmt:formatNumber value="${recentUL.Cost}" type="number" groupingUsed="TRUE"></fmt:formatNumber>
                                    <c:out value="${compensationvalue}"/>
                                    </td>
                                    <td>
                                        <img src="images/edit.png" alt="Edit" title="Edit" onclick="$('#add').hide();$('#edit').show();$('#edit').load('edithrdata.htm?hrid=${recentUL.EmployeeEntryId}&enid=${recentUL.EntryId}');$('#tabs li a:first').click()"/>
                                    </td>
                                    <td><img src="images/cross.png" alt="Delete" onclick="deleteEntry('${recentUL.EmployeeEntryId}')"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
            </div>
        </c:if>

    </div>
    <!--Last Tab(Empty) content ends-->
</div>
<!--Tab content ends here-->