/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    addactionsValidate();
    var value = $('#subprocess').val();
    updateSubprocess($('#process').val(), 'subprocess');
    $('#subprocess').val(value);
});


function addactionsValidate() {
    $("#Editactions").validate(
            {
                submitHandler: function(form)
                {
                    var emptyFields = false;
                    if (checkActionName())
                    {
                        emptyFields = true;
                    }
                    if (emptyFields) {
                        ajaxformSubmit(form);
                    }
                },
                rules:
                        {
                            subprocess:
                                    {
                                        required: true
                                    },
                            ActionTypeId:
                                    {
                                        required: true
                                    },
                            percentage:
                                    {
                                        required: true,
                                        number: true,
                                        max: 100
                                    },
                            action:
                                    {
                                        required: true
                                    },
                            status:
                                    {
                                        required: true
                                    },
                            actionType:
                                    {
                                        required: true
                                    }
                        },
                messages:
                        {
                            subprocess:
                                    {
                                        required: "Please enter a Sub process"
                                    },
                            ActionTypeId:
                                    {
                                        required: "Please select an Action Type"
                                    },
                            percentage:
                                    {
                                        required: "Please enter a Percentage",
                                        number: "Please enter a Number",
                                        max: "Please enter a number below 100"
                                    },
                            action:
                                    {
                                        required: "Please enter an Action"
                                    },
                            status:
                                    {
                                        required: "Please select a Status"
                                    },
                            actionType:
                                    {
                                        required: "Please enter an Action Type"
                                    }
                        },
                errorElement: "label",
                errorPlacement: function(error, element)
                {
                    $("#invalid-" + element.attr('id')).html('');
                    error.appendTo('#invalid-' + element.attr('id'));
                    error.attr('class', 'validationerror');
                }});
}

function updateSubprocess(value, id)
{
    $.ajax(
            {
                cache: false,
                datatype: 'json',
                type: 'POST',
                url: 'getsubprocess.htm?pid=' + value,
                async: false,
                beforeSend: function()
                {

                },
                success: function(json)
                {
                    var data = json.SubProcessList;
                    var html = "<option value='0'>Select</option>";
                    $.each(data, function(i, val)
                    {
                        html += "<option value='" + i + "'>" + val + "</option>";
                    });
                    $("#" + id).html(html);
                }
            });
}


function dropdownselected(id)
{
    if ($('#' + id).val() === '0') {
        $('#actiontypeid').html("<input type='text' maxlength='30' name='actionType' autocomplete='off' onchange='checkActionType(this.id)' id='ActionTypeId'  />");
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
                            $('label.error').empty();
                            $("#invalid-ActionTypeId").html("<label class='validationerror' for='invalid-username' generated='true'>Action Type already exists</label>");
                            $('#ActionTypeId').val('');
                        }
                        return json.check;
                    }
                }
        );
    }
}

function checkActionName()
{
    var flag = true;
    $.ajax({
        async: false,
        cache: false,
        datatype: 'json',
        type: 'POST',
        url: 'checkactionsedit.htm?action=' + $('#action').val() + '&actiontype=' + $('#ActionTypeId').val() + '&actionid=' + $('#actionId').val(),
        beforeSend: function()
        {
            $('#invalid-action').html('');
        },
        success: function(json)
        {
            if (json.check === 'false') {
                $('#invalid-action').html('<label for="action" generated="true" class="validationerror">Action already exists</label>')
                $('#action').val('');
                flag = false;
            }
        }
    });
    return flag;
}