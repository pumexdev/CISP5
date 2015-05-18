/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function ()
{
    $('#addhrdata').jScrollPane({autoReinitialise: true});
    addTarget();
    $("#target1").val("0");
    $("#high1").val("0");
    $("#low1").val("0");
    $("#medium1").val("0");
});

function addTarget() {
    $("#AddTarget").validate(
            {
                submitHandler: function (form)
                {
                    var subprocess = true;
                    var metric = true;
                    var target = true;
                    var high = true;
                    var medium = true;
                    var low = true;
                    $("select[name^='subprocess']").each(function ()
                    {
                        var labelhtml1 = "<label for='costcenter' generated='true' class='validationerror'>Please select Subprocess</label>";
                        var val1 = $(this).val();
                        var id1 = $(this).attr("id");
                        console.log(val1 + id1);
                        if (val1 == "")
                        {
                            $("#invalid-" + id1).html(labelhtml1);
                            subprocess = false;
                        }
                        else
                        {
                            $("#invalid-" + id1).html('');
                            subprocess = true;
                        }
                    });

                    $("select[name^='metric']").each(function ()
                    {
                        var labelhtml2 = "<label for='costcenter' generated='true' class='validationerror'>Please select Metric</label>";
                        var val2 = $(this).val();
                        var id2 = $(this).attr("id");
                        console.log(val2 + id2);
                        if (val2 == "")
                        {
                            $("#invalid-" + id2).html(labelhtml2);
                            metric = false;
                        }
                        else
                        {
                            $("#invalid-" + id2).html('');
                            metric = true;
                        }
                    });
                    $("input[name^='target']").each(function ()
                    {
                        var labelhtml2 = "<label for='costcenter' generated='true' class='validationerror'>Please enter Target</label>";
                        var val2 = $(this).val();
                        var id2 = $(this).attr("id");
                        console.log(val2 + id2);
                        if (val2 == "")
                        {
                            $("#invalid-" + id2).html(labelhtml2);
                            target = false;
                        }
                        else
                        {
                            if (!$.isNumeric(val2)) {
                                labelhtml2 = "<label for='costcenter' generated='true' class='validationerror'>Please enter number</label>";
                                 $("#invalid-" + id2).html(labelhtml2);
                                target = false;
                            } else {
                                $("#invalid-" + id2).html('');
                                target = true;
                            }
                        }
                    });
                    $("input[name^='high']").each(function ()
                    {
                        var labelhtml2 = "<label for='costcenter' generated='true' class='validationerror'>Please enter High</label>";
                        var val2 = $(this).val();
                        var id2 = $(this).attr("id");
                        console.log(val2 + id2);
                        if (val2 == "")
                        {
                            $("#invalid-" + id2).html(labelhtml2);
                            high = false;
                        }
                        else
                        {
                            if (!$.isNumeric(val2)) {
                                labelhtml2 = "<label for='costcenter' generated='true' class='validationerror'>Please enter number</label>";
                                 $("#invalid-" + id2).html(labelhtml2);
                                high = false;
                            } else {
                                $("#invalid-" + id2).html('');
                                high = true;
                            }
                        }
                    });
                    $("input[name^='medium']").each(function ()
                    {
                        var labelhtml2 = "<label for='costcenter' generated='true' class='validationerror'>Please enter Medium</label>";
                        var val2 = $(this).val();
                        var id2 = $(this).attr("id");
                        console.log(val2 + id2);
                        if (val2 == "")
                        {
                            $("#invalid-" + id2).html(labelhtml2);
                            medium = false;
                        }
                        else
                        {
                            if (!$.isNumeric(val2)) {
                                labelhtml2 = "<label for='costcenter' generated='true' class='validationerror'>Please enter number</label>";
                                 $("#invalid-" + id2).html(labelhtml2);
                                medium = false;
                            } else {
                                $("#invalid-" + id2).html('');
                                medium = true;
                            }
                        }
                    });
                    $("input[name^='low']").each(function ()
                    {
                        var labelhtml2 = "<label for='costcenter' generated='true' class='validationerror'>Please enter Low</label>";
                        var val2 = $(this).val();
                        var id2 = $(this).attr("id");
                        console.log(val2 + id2);
                        if (val2 == "")
                        {
                            $("#invalid-" + id2).html(labelhtml2);
                            low = false;
                        }
                        else
                        {
                            if (!$.isNumeric(val2)) {
                                labelhtml2 = "<label for='costcenter' generated='true' class='validationerror'>Please enter number</label>";
                                 $("#invalid-" + id2).html(labelhtml2);
                                low = false;
                            } else {
                                $("#invalid-" + id2).html('');
                                low = true;
                            }
                        }
                    });
                    if (subprocess && metric && target && high && medium && low)
                    {
                        ajaxformSubmit(form);
                    }
                },
                rules:
                        {
                            processId:
                                    {
                                        required: true
                                    }
                        },
                messages:
                        {
                            processId:
                                    {
                                        required: "Please select a Process"
                                    }
                        },
                errorElement: "label",
                errorPlacement: function (error, element)
                {
                    $("#invalid-" + element.attr('id')).html('');
                    error.appendTo('#invalid-' + element.attr('id'));
                    error.attr('class', 'validationerror');
                }
            });

}

