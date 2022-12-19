<%-- 
    Document   : timedoutRedirect
    Created on : Feb 24, 2016, 9:03:28 AM
    Author     : munawwarah.mahmod
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<%
int timeout = session.getMaxInactiveInterval();
response.setHeader("Refresh", timeout + "; URL = LoginPage.jsp");
%>
