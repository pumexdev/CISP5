/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function ()
{
    searchHRData();
    $('#hrlist').dataTable();
    var url="exporthelp.htm";
    var imghtml="<img id='imghelp' src='images/help_balloon.png'/>";
    $("#help").html(imghtml);
    $("#imghelp").bind("click",function()
    {
        windopopup(url);
    });
});

function  searchHRData()
{
    $("#SearchHRDataEntry").validate(
    {
        submitHandler: function(form)
        {
            $(form).ajaxSubmit({
                url:'hrdatareport.htm',
                beforeSend:function()
                {
                    $("#throbber").show();
                },
                success: function(data)
                {
                    $("#global_area_main_admin").html(data);
                    $("#throbber").hide();
                }
            });
        },
        rules:
        {
            entry_date:
            {
                required: true
            },
            business_center_id:
            {
                required: true
//                min:"1"
            }
        },
        messages:
        {
            entry_date:
            {
                required: "Please select a Date"
            },
            business_center_id:
            {
                required: "Please select a Business Center"
//                min:"Please select a Business Center"
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
}
