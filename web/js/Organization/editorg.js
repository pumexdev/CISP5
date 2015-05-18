function editorganizationvalidate()
{
    $("#EditOrganization").validate(
    {
        submitHandler: function(form)
        {
            var file = document.getElementById("file");
            if($("#file").val().length==0)
            {
                file.parentNode.removeChild(file);
            }
            
            $(form).ajaxSubmit({
                beforeSend:function()
                {
                    $("#throbber").show();
                },
                success: function(data)
                {
                    $("#global_area_main_admin").html(data);
                    $("#throbber").hide();
                },
                error:function()
                {
                    $("#message").html('Updation failed');
                }
            });
            //ajaxformSubmit(form);
        },
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
                //required: true,
                minlength:2,
                regex: /^[A-Za-z\s\d@#_.-]+$/   //Allows alphabets,digits,spaces,@#_.-
            },
            address:
            {
                //required: true,
                minlength:2,
                maxlength:255
            },
            contactPerson:
            {
                //required: true,
                minlength:2,
                regex: /^[A-Za-z\s@#_.-]+$/   //Allows alphabets,digits,@#_.-     No digits
            },
            phno1:
            {
                required: true,
                minlength:2,
                regex: /^[\d-]+$/              //Allows digits and - only
            },
            phno2:
            {
                //required: true,
                minlength:2,
                regex: /^[\d-]+$/               //Allows digits and - only
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
            },
            file:
            {
                accept: "jpg,jpeg,JPG,JPEG,png,PNG", 
                filesize:1048576
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
                //required: "Please enter a Short Name",
                minlength:"Short Name should contain atleast 2 characters",
                regex:"(^,%,$,|,{,},',/,?,=,+,!,*,()|,\ are not allowed)"
            },
            address:
            {
                //required: "Please enter an Address",
                minlength:"Address should contain atleast 2 characters",
                maxlength:"Maximum 255 characters are allowed"
            },
            contactPerson:
            {
                //required: "Please enter a Person to contact",
                minlength:"Contact person should contain atleast 2 characters",
                regex:"(^,%,$,|,{,},',/,?,=,+,!,*,()|,\,digits are not allowed)"
            },
            phno1:
            {
                //required: "Please enter a Phone Number",
                minlength:"Phone Number should contain atleast 2 characters",
                regex:"(A-Z,a-z,^,%,$,|,{,},',/,?,=,+,!,*,()|,\ are not allowed)"
            },
            phno2:
            {
                //required: "Please enter a Phone Number",
                minlength:"Phone Number should contain atleast 2 characters",
                regex:"(A-Z,a-z,^,%,$,|,{,},',/,?,=,+,!,*,()|,\ are not allowed)"
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
            },
            file:
            {
                accept:'Only image files are allowed',
                filesize:'Maximum file size is 1 MB'
            }
        },
        errorElement: "label",
        errorPlacement: function(error, element)
        {
            $("#invalid-"+element.attr('id')).html('');
            error.appendTo('#invalid-' + element.attr('id'));
            error.attr('class','validationerror');
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
        $.validator.addMethod('filesize', function(value, element, param)
        {
            return this.optional(element) || (element.files[0].size <= param)
        });
}

//function formsubmit()
//{
//    $.ajax({
//        dataType:'html',
//        type: "POST",
//        url: 'organization.htm',
//        data: $("#EditOrganization").serialize(), // our form data da give and id to the form we use here
//        success: function(data)
//        {
//            alert(data);
//        }
//    });
//}=======
