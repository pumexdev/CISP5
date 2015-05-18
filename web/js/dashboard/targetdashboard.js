/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function()
{
    $("#scrollbar1 .viewport").css("height", 450);
    $("#scrollbar1").tinyscrollbar();
    $("#scrollbar1").tinyscrollbar_update();
    $("#generate1").on('click', function()
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
                    targetEfficiency();
                    break;
                case 2 :
//                    TargetStructure();
//                    getActionDetailes();
                    break;
                case 3 :
//                    targetEfficiency();
                    break;
            }
        }
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

function targetEfficiency()
{
    var array1 = new Array();
    var array2 = new Array();
    var date1 = $("#date1").val();
    var date2 = $("#date2").val();
    var spid = $("#spid").val();
    var tier = $("#tier").val();
    var bcid = $("#bcid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var target1;
    var params = {date: array1, spid: spid, bcid: bcid, tier: tier};
    changeGraph('ajaxtargeteffgraph1.htm', params, array1, array2, 'Sub-Process Volume per FTE', 'Center', 'Volume', 'graphdiv11', 'graphdiv12');
    changeGraph('ajaxtargeteffgraph2.htm', params, array1, array2, 'Average labor cost per transaction', 'Center', 'Average Labor cost', 'graphdiv13', 'graphdiv14');
    drawTarget("ajaxtargeteffgraphT31.htm", params, "high1", "medium1", "low1", "target1");
    drawTarget("ajaxtargeteffgraphT33.htm", params, "high2", "medium2", "low2", "target2");
    drawTgtTable("ajaxtargeteffgraphL3G1tab.htm", params, "vol1", "fte1", "vf1");
    drawTgtTable("ajaxtargeteffgraphL3G2tab.htm", params, "x2", "y2", "xy2");

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
    changeGraph('ajaxsubprocessgraphtarget2.htm', params, array1, array2, 'Average labor cost per FTE', 'Center', 'Cost $', 'graphdiv3', 'graphdiv4');
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

function processefficiency3_4()
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
    changeGraph('ajaxprocessefficiency2.htm', params, array1, array2, '% Resources per Tier', 'Tier', '% Resource', 'graphdiv8', 'graphdiv9');
    //changeBarGraph('ajaxprocessefficiency2.htm', params, array1, array2,'% Resources per Tier','Tier','% Resource', 'graphdiv8', 'graphdiv9');
}

function targetdownload()
{
    if (subProcessDashboardValidate())
        window.open("targetdashboardexcel.htm?d1=" + encodeURIComponent($("#dat1").val()) + "&d2=" + encodeURIComponent($("#dat2").val()) + "&spid=" + encodeURIComponent($("#spid").val()) + "&bcid=" + encodeURIComponent($("#bcid").val()) + "&tier=" + encodeURIComponent($("#tier").val()));
}

function getActionDetailes() {
    var processid = $("#subprocess1").val();
    $.ajax({
        type: 'POST',
        datatype: 'json',
        cache: false,
        url: 'getactiondetailsprocess.htm?pid=' + processid,
        success: function(json)
        {
            var data = json.actionlist;
            var html = '';
            $('#tbody1').html("<tr class='box_table_height'><td colspan='3' style='text-align : center;'>No Results Found</td></tr>");
            $('#tbody2').html("<tr class='box_table_height'><td colspan='3' style='text-align : center;'>No Results Found</td></tr>");
            $('#tbody3').html("<tr class='box_table_height'><td colspan='3' style='text-align : center;'>No Results Found</td></tr>");
            for (var i = 0; i < data.length; ++i)
            {

                html += "<tr class='box_table_height'>";
                html += "<td width='40%' align='center'><span>" + data[i].ActionName + "</span></td>";
                html += "<td width='20%' align='center'>" + data[i].ActionType + "</td>";
                html += "<td width='20%' align='center'>" + data[i].Percentage + "%</td>";
                html += "<td width='20%' align='center'><img width='14' height='14' alt='edit' id='edit" + i + "' src='images/edit.png' onclick='editactions(" + data[i].ActionsId + ", \"" + data[i].ActionName + "\", \"" + data[i].ActionType + "\",\"" + data[i].Percentage + "\");'>&nbsp;&nbsp;<img width='14' height='14' id='delete" + i + "'alt='' src='images/cross.png' onclick='deleteaction(" + data[i].ActionsId + ");'></td></tr>";
                $('#tbody1').html(html);
                $('#tbody2').html(html);
                $('#tbody3').html(html);
            }
        }
    });
}

function deleteaction(actionId) {
    var result = confirm("Do you want to delete the action?");
    if (result) {
        alert('Remove the Action : ' + actionId)
        alert('Id : ' + this.id);
    }
}

function  editactions(actionId, name, actiontype, percentage) {
    alert('Id : ' + actionId);
    alert('Id : ' + name);
    alert('Id : ' + actiontype);
    alert('Id : ' + percentage);
}
