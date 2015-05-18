/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function ()
{
    userformvalidate();
    var url="locationhelp.htm";
    var imghtml="<img id='imghelp' src='images/help_balloon.png'/>";
    $("#help").html(imghtml);
    $("#imghelp").bind("click",function()
    {
        windopopup(url);
    });
    $("#locationtable").dataTable();
    $('#scrollbar1').tinyscrollbar();
    $("#scrollbar1").tinyscrollbar_update();
    $(".dataTables_length select").change(function()
    {
        $('#scrollbar1').tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update();
    });
});


function messagedelete()
{
    $("#message").hide();
}
function userformvalidate()
{
    $("#SearchLocation").validate(
    {
        rules:
        {
            name:
            {
                required: true,
                regex: /^[A-Za-z\d\s@#_.-]+$/
            }
        },
        messages:
        {
            name:
            {
                required: "Please enter a Business Center",               
                regex:"Only alphabets ,number and special characters(@#-_. are allowed)"
            }
        },
        errorElement: "label",
        errorPlacement: function(error, element)
        {
            $("#invalid-"+element.attr('id')).html('');
            error.appendTo('#invalid-' + element.attr('id'));
            error.attr('class','validationerror');
        }
    });
    
$.validator.addMethod("regex",function(value, element, regexp) {
    var check = false;
    var re = new RegExp(regexp);
    return this.optional(element) || re.test(value);
},
"Only alphabets ,number and special characters(@#-_. are allowed)"  //No special Characters allowed here. Use only upper and lowercase letters (A through Z; a through z), numbers and punctuation marks (. , : ; ? ' ' \" - = ~ ! @ # $ % ^ & * ( ) _ + / < > { } )
);
}

