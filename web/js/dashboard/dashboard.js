/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function()
{
    descriptiontoggle();
    initialiseTabs();
    setTabno();
    selectfunc();
    $("input[type=button]").each(function()
    {
        var id = $(this).attr("id").toString();
        var testid1 = "graphdiv";
        var testid2 = "_button";
        var index1 = id.indexOf(testid1, 0);
        var index2 = id.indexOf(testid2, 0);
        if(index1!=-1 && index2!=-1)
        {
            $(this).css("display","none");
            //$(this).attr("style","");
        }
    });
});

function selectfunc()
{
    $(".date_left").each(function()
    {
        $(this).css("padding-top","5");
    });
}

function initialiseTabs()
{
    $("#carea div.tabcontent").hide(); // Initially hide all content	
    $("#tabs li:first").attr("id","current"); // Activate first tab
    $("#content div:first").fadeIn(); // Show first tab content

    $('#tabs a').click(function(e) {
        e.preventDefault();        
        $("#content div.tabcontent").hide();
        $("#tabs li").attr("id","");
        $(this).parent().attr("id","current");
        $('#' + $(this).attr('title')).fadeIn();
    });
}

function descriptiontoggle()
{
    $(".chartdescdiv").each(function()
    {
        var divid = $(this).attr("id");
        var divno = divid.substring(divid.length-2, divid.length);
        divno = getno(divno);
        var iconid = "infoicon"+divno;
        
        $("#"+iconid+" a").unbind('click');
        $("#"+iconid+" a").bind('click',function()
        {
            $("#"+divid).slideToggle('fast');
            return false;
        });
    });
}


function setTabno()
{
    $("#tabs li a").each(function()
    {
        var title = $(this).attr("title");
        var tabno = title.substring(title.length-1, title.length);
        $(this).click(function()
        {
            $("#tabno").val(tabno);
            $("#scrollbar1").tinyscrollbar();
            $("#scrollbar1").tinyscrollbar_update();
        });
    });
}

function getno(no)
{
    var no1 = parseInt(no, 10);
    if(no1.toString()=='NaN')
    {
        no1 = no.substring(no.length-1, no.length);
        no1 = parseInt(no1, 10);
    }
    return no1;
}

/*function download()
{
    window.location.href="draw.htm?d1="+encodeURIComponent($("#dat1").val())+"&d2="+encodeURIComponent($("#dat2").val());
}*/

