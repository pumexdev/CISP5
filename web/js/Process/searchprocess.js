/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function()
{
    $("#processlist").dataTable();
    $('#scroll').jScrollPane({autoReinitialise:true});
});

function checkProcessName(processName,id,name,async)
{
    var asyncvalue=false;
    if(async=="1")
        asyncvalue=true;
    if(processName!="" && processName!=$("#processname").val())
    {
        $.ajax(
        {
            async:asyncvalue,
            cache:false,
            datatype:'json',
            type:'POST',
            url:'checkprocess.htm?name='+encodeURIComponent(processName),
            success:function(json)
            {
                if(json.check=='true')
                {
                    $("#"+id).html('');
                }
                else if(json.check=='false')
                {
                    $('label.error').empty();
                    $("#"+id).html("<label class='validationerror' for='"+id+"' generated='true'>Process already exists</label>");
                    $('#'+name).val($("#processname").val());
                }
                return json.check;
            }
        }
        );
    }
}


