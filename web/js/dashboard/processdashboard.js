var graph1=["processprofile1.htm?","processprofile2.htm?","processprofile3.htm?"];
var graph2=["processefficiency1.htm?","processefficiency2.htm?","processefficiency3.htm?"];

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
        var tabno = $("#tabno").val();
        tabno = parseInt(tabno, 10);
        if(processDashboardValidate())
        {
            switch(tabno)
            {
                case 1 :
                    processprofile1_2();
                    processprofile3_4();
                    processprofile5();
                    break;
                case 2 :
                    processefficiency1_2();
                    processefficiency3_4();
                    processefficiency5();
                    break;
            }
        }
    });
});

function generateGraph1(date1,date2,pid,tier,bcid)
{
    if(date1!="" && pid!="0")
    {
        $("#pgraph1").attr("src",graph1[0]+"d1="+date1+"&pid="+pid);
        $("#pgraph2").attr("src",graph1[0]+"d1="+date1+"&d2="+date2+"&pid="+pid);
        $("#pgraph3").attr("src",graph1[1]+"d1="+date1+"&pid="+pid);
        $("#pgraph4").attr("src",graph1[1]+"d1="+date1+"&d2="+date2+"&pid="+pid);
        $("#pgraph5").attr("src",graph1[2]+"d1="+date1+"&pid="+pid+"&d2="+date2);
    }
}

function generateGraph2(date1,date2,pid,tier,bcid)
{
    if(date1!="" && pid!="0")
    {
        $("#pgraph11").attr("src",graph2[0]+"d1="+date1+"&pid="+pid+"&bcid="+bcid);
        $("#pgraph12").attr("src",graph2[0]+"d1="+date1+"&d2="+date2+"&pid="+pid+"&bcid="+bcid);
        $("#pgraph13").attr("src",graph2[1]+"d1="+date1+"&pid="+pid+"&bcid="+bcid);
        $("#pgraph14").attr("src",graph2[1]+"d1="+date1+"&d2="+date2+"&pid="+pid+"&bcid="+bcid);
        $("#pgraph15").attr("src",graph2[2]+"d1="+date1+"&pid="+pid+"&d2="+date2+"&bcid="+bcid+"&tier="+tier);
    }
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
            $("#business").html(html);
        }
    });
}


function processdownload()
{
    if(processDashboardValidate())
        window.open("procdashboard.htm?d1="+encodeURIComponent($("#dat1").val())+"&d2="+encodeURIComponent($("#dat2").val())+"&pid="+encodeURIComponent($("#pid").val())+"&bcid="+encodeURIComponent($("#bcid").val())+"&tier="+encodeURIComponent($("#tier").val()));
}

function downloadPdf()
{
    var processid=$("#pid").val();
    var processtext= encodeURIComponent(returnOptionText('process1', processid));
    if(processDashboardValidate())
        window.open("processpdf.htm?name="+processtext+"&d1="+encodeURIComponent($("#dat1").val())+"&d2="+encodeURIComponent($("#dat2").val())+"&bcid="+encodeURIComponent($("#bcid").val())+"&tier="+encodeURIComponent($("#tier").val())+"&pid="+encodeURIComponent($("#pid").val()));
}

function processprofile1_2()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var tier=$("#tier").val();
    var processid=$("#process1").val();
    var processtext=returnOptionText('process1', processid);
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,pid:processid,tier:tier};
    changeGraph('ajaxprocessprofile1.htm', params, array1, array2,'Resources per Center for '+processtext+"\n",'Center','Resources', 'graphdiv1', 'graphdiv2');
}

function processprofile3_4()
{
    var array1=new Array();
    var array2=new Array();
    var processid=$("#process1").val();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var tier=$("#tier").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,pid:processid,tier:tier};
    changeGraph('ajaxprocessprofile2.htm', params, array1, array2,'Average labor cost per FTE','Center','Cost $', 'graphdiv3', 'graphdiv4');

}

function processprofile5()
{
    var array1=new Array();
    var array2=new Array();
    var processid=$("#process1").val();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var tier=$("#tier").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,pid:processid,tier:tier};
    changeGraph2('ajaxprocessprofile3.htm', params, array1, array2,"Average labor cost per FTE (000's)",'','Thousands', 'graphdiv5','graphdiv5');
}

function processefficiency1_2()
{
    var array1=new Array();
    var array2=new Array();
    var processid=$("#process1").val();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var tier=$("#tier").val();
    var bcid=$("#bcid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,pid:processid,tier:tier,bcid:bcid};
    changeGraph('ajaxprocessefficiency1.htm', params, array1, array2,'Resources per Tier','Tier','Resources', 'graphdiv6', 'graphdiv7');
    //changeBarGraph('ajaxprocessefficiency1.htm', params, array1, array2,'Resources per Tier','Tier','Resources', 'graphdiv6', 'graphdiv7');
}

function processefficiency3_4()
{
    var array1=new Array();
    var array2=new Array();
    var processid=$("#process1").val();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var tier=$("#tier").val();
    var bcid=$("#bcid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,pid:processid,tier:tier,bcid:bcid};
    changeGraph('ajaxprocessefficiency2.htm', params, array1, array2,'% Resources per Tier','Tier','% Resource', 'graphdiv8', 'graphdiv9');
    //changeBarGraph('ajaxprocessefficiency2.htm', params, array1, array2,'% Resources per Tier','Tier','% Resource', 'graphdiv8', 'graphdiv9');
}

function processefficiency5()
{
    var array1=new Array();
    var array2=new Array();
    var processid=$("#process1").val();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var tier=$("#tier").val();
    var bcid=$("#bcid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,pid:processid,tier:tier,bcid:bcid};
    changeGraph1('ajaxprocessefficiency3.htm', params, array1, array2,'Tier 3 Comparison','Center','(% Span of control)', 'graphdiv10');
    //changeBarGraph1('ajaxprocessefficiency3.htm', params, array1, array2,'Tier 3 Comparison','Center','(% Span of control)', 'graphdiv10');
}

function processDashboardValidate()
{
    var date1 = $("#date1").val();
    var date2 = $("#date2").val();
    var process = $("#process1").val();
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
    else if(process=="")
    {
        alert("Select Process");
        return false;
    }
    else
    {
        return true;
    }
}
