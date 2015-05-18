$(document).ready(function ()
{
    addtiervalidate();
   
});

function addtiervalidate()
{
    $("#SubProcessTarget").validate(
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
               min: "Please select a Sub process"
                
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
            offset = element.offset();
            error.insertBefore(element);
            error.css('position', 'absolute');
            error.css('color','red');
            error.css('font-size','11px');           
            error.css('padding-left','19%');
            error.css('top', offset.top+'1px');
        
    }});
}
    
