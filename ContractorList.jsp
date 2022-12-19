<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>
    <head>
        <meta charset="UTF-8">
        <title>BCMS | Contractor List</title>
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
                        Contractor Records
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Contractor Records</li>
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
    <%--#searchSpecialisationCode option{
    display:none;
    }

    #searchSpecialisationCode option.label{
    display:block;
    }--%>
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
    <form method="POST">
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
            <label for="inputContractorType" class="col-sm-3 control-label">Contractor Type</label>
                <div class="col-sm-8">
                    <select name="searchContractorType" id="searchContractorType" class="form-control">
                        <option value="0">ALL</option>
                        <c:forEach items="${contractorType}" var="row">
                        <option value="${row.contractorTypeID}">${row.contractorTypeCode}: ${row.contractorTypeDesc}</option>
                         </c:forEach>
                    </select>
                </div>
        </div>
         <div class="form-group-sm">
            <label for="inputSearchCidbGrade" class="col-sm-3 control-label">CIDB Grade</label>
            <div class="col-sm-8">
                <select name="searchCidbGrade" id="searchCidbGrade" class="form-control">
                        <option value="0">ALL</option>
                        <c:forEach items="${cidbGrade}" var="row">
                            <option value="${row.gradeID}">${row.gradeCode}</option>
                        </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSearchSpecialisation" class="col-sm-3 control-label">Specialisation</label>
            <div class="col-sm-8">
                <select name="searchSpecialisationCode" id="searchSpecialisationCode" class="form-control">
                            <option value="0">ALL</option>
                            <c:forEach items="${specialisation}" var="row" >
                            <option value="${row.specialisationID}">${row.specialisationCode}</option>
                            </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSearchSpkkSubCat" class="col-sm-3 control-label">SPKK Sub-category</label>
            <div class="col-sm-8">
                <select name="searchSpkkSubCat" id="searchSpkkSubCat" class="form-control" >
                            <option value="0">ALL</option>
                            <c:forEach items="${spkkSpec}" var="row" >
                            <option value="${row.spkkSpecID}">${row.spkkSpecCode} - ${row.spkkSpecDesc}</option>
                            </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSearchAppStatus" class="col-sm-3 control-label">Application Status</label>
            <div class="col-sm-8">
                <select name="searchAppStatus" id="searchAppStatus"  class="form-control">
                        <option value="0">ALL</option>
                        <option value="PASSED">PASSED</option>
                        <option value="PENDING">PENDING</option>
                        <option value="FAILED">FAILED</option>
                        <option value="BLACKLISTED">BLACKLISTED</option>
                </select>
            </div>
      
    </div>
        <div class="form-group-sm">
            <label for="inputSearchAwardStatus" class="col-sm-3 control-label">Award Status</label>
            <div class="col-sm-8">
                <select name="searchAwardStatus" id="searchAwardStatus"  class="form-control">
                        <option value="0">ALL</option>
                        <option value="AWARDED">AWARDED</option>
                        <option value="AVAILABLE">AVAILABLE</option>
                </select>
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
    <div id="ContractorTableContainer"></div>
</div>  
<script type="text/javascript">
    $(document).ready(function () {
        $('#ContractorTableContainer').jtable({
            title: 'Contractor',
            openChildAsAccordion: true,
            columnSelectable: false,
            columnResizable:false,
            paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10) 
            //sorting: true,
            defaultSorting: 'contractor_id desc',
            selectOnRowClick: false, //Enable this to only select using checkboxes
            actions: {
                listAction: 'ContractorServlet?action=contractor_list',
                //createAction:'FormServlet?action=form_create',
                //updateAction: 'ContractorServlet?action=contractor_update',
                deleteAction: 'ContractorServlet?action=contractor_delete'
                
            },
            rowInserted: function(event, data){
                if (<%=session.getAttribute("roleID") %> === 2){
                    data.row.find('.jtable-delete-command-button').hide();
                    data.row.find('#editContractor').hide();
                    //$('#ContractorTableContainer').jtable('changeColumnVisibility','editContractor','hidden');
                }
            },
            fields: {
                contractorID:{
                    key: true,
                    list:false
                    
                },
                rowNumber:{
                    title:'#',
                    width: '1%',
                    list: true,
                    create:false,
                    edit:false
                },
                compName:{
                    title: 'Company Name',
                    width: '25%',
                    list: true,
                    create:false,
                    edit:false
                },
                gradeCode:{
                    title: 'CIDB Grade',
                    width: '6%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter'
                },
                specialisationCode:{
                    title: 'Specialisation Code',
                    width: '15%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter'
                },
                spkkSpecCode:{
                    title: 'SPKK Sub Category',
                    width: '15%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter'
                },
                appStatus:{
                    title: 'Application Status',
                    width: '8%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter'
                },
                contractorTypeDesc:{
                    title: 'Contractor Type',
                    width: '8%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter'
                },
                stbExpiryDate:{
                    title: 'STB Expiry Date',
                    width: '8%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter'
                },
                viewContractor:{
                    title:'',
                    width: '5%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter',
                    display: function (data) {
                        return '<form action="ContractorServlet?action=ContractorView&contractorID=' + data.record.contractorID + '"  method="POST"><input type="submit" value="View" /></form>';        
                        
                    }
                },
                editContractor:{
                    title:'',
                    width: '5%',
                    list: true,
                    create:false,
                    edit:false,
                    listClass: 'aligncenter',
                    display: function (data) {
                        return '<form action="ContractorServlet?action=ContractorRegEdit&contractorID=' + data.record.contractorID + '"  method="POST"><input type="submit" name="editContractor" id="editContractor" value="Edit" /></form>';        
                        
                    }
                }
            }  
        });
         //Re-load records when user click 'load records' button.
        $('#LoadRecordsButton').click(function (e) {
            e.preventDefault();
            $('#ContractorTableContainer').jtable('load', {
                searchName: $('#searchName').val(),
                searchCidbGrade: $('#searchCidbGrade').val(),
                searchContractorType: $('#searchContractorType').val(),
                searchSpecialisationCode: $('#searchSpecialisationCode').val(),
                searchSpkkSubCat: $('#searchSpkkSubCat').val(),
                searchAppStatus: $('#searchAppStatus').val(),
                searchAwardStatus: $('#searchAwardStatus').val()
                 
            });
        });
 
        //Load all records when page is first shown
        $('#LoadRecordsButton').click();
        console.log($('#searchCidbGrade').val());
        //$('#ContractorTableContainer').jtable('load');
        
    });
    
</script>
   <%-- <script>
        $(function(){
        var $cat = $("#searchCidbGrade"),
        $subcat = $("#searchSpecialisationCode"); 
        $cat.on("change",function(){
        var _rel = $(this).find('option:selected').attr('rel');
        $subcat.find("option").attr("style","");
        $subcat.val("");
        if(!_rel) return $subcat.prop("disabled",true);
        $subcat.find("[rel="+_rel+"]").show();
        $subcat.prop("disabled",false);
            });
        });
    </script>--%>
    
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        
         <!-- Bootstrap -->
        <!--<script src="js/plugins/bootstrap.min.js" type="text/javascript"></script>-->
        <!-- AdminLTE App -->
        <!--<script src="js/plugins/AdminLTE/app.js" type="text/javascript"></script>-->

    </body>
</html>