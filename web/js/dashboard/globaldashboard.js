var graph1=["globalprofile1.htm?","globalprofile2.htm?","globalprofile3.htm?"];
var graph2=["globalefficiency1.htm?","globalefficiency2.htm?","globalefficiency3.htm?"];
var graph3=["globalcost1.htm?","globalcost2.htm?"];

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
        if(globaldashboardValidate())
        {
            switch(tabno)
            {
                case 1 :
                    globalprofile1_2();
                    globalprofile3();
                    globalprofile4_5();
                    break;
                case 2 :
                    globalprofile6_7();
                    globalprofile8_9();
                    globalprofile10();
                    break;
                case 3 :
                    globalprofile11_12();
                    globalprofile13();
                    break;
            }
        }   
    });
});

function generateGraph1(date1,date2)
{
    $("#graph1").attr("src",graph1[0]+"d1="+date1);
    $("#graph2").attr("src",graph1[0]+"d1="+date1+"&d2="+date2);
    $("#graph3").attr("src",graph1[1]+"d1="+date1);
    $("#graph4").attr("src",graph1[2]+"d1="+date1);
    $("#graph5").attr("src",graph1[2]+"d1="+date1+"&d2="+date2);
}

function generateGraph2(date1,date2,tier)
{
    $("#graph6").attr("src",graph2[0]+"d1="+date1);
    $("#graph7").attr("src",graph2[0]+"d1="+date1+"&d2="+date2);
    $("#graph8").attr("src",graph2[1]+"d1="+date1+"&tier="+tier);
    $("#graph9").attr("src",graph2[1]+"d1="+date1+"&d2="+date2+"&tier="+tier);
    $("#graph10").attr("src",graph2[2]+"d1="+date1+"&tier="+tier);
}

function generateGraph3(date1,date2)
{
    $("#graph11").attr("src",graph3[0]+"d1="+date1);
    $("#graph12").attr("src",graph3[0]+"d1="+date1+"&d2="+date2);
    $("#graph13").attr("src",graph3[1]+"d1="+date1);
}

function downloadPdf()
{
    if(globaldashboardValidate())
        window.open("globalpdf.htm?d1="+encodeURIComponent($("#dat1").val())+"&d2="+encodeURIComponent($("#dat2").val())+"&tier="+encodeURIComponent($("#tier").val()));
}

function globaldownload()
{
    if(globaldashboardValidate())
        window.open("draw.htm?d1="+encodeURIComponent($("#dat1").val())+"&d2="+encodeURIComponent($("#dat2").val())+"&tier="+encodeURIComponent($("#tier").val()));
}

/*Ajax calls start here*/

function globalprofile1_2()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1};
    changeGraph('ajaxglobalprofile1.htm', params, array1, array2,'Resources per Center','Center','Resources', 'graphdiv1', 'graphdiv2');
}

function globalprofile3()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array2};
    changeGraph1('ajaxglobalprofile2.htm', params, array1, array2,'Fully loaded labor cost per Center','Center','Cost $', 'graphdiv3');
}

function globalprofile4_5()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1};
    changeGraph('ajaxglobalprofile3.htm', params, array1, array2,'Average cost per resource per Center','Center','Cost $', 'graphdiv4', 'graphdiv5');
}

function globalprofile6_7()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var tier=$("#tier").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,tier:tier};
    changeGraph('ajaxglobalefficiency1.htm', params, array1, array2,'Resources per Tier','Tier','Resources', 'graphdiv6', 'graphdiv7');
    //changeBarGraph('ajaxglobalefficiency1.htm', params, array1, array2,'Resources per Tier','Tier','Resources', 'graphdiv6', 'graphdiv7');
}

function globalprofile8_9()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var tier=$("#tier").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1};
    changeGraph('ajaxglobalefficiency2.htm', params, array1, array2,'Span of Control %','Tier','Resources %', 'graphdiv8', 'graphdiv9');
    //changeBarGraph('ajaxglobalefficiency2.htm', params, array1, array2,'Span of Control %','Tier','Resources %', 'graphdiv8', 'graphdiv9');
}

function globalprofile10()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var tier=$("#tier").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1,tier:tier};
    changeGraph1('ajaxglobalefficiency3.htm', params, array1, array2,'Tier 3 Comparison','Center','Resources %', 'graphdiv10');
    //changeBarGraph1('ajaxglobalefficiency3.htm', params, array1, array2,'Tier 3 Comparison','Center','Resources %', 'graphdiv10');
}

function globalprofile11_12()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1};
    changeGraph('ajaxglobalcost1.htm', params, array1, array2,'Resources per Process','Process','Resources', 'graphdiv11', 'graphdiv12');
}

function globalprofile13()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1};
    changeGraph1('ajaxglobalcost2.htm', params, array1, array2,'Average cost per FTE','Process','Cost $', 'graphdiv13');
}

function globaldashboardValidate()
{
    var date1 = $("#date1").val();
    var date2 = $("#date2").val();
    if(date1=="")
    {
        alert("Select As on date");
        $(".errordiv").html('Hii');
        return false;
    }
    else if(date2=="" ||date2=='0')
    {
        alert("Select Previous Date");
        $("#date2").focus();
        return false;
    }
    else
    {
        return true;
    }    
}