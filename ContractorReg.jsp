<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | New Contractor</title>
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
        
        
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/history-stealer.js"></script>
        <!-- Morris.js charts -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="js/raphael-min.js" type="text/javascript"></script>
        <script src="js/plugins/morris/morris.min.js" type="text/javascript"></script>
        
    </head>
    <body class="skin-blue" onload="alertContractor()">
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
                        Contractor Registration
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Contractor Registration</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
<style type="text/css">     
    .selectoption {
        width:500px;
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
        <form action="ContractorServlet?action=contractor_create" id="contractorReg" name="contractorReg" method="POST" enctype="multipart/form-data" accept-charset='UTF-8'>
        <div class="panel-group" id="accordion">
            
    <div class="panel panel-default" id="panel1">
        <div class="panel-heading bg-light-blue">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne" class="collapsed">
          Company Details
        </a>
      </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
                
        <div class="form-group-sm">
            <label for="inputSsmCertNo" class="col-sm-3 control-label">SSM Certificate Number</label>
            <div class="col-sm-8">
                <input type="text"  size="50" id="ssmCertNo" name="ssmCertNo" class="form-control" required="required" placeholder="Enter SSM Certificate Number">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCompanyName" class="col-sm-3 control-label">Company Name</label>
            <div class="col-sm-8">
                <input type="text" size="50" id="companyName" name="companyName" class="form-control" required="required" placeholder="Enter Company Name">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputContractorType" class="col-sm-3 control-label">Contractor Type</label>
                <div class="col-sm-8">
                    <select name="contractorType" id="contractorType" class="form-control">
                        <c:forEach items="${contractorType}" var="row">
                        <option value="${row.contractorTypeID}">${row.contractorTypeCode}: ${row.contractorTypeDesc}</option>
                         </c:forEach>
                    </select>
                </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputProgram" class="col-sm-3 control-label">Entrepreneur Program</label>
                <div class="col-sm-8">
                    <select name="program" id="program" class="form-control">
                        <option value="1">NA-Not Applicable</option>
                        <c:forEach items="${program}" var="row">
                        <option value="${row.programID}">${row.programCode}-${row.programDesc}</option>
                         </c:forEach>
                    </select>
                </div>
        </div>--%>
        <div class="form-group-sm" id="othersdiv" style="display:none">
            <label for="inputOthers" class="col-sm-3 control-label">Remarks</label>
            <div class="input_fields_wrap5">
                <div class="col-sm-8">
                    <input type="text" name="othersRemark" id="othersRemark" class="form-control-static" style="width:600px;" placeholder="Remarks">
                    <button id="btnOthers" class="add_field_button5" >Add</button>
                </div>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputOfficePhoneNo" class="col-sm-3 control-label">Office Phone Number</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="officePhoneNo" class="form-control" placeholder="Enter Office Phone Number">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputOfficeFaxNo" class="col-sm-3 control-label">Office Fax Number</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="officeFaxNo" class="form-control" placeholder="Enter Office Fax Number">
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputPicName" class="col-sm-3 control-label">PIC Name</label>
            <div class="col-sm-8">
                <input type="text"  size="100" name="picName" class="form-control" placeholder="Enter PIC Name">
            </div>
        </div>--%>
        <div class="form-group-sm">
            <label for="inputPicMobileNo" class="col-sm-3 control-label">Mobile Number</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="picMobileNo" class="form-control" placeholder="Enter Mobile Number">
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputPicMobileNo2" class="col-sm-3 control-label">PIC Mobile Number 2</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="picMobileNo2" class="form-control" placeholder="Enter PIC Mobile Number 2">
            </div>
        </div>--%>
        <div class="form-group-sm">
            <label for="inputEmailAddress1" class="col-sm-3 control-label">Email Address</label>
            <div class="col-sm-8">
                <input type="email" name="emailAddress1" class="form-control" placeholder="Email Address">
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputEmailAddress2" class="col-sm-3 control-label">Email Address 2</label>
                <div class="col-sm-8">
                    <input type="email" name="emailAddress2" class="form-control" placeholder="Email Address">
                </div>
        </div>--%>
        <div class="col-sm-10">
                <label for="inputCorrespondenceAddress" class="control-label">Correspondence Address</label>
        </div>
        <div class="form-group-sm">
            <label for="inputCorrespondenceAddress1" class="col-sm-3 control-label">Address 1</label>
            <div class="col-sm-8">
                <input type="text" name="correspondenceAddress1" id="correspondenceAddress1" class="form-control" placeholder="Address">
             </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCorrespondenceAddress2" class="col-sm-3 control-label">Address 2</label>
            <div class="col-sm-8">
                <input type="text" name="correspondenceAddress2" id="correspondenceAddress2" class="form-control" placeholder="Address">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCorrespondencePostCode" class="col-sm-3 control-label">Post Code</label>
            <div class="col-sm-8">
                <input type="number" name="correspondencePostCode" id="correspondencePostCode" class="form-control" placeholder="Post Code">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCorrespondenceCity" class="col-sm-3 control-label">City</label>
            <div class="col-sm-8">
                <input type="text" name="correspondenceCity" id="correspondenceCity" class="form-control" placeholder="City">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCorrespondenceState" class="col-sm-3 control-label">State</label>
                <div class="col-sm-8">
                    <select name="correspondenceState" id="correspondenceState" class="form-control">
                        <option value="">Please Select</option>
                        <c:forEach items="${correspondenceState}" var="row">
                        <option value="${row.stateID}">${row.stateName}</option>
                         </c:forEach>
                    </select>
                </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputCorrespondenceGpsLoc" class="col-sm-3 control-label">GPS Location</label>
            <div class="col-sm-8">
                <input type="text" name="correspondenceGpsLoc" id="correspondenceGpsLoc" class="form-control" placeholder="GPS Location">
            </div>
        </div>
        <div class="col-sm-10">
            <label for="inputSSMAddress" class="control-label">SSM Registered Address </label> &nbsp;
            <input type="checkbox" name="sameAsAbove" class="control-label" id="sameAsAbove" value="yes" onclick="data_copy()">Same as above
        </div>
        <div class="form-group-sm">
            <label for="inputCompanySsmAddress1" class="col-sm-3 control-label">Address 1</label>
            <div class="col-sm-8">
                <input type="text" name="companySsmAddress1" id="companySsmAddress1" class="form-control" placeholder="Address">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCompanySsmAddress2" class="col-sm-3 control-label">Address 2</label>
            <div class="col-sm-8">
                <input type="text" name="companySsmAddress2" id="companySsmAddress2" class="form-control" placeholder="Address">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCompanySsmCity" class="col-sm-3 control-label">City</label>
                <div class="col-sm-8">
                    <input type="text" name="companySsmCity" id="companySsmCity" class="form-control" placeholder="City">
                </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCompanySsmPostCode" class="col-sm-3 control-label">Post Code</label>
                <div class="col-sm-8">
                    <input type="number" name="companySsmPostCode" id="companySsmPostCode" class="form-control" placeholder="Post Code">
                </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCompanySsmState" class="col-sm-3 control-label">State</label>
            <div class="col-sm-8">
                <select name="companySsmState" id="companySsmState" class="form-control">
                    <option value="">Please Select</option>
                    <c:forEach items="${companySsmState}" var="row">
                        <option value="${row.stateID}">${row.stateName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>--%>
        <%--<div class="form-group-sm">
            <label for="inputCompanyLogo" class="col-sm-3 control-label">Company Logo</label>
            <div class="col-sm-8">
                <input type="file" name="attachCompanyLogo" id="attachCompanyLogo" class="form-control-static"/>
            </div>
        </div>--%>
        
                </div>
        </div>
    </div>
                
                
        <div class="panel panel-default" id="panel2">
        <div class="panel-heading bg-light-blue">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseTwo" 
           href="#collapseTwo" class="collapsed">
          Company Owners Information
        </a>
      </h4>

        </div>
        <div id="collapseTwo" class="panel-collapse collapse">
            <div class="panel-body">        
        <div class="form-group-sm">
            <label for="inputOwnerName" class="col-sm-3 control-label">Company Owner Name & IC No.</label>
            <div class="input_fields_wrap">
                <div class="col-sm-8">
                    <input type="text" name="companyOwner" id="companyOwner" class="form-control-static" style="width:250px;" placeholder="Company Owner">
                    <input type="text" name="icNo" id="icNo" class="form-control-static" style="width:150px;" placeholder="IC No.">
                    <input type="text" name="ownerContactNo" id="ownerContactNo" class="form-control-static" style="width:150px;" placeholder="Contact No.">
                    <button class="add_field_button">Add Owner</button>
                </div>
            </div>
        </div>
        </div>
        </div>
    </div>
        <div class="panel panel-default" id="panel3">
        <div class="panel-heading bg-light-blue">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseThree"
           href="#collapseThree" class="collapsed">
          Certificate Information
        </a>
      </h4>

        </div>
        <div id="collapseThree" class="panel-collapse collapse">
            <div class="panel-body"> 
        <div class="form-group-sm">
                <label for="inputStbExpiryDate" class="col-sm-3 control-label">STB Expiry Date</label>
                <div class="col-sm-8">
                <input type="date" name="stbExpiryDate" id="stbExpiryDate" class="form-control" min="2000-01-01" max="2040-12-31">
                </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCidbRegNo" class="col-sm-3 control-label">CIDB Registration Number</label>
                <div class="col-sm-8">
                    <input type="text" name="cidbRegNo" id="cidbRegNo" class="form-control" placeholder="CIDB Registration Number">
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
            <label for="inputCidbExpiryDate" class="col-sm-3 control-label">CIDB Expiry Date</label>
                <div class="col-sm-8">
                    <input type="date" name="cidbExpiryDate" id="cidbExpiryDate" class="form-control" min="2000-01-01" max="2040-12-31">
                </div>
        </div>    
        
        <div class="form-group-sm">
            <label for="inputSpecialisation" class="col-sm-3 control-label">MRT Specialisation</label>
            <div class="col-sm-8">
                     <select name="specialisationChkBox" id="specialisationChkBox" multiple="multiple" class="form-control" size="4">
                            <c:forEach items="${specialisation}" var="row">
                            <option value="${row.specialisationID}" rel="${row.groupingB}">${row.specialisationCode} - ${row.specialisationDesc}</option>
                            </c:forEach>
                     </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSpecialisation" class="col-sm-3 control-label">MRT Specialisation (Others)</label>
            <div class="col-sm-8">
                     <input type="text" name="specialisationOthers" id="specialisationOthers" class="form-control" placeholder="Others (Please Specify)">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSpkkCat" class="col-sm-3 control-label">SPKK Category</label>
                <div class="col-sm-8">
                      <select name="spkkCat" id="spkkCat" multiple="multiple" class="form-control" size="4">
                            <c:forEach items="${spkkCat}" var="row">
                            <option value="${row.spkkCatID}">${row.spkkCatCode} - ${row.spkkCatDesc}</option>
                            </c:forEach>
                      </select>
               </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSpkkSpec" class="col-sm-3 control-label">SPKK Sub-category</label>
                <div class="col-sm-8">
                     <select name="spkkSpec" id="spkkSpec" multiple="multiple" class="form-control" size="4">
                            <c:forEach items="${spkkSpec}" var="row">
                            <option value="${row.spkkSpecID}" rel="${row.spkkCatID}">${row.spkkSpecCode} - ${row.spkkSpecDesc}</option>
                            </c:forEach>
                     </select>
               </div>
        </div>
        <div class="form-group-sm">
            <label for="inputMof" class="col-sm-3 control-label">MOF Code</label>
                <div class="col-sm-8">
                     <select name="mof" id="mof" multiple="multiple" class="form-control" size="4">
                            <c:forEach items="${mof}" var="row">
                            <option value="${row.mofCodeID}">${row.mofCode} - ${row.mofDesc}</option>
                            </c:forEach>
                     </select>
               </div>
        </div>
               
        
        <%--<div class="form-group-sm">
                <label for="inputStbNo" class="col-sm-3 control-label">STB Number</label>
                <div class="col-sm-8">
                <input type="text" name="stbNo" id="stbNo" class="form-control" placeholder="STB Number">
                </div>
        </div>--%>
            </div>
        </div>
        </div>
        
        <div class="panel panel-default" id="panel4">
        <div class="panel-heading bg-light-blue">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseFour"
           href="#collapseFour" class="collapsed">
          Projects & Awards
        </a>
      </h4>

        </div>
        <div id="collapseFour" class="panel-collapse collapse">
            <div class="panel-body">  
        <div class="form-group-sm">
            <label for="inputPastProject" class="col-sm-3 control-label">List of Past Projects</label>
            <div class="input_fields_wrap2">
                <div class="col-sm-9">
                    <input type="number" name="pastProjectYear" min="2009" max="2040" class="form-control-static">
                    <input type="text" name="pastProject" id="pastProject" class="form-control-static" style="width:370px;" placeholder="Past Project">
                    <input type="text" name="pastProjectAmount[]" id="pastProjectAmount" class="form-control-static" style="width:110px;" placeholder="Amount">
                    
                    <span id="total"></span><button class="add_field_button2">Add Past Project</button>
                </div>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCurrentProject" class="col-sm-3 control-label">List of Current Projects</label>
            <div class="input_fields_wrap3">
                <div class="col-sm-9">
                    <input type="number" name="currentProjectYear" min="2009" max="2040" class="form-control-static">
                    <input type="text" name="currentProject" id="currentProject" class="form-control-static" style="width:370px;" placeholder="Current Project">
                    <input type="text" name="currentProjectAmount" id="currentProjectAmount" class="form-control-static" style="width:110px;" placeholder="Amount">
                    <button class="add_field_button3">Add Current Project</button>
                </div>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputAwardGov" class="col-sm-3 control-label">List of Awards/Recognitions</label>
            <div class="input_fields_wrap4">
                <div class="col-sm-9">
                    <input type="number" name="awardGovYear" min="2009" max="2040" class="form-control-static">
                    <input type="text" name="awardGov" id="awardGov" class="form-control-static" style="width:450px;" placeholder="Awards/Recognitions">
                    <button class="add_field_button4">Add Awards/Recognitions</button>
                </div>
            </div>
        </div>
        </div>
            </div>
        </div>
        
        <div class="panel panel-default" id="panel5">
        <div class="panel-heading bg-light-blue">
             <h4 class="panel-title">  
         <a data-toggle="collapse" data-target="#collapseFive"
           href="#collapseFive" class="collapsed">
          Form Information
        </a>
      </h4>

        </div>
        <div id="collapseFive" class="panel-collapse collapse">
            <div class="panel-body">  
        <div class="form-group-sm">
            <label for="inputFormType" class="col-sm-3 control-label">Type</label>
            <div class="col-sm-8">
                <c:forEach items="${formType}" var="row">
                    <input type="radio" name="formType" id="formType" value="${row.formTypeID}">${row.formTypeDesc} &nbsp;         
                </c:forEach>
                <input type="text" name="formTypeOthers" id="formTypeOthers" class="form-control-static" style="width:250px;" placeholder="Others">
            </div>
        </div>        
        <div class="form-group-sm">
            <label for="inputFormSubmittedDate" class="col-sm-3 control-label">Form Submission Date</label>
            <div class="col-sm-8">
                <input type="date" name="dateFormSubmitted" class="form-control" id="dateFormSubmitted" min="2000-01-01" max="2040-12-31">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputAppStatus" class="col-sm-3 control-label">Status</label>
            <div class="col-sm-8">
                <input type="radio" name="appStatus" id="appStatus" value="PASSED"> Passed &nbsp;
                <input type="radio" name="appStatus" id="appStatus" value="PENDING"> Pending &nbsp;
                <input type="radio" name="appStatus" id="appStatus" value="FAILED"> Failed &nbsp;
                <input type="radio" name="appStatus" id="appStatus" value="BLACKLISTED"> Blacklisted &nbsp;
                <input type="radio" name="appStatus" id="appStatus" value="OTHERS"> Others &nbsp;
                <input type="radio" name="appStatus" id="appStatus" value="EXPIRED"> Expired
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputAppStatusDate" class="col-sm-3 control-label">Application Status Date</label>
            <div class="col-sm-8">
                <input type="date" name="dateAppStatus" class="form-control" id="dateAppStatus" min="2000-01-01" max="2040-12-31">
            </div>
        </div>--%>
        <div class="form-group-sm">
            <label for="inputReasonFailed" class="col-sm-3 control-label">Remarks</label>
            <div class="col-sm-8">
                <textarea rows="2" cols="50" name="reasonFailed" class="form-control" id="reasonFailed" placeholder="Remarks"></textarea>
            </div>
        </div>
            </div>
        </div>
        </div>
        <div class="panel panel-default" id="panel6">
        <div class="panel-heading bg-light-blue">
            <h4 class="panel-title">  
            <a data-toggle="collapse" data-target="#collapseSix"
              href="#collapseSix" class="collapsed">
             Attachment
            </a>
            </h4>
        </div>
        <div id="collapseSix" class="panel-collapse collapse">
        <div class="panel-body"> 
        <div class="form-group-sm">
            <div class="col-sm-8">
            <table>
            <tr><td><label for="inputAttachScreenForm">Screening Form</label></td><td><input type="file" name="attachScreenForm" id="attachScreenForm" class="form-control-static" multiple="multiple"/></td></tr>
            <tr><td><label for="inputAttachStb">STB Certificate</label></td><td><input type="file" name="attachStbCert" id="attachStbCert" class="form-control-static" multiple="multiple"/></td></tr>
            <tr><td><label for="inputAttachCidb">CIDB Certificate</label></td><td><input type="file" name="attachCidbCert" id="attachCidbCert" class="form-control-static" multiple="multiple"/></td></tr>
            <tr><td><label for="inputAttachAppForm">Application Form</label></td><td><input type="file" name="attachAppForm" id="attachAppForm" class="form-control-static" multiple="multiple"/></td></tr>
            <tr><td><label for="inputAttachMisc">Misc</label></td><td><input type="file" name="attachContractorMisc" id="attachContractorMisc" class="form-control-static" multiple="multiple"/></td></tr>
            </table>
            </div>
        </div>
            </div>
        </div>
        </div>
        </div>
        <div class="form-group"> 
            <div class="col-sm-offset-1">
                <input type="submit" value="Add Contractor" id="AddContractor" class="form-control-static" onclick="return confirm('Confirm to add contractor?')"/>
                <input type="reset" value="Reset" id="ResetContractor" class="form-control-static"/>
                <a href="ContractorServlet?action=ContractorListView" style=""><font color="#000000"><input type="button" class="form-control-static" value="Cancel" style=""/></font></a>
            </div>
        </div>
        </form>
        <%--<input type="button" value="Get Box" id="GetBox" onclick="addBoxFunction()"/>--%>
    
    

    </section><!-- /.content -->
    </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        
         <!-- Bootstrap -->
        <!--<script src="js/plugins/bootstrap.min.js" type="text/javascript"></script>-->
        <!-- AdminLTE App -->
        <!--<script src="js/plugins/AdminLTE/app.js" type="text/javascript"></script>-->

    </body>
    <script type="text/javascript">
        $('#ssmCertNo').keyup(function(){
        this.value = this.value.toUpperCase();
        });
        $('#companyName').keyup(function(){
        this.value = this.value.toUpperCase();
        });
    </script>
    <script type="text/javascript">
    function data_copy()
    {
    if(document.contractorReg.sameAsAbove.checked){
    document.contractorReg.companySsmAddress1.value=document.contractorReg.correspondenceAddress1.value;
    document.contractorReg.companySsmAddress2.value=document.contractorReg.correspondenceAddress2.value;
    document.contractorReg.companySsmPostCode.value=document.contractorReg.correspondencePostCode.value;
    document.contractorReg.companySsmCity.value=document.contractorReg.correspondenceCity.value;
    document.contractorReg.companySsmState.value=document.contractorReg.correspondenceState.value;
    }
    }
</script>
    <script language="javascript">
      $(document).ready(function() {
    var max_fields      = 40; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID
    
    var max_fields2      = 40; //maximum input boxes allowed
    var wrapper2         = $(".input_fields_wrap2"); //Fields wrapper
    var add_button2      = $(".add_field_button2"); //Add button ID
    
    var max_fields3      = 40; //maximum input boxes allowed
    var wrapper3         = $(".input_fields_wrap3"); //Fields wrapper
    var add_button3      = $(".add_field_button3"); //Add button ID
    
    var max_fields4      = 40; //maximum input boxes allowed
    var wrapper4         = $(".input_fields_wrap4"); //Fields wrapper
    var add_button4      = $(".add_field_button4"); //Add button ID
    
     var max_fields5      = 10; //maximum input boxes allowed
    var wrapper5         = $(".input_fields_wrap5"); //Fields wrapper
    var add_button5      = $(".add_field_button5"); //Add button ID
    
    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div class="form-group-sm"><label for="inputOwnerName" class="col-sm-3 control-label"></label><div class="col-sm-8"><input type="text" name="companyOwner" id="companyOwner" class="form-control-static" style="width:250px;" placeholder="Company Owner">\n\
            <input type="text" name="icNo" id="icNo" class="form-control-static" style="width:150px;" placeholder="IC No.">\n\
            <input type="text" name="ownerContactNo" id="ownerContactNo" class="form-control-static" style="width:150px;" placeholder="Contact No."><a href="#" class="remove_field"> Remove</a></div></div>');
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
             $(wrapper2).append('<div class="form-group-sm"><label for="inputPastProject" class="col-sm-3 control-label"></label><div class="col-sm-9"><input type="number" name="pastProjectYear" min="2009" max="2040" class="form-control-static">\n\
            <input type="text" name="pastProject" id="pastProject" class="form-control-static" style="width:370px;" placeholder="Past Project">\n\
            <input type="text" name="pastProjectAmount[]" id="pastProjectAmount" style="width:110px;" class="form-control-static" placeholder="Amount"><a href="#" class="remove_field2"> Remove</a></div></div>'); //add input box
        }
    });
    
    $(wrapper2).on("click",".remove_field2", function(e2){ //user click on remove text
        e2.preventDefault(); $(this).parent('div').parent('div').remove(); x2--;
    });
    
    var x3 = 1; //initlal text box count
    $(add_button3).click(function(e3){ //on add input button click
        e3.preventDefault();
        if(x3 < max_fields3){ //max input box allowed
            x3++; //text box increment
           $(wrapper3).append('<div class="form-group-sm"><label for="inputCurrentProject" class="col-sm-3 control-label"></label><div class="col-sm-9"><input type="number" name="currentProjectYear" min="2009" max="2040" class="form-control-static">\n\
            <input type="text" name="currentProject" id="currentProject" class="form-control-static" style="width:370px;" placeholder="Current Project">\n\
            <input type="text" name="currentProjectAmount" id="currentProjectAmount" style="width:110px;" class="form-control-static" placeholder="Amount"><a href="#" class="remove_field3"> Remove</a></div></div>'); //add input box
        }
    });
    
    $(wrapper3).on("click",".remove_field3", function(e3){ //user click on remove text
        e3.preventDefault(); $(this).parent('div').parent('div').remove(); x3--;
    });
    
     var x4 = 1; //initlal text box count
    $(add_button4).click(function(e4){ //on add input button click
        e4.preventDefault();
        if(x4 < max_fields4){ //max input box allowed
            x4++; //text box increment
           $(wrapper4).append('<div class="form-group-sm"><label for="inputAwardGov" class="col-sm-3 control-label"></label><div class="col-sm-9"><input type="number" name="pastProjectYear" min="2009" max="2040" class="form-control-static">\n\
            <input type="text" name="awardGov" id="awardGov" class="form-control-static" style="width:450px;" placeholder="Awards/Recognitions"><a href="#" class="remove_field4"> Remove</a></div></div>'); //add input box
        }
    });
    
    $(wrapper4).on("click",".remove_field4", function(e4){ //user click on remove text
        e4.preventDefault(); $(this).parent('div').parent('div').remove(); x4--;
    });
    
     var x5 = 1; //initlal text box count
    $(add_button5).click(function(e5){ //on add input button click
        e5.preventDefault();
        if(x5 < max_fields5){ //max input box allowed
            x5++; //text box increment
           $(wrapper5).append('<div class="form-group-sm"><label for="inputOthers" class="col-sm-3 control-label"></label><div class="col-sm-8"><input type="text" name="othersRemark" id="othersRemark" class="form-control-static" style="width:600px;" placeholder="Remarks">\n\
            <a href="#" class="remove_field5"> Remove</a></div></div>'); //add input box
        }
    });
    
    $(wrapper5).on("click",".remove_field5", function(e5){ //user click on remove text
        e5.preventDefault(); $(this).parent('div').parent('div').remove(); x5--;
    });
});
      
    </script>
    <script>
        $(function(){
        var $cat = $("#cidbGrade"),
        $subcat = $("#specialisationChkBox");
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
    <script>
    function alertContractor()
    {
    var javascriptVar="${alertcontractor}";
    if(javascriptVar === 'true'){
        alert("Contractor Information Successfully Added");
        window.location = "fwdContractorReg.jsp";
        return true;
        }
        else if (javascriptVar === 'false') {
        alert("An Error Occured. Please Try Again");   
        return false;
        }

    }
    </script>
<%--<script>
$(function() {
    window.invalidate_input = function() {
        if ($('input[name=appStatus]:checked').val() === "FAILED" || $('input[name=appStatus]:checked').val() === "PENDING")
            $('#reasonFailed').removeAttr('disabled');
        else
            $('#reasonFailed').attr('disabled', 'disabled');
    };
    
    $("input[name=appStatus]").change(invalidate_input);
    
    invalidate_input();
});
</script>--%>
<script>
$(function() {
    window.invalidate_input = function() {
        if ($('input[name=formType]:checked').val() === "2")
            $('#formTypeOthers').removeAttr('disabled');
        else
            $('#formTypeOthers').attr('disabled', 'disabled');
    };
    
    $("input[name=formType]").change(invalidate_input);
    
    invalidate_input();
});
</script>
<%--<script>
$(function() {
    window.invalidate_input = function() {
        if ($('input[name=specialisation]:selected').val() === "21")
            $('#specialisationOthers').removeAttr('disabled');
        else
            $('#specialisationOthers').attr('disabled', 'disabled');
    };
    
    $("input[name=specialisation]").change(invalidate_input);
    
    invalidate_input();
});
</script>--%>
<%--<script type="text/javascript">
$(document).ready(function () {
    $('#AddContractor').click(function() {
      checked = $("input[type=checkbox]:checked").length;

      if(!checked) {
        alert("You must check at least one checkbox.");
        return false;
      }

    });
});

</script>--%>
<script>
    $("#contractorReg input[type='submit']").on("click", function(event) {
     
    // traverse all the required elements looking for an empty one
    $("#contractorReg input[required='required']").each(function() {
       
        // if the value is empty, that means that is invalid
        if ($(this).val() === "") {
            
            // hide the currently open accordion and open the one with the required field
            $(".panel-collapse.in").removeClass("in");
            $(this).closest(".panel-collapse").addClass("in").css("height","auto");

            // stop scrolling through the required elements
            return false;
        }
    });
});
    
</script>
<script>
        $(function(){
        var $cat = $("#ResetContractor"),
        $subcat = $("#specialisationChkBox");
        $cat.on("click",function(){
        
        $subcat.find("option").attr("style","");
        $subcat.val("");
        
            });
        });
    </script>
   <script>
        var element = document.getElementById("othersdiv");
        $(function(){
        var $cat = $("#program");
       
        $cat.on("change",function(){
        var value = $cat.val();
        if (value === "4") {
        element.style.display = 'block';  
         }
        else {
        element.style.display = 'none'; 
        }
            });
        });
    </script>
<%--<script>
    $.fn.fonkTopla = function() {
    var total = 0;
    this.each(function() {
       var deger = fonkDeger($(this).val());
       total += deger;
    });
    return total;
    };


function fonkDeger(veri) {
    return (veri != '') ? parseInt(veri) : 0;
}

$(document).ready(function(){
$('input[name^="pastProjectAmount"]').bind('keyup', function() {
   $('#total').html( $('input[name^="pastProjectAmount"]').fonkTopla());
});
});
    
</script>--%>
</html>