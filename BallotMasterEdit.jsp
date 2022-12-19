<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>

    <head>
        <meta charset="UTF-8">
        <title>BCMS | Ballot Master Edit</title>
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
                        Ballot Master Edit
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Ballot Master Edit</li>
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
    #ssmCertNo option{
    display:none;
    }

    #ssmCertNo option.label{
    display:block;
    }
    

</style>
    <form action="BallotServlet?action=ballotmaster_update"  method="POST" enctype="multipart/form-data" accept-charset='UTF-8'>
        <input type="hidden" name="ballotMasterID" value="${ballotMasterID}">  
        <div class="panel-group" id="accordion">
             <div class="panel panel-default" id="panel1">
        <div class="panel-heading bg-light-blue">
             <h4 class="panel-title">
        <a data-toggle="collapse" data-target="#collapseOne" 
           href="#collapseOne" class="collapsed">
          Ballot Master Details
        </a>
      </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
        <div class="form-group-sm">
            <label for="inputBallotTitle" class="col-sm-3 control-label">Ballot Title</label>
            <div class="col-sm-8">
                <input type="text"  size="50" id="ballotTitle" name="ballotTitle" class="form-control" placeholder="Enter Ballot Title" value="${ballotTitle}">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputBallotDate" class="col-sm-3 control-label">Balloting Date</label>
            <div class="col-sm-8">
                <input type="date" name="ballotDate" id="ballotDate" class="form-control" min="2000-01-01" max="2040-12-31" value="${ballotDate}">
            </div>
        </div>
        <div class="form-group-sm">
            <label for="inputBallotVenue" class="col-sm-3 control-label">Balloting Venue</label>
            <div class="col-sm-8">
                <input type="text"  size="50" id="ballotVenue" name="ballotVenue" class="form-control" placeholder="Enter Ballot Venue" value="${ballotVenue}">
            </div>
        </div>
        <%--<div class="form-group-sm">
            <label for="inputCidbGrade" class="col-sm-3 control-label">CIDB Grade</label>
                <div class="col-sm-8">
                    <select name="cidbGrade" id="cidbGrade" multiple="multiple" class="form-control">
                            <c:forEach items="${cidbGrade}" var="row">
                            <option value="${row.gradeID}">${row.gradeCode}</option>
                            <c:forEach items="${selectedCidbGrade}" var="row2">
                              <c:choose>
                              <c:when test="${row.gradeID == row2.gradeID}">
                                  <script>
                                       $('#cidbGrade option[value="' + ${row.gradeID} +'"]').prop("selected", true);
                                  </script>
                              </c:when>
                              </c:choose>
                           </c:forEach>
                            </c:forEach>
                      </select>
               </div>
        </div>
        <div class="form-group-sm">
            <label for="inputSpecSow" class="col-sm-3 control-label">Specialisation & SoW</label>
                <div class="col-sm-8">
                    <select name="specSow" id="specSow" multiple="multiple" size="6" class="form-control">
                            <c:forEach items="${specSow}" var="row">
                            <option value="${row.subContractSpecID}">${row.specialisationCode} - ${row.scopeWork}</option>
                            <c:forEach items="${selectedSpecSow}" var="row2">
                              <c:choose>
                              <c:when test="${row.subContractSpecID == row2.subContractSpecID}">
                                  <script>
                                       $('#specSow option[value="' + ${row.subContractSpecID} +'"]').prop("selected", true);
                                  </script>
                              </c:when>
                              </c:choose>
                           </c:forEach>
                            </c:forEach>
                      </select>
               </div>
        </div>--%>  
        <div class="form-group-sm">
            <label for="inputAttachBallot" class="col-sm-3 control-label">Attachment</label>
            <div class="col-sm-8">
                <input type="file" name="attachBallotSession" id="attachBallotSession" class="form-control-static" multiple="multiple"/>
            <div class="input_fields_wrapEdit8">
                        
                        <div class="">
                            <c:forEach items="${insertedAttachmentBallotSession}" var="row" >
                                <ul>
                            <script language="javascript">
                                var wrapperEdit8 = $(".input_fields_wrapEdit8");
                                $(wrapperEdit8).append('<div class="form-group-sm"><li><a id="test" href="BallotServlet?action=DownloadAttachment&fileName=${row.attachBallotSessionFileName}&oriFileName=${row.oriAttachBallotSessionFileName}&typeID=${row.attachBallotSessionMasterID}" rel="${row.attachBallotSessionID}">${row.oriAttachBallotSessionFileName}</a>&nbsp;&nbsp;<input type="image" rel="${row.attachBallotSessionID}" class="remove_fieldEdit8" src="images/close.png"/></li></div>');     
                                
                                
                            </script>
                                </ul>
                                <input type="hidden" id="attachBallotSessionID" value="${row.attachBallotSessionID}" />
                            </c:forEach>
                            <script>
                                var wrapperEdit9 = $(".input_fields_wrapEdit8");
                                $(wrapperEdit9).on("click",".remove_fieldEdit8", function(e){ //user click on remove text
                                 
                                if (confirm("Confirm to delete attachment?") === true) {
                                    //var attachAppFormID = $("#attachAppFormID").val();
                                    var attachBallotSessionID = $(this).attr("rel");
                                    var xhr = new XMLHttpRequest();  
                                    xhr.open("POST","BallotServlet?action=deleteAttachmentBallotSession&attachID="+attachBallotSessionID,true);
                                    xhr.send();
                                    e.preventDefault();
                                    $(this).parent('li').parent('div').remove();
                                    alert("Attachment Deleted");
                                }else {
                                    
                                    e.preventDefault();
                                }
                                });
                                
                            </script>
                    </div>
                        
            </div>
            </div>
        </div>
        </div>
        </div>
    </div>
         </div>
        <div class="form-group"> 
            <div class="col-sm-offset-1">
                <input type="submit" value="Save Ballot Master" id="SaveBallotMaster" class="form-control-static" onclick="return confirm('Confirm to save ballot master?')"/>
                <a href="BallotMasterList.jsp" style=""><font color="#000000"><input type="button" class="form-control-static" value="Cancel" style=""/></font></a>
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
        function alertBallot()
{
var javascriptVar="${alertballot}";
if(javascriptVar === 'true'){
    alert("Ballot Master Information Successfully Updated");
    window.location = "BallotMasterList.jsp";
    return true;
    }
    else if (javascriptVar === 'false') {
    alert("An Error Occured. Please Try Again");   
    return false;
    }
    
}
</script>

<script>
        $(function(){
        var $cat = $("#ResetBallot"),
        $subcat = $("#ssmCertNo");
        $subcat2 = $("#cidbGrade");
        $cat.on("click",function(){
        
        $subcat.find("option").attr("style","");
        $subcat.val("");
        $subcat2.find("option").attr("style","");
        $subcat2.val("");
        
            });
        });
    </script>
