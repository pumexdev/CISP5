/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function ()
{
    kpisearchValidate();
    if($("#operation").val()=="2")
    {
        disableAll();
    }
    $('#scrollbar1').tinyscrollbar();
    $("#scrollbar1").tinyscrollbar_update();
    addPercentage();
    $("#kpidiv").jScrollPane();
});



function kpisearchValidate()
{
    $("#KPI").validate(
    {
        submitHandler: function(form)
        {
            var operation = $("#operation").val();
            operation=parseInt(operation, 10);
            if(operation==2)
            {
                kpiValidate(form);
            }
            else
            {
                formSubmit();
                ajaxformSubmit(form);
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
            }
        },
        errorElement: "label",
        errorPlacement: function(error, element)
        {
            $("#invalid-"+element.attr('id')).html('');
            error.appendTo('#invalid-' + element.attr('id'));
            error.attr('class','validationerror');
        }
    });
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

function kpiValidate(form)
{
    var status=true;
    $("input[name='clientkpi1']").each(function()
    {
        var id=$(this).attr("id");
        var kpivalue=$(this).val();
        var pattern = new RegExp("^\\$?([0-9]{1,3},([0-9]{3},)*[0-9]{3}|[0-9]+)(\\.[0-9]*)?$");
        var check = pattern.test(kpivalue);
        kpivalue = kpivalue.replace(",", "", "g");
        kpivalue = kpivalue.replace("%", "", "g");
        $("#c"+id).val(kpivalue);
        if(kpivalue=="")
        {
            $("#invalid-"+id).html('*');
            $("#invalid-"+id).css("color","red");
            status=false;
        }
        else
        {
            check = pattern.test(kpivalue);
            if(!check)
            {
                $("#invalid-"+id).html('Only Digits');
                $("#invalid-"+id).css("color","red");
                status=false;
            }
            else
                $("#invalid-"+id).html('');
            $("#kpidiv").jScrollPane();
        }
    });
    $("input[name='centeravg1']").each(function()
    {
        var id=$(this).attr("id");
        var centeravg=$(this).val();
        var pattern = new RegExp("^\\$?([0-9]{1,3},([0-9]{3},)*[0-9]{3}|[0-9]+)(\\.[0-9]*)?$");
        var check = pattern.test(centeravg);
        centeravg = centeravg.replace(",", "", "g");
        centeravg = centeravg.replace("%", "", "g");
        $("#c"+id).val(centeravg);
        if(centeravg=="")
        {
            $("#invalid-"+id).html('Center average required');
            $("#invalid-"+id).css("color","red");
            status=false;
        }
        else
        {
            check = pattern.test(centeravg);
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
    $("#scrollbar1").tinyscrollbar_update('bottom');
    $("#kpidiv").jScrollPane();
    if(status==true)
    {
        formSubmit();
        ajaxformSubmit(form);
    }
    else
        return status;
}

function disableAll()
{
    $("#businesscenter").attr("disabled","true");
    $("#fromdate").attr("disabled","true");
}

function addPercentage()
{
    $("input[name=clientkpi1]").each(function()
    {
        $(this).change(function()
        {
            var val = $(this).val().replace("%", "", "g");
            $(this).val(val+"%");
        });
    });
}

function kpicancel()
{
    var confirmation = confirm("Are you sure to cancel?");
    if(confirmation)
    {
        load('kpi.htm');
    }    
}

