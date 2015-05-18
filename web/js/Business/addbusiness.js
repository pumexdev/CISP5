

/*$(document).ready(function ()
{
    userformvalidate();
});*/

function addbusinessvalidate()
{
   $("#Business").validate(
    {
        rules:
        {
            name:
            {
                required: true,
                minlength:2,
                regex: /^[A-Za-z\s\d@#_.-]+$/   //Allows alphabets,digits,spaces,@#_.-
            },
            shortName:
            {
                required: true,
                minlength:2,
                regex: /^[A-Za-z\d@#_.-]+$/     //Allows alphabets,digits,@#_.-     No spaces
            },
            organization:
            {
                min:"1"
            },
            status:
            {
                min:"1"
            }
        },
        messages:
        {
            name:
            {
                required: "Please enter a Name",
                minlength:"Name should contain atleast 2 characters",
                regex:"(^,%,$,|,{,},',/,?,=,+,!,*,()|,\ are not allowed)"               
            },
            shortName:
            {
                required: "Please enter a Short Name",
                minlength:"Short Name should contain atleast 2 characters",
                regex:"(^,%,$,|,{,},',/,?,=,+,!,*,()|,\,space are not allowed)"
            },
            organization:
            {
                min:"Please select a Organzation"
            },
            status:
            {
                min:"Please select a Status"
            }
        },
        //errorElement: "label",
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
        $.validator.addMethod("regex",function(value, element, regexp)
        {
            var check = false;
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
            },
            "(^,%,$,|,{,},',/,?,=,+,!,*,()|,\,space are not allowed)"  //No special Characters allowed here. Use only upper and lowercase letters (A through Z; a through z), numbers and punctuation marks (. , : ; ? ' ' \" - = ~ ! @ # $ % ^ & * ( ) _ + / < > { } )
        );
}