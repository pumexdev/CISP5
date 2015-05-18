<%-- 
    Document   : vloumeperfte
    Created on : Apr 23, 2015, 11:21:26 AM
    Author     : Vishnu AU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/Target/volumeperftedashboard.js"></script>
<script type="text/javascript" src="js/Target/actioninlineoperations.js"></script>
<script type="text/javascript" src="js/dashboard/dashboard.js"></script>
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

<form:form modelAttribute="TVolumeperFTEDashBoard">
    <div class="global_area_main">
        <!-- Dashboard Menu starts-->
        <div class="d2_menu_nav_out">
            <div class="left_text_menu">Target Dashboard</div>
            <div style="float:right">
                <ul id="tabs">
                    <!--                    <li><a href="#" title="tab1">1. Profile</a></li>-->
                    <!--                    <li><a href="#" title="tab2">2. Organization Structure</a></li>
                                        <li><a href="#" title="tab3">3. Sub-Process Efficiency</a></li>-->
                </ul>
            </div>
        </div>
        <!-- Dashboard Menu ends-->

        <!--Date and other parameter selection starts-->
        <div class="date_area">
            <div class="date_right">
                <div class="date_text01">As on date<span>*</span></div>
                <div class="date_left">
                    <select name="date1" id="date1" onchange="onChange(this.id, this.value, 'dat1');
                            getDates2(this.value, 'date2')" ><!-- style="width: 121px; position: absolute; opacity: 0; height: 25px; font-size: 11px;"-->
                        <option value="">Select</option>
                        <c:forEach var="date" items="${dates1}">
                            <option value="${date.key}">${date.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="date_text01">Previous Date<span>*</span></div>
                <div class="date_left">
                    <select name="date2" id="date2" onchange="onChange(this.id, this.value, 'dat2');" >
                        <option value="">Select</option>
                        <c:forEach var="date" items="${dates2}">
                            <option value="${date.key}">${date.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="date_text01">Sub-Process<span>*</span></div>
                <div class="date_left">
                    <form:select id="subprocess1" path="subProcessId" onchange="onChange(this.id, this.value,'spid')">
                        <form:option value="">Select</form:option>
                        <form:options items="${volumesubprocess}"></form:options>
                    </form:select>
                </div>
                <div class="date_text01">
                    <input type="button" name="generate" id="generate1" value="Display" style="margin-top: 0 !important;"/>
                </div>
            </div>
        </div>
        <!--Date and other parameter selection ends-->

        <!-- Dashboard content div starts -->
        <div style="height: 90%">
            <div class="test01">
                <!--Content slide starts-->
                <div class="content_slide" style="width: 95%;">
                    <!--Scroll bar items starts-->
                    <div id="scrollbar1" style="width: 100%!important;">
                        

                        <div class="viewport" id="style-2">
                            <div class="overview">

                                <!--Tab divs starting-->
                                <div id="content">
                                    <div id="tab1" class="tabcontent">
                                        <div class="expand_style" style="margin-top: 3px;overflow:auto; height:100%;min-height:750px;"><!--min-height:750px;-->
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon11"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">S3.2 What is the volume per FTE? How does it compare to Target?How has that changed?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv11_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv11')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv11">What is the level of productivity by Center for this Sub-Process? Should the leading Center be the benchmark for others?</div>
                                            <div class="graphOuter">
                                                <!--New Design-->
                                                <div class="box_expand_style  expand_box1">
                                                    <div class="top_bar">
                                                        <div class="txt_head01 expand_box_textleft">Volume</div>
                                                        <div class="txt_head01 expand_box_textleft">FTE's</div>
                                                        <div class="txt_head01 expand_box_textleft">Volume per FTE</div>
                                                    </div>
                                                    <div class="box_content">
                                                        <div class="txt_head01 expand_box_textleft vol1">0</div>
                                                        <div class="txt_head01 expand_box_textleft fte1">0</div>
                                                        <div class="txt_head01 expand_box_textleft vf1">0</div>
                                                    </div>
                                                </div>
                                                <div class="box_expand_style  expand_box2 expand_box_leftmargin ">
                                                    <div class="top_bar">
                                                        <div class="txt_head01 expand_box_textleft" style="width: 54% !important;">Target</div>
                                                        <div style="width: 10%;float: right;padding-top: 10px;"><img id="editT" onclick="activateTarget(this)"src="images/edit.png" alt="Edit" title="Edit"  style="cursor: pointer"/></div>
                                                    </div>
                                                    <div class="box_content box_background_grey" style="height:25px;">
                                                        <table width="100%" cellspacing="0" cellpadding="0" border="0">
                                                            <tbody id="targettab">                                                           
                                                                <tr>
                                                                    <td style="width: 100%;float: none;text-align: right;padding: 0px;" id="targetv"><input type='text' style="text-align: right; margin-left: 13%!important; margin-right: 18%!important;min-width: 65%!important;" id='target' class="target1" maxlength="10" disabled="true"/></td>
                                                                    <!--<td style="width: 29%;float: right;">img id="metricedit${loop.index+1}" onclick="targetEditClick(target.value,this)"src="images/edit.png" alt="Edit" title="Edit"  style="cursor: pointer"/</td>-->
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                            <!--<h3 class="expand_box_textcenter target1">${target1.target}</h3>-->
                                                    </div>
                                                </div>
                                                <!--                                                    <div class="chart_box expand_box1 box_background_grey" style="margin-left: 2.5%;">
                                                                                                    <div class="chart_box_text"> <div class="graffDiv" id="graphdiv11"></div> </div>
                                                                                                </div>-->
                                                <div class="chart_box expand_box1 box_background_grey" style="margin-left: 2.5%;">
                                                    <div class="chart_box_text"> <div class="graffDiv" id="graphdiv12"></div> </div>
                                                </div>
                                                <div class="box_expand_style_1  expand_box2 expand_box_leftmargin ">
                                                    <div class="top_bar">
                                                        <div class="txt_head01 expand_box_textleft" style="width: 61% !important;">Benchmarks</div>
                                                        <div style="width: 10%;float: right;padding-top: 10px;"><img id="editT" onclick="activateBenchMark(this);"src="images/edit.png" alt="Edit" title="Edit"  style="cursor: pointer"/></div>
                                                    </div>
                                                    <div>
                                                        <div class="expand_box3 box_border_right box_textf" style="width: 49%;">High (Best)</div>
                                                        <div class="expand_box3 box_textf2" id="high1"><input type='text' style="text-align: right;min-width: 85% !important;margin-top: 12%;" maxlength="10" id='high' class="high1" disabled="true"/></div>
                                                        <div class="expand_box3 box_border_right box_textf3" style="width: 49%;">Medium</div>
                                                        <div class="expand_box3 box_textf4"><input type='text' style="text-align: right;min-width: 85% !important;margin-top: 12%;" id='medium' maxlength="10" class="medium1" disabled="true"/></div>
                                                        <div class="expand_box3 box_border_right box_textf" style="width: 49%;">Low</div>
                                                        <div class="expand_box3 box_textf2"><input type='text' style="text-align: right;min-width: 85% !important;margin-top: 12%;" id='low' maxlength="10" class="low1" disabled="true"/></div>



                                                    </div>
                                                </div>
                                                <div class="box_expand_style  expand_box1" style="margin-bottom: 2.5%;">
                                                    <!--                                                    <div class="top_bar">
                                                                                                            <div class="txt_head01 expand_box_textleft">Action</div>
                                                                                                            <div class="txt_head01 expand_box_textleft">Type</div>
                                                                                                            <div class="txt_head01 expand_box_textleft">% Complete</div>
                                                                                                        </div>-->
                                                    <div style="overflow-y: auto; max-height: 300px; min-height: 50px;">
                                                        <table width="100%" cellspacing="0" cellpadding="0" border="0" id="actiontable1">
                                                            <thead class="top_bar">
                                                                <tr>
                                                                    <th class="txt_head01" width='50%'>Action</th>
                                                                    <th class="txt_head01" width='20%'>Type</th>
                                                                    <th class="txt_head01" width='20%'>% Complete</th>
                                                                    <th class="txt_head01" width='10%'><!--Operations--></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="tbody1">                                                           

                                                            </tbody>
                                                        </table>
                                                        <!--<div class="box_bottom_area"><input type="button" value="Add item" class="add_item_button box_background_green"><input type="button" value="Edit List" class="add_item_button box_background_darkgrey"></div>-->
                                                    </div>
                                                </div>
                                                <!--End of design-->
                                                <!--                                                <div class="graffDiv" id="graphdiv11"></div>-->
                                            </div>
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
                    <div class="side_panel_icon"><a href="#" title="PDF" onclick="downloadPdf()"><div class="side_button01"><div class="side-image1"></div></div></a></div>
                    <div class="side_panel_icon"><a href="#" title="EXCEL" onclick="targetdownload()"><div class="side_button01"><div class="side-image2"></div></div></a></div>
                    <div class="side_panel_icon"><a href="#" title="HELP"  onclick="$('.prefs-link').click();"><div class="side_button01"><div class="side-image3"></div></div></a></div>  
                    <div class="handle_style"><a href="#preferences" class="prefs-link"><img src="images/slide-handle.png" width="24" height="40" alt="Click here" onclick="reduce();"/></a></div>
                </div>
                <div id="preferences" style="display:none;">
                    <div class="expanded_box01">
                        <div class="get-button_main"><a href="#" onclick="downloadPdf()"><div class="get-button">PDF<div class="get-image1"></div></div></a></div>
                        <div class="get-button_main"><a href="#" onclick="targetdownload()"><div class="get-button">EXCEL<div class="get-image2"></div></div></a></div>   
                        <div class="clear"></div>                
                        <div class="get_area">
                            <div class="quote">
                                <div class="q_inner_top">
                                    <p class="q_text">Dashboard: Sub-Process</p>
                                    <p>View of sub-process resources, cost and performance across centers.
                                        Ability to drive improvement opportunities across centers, and spot trends.</p>
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
        <input type="hidden" id="tabno" value="1"/>
    </div>
</form:form>
<script type="text/javascript" src="js/dashboard/jQueryeffects/jquery.pageslide.min.js"/>
<script>
                        $(document).ready(function () {
                            $(".content_slide").css("width", "94%");
                        });

                        function reduce()
                        {
                            $(".content_slide").css("width", "69%");
                             $(".expand_box1").css("width", "30%");
                             $(".expand_box2").css("width", "15%");
                            toggleContentFrame();
                        }
                        function expand()
                        {
                            $(".content_slide").css("width", "94%");
                            $(".expand_box1").css("width", "61.6%");
                            $(".expand_box2").css("width", "28%");
                            toggleContentFrame()
                        }
                        function toggleContentFrame()
                        {
                            var framewidth = $(".content_slide").width() * 0.93;
                            var viewpheight = $(window).height() * 0.7;
                            $("#contentframe").attr('width', Math.ceil(framewidth));
                            $("#contentframe").attr('height', $(".content_slide").height());
                            $("#scrollbar1 .viewport").css("height", Math.ceil(viewpheight));
//                            var expandboxWidth=$(".expand_box1").width()*0.93;
                            
//                             expandboxWidth=$(".expand_box2").width()*0.93;
//                             $("#expand_box2").css("width", Math.ceil(expandboxWidth));
                        }

                        function centerDivToggle()
                        {
                            var clb_height = $(window).height() - ($('#side_topbar').height() + $('#sidebar_footer').height());
                            $('#center_leftbar').css("height", clb_height);
                        }

                        //toggleContentFrame();
                        //centerDivToggle();

                        $(window).resize(function () {
                            toggleContentFrame();
                            centerDivToggle();
                        });
                        $(document).resize(function () {
                            toggleContentFrame();
                            centerDivToggle();
                        });

                        $(document).ready(function ()
                        {
                            $('.theme').click(function ()
                            {
                                var value = $(this).attr("id");
                                var file = "css/" + value + ".css";
                                $('.theme').each(function ()
                                {
                                    $(this).removeClass('selected');
                                });
                                $(this).addClass('selected');
                                $("#theme").attr("href", file);
                                return false;
                            });
                            $(".prefs-link").pageslide({modal: true});
                        });
</script>