/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function showForms(value)
{
    var status=parseInt(value, 10);
    $('label.validationerror').remove();
    switch(status)
    {
        case 1:
            $("#save").attr("value","Create");
            $("#open").show();
            $("#close").hide();
            $("#reopen").hide();
            break;
        case 2:
            $("#save").attr("value","Close");
            $("#sdate").val('');
            $("#opendate").val('0');
            $("#close").show();
            $("#open").hide();
            $("#reopen").hide();
            break;
        case 3:
            $("#save").attr("value","Reopen");
            $("#sdate").val('');
            $("#closedate").val('0');
            $("#reopen").show();
            $("#close").hide();
            $("#open").hide();
            break;
       case 0:
            $("#save").attr("value","Create");
            $("#sdate").val('');
            $("#opendate").val('0');
            $("#closedate").val('0');
            $("#open").show();
            $("#close").hide();
            $("#reopen").hide();
            break;
    }
}

$(document).ready(function()
{
    $('#statementlist').dataTable();
    cisdate('sdate');
    $('#scrollbar1').tinyscrollbar();
    $(".dataTables_length select").change(function()
    {
        $('#scrollbar1').tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update();
    });
    validate();
});

function editStatement(date,status)
{
    load('editstatement.htm?date='+date+"&status="+status);
}

function validate()
{
    $("#Statement").validate(
    {
        submitHandler: function(form)
        {
            ajaxformSubmit(form);
        },
        rules:
        {
            statementStatus:
            {
                required: true,
                min:"1"
            },
            statementdate:
            {
                required: true
            },
            opendate:
            {
                required: true
            },
            closedate:
            {
                required: true
            }
        },
        messages:
        {
            statementStatus:
            {
                required: "Please enter Statement Status",
                min:"Please enter Statement Status"
            },
            statementdate:
            {
                required: "Please select a Date to open"
            },
            opendate:
            {
                required: "Please select a Date to Close"
            },
            closedate:
            {
                required: "Please select a Date to Reopen"
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
        $.validator.addMethod("regex",function(value, element, regexp)
        {
            var check = false;
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
            },
            "(^,%,$,|,{,},',/,?,=,+,!,*,()|,\,space are not allowed)"  //No special Characters allowed here. Use only upper and lowercase letters (A through Z; a through z), numbers and punctuation marks (. , : ; ? ' ' \" - = ~ ! @ # $ % ^ & * ( ) _ + / < > { } )
        );
}

function checkDate(date,val,id)
{
    if(date!="")
    {
        $.ajax(
        {
            cache:false,
            datatype:'json',
            type:'POST',
            url:'checkDate.htm?date='+encodeURIComponent(date),
            success:function(json)
            {
                if(json.check=='false')
                {
                    var label = "<label for='status' generated='true' class='validationerror'>Statement Date already added</label>";
                    $("#invalid-sdate").html(label);
                    $("#"+id).val('');
                    return false;
                }
                else
                {
                    $("#invalid-sdate").html('');
                    return true;
                }
            }
        });
    }
}
