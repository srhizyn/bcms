<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | Ballot Master</title>
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
    <body class="skin-blue" onload="alertBallot()">
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
                        Ballot Master
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Ballot Master</li>
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
    

</style>
    <form action="BallotServlet?action=ballotmaster_create"  method="POST" enctype="multipart/form-data" accept-charset='UTF-8'>
         <div class="panel-group" id="accordion">
             <div class="panel panel-default" id="panel1">
        <div class="panel-heading bg-light-blue">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne" class="collapsed">
          Ballot Master Details
        </a>
      </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
        <div class="form-group-sm">
            <label for="inputBallotTitle" class="col-sm-3 control-label">Ballot Title</label>
            <div class="col-sm-8">
                <input type="text"  size="50" id="ballotTitle" name="ballotTitle" class="form-control" required="required" placeholder="Enter Ballot Title">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputBallotDate" class="col-sm-3 control-label">Balloting Date</label>
            <div class="col-sm-8">
                <input type="date" name="ballotDate" id="ballotDate" class="form-control" required="required" min="2000-01-01" max="2040-12-31">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputBallotVenue" class="col-sm-3 control-label">Balloting Venue</label>
            <div class="col-sm-8">
                <input type="text"  size="50" id="ballotVenue" name="ballotVenue" class="form-control" required="required" placeholder="Enter Ballot Venue">
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputCidbGrade" class="col-sm-3 control-label">Work Package</label>
        <div class="input_fields_wrap">
        <div class="col-sm-8">
            <select name="cidbGrade" id="cidbGrade" class="form-inline" style="width:140px; vertical-align:top;" >
                        <option value="">Please Select</option>
                        <c:forEach items="${cidbGrade}" var="row">
                            <option value="${row.gradeID}">${row.gradeCode}</option>
                        </c:forEach>
                        <option value="NA">Not Available</option>
            </select>
            <select name="specialisation" id="specialisation" class="form-inline" style="width:140px;"  multiple size="3">
                        <option value="">Please Select</option>
                        <c:forEach items="${specialisation}" var="row">
                            <option value="${row.specialisationID}">${row.specialisationCode}</option>
                        </c:forEach>
                        <option value="NA">Not Available</option>
            </select>
            <select name="spkkSpec" id="spkkSpec" class="form-inline" style="width:140px;" multiple size="3">
                        <option value="">Please Select</option>
                        <c:forEach items="${spkkSpec}" var="row">
                            <option value="${row.spkkSpecID}">${row.spkkSpecCode}</option>
                        </c:forEach>
                        <option value="NA">Not Available</option>
            </select>
            <button class="add_field_button" style="height:23px;">+</button>
        </div>
        </div>
        </div>--%>
        <%--<div class="form-group-sm">
            <label for="inputCidbGrade" class="col-sm-3 control-label">CIDB Grade</label>
        <div class="col-sm-8">
            <select name="cidbGrade" id="cidbGrade" class="form-control" multiple size="3">
                        <c:forEach items="${cidbGrade}" var="row">
                            <option value="${row.gradeID}">${row.gradeCode}</option>
                        </c:forEach>
                    </select>
        </div>
        </div>--%>
        <%--<div class="form-group-sm">
            <label for="inputSpecSow" class="col-sm-3 control-label">Specialisation & Scope of Work</label>
        <div class="col-sm-8">
            <select name="specSow" id="specSow" class="form-control" multiple size="6">
                        <c:forEach items="${specSow}" var="row">
                            <option value="${row.subContractSpecID}">${row.specialisationCode} - ${row.scopeWork}</option>
                        </c:forEach>
                    </select>
        </div>
        </div>--%>
        <div class="form-group-sm">
            <label for="inputAttachBallotSession" class="col-sm-3 control-label">Attachment</label>
            <div class="col-sm-8">
                <input type="file" name="attachBallotSession" id="attachBallotSession" class="form-control-static" multiple="multiple"/>
            </div>
        </div>
        </div>
        </div>
    </div>
         </div>
        <div class="form-group"> 
            <div class="col-sm-offset-1">
                <input type="submit" value="Add Ballot Master" id="AddBallotMaster" class="form-control-static" onclick="return confirm('Confirm to add ballot master details?')"/>
                <input type="reset" value="Reset" id="ResetBallotMaster" class="form-control-static"/>
                <a href="BallotMasterList.jsp" style=""><font color="#000000"><input type="button" class="form-control-static" value="Cancel" style=""/></font></a>
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
</html>
<script>
        function alertBallot()
{
var javascriptVar="${alertballot}";
if(javascriptVar === 'true'){
    alert("Ballot Master Information Successfully Added");
    window.location = "fwdBallotMaster.jsp";
    return true;
    }
    else if (javascriptVar === 'false') {
    alert("An Error Occured. Please Try Again");   
    return false;
    }
    
}
</script>
<script language="javascript">
      $(document).ready(function() {
        var max_fields      = 40; //maximum input boxes allowed
        var wrapper         = $(".input_fields_wrap"); //Fields wrapper
        var add_button      = $(".add_field_button"); //Add button ID

        
        var x = 1; //initlal text box count
        $(add_button).click(function(e){ //on add input button click
            e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                 $(wrapper).append('<div class="form-group-sm"><label for="inputCidbGrade" class="col-sm-3 control-label"></label><div class="col-sm-9"><select name="cidbGrade" id="cidbGrade" class="form-inline" style="width:140px; vertical-align:top;" >' +
                '<option value="">Please Select</option>'+
                <c:forEach items="${cidbGrade}" var="row">
                    '<option value="'+${row.gradeID}+'">${row.gradeCode}</option>'+
                </c:forEach>
                '<option value="NA">Not Available</option></select>\n\
                <select name="specialisation" id="specialisation" class="form-inline" style="width:140px;" multiple size="3">' +
                '<option value="">Please Select</option>'+
                <c:forEach items="${specialisation}" var="row">
                    '<option value="'+${row.specialisationID}+'">${row.specialisationCode}</option>'+
                </c:forEach>
                '<option value="NA">Not Available</option></select>\n\
                <select name="spkkSpec" id="spkkSpec" class="form-inline" style="width:140px;" multiple size="3">' +
                '<option value="">Please Select</option>'+
                <c:forEach items="${spkkSpec}" var="row">
                    '<option value="'+${row.spkkSpecID}+'">${row.spkkSpecCode}</option>'+
                </c:forEach>
                '<option value="NA">Not Available</option></select>\n\
                <a href="#" class="remove_field"> Remove</a></div></div>'); //add input box
        }
    });
    
     $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
            e.preventDefault(); $(this).parent('div').parent('div').remove(); x--;
        });
        });
</script>
<script>
        $(function(){
        var $cat = $("#ResetBallot"),
        $subcat = $("#ssmCertNo");
        $subcat2 = $("#cidbGrade");
        $cat.on("click",function(){
        
        $subcat.find("option").attr("style","");
        $subcat.val("");
        $subcat2.find("option").attr("style","");
        $subcat2.val("");
        
            });
        });
    </script>

