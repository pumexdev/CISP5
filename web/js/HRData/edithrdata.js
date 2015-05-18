/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function ()
{
    edithrformvalidate();
});

function  edithrformvalidate()
{
    $("#EditHRdata").validate(
    {
        submitHandler: function(form){
            var client = true;
            var fte = true;
            $("select[name^='businessunits']").each(function()
            {
                var labelhtml1 = "<label for='costcenter' generated='true' class='validationerror'>Please select Client</label>";
                var val1 = $(this).val();
                var id1 = $(this).attr("id");
                if(val1=="")
                {
                    $("#invalid-"+id1).html(labelhtml1);
                    client=false;
                }
                else
                {
                    $("#invalid-"+id1).html('');
                    client=true;
                }
            });
            
            $("select[name^='fte']").each(function()
            {
                var labelhtml2 = "<label for='costcenter' generated='true' class='validationerror'>Please select FTE</label>";
                var val2 = $(this).val();
                var id2 = $(this).attr("id");
                if(val2=="")
                {
                    $("#invalid-"+id2).html(labelhtml2);
                    fte=false;
                }
                else
                {
                    $("#invalid-"+id2).html('');
                    fte=true;
                }
            });
            var status=iterateAllSums();
            if(status)
            {
                $(form).ajaxSubmit(
                {
                    beforeSend:function()
                    {
                        $("#throbber").show();
                    },
                    success: function(data)
                    {
                        $("#edit").html(data);
                        $("#throbber").hide();
                    },
                    error:function(request,error)
                    {
                        $("#throbber").hide();
                        $("#message").html('Updation failed');
                    }
                });
            }
            else
            {
                $("#invalid-sum").html("Total Fte should be less than or equal to 1");
                return false;
            }
        },
        rules:
        {
            date:
            {
                required: true
            },
            location:
            {
                required: true
            },
            name:
            {
                required: true
            },
            emp_type:
            {
                required: true
            },
            grade:
            {
                required: true,
                number:true
            },
            tier:
            {
                required: true
            },
            process:
            {
                required: true
            },
            subprocess:
            {
                required: true
            },
            costcenter:
            {
                required: true
            },
            total_compensation:
            {
                required: true,
                number:true
            }
        },
        messages:
        {
            date:
            {
                required: "Please select a Date"
            },
            location:
            {
                required: "Please select a Center"
            },
            name:
            {
                required: "Please enter a Name"
            },
            emp_type:
            {
                required: "Please select an Employee type"
            },
            grade:
            {
                required: "Please enter a Grade",
                number:"Only numbers"
            },
            tier:
            {
                required: "Please select a Tier"
            },
            process:
            {
                required: "Please select a Process"
            },
            subprocess:
            {
                required: "Please select a Sub-Process"
            },
            costcenter:
            {
                required: "Please enter Cost Center"
            },
            total_compensation:
            {
                required: "Please enter a Labor Cost",
                number:"Only numbers"
            }
        },
        errorElement: "label",
        errorPlacement: function(error, element)
        {
            $("#invalid-"+element.attr('id')).html('');
            error.appendTo('#invalid-' + element.attr('id'));
            error.attr('class','validationerror');
        }});
    
    $.validator.addMethod("valueNotEquals", function(value, element, arg)
    {
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
            digits: "Please enter only digits.",
            creditcard: "Please enter a valid credit card number.",
            equalTo: "Please enter the same value again.",
            accept: "Please enter a value with a valid extension.",
            maxlength: $.validator.format("Please enter no more than {0} characters."),
            minlength: $.validator.format("Please enter at least {0} characters."),
            rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
            range: $.validator.format("Please enter a value between {0} and {1}."),
            max: $.validator.format("Please enter a value less than or equal to {0}."),
            min: $.validator.format("Please enter a value greater than or equal to {0}.")
        });
}


