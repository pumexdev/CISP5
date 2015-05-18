/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getActionDetailes() {
    var processid = $("#subprocess1").val();
    $.ajax({
        type: 'POST',
        datatype: 'json',
        cache: false,
        async: false,
        url: 'getactiondetailsprocess.htm?pid=' + processid,
        success: function(json)
        {
            var data = json.actionlist;
            var html = '';
            $('#tbody1').html("<tr class='box_table_height'><td colspan='3' style='text-align : center;'>No Results Found</td></tr>");
            for (var i = 0; i < data.length; ++i)
            {
                html += "<tr class='box_table_height'>";
                html += "<td width='50%' align='center'><span>" + data[i].ActionName + "</span></td>";
                html += "<td width='20%' align='center'>" + data[i].ActionType + "</td>";
                html += "<td width='20%' align='center'>" + data[i].Percentage + "%</td>";
                html += "<td width='10%' align='center'><img width='14' height='14' alt='edit' id='edit" + i + "' src='images/edit.png' onclick='editactions(" + i + "," + data[i].ActionsId + ", \"" + data[i].ActionName + "\", \"" + data[i].ActionType + "\",\"" + data[i].Percentage + "\");'>&nbsp;&nbsp;<img width='14' height='14' id='delete" + i + "'alt='' src='images/cross.png' onclick='deleteaction(" + i + "," + data[i].ActionsId + ");'></td></tr>";
                $('#tbody1').html(html);
            }
        }
    });

    var dropdown;
    $.ajax({
        cache: false,
        url: 'getactiontypemap.htm',
        type: 'POST',
        datatype: 'json',
        async: false,
        success: function(json)
        {
            var data = json.actiontypelist;
            dropdown = "<select id='newactiontype' onchange='removebordercolor(this.id); dropdownselected(this.id); checkActionNameAdd();'>";
            dropdown += "<option value='-1'>Select Action type</option>";
            dropdown += "<option value='0'>Add New Type</option>";
            for (var i = 0; i < data.length; ++i)
            {
                dropdown += "<option value='" + data[i].ActionTypeId + "'>" + data[i].ActionType + "</option>";
            }
            dropdown += "</select>"
        }
    });

    var html = '';
    html += "<tr class='box_table_height'>";
    html += "<td width='50%' align='center'><input id='newaction' onkeypress='removebordercolor(this.id);' type='text' placeholder='Enter Action' autocomplete='off' onchange='checkActionNameAdd()' style='min-width : 10px; width : 375px' maxlength='100' /></td>";
    html += "<td width='20%' align='center'>" + dropdown + "</td>";
    html += "<td width='20%' align='center'><input id='newactionprecentage' onkeypress='removebordercolor(this.id);' maxlength='3' type='text' placeholder='%' style='min-width : 10px; width : 50px' /></td>";
    html += "<td width='10%' align='center'><input type='button' value='Save' onclick='saveAction();' style='height: 25px; line-height: 10px; margin-top: 0px;'></td>";
    $('#tbody1').append(html);
}

function checkActionNameAdd()
{
    $.ajax({
        cache: false,
        datatype: 'json',
        type: 'POST',
        url: 'checkactions.htm?action=' + $('#newaction').val() + '&actiontype=' + $('#newactiontype').val(),
        beforeSend: function()
        {
            $('#invalid-action').html('');
        },
        success: function(json)
        {
            if (json.check === 'false') {
                alert('Action already exists')
                $('#newaction').val('');
                $('#newaction').attr('style', 'min-width : 10px; width : 375px;border-color : red');
                $('#newaction').focus();
            }
        }
    });
}



