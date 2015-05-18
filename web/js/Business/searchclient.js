/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function()
{
    updateSearchClientForms();
    $("#clientlist").dataTable();
    /*$('#scrollbar1').tinyscrollbar();*/
    $(".dataTables_length select").change(function()
    {
        /*$('#scrollbar1').tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update();*/
        $("#scrollbar1").jScrollPane();
    });
    $("#scrollbar1").jScrollPane();

});
function callForm()
{
    
    $.ajax({
        type:'POST',
        url:'searchclient.htm',           
        data: $("#SearchClient").serialize(), // our form data da give and id to the form we use here
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
function updateSearchClientForms()
{
    $("#SearchClient").validate(
    {
        submitHandler: function(form)
        {
            ajaxformSubmit(form);
        },
        rules:
        {
            location:
            {
                required: true                
            }
        },
        message:
        {           
            location:
            {
                required:"Please select a Business Center"
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
}