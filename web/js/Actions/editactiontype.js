/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    editactiontypeValidate();
});

function editactiontypeValidate() {
    $("#EditActionType").validate(
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
    var actiontypeid = $('#actionTypeId').val();
    if (actiontype != "")
    {
        $.ajax(
                {
                    cache: false,
                    url: 'checkactiontypeedit.htm?actiontype=' + encodeURIComponent(actiontype) + "&actiontypeid=" + encodeURIComponent(actiontypeid),
                    type: 'POST',
                    datatype: 'json',
                    async: false,
                    success: function(json)
                    {
                        if (json.check == "true")
                        {
                            $("#invalid-actionType").html('');
                            return true;
                        }
                        else if (json.check == "false")
                        {
                            $('label.error').empty();
                            $("#invalid-actionType").html("<label class='validationerror' for='invalid-username' generated='true'>Action Type already exists</label>");
                            $('#actionType').val('');
                        }
                        return false;
                    }
                }
        );
    }
}


function checkAction() {
    if ($('#status').val() === '2') {
        $.ajax({
            cache: false,
            url: 'checkactiontypeactions.htm?actiontypeid=' + encodeURIComponent($('#actionTypeId').val()),
            type: 'POST',
            datatype: 'json',
            async: false,
            success: function(json)            {

                if (json.check == "false")
                {
                    alert('Action item exists for this action type. Please delete action first.');
                    $('#status').val(1);                   
                }
                
            }
        });
    }
}




