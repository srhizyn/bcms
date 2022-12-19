<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="UTF-8">
        <title>BCMS | Dashboard</title>
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
       
        
    </head>
    
    <body class="skin-blue">
        
        <!-- header logo: style can be found in header.less -->
        
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">                
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="images/user.png" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>Hello, <%=session.getAttribute("displayName") %></p>

                            
                        </div>
                    </div>
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <c:choose>
                        <c:when test="${sessionScope.user.roleID == 3}">
                          <li class="active">
                            <a href="home.jsp">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                            </a>
                              
                           </li>
<!--                           <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i>
                                <span>Project</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="home.jsp"><i class="fa fa-angle-double-right"></i>SBK-LINE (KJLINE - LINE 1)</a></li>
                                <li><a href="home.jsp"><i class="fa fa-angle-double-right"></i>SSP-LINE (PYLINE - LINE 2)</a></li>
                                <li><a href="home.jsp"><i class="fa fa-angle-double-right"></i>MRTS (RTSLINK)</a></li>
                                <li><a href="home.jsp"><i class="fa fa-angle-double-right"></i>MRT3 (MRTL - LINE 3)</a></li>
                            </ul>
                            </li>-->
                           <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i>
                                <span>Contractor</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="fwdContractorReg.jsp"><i class="fa fa-angle-double-right"></i>Contractor Registration</a></li>
                                <li><a href="fwdContractorList.jsp"><i class="fa fa-angle-double-right"></i>Contractor List</a></li>
                            </ul>
                            </li>
                            <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i>
                                <span>Contract</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="fwdContractReg.jsp"><i class="fa fa-angle-double-right"></i>Contract Particulars</a></li>
                                <li><a href="ContractRegList.jsp"><i class="fa fa-angle-double-right"></i>Contract List</a></li>
                                <li><a href="fwdSubContractReg.jsp"><i class="fa fa-angle-double-right"></i>Sub Contract Particulars</a></li>
                                <li><a href="fwdSubContractList.jsp"><i class="fa fa-angle-double-right"></i>Sub Contract List</a></li>
                                
                            </ul>
                            </li>
                            <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i>
                                <span>Ballot</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="fwdBallotMaster.jsp"><i class="fa fa-angle-double-right"></i>Ballot Master Registration</a></li>
                                <li><a href="BallotMasterList.jsp"><i class="fa fa-angle-double-right"></i>Ballot Master List</a></li>
                                <li><a href="fwdBallot.jsp"><i class="fa fa-angle-double-right"></i>Ballot Registration</a></li>
                                <li><a href="BallotList.jsp"><i class="fa fa-angle-double-right"></i>Ballot List</a></li>
                            </ul>
                            </li>
                            <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i>
                                <span>Award</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="fwdContractAwardReg.jsp"><i class="fa fa-angle-double-right"></i>Contract Award Registration</a></li>
                                <li><a href="ContractAwardRegList.jsp"><i class="fa fa-angle-double-right"></i>Contract Award List</a></li>
                                <li><a href="fwdDeclineContractAwardReg.jsp"><i class="fa fa-angle-double-right"></i>Declination Registration</a></li>
                                <li><a href="DeclineContractAwardList.jsp"><i class="fa fa-angle-double-right"></i>Declination List</a></li>
                            </ul>
                            </li>
                            <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i>
                                <span>Key Issues</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="fwdMattersTrackingList.jsp"><i class="fa fa-angle-double-right"></i>Key Issues List</a></li>
                            </ul>
                            </li>
                            <li class="treeview">
                            <a href="#">
                                <i class="fa fa-table"></i>
                                <span>Reports</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="rptContractorList.jsp"><i class="fa fa-angle-double-right"></i>Ballot Qualified List Report</a></li>
                                <li><a href="rptContractorMasterList.jsp"><i class="fa fa-angle-double-right"></i>Contractor Master List Report</a></li>
                                <li><a href="CertificateListReport.jsp"><i class="fa fa-angle-double-right"></i>Certificate List Report</a></li>
                                <li><a href="ApplicationStatusListReport.jsp"><i class="fa fa-angle-double-right"></i>Application Status List Report</a></li>
                                <li><a href="BallotListReport.jsp"><i class="fa fa-angle-double-right"></i>Ballot List Report</a></li>
                            </ul>
                            </li>
                        </c:when>
                        <c:when test="${sessionScope.user.roleID == 2}">
                          <li class="active">
                            <a href="home.jsp">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                            </a>
                           </li>
                           <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i>
                                <span>Contractor</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="fwdContractorList.jsp"><i class="fa fa-angle-double-right"></i>Contractor List</a></li>
                            </ul>
                            </li>
                            <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i>
                                <span>Contract</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="ContractRegList.jsp"><i class="fa fa-angle-double-right"></i>Contract List</a></li>
                                <li><a href="fwdSubContractList.jsp"><i class="fa fa-angle-double-right"></i>Sub Contract List</a></li>
                                <li><a href="ContractAwardRegList.jsp"><i class="fa fa-angle-double-right"></i>Contract Award List</a></li>
                                <li><a href="fwdMattersTrackingList.jsp"><i class="fa fa-angle-double-right"></i>Matters Tracking List</a></li>
                                
                            </ul>
                            </li>
                            <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i>
                                <span>Ballot</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="BallotList.jsp"><i class="fa fa-angle-double-right"></i>Ballot List</a></li>
                            </ul>
                            </li>
                            <li class="treeview">
                            <a href="#">
                                <i class="fa fa-table"></i>
                                <span>Reports</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="rptContractorList.jsp"><i class="fa fa-angle-double-right"></i>Ballot Qualified List Report</a></li>
                                <li><a href="rptContractorMasterList.jsp"><i class="fa fa-angle-double-right"></i>Contractor Master List Report</a></li>
                                <li><a href="CertificateListReport.jsp"><i class="fa fa-angle-double-right"></i>Certificate List Report</a></li>
                                <li><a href="ApplicationStatusListReport.jsp"><i class="fa fa-angle-double-right"></i>Application Status List Report</a></li>
                                <li><a href="BallotListReport.jsp"><i class="fa fa-angle-double-right"></i>Ballot List Report</a></li>
                            </ul>
                            </li>
                        </c:when>
                        <c:when test="${sessionScope.user.roleID == 4}">
                          <li class="active">
                            <a href="home.jsp">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                            </a>
                           </li>
                           <li class="treeview">
                            <a href="#">
                                <i class="fa fa-table"></i>
                                <span>Reports</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="rptContractorList.jsp"><i class="fa fa-angle-double-right"></i>Ballot Qualified List Report</a></li>
                                <li><a href="rptContractorMasterList.jsp"><i class="fa fa-angle-double-right"></i>Contractor Master List Report</a></li>
                                <li><a href="CertificateListReport.jsp"><i class="fa fa-angle-double-right"></i>Certificate List Report</a></li>
                                <li><a href="ApplicationStatusListReport.jsp"><i class="fa fa-angle-double-right"></i>Application Status List Report</a></li>
                                <li><a href="BallotListReport.jsp"><i class="fa fa-angle-double-right"></i>Ballot List Report</a></li>
                            </ul>
                           </li>
                        </c:when>
                        <c:otherwise>
                          
                        </c:otherwise>
                      </c:choose>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

           
        </div><!-- ./wrapper -->


         <!-- Bootstrap -->
        <!--<script src="js/plugins/bootstrap.min.js" type="text/javascript"></script>-->
        <!-- AdminLTE App -->
        <!--<script src="js/plugins/AdminLTE/app.js" type="text/javascript"></script>-->
        <script src="js/AdminLTE/app.js" type="text/javascript"></script>
        
    </body>
</html>