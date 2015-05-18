<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">.
<script type="text/javascript" src="js/common/jsapi.js"></script>
<script type="text/javascript" src="js/common/arrayscript.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
$(window).load(function()
{

    $('.test').on('click',function()
    {    
        var array=new Array();
        array.push('2014-10-31');
        array.push('2013-07-31');
        var datalength=0;
        $.ajax({
            type:'POST',
            datatype:'json',
            cache:false,
            url:'ajaxglobalcost1.htm',
            data:{'date':array},
            success: function(json)
            {
                var datesArray=new Array();
                datesArray.push('');
                var data=json.Data;
                var dataArray=new Array();
                $.each(data,function(i,val)
                {
                    datesArray.push(i);
                    var datewisedata=val;
                    datalength++;
                    for(var j=0;j<datewisedata.length;++j)
                    {
                        dataArray=rowIteration(dataArray,datewisedata[j].xValue,datewisedata[j].yValue);
                    }    
                });
                drawChart(generateTargetArray(dataArray,datalength,datesArray), 'Title', 'XAxis', 'YAxis', 'chart_div');
            }});
        });
        
    
});
</script>

<form:form modelAttribute="test" method="post">
    <%--form:input type="text" path="username" id="username"/>
    <form:input type="password" path="password" id="password"/>
    <input type="submit" value="OK"/--%>
    <input type="button" name="test" class="test" id="test" value="test"/>
    <div id="chart_div" style="width: 900px; height: 500px;">   
    </div>
    <div id="chart_div1" style="width: 900px; height: 500px;">   
    </div>
    
</form:form>
