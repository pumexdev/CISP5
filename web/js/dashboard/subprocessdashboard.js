var graph=["subprocessgraph1.htm?","subprocessgraph2.htm?","subprocessgraph3.htm?","subprocessgraph4.htm?"];

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
        if(subProcessDashboardValidate())
        {
            switch(tabno)
            {
                case 1 :
                    subprocessprofile();
                    break;
                case 2 :
                    subprocessorgeff();
                    break;
                case 3 :
                    subprocess_sub();
                    kpiGraphs1();
                    break;
            }
        }
    });
});


function generateGraph(date1,date2,spid,tier,bid)
{
    if(date1!="" && spid!="0")
    {
        $("#spgraph1").attr("src",graph[0]+"d1="+date1+"&spid="+spid);
        $("#spgraph2").attr("src",graph[0]+"d1="+date1+"&d2="+date2+"&spid="+spid);
        $("#spgraph3").attr("src",graph[1]+"d1="+date1+"&spid="+spid);
        $("#spgraph4").attr("src",graph[1]+"d1="+date1+"&d2="+date2+"&spid="+spid);
        $("#spgraph5").attr("src",graph[2]+"d1="+date1+"&d2="+date2+"&spid="+spid+"&bcid="+bid+"&tier="+tier);
        $("#spgraph6").attr("src",graph[3]+"d1="+date1+"&d2="+date2+"&spid="+spid+"&bcid="+bid+"&tier="+tier);    
    }
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

function getSubProcess(processId)
{
    
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getsubprocesslist.htm?pid='+encodeURIComponent(processId),
        success:function(json)
        {
            var html="<option value='0'>Select</option>";
            var data=json.subProcessList;
            $.each(data, function(i, val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#subprocess").html(html);
        }
    });
}

function subprocessdownload()
{
    if(subProcessDashboardValidate())
        window.open("subprocdashboard.htm?d1="+encodeURIComponent($("#dat1").val())+"&d2="+encodeURIComponent($("#dat2").val())+"&spid="+encodeURIComponent($("#spid").val())+"&bcid="+encodeURIComponent($("#bcid").val())+"&tier="+encodeURIComponent($("#tier").val()));
}

function downloadPdf()
{
    var spid=$("#spid").val();
    var subprocesstext= encodeURIComponent(returnOptionText('subprocess1',spid));
    if(subProcessDashboardValidate())
        window.open("subprocesspdf.htm?name="+subprocesstext+"&d1="+encodeURIComponent($("#dat1").val())+"&d2="+encodeURIComponent($("#dat2").val())+"&spid="+encodeURIComponent($("#spid").val())+"&bcid="+encodeURIComponent($("#bcid").val())+"&tier="+encodeURIComponent($("#tier").val()));
}

function subprocessprofile()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var spid=$("#spid").val();
    var subprocesstext=returnOptionText('subprocess1',spid);
    var tier=$("#tier").val();
    var bcid=$("#bcid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,spid:spid,bcid:bcid,tier:tier};
    changeGraph('ajaxsubprocessgraph1.htm', params, array1, array2,'Resources per Center for '+subprocesstext+"\n",'Center','Resources', 'graphdiv1', 'graphdiv2');
    changeGraph('ajaxsubprocessgraph2.htm', params, array1, array2,'Average labor cost per FTE','Center','Cost $', 'graphdiv3', 'graphdiv4');
    changeGraph2('ajaxsubprocessgraph3.htm', params, array1, array2,"Average labor cost per FTE(000's)",'','Thousands', 'graphdiv5');
}

function subprocessorgeff()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var spid=$("#spid").val();
    var subprocesstext=returnOptionText('subprocess1',spid);
    var tier=$("#tier").val();
    var bcid=$("#bcid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,spid:spid,bcid:bcid,tier:tier};
    changeGraph('ajaxsubprocessorggraph1.htm', params, array1, array2,'Resources per Tier for '+subprocesstext+"\n",'Tier','Resources', 'graphdiv6', 'graphdiv7');
    changeGraph('ajaxsubprocessorggraph2.htm', params, array1, array2,'% Resources per Tier','Tier','% Resources', 'graphdiv8', 'graphdiv9');
    changeGraph1('ajaxsubprocessorggraph3.htm', params, array1, array2,"% Tier 3 Resources",'Center','% Resources', 'graphdiv10');
    /*changeBarGraph('ajaxsubprocessorggraph1.htm', params, array1, array2,'Resources per Tier for '+subprocesstext+"\n",'Tier','Resources', 'graphdiv6', 'graphdiv7');
    changeBarGraph('ajaxsubprocessorggraph2.htm', params, array1, array2,'% Resources per Tier','Tier','% Resources', 'graphdiv8', 'graphdiv9');
    changeBarGraph1('ajaxsubprocessorggraph3.htm', params, array1, array2,"% Tier 3 Resources",'Center','% Resources', 'graphdiv10');*/
}

function subprocess_sub()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var spid=$("#spid").val();
    var subprocesstext=returnOptionText('subprocess1',spid);
    var tier=$("#tier").val();
    var bcid=$("#bcid").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,spid:spid,bcid:bcid,tier:tier};
    changeGraph('ajaxsubprocesseffgraph1.htm', params, array1, array2,'Sub-Process Volume per FTE','Center','Volume', 'graphdiv11', 'graphdiv12');
    changeGraph('ajaxsubprocesseffgraph2.htm', params, array1, array2,'Average labor cost per transaction','Center','Average Labor cost', 'graphdiv13', 'graphdiv14');
}

