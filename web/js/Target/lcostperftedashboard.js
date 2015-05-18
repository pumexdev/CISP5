/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function ()
{
//    $("#scrollbar1 .viewport").css("height", 450);
//    $("#scrollbar1").tinyscrollbar();
//    $("#scrollbar1").tinyscrollbar_update();
var framewidth = $(".content_slide").width() * 0.93;
                            var viewpheight = $(window).height() * 0.7;
                            $("#contentframe").attr('width', Math.ceil(framewidth));
                            $("#contentframe").attr('height', $(".content_slide").height());
                            $("#scrollbar1 .viewport").css("height", Math.ceil(viewpheight));
    $("#generate1").on('click', function ()
    {
        var tabno = $("#tabno").val();
        tabno = parseInt(tabno, 10);
        if (subProcessDashboardValidate())
        {
            switch (tabno)
            {
                case 1 :
                    TargetStructure();
                    getActionDetailes();
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
            }
        }
        $("#scrollbar1").tinyscrollbar_update();
    });
});



function subProcessDashboardValidate()
{
    var date1 = $("#date1").val();
    var date2 = $("#date2").val();
    var subprocess = $("#subprocess1").val();
    if (date1 == "")
    {
        alert("Select As on date");
        return false;
    }
    else if (date2 == "" || date2 == '0')
    {
        alert("Select Previous Date");
        return false;
    }
    else if (subprocess == "")
    {
        alert("Select Sub-Process");
        return false;
    }
    else
    {
        return true;
    }
}


function downloadPdf()
{
    var spid = $("#spid").val();
    var subprocesstext = encodeURIComponent(returnOptionText('subprocess1', spid));
    if (subProcessDashboardValidate())
        window.open("targetpdf.htm?name=" + subprocesstext + "&d1=" + encodeURIComponent($("#dat1").val()) + "&d2=" + encodeURIComponent($("#dat2").val()) + "&spid=" + encodeURIComponent($("#spid").val()) + "&bcid=" + encodeURIComponent($("#bcid").val()) + "&tier=" + encodeURIComponent($("#tier").val()));
}

