/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *Operation
 *1-Search
 *2-Edit
 *3-Add
 *
 **/



$(document).ready(function ()
{
    $("#actualdiv").jScrollPane();
    $(".q_inner_top").jScrollPane();
    setid();
    actualValueOnload();
    var path=window.location.pathname;
    console.log(path.split("/", path.length)[2]);
    var operation=$("#operation").val();
    operation=parseInt(operation, 10);
    addActualformvalidate();
    switch(operation)
    {
        case 1:
               break;
        case 2:
               $("#businesscenter").attr("disabled","true");
               $("#fromdate").attr("disabled","true");
               $("#paramtypecode").attr("disabled","true");
               $("#status").attr("disabled","true");
               break;
        case 3:
               $("#businesscenter").attr("disabled","true");
               $("#fromdate").attr("disabled","true");
               $("#paramtypecode").attr("disabled","true");
               $("#status").attr("disabled","true");
               if($("#clientlength").val()!="0")
               {
                   $("#save").show();
               }
               break;
    }
    $('#scrollbar1').tinyscrollbar();
    $("#scrollbar1").tinyscrollbar_update();
});

function messageHide()
{
    $("#message").hide();
}

function addActualformvalidate()
{
    $("#ActualValue").validate(
    {
        submitHandler: function(form)
        {
            var operation = $("#operation").val();
            var paramtypecode = $("#paramtypecode").val();
            if(operation=="2" || operation=="3")
            {
                if(paramtypecode=="1")
                    revenueCheck(form);
                else if(paramtypecode=="2")
                    volumeCheck(form);
            }    
            else
            {
                ajaxformSubmit(form);
                return true;
            }
        },
        rules:
        {
            businesscenter:
            {
                required: true
            },
            fromdate:
            {
                required: true
            },
            paramtypecode:
            {
                required: true
            },
            status:
            {
                required: true
            }
        },
        messages:
        {
            businesscenter:
            {
                required: "Please Select a Business center"
            },
            fromdate:
            {
                required: "Please Select a Date"
            },
            paramtypecode:
            {
                required: "Please Select a Parameter"
            },
            status:
            {
                required: "Please Select a Status"
            }
        },
        errorElement: "label",
        errorPlacement: function(error, element)
        {
            $("#invalid-"+element.attr('id')).html('');
            error.appendTo('#invalid-' + element.attr('id'));
            error.attr('class','validationerror');
        }});
        $.validator.addMethod("valueNotEquals", function(value, element, arg){
            return arg != value;
        }, "Value must not equal arg.");
        $.extend($.validator.messages, {
            required: "Required.",
            remote: "Please fix this field.",
            email: "Please enter a valid email address.",
            url: "Please enter a valid URL.",
            date: "Please enter a valid date.",
            dateISO: "Please enter a valid date (ISO).",
            number: "Number required",
            digits: "Digits required",
            creditcard: "Please enter a valid credit card number.",
            equalTo: "Please enter the same value again.",
            accept: "Please enter a value with a valid extension.",
            maxlength: $.validator.format("Maximum {0} characters."),
            minlength: $.validator.format("Please enter at least {0} characters."),
            rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
            range: $.validator.format("Please enter a value between {0} and {1}."),
            max: $.validator.format("Please enter a value less than or equal to {0}."),
            min: $.validator.format("Please enter a value greater than or equal to {0}.")
        });
}

function iterateActualValues()
{
    var data=$("#actualvalues").val();
    data=data.replace("{", "", "i");
    data=data.replace("}", "", "i");
    data=data.replace(" ", "", "i");
    var arrays=data.split(",", data.length);
    for(var i=0;i<arrays.length;++i)
    {
        var singlearray=arrays[i].split("=", arrays[i].length);
        var id = singlearray[0];
        var value = singlearray[1];
        value=value.replace(" ", "", "i");
        value=value.replace("null", "", "i");
        value=value.split(".", value.length)[0];
        value=value.split(",", value.length)[0];
        $("#clientvolume"+(i+1)).val(value);
        $("#clientvolumevalue"+(i+1)).val(value);
        $("#actualreferenceid"+(i+1)).val(id);
    }
}

