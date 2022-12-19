<%-- 
    Document   : BallotView
    Created on : Feb 17, 2016, 3:00:26 PM
    Author     : munawwarah.mahmod
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | Ballot View</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
         
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

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        
        <!-- Morris.js charts -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="js/raphael-min.js" type="text/javascript"></script>
        <script src="js/plugins/morris/morris.min.js" type="text/javascript"></script>
        
        <!-- Logout Start -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <!-- Logout End -->

        
    </head>
    <body class="skin-blue" >
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
                        <li class="dropdown" style="width:100px"><a class="dropdown-toggle" data-toggle="dropdown" href="LoginServlet?action=logout">Logout <span class="glyphicon glyphicon-log-out"></span></a>

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
                        Ballot
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Ballot</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                     <div id="show" align="center"></div>
<div>
<style type="text/css">     
    .selectoption {
        width:310px;
    }
    select {
        color:black;
    }
    h4{
        text-decoration: underline;
        margin-left: 45px;
    }
    tr.spaceUnder > td
    {
      padding-bottom: 1em;
    }
    tr.spaceUnder2 > td
    {
      padding-bottom: 0.3em;
    }
    div
    {
     padding-bottom: 0.3em; 
    }
    #ssmCertNo option{
    display:none;
    }

    #ssmCertNo option.label{
    display:block;
    }
    
    #cidbGrade option{
    display:none;
    }

    #cidbGrade option.label{
    display:block;
    }
    
     #ballotSession option{
    display:none;
    }

    #ballotSession option.label{
    display:block;
    }

</style>
    <form action="BallotServlet?action=ballot_create"  method="POST">
         <div class="panel-group" id="accordion">
             <div class="panel panel-default" id="panel1">
        <div class="panel-heading">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne" class="collapsed">
          Ballot Details
        </a>
      </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
        <div class="form-group-sm">
            <label for="inputBallotSession" class="col-sm-3 control-label">Ballot Session</label>
            <div class="col-sm-8">
                    <select name="ballotSession" id="ballotSession" class="form-control" size="1" disabled>
                        <c:forEach items="${ballotSession}" var="row">
                            <option value="${row.ballotMasterID}">${row.titleBallot} - ${row.dateBallot}</option>
                        </c:forEach>
                    </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputContractor" class="col-sm-3 control-label">Work Package</label>
            <div class="col-sm-8">
                <select name="workPackage" id="workPackage" class="form-control" disabled>
                      <option value="">Please Select</option>
                      <c:forEach items="${workPackageSelectedList}" var="row">
                      <option value="${row.ballotWorkPackageID}">${row.ballotWorkPackage}</option>
                      </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputContractor" class="col-sm-3 control-label">Contractor</label>
            <div class="col-sm-8">
                <select name="contractor" id="contractor" class="form-control" disabled>
                      <option value="">Please Select</option>
                      <c:forEach items="${contractorSelectedList}" var="row">
                      <option value="${row.contractorID}">${row.companyName}</option>
                      </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputReferenceNumber" class="col-sm-3 control-label">SSM Certificate Number</label>
            <div class="col-sm-8" >
                <input type="text" name="ssmCertNo" id="ssmCertNo" class="form-control" placeholder="SSM Cert No" value="${ssmCertNo}" readonly>
            </div>
            
        </div>
        <div class="form-group-sm">
            <label for="inputCidbGrade" class="col-sm-3 control-label">CIDB Grade</label>
            <div class="col-sm-8">
                    <input type="text" name="cidbGrade" id="cidbGrade" class="form-control" placeholder="CIDB Grade" value="${cidbGrade}" readonly>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputFormType" class="col-sm-3 control-label">Ballot Status</label>
            <div class="col-sm-8">
                <c:forEach items="${ballotStatus}" var="row">
                    <input type="radio" name="ballotStatus" id="ballotStatus" value="${row.ballotStatusID}" disabled>${row.ballotStatusDesc} &nbsp;         
                </c:forEach>
                <script>
                        $('input[name="ballotStatus"][value="${selectedBallotStatus}"]').prop("checked", true);
                </script>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputAttachBallot" class="col-sm-3 control-label">Ballot Attachment</label>
            <div class="col-sm-8">
            <c:choose>
                    <c:when test="${insertedAttachmentBallot == []}">
                        <label for="inputInsertedAttachmentBallot" class="col-sm-9 control-label">No File</label>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${insertedAttachmentBallot}" var="row">
                            <ul><li><a href="BallotServlet?action=DownloadAttachment&fileName=${row.attachBallotFileName}&oriFileName=${row.oriAttachBallotFileName}&typeID=${row.attachBallotMasterID}" >${row.oriAttachBallotFileName}</a><br>   
                            </li></ul>
                            </c:forEach>
                    </c:otherwise>
                    </c:choose>
            </div>
        </div>
        </div>
        </div>
    </div>
         </div>
        <div class="form-group"> 
            <div class="col-sm-offset-1">
                <a href="BallotList.jsp" style=""><font color="#000000"><input type="button" class="form-control-static" value="Back" style=""/></font></a>
            </div>
        </div>
    </form>
</div>
    </section><!-- /.content -->
    </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        
         <!-- Bootstrap -->
        <!--<script src="js/plugins/bootstrap.min.js" type="text/javascript"></script>-->
        <!-- AdminLTE App -->
        <!--<script src="js/plugins/AdminLTE/app.js" type="text/javascript"></script>-->

    </body>
    <script>
    function setSelectedContractor(s, valsearch)
    {
    // Loop through all the items in drop down list
    for (i = 0; i< s.options.length; i++)
    { 
    if (s.options[i].value === valsearch)
    {
    // Item is found. Set its property and exit
    s.options[i].selected = true;
    break;
    }
    }
    return;
    }
    setSelectedContractor(document.getElementById("contractor"),"${selectedContractorID}");
    </script>
    <script>
    function setSelectedIndex5(s5, valsearch4)
    {
    // Loop through all the items in drop down list
    for (i = 0; i< s5.options.length; i++)
    { 
    if (s5.options[i].value === valsearch4)
    {
    // Item is found. Set its property and exit
    s5.options[i].selected = true;
    break;
    }
    }
    return;
    }
    setSelectedIndex5(document.getElementById("ballotSession"),"${selectedBallotMasterID}");
    </script>
    <script>
    function setSelectedIndex6(s6, valsearch5)
    {
    // Loop through all the items in drop down list
    for (i = 0; i< s6.options.length; i++)
    { 
    if (s6.options[i].value === valsearch5)
    {
    // Item is found. Set its property and exit
    s6.options[i].selected = true;
    break;
    }
    }
    return;
    }
    setSelectedIndex6(document.getElementById("workPackage"),"${selectedBallotWorkPackageID}");
    </script>
</html>

