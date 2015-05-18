/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function()
{
    
    $("#targetlist").dataTable();
    $('#scrollbar1').tinyscrollbar();
    $("#scrollbar1").tinyscrollbar_update();
    $(".dataTables_length select").change(function()
    {
        $('#scrollbar1').tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update();
    });
});

function searchTarget(){
    $.ajax({
        type:'POST',
        url:'searchtarget.htm',           
        data: $("#SearchTarget").serialize(), // our form data da give and id to the form we use here
        beforeSend:function()
        {
            $("#throbber").show();
        },
        success: function(response)
        {
            $('#global_area_main_admin').html(response);
            $("#throbber").hide();
        }
    });
}