/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function ()
{
    userformvalidate();
   getProcessValidate($("#paramtypecode").val());
});

function messageHide()
{
    $("#message").hide();
}

function userformvalidate()
{
    $("#EditActualValue").validate(
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
            actual:
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
                required: "Please select an Actual value",
                min:"Please select an Actual value"
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
                required: "Please select a From date" ,
                min:"Please select a From date"
            },
            todate:
            {
                required: "Please select a To date",
                min:"Please select a To date"
            },            
            actual:
            {
                required:"Please enter an Actual value",
                number:"please enter a valid number"
            },
            status:
            {
                required:"Please select a Status",
                min:"Please select a Status"
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
        }});
        $.validator.addMethod("valueNotEquals", function(value, element, arg){
            return arg != value;
        }, "Value must not equal arg.");
}


