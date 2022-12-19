<%-- 
    Document   : ProjectMRTS
    Created on : Dec 15, 2022, 4:38:54 PM
    Author     : azim.kamarudin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <c:choose>
        <c:when test="${sessionScope.user.roleID == 3 || sessionScope.user.roleID == 6 }">

            <div class="row"> 
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-yellow">
                        <div class="inner">
                            <p>
                                Passed
                            </p>
                            <h3>
                                <%=session.getAttribute("g1PassedCount")%>
                            </h3>
                        </div>
                        <div class="inner">
                            <p>
                                Awarded
                            </p>
                            <h3>
                                <%=session.getAttribute("g1AwardedCount")%>
                            </h3>
                        </div>
                        <div class="icon">
                            G1
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-purple">
                        <div class="inner">
                            <p>
                                Passed
                            </p>
                            <h3>
                                <%=session.getAttribute("g2PassedCount")%>
                            </h3>
                        </div>
                        <div class="inner">
                            <p>
                                Awarded
                            </p>
                            <h3>
                                <%=session.getAttribute("g2AwardedCount")%>
                            </h3>
                        </div>
                        <div class="icon">
                            G2
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-blue">
                        <div class="inner">
                            <p>
                                Passed
                            </p>
                            <h3>
                                <%=session.getAttribute("g3PassedCount")%>
                            </h3>
                        </div>
                        <div class="inner">
                            <p>
                                Awarded
                            </p>
                            <h3>
                                <%=session.getAttribute("g3AwardedCount")%>
                            </h3>
                        </div>
                        <div class="icon">
                            G3
                        </div>
                    </div>
                </div>         
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-green">
                        <div class="inner">
                            <p>
                                Passed
                            </p>
                            <h3>
                                <%=session.getAttribute("g4PassedCount")%>
                            </h3>
                        </div>
                        <div class="inner">
                            <p>
                                Awarded
                            </p>
                            <h3>
                                <%=session.getAttribute("g4AwardedCount")%>
                            </h3>
                        </div>
                        <div class="icon">
                            G4
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-red">
                        <div class="inner">
                            <p>
                                Passed
                            </p>
                            <h3>
                                <%=session.getAttribute("g5PassedCount")%>
                            </h3>
                        </div>
                        <div class="inner">
                            <p>
                                Awarded
                            </p>
                            <h3>
                                <%=session.getAttribute("g5AwardedCount")%>
                            </h3>
                        </div>
                        <div class="icon">
                            G5
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-gray" style="color: white">
                        <div class="inner">
                            <p>
                                Passed
                            </p>
                            <h3>
                                <%=session.getAttribute("g6PassedCount")%>
                            </h3>
                        </div>
                        <div class="inner">
                            <p>
                                Awarded
                            </p>
                            <h3>
                                <%=session.getAttribute("g6AwardedCount")%>
                            </h3>
                        </div>
                        <div class="icon">
                            G6
                        </div>
                    </div>
                </div>
            </div> 
        </c:when>
    </c:choose> 
</body>
</html>