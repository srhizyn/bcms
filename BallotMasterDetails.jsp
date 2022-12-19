<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | Ballot Master Details</title>
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
                        ${ballotTitle}
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
    
    

</style>
    <form action="BallotServlet?action=ballotmasterdetails_create"  method="POST" enctype="multipart/form-data" accept-charset='UTF-8'>
        
        <input type="hidden" name="ballotMasterID" value="${ballotMasterID}"> 
        <div class="panel-group" id="accordion">
             <div class="panel panel-default" id="panel1">
        <div class="panel-heading">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne" class="collapsed">
          Ballot Session Details
        </a>
      </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
        <div class="form-group-sm">
            <label for="inputCidbGrade" class="col-sm-3 control-label">CIDB Grade</label>
                <div class="col-sm-8">
                    <select name="cidbGrade" id="cidbGrade" class="form-control" >
                        <option value="">Please Select</option>
                        <c:forEach items="${cidbGrade}" var="row">
                            <option value="${row.gradeID}">${row.gradeCode}</option>
                        </c:forEach>
                    </select>
                </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSpecialisation" class="col-sm-3 control-label">Specialisation</label>
                <div class="col-sm-8">
                    <select name="specialisation" id="specialisation" class="form-control" multiple size="3">
                        <option value="">Please Select</option>
                        <c:forEach items="${specialisation}" var="row">
                            <option value="${row.specialisationID}">${row.specialisationCode}</option>
                        </c:forEach>
                    </select>
                </div>
        </div> 
        <div class="form-group-sm">
            <label for="inputSpkkSpec" class="col-sm-3 control-label">SPKK Specialisation</label>
                <div class="col-sm-8">
                    <select name="spkkSpec" id="spkkSpec" class="form-control" multiple size="3">
                        <option value="">Please Select</option>
                        <c:forEach items="${spkkSpec}" var="row">
                            <option value="${row.spkkSpecID}">${row.spkkSpecCode}</option>
                        </c:forEach>
                    </select>
                </div>
        </div>
        
        <div class="form-group-sm">
            <label for="inputAdd" class="col-sm-3 control-label"></label>
            <div class="col-sm-8">
                <input type="submit" value="Add" id="Add" class="form-control-static" onclick="return confirm('Add?')"/>
            </div>
        </div>
        </div>
        </div>
    </div>
         </div>
        
    </form>
    <div id="BallotMasterDetailsTableContainer"></div>
    <script type="text/javascript">
    $(document).ready(function () {
        $('#BallotMasterDetailsTableContainer').jtable({
            title: 'Ballot Session Details',
            openChildAsAccordion: true,
            paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10) 
            columnSelectable: false,
            columnResizable:false,
            //sorting: true,
            defaultSorting: 'ballotmasterdetails_id desc',
            //selecting: true, //Enable selecting
            //selectingCheckboxes: true, //Show checkboxes on first column
            selectOnRowClick: false, //Enable this to only select using checkboxes
            actions: {
                listAction: 'BallotServlet?action=ballotmasterdetails_list&ballotMasterID='+ ${ballotMasterID},
                deleteAction: 'BallotServlet?action=ballotmasterdetails_delete'
                //createAction:'FormServlet?action=form_create',
                //updateAction: 'ContractorServlet?action=contractor_update',
                
                
            },
            fields: {
                ballotWorkPackageID:{
                    key: true,
                    list:false
                    
                },
                rowNumber:{
                    title:'#',
                    width: '2%',
                    list: true,
                    create:false,
                    edit:false
                },
                gradeCode:{
                    title:'CIDB Grade',
                    width: '10%',
                    list: true,
                    create:false,
                    edit:false
                },
                specialisationCode:{
                    title:'Specialisation',
                    width: '10%',
                    list: true,
                    create:false,
                    edit:false
                },
                spkkSpecCode:{
                    title:'SPKK Spec',
                    width: '25%',
                    list: true,
                    create:false,
                    edit:false
                }
            }  
        });
        
        //Load all records when page is first shown
        //$('#LoadRecordsButton').click();
        $('#BallotMasterDetailsTableContainer').jtable('load');
        
    });
    
</script>
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
<%--<script>
        function alertMattersTracking()
{
var javascriptVar="${alertmatterstracking}";
if(javascriptVar === 'true'){
    alert("Matters Tracking Successfully Updated");
    window.location = "fwdMattersTrackingReg.jsp";
    return true;
    }
    else if (javascriptVar === 'false') {
    alert("An Error Occured. Please Try Again");   
    return false;
    }
    
}
    </script>--%>
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
    <script language="javascript">
      $(document).ready(function() {
    var max_fields      = 40; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID
    
    var max_fields2      = 40; //maximum input boxes allowed
    var wrapper2         = $(".input_fields_wrap2"); //Fields wrapper
    var add_button2      = $(".add_field_button2"); //Add button ID
    
    
    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div class="form-group-sm"><label for="inputPDPRemarks" class="col-sm-3 control-label"></label><div class="col-sm-8"><input type="text" name="PDPRemarks" id="PDPRemarks" class="form-control-static" style="width:550px;" placeholder="PDP Remarks"><a href="#" class="remove_field"> Remove</a></div></div>');
        }
    });
    
    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent('div').parent('div').remove(); x--;
    });
    
    var x2 = 1; //initlal text box count
    $(add_button2).click(function(e2){ //on add input button click
        e2.preventDefault();
        if(x2 < max_fields2){ //max input box allowed
            x2++; //text box increment
             $(wrapper2).append('<div class="form-group-sm"><label for="inputMRTRemarks" class="col-sm-3 control-label"></label><div class="col-sm-9"><input type="text" name="MRTRemarks" id="MRTRemarks" class="form-control-static" style="width:550px;" placeholder="MRT Remarks"><a href="#" class="remove_field2"> Remove</a></div></div>'); //add input box
        }
    });
    
    $(wrapper2).on("click",".remove_field2", function(e2){ //user click on remove text
        e2.preventDefault(); $(this).parent('div').parent('div').remove(); x2--;
    });
 
});
      
    </script>
<script>
        function alertBallot()
{
var javascriptVar="${alertballot}";
if(javascriptVar === 'true'){
    alert("Ballot Master Details Information Successfully Added");
    //window.location = "fwdBallotMaster.jsp";
    return true;
    }
    else if (javascriptVar === 'false') {
    alert("An Error Occured. Please Try Again");   
    return false;
    }
    
}
</script>
<%--<script>
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
    setSelectedIndex3(document.getElementById("contractAward"),"${selectedContractAwardID}");
    </script>--%>