function  editactions(id, actionId, name, actiontype, percentage) {

    $.ajax({
        cache: false,
        url: 'getactiontypemap.htm',
        type: 'POST',
        datatype: 'json',
        async: false,
        success: function(json)
        {
            var data = json.actiontypelist;
            var dropdown = "<select id='actiontype" + id + "' onchange='removebordercolor(this.id); dropdownselected(this.id);'>";
            dropdown += "<option value='-1'>Select</option>";
            dropdown += "<option value='0'>Add New Type</option>";
            for (var i = 0; i < data.length; ++i)
            {
                if (data[i].ActionType === actiontype)
                    dropdown += "<option selected='selected' value='" + data[i].ActionTypeId + "'>" + data[i].ActionType + "</option>";
                else
                    dropdown += "<option value='" + data[i].ActionTypeId + "'>" + data[i].ActionType + "</option>";
            }
            dropdown += "</select>"
            var html = "<td align='center' width='50%'><input type='text'  maxlength = '100' onkeypress='removebordercolor(this.id);' id='action" + id + "' style='min-width : 10px; width : 375px' value='" + name + "' /></td>";
            html += "<td align='center' width='20%'>" + dropdown + "</td>";
            html += "<td align='center' width='20%'><input maxlength = '3' id='actionpercentage" + id + "' onkeypress='removebordercolor(this.id);' type='text' style='min-width : 10px; width : 50px' value='" + percentage + "' /></td>";
            html += "<td align='center' width='10%'><input type='button' value='Update' id='updateactionbtn" + id + "' style='height: 25px; line-height: 10px; margin-top: 0px;' onclick='updateactions(" + id + "," + actionId + ");'></td>";
            $("#actiontable1 tbody tr:nth-child(" + parseInt(id + 1) + " )").html(html);

        }
    });
}


function removebordercolor(id) {
    $('#' + id).css("border-color", "");
}

function deleteaction(id, actionId) {
    var result = confirm("Do you want to delete this action?");
    if (result) {
        $.ajax({
            cache: false,
            url: 'deleteactionajax.htm?actionId=' + encodeURIComponent(actionId),
            type: 'POST',
            datatype: 'json',
            async: false,
            success: function(json)
            {

                if (json.check == "true")
                {
                    getActionDetailes();
                }
                else {
                    alert('Error while deleting Actions.');
                }

            }
        });
    }
}
function saveAction() {

    var actionName = $('#newaction').val();
    var actionType = $('#newactiontype').val();
    var actionPercentage = $('#newactionprecentage').val();
    var actionTypeText = $('#textnewactiontype').val();
    if (typeof actionType === "undefined")
    {
        actionType = 0;
    }
    if (checkAddValidation(actionName, actionType, actionPercentage, actionTypeText)) {
        $.ajax({
            cache: false,
            url: 'saveactionajax.htm?subprocessid=' + $('#subprocess1').val() + '&action=' + encodeURIComponent(actionName) + '&actiontype=' + encodeURIComponent(actionType) + '&percentage=' + encodeURIComponent(actionPercentage) + '&actiontypetext=' + encodeURIComponent(actionTypeText),
            type: 'POST',
            datatype: 'json',
            async: false,
            success: function(json)
            {

                if (json.check == "true")
                {
                    getActionDetailes();
                }
                else {
                    alert('Error while saving Actions.');
                }

            }
        });
    }
}

function checkAddValidation(actionName, actionType, actionPercentage, actionTypeText) {
    var flag = true;
    if (actionName === "") {
        alert('Please enter an Action');
        $('#newaction').attr('style', 'min-width : 10px; width : 375px;border-color : red');
        $('#newaction').focus();
        flag = false;

    }
    else if (actionType !== 0 && parseInt(actionType) < 1) {
        alert('Please select an Action Type');
        $('#newactiontype').attr('style', 'border-color : red');
        $('#newactiontype').focus();
        flag = false;
    }
    else if (typeof actionTypeText !== "undefined" && actionTypeText === '') {
        alert('Please enter an Action Type');
        $('#textnewactiontype').attr('style', 'min-width : 10px; width : 150px;border-color : red');
        $('#textnewactiontype').focus();
        flag = false;
    }
    else if (actionPercentage === "") {
        alert('Please enter a Percentage')
        $('#newactionprecentage').attr('style', 'min-width : 10px; width : 150px;border-color : red');
        $('#newactionprecentage').focus();
        flag = false;
    }
    else if (!$.isNumeric(actionPercentage)) {
        alert('Please enter a Number')
        $('#newactionprecentage').attr('style', 'min-width : 10px; width : 150px;border-color : red');
        $('#newactionprecentage').focus();
        flag = false;
    }
    else if (parseInt(actionPercentage) > 100) {
        alert('Please enter a number below 100')
        $('#newactionprecentage').attr('style', 'min-width : 10px; width : 150px;border-color : red');
        $('#newactionprecentage').focus();
        flag = false;
    }


    return flag;
}

function dropdownselected(id)
{
    if ($('#' + id).val() === '0') {
        $('#' + id).closest('td').html("<input type='text' maxlength='30' autocomplete='off' onkeypress='removebordercolor(this.id);' id='text" + id + "' onchange='checkActionType(this.id)' placeholder='Enter Action Type' style='min-width : 10px; width : 150px' />");
    }
}

