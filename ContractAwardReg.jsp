<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | New Contract Award</title>
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
    <body class="skin-blue" onload="alertContract()">
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
                        Contract Award
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Contract Award</li>
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
    #dateBallot option{
    display:none;
    }

    #dateBallot option.label{
    display:block;
    }
    
    #datePerformaAccepted option{
    display:none;
    }

    #datePerformaAccepted option.label{
    display:block;
    }
    
    #ballotStatus option{
    display:none;
    }

    #ballotStatus option.label{
    display:block;
    }
    
    #wpc option{
    display:none;
    }

    #wpc option.label{
    display:block;
    }
    
    #contractPackage option{
    display:none;
    }

    #contractPackage option.label{
    display:block;
    }
    
    #contractAmount option{
    display:none;
    }

    #contractAmount option.label{
    display:block;
    }
    
    #scopeOfWork option{
    display:none;
    }

    #scopeOfWork option.label{
    display:block;
    }
    
    #contractor option{
    display:none;
    }

    #contractor option.label{
    display:block;
    }
    
    
    
</style>
    <form action="ContractAwardServlet?action=contractawardreg_create"  method="POST" enctype="multipart/form-data" accept-charset='UTF-8'>
         <div class="panel-group" id="accordion">
             <div class="panel panel-default" id="panel1">
        <div class="panel-heading bg-light-blue">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne" class="collapsed">
          Contract Award Registration
        </a>
      </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
        
        <div class="form-group-sm details_div">
            <label for="inputPackage" class="col-sm-3 control-label">Package Code</label>
            <div class="col-sm-8" >
                <select name="contract" id="contract" class="form-control">
                    <option value="">Please Select</option>
                    <c:forEach items="${contract}" var="row">
                    <option value="${row.contractID}" rel="${row.contractID}">${row.contractPackageCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm details_div">
            <label for="inputWpc" class="col-sm-3 control-label">WPC</label>
            <div class="col-sm-8" >
                <select name="wpc" id="wpc" multiple="multiple" class="form-control" size="1" readonly>
                    <c:forEach items="${contract}" var="row">
                    <option value="${row.wpc}" rel="${row.contractID}">${row.wpc}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputScopeOfWork" class="col-sm-3 control-label">Scope of Work</label>
        <div class="col-sm-8">
            <select name="scopeOfWork" id="scopeOfWork" class="form-control" disabled="disabled">
                        <option value=""></option>
                        <c:forEach items="${scopeOfWork}" var="row">
                            <option value="${row.subContractID}" rel="${row.contractID}">${row.scopeWork}</option>
                        </c:forEach>
                    </select>
        </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCidbGradeCategory" class="col-sm-3 control-label">CIDB Grade Category</label>
            <div class="col-sm-8">
                <select name="cidbGradeCategory" id="cidbGradeCategory" class="form-control">
                      <option value="">Please Select</option>
                      <c:forEach items="${cidbGradeCategory}" var="row">
                      <option value="${row.cidbGradeCategoryID}" rel="${row.cidbGradeCategoryID}">${row.cidbGradeCategoryDesc}</option>
                      </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputContractor" class="col-sm-3 control-label">Contractor</label>
            <div class="col-sm-8">
                <select name="contractor" id="contractor" class="form-control">
                      <option value="">Please Select</option>
                      <c:forEach items="${contractor}" var="row">
                      <option value="${row.contractorID}" rel="${row.cidbGradeCategoryID}">${row.companyName}</option>
                      </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm details_div">
            <label for="inputBallotDate" class="col-sm-3 control-label">Ballot Date</label>
            <div class="col-sm-8" >
                <select name="dateBallot" id="dateBallot" multiple="multiple" class="form-control" size="1" readonly>
                    <c:forEach items="${contractor}" var="row">
                    <option value="${row.dateBallot}" rel="${row.contractorID}">${row.dateBallot}</option>
                    </c:forEach>
                </select>
                
            </div>
            
        </div>
        <div class="form-group-sm">
            <label for="inputPerformaAcceptedDate" class="col-sm-3 control-label">Pernyataan Persetujuan dan Akujanji</label>
            <div class="col-sm-8">
                <input type="date" name="performaAcceptedDate" id="performaAcceptedDate" class="form-control" min="2000-01-01" max="2040-12-31">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputLoaDate" class="col-sm-3 control-label">LOA/WO Date</label>
            <div class="col-sm-8">
                <input type="date" name="loaDate" id="loaDate" class="form-control" min="2000-01-01" max="2040-12-31">
            </div>
        </div> 
        <div class="form-group-sm">
            <label for="inputCommencementDate" class="col-sm-3 control-label">Commencement Date</label>
            <div class="col-sm-8">
                <input type="date" name="commencementDate" id="commencementDate" class="form-control" min="2000-01-01" max="2040-12-31">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCompletionDate" class="col-sm-3 control-label">Completion Date</label>
            <div class="col-sm-8">
                <input type="date" name="completionDate" id="completionDate" class="form-control" min="2000-01-01" max="2040-12-31">
            </div>
        </div> 
        <div class="form-group-sm">
            <label for="inputActualCompletionDate" class="col-sm-3 control-label">Actual Completion Date</label>
            <div class="col-sm-8">
                <input type="date" name="actualCompletionDate" id="actualCompletionDate" class="form-control" min="2000-01-01" max="2040-12-31">
            </div>
        </div> 
        <div class="form-group-sm">
            <label for="inputProgressAuditDate" class="col-sm-3 control-label">Audit Date</label>
            <div class="col-sm-8">
                <input type="date" name="progressAuditDate" id="progressAuditDate" class="form-control" min="2000-01-01" max="2040-12-31">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputFinalAccountDate" class="col-sm-3 control-label">Final Account Date</label>
            <div class="col-sm-8">
                <input type="date" name="finalAccountDate" id="finalAccountDate" class="form-control" min="2000-01-01" max="2040-12-31">
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputContractAwardRemarks" class="col-sm-3 control-label">Remarks</label>
            <div class="col-sm-8">
                <textarea rows="5" cols="110" name="contractAwardRemarks" class="form-control" placeholder="Remarks"></textarea>
            </div>
        </div>--%>
        </div>
        </div>
    </div>
         </div>
        <div class="form-group"> 
            <div class="col-sm-offset-1">
                <input type="submit" value="Register Contract Award" id="AddContractAward" class="form-control-static" onclick="return isValidDate();return confirm('Confirm to register contract award?');"/>
                <input type="reset" value="Reset" id="ResetContractAward" class="form-control-static"/>
                <a href="ContractAwardRegList.jsp" style=""><font color="#000000"><input type="button" class="form-control-static" value="Cancel" style=""/></font></a>
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
        function alertContract()
{
var javascriptVar="${alertcontract}";
if(javascriptVar === 'true'){
    alert("Contract Award Information Successfully Added");
    window.location = "fwdContractAwardReg.jsp";
    return true;
    }
    else if (javascriptVar === 'false') {
    alert("An Error Occured. Please Try Again");   
    return false;
    }
    
}
    </script>
<script type="text/javascript">
function isValidDate(dateFrom, dateTo) {
    var fromdate, todate, dt1, dt2, mon1, mon2, yr1, yr2, date1, date2;
    var chkFrom = document.getElementById("contractPeriodFrom");
    var chkTo = document.getElementById("contractPeriodTo");
    if (chkFrom !== null && chkTo !== null) {
    
        fromdate = chkFrom.value;
        todate = chkTo.value;
        date1 = new Date(fromdate);
        date2 = new Date(todate);
 
        if (date2 < date1) {
          alert("To date should be greater than from date");
          chkFrom.value = '';
          chkFrom.focus();
          return false;
        }
      
    }
    return true;
  }
  
   function checkdate(input) {
    var validformat = /^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/; //Basic check for format validity
    var returnval = true;
    if (input.value === '' || !validformat.test(input.value))
    {
      alert("Invalid date format. Please correct and submit again.");
      var returnval = false;
    }
    return returnval;
  }

</script>
<script>
        $(function(){
        var $cat = $("#contractor"),
        $subcat = $("#dateBallot");
        $subcat2 = $("#datePerformaAccepted");
        $subcat3 = $("#ballotStatus");
        $cat.on("change",function(){
        var _rel = $(this).find('option:selected').val();
        
        $subcat.find("option").attr("style","");
        $subcat.val("");
        $subcat2.find("option").attr("style","");
        $subcat2.val("");
        $subcat3.find("option").attr("style","");
        $subcat3.val("");
        
        if(!_rel) return $subcat.prop("disabled",true);
        $subcat.find("[rel="+_rel+"]").show();
        $subcat.prop("disabled",false);
        
        if(!_rel) return $subcat2.prop("disabled",true);
        $subcat2.find("[rel="+_rel+"]").show();
        $subcat2.prop("disabled",false);
        
        if(!_rel) return $subcat3.prop("disabled",true);
        $subcat3.find("[rel="+_rel+"]").show();
        $subcat3.prop("disabled",false);
            });
        });
    </script>
<script>
        $(function(){
        var $cat2 = $("#contract"),
        $subcat6 = $("#wpc");
        $subcat7 = $("#contractPackage");
        $subcat8 = $("#contractAmount");
        $subcat9 = $("#scopeOfWork");
        $cat2.on("change",function(){
        var _rel2 = $(this).find('option:selected').val();
        
        $subcat6.find("option").attr("style","");
        $subcat6.val("");
        $subcat7.find("option").attr("style","");
        $subcat7.val("");
        $subcat8.find("option").attr("style","");
        $subcat8.val("");
        $subcat9.find("option").attr("style","");
        $subcat9.val("");
        
        if(!_rel2) return $subcat6.prop("disabled",true);
        $subcat6.find("[rel="+_rel2+"]").show();
        $subcat6.prop("disabled",false);
        if(!_rel2) return $subcat7.prop("disabled",true);
        $subcat7.find("[rel="+_rel2+"]").show();
        $subcat7.prop("disabled",false);
        if(!_rel2) return $subcat8.prop("disabled",true);
        $subcat8.find("[rel="+_rel2+"]").show();
        $subcat8.prop("disabled",false);
        if(!_rel2) return $subcat9.prop("disabled",true);
        $subcat9.find("[rel="+_rel2+"]").show();
        $subcat9.prop("disabled",false);
            });
        });
    </script>
<script>
        $(function(){
        var $cat = $("#ResetContractAward"),
        $subcat = $("#dateBallot");
        $subcat2 = $("#datePerformaAccepted");
        $subcat3 = $("#ballotStatus");
        $subcat4 = $("#wpc");
        $subcat5 = $("#contractPackage");
        $subcat6 = $("#scopeOfWork");
        $cat.on("click",function(){
        $subcat.find("option").attr("style","");
        $subcat.val("");
        $subcat2.find("option").attr("style","");
        $subcat2.val("");
        $subcat3.find("option").attr("style","");
        $subcat3.val("");
        $subcat4.find("option").attr("style","");
        $subcat4.val("");
        $subcat5.find("option").attr("style","");
        $subcat5.val("");
        $subcat6.find("option").attr("style","");
        $subcat6.val("");
        //$subcat6.prop("disabled",true);
            });
        });
    </script>
<script>
        $(function(){
        var $cat = $("#cidbGradeCategory"),
        $subcat = $("#contractor");
        $cat.on("change",function(){
        var _rel = $(this).find('option:selected').attr('rel');
        $subcat.find("option").attr("style","");
        $subcat.val("");
        if(!_rel) return $subcat.prop("disabled",true);
        $subcat.find("[rel="+_rel+"]").show();
        $subcat.prop("disabled",false);
            });
        });
    </script>