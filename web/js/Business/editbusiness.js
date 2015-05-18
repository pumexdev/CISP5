function editbusinessvalidate()
{
    $("#EditBusiness").validate(
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
                regex: /^[A-Za-z\s\d@#_.-]+$/   //Allows alphabets,digits,spaces,@#_.-
            /*regex: /^[A-Za-z\d@#_.-]+$/     //Allows alphabets,digits,@#_.-     No spaces*/
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
                regex:"(^,%,$,|,{,},',/,?,=,+,!,*,()|,\ are not allowed)"
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
        errorElement: "td",
        errorPlacement: function(error, element)
        {
            /*offset = element.offset();
            error.insertBefore(element);
            error.css('position', 'absolute');
            error.css('color','red');
            error.css('font-size','11px');
            error.css('left', offset.left-11);
            error.css('top', offset.top-14);*/
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
}