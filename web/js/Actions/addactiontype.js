/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $('#status').val("1");
    addactiontypeValidate();
});

function addactiontypeValidate() {
    $("#AddActionType").validate(
            {
                submitHandler: function(form)
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
                errorPlacement: function(error, element)
                {
                    $("#invalid-" + element.attr('id')).html('');
                    error.appendTo('#invalid-' + element.attr('id'));
                    error.attr('class', 'validationerror');
                }});
}

function checkActionType(actiontype)
{
    if (actiontype != "")
    {
        $.ajax(
                {
                    cache: false,
                    url: 'checkactiontype.htm?actiontype=' + encodeURIComponent(actiontype),
                    type: 'POST',
                    datatype: 'json',
                    success: function(json)
                    {
                        if (json.check == "true")
                        {
                            $("#invalid-actionType").html('');
                        }
                        else if (json.check == "false")
                        {
                            $('label.error').empty();
                            $("#invalid-actionType").html("<label class='validationerror' for='invalid-username' generated='true'>Action Type already exists</label>");
                            $('#actionType').val('');
                        }
                        return json.check;
                    }
                }
        );
    }
}
