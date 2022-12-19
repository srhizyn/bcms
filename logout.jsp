<%-- 
    Document   : logout
    Created on : Aug 25, 2015, 4:05:15 PM
    Author     : raffie.ismail
--%>
<%
session.setAttribute("displayName", null);
session.setAttribute("name", null);
session.setAttribute("pass", null);
session.invalidate();
response.sendRedirect("LoginPage.jsp");
%>