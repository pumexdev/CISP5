/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    getSubMenuHome(1, this.event);
});

function setTitle(title)
{
    $('#heading').html(title);

    $(document).attr("title", title);
    if (title.match('Administration'))
    {
        $('#headingimage').attr({
            src: "images/administrator-icon.png"
        });
        $('#menuHead').html('');
    }
    else if (title.match('Dashboard'))
    {
        $('#headingimage').attr({
            src: "images/dashboard-icon-top.png"
        });
        $('#menuHead').html('');
    }
    else if (title.match('Statement'))
    {
        $('#headingimage').attr({
            src: "images/statement-icon.png"
        });
        $('#menuHead').html('');//<b>Management Statements  |  </b><i>Manage Data:</i>
    }
    else if (title.match('Target'))
    {
        $('#headingimage').attr({
            src: "images/imgo.png"
        });
        $('#menuHead').html('');
    }
}

function getSubMenu(menuID, event)
{
    var session;
    $.ajax({
        cache: false,
        async: false,
        type: 'POST',
        url: 'checksession.htm',
        beforeSend: function ()
        {
        },
        success: function (json)
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
    if (session == "false")
    {
        window.location.reload();
    }

    setTitle($('#menu' + menuID).text());
    //     $('#menu'+menuID).attr("style","color: #FFFFFF;background:url('images/administrator-icon-over.png') no-repeat center 22px");


    $("#tabdiv").remove();
    $(".d_menu_nav").show();
    var homePage = '';
    event.preventDefault();
    $.ajax({
        async: false,
        url: 'getsubmenu.htm',
        data: {
            menuid: menuID
        },
        cache: false,
        datatype: 'json',
        type: 'POST',
        success: function (json)
        {
            var data = json.subMenuList;
            homePage = json.homePage;
            var html = '';
            for (var i = 0; i < data.length; ++i)
            {
                if(data[i].Description=="Manage Statements"){
                     html += "<li><a style='cursor: pointer; float: left;' href='" + data[i].MenuUrl + "'>"+ data[i].Description +"</a><span style='float: left; font-weight: normal; padding: 0px; color: rgb(32, 32, 32); margin: 12px 0px 0px;'> | <i style='margin-left:10'>Manage Data:</i></span></li> ";
                }else{
                     html += "<li><a href='" + data[i].MenuUrl + "'>" + data[i].Description + "</a></li> ";
                }
                //html += "<li><a href='" + data[i].MenuUrl + "'>" + data[i].Description + "</a></li> "
            }
            $(".d_menu_nav").html(html);
        }
    });
    disableAnchor();
    $("#global_area_main_admin").html('');

    return false;
}

function getSubMenuHome(menuID, event) {
    $.ajax({
        url: 'getsubmenuhome.htm',
        data: {
            menuid: menuID
        },
        beforeSend: function ()
        {
            $("#throbber").show();
        },
        success: function (data)
        {
            $("#global_area_main_admin").html(data);

        }

    });
    $("#throbber").hide();
}

function editactiontypeValidate() {
    $("#EditActionType").validate(
            {
                submitHandler: function (form)
                {
                    ajaxformSubmit(form);

                },
                rules:
                        {
                            actionType:
                                    {
                                        required: true
                                    },
                            status:
                                    {
                                        required: true
                                    }
                        },
                messages:
                        {
                            actionType:
                                    {
                                        required: "Please enter an Action Type"
                                    },
                            status:
                                    {
                                        required: "Please select a Status"
                                    }
                        },
                errorElement: "label",
                errorPlacement: function (error, element)
                {
                    $("#invalid-" + element.attr('id')).html('');
                    error.appendTo('#invalid-' + element.attr('id'));
                    error.attr('class', 'validationerror');
                }});
}

function checkAction() {
    if ($('#status').val() === '2') {
        $.ajax({
            cache: false,
            url: 'checkactiontypeactions.htm?actiontypeid=' + encodeURIComponent($('#actionTypeId').val()),
            type: 'POST',
            datatype: 'json',
            async: false,
            success: function (json)
            {

                if (json.check == "false")
                {
                    alert('Action item exists for this action type. Please delete action first.');
                    return false;
                }
                else {
                    return true;
                }

            }
        });
    }
    else {
        return true;
    }
}

