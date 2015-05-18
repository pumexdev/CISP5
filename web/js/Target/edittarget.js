/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
    edittargetvalidate();
});

function edittargetvalidate(){
    $("#EditTarget").validate(
    {
        submitHandler: function(form)
        {
            ajaxformSubmit(form);
        },
        rules:
        {
            target:
            {
                required: true,
                number: true
            },
            high:
            {
                required: true,
                number: true
            },
            medium:
            {
                required: true,
                number: true
            },
            low:
            {
                required: true,
                number: true
            }
        },
        messages:
        {
            target:
            {
                required: "Please enter a Target",
                number:"Please enter digit only"
            },
            high:
            {
                required:"Please Enter a Benchmark high",
                number:"Please enter digit only"
            },
            medium:
            {
                required:"Please Enter a Benchmark medium",
                number:"Please enter digit only"
            },
            low:
            {
                required:"Please Enter a Benchmark low",
                number:"Please enter digit only"
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
