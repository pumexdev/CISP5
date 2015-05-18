var urlArray = ["logout.htm","submenu.htm"];//"globaldashboard.htm","locationdashboard.htm","processdashboard.htm","subprocessdashboard.htm","connecteddashboard.htm",

/**
 * Restrict the textarea with the maxLength
 */
function checkMaxLength(textareaID, maxLength)
{
    var currentLengthInTextarea = $("#"+textareaID).val().length;
    if (currentLengthInTextarea > (maxLength))
    {
        $("#"+textareaID).val($("#"+textareaID).val().slice(0, maxLength));
    }
}

/**
 * Return the keycode of the current key pressed
 */
function returnkeycode(event)
{
    var keycode = (event.keyCode ? event.keyCode : event.which);
    return keycode;
}

/**
 * Shows the throbber image
 */
function throbberhide()
{
    $("#throbber").hide();
    $("#bg").hide();
}

/**
 * Hides the throbber image
 */
function throbbershow()
{
    $("#throbber").show();
    $("#bg").show();
}

/**
 * Extract the url parameters
 */
function extractParams(param)
{
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value)
    {
        vars[key] = value;
    });
    return vars[param];
}

/**
 * Fancy box common funtion Just pass id and onclose function as params
 */
function getFancyBox(id,func)
{
    $("#"+id).fancybox({
        'width'		: '70%',
        'height'        : '65%',
        'autoScale'	: true,
        'transitionIn'	: 'none',
        'transitionOut'	: 'none',
        'type'		: 'iframe',
        'onClosed'      : function()
        {
            func;
        }
    });
}

function getFancyBox(id)
{
    $("#"+id).fancybox({
        'width'		: '70%',
        'height'        : '65%',
        'autoScale'	: true,
        'transitionIn'	: 'none',
        'transitionOut'	: 'none',
        'type'		: 'iframe',
        'onClosed'      : function()
        {
            
        }
    });
}

function cisdatetime(id)
{
    $(function()
    {
        $( "#"+id ).datepicker({
            showOn: "button",
            buttonImage: "images/calendar.gif",
            buttonImageOnly: true,
            changeMonth: true,
            changeYear: true,
            dateFormat:"yy-mm-dd"
        });
    });
}
function cisdate(id)
{
    $( "#"+id ).datepicker({
        showOn: "button",
        buttonImage: "images/calendar.gif",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        dateFormat:"yy-mm-dd"
    });
}
function cisfuturedate(id)
{
    $(function()
    {
        $( "#"+id ).datepicker({
            showOn: "button",
            buttonImage: "images/calendar.gif",
            buttonImageOnly: true,
            changeMonth: true,
            changeYear: true,
            dateFormat:"yy-mm-dd",
            maxDate: new Date
        });
    });
}

function cisdatetime(id,date)
{
    $(function()
    {
        $( "#"+id ).datepicker({
            showOn: "button",
            buttonImage: "images/calendar.gif",
            buttonImageOnly: true,
            changeMonth: true,
            changeYear: true,
            dateFormat:"yy-mm-dd",
            maxDate: new Date,
            minDate:date
        });
    });
}

function onChange(parentid,value,childid)
{
    if(value!="" || value!="0")
        $("#"+childid).val($("#"+parentid).val());
}

function cancel()
{
    var conf=confirm("Are your sure to cancel and go back to Home");
    if(conf)
    {
        $("td.error").remove();
        $('#global_area_main_admin').load('home.htm');
    }
}

function cancel(url)
{
    var conf=confirm("Are your sure to cancel and go back?");
    if(conf)
    {
        $("td.error").remove();
        if(url==undefined)
            $('#global_area_main_admin').html('');
        else
        {
            $('#global_area_main_admin').load(url, function(response, status, xhr)
            {
                if (status == "error")
                {
                    $('body').load('404.htm');
                }
            });
        }
    }
}

function tooltip(id)
{
    $('#'+id).tooltip(
    { 
        delay: 0, 
        showURL: false, 
        bodyHandler: function()
        { 
            return $("<img width='500px' height='500px'/>").attr("src", this.src); 
        }
    });
}

