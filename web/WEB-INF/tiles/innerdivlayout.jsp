<%@page import="java.util.List"%>
<%@page import="com.pumex.ConnectedInsight.login.beans.UserBean"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="images/small_mark.png" type="image/x-icon">
        <title>
            <tiles:insertAttribute name="title" ignore="true" />
        </title>
        <!--Scripts start-->
        <noscript><meta http-equiv="refresh" content="0; url=noscript.jsp"></noscript>

        <script type="text/javascript">
            $(document).ready(function()
            {
                $(this).attr('title','<tiles:insertAttribute name="title" ignore="true" />');
            });
        </script>

    </head>
    <%
        UserBean userBean = (UserBean) session.getAttribute("user");
        List menuList = (List) session.getAttribute("menuList");
        List subMenuList = (List) session.getAttribute("subMenuList");
        request.setAttribute("subMenuList", subMenuList);
        request.setAttribute("menuList", menuList);
        request.setAttribute("UserBean", userBean);
        /*request.setAttribute("defaultLocation",session.getAttribute("defaultLocation"));
        request.setAttribute("defaultProcess",session.getAttribute("defaultProcess"));
        request.setAttribute("defaultSubProcess",session.getAttribute("defaultSubProcess"));*/
        request.setAttribute("Organization", session.getAttribute("Organization"));
    %>
    <body>
        <%--Body part starts here--%>
        <tiles:insertAttribute name="body" />
        <%--Body part ends here--%>
    </body>
</html>