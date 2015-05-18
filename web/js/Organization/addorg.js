

$(document).ready(function ()
{
    userformvalidate();
});

function userformvalidate()
{
    $("#Organization").validate(
    {
        rules:
        {
            name:
            {
                required: true,
                minlength:2
            },
            shortName:
            {
                //required: true,
                minlength:2
            },
            address:
            {
                //required: true,
                minlength:2
            },
            contactPerson:
            {
                //required: true,
                minlength:2
            },
            phno1:
            {
                //required: true,
                minlength:2
            },
            phno2:
            {
                //required: true,
                minlength:2
            },
            emailid:
            {
                //required: true,
                email:true
            },
            website:
            {
                //required: true,
                url:true
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
                required: "Please Choose a Name",
                minlength:"Name should contain atleast 2 characters"
            },
            shortName:
            {
                //required: "Please enter a Short Name",
                minlength:"Shortname should contain atleast 2 characters"
            },
            address:
            {
                //required: "Please enter a Address",
                minlength:"Address should contain atleast 2 characters"
            },
            contactPerson:
            {
                //required: "Please enter a Person to contact",
                minlength:"Contact person should contain atleast 2 characters"
            },
            phno1:
            {
                //required: "Please enter a Phone Number",
                minlength:"Phone Number should contain atleast 2 characters"
            },
            phno2:
            {
                //required: "Please enter a Phone Number",
                minlength:"Phone Number should contain atleast 2 characters"
            },
            emailid:
            {
                //required: "Please enter a EmailID",
                email:"Please enter a valid EmailID"
            },
            website:
            {
                //required: "Please enter a Website",
                url:"Please enter a valid Url(eg:http://www.abc.com)"
            },
            status:
            {
                min:"Please select a Status"
            }
        },
        errorElement: "label",
        errorPlacement: function(error, element)
        {
            offset = element.offset();
            error.insertBefore(element);
            error.css('position', 'absolute');
            error.css('color','red');
            error.css('font-size','14px');
            error.css('font-weight','bold');
            error.css('left', offset.left + element.outerWidth());
            error.css('top', offset.top+'3px');
        }});
        $.validator.addMethod("valueNotEquals", function(value, element, arg){
            return arg != value;
        }, "Value must not equal arg.");
}