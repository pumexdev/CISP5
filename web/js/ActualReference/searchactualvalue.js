/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function ()
{
   searchformvalidate();
});

function UpdateForms()
{
    $("#business").val('0');
    $("#businessunit").val('0');
    $("#businesscenter").val('0');    
    $("#message").hide();
}
function searchformvalidate()
{
    $("#SearchActualValue").validate(
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
                required: true, 
                min:"1"
            },
            todate:
            {
                required: true,
                min:"1"
               
            }           
            
        },
        messages:
        {
            paramtypecode:
            {
                required: "Please select a parameter",
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
            businesscenter:
            {
                required: "Please select a Location",
                min:"Please select a Location"
            },
            fromdate:
            {
                min: "Please select a Year"                
            },
            todate:
            {
                min: "Please select a Month"          
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


