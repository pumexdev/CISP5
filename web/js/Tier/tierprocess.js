$(document).ready(function ()
    {
        addtiervalidate();
        cisfuturedate("from");
        cisdate("to");
    });

function addtiervalidate()
{
    $("#AddTierProcess").validate(
    {
        rules:
        {
        
            fromdate:
            {
                required: true
                
            },
            todate:
            {
                required: true
                
            },
            process:
            {
                min:"1"
                
            },
            tier:
            {
                min:"1"
                
            },
            status:
            {
                min:"1"
                
            },
            target:
            {
                number: true,
                required: true
               
            }
            
        },
        messages:
        {
            fromdate:
            {
                required: "Please select a From date"
                
            },
            todate:
            {
                required: "Please select a To date"
            },
            process:
            {
                min: "Please select a Process"
                
            },
            tier:
            {
                min: "Please select a Tier"
                
            },
            status:
            {
                min: "Please select a Status"
            },
            target:
            {
                number: "Please eneter a valid number ",
                required: "Please eneter a Target"
                                  
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
    $.validator.addMethod("regex",function(value, element, regexp)
    {
        var check = false;
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "(^,%,$,|,{,},',/,?,=,+,!,*,()|,\,space are not allowed)"  //No special Characters allowed here. Use only upper and lowercase letters (A through Z; a through z), numbers and punctuation marks (. , : ; ? ' ' \" - = ~ ! @ # $ % ^ & * ( ) _ + / < > { } )
    );
}
    
