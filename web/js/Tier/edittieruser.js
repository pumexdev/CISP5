/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function ()
{
    tierformvalidate();
   
});

function tierformvalidate()
{
    $("#EditTier").validate(
    {
        rules:
        {
        
            tier:
            {
                required: true,
                number: true
            },
            designation:
            {
                required: true,
                regex: /^[A-Za-z\s\d]+$/   //Allows alphabets,digits,spaces,@#_.-
                
            }
            
        },
        messages:
        {
            tier:
            {
                required: "Please enter a Tier value",
                number  : "Only numbers are allowed"
            },
            designation:
            {
                required: "Please enter a Designation",                
                regex:"(@,#,^,%,$,|,{,},',/,?,=,+,!,*,()|,\ are not allowed)"
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
        $.validator.addMethod("regex",function(value, element, regexp)
        {
            var check = false;
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
            },
            "(^,%,$,|,{,},',/,?,=,+,!,*,()|,\,space are not allowed)"  //No special Characters allowed here. Use only upper and lowercase letters (A through Z; a through z), numbers and punctuation marks (. , : ; ? ' ' \" - = ~ ! @ # $ % ^ & * ( ) _ + / < > { } )
        );
}

