/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function globalprofile11_12()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#dat1").val();
    var date2=$("#dat2").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1};
    changePieChart('ajaxglobalcost1.htm', params, array1, array2,'Resources per Process','Process','Resources', 'graphdiv1');
}

function globalprofile1_2()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#dat1").val();
    var date2=$("#dat2").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1};
    changeGraph1('ajaxglobalprofile1.htm', params, array1, array2,'Resources per Center','Center','Resources', 'graphdiv2');
}

function globalprofile6_7()
{
    var array1=new Array();
    var array2=new Array();
    var date1=$("#dat1").val();
    var date2=$("#dat2").val();
    array1.push(date1);
    array1.push(date2);
    array2.push(date1);
    var params={date:array1};
    changePieChart('ajaxglobalefficiency1.htm', params, array1, array2,'Resources per Tier','Tier','Resources', 'graphdiv3');
}

$(document).ready(function()
{
    getLandingData($("#dat1").val());
    globalprofile11_12();
    globalprofile6_7();
    globalprofile1_2();
    $(".landing_left").jScrollPane({autoReinitialise:true});
    $("#date1").change(function()
    {
        if($(this).val()!="")
        {
            globalprofile11_12();
            globalprofile6_7();
            globalprofile1_2();
            getLandingData($(this).val());
        }
    });
});


function getLandingData(value)
{
    $.ajax(
    {
       cache:false,
       type:'POST',
       dataType:'json',
       url:'landingdata.htm',
       data:{date1:value},
       beforeSend:function()
       {
           $("#throbber").show();
       },
       success:function(json)
       {
           var data1 = parseFloat(json.ptpAnnualizedinvoice);
           var data2 = parseFloat(json.costperinvoice);
           var data3 = parseFloat(json.otcannualizedcost);
           var data4 = parseFloat(json.otccostpercash);
           var data5 = parseFloat(json.rtrannualizedmanual);
           var data6 = parseFloat(json.rtrcostpermanualje);
           var data7 = parseFloat(json.totalemployees);
           var data8 = parseFloat(json.avgcostperfte);
           data1 = addCommas(data1.toString());
           data2 = addCommas(data2.toString());
           data3 = addCommas(data3.toString());
           data4 = addCommas(data4.toString());
           data5 = addCommas(data5.toString());
           data6 = addCommas(data6.toString());
           data6 = addCommas(data6.toString());
           data8 = addCommas(data8.toString());
           $("#1").html(data1);
           $("#2").html(data2);
           $("#3").html(data3);
           $("#4").html(data4);
           $("#5").html(data5);
           $("#6").html(data6);
           $("#7").html(data7);
           $("#8").html(data8);
           $("#throbber").hide();
       }
    });
}

function addCommas(nStr)
{
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}