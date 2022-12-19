<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | Edit Contract</title>
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
                        Contract
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Contract</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                     <div id="show" align="center"></div>
<div>
<style type="text/css">     
    .selectoption {
        width:400px;
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
    
    table {
    border-collapse: collapse;
    }

    table, th, td {
    border: 1px solid lightgray;
    }
    
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
    /* display: none; <- Crashes Chrome on hover */
    -webkit-appearance: none;
    margin: 0; /* <-- Apparently some margin are still there even though it's hidden */
}

</style>
    <form action="ContractServlet?action=contractreg_update"  method="POST" enctype="multipart/form-data" accept-charset='UTF-8'>
        <input type="hidden" name="contractID" value="${contractID}">
        <div class="panel-group" id="accordion">
             <div class="panel panel-default" id="panel1">
        <div class="panel-heading">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne" class="collapsed">
          Contract Edit
        </a>
      </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
         <div class="form-group-sm">
            <label for="inputWPC" class="col-sm-3 control-label">WPC</label>
            <div class="col-sm-8">
                <select name="wpc" id="wpc" class="form-control">
                        <c:forEach items="${wpc}" var="row">
                        <option value="${row.wpcID}">${row.wpcName}</option>
                         </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputPackage" class="col-sm-3 control-label">Package</label>
            <div class="col-sm-8">
                <textarea rows="5" cols="110" name="package" class="form-control" placeholder="Package">${contractPackage}</textarea>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputPackageCode" class="col-sm-3 control-label">Package Code</label>
            <div class="col-sm-8">
                <input type="text" name="packageCode" id="packageCode" class="form-control" placeholder="Package Code" value="${contractPackageCode}">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputContractNum" class="col-sm-3 control-label">Contract Number</label>
            <div class="col-sm-8">
                <input type="text" name="contractNo" id="contractNo" class="form-control" placeholder="Contract Number" value="${contractNo}">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputContractAmount" class="col-sm-3 control-label">Contract Sum</label>
            <div class="col-sm-8">
                <input type="text" name="contractAmount" id="contractAmount" class="form-control" placeholder="Contract Sum" value="${contractAmount}">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputRevisedContractAmount" class="col-sm-3 control-label">Revised Contract Sum</label>
            <div class="input_fields_wrapEdit">
                        <div class="">
                            <c:forEach items="${insertedRevisedContractAmount}" var="row" >
                            <script>
                                var wrapperEdit = $(".input_fields_wrapEdit");
                                $(wrapperEdit).append('<div class="form-group-sm"><label for="inputRevisedContractAmount" class="col-sm-3 control-label"></label><div class="col-sm-8"><input type="text" name="revisedContractAmount" value="${row.revisedContractAmount}" id="revisedContractAmount" class="form-control-static" style="width:400px;" placeholder="No Data">\n\
                                <a href="#" class="remove_fieldEdit"> Remove</a></div></div>');
                                $(wrapperEdit).on("click",".remove_fieldEdit", function(e){ //user click on remove text
                                e.preventDefault(); $(this).parent('div').parent('div').remove(); 
                                });
                            </script>
                            </c:forEach>
                        </div>
            </div>
                <div class="form-group-sm">
                    <div class="input_fields_wrap">
                        <label for="inputRevisedContractAmount" class="col-sm-3 control-label"></label>
                         <div class="col-sm-8">
                             <input type="text" name="revisedContractAmount" id="revisedContractAmount" class="form-control-static" style="width:400px;" placeholder="Revised Contract Sum">
                             <button class="add_field_button">Add Amount</button>
                         </div>
                     </div>         
                </div>
        </div>    
        <div class="form-group-sm">
            <label for="inputContractPeriod" class="col-sm-3 control-label">Contract Period</label>
            <div class="col-sm-8">
                <input type="date" name="contractPeriodFrom" id="contractPeriodFrom" class="form-control-static" required="required" min="2000-01-01" max="2040-12-31" value="${contractPeriodFrom}"> -
                <input type="date" name="contractPeriodTo" id="contractPeriodTo" class="form-control-static" required="required" min="2000-01-01" max="2040-12-31" value="${contractPeriodTo}">
            </div>
        </div>  
        <div class="form-group-sm">
            <label class="col-sm-3 control-label"></label>
            <div class="col-sm-8">
                
            </div>
        </div> 
        <div class="form-group-sm">
            <label class="col-sm-3 control-label"></label>
            <div class="col-sm-8">
                
            </div>
        </div>
        <div class="form-group-sm">       
      <div id="table">
      <table class="table">
      <tr>
        <th rowspan="2" style="text-align:center;">Grade</th>
        <th colspan="2" style="text-align:center;">Requirement</th>
        <th colspan="2" style="text-align:center;">Proposal</th>
      </tr>
      <tr>
        <td align="center">Nos</td>
        <td align="center">Values</td>
        <td align="center">Nos</td>
        <td align="center">Values</td>
      </tr>
      <tr>
        <td>G1</td>
        <td align="center"><input type="number" min="0" name="g1ReqNos" id="g1ReqNos" class="toAddSum toAddSum9" onkeyup="AddInputsSum();AddInputsSum9();" value="${g1ReqNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g1ReqAmt" id="g1ReqAmt" class="toAddSum2 toAddSum10" onkeyup="AddInputsSum2();AddInputsSum10();" value="${g1ReqAmt}"></td>
        <td align="center"><input type="number" min="0" name="g1ProposalNos" id="g1ProposalNos" class="toAddSum3 toAddSum11" onkeyup="AddInputsSum3();AddInputsSum11();" value="${g1ProposalNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g1ProposalAmt" id="g1ProposalAmt" class="toAddSum4 toAddSum12" onkeyup="AddInputsSum4();AddInputsSum12();" value="${g1ProposalAmt}"></td>
      </tr>
      <tr>
        <td>G2</td>
        <td align="center"><input type="number" min="0" name="g2ReqNos" id="g2ReqNos" class="toAddSum toAddSum9" onkeyup="AddInputsSum();AddInputsSum9();" value="${g2ReqNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g2ReqAmt" id="g2ReqAmt" class="toAddSum2 toAddSum10" onkeyup="AddInputsSum2();AddInputsSum10();" value="${g2ReqAmt}"></td>
        <td align="center"><input type="number" min="0" name="g2ProposalNos" id="g2ProposalNos" class="toAddSum3 toAddSum11" onkeyup="AddInputsSum3();AddInputsSum11();" value="${g2ProposalNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g2ProposalAmt" id="g2ProposalAmt" class="toAddSum4 toAddSum12" onkeyup="AddInputsSum4();AddInputsSum12();" value="${g2ProposalAmt}"></td>
      </tr>
      <tr>
        <td>G3</td>
        <td align="center"><input type="number" min="0" name="g3ReqNos" id="g3ReqNos" class="toAddSum toAddSum9" onkeyup="AddInputsSum();AddInputsSum9();" value="${g3ReqNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g3ReqAmt" id="g3ReqAmt" class="toAddSum2 toAddSum10" onkeyup="AddInputsSum2();AddInputsSum10();" value="${g3ReqAmt}"></td>
        <td align="center"><input type="number" min="0" name="g3ProposalNos" id="g3ProposalNos" class="toAddSum3 toAddSum11" onkeyup="AddInputsSum3();AddInputsSum11();" value="${g3ProposalNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g3ProposalAmt" id="g3ProposalAmt" class="toAddSum4 toAddSum12" onkeyup="AddInputsSum4();AddInputsSum12();" value="${g3ProposalAmt}"></td>
      </tr>
      <tr>
        <td>G4</td>
        <td align="center"><input type="number" min="0" name="g4ReqNos" id="g4ReqNos" class="toAddSum toAddSum9" onkeyup="AddInputsSum();AddInputsSum9();" value="${g4ReqNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g4ReqAmt" id="g4ReqAmt" class="toAddSum2 toAddSum10" onkeyup="AddInputsSum2();AddInputsSum10();" value="${g4ReqAmt}"></td>
        <td align="center"><input type="number" min="0" name="g4ProposalNos" id="g4ProposalNos" class="toAddSum3 toAddSum11" onkeyup="AddInputsSum3();AddInputsSum11();" value="${g4ProposalNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g4ProposalAmt" id="g4ProposalAmt" class="toAddSum4 toAddSum12" onkeyup="AddInputsSum4();AddInputsSum12();" value="${g4ProposalAmt}"></td>
      </tr>
      <tr>
        <td>Total</td>
        <td align="center"><input type="number" min="0" name="g1g4ReqNos" id="g1g4ReqNos" placeholder="Total" readonly value="${g1g4ReqNos}"></td>
        <td align="center"><input type="number" min="0" name="g1g4ReqAmt" id="g1g4ReqAmt" placeholder="Total" readonly value="${g1g4ReqAmt}"></td>
        <td align="center"><input type="number" min="0" name="g1g4ProposalNos" id="g1g4ProposalNos" placeholder="Total" readonly value="${g1g4ProposalNos}"></td>
        <td align="center"><input type="number" min="0" name="g1g4ProposalAmt" id="g1g4ProposalAmt" placeholder="Total" readonly value="${g1g4ProposalAmt}"></td>
        
      </tr>
      <tr>
        <td>G5</td>
        <td align="center"><input type="number" min="0" name="g5ReqNos" id="g5ReqNos" class="toAddSum5 toAddSum9" onkeyup="AddInputsSum5();AddInputsSum9();" value="${g5ReqNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g5ReqAmt" id="g5ReqAmt" class="toAddSum6 toAddSum10" onkeyup="AddInputsSum6();AddInputsSum10();" value="${g5ReqAmt}"></td>
        <td align="center"><input type="number" min="0" name="g5ProposalNos" id="g5ProposalNos" class="toAddSum7 toAddSum11" onkeyup="AddInputsSum7();AddInputsSum11();" value="${g5ProposalNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g5ProposalAmt" id="g5ProposalAmt" class="toAddSum8 toAddSum12" onkeyup="AddInputsSum8();AddInputsSum12();" value="${g5ProposalAmt}"></td>
      </tr>
      <tr>
        <td>G6</td>
        <td align="center"><input type="number" min="0" name="g6ReqNos" id="g6ReqNos" class="toAddSum5 toAddSum9" onkeyup="AddInputsSum5();AddInputsSum9();" value="${g6ReqNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g6ReqAmt" id="g6ReqAmt" class="toAddSum6 toAddSum10" onkeyup="AddInputsSum6();AddInputsSum10();" value="${g6ReqAmt}"></td>
        <td align="center"><input type="number" min="0" name="g6ProposalNos" id="g6ProposalNos" class="toAddSum7 toAddSum11" onkeyup="AddInputsSum7();AddInputsSum11();" value="${g6ProposalNos}"></td>
        <td align="center"><input type="number" step=".01" min="0" name="g6ProposalAmt" id="g6ProposalAmt" class="toAddSum8 toAddSum12" onkeyup="AddInputsSum8();AddInputsSum12();" value="${g6ProposalAmt}"></td>
      </tr>
      <tr>
        <td>Total</td>
        <td align="center"><input type="number" min="0" name="g5g6ReqNos" id="g5g6ReqNos" placeholder="Total" readonly value="${g5g6ReqNos}"></td>
        <td align="center"><input type="number" min="0" name="g5g6ReqAmt" id="g5g6ReqAmt" placeholder="Total" readonly value="${g5g6ReqAmt}"></td>
        <td align="center"><input type="number" min="0" name="g5g6ProposalNos" id="g5g6ProposalNos" placeholder="Total" readonly value="${g5g6ProposalNos}"></td>
        <td align="center"><input type="number" min="0" name="g5g6ProposalAmt" id="g5g6ProposalAmt" placeholder="Total" readonly value="${g5g6ProposalAmt}"></td> 
      </tr>
      <tr>
        <td>Grand Total</td>
        <td align="center"><input type="number" min="0" name="g1g6ReqNos" id="g1g6ReqNos" placeholder="Grand Total" readonly value="${g1g6ReqNos}"></td>
        <td align="center"><input type="number" min="0" name="g1g6ReqAmt" id="g1g6ReqAmt" placeholder="Grand Total" readonly value="${g1g6ReqAmt}"></td>
        <td align="center"><input type="number" min="0" name="g1g6ProposalNos" id="g1g6ProposalNos" placeholder="Grand Total" readonly value="${g1g6ProposalNos}"></td>
        <td align="center"><input type="number" min="0" name="g1g6ProposalAmt" id="g1g6ProposalAmt" placeholder="Grand Total" readonly value="${g1g6ProposalAmt}"></td> 
        </tr>
        </table>
        </div>
        </div>    
            
        </div>
        </div>
    </div>
         </div>
        <div class="form-group"> 
            <div class="col-sm-offset-1">
                <input type="submit" value="Save Contract" id="SaveContract" class="form-control-static" onclick="return confirm('Confirm to save contract?')"/>
                <a href="ContractRegList.jsp" style=""><font color="#000000"><input type="button" class="form-control-static" value="Cancel" style=""/></font></a>
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
    alert("Contract Information Successfully Added");
    window.location = "ContractRegList.jsp";
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
    var add_button    = $(".add_field_button"); //Add button ID
    
   
    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
             $(wrapper).append('<div class="form-group-sm"><label for="inputRevisedContractAmount" class="col-sm-3 control-label"></label><div class="col-sm-8"><input type="text" name="revisedContractAmount" id="revisedContractAmount" class="form-control-static" style="width:400px;" placeholder="Revised Contract Sum"><a href="#" class="remove_field"> Remove</a></div></div>'); //add input box
        }
    });
    
    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent('div').parent('div').remove(); x--;
    });
    
  
});
      
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
    setSelectedIndex3(document.getElementById("wpc"),"${selectedWpcID}");
    </script>
    <script>
    function AddInputsSum(){
    var elems = document.getElementsByClassName('toAddSum');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseInt(ele.value);
        newTotal = total.toFixed(0);
    }

