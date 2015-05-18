<%-- 
    Document   : loginlayout
    Created on : Apr 13, 2012, 10:49:21 PM
    Author     : Vishnu
    Description: Layout for the login page and other error pages
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="images/small_mark.png" type="image/x-icon"/>
        <title>
            <tiles:insertAttribute name="title" ignore="true" />
        </title>
        <!--Scripts start-->

        <noscript><meta http-equiv="refresh" content="0; url=noscript.jsp"></noscript>

        <script type="text/javascript" src="js/jQuery/Jquery.js"></script>
        <script type="text/javascript" src="js/jQuery/jquery.validate.js"></script>
        <!--Menu Scripts end-->
        <script type="text/javascript" src="js/jQuery/jquery.tinyscrollbar.js"></script>
        <link type="text/css" href="css/website.css" rel="stylesheet" />

        <script type="text/javascript" src="js/common/common.js"></script>
        <!--Scripts end-->

        <!--Styles start-->
        <link type="text/css" href="css/doodlestyle.css" rel="stylesheet" />
        <link type="text/css" href="css/style.css" rel="stylesheet" />
        <!--Styles end-->        


    </head>
    <body class="bg0">
        <tiles:insertAttribute name="body" />
    </body>
</html>