function TargetStructure()
{
    var array1 = new Array();
    var array2 = new Array();
    var date1 = $("#date1").val();
    var date2 = $("#date2").val();
    var spid = $("#spid").val();
    var subprocesstext = returnOptionText('subprocess1', spid);
    var tier = $("#tier").val();
    var bcid = $("#bcid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params = {date: array1, spid: spid, bcid: bcid, tier: tier};
    changeGraph2('ajaxsubprocessorggraph2.htm', params, array1, array2,'% Resources per Tier','Tier','% Resources', 'graphdiv4');
//    changeGraph('ajaxsubprocessorggraph2.htm', params, array1, array2,'% Resources per Tier','Tier','% Resources', 'graphdiv8', 'graphdiv9');
//    changeGraph('ajaxsubprocessgraphtarget2.htm', params, array1, array2, 'Average labor cost per FTE', 'Center', 'Cost $', 'graphdiv3', 'graphdiv4');
    drawTarget("ajaxtargeteffgraphT13.htm", params, "high3", "medium3", "low3", "target3");
    drawTgtTable("ajaxtargeteffgraphL1G1tab.htm", params, "x3", "y3", "xy3");
}

function processefficiency1_2()
{
    var array1 = new Array();
    var array2 = new Array();
    var processid = $("#process1").val();
    var date1 = $("#date1").val();
    var date2 = $("#date2").val();
    var tier = $("#tier").val();
    var bcid = $("#bcid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params = {date: array1, pid: processid, tier: tier, bcid: bcid};
    changeGraph('ajaxprocessefficiency3.htm', params, array1, array2, 'Tier 3 Comparison', 'Center', '(% Span of control)', 'graphdiv10');
    //changeBarGraph('ajaxprocessefficiency1.htm', params, array1, array2,'Resources per Tier','Tier','Resources', 'graphdiv6', 'graphdiv7');
}

function targetdownload()
{
    if (subProcessDashboardValidate())
        window.open("targetdashboardexcel.htm?d1=" + encodeURIComponent($("#dat1").val()) + "&d2=" + encodeURIComponent($("#dat2").val()) + "&spid=" + encodeURIComponent($("#spid").val()) + "&bcid=" + encodeURIComponent($("#bcid").val()) + "&tier=" + encodeURIComponent($("#tier").val()));
}

function targetEditClick(target, rowid) {
    var newhtml = "";
    newhtml = newhtml + "<td style='width: 69%;float: none;text-align: right;'><input type='text' style='text-align: right;' class='target3' id='target' value='" + target + "'/></td>";
    newhtml = newhtml + "<td style='width: 29%;float: right;'><img id='tedit1' onclick='targetSaveClick(target.value,this)' src='images/ticker.png' alt='Edit' title='Edit'  style='cursor: pointer'/></td>";
    newhtml = newhtml + "";
    $(rowid).parent().parent().html(newhtml);
    $("#high").prop('disabled', false);
    $("#medium").prop('disabled', false);
    $("#low").prop('disabled', false);
    //$("#high").disabled(false);
}

function targetSaveClick(target, rowid) {
    if (targetValidation()) {
        saveTarget();
        var newhtml = "";
        newhtml = newhtml + "<td style='width: 69%;float: none;text-align: right;'><input type='text' class='target3' style='text-align: right;' id='target' value='" + target + "' disabled='true'/></td>";
        newhtml = newhtml + "<td style='width: 29%;float: right;'><img id='tedit1' onclick='targetEditClick(target.value,this)' src='images/edit.png' alt='Edit' title='Edit'  style='cursor: pointer'/></td>";
        newhtml = newhtml + "";
        $(rowid).parent().parent().html(newhtml);
        $("#high").prop('disabled', true);
        $("#medium").prop('disabled', true);
        $("#low").prop('disabled', true);
    }
}

function saveTarget() {
    var spid = $("#spid").val();
    var metricId = 3;
    var target = $("#target").val();
    var high = $("#high").val();
    var medium = $("#medium").val();
    var low = $("#low").val();
    var params = {spid: spid, metricId: metricId, target: target, high: high, medium: medium, low: low};
    $.ajax(
            {
                type: 'POST',
                datatype: 'json',
                cache: false,
                url: 'savetarget.htm',
                data: params,
                success: function (json)
                {

                }
            });
}


function targetValidation() {
    var subprocess = $("#subprocess1").val();
    var target = $("#target").val();
    target=target.replace(/,/g,'');
    var high = $("#high").val();
    var medium = $("#medium").val();
    var low = $("#low").val();
    if (subprocess == "")
    {
        alert("Please select subprocess");
        return false;
    } else if (target == "") {
        alert("Please enter valid target!");
        $("#target").focus();
        return false;
    } else if (!$.isNumeric(target)) {
        alert("Please enter number!");
        $("#target").focus();
        return false;
    } else {
        return true;
    }
}

function benchmarkValidation() {
    var subprocess = $("#subprocess1").val();
    var target = $("#target").val();
    var high = $("#high").val();
    high=high.replace(/,/g,'');
    var medium = $("#medium").val();
    medium=medium.replace(/,/g,'');
    var low = $("#low").val();
    low=low.replace(/,/g,'');
    if (subprocess == "")
    {
        alert("Please select subprocess");
        return false;
    } else if (high == "") {
        alert("Please enter valid target High!!");
        return false;
    } else if (!$.isNumeric(high)) {
        alert("Please enter number!");
        $("#high").focus();
        return false;
    } else if (medium == "") {
        alert("Please enter valid target Medium!");
        return false;
    } else if (!$.isNumeric(medium)) {
        alert("Please enter number!");
        $("#medium").focus();
        return false;
    } else if (low == "") {
        alert("Please enter valid target Low!");
        return false;
    } else if (!$.isNumeric(low)) {
        alert("Please enter number!");
        $("#low").focus();
        return false;
    } else {
        return true;
    }
}

function activateTarget(rowid) {
    $("#target").prop('disabled', false);
    var newhtml = "";
    newhtml = newhtml + "<div class='txt_head01 expand_box_textleft' style='width: 54% !important;'>Target</div>";
    newhtml = newhtml + "<div style='width: 10%;float: right;padding-top: 10px;'><img id='editT' onclick='deactivateTarget(this)'src='images/ticker.png' alt='Save' title='Save'  style='cursor: pointer'/></div>";
//    var newhtml="<img id='editT' onclick='deactivateTarget(this)'src='images/ticker.png' alt='Save' title='Save'  style='cursor: pointer'/>";
    $(rowid).parent().parent().html(newhtml);
//    $("#editT").prop('src', 'images/ticker.png');
//    $("#editT").prop('onclick', 'deactivateTarget()');
}

function deactivateTarget(rowid) {
    if (targetValidation()) {
        saveTarget();
        $("#target").prop('disabled', true);
        var newhtml = "";
        newhtml = newhtml + "<div class='txt_head01 expand_box_textleft' style='width: 54% !important;'>Target</div>";
        newhtml = newhtml + "<div style='width: 10%;float: right;padding-top: 10px;'><img id='editT' onclick='activateTarget(this)'src='images/edit.png' alt='Edit' title='Edit'  style='cursor: pointer'/></div>";
//    newhtml="<img id='editT' onclick='activateTarget(this)'src='images/ticker.png' alt='Save' title='Save'  style='cursor: pointer'/>";
        $(rowid).parent().parent().html(newhtml);
//    $("#editT").prop('src', 'images/edit.png');
//    $("#editT").prop('onclick', 'activateTarget()');
    }
}

function activateBenchMark(rowid) {
    var newhtml = "";
    newhtml = newhtml + "<div class='txt_head01 expand_box_textleft' style='width: 61% !important;'>Benchmarks</div>";
    newhtml = newhtml + "<div style='width: 10%;float: right;padding-top: 10px;'><img id='editT' onclick='deactivateBenchMark(this)'src='images/ticker.png' alt='Save' title='Save'  style='cursor: pointer'/></div>";
    $(rowid).parent().parent().html(newhtml);
    $("#high").prop('disabled', false);
    $("#medium").prop('disabled', false);
    $("#low").prop('disabled', false);
}

function deactivateBenchMark(rowid) {
    if(benchmarkValidation()){
        saveTarget();
    var newhtml = "";
    newhtml = newhtml + "<div class='txt_head01 expand_box_textleft' style='width: 61% !important;'>Benchmarks</div>";
    newhtml = newhtml + "<div style='width: 10%;float: right;padding-top: 10px;'><img id='editT' onclick='activateBenchMark(this)'src='images/edit.png' alt='Edit' title='Edit'  style='cursor: pointer'/></div>";
    $(rowid).parent().parent().html(newhtml);
    $("#high").prop('disabled', true);
    $("#medium").prop('disabled', true);
    $("#low").prop('disabled', true);
    }
}