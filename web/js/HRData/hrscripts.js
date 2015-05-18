/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function loadClients(locationid,id)
{
    $.ajax(
    {
        cache:false,
        type:'POST',
        url:'loadclients.htm?locationid='+encodeURIComponent(locationid),
        datatype:'json',
        success:function(json)
        {
            var html="<option value=''>Select</option>";
            var data=json.Clientlist;
            $.each(data,function(i,val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#"+id).html(html);
        }
    });
}

function getClients(locationid)
{
    $("select[name^='businessunits']").each(function()
    {
        loadClients(locationid, $(this).attr("id"));
    });
}

