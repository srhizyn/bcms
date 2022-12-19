<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <title>BCMS | Dashboard</title>
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- Include one of jTable styles. -->
        <link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
        <!-- Include jTable script file. -->

        <script src="js/jquery-1.11.3.js" type="text/javascript"></script>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.jtable.js" type="text/javascript"></script>


        <!-- Logout Start -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <!-- Logout End -->
        <!-- Project Nav-bar Style -->
        <style>


        </style>   

    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
    <header class="header">
        <a href="" class="logo">
            <!-- Add the class icon to your logo image or logo icon to add the margining -->
            BCMS
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <div class="navbar-right">
                <ul class="nav navbar-nav">

                    <!-- User Account: style can be found in dropdown.less -->
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="LoginServlet?action=logout">Logout <span class="glyphicon glyphicon-log-out"></span></a>

                            <div class="dropdown-menu">
                                <form id="formLogin" class="form container-fluid" action="LoginServlet?action=logout" method="POST">
                                    <button type="submit" id="btnLogin" class="btn btn-block">Log out</button>
                                </form>                                
                            </div>

                        </li>
                    </ul>
                    <p></p>
                </ul>
            </div>
        </nav>
    </header>
    <div class="wrapper row-offcanvas row-offcanvas-left">
        <!-- Left side column. contains the logo and sidebar -->
        <jsp:include page="MenuPage.jsp" />

        <!-- Right side column. Contains the navbar and content of the page -->
        <aside class="right-side">                
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    Dashboard 
                    <small></small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                </ol>
            </section>

            <!-- Main content -->
            <!--EDITED BY azim.kamarudin-->
            <section class="content">

                <h4>Project:</h4>

                <div class="tab">
                    <button class="tablinks" onclick="openCity(event, 'SSP')" id="defaultOpen">SSP-LINE (PYLINE - LINE 2)</button>
                    <button class="tablinks" onclick="openCity(event, 'MRTS')">MRTS (RTSLINK)</button>
                    <button class="tablinks" onclick="openCity(event, 'SBK')">SBK-LINE (KJLINE - LINE 1)</button>
                    <button class="tablinks" onclick="openCity(event, 'MRT3')">MRT3 (MRTL - LINE 3)</button>
                </div>


                <!--**********PROJECT: PYLINE - LINE 2**********-->
                <div id="SSP" class="tabcontent">
                    <h3>SSP-LINE (PYLINE - LINE 2)<br>Contractor</h3>
                        <jsp:include page="ProjectSSP.jsp" />
                </div>

                <!--**********PROJECT: MRTS - RTSLINK**********-->
                <div id="MRTS" class="tabcontent">
                    <h3>MRTS (RTSLINK)<br>Contractor</h3>
                        <jsp:include page="ProjectMRTS.jsp" />
                </div>

                <div id="SBK" class="tabcontent">
                    <h3>SBK-LINE (KJLINE - LINE 1)<br>Contractor</h3></h3>
                    <jsp:include page="ProjectSBK.jsp" />
                </div>

                <div id="MRT3" class="tabcontent">
                    <h3>MRT3 (MRTL - LINE 3)<br>Contractor</h3></h3>
                    <jsp:include page="ProjectMRTL.jsp" />
                </div>


            </section>

            <section class="content">

                <br>
                <div id="show" align="center"></div>


            </section><!-- /.content -->
        </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->

    <!-- Bootstrap -->
    <!--<script src="js/plugins/bootstrap.min.js" type="text/javascript"></script>-->
    <!-- AdminLTE App -->
    <!--<script src="js/plugins/AdminLTE/app.js" type="text/javascript"></script>-->



</body>
<script>
    function openCity(evt, cityName) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(cityName).style.display = "block";
        evt.currentTarget.className += " active";
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();
</script>
</html>