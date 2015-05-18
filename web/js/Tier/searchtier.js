/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function()
{
    updateForms($("#tier").val());
    tierformvalidate();  
});

function messageDelete()
{
    
    $("#message").hide();
}
function updateForms(value)
{
     $("#message").hide();    
    if(value=="0")
    {
        $("#tier").val(0);
        $("#Tier1").show();
        $("#EditTier1").hide();
    }
    else
    {
        $("#tier").val(value);
        //$("#EditBusiness").attr("action","editbusiness.htm?bid="+value);
        $("#EditTier1").show();
        $("#Tier1").hide();
        getTierDetails(value);
    }
   
}

function getTierDetails(value)
{
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'gettierdetails.htm?tierdid='+value,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.EditTier;
            for(var i=0;i<data.length;++i)
            {
                $("#tier1").val(data[i].tier);
                $("#designation1").val(data[i].designation);                
            }
        }
    });
}



