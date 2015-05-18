<%-- 
    Document   : charts
    Created on : Jul 4, 2012, 1:12:48 PM
    Author     : mjons
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/dashboard/dashboard.js"></script>
<script type="text/javascript" src="js/dashboard/maindashboard.js"></script>
<link type="text/css" href="css/tab.css" rel="stylesheet"/>

<style>


#style-2::-webkit-scrollbar-track
{
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
	border-radius: 10px;
	background-color: #F5F5F5;
}

#style-2::-webkit-scrollbar
{
	width: 12px;
	background-color: #F5F5F5;
}

#style-2::-webkit-scrollbar-thumb
{
	border-radius: 10px;
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
	background-color: #BABABA;
}


</style>

<form:form modelAttribute="GlobalDashBoard">
    <div class="global_area_main">

        <!-- Dashboard Menu starts-->
        <div class="d2_menu_nav_out">
            <div class="left_text_menu">Dashboard</div>
            <div style="float: right; margin-top: 10px; margin-right: 75px;border-radius:opx">
                <select name="date1" id="date1" onchange="onChange(this.id, this.value, 'dat1');getDates2(this.value, 'date2')" ><!-- style="width: 121px; position: absolute; opacity: 0; height: 25px; font-size: 11px;"-->
                    <c:forEach var="date" items="${dates1}">
                        <option value="${date.key}">${date.value}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!-- Dashboard Menu ends-->

        <!-- Dashboard content div starts -->
        <div style="height: 90%"><!--style="height: 90%"-->
            <div class="test01">
                <!--Content slide starts-->
                <div class="content_slide" style="padding: 0">
                    <div class="landing_left" style="height: 80%;overflow-y: auto">
                        <div id="content">
                            <div id="tab1" class="tabcontent">
                                <table id="kpitable" style="width: 100%;">
                                    <tr>
                                        <td>
                                            <div class="expand_style">
                                                <div class="top_bar">
                                                    <div class="ex_left01">Chart Description</div>
                                                    <div class="information_btn_out" id="infoicon2"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                    <div class="ex_left02">H1 How many resources across your Centers?</div>
                                                    <div class="ex_right" style="margin-top: -14px">
                                                        <input id="graphdiv2_button" type="button" value="Export" name="" class="export_button" style="display: block" onclick="saveAsImg('graphdiv2')"/>
                                                    </div>
                                                </div>
                                                <div class="chartdescdiv" id="chartdescdiv2">Where have you added resources? Is that consistent with your plan and strategy?</div>
                                                <div class="graphOuter">
                                                    <div class="graffDiv" id="graphdiv2"></div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="expand_style">
                                                <div class="top_bar">
                                                    <div class="ex_left01">Chart Description</div>
                                                    <div class="information_btn_out" id="infoicon3"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                    <div class="ex_left02">H2 What are the resources per Tier for your global organization?</div>
                                                    <div class="ex_right" style="margin-top: -14px">
                                                        <input id="graphdiv3_button" type="button" value="Export" name="" style="display: block" onclick="saveAsImg('graphdiv3')" class="export_button"/>
                                                    </div>
                                                </div>
                                                <div class="chartdescdiv" id="chartdescdiv3">What is the span of control of your global organization? Are changes consistent with your plan?</div>
                                                <div class="graphOuter">
                                                    <div class="graffDiv" id="graphdiv3"></div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="expand_style">
                                                <div class="top_bar">
                                                    <div class="ex_left01">Chart Description</div>
                                                    <div class="information_btn_out" id="infoicon1"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                    <div class="ex_left02">H3 What are the resources per process for your global organization?</div>
                                                    <div class="ex_right" style="margin-top: -14px">
                                                        <input id="graphdiv1_button" type="button" value="Export" name="" class="export_button" style="display: block" onclick="saveAsImg('graphdiv1')"/>
                                                    </div>
                                                </div>
                                                <div class="chartdescdiv" id="chartdescdiv1">What is your resource allocation across processes? Are changes consistent with your plan and strategy?</div>
                                                <div class="graphOuter">
                                                    <div class="graffDiv" id="graphdiv1"></div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="landing_right">
                        <div class="landing_listing">
                            <div class="landing_box">Number of Employees</div>
                            <div class="landing_box2" id="7">${span}</div>
                        </div>
                        <div class="landing_listing">
                            <div class="landing_box">Span of Control (Tier 3 %)</div>
                            <div class="landing_box2">${span}</div>
                        </div>
                        <div class="landing_listing">
                            <div class="landing_box">Average Cost per FTE</div>
                            <div class="landing_box2" id="8">${avgcostperfte}</div>
                        </div>
                        <div class="landing_listing">
                            <div class="landing_box">PTP - Annualized Invoices</div>
                            <div class="landing_box2" id="1">${ptpAnnualizedinvoice}</div>
                        </div>
                        <div class="landing_listing">
                            <div class="landing_box">PTP - Cost per Invoice</div>
                            <div class="landing_box2" id="2">${costperinvoice}</div>
                        </div>
                        <div class="landing_listing">
                            <div class="landing_box">OTC - Annualized Cash Receipts</div>
                            <div class="landing_box2" id="3">${otcannualizedcost}</div>
                        </div>
                        <div class="landing_listing">
                            <div class="landing_box">OTC - Cost per Cash Receipt</div>
                            <div class="landing_box2" id="4">${otccostpercash}</div>
                        </div>
                        <div class="landing_listing">
                            <div class="landing_box">RTR - Annualized Manual JEs'</div>
                            <div class="landing_box2" id="5">${rtrannualizedmanual}</div>
                        </div>
                        <div class="landing_listing">
                            <div class="landing_box">RTR - Cost per Manual JE</div>
                            <div class="landing_box2" id="6">${rtrcostpermanualje}</div>
                        </div>
                    </div>
                </div>
                <!--Content slide ends-->
            </div>
        </div>
        <!-- Dashboard content div ends -->
    </div>
    <div style="display: none">
        <input type="hidden" id="dat1" value="${date1}"/>
        <input type="hidden" id="dat2" value="${date2}"/>
        <input type="hidden" id="spid" value="${defaultSubProcess}"/>
        <input type="hidden" id="bcid" value="${defaultLocation}"/>
        <input type="hidden" id="tier" value="${tier}"/>
        <input type="hidden" id="organization" value="${orgid}"/>
    </div>
</form:form>
<script>
    $(document).ready(function() {
        //$(".content_slide").css("width","94%");
    });
    
    function reduce()
    {
        $(".content_slide").css("width","69%");
        toggleContentFrame();
    }
    function expand()
    {
        $(".content_slide").css("width","94%");
        toggleContentFrame()
    }
    function toggleContentFrame()
    {
        var framewidth = $(".content_slide").width()*0.93;
        var viewpheight = $(window).height()*0.7;
        $("#contentframe").attr('width',Math.ceil(framewidth));
        $("#contentframe").attr('height',$(".content_slide").height());
        $("#scrollbar1 .viewport").css("height",Math.ceil(viewpheight));
    }
    
    function centerDivToggle()
    {
        var clb_height = $(window).height() - ($('#side_topbar').height()+$('#sidebar_footer').height());
        $('#center_leftbar').css("height",clb_height);
    }
    
    toggleContentFrame();
    centerDivToggle();
    
    $(window).resize(function() { toggleContentFrame(); centerDivToggle(); });
    $(document).resize(function() { toggleContentFrame(); centerDivToggle(); });
</script>