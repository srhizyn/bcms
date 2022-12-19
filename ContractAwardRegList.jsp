<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>
    <head>
        <meta charset="UTF-8">
        <title>BCMS | Contract Award List</title>
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
                        Contract Award Records
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Contract Award Records</li>
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
            <label for="inputSearchCompanyName" class="col-sm-3 control-label">Company Name</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="searchName" id="searchName" class="form-control" placeholder="Search Company Name">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSearchPackage" class="col-sm-3 control-label">Package Code</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="searchPackageCode" id="searchPackageCode" class="form-control" placeholder="Search Package Code">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSearchScopeWork" class="col-sm-3 control-label">Scope of Work</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="searchScopeWork" id="searchScopeWork" class="form-control" placeholder="Search Scope of Work">
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
    <div id="ContractAwardTableContainer"></div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#ContractAwardTableContainer').jtable({
            title: 'Contract Award',
            openChildAsAccordion: true,
            paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10) 
            columnSelectable: false,
            columnResizable:false,
            //sorting: true,
            defaultSorting: 'contractaward_id desc',
            //selecting: true, //Enable selecting
            //selectingCheckboxes: true, //Show checkboxes on first column
            selectOnRowClick: false, //Enable this to only select using checkboxes
            actions: {
                listAction: 'ContractAwardServlet?action=contractawardreg_list',
                //createAction:'FormServlet?action=form_create',
                //updateAction: 'ContractorServlet?action=contractor_update',
                deleteAction: 'ContractAwardServlet?action=contractawardreg_delete'
                
            },
            rowInserted: function(event, data){
                if (<%=session.getAttribute("roleID") %> === 2){
                    data.row.find('.jtable-delete-command-button').hide();
                    data.row.find('#editContractAward').hide();
                    //$('#ContractAwardTableContainer').jtable('changeColumnVisibility','editContractAward','hidden');
                }
            },
            fields: {
                contractAwardID:{
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
                compName:{
                    title:'Company Name',
                    width: '20%',
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
                scopeWork:{
                    title:'Scope of Work',
                    width: '50%',
                    list: true,
                    create:false,
                    edit:false
                },
                dateCommencement:{
                    title:'Commencement Date',
                    width: '10%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter'
                },
                dateCompletion:{
                    title:'Completion Date',
                    width: '10%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter'
                },
                viewContractAward:{
                    title:'',
                    width: '3%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter',
                    display: function (data) {
                        return '<form action="ContractAwardServlet?action=ContractAwardView&contractAwardID=' + data.record.contractAwardID + '"  method="POST"><input type="submit" value="View" /></form>';        
                        
                    }
                },
                editContractAward:{
                    title:'',
                    width: '3%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter',
                    display: function (data) {
                        return '<form action="ContractAwardServlet?action=ContractAwardRegEdit&contractAwardID=' + data.record.contractAwardID + '"  method="POST"><input type="submit" value="Edit" name="editContractAward" id="editContractAward"/></form>';        
                        
                    }
                }
            }  
        });
         //Re-load records when user click 'load records' button.
        $('#LoadRecordsButton').click(function (e) {
            e.preventDefault();
            $('#ContractAwardTableContainer').jtable('load', {
                searchName: $('#searchName').val(),
                searchPackageCode: $('#searchPackageCode').val(),
                searchScopeWork: $('#searchScopeWork').val()
            });
             //console.log('hello');
        });
 
        //Load all records when page is first shown
        $('#LoadRecordsButton').click();
        //$('#ContractAwardTableContainer').jtable('load');
        
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