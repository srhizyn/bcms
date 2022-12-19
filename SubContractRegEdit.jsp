<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | Edit Sub Contract</title>
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
                        Edit Sub Contract
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Edit Sub Contract</li>
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
    
    #contractAmount option{
    display:none;
    }

    #contractAmount option.label{
    display:block;
    }

    

</style>
    <form action="SubContractServlet?action=subcontractreg_update" method="POST" enctype="multipart/form-data" accept-charset='UTF-8'>
        <input type="hidden" name="subContractID" value="${subContractID}"> 
        <div class="panel-group" id="accordion">
             <div class="panel panel-default" id="panel1">
        <div class="panel-heading">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne" class="collapsed">
          View Sub Contract
        </a>
      </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
        <div class="form-group-sm details_div">
            <label for="inputPackage" class="col-sm-3 control-label">Package Code</label>
            <div class="col-sm-8" >
                <select name="contractPackage" id="contractPackage" class="form-control">
                    <option value="">Please Select</option>
                    <c:forEach items="${contract}" var="row">
                    <option value="${row.contractID}">${row.contractPackageCode}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSpecSow" class="col-sm-3 control-label">Scope of Work</label>
            <div class="col-sm-8">
                <textarea rows="5" cols="310" name="scopeWork" class="form-control" placeholder="Scope of Work">${registeredScopeWork}</textarea>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCidbGrade" class="col-sm-3 control-label">CIDB Grade</label>
            <div class="col-sm-8">
                <select name="cidbGrade" id="cidbGrade" class="form-control">
                        <option value="">Please Select</option>
                        <c:forEach items="${cidbGrade}" var="row">
                            <option value="${row.gradeID}" rel="${row.groupingA}">${row.gradeCode}</option>
                        </c:forEach>
                </select>
            </div>
        </div> 
        <div class="form-group-sm">
            <label for="inputSubContractNum" class="col-sm-3 control-label">Reference Number</label>
            <div class="col-sm-8">
                <input type="text" name="subContractNo" id="subContractNo" class="form-control" placeholder="Reference Number" value="${selectedSubContractNo}">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSubContractAmount" class="col-sm-3 control-label">Contract Value</label>
            <div class="col-sm-8">
                <input type="text" name="subContractAmount" id="subContractAmount" class="form-control" placeholder="Contract Value" value="${selectedSubContractValue}">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputRevisedSubContractAmount" class="col-sm-3 control-label">Revised Sub Contract Sum</label>
            <div class="input_fields_wrapEdit2">
                        <div class="">
                            <c:forEach items="${insertedRevisedSubContractValue}" var="row" >
                            <script>
                                var wrapperEdit2 = $(".input_fields_wrapEdit2");
                                $(wrapperEdit2).append('<div class="form-group-sm"><label for="inputRevisedSubContractAmount" class="col-sm-3 control-label"></label><div class="col-sm-8"><input type="text" name="revisedSubContractAmount" value="${row.revisedSubContractAmount}" id="revisedSubContractAmount" class="form-control-static" style="width:400px;" placeholder="No Data">\n\
                                <a href="#" class="remove_fieldEdit2"> Remove</a></div></div>');
                                $(wrapperEdit2).on("click",".remove_fieldEdit2", function(e){ //user click on remove text
                                e.preventDefault(); $(this).parent('div').parent('div').remove(); 
                                });
                            </script>
                            </c:forEach>
                        </div>
            </div>
                <div class="form-group-sm">
                    <div class="input_fields_wrap2">
                        <label for="inputRevisedSubContractAmount" class="col-sm-3 control-label"></label>
                         <div class="col-sm-8">
                             <input type="text" name="revisedSubContractAmount" id="revisedSubContractAmount" class="form-control-static" style="width:400px;" placeholder="Revised Sub Contract Sum">
                             <button class="add_field_button2">Add Amount</button>
                         </div>
                     </div>         
                </div>
        </div>    
        <div class="form-group-sm">
            <label for="inputSubContractPeriod" class="col-sm-3 control-label">Contract Duration</label>
            <div class="col-sm-8">
                <input type="text" name="subContractDate" id="subContractDate" class="form-control-static" style="width:150px;" placeholder="Contract Duration" value="${selectedSubContractDate}">
                 <select name="dateCategory" id="dateCategory" class="form-control-static" style="width:150px;">
                    <option value="">Please Select</option>
                     <c:forEach items="${dateCategory}" var="row">
                    <option value="${row.dateCategoryID}">${row.dateCategoryDesc}</option>
                    </c:forEach>
                </select>
            </div>
        </div> 
        </div>
    </div>
         </div>
        </div>
        <div class="form-group"> 
            <div class="col-sm-offset-1">
                <input type="submit" value="Save Sub Contract" id="SaveContract" class="form-control-static" onclick="return confirm('Confirm to save sub contract?')"/>
                <a href="SubContractServlet?action=SubContractListView" style=""><font color="#000000"><input type="button" class="form-control-static" value="Cancel" style=""/></font></a>
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
        function alertContract()
{
var javascriptVar="${alertcontract}";
if(javascriptVar === 'true'){
    alert("Sub Contract Information Successfully Updated");
    window.location = "SubContractList.jsp";
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
<script language="javascript">
      $(document).ready(function() {
        var max_fields      = 40; //maximum input boxes allowed
        var wrapper         = $(".input_fields_wrap"); //Fields wrapper
        var wrapper2         = $(".input_fields_wrap2"); //Fields wrapper
        var add_button      = $(".add_field_button"); //Add button ID
        var add_button2      = $(".add_field_button2"); //Add button ID

        
        var x2 = 1; //initlal text box count
        $(add_button2).click(function(e){ //on add input button click
            e.preventDefault();
            if(x2 < max_fields){ //max input box allowed
                x2++; //text box increment
                 $(wrapper2).append('<div class="form-group-sm"><label for="inputRevisedSubContractAmount" class="col-sm-3 control-label"></label><div class="col-sm-8"><input type="text" name="revisedSubContractAmount" id="revisedSubContractAmount" class="form-control-static" style="width:400px;" placeholder="Revised Sub Contract Sum"><a href="#" class="remove_field2"> Remove</a></div></div>'); //add input box
        }
    });
    
     $(wrapper2).on("click",".remove_field2", function(e){ //user click on remove text
            e.preventDefault(); $(this).parent('div').parent('div').remove(); x--;
        });
        });
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
        $cat2.on("change",function(){
        var _rel2 = $(this).find('option:selected').val();
        
        $subcat6.find("option").attr("style","");
        $subcat6.val("");
        $subcat7.find("option").attr("style","");
        $subcat7.val("");
        
        if(!_rel2) return $subcat6.prop("disabled",true);
        $subcat6.find("[rel="+_rel2+"]").show();
        $subcat6.prop("disabled",false);
        if(!_rel2) return $subcat7.prop("disabled",true);
        $subcat7.find("[rel="+_rel2+"]").show();
        $subcat7.prop("disabled",false);
        
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
            });
        });
    </script>
    <script>
    function setSelectedIndex3(s3, valsearch3)
    {
    // Loop through all the items in drop down list
    for (i = 0; i< s3.options.length; i++)
    { 
    if (s3.options[i].value === valsearch3)
    {
    // Item is found. Set its property and exit
    s3.options[i].selected = true;
    break;
    }
    }
    return;
    }
    setSelectedIndex3(document.getElementById("contractPackage"),"${selectedContractID}");
    </script>
    <script>
    function setSelectedIndex4(s4, valsearch4)
    {
    // Loop through all the items in drop down list
    for (i = 0; i< s4.options.length; i++)
    { 
    if (s4.options[i].value === valsearch4)
    {
    // Item is found. Set its property and exit
    s4.options[i].selected = true;
    break;
    }
    }
    return;
    }
    setSelectedIndex4(document.getElementById("dateCategory"),"${selectedDateCategoryID}");
    </script>
    <script>
    function setSelectedIndex5(s5, valsearch5)
    {
    // Loop through all the items in drop down list
    for (i = 0; i< s5.options.length; i++)
    { 
    if (s5.options[i].value === valsearch5)
    {
    // Item is found. Set its property and exit
    s5.options[i].selected = true;
    break;
    }
    }
    return;
    }
    setSelectedIndex5(document.getElementById("cidbGrade"),"${selectedCidbGradeID}");
    </script>
    </html>