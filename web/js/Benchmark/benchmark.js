/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function ()
{
    userformvalidate();
    //getProcessValidate($("#paramtypecode").val());
});

function messageHide()
{
    $("#message").hide();
}

function userformvalidate()
{
    $("#AddBenchmark").validate(
    {
        rules:
        {
            paramtypecode:
            {
                required: true,
                min:"1"
            },
            business:
            {
                required: true,
                min:"1"
            },
            businessunit:
            {
                required: true,
                min:"1"
            },            
            businesscenter:
            {
                required: true,
                min:"1"
            },
            process:
            {
                required: true,
                min:"1"
            },  
             subprocess:
            {
                required: true,
                min:"1"
            },
            fromdate:
            {
                required: true                
            },
            todate:
            {
                required: true              
               
            },
            top:
            {
                required: true,
                number: true
            },
            median:
            {
                required: true,
                number: true
            },
            low:
            {
                required: true,
                number: true
            },
            status:
            {
                required: true,
                min:"1"
            } 
            
        },
        messages:
        {
            paramtypecode:
            {
                required: "Please select a Benchmark",
                min:"Please select a Benchmark"
            },
            business:
            {
                required: "Please select a Business",
                min:"Please select a Business"
            },
            businessunit:
            {
                required: "Please select a Business unit",
                min:"Please select a Business unit"
            },            
             process:
            {
                required: "Please select a Process",
                min:"Please select a Process"
            },
             subprocess:
            {
                required: "Please select a Sub process",
                min:"Please select a Sub process"
            },
            businesscenter:
            {
                required: "Please select a Location",
                min:"Please select a Location"
            },
            fromdate:
            {
                required: "Please select a From date"                
            },
            todate:
            {
                required: "Please select a To date"          
            },
            top:
            {
                required: "Top value required",
                number:"please enter a valid number"               
            },
            median:
            {
                required: "Medium value required",
                number:"please enter a valid number"
            },            
            low:
            {
                required: "Low value required",
                number:"please enter a valid number"
            },
            status:
            {
                required: "Please select a Status",
                min:"Please select a Status"
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
    $.validator.addMethod("regex",function(value, element, regexp)
        {
            var check = false;
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
            },
            "(^,%,$,|,{,},',/,?,=,+,!,*,()|,\,space are not allowed)"  //No special Characters allowed here. Use only upper and lowercase letters (A through Z; a through z), numbers and punctuation marks (. , : ; ? ' ' \" - = ~ ! @ # $ % ^ & * ( ) _ + / < > { } )
        );
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
    return arg != value;
}, "Value must not equal arg.");
}