/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function()
{
   searchprocesstargetvalidate();
});

function searchprocesstargetvalidate()
{
    
    
    $("#SearchTierProcessTarget").validate(
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
        errorElement: "label",
        errorPlacement: function(error, element)
        {
            offset = element.offset();
            error.insertBefore(element);
            error.css('position', 'absolute');
            error.css('color','red');
            error.css('font-size','11px');
            error.css('left', offset.left-15);
            error.css('top', offset.top+30);
        }});
}

