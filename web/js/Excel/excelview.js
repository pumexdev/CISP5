/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function ()
{
    searchHRData();
    $('#hrlist').dataTable();
});

function  searchHRData()
{
    $("#ExcelExportView").validate(
    {
        submitHandler: function(form)

        {
            $(form).ajaxSubmit({
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
        //ajaxformSubmit(form);
        },
        rules:
        {
            entry_date:
            {
                required: true
            },
            business_center_id:
            {
                required: true,
                min:"1"
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
                required: "Please select a Business Center",
                min:"Please select a Business Center"
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
$.validator.addMethod("valueNotEquals", function(value, element, arg){
    return arg != value;
}, "Value must not equal arg.");
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
        window.open('excelexport.htm?e_date='+$('#entry_date').val()+'&bc_id='+$('#business_center_id').val());
    }
}

function validateExcelAll(no){
    if($("#entry_date"+no).val()==""){
        alert("Please select Date");
        return false;
    }
    else{
        window.open('excelexportall.htm?e_date='+$('#entry_date').val());
    }
}