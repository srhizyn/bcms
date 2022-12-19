<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | View Contractor</title>
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
                        View Contractor
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
    #program option{
    display:none;
    }

    #program option.label{
    display:block;
    }
    .panel-heading a:after {
    font-family:'Glyphicons Halflings';
    content:"\e114";
    float: right;
    color: grey;
    }
    .panel-heading a.collapsed:after {
        content:"\e080";
    }
    tr.spaceUnder3 > td
    {
      padding-bottom: 2em;
    }
</style>
        <form action="ContractorServlet?action=contractor_create" id="contractorReg" name="contractorReg" method="POST">
        <div class="panel-group" id="accordion">
            
    <div class="panel panel-default" id="panel1">
        <div class="panel-heading">
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
                <input type="text"  size="50" name="ssmCertNo" class="form-control" placeholder="SSM Certificate Number" value="${ssmCertNo}" readonly>
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputSsmExpiryDate" class="col-sm-3 control-label">SSM Expiry Date</label>
            <div class="col-sm-8">
                <input type="date" name="ssmExpiryDate" id="ssmExpiryDate" class="form-control" min="2000-01-01" max="2040-12-31" value="${ssmExpiryDate}" readonly>
            </div>
        </div>--%>
        <div class="form-group-sm">
            <label for="inputCompanyName" class="col-sm-3 control-label">Company Name</label>
            <div class="col-sm-8">
                <input type="text" size="50" name="companyName" class="form-control" required placeholder="Company Name" value="${companyName}" readonly>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputContractorType" class="col-sm-3 control-label">Contractor Type</label>
                <div class="col-sm-8">
                    <select name="contractorType" id="contractorType" class="form-control" readonly>
                        <c:forEach items="${contractorType}" var="row">
                        <option value="${row.contractorTypeID}">${row.contractorTypeCode}: ${row.contractorTypeDesc}</option>
                         </c:forEach>
                    </select>
                </div>
        </div>
        <%--div class="form-group-sm">
            <label for="inputProgramID" class="col-sm-3 control-label">Entrepreneur Program</label>
            <div class="col-sm-8">
                    <select name="program" id="program" class="form-control" readonly>
                        <c:forEach items="${program}" var="row">
                            <option value="${row.programID}" >${row.programCode}-${row.programDesc}</option>
                        </c:forEach>
                    </select>
            </div>
        </div>--%>
        <div class="form-group-sm" id="registeredOthersRemark">
            <label for="inputOthersRemark" class="col-sm-3 control-label">Remarks</label>
            <div class="input_fields_wrapEdit6">
                        <div class="">
                            <c:forEach items="${registeredOthersRemark}" var="row" >
                            <script>
                                var wrapperEdit6 = $(".input_fields_wrapEdit6");
                                $(wrapperEdit6).append('<div class="form-group-sm"><label for="inputOthersRemark" class="col-sm-3 control-label"></label><div class="col-sm-8"><input type="text" size="50" name="othersRemark" id="othersRemark" class="form-control"  placeholder="Remarks" value="${row.othersRemark}" readonly>\n\
                                </div></div></div>'); //add input box
                                
                            </script>
                            </c:forEach>
                    </div>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputOfficePhoneNo" class="col-sm-3 control-label">Office Phone Number</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="officePhoneNo" class="form-control" required placeholder="Office Phone Number" value="${officePhoneNo}" readonly>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputOfficeFaxNo" class="col-sm-3 control-label">Office Fax Number</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="officeFaxNo" class="form-control" placeholder="Office Fax Number" value="${officeFaxNo}" readonly>
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputPicName" class="col-sm-3 control-label">PIC Name</label>
            <div class="col-sm-8">
                <input type="text"  size="100" name="picName" class="form-control" placeholder="PIC Name" value="${picName}" readonly>
            </div>
        </div>--%>
        <div class="form-group-sm">
            <label for="inputPicMobileNo" class="col-sm-3 control-label">PIC Mobile Number</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="picMobileNo" class="form-control" placeholder="PIC Mobile Number" value="${picMobileNo}" readonly>
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputPicMobileNo2" class="col-sm-3 control-label">PIC Mobile Number 2</label>
            <div class="col-sm-8">
                <input type="text"  size="50" name="picMobileNo2" class="form-control" placeholder="PIC Mobile Number 2" value="${picMobileNo2}" readonly>
            </div>
        </div>--%>
        <div class="form-group-sm">
            <label for="inputEmailAddress1" class="col-sm-3 control-label">Email Address</label>
            <div class="col-sm-8">
                <input type="email" name="emailAddress1" class="form-control" required placeholder="Email Address" value="${emailAddress1}" readonly>
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputEmailAddress2" class="col-sm-3 control-label">Email Address 2</label>
                <div class="col-sm-8">
                    <input type="email" name="emailAddress2" class="form-control" placeholder="Email Address" value="${emailAddress2}" readonly>
                </div>
        </div>--%>
        <div class="col-sm-10">
                <label for="inputCorrespondenceAddress" class="control-label">Correspondence Address</label>
        </div>
        <div class="form-group-sm">
            <label for="inputCorrespondenceAddress1" class="col-sm-3 control-label">Address 1</label>
            <div class="col-sm-8">
                <input type="text" name="correspondenceAddress1" id="correspondenceAddress1" class="form-control" required placeholder="Address" value="${correspondenceAddress1}" readonly>
             </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCorrespondenceAddress2" class="col-sm-3 control-label">Address 2</label>
            <div class="col-sm-8">
                <input type="text" name="correspondenceAddress2" id="correspondenceAddress2" class="form-control" placeholder="Address" value="${correspondenceAddress2}" readonly>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCorrespondencePostCode" class="col-sm-3 control-label">Post Code</label>
            <div class="col-sm-8">
                <input type="text" name="correspondencePostCode" id="correspondencePostCode" class="form-control" required placeholder="Post Code" value="${correspondencePostCode}" readonly>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCorrespondenceCity" class="col-sm-3 control-label">City</label>
            <div class="col-sm-8">
                <input type="text" name="correspondenceCity" id="correspondenceCity" class="form-control" required placeholder="City" value="${correspondenceCity}" readonly>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCorrespondenceState" class="col-sm-3 control-label">State</label>
                <div class="col-sm-8">
                    <select name="correspondenceState" id="correspondenceState" required class="form-control" readonly>
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
                <input type="text" name="correspondenceGpsLoc" id="correspondenceGpsLoc" class="form-control" placeholder="GPS Location" value="${correspondenceGpsLoc}" readonly>
            </div>
        </div>
        <div class="col-sm-10">
            <label for="inputSSMAddress" class="control-label">SSM Registered Address </label> &nbsp;
        </div>
        <div class="form-group-sm">
            <label for="inputCompanySsmAddress1" class="col-sm-3 control-label">Address 1</label>
            <div class="col-sm-8">
                <input type="text" name="companySsmAddress1" id="companySsmAddress1" class="form-control" placeholder="Address" value="${ssmAddress1}" readonly>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCompanySsmAddress2" class="col-sm-3 control-label">Address 2</label>
            <div class="col-sm-8">
                <input type="text" name="companySsmAddress2" id="companySsmAddress2" class="form-control" placeholder="Address" value="${ssmAddress2}" readonly>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCompanySsmCity" class="col-sm-3 control-label">City</label>
                <div class="col-sm-8">
                    <input type="text" name="companySsmCity" id="companySsmCity" class="form-control" placeholder="City" value="${ssmCity}" readonly>
                </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCompanySsmPostCode" class="col-sm-3 control-label">Post Code</label>
                <div class="col-sm-8">
                    <input type="text" name="companySsmPostCode" id="companySsmPostCode" class="form-control" placeholder="Post Code" value="${ssmPostCode}" readonly>
                </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCompanySsmState" class="col-sm-3 control-label">State</label>
            <div class="col-sm-8">
                <select name="companySsmState" id="companySsmState" class="form-control" readonly>
                    <option value="">Please Select</option>
                    <c:forEach items="${ssmState}" var="row">
                        <option value="${row.stateID}">${row.stateName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>--%>
     </div>
        </div>
    </div>
                
                
        <div class="panel panel-default" id="panel2">
        <div class="panel-heading">
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
            <div class="input_fields_wrapEdit">
                            <c:choose>
                            <c:when test="${registeredCompanyOwner == []}">
                                <div class="form-group-sm"><label for="inputOwnerName" class="col-sm-3 control-label"></label><div class="col-sm-8">
                               <input type="text" name="companyOwner" value="No Data" id="companyOwner" class="form-control-static" style="width:250px;" placeholder="No Data" readonly>
                               <input type="text" name="icNo" id="icNo" class="form-control-static" style="width:150px;" placeholder="No Data" value="No Data" readonly><input type="text" name="ownerContactNo" id="ownerContactNo" class="form-control-static" style="width:150px;" placeholder="No Data"></div></div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${registeredCompanyOwner}" var="row" >
                                <script>
                                    var wrapperEdit = $(".input_fields_wrapEdit");
                                    //$(wrapperEdit).append('<div><input type="text" size="50" name="companyOwner" placeholder="No Data" value="${row.companyOwner}" readonly/> <input type="text" size="16" name="icNo" placeholder="No Data" value="${row.icNo}" readonly></div>');
                                    $(wrapperEdit).append('<div class="form-group-sm"><label for="inputOwnerName" class="col-sm-3 control-label"></label><div class="col-sm-8"><input type="text" name="companyOwner" value="${row.companyOwner}" readonly id="companyOwner" class="form-control-static" style="width:250px;" placeholder="No Data">\n\
                                    <input type="text" name="icNo" id="icNo" class="form-control-static" style="width:150px;" placeholder="No Data" value="${row.icNo}" readonly>\n\
                                    <input type="text" name="ownerContactNo" id="ownerContactNo" class="form-control-static" style="width:150px;" placeholder="No Data" value="${row.ownerContactNo}"></div></div>');
                                    $(wrapperEdit).on("click",".remove_fieldEdit", function(e){ //user click on remove text
                                    e.preventDefault(); $(this).parent('div').parent('div').remove(); 
                                    });
                                </script>
                                </c:forEach>
                            </c:otherwise>
                            </c:choose>
            </div>
        </div>
        </div>
        </div>
    </div>
        <div class="panel panel-default" id="panel3">
        <div class="panel-heading">
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
                <input type="date" name="stbExpiryDate" id="stbExpiryDate" class="form-control" required min="2000-01-01" max="2040-12-31" value="${stbExpiryDate}" readonly>
                </div>
        </div>        
        <div class="form-group-sm">
            <label for="inputCidbRegNo" class="col-sm-3 control-label">CIDB Registration Number</label>
                <div class="col-sm-8">
                    <input type="text" name="cidbRegNo" id="cidbRegNo" class="form-control" value="${cidbRegNo}" placeholder="CIDB Registration Number" disabled>
                </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCidbGrade" class="col-sm-3 control-label">CIDB Grade</label>
            <div class="col-sm-8">
                    <select name="cidbGrade" id="cidbGrade" class="form-control" disabled>
                        <c:forEach items="${cidbGrade}" var="row">
                            <option value="${row.gradeID}" >${row.gradeCode}</option>
                        </c:forEach>
                    </select>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCidbExpiryDate" class="col-sm-3 control-label">CIDB Expiry Date</label>
                <div class="col-sm-8">
                    <input type="date" name="cidbExpiryDate" id="cidbExpiryDate" class="form-control" required min="2000-01-01" max="2040-12-31" value="${cidbExpiryDate}" readonly>
                </div>
        </div>     
        <div class="form-group-sm">
            <label for="inputSpecialisation" class="col-sm-3 control-label">MRT Specialisation</label>
            <div class="col-sm-8">
                <c:choose>
                <c:when test="${selectedSpecID == []}">
                    <input type="text" name="specialisation" id="specialisation" class="form-control" placeholder="Specialisation" value="No Data" readonly>
                </c:when>
                <c:otherwise>
                        <c:forEach items="${selectedSpecID}" var="row">
                        <input type="text" name="specialisation" id="specialisation" class="form-control" placeholder="Specialisation" value="${row.specialisationCode} - ${row.specialisationDesc}" readonly>
                        </c:forEach>
                </c:otherwise>
              </c:choose>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSpecialisation" class="col-sm-3 control-label">MRT Specialisation (Others)</label>
            <div class="col-sm-8">
                     <input type="text" name="specialisationOthers" id="specialisationOthers" class="form-control" placeholder="Others (Please Specify)" value="${insertedSpecialisationOthers}" disabled>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSpkkCat" class="col-sm-3 control-label">SPKK Category</label>
                <div class="col-sm-8">
                    <c:choose>
                    <c:when test="${selectedSPKKCatID == []}">
                        <input type="text" name="spkkCat" id="spkkCat" class="form-control" placeholder="SPKK Category" value="No Data" readonly>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${selectedSPKKCatID}" var="row">
                        <input type="text" name="spkkCat" id="spkkCat" class="form-control" placeholder="SPKK Category" value="${row.spkkCatCode} - ${row.spkkCatDesc}" readonly>
                        </c:forEach>
                    </c:otherwise>
                    </c:choose>
               </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSpkkSpec" class="col-sm-3 control-label">SPKK Sub-category</label>
                <div class="col-sm-8">
                    <c:choose>
                    <c:when test="${selectedSPKKSpecID == []}">
                        <input type="text" name="spkkSpec" id="spkkSpec" class="form-control" placeholder="SPKK Specialisation" value="No Data" readonly>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${selectedSPKKSpecID}" var="row">
                        <input type="text" name="spkkSpec" id="spkkSpec" class="form-control" placeholder="SPKK Specialisation" value="${row.spkkSpecCode} - ${row.spkkSpecDesc}" readonly>
                        </c:forEach>
                    </c:otherwise>
                    </c:choose>
               </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSpkkSpec" class="col-sm-3 control-label">MOF Code</label>
                <div class="col-sm-8">
                    <c:choose>
                    <c:when test="${selectedMofCodeID == []}">
                        <input type="text" name="mof" id="mof" class="form-control" placeholder="MOF Code" value="No Data" readonly>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${selectedMofCodeID}" var="row">
                        <input type="text" name="mof" id="mof" class="form-control" placeholder="MOF Code" value="${row.mofCode} - ${row.mofDesc}" readonly>
                        </c:forEach>
                    </c:otherwise>
                    </c:choose>
               </div>
        </div>
        
        
         <%--<div class="form-group-sm">
                <label for="inputStbNo" class="col-sm-3 control-label">STB Number</label>
                <div class="col-sm-8">
                <input type="text" name="stbNo" id="stbNo" class="form-control" placeholder="STB Number" value="${stbNo}" readonly>
                </div>
        </div>--%>
            </div>
        </div>
        </div>
        
        <div class="panel panel-default" id="panel4">
        <div class="panel-heading">
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
            <div class="input_fields_wrapEdit2">
                            <c:choose>
                            <c:when test="${registeredPastProject == []}">
                                <div class="form-group-sm"><label for="inputPastProject" class="col-sm-3 control-label"></label><div class="col-sm-9">
                                <input type="number" name="pastProjectYear" min="2009" max="2040" class="form-control-static" readonly>        
                                <input type="text" name="pastProject" id="pastProject" class="form-control-static" style="width:370px;" value="No Data" placeholder="Past Project" readonly>
                                <input type="text" name="pastProjectAmount" id="pastProjectAmount" class="form-control-static" style="width:110px;" value="No Data" placeholder="Amount" readonly></div></div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${registeredPastProject}" var="row" >
                                <script>
                                    var wrapperEdit2 = $(".input_fields_wrapEdit2");
                                    $(wrapperEdit2).append('<div class="form-group-sm"><label for="inputPastProject" class="col-sm-3 control-label"></label><div class="col-sm-9"><input type="number" name="pastProjectYear" min="2009" max="2040" value="${row.pastProjectYear}" class="form-control-static" readonly>\n\
                                    <input type="text" name="pastProject" id="pastProject" class="form-control-static" style="width:370px;" placeholder="No Data" value="${row.pastProject}" readonly>\n\
                                    <input type="text" name="pastProjectAmount" id="pastProjectAmount" style="width:110px;" class="form-control-static" placeholder="No Data" value="${row.pastProjectAmount}" readonly></div></div>'); //add input box
                                    $(wrapperEdit2).on("click",".remove_fieldEdit2", function(e){ //user click on remove text
                                    e.preventDefault(); $(this).parent('div').parent('div').remove(); 
                                    });
                                </script>
                            </c:forEach>
                            </c:otherwise>
                            </c:choose>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputCurrentProject" class="col-sm-3 control-label">List of Current Projects</label>
            <div class="input_fields_wrapEdit3">
                            <c:choose>
                            <c:when test="${registeredCurrProject == []}">
                                <div class="form-group-sm"><label for="inputCurrentProject" class="col-sm-3 control-label"></label><div class="col-sm-9">
                                <input type="number" name="currentProjectYear" min="2009" max="2040" class="form-control-static" readonly>        
                                <input type="text" name="currentProject" id="currentProject" class="form-control-static" style="width:370px;" value="No Data" placeholder="Current Project" readonly> 
                                <input type="text" name="currentProjectAmount" id="currentProjectAmount" class="form-control-static" style="width:110px;" value="No Data" placeholder="Amount" readonly></div></div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${registeredCurrProject}" var="row" >
                                <script>
                                    var wrapperEdit3 = $(".input_fields_wrapEdit3");
                                    $(wrapperEdit3).append('<div class="form-group-sm"><label for="inputCurrentProject" class="col-sm-3 control-label"></label><div class="col-sm-9"><input type="number" name="currentProjectYear" min="2009" max="2040" value="${row.currentProjectYear}" class="form-control-static" readonly>\n\
                                    <input type="text" name="currentProject" id="currentProject" class="form-control-static" style="width:370px;" placeholder="No Data" value="${row.currentProject}" readonly>\n\
                                    <input type="text" name="currentProjectAmount" id="currentProjectAmount" style="width:110px;" class="form-control-static" placeholder="No Data" value="${row.currentProjectAmount}" readonly></div></div>'); //add input box
                                    $(wrapperEdit3).on("click",".remove_fieldEdit3", function(e){ //user click on remove text
                                    e.preventDefault(); $(this).parent('div').parent('div').remove();
                                    });
                                </script>
                                </c:forEach>
                            </c:otherwise>
                            </c:choose>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputAwardGov" class="col-sm-3 control-label">List of Awards/Recognitions</label>
             <div class="input_fields_wrapEdit4">
                            <c:choose>
                            <c:when test="${registeredAwardGov == []}">
                                <div class="form-group-sm"><label for="inputAwardGov" class="col-sm-3 control-label"></label><div class="col-sm-9">
                                <input type="number" name="awardGovYear" min="2009" max="2040" class="form-control-static" readonly>
                                <input type="text" name="awardGov" id="awardGov" class="form-control-static" style="width:450px;" value="No Data" placeholder="Award Gov" readonly>
                                </div></div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${registeredAwardGov}" var="row" >
                                <script>
                                    var wrapperEdit4 = $(".input_fields_wrapEdit4");
                                    $(wrapperEdit4).append('<div class="form-group-sm"><label for="inputAwardGov" class="col-sm-3 control-label"></label><div class="col-sm-9"><input type="number" name="awardGovYear" min="2009" max="2040" value="${row.awardGovYear}" class="form-control-static" readonly>\n\
                                    <input type="text" name="awardGov" id="awardGov" class="form-control-static" style="width:450px;" placeholder="No Data" value="${row.awardGov}" readonly></div></div>');  
                                    $(wrapperEdit4).on("click",".remove_fieldEdit4", function(e){ //user click on remove text
                                    e.preventDefault(); $(this).parent('div').parent('div').remove();
                                    });
                                </script>
                                </c:forEach>
                            </c:otherwise>
                            </c:choose>
            </div>
        </div>
        </div>
            </div>
        </div>
        
        <div class="panel panel-default" id="panel5">
        <div class="panel-heading">
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
            <label for="inputFormType" class="col-sm-3 control-label">Form Type</label>
            <div class="col-sm-8">
                <c:forEach items="${formType}" var="row">
                    <input type="radio" name="formType" id="formType" value="${row.formTypeID}" disabled>${row.formTypeDesc} &nbsp;         
                </c:forEach>
                <script>
                        $('input[name="formType"][value="${selectedFormType}"]').prop("checked", true);
                </script>
                        <input type="text" name="formTypeOthers" id="formTypeOthers" class="form-control-static" style="width:250px;" placeholder="Others" value="${insertedFormTypeOthersDesc}" disabled>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputFormSubmittedDate" class="col-sm-3 control-label">Form Submission Date</label>
            <div class="col-sm-8">
                <input type="date" name="dateFormSubmitted" class="form-control" id="dateFormSubmitted" min="2000-01-01" max="2040-12-31" value="${dateFormSubmitted}" readonly>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputAppStatus" class="col-sm-3 control-label">Application Status</label>
            <div class="col-sm-8">
                <input type="radio" name="appStatus" id="appStatus" value="PASSED" disabled> Passed &nbsp;
                <input type="radio" name="appStatus" id="appStatus" value="PENDING"> Pending &nbsp;
                <input type="radio" name="appStatus" id="appStatus" value="FAILED" disabled> Failed &nbsp;
                <input type="radio" name="appStatus" id="appStatus" value="BLACKLISTED"> Blacklisted &nbsp;
                <input type="radio" name="appStatus" id="appStatus" value="OTHERS"> Others &nbsp;
                <input type="radio" name="appStatus" id="appStatus" value="EXPIRED"> Expired
                <script>
                        $('input[name="appStatus"][value="${appStatus}"]').prop("checked", true);
                </script>
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputReasonFailed" class="col-sm-3 control-label">Remarks</label>
            <div class="col-sm-8">
                <textarea rows="2" cols="50" name="reasonFailed" class="form-control" id="reasonFailed" placeholder="Reason for Failed" readonly>${reasonFailed}</textarea>
            </div>
        </div>
        
            </div>
        </div>
        </div>
        <div class="panel panel-default" id="panel6">
        <div class="panel-heading">
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
            <tr>
                <td><label for="inputAttachScreenForm">Screening Form</label></td>
            </tr>
            <tr><td>
                    <c:choose>
                    <c:when test="${insertedAttachmentScreenForm == []}">
                        <label for="inputInsertedAttachmentScreenForm" class="col-sm-9 control-label">No File</label>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${insertedAttachmentScreenForm}" var="row">
                            <ul><li><a href="ContractorServlet?action=DownloadAttachment&fileName=${row.attachScreenFormFileName}&oriFileName=${row.oriAttachScreenFormFileName}&typeID=${row.attachScreenFormMasterID}" >${row.oriAttachScreenFormFileName}</a><br>   
                            </li></ul>
                            </c:forEach>
                    </c:otherwise>
                    </c:choose>
            </td></tr>
            <tr class="spaceUnder3"><td></td></tr>
            <tr>
                <td><label for="inputAttachStb">STB Certificate</label></td>
            </tr>
            <tr><td>
                    <c:choose>
                    <c:when test="${insertedAttachmentStbCert == []}">
                        <label for="inputInsertedAttachmentStbCert" class="col-sm-9 control-label">No File</label>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${insertedAttachmentStbCert}" var="row">
                            <ul><li><a href="ContractorServlet?action=DownloadAttachment&fileName=${row.attachStbCertFileName}&oriFileName=${row.oriAttachStbCertFileName}&typeID=${row.attachStbCertMasterID}" >${row.oriAttachStbCertFileName}</a><br>   
                            </li></ul>
                            </c:forEach>
                    </c:otherwise>
                    </c:choose>
            </td></tr>
            <tr class="spaceUnder3"><td></td></tr>
            <tr>
                <td><label for="inputAttachCidb">CIDB Certificate</label></td>
            </tr>
            <tr><td>
                    <c:choose>
                    <c:when test="${insertedAttachmentCidbCert == []}">
                        <label for="inputInsertedAttachmentCidbCert" class="col-sm-9 control-label">No File</label>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${insertedAttachmentCidbCert}" var="row">
                            <ul><li><a href="ContractorServlet?action=DownloadAttachment&fileName=${row.attachCidbCertFileName}&oriFileName=${row.oriAttachCidbCertFileName}&typeID=${row.attachCidbCertMasterID}" >${row.oriAttachCidbCertFileName}</a><br>   
                            </li></ul>
                            </c:forEach>
                    </c:otherwise>
                    </c:choose>
            </td></tr>
            <tr class="spaceUnder3"><td></td></tr>
            <tr>
                <td><label for="inputAttachAppForm">Application Form</label></td>
            </tr>
            <tr><td>
                    <c:choose>
                    <c:when test="${insertedAttachmentAppForm == []}">
                        <label for="inputInsertedAttachmentAppForm" class="col-sm-9 control-label">No File</label>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${insertedAttachmentAppForm}" var="row">
                            <ul><li><a href="ContractorServlet?action=DownloadAttachment&fileName=${row.attachAppFormFileName}&oriFileName=${row.oriAttachAppFormFileName}&typeID=${row.attachAppFormMasterID}" >${row.oriAttachAppFormFileName}</a><br>   
                            </li></ul>
                            </c:forEach>
                    </c:otherwise>
                    </c:choose>
            </td></tr>
            <tr class="spaceUnder3"><td></td></tr>
            <tr>
                <td><label for="inputAttachMisc">Misc</label></td>
            </tr>
            <tr><td>
                    <c:choose>
                    <c:when test="${insertedAttachmentContractorMisc == []}">
                        <label for="inputInsertedAttachmentContractorMisc" class="col-sm-9 control-label">No File</label>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${insertedAttachmentContractorMisc}" var="row">
                            <ul><li><a href="ContractorServlet?action=DownloadAttachment&fileName=${row.attachContractorMiscFileName}&oriFileName=${row.oriAttachContractorMiscFileName}&typeID=${row.attachContractorMiscMasterID}" >${row.oriAttachContractorMiscFileName}</a><br>   
                            </li></ul>
                            </c:forEach>
                    </c:otherwise>
                    </c:choose>
            </td></tr>
            </table>
            </div>
        </div>
            </div>
        </div>
        </div>
        </div>
        <div class="form-group"> 
            <div class="col-sm-offset-1">
        <a href="ContractorServlet?action=ContractorListView" style=""><font color="#000000"><input type="button" class="form-control-static" value="Back" style=""/></font></a>
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
    <script>
    function setSelectedIndex(s, valsearch)
    {
    // Loop through all the items in drop down list
    for (i = 0; i< s.options.length; i++)
    { 
    if (s.options[i].value === valsearch)
    {
    // Item is found. Set its property and exit
    s.options[i].selected = true;
    break;
    }
    }
    return;
    }
    setSelectedIndex(document.getElementById("correspondenceState"),"${selectedCorrespondenceStateID}");
    </script>
    <script>
    function setSelectedIndex2(s2, valsearch2)
    {
    // Loop through all the items in drop down list
    for (i = 0; i< s2.options.length; i++)
    { 
    if (s2.options[i].value === valsearch2)
    {
    // Item is found. Set its property and exit
    s2.options[i].selected = true;
    break;
    }
    }
    return;
    }
    setSelectedIndex2(document.getElementById("companySsmState"),"${selectedSsmStateID}");
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
    setSelectedIndex3(document.getElementById("cidbGrade"),"${selectedCidbID}");
    </script>
    <script>
    function setSelectedIndex4(s4, valsearch3)
    {
    // Loop through all the items in drop down list
    for (i = 0; i< s4.options.length; i++)
    { 
    if (s4.options[i].value === valsearch3)
    {
    // Item is found. Set its property and exit
    s4.options[i].selected = true;
    break;
    }
    }
    return;
    }
    setSelectedIndex4(document.getElementById("program"),"${selectedProgramID}");
    </script>
    <script>
    function setSelectedIndex5(s5, valsearch4)
    {
    // Loop through all the items in drop down list
    for (i = 0; i< s5.options.length; i++)
    { 
    if (s5.options[i].value === valsearch4)
    {
    // Item is found. Set its property and exit
    s5.options[i].selected = true;
    break;
    }
    }
    return;
    }
    setSelectedIndex5(document.getElementById("contractorType"),"${selectedContractorTypeID}");
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
    <script language="javascript">
      $(document).ready(function() {
        var element2 = document.getElementById("registeredOthersRemark");
        
        var $cat = $("#program");
        var value = $cat.val();
        if (value === "4") {
        element2.style.display = 'block';  
         }
        else {
        element2.style.display = 'none';  
        }
            });

      
    </script>
    <script language="javascript">
      $(document).ready(function() {
        //document.getElementById("ssmExpiryDate").style.backgroundColor = "red";
        //var element2 = document.getElementById("ssmExpiryDate");
        var today = new Date();
        var ssmExpiryDate = document.getElementById("ssmExpiryDate").value;
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear();

        if(dd<10) {
            dd='0'+dd;
        } 

        if(mm<10) {
            mm='0'+mm;
        } 

        today = yyyy+'-'+mm+'-'+dd;
        
        date1 = new Date(today);
        date2 = new Date(ssmExpiryDate);
 
        if (date2 < date1) {
            document.getElementById("ssmExpiryDate").style.backgroundColor = "red";
        }
        });

      
    </script>
    <script language="javascript">
      $(document).ready(function() {
        //document.getElementById("ssmExpiryDate").style.backgroundColor = "red";
        //var element2 = document.getElementById("ssmExpiryDate");
        var today = new Date();
        var ssmExpiryDate = document.getElementById("cidbExpiryDate").value;
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear();

        if(dd<10) {
            dd='0'+dd;
        } 

        if(mm<10) {
            mm='0'+mm;
        } 

        today = yyyy+'-'+mm+'-'+dd;
        
        date1 = new Date(today);
        date2 = new Date(ssmExpiryDate);
 
        if (date2 < date1) {
            document.getElementById("cidbExpiryDate").style.backgroundColor = "red";
        }
        });

      
    </script>
    <script language="javascript">
      $(document).ready(function() {
        //document.getElementById("ssmExpiryDate").style.backgroundColor = "red";
        //var element2 = document.getElementById("ssmExpiryDate");
        var today = new Date();
        var ssmExpiryDate = document.getElementById("stbExpiryDate").value;
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear();

        if(dd<10) {
            dd='0'+dd;
        } 

        if(mm<10) {
            mm='0'+mm;
        } 

        today = yyyy+'-'+mm+'-'+dd;
        
        date1 = new Date(today);
        date2 = new Date(ssmExpiryDate);
 
        if (date2 < date1) {
            document.getElementById("stbExpiryDate").style.backgroundColor = "red";
        }
        });

      
    </script>
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
</html>