document.getElementById('g1g4ReqNos').value = newTotal;
    }
</script>
<script>
    function AddInputsSum2(){
    var elems = document.getElementsByClassName('toAddSum2');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseFloat(ele.value);
        newTotal = total.toFixed(2);
    }

document.getElementById('g1g4ReqAmt').value = newTotal;
    }
</script>
<script>
    function AddInputsSum3(){
    var elems = document.getElementsByClassName('toAddSum3');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseInt(ele.value);
        newTotal = total.toFixed(0);
    }

document.getElementById('g1g4ProposalNos').value = newTotal;
    }
</script>
<script>
    function AddInputsSum4(){
    var elems = document.getElementsByClassName('toAddSum4');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseFloat(ele.value);
        newTotal = total.toFixed(2);
    }

document.getElementById('g1g4ProposalAmt').value = newTotal;
    }
</script>
<script>
    function AddInputsSum5(){
    var elems = document.getElementsByClassName('toAddSum5');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseInt(ele.value);
        newTotal = total.toFixed(0);
    }

document.getElementById('g5g6ReqNos').value = newTotal;
    }
</script>
<script>
    function AddInputsSum6(){
    var elems = document.getElementsByClassName('toAddSum6');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseFloat(ele.value);
        newTotal = total.toFixed(2);
    }

