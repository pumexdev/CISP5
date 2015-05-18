/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function()
{
    activeButtons();
    $('#scrollbar1').tinyscrollbar();
});

function activeButtons()
{
    if($("#status").val()!="Failed")
    {
        $("#confirm").removeAttr("disabled");
    }
    else
    {
        $("#confirm").css("color","gray");
    }
}

function activeDataDiv(){
    $("#daterow").show();
}

function deactiveDataDiv(){
    $("#daterow").hide();
}

function dateOverWrite(){
    if(document.getElementById("dateCheck").checked==true){
        $.ajax({
            url:'overwrite.htm?d1='+$("#date").val()+'&bcid='+$("#business_center_id").val(),
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
        //window.location.href="overwrite.htm?d1="+$("#date").val()+"&bcid="+$("#business_center_id").val();
    }
}

function kpiOverWrite(){
    if(document.getElementById("dateCheck").checked==true){
        $.ajax({
            url:'overwritevolkpi.htm?d1='+$("#date").val()+'&bcid='+$("#business_center_id").val(),
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
//    if(document.getElementById("dateCheck").checked==true){
//        window.location.href="overwritevolkpi.htm?d1="+$("#date").val()+"&bcid="+$("#business_center_id").val();
   }
   
}

function cancelFunction(urlvalue){
    var conf=confirm("Are your sure to cancel and go back?");
    if(conf){
        $("td.error").remove();
        $.ajax({
            url:urlvalue,
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
        $("#global_area_main_admin").html();
    }
    
}

function saveHRData(form){
    $(form).ajaxSubmit({
        url:'processing.htm',
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

function saveVolumeData(form){
    $(form).ajaxSubmit({
        url:'volkpiprocessing.htm',
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