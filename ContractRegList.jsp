<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>
    <head>
        <meta charset="UTF-8">
        <title>BCMS | Contract List</title>
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
        <link href="css/metro/lightgray/jtable.css" rel="stylesheet" type="text/css" />
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
                        Contract Records
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Contract Records</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                     <div id="show" align="center"></div>
<div style="width:100%;text-align:left;">
<style type="text/css">     
    .selectoption {
        width:310px;
    }
    select {
        color:black;
    }
    .aligncenter
    {
        text-align: center;
    }
    #searchSpecialisationCode option{
    display:none;
    }

    #searchSpecialisationCode option.label{
    display:block;
    }
    .selectoption {
        width:500px;
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
    .container { border:1px solid #ccc; width:500px; height: 140px; overflow-y: scroll; }
    #specialisationChkBox option{
    display:none;
    }

    #specialisationChkBox option.label{
    display:block;
    }
    
    .panel-heading a:after {
    font-family:'Glyphicons Halflings';
    content:"\e114";
    float: right;
    color: lightblue;
    }
    .panel-heading a.collapsed:after {
        content:"\e080";
    }
</style> 
    <div class="filtering">
        <form>
    <div class="panel-group" id="accordion">
        <div class="panel panel-default" id="panel1">
            <div class="panel-heading">
             <h4 class="panel-title">
        <a>
          Search
        </a>
      </h4>
        </div>
        <div id="collapseOne">
        <div class="panel-body">
        <div class="form-group-sm">
            <label for="inputSearchWpc" class="col-sm-3 control-label">WPC</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="searchWpc" id="searchWpc" class="form-control" placeholder="Search WPC">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSearchPackage" class="col-sm-3 control-label">Package</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="searchPackage" id="searchPackage" class="form-control" placeholder="Search Package">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSearchContractNo" class="col-sm-3 control-label">Contract No</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="searchContractNo" id="searchContractNo" class="form-control" placeholder="Search Contract No">
            </div>
        </div>
        <div class="form-group-sm">
            <label class="col-sm-3 control-label"></label>
            <div class="col-sm-8"><button type="submit" id="LoadRecordsButton">Load records</button></div>
        </div>
        </div>
        </div>
        </div> 
    </div>
                </form>

    </div>
    <br>
    <div id="ContractTableContainer"></div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#ContractTableContainer').jtable({
            title: 'Contract',
            openChildAsAccordion: true,
            paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10) 
            columnSelectable: false,
            columnResizable:false,
            //sorting: true,
            defaultSorting: 'contract_id desc',
            //selecting: true, //Enable selecting
            //selectingCheckboxes: true, //Show checkboxes on first column
            selectOnRowClick: false, //Enable this to only select using checkboxes
            actions: {
                listAction: 'ContractServlet?action=contractreg_list',
                //createAction:'FormServlet?action=form_create',
                //updateAction: 'ContractorServlet?action=contractor_update',
                deleteAction: 'ContractServlet?action=contractreg_delete'
                
            },
            rowInserted: function(event, data){
                 if (<%=session.getAttribute("roleID") %> === 2){
                    data.row.find('.jtable-delete-command-button').hide();
                    data.row.find('#editContract').hide();
                    //$('#ContractTableContainer').jtable('changeColumnVisibility','editContract','hidden');
                }
            },
            fields: {
                contractID:{
                    key: true,
                    list:false
                    
                },
                rowNumber:{
                    title:'#',
                    width: '5%',
                    list: true,
                    create:false,
                    edit:false
                },
                wpcName:{
                    title:'WPC',
                    width: '20%',
                    list: true,
                    create:false,
                    edit:false
                },
                contractPackage:{
                    title:'Package',
                    width: '50%',
                    list: true,
                    create:false,
                    edit:false
                },
                contractPackageCode:{
                    title:'Package Code',
                    width: '15%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter'
                },
                contractNo:{
                    title:'Contract Number',
                    width: '20%',
                    list: true,
                    create:false,
                    edit:false
                },
                contractAmount:{
                    title:'Contract Sum',
                    width: '30%',
                    list: true,
                    create:false,
                    edit:false
                },
                <%--totalBumiParticipationSum:{
                    title:'Total Bumi Participation Sum',
                    width: '30%',
                    list: true,
                    create:false,
                    edit:false
                },
                totalBumiParticipationPercent:{
                    title:'Total Bumi Participation %',
                    width: '30%',
                    list: true,
                    create:false,
                    edit:false
                },--%>
                viewContract:{
                    title:'',
                    width: '3%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter',
                    display: function (data) {
                        return '<form action="ContractServlet?action=ContractView&contractID=' + data.record.contractID + '"  method="POST"><input type="submit" value="View" /></form>';        
                        
                    }
                },
                editContract:{
                    title:'',
                    width: '3%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter',
                    display: function (data) {
                        return '<form action="ContractServlet?action=ContractRegEdit&contractID=' + data.record.contractID + '"  method="POST"><input type="submit" value="Edit" name="editContract" id="editContract"/></form>';        
                        
                    }
                }
            }  
        });
        
        $('#LoadRecordsButton').click(function (e) {
            e.preventDefault();
            $('#ContractTableContainer').jtable('load', {
                searchWpc: $('#searchWpc').val(),
                searchPackage: $('#searchPackage').val(),
                searchContractNo: $('#searchContractNo').val()
            });
             //console.log('hello');
        });
 
        //Load all records when page is first shown
        $('#LoadRecordsButton').click();
        //$('#ContractTableContainer').jtable('load');
        
    });
    
</script>

                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        
         <!-- Bootstrap -->
        <!--<script src="js/plugins/bootstrap.min.js" type="text/javascript"></script>-->
        <!-- AdminLTE App -->
        <!--<script src="js/plugins/AdminLTE/app.js" type="text/javascript"></script>-->

    </body>
</html>