document.getElementById('g5g6ReqAmt').value = newTotal;
    }
</script>
<script>
    function AddInputsSum7(){
    var elems = document.getElementsByClassName('toAddSum7');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseInt(ele.value);
        newTotal = total.toFixed(0);
    }

document.getElementById('g5g6ProposalNos').value = newTotal;
    }
</script>
<script>
    function AddInputsSum8(){
    var elems = document.getElementsByClassName('toAddSum8');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseFloat(ele.value);
        newTotal = total.toFixed(2);
    }

document.getElementById('g5g6ProposalAmt').value = newTotal;
    }
</script>
<script>
    function AddInputsSum9(){
    var elems = document.getElementsByClassName('toAddSum9');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseInt(ele.value);
        newTotal = total.toFixed(0);
    }

document.getElementById('g1g6ReqNos').value = newTotal;
    }
</script>
<script>
    function AddInputsSum10(){
    var elems = document.getElementsByClassName('toAddSum10');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseFloat(ele.value);
        newTotal = total.toFixed(2);
    }

document.getElementById('g1g6ReqAmt').value = newTotal;
    }
</script>
<script>
    function AddInputsSum11(){
    var elems = document.getElementsByClassName('toAddSum11');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseInt(ele.value);
        newTotal = total.toFixed(0);
    }

document.getElementById('g1g6ProposalNos').value = newTotal;
    }
</script>
<script>
    function AddInputsSum12(){
    var elems = document.getElementsByClassName('toAddSum12');

    var myLength = elems.length,
    total = 0;
    for ( var i = 0; i<myLength; i++)
    {
        var ele = elems[i];
        total += parseFloat(ele.value);
        newTotal = total.toFixed(2);
    }

document.getElementById('g1g6ProposalAmt').value = newTotal;
    }
</script>