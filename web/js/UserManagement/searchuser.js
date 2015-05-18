$(document).ready(function ()
{
    updateUserTable();
    var url="userhelp.htm";
    var imghtml="<img id='imghelp' src='images/help_balloon.png'/>";
    $("#help").html(imghtml);
    $("#imghelp").bind("click",function()
    {
        windopopup(url);
    });
    $('#usertable').dataTable();
    $('#scrollbar1').tinyscrollbar();
    $(".dataTables_length select").change(function()
    {
        $('#scrollbar1').tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update();
    });
});

function userformvalidate()
{
    $("#SearchUser").validate(
    {
        rules:
        {
            username:
            {
                username: "Type first letter/name",
                regex: /^[A-Za-z\d@#_.-]+$/ /*add the necessary symbols after the $-*/,
                require_from_group: [1,".filled"]
            },
            userlevel:
            {
                require_from_group: [1,".filled"]
            }            
        },
        messages:
        {
            username:
            {
                username:"ssasasasas",
                regex:"Only alphabets ,number and special characters(@#-_. are allowed)",
                require_from_group:"Please enter atleast one element"
            },
            userlevel:
            {
                require_from_group: "Please enter atleast one element"
            }
        },
        errorElement: "label",
        errorPlacement: function(error, element)
        {
            $("#invalid-"+element.attr('id')).html('');
            error.css('color','red');
            error.appendTo('#invalid-' + element.attr('id'));
            error.attr('class','validationerror');
        }
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    }, "Value must not equal arg.");
        
        
    $.validator.addMethod("require_from_group", function(value, element, options){
        var numberRequired = options[0],
        selector = options[1],
        $fields = $(selector, element.form),
        validOrNot = $fields.filter(function() {
            return $(this).val();
        }).length >= numberRequired,
        validator = this;
        if(!$(element).data('being_validated')) {
            $fields.data('being_validated', true).each(function(){
                validator.valid(this);
            }).data('being_validated', false);
        }
        return validOrNot;
    }, $.format("Please fill out at least {0} of these fields."));

        
    $.validator.addMethod("regex",function(value, element, regexp) {
        var check = false;
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "Only alphabets ,number and special characters(@#-_. are allowed)"  //No special Characters allowed here. Use only upper and lowercase letters (A through Z; a through z), numbers and punctuation marks (. , : ; ? ' ' \" - = ~ ! @ # $ % ^ & * ( ) _ + / < > { } )
    );

}

function updateUserTable()
{
    
    var usercount=$("#usertable tr").length-1;
    if(usercount>0)
    {
        for(var i=0;i<usercount;++i)
        {
        //getFancyBox('useredit'+(i+1));
        }
    }
}

function addWaterMark(id)
{
    if($("#"+id).val()=="" || $("#"+id).val()=="Type first letter/name")
    {
        $("#"+id).val('Type first letter/name');
        $("#"+id).css("color", "gray");
        $("#"+id).css("font-style", "italic");
    }
}

function removeWaterMark(id)
{
    
    if($("#"+id).val()=="Type first letter/name")
    {
        $("#"+id).val('');
        $("#"+id).css("color", "black");
        $("#"+id).css("font-style", "normal");
    }
    $("#userlevel").val("0");
}

function validateform()
{
    var status=false;
    var username=$("#username").val();
    var userlevel=$("#userlevel").val();
    var numericReg = /^[A-Za-z\d@#_.-]+$/;
    var check= new RegExp(numericReg).test(username);
    console.log(check);
    if(check==false && userlevel=="0")
        $("#invalid-username").html('Only alphabets are allowed');
    else if( (username=="" || username=="Type first letter/name") && userlevel=="0")
        $("#invalid-username").html('Please enter atleast one element');
    else
        status=true;
    return status;
}

function checkKeyPress(event){
    
    var keycode=returnkeycode(event);
    if( !(keycode==8 ||keycode==13|| (keycode>=65 && keycode<=90) || (keycode>=97 && keycode<=122)) )
        event.preventDefault();
}
