

$(document).ready(function ()
{
    userformvalidate();
    $('#client option:selected').addClass("multioptionselect");   
});

//$('#client').change(function() {        
//    
//    $('#client > option').each(function(){   
//     
//      var test=$(this).attr('selected');
//     if(test == undefined)
//         $(this).attr('class','multioptiondeselect');
//     else
//         $(this).attr('class','multioptionselect');           
//    });    
//  
//}).change();

function messagedelete()
{
     $("#message").hide();
}
function userformvalidate()
{
    $("#Location").validate(
    {
        submitHandler: function(form)
        {
            ajaxformSubmit(form);
        },
        rules:
        {
            name:
            {
                required: true,
                minlength:2,
                regex: /^[A-Za-z\s\d]+$/   //Allows alphabets,digits,spaces,@#_.-
            },
            shortName:
            {
                required: true,
                minlength:2,
                regex: /^[A-Za-z\s\d]+$/   //Allows alphabets,digits,spaces,@#_.-
            },
            country:
            {
                min:"1"
            },
            city:
            {
                required: true,
                minlength:2,
                regex: /^[A-Za-z\s\d]+$/   //Allows alphabets,digits,spaces,@#_.-
            },
            businesses:
            {
                required:true
            }
        },
        messages:
        {
            name:
            {
                required: "Please enter a name",
                minlength:"Name should contain atleast 2 characters",                
                regex:"(@,#,^,%,$,|,{,},',/,?,=,+,!,*,()|,\ are not allowed)"
            },
            shortName:
            {
                required: "Please enter a short name",
                minlength:"Short Name should contain atleast 2 characters",
                regex:"(@,#,^,%,$,|,{,},',/,?,=,+,!,*,()|,\ are not allowed)"
            },
            country:
            {
                min:"Please select a country"
            },
            city:
            {
                required: "Please enter a city",
                minlength:"City should contain atleast 2 characters",
                regex:"(@,#,^,%,$,|,{,},',/,?,=,+,!,*,()|,\ are not allowed)"
            },
            businesses:
            {
                required:"Please select atleast a business"
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

function checkLocation(locationname,async)
{
    var asyncvalue=false;
    if(async=="1")
        asyncvalue=true;
    if(locationname!="")
    {
        $.ajax(
            {
                async:asyncvalue,
                cache:false,
                datatype:'json',
                type:'POST',
                url:'checklocation.htm?name='+encodeURIComponent(locationname),
                success:function(json)
                {
                    if(json.check=='true')
                    {
                        $("#invalid-name").html('');
                    }
                    else if(json.check=='false')
                    {
                        $('label.error').empty();
                        $("#invalid-name").html("<label class='validationerror' for='invalid-name' generated='true'>Location already exists</label>");
                        $('#name').val($("#locationname").val());
                    }
                    return json.check;
                }
            }
            );
    }
}