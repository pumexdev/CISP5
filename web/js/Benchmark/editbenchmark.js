/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function ()
{
    userformvalidate();
    //getProcessValidate($("#paramtypecode").val());
});

function userformvalidate()
{
    $("#EditBenchmark").validate(
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
                min:"Please select a benchmark"
            },
            business:
            {
                required: "Please select a Business",
                min:"Please select a business"
            },
            businessunit:
            {
                required: "Please select a Business unit",
                min:"Please select a business unit"
            },
            process:
            {
                required: "Please select a Process",
                min:"Please select a process"
            },
             subprocess:
            {
                required: "Please select a Sub process",
                min:"Please select a sub process"
            },
            businesscenter:
            {
                required: "Please select a Location",
                min:"Please select a location"
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
        }});
        $.validator.addMethod("valueNotEquals", function(value, element, arg){
            return arg != value;
        }, "Value must not equal arg.");
}
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


