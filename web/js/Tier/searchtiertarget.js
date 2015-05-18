/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function()
{
   searchtiertargetvalidate();
});

function messageHide()
{
    $("#message").hide();
}
function searchtiertargetvalidate()
{
    
    $("#SearchTierTarget").validate(
    {
        rules:
        {
        
            fromdate:
            {
                min:"1"
                
            },
            month:
            {
               min:"1"
                
            }
        },
        messages:
        {
            fromdate:
            {
                min: "Please select a Year"
                
            },
            month:
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
}
