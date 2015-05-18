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
<script type="text/javascript" src="js/dashboard/locationdashboard.js"></script>
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
            <div class="left_text_menu">Location Dashboard</div>
            <div style="float:right">
                <ul id="tabs">
                    <li><a href="#" title="tab1" onclick="$('#subprocessdiv').hide()">1. Profile of Organization</a></li>
                    <li><a href="#" title="tab2" onclick="$('#subprocessdiv').hide()">2. Efficiency by Organization Structure</a></li>
                    <li><a href="#" title="tab3" onclick="$('#subprocessdiv').show()">3. Efficiency by Sub-Process</a></li>
                    <li><a href="#" title="tab4" onclick="$('#subprocessdiv').hide()">4. Efficiency by Client</a></li>
                </ul>
            </div>
        </div>
        <!-- Dashboard Menu ends-->
        
        <!--Date and other parameter selection starts-->
        <div class="date_area">
            <div class="date_right" style="margin-left: 0px;">
                <div class="date_text01" style="margin-left: 5px!important">As on date<span>*</span></div>
                <div class="date_left">
                    <form:select path="date1" id="date1" onchange="onChange(this.id, this.value, 'dat1');getDates2(this.value, 'date2')" ><!-- style="width: 121px; position: absolute; opacity: 0; height: 25px; font-size: 11px;"-->
                        <form:option value="">Select</form:option>
                        <form:options items="${dates1}"></form:options>
                    </form:select>
                </div>
                <div class="date_text01" style="margin-left: 5px!important">Previous Date<span>*</span></div>
                <div class="date_left">
                    <form:select path="date2" id="date2" onchange="onChange(this.id, this.value, 'dat2');" >
                        <form:option value="">Select</form:option>
                        <form:options items="${dates2}"></form:options>
                    </form:select>
                </div>
                <c:if test="${UserBean.userlevel=='3' || UserBean.userlevel=='6'}">
                    <div class="date_text01" style="margin-left: 5px!important">Center<span>*</span></div>
                    <div class="date_left">
                        <form:select id="business1" path="businessCenterId" onchange="onChange(this.id, this.value,'bcid')">
                            <form:option value="">Select</form:option>
                            <form:options items="${dashboardbusinesscenters}"></form:options>
                        </form:select>
                    </div>
                </c:if>
                <c:if test="${UserBean.userlevel=='4'}">                    
                    <input type="hidden" id="business1" value="${defaultLocation}"/>
                </c:if>
                <div id="subprocessdiv" style="display: none">
                    <div class="date_text01" style="margin-left: 5px!important">Sub-Process<span>*</span></div>
                    <div class="date_left">
                        <form:select id="process1" path="processId" onchange="onChange(this.id, this.value,'spid')" cssStyle="width:200px!important">
                            <form:option value="">Select</form:option>
                            <form:options items="${volumesubprocess}"></form:options>
                        </form:select>
                    </div>
                </div>
                <div class="date_text01" style="margin-left: 5px!important">
                    <input type="button" name="generate" id="generate1" value="Display" style="margin-top: 0 !important;"/>
                </div>
            </div>
        </div>
        <!--Date and other parameter selection ends-->

        <!-- Dashboard content div starts -->
        <div style="height: 90%"><!--style="height: 90%"-->
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
                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon1"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L1.1 What are the resources per process for this Center?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv1_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv1')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv1">What is the resource allocation across processes for your center? Are changes consistent with your plan and strategy?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv1"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon2"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L1.2 What has changed?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv2_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv2')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv2">What is the resource allocation across processes for your center? Are changes consistent with your plan and strategy?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv2"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon3"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L1.3 What are the resources per assigned business for this Center?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv3_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv3')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv3">What is the resource allocation across client assignments for your center? Are changes consistent with your plan and strategy?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv3"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon4"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L1.4 What has changed?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv4_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv4')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv4">What is the resource allocation across client assignments for your center? Are changes consistent with your plan and strategy?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv4"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon5"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L1.5 What are the resources per cost center?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv5_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv5')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv5">What is your resource allocation across Cost Centers? Are changes consistent with your plan and strategy?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv5"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon6"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L1.6 What has changed?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv6_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv6')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv6">What is your resource allocation across Cost Centers? Are changes consistent with your plan and strategy?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv6"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon7"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L1.7 What are the resources per employee grade?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv7_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv7')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv7">What is your resource allocation per employee grade? Are changes consistent with your plan and strategy?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv7"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon8"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L1.8 What has changed?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv8_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv8')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv8">What is your resource allocation per employee grade? Are changes consistent with your plan and strategy?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv8"></div>
                                            </div>
                                        </div>

                                    </div>
                                    <div id="tab2" class="tabcontent" style="display: none">
                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon9"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L2.1 What are the resources per Tier for this Center?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv9_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv9')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv9">What is the span of control for this Center? Are changes consistent with your plan and strategy?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv9"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon10"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L2.2 What has changed?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv10_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv10')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv10">What is the span of control for this Center? Are changes consistent with your plan and strategy?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv10"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon11"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L2.3 What is the span of control (% resources per tier)?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv11_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv11')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv11">What is percentage of the Tier 3 resources? Is that the optimum span of control?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv11"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon12"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L2.4 How does Span of Control compare? Tier 3 Comparison to other Centers</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv12_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv12')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv12">How does it compare to other Centers?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv12"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon13"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L2.5 What is the span of control per process?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv13_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv13')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv13">Does each process team in the Center have the appropriate span?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv13"></div>
                                            </div>
                                        </div>

                                        <%--div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon14"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">Chart Description</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv14_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv14')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv14"></div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv14"></div>
                                            </div>
                                        </div--%>

                                    </div>
                                    <div id="tab3" class="tabcontent" style="display: none">
                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon15"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L3.1 For the selected process what is the volume per FTE for each Center Client?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv15_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv15')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv15">What is the level of productivity by Client for Process XYZ in the Center? Should the leading Client be the benchmark for others?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv15"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon16"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L3.2 How does the Center average compare?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv16_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv16')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv16">What is the level of productivity by Client for Process XYZ in the Center? Should the leading Client be the benchmark for others?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv16"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon17"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L3.3 For the selected process What is the labor cost per transaction?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv17_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv17')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv17">What is the cost per transaction by Client for process XYZ in the Center? How does that compare to other Centers?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv17"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon18"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L3.4 How does the center average compare?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv18_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv18')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv18">What is the cost per transaction by Client for process XYZ in the Center? How does that compare to other Centers?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv18"></div>
                                            </div>
                                        </div>

                                    </div>                 
                                    <div id="tab4" class="tabcontent" style="display: none">
                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon19"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L4.1 What are the resources per Client for this Center?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv19_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv19')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv19">How has this Center assigned resources to clients? How has that changed?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv19"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon20"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L4.2 What has changed?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv20_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv20')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv20">How has this Center assigned resources to clients? How has that changed?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv20"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon21"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">L4.3 What are the resources per $Billion for this Center?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv21_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv21')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv21">What is the size of the Client team relative to revenue? Should the leading Client be the benchmark for others?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv21"></div>
                                            </div>
                                        </div>

                                        <div class="expand_style">
                                            <div class="top_bar">
                                                <div class="ex_left01">Chart Description</div>
                                                <div class="information_btn_out" id="infoicon22"><a href="#"><div class="information_btn"><div class="i-image1"></div></div></a></div>
                                                <div class="ex_left02">l4.4 What has changed?</div>
                                                <div class="ex_right">
                                                    <input id="graphdiv22_button" type="button" value="Export" name="" style="margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;" onclick="saveAsImg('graphdiv22')"/>
                                                </div>
                                            </div>
                                            <div class="chartdescdiv" id="chartdescdiv22">What is the size of the Client team relative to revenue? Should the leading Client be the benchmark for others?</div>
                                            <div class="graphOuter">
                                                <div class="graffDiv" id="graphdiv22"></div>
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
                    <div class="side_panel_icon"><a href="#" onclick="downloadPdf()"  title="PDF"><div class="side_button01"><div class="side-image1"></div></div></a></div>
                    <div class="side_panel_icon"><a href="#" onclick="locationdownload()"  title="EXCEL"><div class="side_button01"><div class="side-image2"></div></div></a></div>
                    <div class="side_panel_icon"><a href="#" title="HELP" onclick="$('.prefs-link').click();"><div class="side_button01"><div class="side-image3"></div></div></a></div>  
                    <div class="handle_style"><a href="#preferences" class="prefs-link"><img src="images/slide-handle.png" width="24" height="40" alt="Click here" onclick="reduce();"/></a></div>
                </div>
                <div id="preferences" style="display:none;">
                    <div class="expanded_box01">
                        <div class="get-button_main"><a href="#"  onclick="downloadPdf()"><div class="get-button">PDF<div class="get-image1"></div></div></a></div>
                        <div class="get-button_main"><a href="#"  onclick="locationdownload()"><div class="get-button">EXCEL<div class="get-image2"></div></div></a></div>   
                        <div class="clear"></div>                
                        <div class="get_area">
                            <div class="quote">
                                <div class="q_inner_top">
                                    <p class="q_text">Dashboard: Location</p>
                                    <p>Center specific view of resource, cost, and performance by process and
                                       client assignment. Ability to identify and manage improvements.
                                    </p>
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
</form:form>
<div style="display: none">
    <input type="hidden" id="dat1" value="${date1}"/>
    <input type="hidden" id="dat2" value="${date2}"/>
    <input type="hidden" id="pid" value="${defaultProcess}"/>
    <input type="hidden" id="spid" value="${defaultProcess}"/>
    <input type="hidden" id="bcid" value="${defaultLocation}"/>
    <input type="hidden" id="tier" value="${tier}"/>
    <input type="hidden" id="organization" value="${orgid}"/>
    <input type="hidden" id="tabno" value="1"/>
</div>
<script type="text/javascript" src="js/dashboard/jQueryeffects/jquery.pageslide.min.js"/>
<script>
    $(document).ready(function() {
        $(".content_slide").css("width","94%");
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
    
    //toggleContentFrame();
    //centerDivToggle();
    
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