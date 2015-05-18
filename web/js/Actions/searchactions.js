/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function()
{
    $("#actionstable").dataTable();
    $('#scrollbar1').tinyscrollbar();
    $(".dataTables_length select").change(function()
    {
        $('#scrollbar1').tinyscrollbar();
        $("#scrollbar1").tinyscrollbar_update();
    });

});
function getSubprocess() {
    $.ajax({
        cache: false,
        url: 'getsubprocessfromprocess.htm?processId=' + encodeURIComponent($('#process').val()),
        type: 'POST',
        datatype: 'json',
        async: false,
        success: function(json)
        {
            var data = json.subprocesslist;
            var html = "<option value='0'>Select</option>";
            for (i = 0; i < data.length; ++i)
            {
                html += "<option value='" + data[i].SubProcessId + "'>" + data[i].SubProcessName + "</option>";
            }
            $("#subprocess").html(html);
        }
    });
}

function callForm(value)
{

    $.ajax({
        type: 'POST',
        url: 'searchactions.htm',
        data: $("#SearchActions").serialize(), // our form data da give and id to the form we use here
        beforeSend: function()
        {
            $("#throbber").show();
        },
        success: function(response)
        {
            $('#global_area_main_admin').html(response);
            $("#throbber").hide();
            getSubprocess();
            $("#subprocess").val(value);
        }
    });
}