function addRow(rowid, rowno)
{
    var count = $("#count").val();
    count = parseInt(count, 10);
//    if(count!=4){
//        rowno=count;
//    }
//    var row_index = $(rowid).parent().index();
//    alert(rowno);
    var validationrow = "<tr id='validationrow'><td></td><td id='invalid-subprocess' style='padding:0.9%;'></td><td></td><td id='invalid-metric' style='padding:0.9%;'></td><td></td><td id='invalid-target' style='padding:0.9%;'></td><td></td><td id='invalid-high' style='padding:0.9%;'></td><td></td><td id='invalid-medium' style='padding:0.9%;'></td><td></td><td id='invalid-low' style='padding:0.9%;'></td></tr>";
    validationrow = validationrow.replace("validationrow", "validationrow" + (count + 1), "m");
    validationrow = validationrow.replace("invalid-subprocess", "invalid-subprocess" + (count + 1), "m");
    validationrow = validationrow.replace("invalid-metric", "invalid-metric" + (count + 1), "m");
    validationrow = validationrow.replace("invalid-target", "invalid-target" + (count + 1), "m");
    validationrow = validationrow.replace("invalid-high", "invalid-high" + (count + 1), "m");
    validationrow = validationrow.replace("invalid-medium", "invalid-medium" + (count + 1), "m");
    validationrow = validationrow.replace("invalid-low", "invalid-low" + (count + 1), "m");

    var newhtml = "<tr>" + $(rowid).parent().parent().html() + "</tr>";
//    newhtml=newhtml.replace("client"+rowno, "client"+(count+1), "gm");
//    newhtml=newhtml.replace("clientvalue"+rowno, "clientvalue"+(count+1), "gm");
    newhtml = newhtml.replace("subprocess" + rowno, "subprocess" + (count + 1), "gm");
    newhtml = newhtml.replace("subprocessvalue" + rowno, "subprocessvalue" + (count + 1), "gm");
    newhtml = newhtml.replace("metric" + rowno, "metric" + (count + 1), "gm");
    newhtml = newhtml.replace("metricvalue" + rowno, "metricvalue" + (count + 1), "gm");
    newhtml = newhtml.replace("target" + rowno, "target" + (count + 1), "gm");
    newhtml = newhtml.replace("high" + rowno, "high" + (count + 1), "gm");
    newhtml = newhtml.replace("medium" + rowno, "medium" + (count + 1), "gm");
    newhtml = newhtml.replace("low" + rowno, "low" + (count + 1), "gm");
    newhtml = newhtml.replace("row" + rowno, "row" + (count + 1), "gm");
//    debugger;
    var no = count + 1;

//    newhtml=newhtml.replace("fte"+rowno, "fte"+(count+1), "gm");
//    newhtml=newhtml.replace("ftevalue"+rowno, "ftevalue"+(count+1), "gm");
//    newhtml=newhtml.replace("compensation"+rowno, "compensation"+(count+1), "gm");
//    newhtml=newhtml.replace("addRow(this,'"+rowno+"')", "addRow(this,'"+(count+1)+"')", "gm");
//    newhtml=newhtml.replace("delRow(this,'"+rowno+"')", "delRow(this,'"+(count+1)+"')", "gm");

    newhtml = validationrow + newhtml;

    $("#empdet").last().append(newhtml);
    $("#count").val(count + 1);
    $("#row" + no).val(count + 1);

}

function delRow(rowid, rowno)
{
    var count = $("#count").val();
    count = parseInt(count, 10);
    var tablelength = count - 4;//($("#empdet tbody tr").length)-6;
//    alert(count);
    if (rowno == 1)
        alert("Row can not be deleted!!");
    else
    {
        $(rowid).parent().parent().remove();
        $("#validationrow" + rowno).remove();
//        if (rowno != 1) {
//            $("#count").val(count - 1);
//        }
    }
}

function getSubprocess(processid)
{
    $("select[name^='subprocess']").each(function ()
    {
        loadSubprocess(processid, $(this).attr("id"));
    });
}

function loadSubprocess(processid, id)
{
    $.ajax(
            {
                cache: false,
                type: 'POST',
                url: 'loadsubprocess.htm?processid=' + encodeURIComponent(processid),
                datatype: 'json',
                success: function (json)
                {
                    var html = "<option value=''>Select</option>";
                    var data = json.Subprocesslist;
                    $.each(data, function (i, val)
                    {
                        html += "<option value='" + i + "'>" + val + "</option>";
                    });
                    $("#" + id).html(html);
                }
            });
}

function cancelAddTarget()
{
//    var confirmation = confirm("Are you sure to reset?");
//    if(confirmation)
//    {
//        $("#add").load('addhrdata.htm');
//        var tabno = $("#tabno").val();
//        $('#tabs a:last').click();
//    }
}