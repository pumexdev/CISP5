/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function()
{
    addbusinessuintvalidate();
    editbusinessunitvalidate();
//    var url=window.location.pathname;
//    var buid=extractParams("buid");      
    if($('#businessunit2').val())
    {
        $("#BusinessUnit1").hide();
        $("#EditBusinessUnit1").show();
    }
    else
    {
        $("#BusinessUnit1").show();
        $("#EditBusinessUnit1").hide();
    }
});


function updateForms(value1)
{
    $("td.error").remove();
    if(value1=="0")
    {
        $("#businessunit").val(0);
        $("#BusinessUnit1").show();
        $("#EditBusinessUnit1").hide();
        $("#business1").val(value1);
        $("#businessunitname").val('');
    }
    else
    {
        $("#message").hide();
        $("#businessid").val(value1);
        $("#EditBusinessUnit1").show();
        $("#BusinessUnit1").hide();
        getBusinessUnitDetails(value1);
        $("#business2").val(value1);
        $("#businessid1").val(value1);
    }
}

function getBusinessUnitDetails(value1)
{
    //$("#businessunit2").val(value1);businessunit
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getbusinessunitdetails.htm?buid='+value1,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.EditBusinessUnit;
            for(var i=0;i<data.length;++i)
            {
                $("#location2 > option").each(function()
                {
                    $("#location2 > option").attr("selected", false);
                });
                $("#name2").val(data[i].name);
                $("#businessunit2").val(data[i].businessunit);
                $("#shortName2").val(data[i].shortName);
                $("#business2").val(data[i].business);
                $("#status2").val(data[i].status);
                $("#businessunitname").val(data[i].name);
                var locations=data[i].locations;
                for(var j=0;j<locations.length;++j)
                {
                    $("#location2 > option").each(function()
                    {
                        if(this.value==locations[j])
                            $(this).attr("selected", true);
                    });
                }
            }
        }
    });    
}

function updateLocation(value)
{
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getlocationlist.htm?bid='+value,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.BusinessUnits;
            var html="<option value='0'>Select</option>";
            $.each(data,function(i,val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#location").html(html);
        }
    });
}

function updateBusinesUnit(value)
{
    $("#message").hide();
    $.ajax(
    {
        cache:false,
        datatype:'json',
        type:'POST',
        url:'getbusinessunits.htm?bid='+value,
        beforeSend:function()
        {
            
        },
        success:function(json)
        {
            var data=json.BusinessUnits;
            var html="<option value='0'>Create New</option>";
            $.each(data,function(i,val)
            {
                html+="<option value='"+i+"'>"+val+"</option>";
            });
            $("#businessunit").html(html);
        }
    });
    $("#BusinessUnit1").show();
    $("#EditBusinessUnit1").hide();
    $("#businessid2").val(value);
    $("#businessid1").val(value);
    $("#businessunitname").val('');
}

function checkBusinessUnit(businessUnitName,id,name,async)
{
    var asyncvalue=false;
    if(async=="1")
        asyncvalue=true;
    if(businessUnitName!="" && businessUnitName!=$("#businessunitname").val())
    {
        $.ajax(
            {
                async:asyncvalue,
                cache:false,
                datatype:'json',
                type:'POST',
                url:'checkbusinessunit.htm?name='+encodeURIComponent(businessUnitName),
                success:function(json)
                {
                    if(json.check=='true')
                    {
                        $("#"+id).html('');
                    }
                    else if(json.check=='false')
                    {
//                        <label class="validationerror" for="name1" generated="true">Please enter a name</label>
//                        $('label.error').empty();
//                         $("#"+id).css('color','red');
//                        $("#"+id).attr('position','absolute');
                        $("#"+id).html("<label class='validationerror' for='"+id+"' generated='true'>Client already exists</label>");
                        $('#'+name).val($("#businessunitname").val());
                    }
                    return json.check;
                }
            }
            );
    }
}

function iterateSelect()
{
    $("#location2 > option").each(function() 
    {
        console.log(this.text + ' ...  ' + this.value);
        $("#location2 > option").attr("selected", true);
    });
}