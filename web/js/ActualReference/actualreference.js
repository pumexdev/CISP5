/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function paramChange(value)
{
    value=parseInt(value, 10);
    switch(value)
    {
        case 1:
            $("#revenue").show();
            $("#volume").hide();
            break;
        case 2:
            $("#volume").show();
            $("#revenue").hide();
            break;
        default:
            $("#revenue").show();
            $("#volume").hide();
            break;
    }
    getandsetdimensions();
}


function resetActualForm()
{
    $("#message").html('');
    $("#businesscenter").focus();
    $("#message").hide();
    $("#businesscenter").val('');
    $("#fromdate").val('');
    $("#paramtypecode").val('');
    $("#status").val('');
    $("#operation").val(1);
    $("#fieldsetrevenue").hide();
    
    /**/
    $("#fieldsetrevenue").empty();
    $("#fieldsetvolume").empty();
    /**/
    $("#save").show();
    //$("#save").attr("class","searchbutton");
    $("input").removeAttr("disabled");
    $("select").removeAttr("disabled");
    footerposition();
    /*
     *$("#businesscenter").attr("disabled","false");
    $("#fromdate").attr("disabled","false");
    $("#paramtypecode").attr("disabled","false");
    $("#status").attr("disabled","false");*/
}

function formSubmit()
{
    $("input").each(function(){
        $(this).removeAttr("disabled");
    });
    $("select").each(function(){
        $(this).removeAttr("disabled");
    });
}

function upload(url){
    $.ajax({
        url:url,
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
}