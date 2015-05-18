
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 
function getBusinessCenter(businessid,id)
{
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'GET',
        url:'getbusinesscenter.htm?bid='+businessid,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.BusinessCenter;
            var html="<option value='0'>Select</option>";
            $.each(data,function(i,val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#"+id).html(html);
        }
    });
}

function getProcessValidate(paramtypecode)
{
    $('label.validationerror').remove();
    if (paramtypecode==1)
    {
        $("#processtr").hide();
        $("#process").val('0');
        $("#subprocess").val('0');
        
    }
    else
    {
        $("#processtr").show();
    }  
}

function getBusinessUnits(businessid)
{
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getbusinessunits.htm?bid='+businessid,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.BusinessUnits;
            $("#businessunit").empty();
            var html="<option value='0'>Select</option>";
            $.each(data,function(i,val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#businessunit").html(html);
        }
    });
}

function getSubProcess(processid,id)
{
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getsubprocess.htm?pid='+processid,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.SubProcessList;
            var html="<option value='0'>Select</option>";
            $.each(data,function(i,val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#"+id).html(html);
        }
    });
}

function bmsearchvalidate()
{
    if(document.getElementById("business").value==0||document.getElementById("business").value==null||document.getElementById("business").value=='')
    {
        alert("Select Business");
        return false;
    }
    else  if(document.getElementById("businessunit").value==0||document.getElementById("businessunit").value==null||document.getElementById("businessunit").value=='')
    {
        alert("Select Business unit");
        return false;
    }
    else  if(document.getElementById("businesscenter").value==0||document.getElementById("businesscenter").value==null||document.getElementById("businesscenter").value=='')
    {
        alert("Select Business Center");
        return false;
    }
    else if(document.getElementById("process").value==0||document.getElementById("process").value==null||document.getElementById("process").value=='')
    {
        alert("Select Process");
        return false;
    }
    else if(document.getElementById("subprocess").value==0||document.getElementById("subprocess").value==null||document.getElementById("subprocess").value==''){
        alert("Select Sub Process");
        return false;
    }
    return true;        
       
}
    
function delbm(rowSlno,bmid,tableId)
{
    alert("Sure You Want to Delete?");
    $.getJSON("delbm.htm?bmId="+bmid,
        function(data)
        {
            var table = document.getElementById(tableId);
            var rowCount = table.rows.length;
            if(rowCount>1)
            {
                table.deleteRow(rowSlno-1);
            }
        }); 
}