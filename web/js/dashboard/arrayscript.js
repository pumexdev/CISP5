/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function checkElement(array,element)
{
    var check=false;
    for(var i=0;i<array.length;++i)
    {
        var arrayElement=array[i];
        if(arrayElement[0].toString()==element)
        {
            check=true;
            break;
        }
        else
            check=false;
    }
    return check;
}

function returnIndex(array,element)
{
    var check=false;
    var index=-1;
    for(var i=0;i<array.length;++i)
    {
        var arrayElement=array[i];
        if(arrayElement[0].toString()==element)
        {
            check=true;
            index=i;
            break;
        }
        else
            check=false;
    }
    return index;
}

function iterateArray(array)
{
    for(var i=0;i<array.length;++i)
    {
        var arrayElement=array[i];
        console.log(arrayElement);
    }
}

function removeElement(parentArray,childArray)
{
    var check=false;
    var index=-1;
    for(var i=0;i<parentArray.length;++i)
    {
        var arrayElement=parentArray[i];
        if(childArray==arrayElement)
        {
            index=i;
            check=true;
            break;
        }    
    }
    if(index!=-1 && check)
    {
        parentArray.splice(index,1);
    }    
}

function convertto2D(array,index)
{
    for(var i=0;i<array.length;++i)
    {
        var arrayElement=array[i];
        if(arrayElement[index]==undefined)
            arrayElement[index]=0;
    }
    return array;
}

function convertArray(array,index,check)
{
    for(var i=0;i<array.length;++i)
    {
        var arrayElement=array[i];
        if(arrayElement[index]==undefined)
            arrayElement[index]=0;
    }
    return array;
}

function drawChart(data,title,xAxisLabel,yAxisLabel,divid)
{
    $("#"+divid).html();
    var data1 = google.visualization.arrayToDataTable(data);


    var options =
    {
        title: title,
        bar: { groupWidth: 'auto' }, 
        hAxis: {title: xAxisLabel, titleTextStyle: {color: '#009900'}},
        vAxis: {title: yAxisLabel, titleTextStyle: {color: '#009900'}}
    };
    
    if(data.length>1)
    {
        var chart = new google.visualization.ColumnChart(document.getElementById(''+divid));
        chart.draw(data1, options);
        $("#"+divid+"_button").show();
    }
    else
    {
        $("#"+divid+"_button").hide();
        $("#"+divid).html("<img src='images/noresults.png'/>");
    }    
}

function drawBarChart(data,title,xAxisLabel,yAxisLabel,divid)
{
    $("#"+divid).html();
    var data1 = google.visualization.arrayToDataTable(data);


    var options =
    {
        title: title,
        bar: { groupWidth: '20%' }, 
        hAxis: {title: yAxisLabel, titleTextStyle: {color: '#009900'}},
        vAxis: {title: xAxisLabel, titleTextStyle: {color: '#009900'}}
    };
    
    if(data.length>1)
    {
        var chart = new google.visualization.BarChart(document.getElementById(''+divid));
        chart.draw(data1, options);
        $("#"+divid+"_button").show();
    }
    else
    {
        $("#"+divid+"_button").hide();
        $("#"+divid).html("<img src='images/noresults.png'/>");
    }    
}

function drawPieChart(data,title,xAxisLabel,yAxisLabel,divid)
{
    $("#"+divid).html();
    var data1 = google.visualization.arrayToDataTable(data);


    var options =
    {
        title: title,
        bar: { groupWidth: '20%' }, 
        hAxis: {title: xAxisLabel, titleTextStyle: {color: '#009900'}},
        vAxis: {title: yAxisLabel, titleTextStyle: {color: '#009900'}}
    };
    
    if(data.length>1)
    {
        var chart = new google.visualization.PieChart(document.getElementById(''+divid));
        chart.draw(data1, options);
        $("#"+divid+"_button").show();
    }
    else
    {
        $("#"+divid+"_button").hide();
        $("#"+divid).html("<img src='images/noresults.png'/>");
    }    
}

function generateTargetArray(dataArray,datalength,datesArray)
{
    dataArray=convertto2D(dataArray,datalength);
    var resultantArray=new Array();
    resultantArray.push(datesArray);
    for(var k=0;k<dataArray.length;++k)
    {
        resultantArray.push(dataArray[k]);
    }
    return resultantArray;
}

function generateTarget3DArray(dataArray1,datalength,datesArray,data,dataArray2)
{
    //dataArray1=convertto2D(dataArray1,datalength);
    var resultantArray=new Array();
    resultantArray.push(datesArray);
    for(var k=0;k<dataArray1.length;++k)
    {
        for(var j=0;j<dataArray1[k].length;++j)
        {
            console.log("data1 array"+dataArray1[k][j]);
        }    
        resultantArray.push(dataArray1[k]);
    }
    for(var k=0;k<dataArray2.length;++k)
    {
        for(var j=0;j<dataArray2[k].length;++j)
        {
            console.log("data2 array"+dataArray2[k][j]);
        }
        resultantArray.push(dataArray2[k]);
    }
    return resultantArray;
}

function rowIteration(dataArray,xValue,yValue)
{
    var singleArray=new Array();
    singleArray.push(xValue,yValue);
    var check=checkElement(dataArray,xValue);
    if(check)
    {
        singleArray=new Array();
        singleArray=dataArray[returnIndex(dataArray,xValue)];
        removeElement(dataArray, singleArray);
        singleArray.push(yValue);
    }
    dataArray.push(singleArray);
    return dataArray;
}


function check(data,element,index)
{
    var status=false;
    for(var i=0;i<data.length;++i)
    {
        if(data[i][index]==element)
        {
            status=true;
            break;
        }
    }
    return status;
}

function getIndex(data,element,index)
{
    var status=false;
    var targetIndex=-1;
    for(var i=0;i<data.length;++i)
    {
        if(data[i][index]==element)
        {
            status=true;
            targetIndex=i;
            break;
        }
    }
    return targetIndex;
}

function getArrayIndex(data,element)
{
    var status=false;
    var targetIndex=-1;
    for(var i=0;i<data.length;++i)
    {
        if(data[i]==element)
        {
            status=true;
            targetIndex=i;
            break;
        }
    }
    return targetIndex;
}