/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function()
{
    var url="hrdatahelp.htm";
    var imghtml="<img id='imghelp' src='images/help_balloon.png'/>";
    $("#help").html(imghtml);
    $("#imghelp").bind("click",function()
    {
        windopopup(url);
    });
    ExcelUploader();
});

function ExcelUploader()
{
    $("#Multipart").validate(
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
                    $("#upid").attr('value','New Upload');
                    $("#throbber").hide();
                    
                }
            });
        //ajaxformSubmit(form);
        },
        rules:
        {
            file:
            {
                required: true, 
                accept: "xls,xlsx", 
                filesize:1048576
            }
        },
        messages:
        {
            file:
            {
                accept:'Only XLSX,XLS files are allowed',
                filesize:'Maximum file size allowed 1 MB',
                required: "Please select a file"
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
    
$.validator.addMethod('filesize', function(value, element, param)
{
    return this.optional(element) || (element.files[0].size <= param)
});
}

function getHelp(){
    $("#helpdiv").show();
}

function process(){
    $.ajax({
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

function processKPI(){
    $.ajax({
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