function actualValueOnload()
{
    var volrev=$("#volrev").val();
    volrev=parseInt(volrev, 10);
    var url="";
    switch(volrev)
    {
        case 1:
            url="revenuehelp.htm";
            break;
        case 2:
            url="volumehelp.htm";
            break;
        default:
            url="revenuehelp.htm";
            break;
    }
    var imghtml="<img id='imghelp' src='images/help_balloon.png'/>";
    $("#help").html(imghtml);
    $("#imghelp").bind("click",function()
    {
        windopopup(url);
    });
}

function updategrids()
{
    var value=$("#actualvalueslength").val();
    if(value=="0")
    {
        $("#fieldsetrevenue").hide();
        $("#save").hide();
    }
}

function formsubmit(event)
{
    var keycode=returnkeycode(event);
    if(keycode==13)
        addActualformvalidate();
}

function revenueCheck(form)
{
    
    var status=true;
    $("input[name='clientrevenue1']").each(function()
    {
        var id=$(this).attr("id");
        var revvalue=$(this).val();
        var pattern = new RegExp("^\\$?([0-9]{1,3},([0-9]{2,3},)*[0-9]{3}|[0-9]+)(\\.[0-9]*)?$");
        var check = pattern.test(revvalue);
        revvalue = revvalue.replace(",", "", "g");
        $("#c"+id).val(revvalue);
        if(revvalue=="")
        {
            console.log(isNaN(revvalue));
            $("#invalid-"+id).html('*');
            $("#invalid-"+id).css("color","red");
            status=false;
        }
        else
        {
            if(!check)
            {
                $("#invalid-"+id).html('Only Digits');
                $("#invalid-"+id).css("color","red");
                status=false;
            }
            else
                $("#invalid-"+id).html('');
        }
    });
    if(status==true)
    {
        formSubmit();
        ajaxformSubmit(form);
    }
    else
        return status;
}

function volumeCheck(form)
{
    var status=true;
    $("input[name='clientvolume1']").each(function()
    {
        var id=$(this).attr("id");
        var volvalue=$(this).val();
        var pattern = new RegExp("^\\$?([0-9]{1,3},([0-9]{2,3},)*[0-9]{3}|[0-9]+)(\\.[0-9][0-9])?$");
        var check = pattern.test(volvalue);
        volvalue = volvalue.replace(",", "", "g");
        $("#c"+id).val(volvalue);
        if(volvalue=="")
        {
            $("#invalid-"+id).html('*');
            $("#invalid-"+id).css("color","red");
            status=false;
        }
        else
        {
            console.log(isNaN(volvalue));
            if(!check)
            {
                $("#invalid-"+id).html('Only Digits');
                $("#invalid-"+id).css("color","red");
                status=false;
            }
            else
                $("#invalid-"+id).html('');
        }
    });
    $('#scrollbar1').tinyscrollbar();
    $("#scrollbar1").tinyscrollbar_update(400);
    $("#actualdiv").jScrollPane();
    $(".q_inner_top").jScrollPane();
    if(status==true)
    {
        formSubmit();
        ajaxformSubmit(form);
    }
    else
        return status;
}

function setid()
{
    var count=1;
    $("input[name='clientvolume1']").each(function()
    {
        $(this).attr("id","clientvolume"+count);
        $(this).attr("onkeypress","onChange(this.id, this.value, 'cclientvolume"+count+"')");
        $(this).attr("onblur","onChange(this.id, this.value, 'cclientvolume"+count+"')");
        $(this).attr("onchange","onChange(this.id, this.value, 'cclientvolume"+count+"')");
        count++;
    });
    
    count=1;
    $("input[name='clientvolume']").each(function()
    {
        $(this).attr("id","cclientvolume"+count);
        count++;
    });
}

function volrevcancel()
{
    var confirmation = confirm("Are you sure to cancel?");
    if(confirmation)
    {
        load('addactualvalue.htm');
    }    
}

function kpiload(val)
{
    var paramtypecode = returnOptionText('paramtypecode',val);
    console.log(paramtypecode);
    if(paramtypecode=="kpi" || paramtypecode=="KPI")
    {
        //var conf = confirm("Y")
        load('kpi.htm');
    }
}