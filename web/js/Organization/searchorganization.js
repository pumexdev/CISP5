/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function()
{
    editorganizationvalidate();
    $("#scrollbar1").tinyscrollbar();
});
function updateForms(value)
{
    $("#message").hide();
    if(value>0)
    {
        $("#orgid1").val(value);        
        $("#EditOrg").show();
        getOrganizationDetails(value);
    }
    else if(value==0)
    {
        $("#orgid1").val(value);
        $("#EditOrg").hide();
    }
}

function getOrganizationDetails(value)
{
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getorgdetails.htm?oid='+value,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.EditOrganization;
            for(var i=0;i<data.length;++i)
            {
                $("#name").val(data[i].name);
                $("#shortName").val(data[i].shortName);
                $("#address").val(data[i].address);
                $("#contactPerson").val(data[i].contactPerson);
                $("#phno1").val(data[i].phno1);
                $("#phno2").val(data[i].phno2);
                $("#emailid").val(data[i].emailid);
                $("#website").val(data[i].website);
                $("#status").val(data[i].status);
            }
        }
    });
}