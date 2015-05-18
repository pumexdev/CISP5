/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function  searchHRData(id)
{
    $("#SearchHRDataEntry"+id).validate(
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

function validate()
{
    var count = $("#datelength").val();
    count = parseInt(count, 10);
    for(var i=0;i<count;++i)
    {
        searchHRData(i+3);
    }
}

$(document).ready(function()
{
    $("#lastdiv").load('searchhrdataentry.htm?check=true');
    
    var liArray = new Array();
    var divArray = new Array();
    var count = 0;
   
    $("#tabs li a").each(function()
    {
        var child = $(this).text().toString();
        child = $.trim(child);
        var title = $(this).attr("title");
        var tabno = title.substr(title.length-1, title.length);
        $(this).click(function()
        {
            $("#tabno").val(tabno);
        });
        if(child!="Statement" && child!="Search")
        {
            liArray.push(child);
            count++;
            divArray.push("tabdivs"+(count+1));
        }
    });
});

function setTabno()
{
    $("#tabs li a").each(function()
    {
        var title = $(this).attr("title");
        var tabno = title.substring(title.length-1, title.length);
        $(this).click(function()
        {
            $("#tabno").val(tabno);
        });
    });
}

