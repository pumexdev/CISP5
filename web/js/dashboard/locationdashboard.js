var graph1=["locationprofile1.htm?","locationprofile2.htm?","locationprofile3.htm?"];
var graph2=["locationorgefficiency1.htm?","locationorgefficiency2.htm?","locationorgefficiency3.htm?","locationorgefficiency4.htm?","locationorgefficiency5.htm?"];
var graph3=["locationprocess1.htm?","locationprocess2.htm?","locationprocess3.htm?","locationprocess4.htm?"];
var graph4=["businessassignment1.htm?","businessassignment2.htm?"];

$(document).ready(function()
{
//    $("#scrollbar1 .viewport").css("height", 450);
//    $("#scrollbar1").tinyscrollbar();
//    $("#scrollbar1").tinyscrollbar_update();
var framewidth = $(".content_slide").width() * 0.93;
                            var viewpheight = $(window).height() * 0.7;
                            $("#contentframe").attr('width', Math.ceil(framewidth));
                            $("#contentframe").attr('height', $(".content_slide").height());
                            $("#scrollbar1 .viewport").css("height", Math.ceil(viewpheight));
    $("#generate1").on('click',function()
    {
        $("#scrollbar1").tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update();
        var tabno = $("#tabno").val();
        tabno = parseInt(tabno, 10);
        if(locationDashboardValidate(0))
        {
            switch(tabno)
            {
                case 1 :
                    locationprofile();
                    break;
                case 2 :
                    locationefficiency();
                    break;
                case 3 :
                    locationprocessefficiency();
                    break;
                case 4 :
                    locationbusiness();
                    break;
            }
        }
    });
});
function generateGraph1(date1,date2,bcid)
{
    $("#graph1").attr("src",graph1[0]+"d1="+date1+"&bcid="+bcid);
    $("#graph2").attr("src",graph1[0]+"d1="+date1+"&d2="+date2+"&bcid="+bcid);
    $("#graph3").attr("src",graph1[1]+"d1="+date1+"&bcid="+bcid);
    $("#graph4").attr("src",graph1[1]+"d1="+date1+"&d2="+date2+"&bcid="+bcid);
    $("#graph17").attr("src",graph1[2]+"d1="+date1+"&bcid="+bcid);
    $("#graph18").attr("src",graph1[2]+"d1="+date1+"&d2="+date2+"&bcid="+bcid);
}

function generateGraph2(date1,date2,pid,tier,bcid)
{
    $("#graph5").attr("src",graph2[0]+"d1="+date1+"&bcid="+bcid);
    $("#graph6").attr("src",graph2[0]+"d1="+date1+"&d2="+date2+"&bcid="+bcid);
    $("#graph7").attr("src",graph2[1]+"d1="+date1+"&bcid="+bcid);
    $("#graph8").attr("src",graph2[2]+"d1="+date1+"&d2="+date2+"&tier="+tier);
    $("#graph9").attr("src",graph2[3]+"d1="+date1+"&bcid="+bcid+"&tier="+tier+"&pid="+pid);
    $("#graph10").attr("src",graph2[4]+"d1="+date1+"&bcid="+bcid+"&pid="+pid+"&tier="+tier);
}

function generateGraph3(date1,date2,pid,bcid)
{
    $("#graph11").attr("src",graph3[0]+"d1="+date1+"&pid="+pid+"&bcid="+bcid);
    $("#graph12").attr("src",graph3[1]+"d1="+date1+"&d2="+date2+"&pid="+pid+"&bcid="+bcid);
    $("#graph13").attr("src",graph3[2]+"d1="+date1+"&pid="+pid+"&bcid="+bcid);
    $("#graph14").attr("src",graph3[3]+"d1="+date1+"&pid="+pid+"&bcid="+bcid);
    footerposition();
}

function generateGraph4(date1,date2,bcid)
{
    $("#graph15").attr("src",graph4[0]+"d1="+date1+"&bcid="+bcid);
    $("#graph16").attr("src",graph4[1]+"d1="+date1+"&d2="+date2+"&bcid="+bcid);
    footerposition();
}

function getBusiness()
{
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getlocations.htm',
        success:function(json)
        {
            var html="<option value='0'>Select</option>";
            var data=json.LocationList;
            $.each(data, function(i, val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#business1").html(html);
            $("#business2").html(html);
            $("#business3").html(html);
            $("#business4").html(html);
        }
    });
}

