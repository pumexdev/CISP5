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
<script type="text/javascript" src="js/dashboard/connecteddashboard.js"></script>
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
            <div class="left_text_menu">Connected Dashboard</div>
            <div style="float:right">
                <ul id="tabs">
                    <li><a href="#" title="tab1" id="atab1">Level 1: Outcome Metric</a></li>
                    <li><a href="#" title="tab2" id="atab1">Level 2: Primary Drivers</a></li>
                    <li><a href="#" title="tab3" id="atab1">Level 3: Secondary Drivers</a></li>
                </ul>
            </div>
        </div>
        <!-- Dashboard Menu ends-->
        
        <!--Date and other parameter selection starts-->
        <div class="date_area" style="padding-right: 73px !important;">
            <div class="date_right" style="padding-right: 0px!important;width: 100%!important;float: none">
                <div class="date_text01">As on date<span>*</span></div>
                <div class="date_left">
                    <form:select path="date1" id="date1" onchange="onChange(this.id, this.value, 'dat1');getDates2(this.value, 'date2')" ><!-- style="width: 121px; position: absolute; opacity: 0; height: 25px; font-size: 11px;"-->
                        <form:option value="">Select</form:option>
                        <form:options items="${dates1}"></form:options>
                    </form:select>
                </div>
                <div class="date_text01">Previous Date<span>*</span></div>
                <div class="date_left">
                    <form:select path="date2" id="date2" onchange="onChange(this.id, this.value, 'dat2');" >
                        <form:option value="">Select</form:option>
                        <form:options items="${dates2}"></form:options>
                    </form:select>
                </div>
                <div class="date_text01">Sub-Process<span>*</span></div>
                <div class="date_left">
                    <form:select id="subprocess" path="subProcessId" onchange="onChange(this.id, this.value,'spid');">
                        <form:option value="">Select</form:option>
                        <form:options items="${volumesubprocess}"></form:options>
                    </form:select>
                </div>
                <input id="status" type="hidden" value="1"/>
            </div>
        </div>
        
        <div class="date_area" style="padding-right: 73px !important;">
            <div class="date_right" style="padding-right: 0px!important;width: 100%!important;float: none">
                <div class="date_text01" >
                    <input type="radio" name="status" id="status2" value="2" style="margin-right: 10px;margin-left: 10px" onclick="onChange(this.id, this.value, 'status');showhide()"/>By Center
                    <input type="radio" name="status" id="status1" value="1" style="margin-right: 10px;margin-left: 10px" checked="true" onclick="onChange(this.id, this.value, 'status');showhide()"/>By client for a center
                </div>
                <div id="bctr">
                    <c:if test="${UserBean.userlevel=='3' || UserBean.userlevel=='6'}">
                        <div class="date_text01" >Center<span>*</span></div>
                        <div class="date_left" style="">
                            <form:select id="business1" path="businessCenterId" onchange="onChange(this.id, this.value,'bcid')">
                                <form:option value="">Select</form:option>
                                <form:options items="${dashboardbusinesscenters}"></form:options>
                            </form:select>
                        </div>
                    </c:if>
                    <c:if test="${UserBean.userlevel=='4'}">                    
                        <input type="hidden" id="business1" value="${defaultLocation}"/>
                    </c:if>
                </div>
                    
                <div class="date_text01">
                    <input type="button" name="generate" id="generate1" value="Display" style="margin-top: 0 !important;"/>
                </div>
            </div>
        </div>
            
        <!--Date and other parameter selection ends-->       
       
        <!-- Dashboard content div starts -->
        <div style="height: 90%"><!--style="height: 90%"-->
            <div class="test01">
                <!--Content slide starts-->
                <div class="content_slide" style="width: 92%;">

                    <!--Scroll bar items starts-->
                    <div id="scrollbar1">
