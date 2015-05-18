$(document).ready(function(){
    $('#status1').val("1");
    $('#location option:selected').addClass("multioptionselect");  
})

//$('#location').change(function() {        
//    
//    $('#location > option').each(function(){   
//     
//      var test=$(this).attr('selected');
//     if(test == undefined)
//         $(this).attr('class','multioptiondeselect');
//     else
//         $(this).attr('class','multioptionselect');           
//    });    
//  
//}).change();

function addbusinessuintvalidate()
{
    $("#BusinessUnit").validate(
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
                regex: /^[A-Za-z\s\d@#_.-]+$/   //Allows alphabets,digits,spaces,@#_.-
            },
            shortName:
            {
                required: true,
                minlength:2,
                regex: /^[A-Za-z\s\d@#_.-]+$/   //Allows alphabets,digits,spaces,@#_.-
            },
            locations:
            {
                required: true
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
            locations:
            {
                required:"Please select atleast 1 Business Center"
            },
            status:
            {
                min:"Please select a Status"
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