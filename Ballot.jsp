<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | Ballot</title>
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
    
    }

</style>
    <form action="BallotServlet?action=ballot_create"  method="POST" enctype="multipart/form-data" accept-charset='UTF-8'>
         <div class="panel-group" id="accordion">
             <div class="panel panel-default" id="panel1">
        <div class="panel-heading bg-light-blue">
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
                    <select name="ballotSession" id="ballotSession" class="form-control" size="1">
                        <option value="0" rel="0">Please Select</option>
                        <c:forEach items="${ballotSession}" var="row">
                            <option value="${row.ballotMasterID}" rel="${row.ballotMasterID}">${row.titleBallot} - ${row.dateBallot}</option>
                        </c:forEach>
                    </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputWorkPackage" class="col-sm-3 control-label">Work Package</label>
            <div class="col-sm-8">
                <select name="workPackage" id="workPackage" class="form-control">
                      <option value="">Please Select</option>
                </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputContractor" class="col-sm-3 control-label">Contractor</label>
            <div class="col-sm-8">
                <select name="contractor" id="contractor" class="form-control">
                      <option value="">Please Select</option>
                </select>
            </div>
        </div>
        <div class="form-group-sm details_div">
            <label for="inputReferenceNumber" class="col-sm-3 control-label">SSM Certificate Number</label>
            <div class="col-sm-8" >
                <select name="ssmCertNo" id="ssmCertNo" multiple="multiple" class="form-control" size="1" readonly>
                    <c:forEach items="${contractor}" var="row">
                    <option value="${row.ssmCertNo}" rel="${row.contractorID}">${row.ssmCertNo}</option>
                    </c:forEach>
                </select>
                
            </div>
            
        </div>
        <div class="form-group-sm">
            <label for="inputCidbGrade" class="col-sm-3 control-label">CIDB Grade</label>
            <div class="col-sm-8">
                    <select name="cidbGrade" id="cidbGrade" multiple="multiple" class="form-control" size="1" readonly>
                        <c:forEach items="${contractor}" var="row">
                            <option value="${row.gradeID}" rel="${row.contractorID}">${row.gradeCode}</option>
                        </c:forEach>
                    </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputFormType" class="col-sm-3 control-label">Ballot Status</label>
            <div class="col-sm-8">
                <c:forEach items="${ballotStatus}" var="row">
                    <input type="radio" name="ballotStatus" id="ballotStatus" value="${row.ballotStatusID}">${row.ballotStatusDesc} &nbsp;         
                </c:forEach>
            </div>
        </div>
        
        <div class="form-group-sm">
            <label for="inputAttachBallot" class="col-sm-3 control-label">Ballot Attachment</label>
            <div class="col-sm-8">
                <input type="file" name="attachBallot" id="attachBallot" class="form-control-static" multiple="multiple"/>
            </div>
        </div>
        </div>
        </div>
    </div>
         </div>
        <div class="form-group"> 
            <div class="col-sm-offset-1">
                <input type="submit" value="Add Ballot" id="AddBallot" class="form-control-static" onclick="return confirm('Confirm to add ballot details?')"/>
                <input type="reset" value="Reset" id="ResetBallot" class="form-control-static"/>
                <a href="BallotList.jsp" style=""><font color="#000000"><input type="button" class="form-control-static" value="Cancel" style=""/></font></a>
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
    alert("Ballot Information Successfully Added");
    window.location = "fwdBallot.jsp";
    return true;
    }
    else if (javascriptVar === 'false') {
    alert("An Error Occured. Please Try Again");   
    return false;
    }
    
}
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
 <script>
        $(function(){
        var $cat = $("#contractor"),
        $subcat = $("#ssmCertNo");
        $subcat2 = $("#cidbGrade");
        $cat.on("change",function(){
        var _rel = $(this).find('option:selected').val();
        
        $subcat.find("option").attr("style","");
        $subcat.val("");
        $subcat2.find("option").attr("style","");
        $subcat2.val("");
        
        if(!_rel) return $subcat.prop("disabled",true);
        $subcat.find("[rel="+_rel+"]").show();
        $subcat.prop("disabled",false);
        
        if(!_rel) return $subcat2.prop("disabled",true);
        $subcat2.find("[rel="+_rel+"]").show();
        $subcat2.prop("disabled",false);
            });
        });
    </script>

<script>
    var $cat = $("#ballotSession");
    console.log($cat);
        //$subcat = $("#ssmCertNo");
        //$subcat2 = $("#cidbGrade");
        $cat.on("change",function(){
        $('#workPackage').empty();
        $('#contractor').empty();
        //$subcat.find("option").attr("style","");
        //$subcat.val("");
        //$subcat2.find("option").attr("style","");
        //$subcat2.val("");
        
        var _rel = $(this).find('option:selected').attr('rel');
        console.log(_rel);
        var _val = $(this).find('option:selected').val();
        $.ajax({
        url: "BallotServlet?action=select_workpackage&ballotMasterID=" +_val,
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var select = document.getElementById('workPackage');
            var option;
            option = document.createElement('option');
            option.text = 'Please Select';
            option.value = '';
            option.rel = 0;
            select.add(option);
            $.each(data, function (i, jsondata) {
            for (var i = 0; i < jsondata.length; i++) {
              option = document.createElement('option');
              option.text = jsondata[i].ballotWorkPackage;
              option.value = jsondata[i].ballotWorkPackageID;
              //option.rel = jsondata[i].gradeID;
              //document.getElementById("workPackage").rel = jsondata[i].gradeID;
              select.add(option);
            } 
            });
            
        }
    });
        });
    
</script>

<script>
    var $cat2 = $("#workPackage");
        $subcat = $("#ssmCertNo");
        $subcat2 = $("#cidbGrade");
        $cat2.on("change",function(){
        $('#contractor').empty();
        $subcat.find("option").attr("style","");
        $subcat.val("");
        $subcat2.find("option").attr("style","");
        $subcat2.val("");
        
        //var _rel = $cat2.find('option:selected').attr('rel');
        var _val = $(this).find('option:selected').val();
        $.ajax({
        url: "BallotServlet?action=select_contractor&ballotWorkPackageID=" +_val,
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var select = document.getElementById('contractor');
            var option2;
            option2 = document.createElement('option');
            option2.text = 'Please Select';
            option2.value = '';
            select.add(option2);
            $.each(data, function (i, jsondata) {
            for (var i = 0; i < jsondata.length; i++) {
              option2 = document.createElement('option');
              option2.text = jsondata[i].compName;
              option2.value = jsondata[i].contractorID;
              select.add(option2);
            } 
            });
            
        }
    });
        });
    
</script>
 
    