function subProcessDashboardValidate()
{
    var date1 = $("#date1").val();
    var date2 = $("#date2").val();
    var subprocess = $("#subprocess1").val();
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
    else if(subprocess=="")
    {
        alert("Select Sub-Process");
        return false;
    }
    else
    {
        return true;
    }
}

function kpiGraphs1()
{
    var subprocessid=$("#spid").val();
    var rowlength=$('#kpitable3 >tbody >tr').length;
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var spid=$("#spid").val();
    var subprocesstext=returnOptionText('subprocess',spid);
    var status=$("#status").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,spid:spid};
    $.ajax(
    {
        cache:false,
        url:'getkpis.htm',
        data:{spid:subprocessid},
        type:'POST',
        dataType:'json',
        success:function(json)
        {
            var data = json.KPIS;
            var html = "";
            var count = 15;
            var originalcount=0;
            var divid="graphdiv14";
            var iconid="infoicon14";
            var chartdescid="chartdescdiv14";
            for(var j=rowlength;j>originalcount;--j)
            {
                $("#kpitable3 tr:last").parent()[0].rows[j-1].outerHTML="";
            }
            var chartNoCount = 5;
            $.each(data,function(i,val)
            {
                html += "<tr>";
                var columnhtml = "<td><div class='expand_style'><div class='top_bar'><div class='ex_left01'>Chart Description</div><div class='information_btn_out' id='infoicon14'><a href='#'><div class='information_btn'><div class='i-image1'></div></div></a></div><div class='ex_left02'>S3.14</div><div class='ex_right'><input id='graphdiv14_button' type='button' value='Export' name='' style='margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;' onclick='saveAsImg('graphdiv4')'/></div></div><div class='chartdescdiv' id='chartdescdiv14'>What are Level 3 the factors influencing productivity?</div><div class='graphOuter'><div class='graffDiv' id='graphdiv14'></div></div></div></td>";
                divid="graphdiv14";
                iconid="infoicon14";
                chartdescid="chartdescdiv14";
                var chartNo="S3.14";
                columnhtml = columnhtml.replace(divid, "graphdiv"+count, "g");
                columnhtml = columnhtml.replace(divid, "graphdiv"+count, "g");
                columnhtml = columnhtml.replace(divid, "graphdiv"+count, "g");
                columnhtml = columnhtml.replace(divid, "graphdiv"+count, "g");
                
                columnhtml = columnhtml.replace(iconid, "infoicon"+count, "g");
                columnhtml = columnhtml.replace(chartdescid, "chartdescdiv"+count, "g");
                columnhtml = columnhtml.replace(chartNo, "S3."+chartNoCount, "g");
                html+= columnhtml + "</tr>";
                count++;
                chartNoCount++;
            });
            $("#kpitable3").last().append(html);
            count = 15;
            $.each(data,function(i,val)
            {
                var ajaxparams={date:array1,spid:spid,status:2,kpiid:i};
                changeGraph2("kpigraph.htm", ajaxparams, array1, array2, val, 'Center', '', 'graphdiv'+count);
                count++;
            });
            $("#scrollbar1").tinyscrollbar();
            $("#scrollbar1").tinyscrollbar_update();
            descriptiontoggle();
        }
    });
}