function updateSubprocess(value,id)
{
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
            var html="<option value='0'>Select</option>";
            $.each(data,function(i,val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#"+id).html(html);           
        }
    });    
}

function numberCheck(event)
{
    var keycode=returnkeycode(event);
    if( !(keycode==8 || keycode==32 || keycode==46 || (keycode>=48 && keycode<=57)))
        event.preventDefault();
}

function addRow(rowid,rowno)
{
    var count=$("#count").val();
    count=parseInt(count, 10);
    var validationrow="<tr id='validationrow'><td></td><td id='invalid-client'></td><td></td><td id='invalid-fte'></td></tr>";
    validationrow=validationrow.replace("validationrow", "validationrow"+(count+1), "m");
    validationrow=validationrow.replace("invalid-client", "invalid-client"+(count+1), "m");
    validationrow=validationrow.replace("invalid-fte", "invalid-fte"+(count+1), "m");
    
    var newhtml="<tr>"+$(rowid).parent().parent().html()+"</tr>";
    newhtml=newhtml.replace("client"+rowno, "client"+(count+1), "gm");
    newhtml=newhtml.replace("clientvalue"+rowno, "clientvalue"+(count+1), "gm");
    newhtml=newhtml.replace("businessunits"+rowno, "businessunits"+(count+1), "gm");
    newhtml=newhtml.replace("fte"+rowno, "fte"+(count+1), "gm");
    newhtml=newhtml.replace("ftevalue"+rowno, "ftevalue"+(count+1), "gm");
    newhtml=newhtml.replace("compensation"+rowno, "compensation"+(count+1), "gm");
    newhtml=newhtml.replace("addRow(this,'"+rowno+"')", "addRow(this,'"+(count+1)+"')", "gm");
    newhtml=newhtml.replace("delRow(this,'"+rowno+"')", "delRow(this,'"+(count+1)+"')", "gm");
    
    newhtml=validationrow+newhtml;
    
    $("#empdet1").last().append(newhtml);
    $("#compensation"+(count+1)).html('');
    $("#count").val(count+1);
    
    //$('#scrollbar1').tinyscrollbar();
    //$("#scrollbar1").tinyscrollbar_update('bottom');
}

function delRow(rowid,rowno)
{
    var tablelength=($("#empdet1 tbody tr").length)-3;
    if(tablelength==0)
        alert("This will delete all rows");
    else
    {
        $(rowid).parent().parent().remove();
        $("#validationrow"+rowno).remove();
    }
}

function updateCompensation(value,id)
{
    var compensation=$("#total_compensation2").val();
    if(compensation=="")
        compensation="0.0";
    if(value=="")
        value="0.0";
    compensation=parseFloat(compensation);
    value=parseFloat(value);
    $("#"+id).html(parseFloat(compensation*value));
}

function iterateAllSums()
{
    var sum=0.0;
    $("input[name^='ftevalue']").each(function(){
        console.log($(this).attr("id")+"--"+value);
        var value=$(this).val();
        if(value.toString()=="NAN")
            value=0;
        sum+=parseFloat(value);
    });
    if(sum<=1.0)
        return true;
    else
        return false;
}

function cancelEditHr()
{
    var confirmation = confirm("Are you sure to cancel?");
    if(confirmation)
    {
        $("#add").load('addhrdata.htm');
        var tabno = $("#tabno").val();
        $('#tabs a:last').click();
    }
}

function compensationUpdate()
{
    var count = 1;
    var compensation = $("#total_compensation2").val();
    alert(compensation);
    if (compensation == "")
        compensation = "0.0";
    compensation = parseFloat(compensation);
    $("select[name^='fte']").each(function()
    {
        var val = $(this).val();
        if (val == "")
            val = "0.0";
        val = parseFloat(val);
        $("#compensation" + count).html(parseFloat(compensation * val).toFixed(2));
        count++;
        console.log(count+"---"+val+"---"+$("#compensation" + count).html());
    });
}