function textonBlur(id,value)
{
    if(value=="")
    {
        var defValue=$("#"+id)[0].defaultValue;
        $("#"+id).val(defValue);
    }
    return $("#"+id).val(defValue);
}

function windopopup(url)
{
    window.open(url,'popUpWindow','height=700,width=800,left=10,top=10,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes');
}

function returnOptionText(id,value)
{
    var optionText="";
    $("#"+id+" > option").each(function()
    {
        var selected=$(this).attr("selected");
        if(selected=="selected")
        {
            optionText=this.text;
        }    
    });
    return optionText;
}

/*Disable the target href*/
function disableAnchor()
{
    $("a").bind('contextmenu',function(event)
    {
        return false;
    });
    
    $("a").click(function(event)
    {
        if(event.which==2)
        {
            return false;
        }
        $("a").each(function()
        {
            $(this).attr("class","");
        });
        var url = $(this).attr('href');
        $(this).attr("class","active");
        if(url=="" || url=="#")
        {
            $('#global_area_main_admin').html('');
            return false;
        }
        else if(url!="" && url!="#")
        {
            var index = checkArrayIndex(urlArray, url);
            if(index==-1)
            {
                load(url);
                $("#tabdiv").remove();
                return false;
            }
            else
            {
                if(url=='logout.htm')
                {
                    return true;
                }
                else if(url=="submenu.htm")
                {
                    $('#global_area_main_admin').html('');
                    return false;
                }
                else if(url=='login.htm')
                {
                    return true;
                }
                else
                {
                    $('body').load(url);
                    return false;
                }
            }
        }
    });
}
/*Disable the target href*/

function ajaxformSubmit(form)
{
    
    var session;
    $.ajax({
        async: false,
        type: 'POST',
        url: 'checksession.htm',
        beforeSend: function()
        {
        },
        success: function(json)
        {
            if (json.check == "true")
            {
                session = "true"
            }
            else
            {
                session = "false";
            }
        }
    });
    
    if(session == "false")
    {
        window.location.reload();
    }
    
    var action = $(form).attr('action');
    var actionList = action.split("/");
    action = actionList[2];
    var formid = $(form).attr('id');
    $(form).ajaxSubmit(
    {
        beforeSend:function()
        {
            $("#throbber").show();
        },
        success: function(data)
        {
            $("#global_area_main_admin").html(data);
            $("#throbber").hide();
        },
        error: function (request, status, error)
        {
            $("#throbber").hide();
            $("#message").html('Updation failed');
        }
    });
}

function load(url)
{
   var session;
    $.ajax({
        async:false,
        type:'POST',
        url:'checksession.htm',
        beforeSend:function()
        {
        },
        success: function(json)
        {
            if(json.check=="true")
            {
                session="true"               
            }
            else
            {
                session="false";
            }           
        }
    });
    
    $("#global_area_main_admin").load(url,
    function(response, status, xhr)
    {
        if(status=="error")
        {
            $('body').load('404.htm');
        }    
    });
    
    if(session=="false")
    {
        window.location.reload();        
    }
    
}

function checkArrayIndex(data,element)
{
    var status=false;
    var targetIndex=-1;
    for(var i=0;i<data.length;++i)
    {
        if(data[i]==element)
        {
            status=true;
            targetIndex=i;
            break;
        }
    }
    return targetIndex;
}

function checkSession()
{
    var session;
    $.ajax(
    {
        async:false,
        type:'POST',
        url:'checksession.htm',
        beforeSend:function()
        {
        },
        success: function(json)
        {
            if(json.check=="true")
            {
                session="true";               
            }
            else
            {
                session="false";
            }           
        }
    });
    if(session=="false")
    {
        window.location.reload();
    }
    setTimeout(checkSession,1801000);
}

function getArray(selectid,date1)
{
    var array = new Array();
    array[0]=date1;
    var index = 1;
    $("#"+selectid+" > option").each(function()
    {
       var selected = $(this).prop("selected");
       var value = $(this).val();
       if(selected==true && value!="")
       {
           array[index] = value;
           index++;
       }       
    });
}

