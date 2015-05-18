/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function()
{
    adddescriptionvalidate();
    editdescriptionvalidate();    
});

function adddescriptionvalidate()
{
    $("#AddDescription").validate(
    {
        rules:
        {
            subprocess:
            {
                required: true
            },
            description:
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
            subprocess:
            {
                required: "Please select a subprocess"
            },            
            description:
            {
                required: "Please enter a description"
                
            },
            status:
            {
                required: "Please select a status"
                                  
            }
        },
        
        errorElement: "td",
        errorPlacement: function(error, element)
        {
            error.appendTo('#invalid-' + element.attr('id'));
            error.css('padding','0px');
            error.css('position', 'absolute');
            error.css('color','red');
            error.css('font-size','11px');
        }
    });
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
function editdescriptionvalidate()
{
    $("#EditDescription").validate(
    {
        rules:
        {
            subprocess:
            {
                required: true
            },
            description:
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
            subprocess:
            {
                required: "Please select a subprocess"
            },            
            description:
            {
                required: "Please enter a description"
                
            },
            status:
            {
                required: "Please select a status"
                                  
            }
        },
        
        errorElement: "td",
        errorPlacement: function(error, element)
        {
            error.appendTo('#invalid-' + element.attr('id'));
            error.css('padding','0px');
            error.css('position', 'absolute');
            error.css('color','red');
            error.css('font-size','11px');
        }
    });
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
function updateForms(value)
{
    $("td.error").remove();
    if(value=="0")
    {
        $("#status").val(0);
        $("#descriptionadd").show();
        $("#descriptionedit").hide();        
    }
    else    
    {
        $("#message").hide();        
        $("#descriptionedit").show();
        $("#descriptionadd").hide();
       
    }
    getDescriptinDetails(value);
}

function getDescriptinDetails(value)
{
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getvolumedescriptiondata.htm?descriptionid='+value,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.EditDescription;
            for(var i=0;i<data.length;++i)
            {
                $("#subprocess1").val(data[i].SubProcessId);
                $("#description1").val(data[i].VolumeDescription);                
                $("#status1").val(data[i].Status);
                $("#descriptionid").val(data[i].VolumeDescId);
                
            }
        }
    });
}