function checkActionType(actiontype)
{
    if (actiontype != "")
    {
        $.ajax(
                {
                    cache: false,
                    url: 'checkactiontype.htm?actiontype=' + encodeURIComponent($('#' + actiontype).val()),
                    type: 'POST',
                    datatype: 'json',
                    success: function(json)
                    {
                        if (json.check == "true")
                        {
                            $("#invalid-ActionTypeId").html('');
                        }
                        else if (json.check == "false")
                        {

                            alert('Action Type already exists');
                            $('#' + actiontype).val('');
                            $('#' + actiontype).attr('style', 'min-width : 10px; width : 150px;border-color : red');
                            $('#' + actiontype).focus();
                        }
                        return json.check;
                    }
                }
        );
    }
}

function checkValidation(id, actionName, actionType, actionPercentage, actionTypeText) {

    var flag = true;
    if (actionName === "") {
        alert('Please enter an Action');
        $('#action' + id).attr('style', 'min-width : 10px; width : 375px;border-color : red');
        $('#action' + id).focus();
        flag = false;

    }
    else if (actionType !== 0 && parseInt(actionType) < 1) {
        alert('Please select an Action Type');
        $('#actiontype' + id).attr('style', 'border-color : red');
        $('#actiontype' + id).focus();
        flag = false;
    }
    else if (typeof actionTypeText !== "undefined" && actionTypeText === '') {
        alert('Please enter an Action Type');
        $('#textactiontype' + id).attr('style', 'min-width : 10px; width : 150px;border-color : red');
        $('#textactiontype' + id).focus();
        flag = false;
    }
    else if (actionPercentage === "") {
        alert('Please enter a Percentage')
        $('#actionpercentage' + id).attr('style', 'min-width : 10px; width : 150px;border-color : red');
        $('#actionpercentage' + id).focus();
        flag = false;
    }
    else if (!$.isNumeric(actionPercentage)) {
        alert('Please enter a Number')
        $('#actionpercentage' + id).attr('style', 'min-width : 10px; width : 150px;border-color : red');
        $('#actionpercentage' + id).focus();
        flag = false;
    }
    else if (parseInt(actionPercentage) > 100) {
        alert('Please enter a number below 100')
        $('#actionpercentage' + id).attr('style', 'min-width : 10px; width : 150px;border-color : red');
        $('#actionpercentage' + id).focus();
        flag = false;
    }


    return flag;
}

function updateactions(id, actionId) {

    var actionName = $('#action' + id).val();
    var actionType = $('#actiontype' + id).val();
    var actionPercentage = $('#actionpercentage' + id).val();
    var actionTypeText = $('#textactiontype' + id).val();
    if (typeof actionType === "undefined")
    {
        actionType = 0;
    }

    if (checkValidation(id, actionName, actionType, actionPercentage, actionTypeText) && checkActionName(id, actionId, actionName, actionType)) {
        $.ajax({
            cache: false,
            url: 'updateactionajax.htm?subprocessid=' + $('#subprocess1').val() + '&actionId=' + encodeURIComponent(actionId) + '&action=' + encodeURIComponent(actionName) + '&actiontype=' + encodeURIComponent(actionType) + '&percentage=' + encodeURIComponent(actionPercentage) + '&actiontypetext=' + encodeURIComponent(actionTypeText),
            type: 'POST',
            datatype: 'json',
            async: false,
            success: function(json)
            {

                if (json.check == "true")
                {
                    getActionDetailes();
                }
                else {
                    alert('Error while updating Actions');
                }

            }
        });
    }
}

function checkActionName(id, actionId, actionName, actionType)
{
    var flag = true;
    $.ajax({
        async: false,
        cache: false,
        datatype: 'json',
        type: 'POST',
        url: 'checkactionsedit.htm?action=' + actionName + '&actiontype=' + actionType + '&actionid=' + actionId,
        beforeSend: function()
        {
            $('#invalid-action').html('');
        },
        success: function(json)
        {
            if (json.check === 'false') {
                alert('Action already exists');  // val();
                $('#action' + id).val('');
                $('#action' + id).attr('style', 'min-width : 10px; width : 375px;border-color : red');
                $('#action' + id).focus();
                flag = false;
            }

        }
    });
    return flag;
}