function getProcessList()
{
    $.ajax(
    {
        cache:false,
        type:'POST',
        datatype:'json',
        url:'getprocesslist.htm',
        success:function(json)
        {
            var html="<option value='0'>Select</option>";
            var data=json.ProcessList;
            $.each(data, function(i, val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#process").html(html);
            $("#process1").html(html);
        }
    });
}

function locationdownload()
{
    if(locationDashboardValidate(1))
        window.open("locdashboard.htm?d1="+encodeURIComponent($("#dat1").val())+"&d2="+encodeURIComponent($("#dat2").val())+"&bcid="+encodeURIComponent($("#bcid").val())+"&pid="+encodeURIComponent($("#pid").val())+"&spid="+encodeURIComponent($("#spid").val()));
}

function downloadPdf()
{
    if(locationDashboardValidate(1))
        window.open("locationpdf.htm?d1="+encodeURIComponent($("#dat1").val())+"&d2="+encodeURIComponent($("#dat2").val())+"&bcid="+encodeURIComponent($("#bcid").val())+"&tier="+encodeURIComponent($("#tier").val())+"&pid="+encodeURIComponent($("#pid").val())+"&spid="+encodeURIComponent($("#spid").val()));
}

function locationprofile()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var bcid=$("#business1").val();
    var pid=$("#pid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,bcid:bcid,pid:pid};
    changeGraph('ajaxlocationprofile1.htm', params, array1, array2,'Resources per Process','Process','Resources', 'graphdiv1', 'graphdiv2');
    changeGraph('ajaxlocationprofile2.htm', params, array1, array2,'Resources per Client','Client','Resources', 'graphdiv3', 'graphdiv4');
    changeGraph('ajaxlocationprofile3.htm', params, array1, array2,'Resources per Cost Center','Cost Center','Resources', 'graphdiv5', 'graphdiv6');
    changeGraph('ajaxlocationprofile4.htm', params, array1, array2,'Employees per Grade','Grade','Resources', 'graphdiv7', 'graphdiv8');
}

function locationefficiency()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var bcid=$("#business1").val();
    var pid=$("#pid").val();
    var tier=$("#tier").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,bcid:bcid,pid:pid,tier:tier};
    changeGraph('ajaxlocationorgefficiency1.htm', params, array1, array2,'Resources per Tier','Tier','Resources', 'graphdiv9', 'graphdiv10');
    changeGraph1('ajaxlocationorgefficiency2.htm', params, array1, array2,'% of Resources per Tier','Tier','% of Resources', 'graphdiv11');
    changeGraph1('ajaxlocationorgefficiency3.htm', params, array1, array2,'Tier 3 Comparison','Center','% of resources', 'graphdiv12');
    changeGraph1('ajaxlocationorgefficiency4.htm', params, array1, array2,'% Resources at Tier 3','Process','% Resources at Tier3', 'graphdiv13');
    changeGraph2('ajaxlocationorgefficiency5.htm', params, array1, array2,'Tier 3 comparison for the process to other centers','Center','% Resources at Tier 3', 'graphdiv14');
    
    /*changeBarGraph('ajaxlocationorgefficiency1.htm', params, array1, array2,'Resources per Tier','Tier','Resources', 'graphdiv9', 'graphdiv10');
    changeBarGraph1('ajaxlocationorgefficiency2.htm', params, array1, array2,'% of Resources per Tier','Tier','% of Resources', 'graphdiv11');
    changeBarGraph1('ajaxlocationorgefficiency3.htm', params, array1, array2,'Tier 3 Comparison','Center','% of resources', 'graphdiv12');
    changeBarGraph1('ajaxlocationorgefficiency4.htm', params, array1, array2,'% Resources at Tier 3','Process','% Resources at Tier3', 'graphdiv13');
    changeBarGraph2('ajaxlocationorgefficiency5.htm', params, array1, array2,'Tier 3 comparison for the process to other centers','Center','% Resources at Tier 3', 'graphdiv14');*/

}

function locationprocessefficiency()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var bcid=$("#business1").val();
    var spid=$("#spid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,bcid:bcid,spid:spid};
    changeGraph1('ajaxlocationprocess1.htm', params, array1, array2,'AP Invoice Volume per FTE','Client','Volume', 'graphdiv15');
    changeGraph1('ajaxlocationprocess2.htm', params, array1, array2,'Average AP Volume per FTE','Center','Volume per FTE', 'graphdiv16');
    changeGraph1('ajaxlocationprocess3.htm', params, array1, array2,'Personnel cost per transaction','Client','Personnel Cost $', 'graphdiv17');
    changeGraph1('ajaxlocationprocess4.htm', params, array1, array2,'Personnel cost per transaction (How does it compare?)','Center','Personnel Cost $', 'graphdiv18');
}

function locationbusiness()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var bcid=$("#business1").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,bcid:bcid};
    changeGraph('ajaxbusinessassignment1.htm', params, array1, array2,'FTEs per Client','Client','FTE', 'graphdiv19','graphdiv20');
    changeGraph('ajaxbusinessassignment2.htm', params, array1, array2,"FTE's per $1Billion Revenue",'Client','FTE', 'graphdiv21','graphdiv22');
}

function locationDashboardValidate(check)
{
    var date1 = $("#date1").val();
    var date2 = $("#date2").val();
    var location = $("#business1").val();
    var subprocess = $("#process1").val();
    var tabno = $("#tabno").val();
    if(date1=="")
    {
        alert("Select As on date");
        return false;
    }
    else if(date2=="" ||date2=='0')
    {
        alert("Select Previous Date");
        return false;
    }
    else if(location=="")
    {
        alert("Select Center");
        return false;
    }
    else if(check==0)
    {
        if(tabno=="3")
        {
            if(subprocess=="")
            {
                alert("Select Sub-Process");
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return true;
        }
    }
    else if(check==1)
    {
        if(subprocess=="")
        {
            alert("Select Sub-Process in 3.Efficiency by Sub-Process");
            return false;
        }
        else
        {
            return true;
        }
    }
    else
    {
        return true;
    }    
}
