/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function()
{
//    addsubprocessvalidate();
//    editsubprocessvalidate();
    $("#subprocesslist").dataTable();
    $('#scroll').jScrollPane({autoReinitialise:true});
});


function updateForms(value1)
{
    $("td.error").remove();
    if(value1=="0")
    {
        $("#subprocess").val(0);
        $("#SubProcess1").show();
        $("#EditSubProcess1").hide();
        $('#divhead').html('Add Sub-Process');
        
    }
    else
    {
        $("#message").hide();
        $("#suprocessid").val(value1);
        $("#EditSubProcess1").show();
        $("#SubProcess1").hide();
        $('#divhead').html('Edit Sub-Process');
        getSubProcessDetails(value1);
    }
}

function getSubProcessDetails(value1)
{
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getsubprocessdetails.htm?spid='+value1,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.SubProcessList;
            for(var i=0;i<data.length;++i)
            {
                $("#name2").val(data[i].name);
                $("#subprocessname").val(data[i].name);
                $("#shortName2").val(data[i].shortName);               
                $("#status2").val(data[i].status);
                $("#process2").val(data[i].process);               
                $("#subprocess2").val(data[i].subprocess);
                if(data[i].usedforvolume.match("true"))
                {
                    $("#usedforvolume2").attr("checked", true);
                }
                else
                {
                    $("#usedforvolume2").attr("checked", false);
                }
            }
        }
    });   
}
function updateSubprocess(value)
{
    $("#processid1").val(value);
    $("#message").hide();
    $.ajax(
    {        
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getsubprocess.htm?pid='+value,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.SubProcessList;
            var html="<option value='0'>Create New</option>";
            $.each(data,function(i,val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#subprocess").html(html);
        }
    });
    $("#SubProcess1").show();
    $("#EditSubProcess1").hide();
    $("#subprocessname").val("");
    $('#divhead').html('Add Sub-Process');
    
}

function checkSubProcessName(subProcessName,id,name,async)
{
    var asyncvalue=false;
    if(async=="1")
        asyncvalue=true;
    if(subProcessName!="" && subProcessName!=$("#subprocessname").val())
    {
        $.ajax(
        {
            async:asyncvalue,
            cache:false,
            datatype:'json',
            type:'POST',
            url:'checksubprocess.htm?name='+encodeURIComponent(subProcessName),
            success:function(json)
            {
                if(json.check=='true')
                {
                    $("#"+id).html('');
                }
                else if(json.check=='false')
                {
                    $('label.error').empty();
                    $("#"+id).html("<label class='validationerror' for='"+id+"' generated='true'>Subprocess already exists</label>");
                    $('#'+name).val($("#subprocessname").val());
                }
                return json.check;
            }
        }
        );
    }
}

function checkUsedforvolume(id)
{
    if($('#'+id).attr('checked'))
        $('#'+id).attr('checked', false);
    else
        $('#'+id).attr('checked', true);   
}
