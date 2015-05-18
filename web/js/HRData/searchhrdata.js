/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function ()
{
    $('#hrlist').dataTable();
});

function  searchHRData()
{
    $("#SearchHRDataEntry").validate(
    {
        submitHandler: function(form){
            $(form).ajaxSubmit({
                beforeSend:function()
                {
                    $("#throbber").show();
                },
                success: function(data)
                {
                    $("#lastdiv").html(data);
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

function deleteEntry(entryId)
{
    var confirmation=confirm("Are you sure to delete?");
    if(confirmation)
        $("#lastdiv").load("deleteentry.htm?entryId="+encodeURIComponent(entryId));
}

function addNewHrData(){
    $.ajax({
        url:'addhrdata.htm',
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


function validateform(no,form)
{
    var businesscenter = $("#business_center_id"+no).val();
    var date = $("#entry_date"+no).val();
    var status = false;
    if(businesscenter=="")
    {
        var html = "<label for='business_center_id' generated='true' class='validationerror'>Please select Center</label>";
        $("#invalid-business_center_id"+no).html(html);
        status = false;
    }
    if(date==""|| date=="0")
    {
        var html = "<label for='entry_date' generated='true' class='validationerror'>Please select Date</label>";
        $("#invalid-entry_date"+no).html(html);
        status = false;
    }
    else if(businesscenter!="" && date!="0")
    {
        $("#invalid-business_center_id"+no).html('');
        $("#invalid-entry_date"+no).html('');
        status = true;
    }
    if(status==true)
    {
        /*$.ajax(
        {
            cache:false,
            type:'POST',
            url:'searchhrdataentry.htm',
            data:{entry_date:date,business_center_id:businesscenter,no:no},
            beforeSend:function(data)
            {
                $("#throbber").show();
            },
            success:function(data)
            {
                $("#resultdiv"+no).html(data);
                $("#hrlist"+no).dataTable();
                
                if($("#hrlist"+no+" >tbody >tr").length > 0)
                {
                    $("#tab"+no).jScrollPane();
                    $(".dataTables_length select").change(function()
                    {
                        $("#tab"+no).jScrollPane();
                    });
                }
                $("#throbber").hide();
            }
        });*/
        searchData(no);
    }
}

function searchData(no)
{
    var businesscenter1 = $("#business_center_id"+no).val();
    var date1 = $("#entry_date"+no).val();
    
    var session;
    $.ajax({
        async: false,
        type: 'GET',
        url: 'checksession.htm',
        beforeSend: function()
        {
        },
        success: function(json)
        {
            if (json.check == "true")
            {
                session = "true"
            }
            else
            {
                session = "false";
            }
        }
    });
    
    if(session == "false")
    {
        window.location.reload();
    }
    
    $.ajax(
    {
        cache:false,
        type:'POST',
        url:'searchhrdataentry.htm',
        data:{entry_date:date1,business_center_id:businesscenter1,no:no},
        beforeSend:function(data)
        {
            $("#throbber").show();
        },
        success:function(data)
        {
            $("#resultdiv"+no).html(data);
            $("#hrlist"+no).dataTable();
            
            if($("#hrlist"+no+" >tbody >tr").length > 0)
            {
                $("#tab"+no).jScrollPane();
                $(".dataTables_length select").change(function()
                {
                    $("#tab"+no).jScrollPane();
                });
            }
            $("#throbber").hide();
        },
        error: function(xhr, status, error)
        {
            console.log(xhr+status+error);
            var err = eval("(" + xhr.responseText + ")");
            alert(err.Message);
        }
    });
    $("#tabno1").val(no);
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

function uploadbutton()
{
    $("#tabdiv").remove();
    upload('exceluploader.htm');
}

function validateExcel(no){
    if($("#entry_date"+no).val()==""){
        alert("Please select Date");
        return false;
    }else if($("#business_center_id"+no).val()==0){
        alert("Please select Business Center");
        return false;
    }
    else{
        checkExport($('#entry_date'+no).val(),$('#business_center_id'+no).val());
    }
}

function validateExcelAll(no){
    if($("#entry_date"+no).val()==""){
        alert("Please select Date");
        return false;
    }
    else{
        checkExportAll($('#entry_date'+no).val());
    }
}

function checkExportAll(date){
    $.ajax({
        url: 'excelexportallajax.htm',
        data:{e_date:date},
        cache:false,
        type:'POST',
        success:function(json){
            if(json.check=='true'){
                window.location.href = 'excelexportall.htm?e_date='+date;
            }else{
                alert("No datas to export");
            }
        }
                
    });
}

function checkExport(date,bc){
    $.ajax({
        url: 'excelexportajax.htm',
        data:{e_date:date,bc_id:bc},
        cache:false,
        type:'POST',
        success:function(json){
            if(json.check=='true'){
                window.location.href = 'excelexport.htm?e_date='+date+'&bc_id='+bc;
            }else{
                alert("No datas to export");
            }
        }
                
    });
}