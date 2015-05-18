<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form modelAttribute="ajaxtest" onsubmit="formsubmit()">
    <label for="login" class="mrg">Username:</label>
    <p id="invalid-username"></p>
    <form:input type="text" path="username" class="text" AUTOCOMPLETE="OFF"/>
    <label for="pass">Password:</label>
    <p id="invalid-password"></p>
    <form:input type="password" path="password"  class="text" AUTOCOMPLETE="OFF"/>
    <input type="button" value="Login" onclick="formsubmit()"/>
    <input type="button" value="Test" onclick="$('body').load('404.htm')"/>
    <%--a class="log_button" href="">Forgotten password?</a--%>
</form:form>
    <div>${success}</div>
    <div id="test"></div>
    
    
    <script type="text/javascript">
        function formsubmit()
        {
            $.ajax({
                dataType : 'html',
                type: "POST",
                url: 'ajaxtest.htm',
                data: $("#ajaxtest").serialize(), // our form data da give and id to the form we use here
                success: function(data)
                {
                    $("#global_area_main_admin").html(data);
                }
            });
        }
    </script>