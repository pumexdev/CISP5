$(document).ready(function ()
    {
        addtiertargetvalidate();
        cisfuturedate("from");
        cisdate("to");
    });

function addtiertargetvalidate()
{
    $("#TierTarget").validate(
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
                number: "Please enter a valid number ",
                required: "Please enter a Target"
                                  
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
    
