<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | Edit Contractor</title>
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
    <body class="skin-black" onload="alertContractor()">
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
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="LoginServlet?action=logout">Logout <span class="glyphicon glyphicon-log-out"></span></a>

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
                        Edit Contractor
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Edit Contractor</li>
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
</style><h4 style="h4">Company Details</h4>
    <form action="ContractorServlet?action=contractor_update"  method="POST">
        <input type="hidden" name="contractorID" value="${contractorID}">
        <table align="left">
        <tr class="spaceUnder2">
                <td align="right">
                    Company Name &nbsp;
                </td> 
                <td align="left">
                    <input type="text" size="50" name="companyName" required placeholder="Enter Company Name" value="${companyName}">
                </td>
                
        </tr>
        <tr class="spaceUnder2">
                <td align="right">
                    SSM Certificate Number &nbsp;
                </td> 
                <td align="left">
                    <input type="text"  size="50" name="ssmCertNo" required placeholder="Enter SSM Certificate Number" value="${ssmCertNo}">
                </td>
                
        </tr>
        <tr class="spaceUnder2">
                <td align="right">
                    SSM Expiry Date &nbsp;
                </td> 
                <td align="left">
                    <input type="date" name="ssmExpiryDate" id="ssmExpiryDate" required min="2000-01-01" max="2040-12-31" value="${ssmExpiryDate}">
                </td>
                
        </tr>
        <tr class="spaceUnder2">
                <td align="right">
                    CIDB Grade &nbsp;
                </td> 
                <td align="left">
                    <select name="cidbGrade" id="cidbGrade" class="selectoption">
                      <c:forEach items="${cidbGrade}" var="row">
                          <option value="${row.gradeID}">${row.gradeCode}</option>
                      </c:forEach>
                    </select>
                </td>
                
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    SPKK Code &nbsp;
                </td> 
                <td align="left">
                      <c:forEach items="${spkk}" var="row">
                          <input type="checkbox" name="spkkChkBox" id="spkkChkBox" value="${row.spkkID}"> ${row.spkkCode} - ${row.spkkDesc}<br>  
                           <c:forEach items="${selectedSPKKID}" var="row2">
                              <c:choose>
                              <c:when test="${row.spkkID == row2.spkkID}">
                                  <script>
                                       //$("input[value=${row.spkkID}]").prop('checked', true);
                                       $('input[name="spkkChkBox"][value=${row.spkkID}]').prop("checked", true);
                                  </script>
                              </c:when>
                              </c:choose>
                           </c:forEach>
                     </c:forEach> 
                </td>
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    CIDB Expiry Date &nbsp;
                </td> 
                <td align="left">
                    <input type="date" name="cidbExpiryDate" id="cidbExpiryDate" required min="2000-01-01" max="2040-12-31" value="${cidbExpiryDate}">
                </td>
                
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    STB Expiry Date &nbsp;
                </td> 
                <td align="left">
                    <input type="date" name="stbExpiryDate" id="stbExpiryDate" required min="2000-01-01" max="2040-12-31" value="${stbExpiryDate}">
                </td>
                
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    Company Registered Address &nbsp;
                </td> 
                <td align="left">
                    <input type="text"  size="50" name="companyRegAddress" required placeholder="Enter Company Registered Address" value="${companyRegAddress}">
                </td>
                
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    Address - SSM &nbsp;
                </td> 
                <td align="left">
                    <input type="text"  size="50" name="ssmAddress" required placeholder="Enter Address - SSM" value="${ssmAddress}">
                </td>
                
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    Address - CIDB &nbsp;
                </td> 
                <td align="left">
                    <input type="text"  size="50" name="cidbAddress" required placeholder="Enter Address - CIDB" value="${cidbAddress}">
                </td>
                
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    Office Phone Number &nbsp;
                </td> 
                <td align="left">
                    <input type="text"  size="50" name="officePhoneNo" required placeholder="Enter Office Phone Number" value="${officePhoneNo}">
                </td>
                
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    Office Fax Number &nbsp;
                </td> 
                <td align="left">
                    <input type="text"  size="50" name="officeFaxNo" placeholder="Enter Office Fax Number" value="${officeFaxNo}">
                </td>
                
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    PIC Mobile Number &nbsp;
                </td> 
                <td align="left">
                    <input type="text"  size="50" name="picMobileNo" placeholder="Enter PIC Mobile Number" value="${picMobileNo}">
                </td>
                
            </tr>
            <tr class="spaceUnder">
                <td align="right">
                    List of Company Owners & IC No.&nbsp;
                </td> 
                <td align="left">
                    <div class="input_fields_wrapEdit">
                        <div class="">
                            <c:forEach items="${registeredCompanyOwner}" var="row" >
                            <script>
                                var wrapperEdit = $(".input_fields_wrapEdit");
                                $(wrapperEdit).append('<div><input type="text" size="50" name="companyOwner" placeholder="Enter Company Owner" value="${row.companyOwner}"/> <input type="text" size="16" name="icNo" placeholder="Enter IC No." value="${row.icNo}"><a href="#" class="remove_fieldEdit"> Remove</a></div>');
                                $(wrapperEdit).on("click",".remove_fieldEdit", function(e){ //user click on remove text
                                e.preventDefault(); $(this).parent('div').remove(); 
                                });
                            </script>
                            </c:forEach>
                    </div>
                    </div>
                    <div class="input_fields_wrap">
                        <div class="">
                            <input type="text" size="50" name="companyOwner" id="companyOwner" placeholder="Enter Company Owner" value="${row.companyOwner}">
                            <input type="text" size="16" name="icNo" id="icNo" placeholder="Enter IC No." value="${row.icNo}">
                            <button class="add_field_button">Add Owner</button>
                           
                    </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <h4 style="h4">Contractor's’ Work Experience</h4>
                </td>
            </tr>
            <tr class="spaceUnder">
                <td align="right">
                    List of Past Projects within 5 Years &nbsp;
                </td> 
                <td align="left">
                    <div class="input_fields_wrapEdit2">
                        <div class="">
                            <c:forEach items="${registeredPastProject}" var="row" >
                            <script>
                                var wrapperEdit2 = $(".input_fields_wrapEdit2");
                                $(wrapperEdit2).append('<div><input type="text" size="50" name="pastProject" placeholder="Enter Past Project" value="${row.pastProject}"><a href="#" class="remove_fieldEdit2"> Remove</a></div>');
                                $(wrapperEdit2).on("click",".remove_fieldEdit2", function(e){ //user click on remove text
                                e.preventDefault(); $(this).parent('div').remove(); 
                                });
                            </script>
                            </c:forEach>
                    </div>
                    </div>
                    <div class="input_fields_wrap2">
                        <div class="">
                    <input type="text" size="50" name="pastProject" id="pastProject" placeholder="Enter Past Project">
                    <button class="add_field_button2">Add Past Project</button>
                    </div>
                    </div>
                </td>
            </tr>
            <tr class="spaceUnder">
                <td align="right">
                    List of Current Projects &nbsp;
                </td> 
                <td align="left">
                    <div class="input_fields_wrapEdit3">
                        <div class="">
                            <c:forEach items="${registeredCurrProject}" var="row" >
                            <script>
                                var wrapperEdit3 = $(".input_fields_wrapEdit3");
                                $(wrapperEdit3).append('<div><input type="text" size="50" name="currentProject" placeholder="Enter Current Project" value="${row.currentProject}"><a href="#" class="remove_fieldEdit3"> Remove</a></div>'); 
                                $(wrapperEdit3).on("click",".remove_fieldEdit3", function(e){ //user click on remove text
                                e.preventDefault(); $(this).parent('div').remove();
                                });
                            </script>
                            </c:forEach>
                    </div>
                    </div>
                    <div class="input_fields_wrap3">
                        <div class="">
                    <input type="text" size="50" name="currentProject" id="currentProject" placeholder="Enter Current Project">
                    <button class="add_field_button3">Add Current Project</button>
                    </div>
                    </div>
                </td>
            </tr>
            <tr class="spaceUnder">
                <td align="right">
                    List of Awards/Recognitions by Malaysian Government &nbsp;
                </td> 
                <td align="left">
                    <div class="input_fields_wrapEdit4">
                    <div class="">
                            <c:forEach items="${registeredAwardGov}" var="row" >
                            <script>
                                var wrapperEdit4 = $(".input_fields_wrapEdit4");
                                $(wrapperEdit4).append('<div><input type="text" size="50" name="awardGov" placeholder="Enter Awards/Recognitions" value="${row.awardGov}"><a href="#" class="remove_fieldEdit4"> Remove</a></div>');
                                $(wrapperEdit4).on("click",".remove_fieldEdit4", function(e){ //user click on remove text
                                e.preventDefault(); $(this).parent('div').remove();
                                });
                            </script>
                            </c:forEach>
                    </div>
                    </div>    
                    <div class="input_fields_wrap4">
                        <div class="">
                    <input type="text" size="50" name="awardGov" id="awardGov" placeholder="Enter Awards/Recognitions">
                    <button class="add_field_button4">Add Awards/Recognitions</button>
                    </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <h4 style="h4">Application Details</h4>
                </td>
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    Form submitted on &nbsp;
                </td> 
                <td align="left">
                    <input type="date" name="dateFormSubmitted" id="dateFormSubmitted" min="2000-01-01" max="2040-12-31" value="${dateFormSubmitted}">
                </td>
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    Application Status &nbsp;
                </td> 
                <td align="left">
                    <input type="radio" name="appStatus" id="appStatus" value="SUCCESS"> Success &nbsp;
                    <input type="radio" name="appStatus" id="appStatus" value="PENDING"> Pending &nbsp;
                    <input type="radio" name="appStatus" id="appStatus" value="FAILED"> Failed
                    <script>
                        $('input[name="appStatus"][value=${appStatus}]').prop("checked", true);
                    </script>
                </td>
            </tr>
            <tr class="spaceUnder2">
                <td align="right">
                    Reason for Failed &nbsp;
                </td> 
                <td align="left">
                    <textarea rows="2" cols="50" name="reasonFailed" id="reasonFailed" placeholder="Enter Reason for Failed">${reasonFailed}</textarea>
                </td>
            </tr>
            <tr>&nbsp;
                <td>&nbsp;</td>
                <td>
                <input type="submit" value="Save Contractor" id="SaveContractor" onclick="return confirm('Confirm to update contractor information?')"/>
                <input type="button" value="Cancel" id="CancelContractor" onclick="location.href='ContractorList.jsp';"/>
                </td>
            </tr>
        </table>
        <%--<input type="button" value="Get Box" id="GetBox" onclick="addBoxFunction()"/>--%>
    </form>
    <script language="javascript">
      $(document).ready(function() {
    var max_fields      = 10; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID
    
    var max_fields2      = 10; //maximum input boxes allowed
    var wrapper2         = $(".input_fields_wrap2"); //Fields wrapper
    var add_button2      = $(".add_field_button2"); //Add button ID
    
    var max_fields3      = 10; //maximum input boxes allowed
    var wrapper3         = $(".input_fields_wrap3"); //Fields wrapper
    var add_button3      = $(".add_field_button3"); //Add button ID
    
    var max_fields4      = 10; //maximum input boxes allowed
    var wrapper4         = $(".input_fields_wrap4"); //Fields wrapper
    var add_button4      = $(".add_field_button4"); //Add button ID
    
    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div><input type="text" size="50" name="companyOwner" placeholder="Enter Company Owner"/> <input type="text" size="16" name="icNo" placeholder="Enter IC No."><a href="#" class="remove_field"> Remove</a></div>'); //add input box
        }
    });
    
    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent('div').remove(); x--;
    });
    
    var x2 = 1; //initlal text box count
    $(add_button2).click(function(e2){ //on add input button click
        e2.preventDefault();
        if(x2 < max_fields2){ //max input box allowed
            x2++; //text box increment
            $(wrapper2).append('<div><input type="text" size="50" name="pastProject" placeholder="Enter Past Project"><a href="#" class="remove_field2"> Remove</a></div>'); //add input box
        }
    });
    
    $(wrapper2).on("click",".remove_field2", function(e2){ //user click on remove text
        e2.preventDefault(); $(this).parent('div').remove(); x2--;
    });
    
    var x3 = 1; //initlal text box count
    $(add_button3).click(function(e3){ //on add input button click
        e3.preventDefault();
        if(x3 < max_fields3){ //max input box allowed
            x3++; //text box increment
            $(wrapper3).append('<div><input type="text" size="50" name="currentProject" placeholder="Enter Current Project"><a href="#" class="remove_field3"> Remove</a></div>'); //add input box
        }
    });
    
    $(wrapper3).on("click",".remove_field3", function(e3){ //user click on remove text
        e3.preventDefault(); $(this).parent('div').remove(); x3--;
    });
    
     var x4 = 1; //initlal text box count
    $(add_button4).click(function(e4){ //on add input button click
        e4.preventDefault();
        if(x4 < max_fields4){ //max input box allowed
            x4++; //text box increment
            $(wrapper4).append('<div><input type="text" size="50" name="awardGov" placeholder="Enter Awards/Recognitions"><a href="#" class="remove_field4"> Remove</a></div>'); //add input box
        }
    });
    
    $(wrapper4).on("click",".remove_field4", function(e4){ //user click on remove text
        e4.preventDefault(); $(this).parent('div').remove(); x4--;
    });
});
      
    </script>
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
    setSelectedIndex(document.getElementById("cidbGrade"),"${selectedCidbID}");
    
    
    </script>
    <script language="javascript">
        function alertContractor()
{
var javascriptVar="${alertcontractor}";
console.log("d" + javascriptVar);
if(javascriptVar === 'true'){
    alert("Contractor Information Successfully Updated");
    window.location = "ContractorList.jsp";
    return true;
    }
    else if (javascriptVar === 'false') {
    alert("An Error Occured. Please Try Again");   
    return false;
    }
    
}
    </script>
<script>
$(function() {
    window.invalidate_input = function() {
        if ($('input[name=appStatus]:checked').val() === "FAILED"){
            document.getElementById("reasonFailed").value= "${reasonFailed}";
            $('#reasonFailed').removeAttr('disabled');
            
        }
        else {
            document.getElementById("reasonFailed").value= "";
            $('#reasonFailed').attr('disabled', 'disabled');
        }
            
    };
    
    $("input[name=appStatus]").change(invalidate_input);
    
    invalidate_input();
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