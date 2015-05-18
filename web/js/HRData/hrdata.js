/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getbusiness(value,combo)
{
    $.ajax(
        {
            cache:false,
            type:'POST',
            url:'getbusiness.htm?bid='+value,
            datatype:'json',
            success:function(json)
            {
                var data=json.rows;
                var html="<option value='0'>Select</option>";
                $.each(data,function(i,val)
                {
                    html+="<option value='"+i+"'>"+val+"</option>";
                });
                $("#"+combo).html(html);
            }
        }
    );         
}