function getDates2(uploadDate,id)
{
    $.ajax(
    {
       cache:false,
       url:'getdate2.htm?d1='+uploadDate,
       type:'POST',
       datatype:'json',
       success:function(json)
       {
           var html="<option value='0'>Select</option>";
           $.each(json.dates2,function(i,val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#"+id).html(html);
       }
    });
}

function getDatesArray2(uploadDate,ids)
{
    $.ajax(
    {
       cache:false,
       url:'getdate2.htm?d1='+uploadDate,
       type:'POST',
       datatype:'json',
       success:function(json)
       {
           var html="";
           $.each(json.dates2,function(i,val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            for(var i=0;i<ids.length;++i)
            {
                $("#"+ids[i]).html(html);
            }
       }
    });
}

/*
  Change graphs onclick
  Parameters : ajax url,data needed,date array1,date array2,
               Graph title,xAxis label,yAxis label,div id1, div id2 (What has changed)
               Div id1 - For single date
               Div id2 - For multiple dates
 */
function changeGraph(targeturl,params,array1,array2,title,xAxisLabel,yAxisLabel,divid1,divid2)
{
    var xArray=new Array();
    var datalength=0;
    $.ajax(
    {
        type:'POST',
        datatype:'json',
        cache:false,
        url:targeturl,
        data:params,
        success: function(json)
        {
            var dates1Array=new Array();
            var dates2Array=new Array();
            dates2Array.push('');
            dates1Array.push('');
            var data=json.Data;
            var data1Array=new Array();
            var data2Array=new Array();
            createData1(data, array1, title,xAxisLabel,yAxisLabel, divid1);
            createData(data, array1, title+" (What has changed)", xAxisLabel, yAxisLabel, divid2);
        }
    });
}

/*
  Change graphs onclick
  Parameters : ajax url,data needed,date array1,date array2,
               Graph title,xAxis label,yAxis label,div id1, div id2 (What has changed)
               Div id1 - For single date
               Div id2 - For multiple dates
 */
function changeGraph(targeturl,params,array1,array2,title,xAxisLabel,yAxisLabel,divid1,divid2,targetBM)
{
    var xArray=new Array();
    var datalength=0;
    $.ajax(
    {
        type:'POST',
        datatype:'json',
        cache:false,
        url:targeturl,
        data:params,
        success: function(json)
        {
            var dates1Array=new Array();
            var dates2Array=new Array();
            dates2Array.push('');
            dates1Array.push('');
            var data=json.Data;
            targetBM=json.target;
            var data1Array=new Array();
            var data2Array=new Array();
            createData1(data, array1, title,xAxisLabel,yAxisLabel, divid1);
            createData(data, array1, title+" (What has changed)", xAxisLabel, yAxisLabel, divid2);
//            $("#"+divid3).text(targetBM.high);
        }
    });
}

/*Change graphs onclick
  Parameters : ajax url,data needed,date array1,date array2,
               Graph title,xAxis label,yAxis label,div id1
               Div id1 - For single dates
 */
function changeGraph1(targeturl,params,array1,array2,title,xAxisLabel,yAxisLabel,divid1)
{
    var datalength=0;
    $.ajax(
    {
        type:'POST',
        datatype:'json',
        cache:false,
        url:targeturl,
        data:params,
        success: function(json)
        {
            var dates1Array=new Array();
            var dates2Array=new Array();
            dates2Array.push('');
            dates1Array.push('');
            var data=json.Data;
            var data1Array=new Array();
            var data2Array=new Array();
            createData1(data, array1, title,xAxisLabel,yAxisLabel, divid1);
        }
    });
}

/*Change graphs onclick
  Parameters : ajax url,data needed,date array1,date array2,
               Graph title,xAxis label,yAxis label,div id1
               Div id1 - For multiple dates
 */
function changeGraph2(targeturl,params,array1,array2,title,xAxisLabel,yAxisLabel,divid1)
{
    var xArray=new Array();
    var datalength=0;
    $.ajax(
    {
        type:'POST',
        datatype:'json',
        cache:false,
        url:targeturl,
        data:params,
        success: function(json)
        {
            var dates1Array=new Array();
            var dates2Array=new Array();
            dates2Array.push('');
            dates1Array.push('');
            var data=json.Data;
            var data1Array=new Array();
            var data2Array=new Array();
            createData(data, array1, title, xAxisLabel, yAxisLabel, divid1);
        }
    });
}

/*
  Change graphs onclick - Bar charts Orientation horizontal
  Parameters : ajax url,data needed,date array1,date array2,
               Graph title,xAxis label,yAxis label,div id1, div id2 (What has changed)
               Div id1 - For single date
               Div id2 - For multiple dates
 */
function changeBarGraph(targeturl,params,array1,array2,title,xAxisLabel,yAxisLabel,divid1,divid2)
{
    var xArray=new Array();
    var datalength=0;
    $.ajax(
    {
        type:'POST',
        datatype:'json',
        cache:false,
        url:targeturl,
        data:params,
        success: function(json)
        {
            var dates1Array=new Array();
            var dates2Array=new Array();
            dates2Array.push('');
            dates1Array.push('');
            var data=json.Data;
            var data1Array=new Array();
            var data2Array=new Array();
            createBarChartData1(data, array1, title, xAxisLabel, yAxisLabel, divid1);
            createBarChartData(data, array1, title+" (What has changed)", xAxisLabel, yAxisLabel, divid2);
        }
    });
}

/*
  Change graphs onclick - Bar charts Orientation horizontal
  Parameters : ajax url,data needed,date array1,date array2,
               Graph title,xAxis label,yAxis label,div id1
               Div id1 - For single date
 */
function changeBarGraph1(targeturl,params,array1,array2,title,xAxisLabel,yAxisLabel,divid1)
{
    var xArray=new Array();
    var datalength=0;
    $.ajax(
    {
        type:'POST',
        datatype:'json',
        cache:false,
        url:targeturl,
        data:params,
        success: function(json)
        {
            var dates1Array=new Array();
            var dates2Array=new Array();
            dates2Array.push('');
            dates1Array.push('');
            var data=json.Data;
            var data1Array=new Array();
            var data2Array=new Array();
            createBarChartData1(data, array1, title, xAxisLabel, yAxisLabel, divid1);
        }
    });
}

/*
  Change graphs onclick - Bar charts Orientation horizontal
  Parameters : ajax url,data needed,date array1,date array2,
               Graph title,xAxis label,yAxis label,div id1
               Div id1 - For multiple date
 */
function changeBarGraph2(targeturl,params,array1,array2,title,xAxisLabel,yAxisLabel,divid1)
{
    var xArray=new Array();
    var datalength=0;
    $.ajax(
    {
        type:'POST',
        datatype:'json',
        cache:false,
        url:targeturl,
        data:params,
        success: function(json)
        {
            var dates1Array=new Array();
            var dates2Array=new Array();
            dates2Array.push('');
            dates1Array.push('');
            var data=json.Data;
            var data1Array=new Array();
            var data2Array=new Array();
            createBarChartData(data, array1, title, xAxisLabel, yAxisLabel, divid1);
        }
    });
}

/*
  Change graphs onclick - Pie charts Orientation horizontal
  Parameters : ajax url,data needed,date array1,date array2,
               Graph title,xAxis label,yAxis label,div id1
               Div id1 - For single date
 */
function changePieChart(targeturl,params,array1,array2,title,xAxisLabel,yAxisLabel,divid1)
{
    var xArray=new Array();
    var datalength=0;
    $.ajax(
    {
        type:'POST',
        datatype:'json',
        cache:false,
        url:targeturl,
        data:params,
        success: function(json)
        {
            var dates1Array=new Array();
            var dates2Array=new Array();
            dates2Array.push('');
            dates1Array.push('');
            var data=json.Data;
            var data1Array=new Array();
            var data2Array=new Array();
            createPieChartData(data, array1, title, xAxisLabel, yAxisLabel, divid1);
        }
    });
}

/*
  Creating the data array for the google charts for multiple dates
  Parameters : input data array,graph title,xLable,yLabel
  Output div id
 */
function createData(data,array1,title,xAxisLabel,yAxisLabel, divid1)
{
    var array=new Array();
    array[0]=[""];
    for(var m=0;m<array1.length;++m)
    {
        array[0][m+1]=array1[m];
    }
    var date_index=1;
    var date_length=array1.length;
    var j_index=1;
    $.each(data,function(i,val)
    {
        for(var j=0;j<val.length;++j)
        {
            if(!check(array, val[j].xValue, 0))
            {
                var innerarray=new Array();
                innerarray[0]=val[j].xValue;
                for(var k=1;k<=date_length;++k)
                {
                    innerarray[k]=0.0;
                }
                array[j_index]=innerarray;
                j_index++;
            }
        }
        date_index++;
    });
    
    
    date_index=1;
    date_length=array1.length;
    j_index=0;
    $.each(data,function(i,val)
    {
        for(var j=0;j<val.length;++j)
        {
            if(check(array, val[j].xValue, 0))
            {
                var index=getIndex(array, val[j].xValue, 0);
                var yValue = parseFloat(val[j].yValue);
                array[index][date_index]=yValue;
            }
        }
        date_index++;
    });
    
    drawChart(array,title,xAxisLabel,yAxisLabel, divid1);
}

/*
  Creating the data array for the google charts for single dates
  Parameters : input data array,graph title,xLable,yLabel
  Output div id
 */
function createData1(data,array1,title,xAxisLabel,yAxisLabel, divid1)
{
    var array=new Array();
    array[0]=[""];
    for(var m=0;m<1;++m)
    {
        array[0][m+1]=array1[m];
    }
    var date_index=1;
    var date_length=1;
    var j_index=1;
    $.each(data,function(i,val)
    {
        if(j_index==1)
        {
            for(var j=0;j<val.length;++j)
            {
                if(!check(array, val[j].xValue, 0))
                {
                    var innerarray=new Array();
                    innerarray[0]=val[j].xValue;
                    for(var k=1;k<=date_length;++k)
                    {
                        innerarray[k]=0.0;
                    }
                    array[j_index]=innerarray;
                    j_index++;
                }
            }
        }
        date_index++;
    });
    
    
    date_index=1;
    date_length=array1.length;
    j_index=1;
    $.each(data,function(i,val)
    {
        if(j_index==1)
        {
            for(var j=0;j<val.length;++j)
            {
                if(check(array, val[j].xValue, 0))
                {
                    var index=getIndex(array, val[j].xValue, 0);
                    var yValue = parseFloat(val[j].yValue);
                    array[index][date_index]=yValue;
                }
            }
            j_index++;
        }
        date_index++;
    });
    
    drawChart(array,title,xAxisLabel,yAxisLabel, divid1);
}

/*
  Creating the data array for the google charts (horizontal orientation) for multiple dates
  Parameters : input data array,graph title,xLable,yLabel
  Output div id
 */
function createBarChartData(data,array1,title,xAxisLabel,yAxisLabel, divid1)
{
    var array=new Array();
    array[0]=[""];
    for(var m=0;m<array1.length;++m)
    {
        array[0][m+1]=array1[m];
    }
    var date_index=1;
    var date_length=array1.length;
    var j_index=1;
    $.each(data,function(i,val)
    {
        for(var j=0;j<val.length;++j)
        {
            if(!check(array, val[j].xValue, 0))
            {
                var innerarray=new Array();
                innerarray[0]=val[j].xValue;
                for(var k=1;k<=date_length;++k)
                {
                    innerarray[k]=0;
                }
                array[j_index]=innerarray;
            }
            j_index++;
        }
        date_index++;
    });
    
    
    date_index=1;
    date_length=array1.length;
    j_index=0;
    $.each(data,function(i,val)
    {
        for(var j=0;j<val.length;++j)
        {
            if(check(array, val[j].xValue, 0))
            {
                var index=getIndex(array, val[j].xValue, 0);
                var yValue = parseFloat(val[j].yValue);
                array[index][date_index]=yValue;
            }
        }
        date_index++;
    });
    
    drawBarChart(array,title,xAxisLabel,yAxisLabel, divid1);
}

/*
  Creating the data array for the google charts (horizontal orientation) for single dates
  Parameters : input data array,graph title,xLable,yLabel
  Output div id
 */
function createBarChartData1(data,array1,title,xAxisLabel,yAxisLabel, divid1)
{
    var array=new Array();
    array[0]=[""];
    for(var m=0;m<1;++m)
    {
        array[0][m+1]=array1[m];
    }
    var date_index=1;
    var date_length=1;
    var j_index=1;
    $.each(data,function(i,val)
    {
        if(j_index==1)
        {
            for(var j=0;j<val.length;++j)
            {
                if(!check(array, val[j].xValue, 0))
                {
                    var innerarray=new Array();
                    innerarray[0]=val[j].xValue;
                    for(var k=1;k<=date_length;++k)
                    {
                        innerarray[k]=0;
                    }
                    array[j_index]=innerarray;
                    j_index++;
                }
            }
        }
        date_index++;
    });
    
    
    date_index=1;
    date_length=array1.length;
    j_index=1;
    $.each(data,function(i,val)
    {
        if(j_index==1)
        {
            for(var j=0;j<val.length;++j)
            {
                if(check(array, val[j].xValue, 0))
                {
                    var index=getIndex(array, val[j].xValue, 0);
                    var yValue = parseFloat(val[j].yValue);
                    array[index][date_index]=yValue;
                }
            }
            j_index++;
        }
        date_index++;
    });
    
    drawBarChart(array,title,xAxisLabel,yAxisLabel, divid1);
}

/*
  Creating the data array for the google charts (Pie Charts) for single dates
  Parameters : input data array,graph title,xLable,yLabel
  Output div id
 */
function createPieChartData(data,array1,title,xAxisLabel,yAxisLabel, divid1)
{
    var array=new Array();
    array[0]=["X","Y"];
    
    var date_index=0;
    var date_length=1;
    var j_index=1;
    $.each(data,function(i,val)
    {
        if(j_index==1)
        {
            for(var j=0;j<val.length;++j)
            {
                var innerarray=new Array();
                innerarray[0]=val[j].xValue;
                var yValue = parseFloat(val[j].yValue);
                innerarray[1]=yValue;
                array[j_index]=innerarray;
                j_index++;
            }
        }
        date_index++;
    });
    
    drawPieChart(array,title,xAxisLabel,yAxisLabel, divid1);
}


function drawTarget(targeturl,params,divhigh,divmed,divlow,divtarget){
    var xArray=new Array();
    var datalength=0;
    $.ajax(
    {
        type:'POST',
        datatype:'json',
        cache:false,
        url:targeturl,
        data:params,
        success: function(json)
        {
            var data=json.target;
            $("input."+divhigh).val(numberWithCommas(data.high));
            $("input."+divlow).val(numberWithCommas(data.low));
            $("input."+divmed).val(numberWithCommas(data.medium));
            $("input."+divtarget).val(numberWithCommas(data.target));
        }
    });
}

function drawTgtTable(targeturl,params,divx,divy,divxpery){
    var xArray=new Array();
    var datalength=0;
    $.ajax(
    {
        type:'POST',
        datatype:'json',
        cache:false,
        url:targeturl,
        data:params,
        success: function(json)
        {
            var data=json.target;
            $("div."+divx).text(numberWithCommas(data.x));
//            alert(numberWithCommas(data.xpery));
            $("div."+divy).text(numberWithCommas(data.y));
            $("div."+divxpery).text(numberWithCommas(data.xpery));
        }
    });
}

function numberWithCommas(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}