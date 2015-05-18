/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*Search Business Onload functions*/

$(document).ready(function()
{
    updateForms($("#status").val());
    addbusinessvalidate();
    editbusinessvalidate();
});

function updateForms(value)
{
    $("td.error").remove();
    if(value=="0")
    {
        $("#status").val(0);
        $("#Business1").show();
        $("#EditBusiness1").hide();
        $("#businessname").val('');
    }
    else
    {
        $("#message").hide();
        $("#businessid").val(value);
        $("#EditBusiness1").show();
        $("#Business1").hide();
        getBusinessDetails(value);
    }
}

function getBusinessDetails(value)
{
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getbusinessdetails.htm?bid='+value,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.EditBusiness;
            for(var i=0;i<data.length;++i)
            {
                $("#name1").val(data[i].name);
                $("#businessname").val(data[i].name);
                $("#shortName1").val(data[i].shortName);
                $("#organization1").val(data[i].organization);
                $("#status1").val(data[i].status);
            }
        }
    });
}
/*Search Business Onload functions*/

function checkBusiness(businessName,id,name,async)
{
    var asyncvalue=false;
    if(async=="1")
        asyncvalue=true;
    if(businessName!="" && businessName!=$("#businessname").val())
    {
        $.ajax(
            {
                async:asyncvalue,
                cache:false,
                datatype:'json',
                type:'POST',
                url:'checkbusiness.htm?name='+encodeURIComponent(businessName),
                success:function(json)
                {
                    if(json.check==true)
                    {
                        $("#"+id).html('');
                    }
                    else if(json.check==false)
                    {
                        $('label.error').empty();
                        $("#"+id).html('Business already exists');
                        $('#'+name).val($("#businessname").val());
                    }
                    return json.check;
                }
            }
            );
    }
}
