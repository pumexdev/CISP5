/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function()
{
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
        if(connectedValidate())
        {
            switch(tabno)
            {
                case 1 :
                    level1Graph();
                    break;
                case 2 :
                    level2Graph();
                    break;
                case 3 :
                    level3Graph();
                    break;
            }
        }
    });
});

function kpiGraphs()
{
    var subprocessid=$("#spid").val();
    var rowlength=$('#kpitable3 >tbody >tr').length;
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var spid=$("#spid").val();
    var bcid=$("#business1").val();
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
            var count = 5;
            var originalcount=0;
            var divid="graphdiv4";
            var iconid="infoicon4";
            var chartdescid="chartdescdiv4";
            for(var j=rowlength;j>originalcount;--j)
            {
                $("#kpitable3 tr:last").parent()[0].rows[j-1].outerHTML="";
            }
            var chartNoCount = 1;
            $.each(data,function(i,val)
            {
                html += "<tr>";
                var columnhtml = "<td><div class='expand_style'><div class='top_bar'><div class='ex_left01'>Chart Description</div><div class='information_btn_out' id='infoicon4'><a href='#'><div class='information_btn'><div class='i-image1'></div></div></a></div><div class='ex_left02'>C3.1</div><div class='ex_right'><input id='graphdiv4_button' type='button' value='Export' name='' style='margin: 0px !important;background: -moz-linear-gradient(center top , #FDFDFD 0%, #EEEEEE 100%) repeat scroll 0 0 transparent;border: 1px solid #C8C8C8;border-radius: 4px 4px 4px 4px;box-shadow: 1px 1px 1px 0 #EBEDF0;color: #2C2C2C;cursor: pointer;font-size: 12px;font-weight: bold;letter-spacing: 0.5px;padding: 4px 6px;' onclick='saveAsImg('graphdiv4')'/></div></div><div class='chartdescdiv' id='chartdescdiv4'>What are Level 3 the factors influencing productivity?</div><div class='graphOuter'><div class='graffDiv' id='graphdiv4'></div></div></div></td>";
                divid="graphdiv4";
                iconid="infoicon4";
                chartdescid="chartdescdiv4";
                var chartNo="C3.1";
                columnhtml = columnhtml.replace(divid, "graphdiv"+count);
                columnhtml = columnhtml.replace(divid, "graphdiv"+count);
                columnhtml = columnhtml.replace(divid, "graphdiv"+count);
                columnhtml = columnhtml.replace(divid, "graphdiv"+count);
                columnhtml = columnhtml.replace(iconid, "infoicon"+count);
                                
                columnhtml = columnhtml.replace(chartdescid, "chartdescdiv"+count);
                columnhtml = columnhtml.replace(chartNo, "C3."+chartNoCount);
                html+= columnhtml + "</tr>";
                count++;
                chartNoCount++;
            });
            $("#kpitable3").last().append(html);
            count = 5;
            $.each(data,function(i,val)
            {
                var ajaxparams={date:array1,spid:spid,status:status,kpiid:i,bcid:bcid};
                changeGraph2("kpigraph.htm", ajaxparams, array1, array2, val, 'Center', '', 'graphdiv'+count);
                count++;
            });
            descriptiontoggle();
            $("#scrollbar1").tinyscrollbar();
            $("#scrollbar1").tinyscrollbar_update();
        }
    });
}

function showhide()
{
    var status=$("#status").val();
    if(status=="1")
    {
        $("#bctr").show();
    }
    else
    {
        $("#bctr").hide();
    }    
}

function level1Graph()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var bcid=$("#bcid").val();
    var spid=$("#spid").val();
    var levelText = returnOptionText('subprocess', spid);
    var status=$("#status").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,bcid:bcid,spid:spid,status:status};
    changeGraph1('ajaxconnectedgraph1.htm', params, array1, array2,'Average labor cost per transaction','Center','Cost $', 'graphdiv1');
    
    /*var leveltext = "Level 1 - This is your <span>cost per transaction </span>for "+levelText;
    $("#leveldesc1").show();
    $("#leveldesc1").html(leveltext);*/
    $("#chartdescdiv1").html("This is your cost per transaction for "+levelText);
    $("#scrollbar1").tinyscrollbar();
    $("#scrollbar1").tinyscrollbar_update();
}

function level2Graph()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var spid=$("#spid").val();
    var tier=$("#tier").val();
    var bcid=$("#bcid").val();
    var status=$("#status").val();
    var subprocesstext=returnOptionText('subprocess',spid);
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,spid:spid,bcid:bcid,tier:tier,status:status};
    var status=$("#status").val();
    if(status=="2")
    {
        $("#g3").show();
        $("#g4").show();
        changeGraph2('ajaxconnectedgraph2.htm', params, array1, array2,'Sub-Process Volume per FTE','Center','Volume', 'graphdiv2');
        changeGraph2('ajaxconnectedgraph3.htm', params, array1, array2,"Average labor cost per FTE (000's)",'Center','Thousands', 'graphdiv3');
        changeGraph2('ajaxconnectedgraph4.htm', params, array1, array2,'% Resources per Tier','Center','% Resources', 'graphdiv4');
    }
    else
    {
        changeGraph1('ajaxlocationprocess1.htm', params, array1, array2,'AP Invoice Volume per FTE','Client','Volume', 'graphdiv2');
        $("#g3").hide();
        $("#g4").hide();
    }
    $("#scrollbar1").tinyscrollbar();
    $("#scrollbar1").tinyscrollbar_update();
}

function level3Graph()
{
    kpiGraphs();
}

function level1_Toggle()
{
    var toggleval1 = $("#toggleval1").val();
    if(toggleval1=='1')
    {
        $("#level2").show();
        $("#toggle2").show();
        $("#level3").hide('slow');
        level2Graph();
        $("#scrollbar1").tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update(650);
        $("#toggleval1").val('2');
    }
    else
    {
        $("#level2").hide();
        $("#level3").hide();
        $("#toggleval1").val('1');
        $("#scrollbar1").tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update();
    }
}

function level2_Toggle()
{
    var toggleval2 = $("#toggleval2").val();
    if(toggleval2=='1')
    {
        $("#level3").show();
        kpiGraphs();
        $("#toggleval2").val('2');
    }
    else
    {
        $("#level3").hide();
        $("#toggleval2").val('1');
        $("#scrollbar1").tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update(650);
    }
}

function connectedValidate()
{
    var date1 = $("#date1").val();
    var date2 = $("#date2").val();
    var subprocess = $("#subprocess").val();
    var status = $("#status").val();
    var location = $("#business1").val();
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
    else if(status!="")
    {
        if(status=="1")
        {
            if(location=="")
            {
                alert("Select Center");
                return false;
            }
            else
                return true;
        }
        else
            return true;
    }
    else
        return true;
}