/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $('#status').val("1");
    addactionsValidate();
});

function addactionsValidate() {
    $("#AddActions").validate(
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
                            process:
                                    {
                                        required: true
                                    },
                            subprocess:
                                    {
                                        required: true
                                    },
                            ActionTypeId:
                                    {
                                        min: 0
                                    },
                            percentage:
                                    {
                                        max: 100,
                                        number: true,
                                        required: true

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
                            process:
                                    {
                                        required: "Please select a Process"
                                    },
                            subprocess:
                                    {
                                        required: "Please select a Sub-process"
                                    },
                            ActionTypeId:
                                    {
                                        min: "Please select an Action Type"
                                    },
                            percentage:
                                    {
                                        max: "Please enter a number below 100",
                                        required: "Please enter a Percentage",
                                        number: "Please enter a Number"

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
        url: 'checkactions.htm?action=' + $('#action').val() + '&actiontype=' + $('#ActionTypeId').val(),
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