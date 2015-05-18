<%@page import="java.util.List"%>
<%@page import="com.pumex.ConnectedInsight.login.beans.UserBean"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="favicon-2.ico" type="image/x-icon"/>
        <title>
            <tiles:insertAttribute name="title" ignore="true" />
        </title>
        <!--Scripts start-->
        <noscript><meta http-equiv="refresh" content="0; url=noscript.jsp"></noscript>

        
        <!--Styles start-->
        <link type="text/css" href="css/doodlestyle.css" rel="stylesheet" />
        <link type="text/css" href="css/style.css" rel="stylesheet" />
        <link type="text/css" href="css/new_layout.css" rel="stylesheet" />
        <link type="text/css" href="css/scroll.css" rel="stylesheet" />
        <link type="text/css" href="css/website.css" rel="stylesheet" media="screen"/>
        <link type="text/css" href="css/jquery.dataTables.css" rel="stylesheet" />
        <link type="text/css" href="css/jquery.validate.password.css" rel="stylesheet" />
        <link type="text/css" href="css/jQuery/jquery-ui-1.css" rel="stylesheet" />
        <!--link type="text/css" href="css/jQuery/jquery.fancybox-1.3.4.css" rel="stylesheet" /-->
        <!--Styles end-->
        
        <script type="text/javascript" src="js/jQuery/Jquery.js"></script>
        <!--new Style-->
<!--        <script type="text/javascript" src="js/jQuery/jquery-1.3.1.min.js"></script>
        <script type="text/javascript" src="js/jQuery/jquery-latest.min.js"></script>-->
        <!--End of new Style-->
        <script type="text/javascript" src="js/jQuery/jquery.tinyscrollbar.js"></script>

        <script type="text/javascript" src="js/jQuery/jquery.validate.js"></script>
        <script type="text/javascript" src="js/jQuery/jquery.form.js"></script>
        <script type="text/javascript" src="js/jQuery/jquery.validate.password.js"></script>
        
        <script type="text/javascript" src="js/jQuery/jquery-ui-1.js"></script>
        <script type="text/javascript" src="js/jQuery/Jscrollpane.js"></script>
        <script type="text/javascript" src="js/jQuery/jQuery.mousewheel.js"></script>
        <!--script type="text/javascript" src="js/jQuery/jquerybgf.js"></script>
        <script type="text/javascript" src="js/jQuery/jquerydimension.js"></script-->

        <script type="text/javascript" src="js/jQuery/jquery.dataTables.js"></script>
        <!--new Style-->
        <script type="text/javascript" src="js/jQuery/Selectyze.jquery.js"></script>
        <script type="text/javascript" src="js/jQuery/Selectyze.jquery.min.js"></script>
        <!--End of new Style-->
        <!--script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.js"></script>
        <script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
        <script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script-->
        <!--Menu Scripts>
        <script type="text/javascript" src="js/menu/hoverIntent.js"></script>
        <script type="text/javascript" src="js/menu/jquery.bgiframe.min.js"></script-->

        <!--Menu Scripts end-->
        <script type="text/javascript" src="js/common/common.js"></script>

        <!--Dashboard scripts-->
        <script type="text/javascript" src="js/dashboard/graphjs1.js"></script>
        <script type="text/javascript" src="js/dashboard/graphjs2.js"></script>
        <script type="text/javascript" src="js/dashboard/tablescripts.js"></script>
        <!--Dashboard scripts-->

        <!--Scripts end-->

        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript" src="js/dashboard/arrayscript.js"></script>
        
        <script type="text/javascript">
            google.load("visualization", "1.0", {packages:["corechart"]});
        </script>


        <script type="text/javascript">
            $(document).ready(function()
            {
                disableAnchor();
                $(document).click(function(e){
                    if(e.which==2)
                    {
                        return false;
                    }
                });
                checkSession();
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
        <div id="throbber"  style="position: absolute;top:50%;left: 50%;display: none;z-index: 9999;">
            <img src="images/throbber.gif"/>
        </div>

        <div id="wrapper_main">
            <div id="content">
                <div class="c1">
                    <%--Title placeholder starts--%>
                    <div class="d_top_head">
                        <div id="headingdiv" class="head_top_out">
                            <img id="headingimage" alt="Dashbord" src="images/administrator-icon.png"/>
                            <p id="heading">Administration</p>
                        </div>
                    </div>
                    <%--Title placeholder ends--%>
                    <jsp:include page="submenu.jsp"/>
                    <%--Body starts--%>
                    <div id="global_area_main_admin" class="global_area_main_admin">
                        <tiles:insertAttribute name="body" />
                    </div>               

                    <%--Body ends--%>
                </div>
            </div>
            <jsp:include page="sidemenu.jsp"/>
        </div>

        <%--Dialog content for the confirmation--%>
        <div id="dialog-confirm" title="Confirmation" style="display: none">

        </div>
        <%--Dialog content--%>

    </body>
</html>