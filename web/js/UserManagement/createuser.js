$(document).ready(function ()
{
    userformvalidate();
    $('#userstatus').val('1');
});

function userformvalidate()
{
    $("#User").validate(
    {
        submitHandler: function(form)
        {
            ajaxformSubmit(form);
        },
        rules:
        {
            username:
            {
                required: true,
                minlength:2,
                regex: /^[A-Za-z\s@#_.-]+$/   //Allows alphabets,digits,@#_.-     No digits
            },
            password:
            {
                required: true,
                minlength:8,
                equalTo:'#password'
            },
            cpassword:
            {
                required: true,
                minlength:8,
                equalTo:'#password'
            },
            userlevel:
            {
                min:"1"
            },
            processid:
            {
                min:"1"
            },
            businesscenterid:
            {
                min:"1"
            },
            userstatus:
            {
                min:"1"
            }
        },
        messages:
        {
            username:
            {
                required: "Please enter a User name",
                minlength:"User Name should contain atleast 2 characters",
                regex:"(^,%,$,|,{,},',/,?,=,+,!,*,()|,\,digits are not allowed)"
            },
            password:
            {
               required: 'Please enter password',
                minlength:'Password should contain atleast 8 characters',
                equalTo:'Please enter the same Password'
            },
            cpassword:
            {
                required: 'Please reenter password',
                minlength:'Password should contain atleast 8 characters',
                equalTo:'Please enter the same password'
            },
            userlevel:
            {
                min:"Please select User Level"
            },
            processid:
            {
                min:"Please select Process"
            },
            businesscenterid:
            {
                min:"Please select Business Center"
            },
            userstatus:
            {
                min:"Please select user Status"
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
}

function showDropDowns(value)
{
    $("label.error").remove();
    value=parseInt(value, 10);
    switch(value)
    {
        case 0:
                hideall();
                break;
        case 1:
                hideall();
                break;
        case 2:
                hideall();
                break;
        case 3:
                hideall();
                break;
        case 4:
                $("#locationtr").show();
                $("#processtr").hide();
                $("#processid").val('0');
                $("#locationid").val('0');
                break;
        case 5:
                $("#locationtr").show();
                $("#processtr").show();
                $("#processid").val('0');
                $("#locationid").val('0');
                break;
        case 6:
                hideall();
                break;
    }
}

function checkUser(username)
{
    if(username!="")
    {
        $.ajax(
            {
                cache:false,
                url:'checkuser.htm?user='+encodeURIComponent(username),
                type:'POST',
                datatype:'json',
                success:function(json)
                {
                    if(json.check=="true")
                    {
                        $("#usercheck").html('');
                    }
                    else if(json.check=="false")
                    {
                        $('label.error').empty();
                        $("#invalid-username").html("<label class='validationerror' for='invalid-username' generated='true'>Username already exists</label>");
                        $('#username').val('');
                    }
                    return json.check;
                }
            }
            );
    }
}

function hideall()
{
    $("#processtr").hide();
    $("#locationtr").hide();
    $("#processid").val('0');
    $("#locationid").val('0');
}