<!--                        <div class="scrollbar">
                            <div class="track">
                                <div class="thumb">
                                    <div class="end"></div>
                                </div>
                            </div>
                        </div>-->

                        <div class="viewport" id="style-2">
                            <div class="overview">
                                <!--div class="connect_style" id="leveldesc1">Level 1 - This is your <span>cost per transaction </span>for process XYZ</div>
                                <Tab divs starting-->
                                <div id="content">
                                    <div id="tab1" class="tabcontent">
                                        <div id="level1">
                                            <table id="kpitable1" style="width: 100%;">
                                                <tr>
                                                    <td>
                                                        <div class="expand_style">
                                                            <div class="top_bar">
                                                                <div class="ex_left01">Chart Description</div>
                                                                <div class="information_btn_out" id="infoicon1"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                                <div class="ex_left02">C1.1</div>
                                                                <div class="ex_right">
                                                                    <input id="graphdiv1_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv1')"/>
                                                                </div>
                                                            </div>
                                                            <div class="chartdescdiv" id="chartdescdiv1"></div>
                                                            <div class="graphOuter">
                                                                <div class="graffDiv" id="graphdiv1"></div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div id="tab2" class="tabcontent" style="display: none">
                                        <div id="level2">
                                            <table id="kpitable2" style="width: 100%;">
                                                <tr>
                                                    <td>
                                                        <div class="expand_style">
                                                            <div class="top_bar">
                                                                <div class="ex_left01">Chart Description</div>
                                                                <div class="information_btn_out" id="infoicon2"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                                <div class="ex_left02">C2.1 Productivity</div>
                                                                <div class="ex_right">
                                                                    <input id="graphdiv2_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv2')"/>
                                                                </div>
                                                            </div>
                                                            <div class="chartdescdiv" id="chartdescdiv2">What are the factors influencing performance?</div>
                                                            <div class="graphOuter">
                                                                <div class="graffDiv" id="graphdiv2"></div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr id="g3">
                                                    <td>
                                                        <div class="expand_style">
                                                            <div class="top_bar">
                                                                <div class="ex_left01">Chart Description</div>
                                                                <div class="information_btn_out" id="infoicon3"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                                <div class="ex_left02">C2.2 Labor cost</div>
                                                                <div class="ex_right">
                                                                    <input id="graphdiv3_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv3')"/>
                                                                </div>
                                                            </div>
                                                            <div class="chartdescdiv" id="chartdescdiv3">What are the factors influencing performance?</div>
                                                            <div class="graphOuter">
                                                                <div class="graffDiv" id="graphdiv3"></div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr id="g4">
                                                    <td>
                                                        <div class="expand_style">
                                                            <div class="top_bar">
                                                                <div class="ex_left01">Chart Description</div>
                                                                <div class="information_btn_out" id="infoicon4"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                                <div class="ex_left02">C2.3 Span of control</div>
                                                                <div class="ex_right">
                                                                    <input id="graphdiv4_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv4')"/>
                                                                </div>
                                                            </div>
                                                            <div class="chartdescdiv" id="chartdescdiv4">What are the factors influencing performance?</div>
                                                            <div class="graphOuter">
                                                                <div class="graffDiv" id="graphdiv4"></div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div id="tab3" class="tabcontent"  style="display: none">
                                        <div id="level3">
                                            <table id="kpitable3" style="width: 100%;">
                                                <tbody></tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <!--Tab divs ends-->
                            </div>
                        </div>
                    </div>
                    <!--Scroll bar items ends-->
                </div>
                <!--Content slide ends-->

                <!--Side Panel starts here-->
                <div class="slide_right_panel">
                    <div class="side_panel_icon"><a href="#" title="PDF"><div class="side_button01"><div class="side-image1"></div></div></a></div>
                    <div class="side_panel_icon"><a href="#" title="EXCEL"><div class="side_button01"><div class="side-image2"></div></div></a></div>
                    <div class="side_panel_icon"><a href="#" title="HELP"  onclick="$('.prefs-link').click();"><div class="side_button01"><div class="side-image3"></div></div></a></div>  
                    <div class="handle_style"><a href="#preferences" class="prefs-link"><img src="images/slide-handle.png" width="24" height="40" alt="Click here" onclick="reduce();"/></a></div>
                </div>
                <div id="preferences" style="display:none;">
                    <div class="expanded_box01">
                        <div class="get-button_main"><a href="#"><div class="get-button">PDF<div class="get-image1"></div></div></a></div>
                        <div class="get-button_main"><a href="#"><div class="get-button">EXCEL<div class="get-image2"></div></div></a></div>   
                        <div class="clear"></div>                
                        <div class="get_area">
                            <div class="quote">
                                <div class="q_inner_top">
                                    <p class="q_text">Flexible KPI Reporting</p>
                                    <p>Additional dashboards and KPI’s can be set up to complete your performance reporting needs, such as</p>
                                    <div class="clear">
                                        <ul style="line-height: 12px;padding-top: 10px;list-style: outside">
                                            <li style="color: black">Cycle times</li>
                                            <li style="color: black">Accuracy rates</li>
                                            <li style="color: black">Automation</li>
                                            <li style="color: black">Process compliance</li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="q_bottom"></div>
                            </div>   
                        </div>
                    </div>                
                    <div  class="handle_style2"><a  href="javascript:$.pageslide.close()" onclick="expand();" ><img src="images/slide-handle-right.png" width="24" height="40" alt="Slide" /></a></div>
                </div>
                <!--Side Panel ends here-->
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
        <input type="hidden" id="tabno" value="1"/>
    </div>
</form:form>
<script type="text/javascript" src="js/dashboard/jQueryeffects/jquery.pageslide.min.js"/>
<script>
    $(document).ready(function() {
        $(".content_slide").css("width","94%");
        $("#pageslide").css("margin-top","255");
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
    
    $(document).ready(function() 
    {
        $('.theme').click(function()
        {
            var value = $(this).attr("id");
            var file = "css/"+value+".css";
            $('.theme').each(function ()
            {
                $(this).removeClass('selected');
            });
            $(this).addClass('selected');
            $("#theme").attr("href", file);
            return false;
        });
        $(".prefs-link").pageslide({ modal: true });
    });
</script>