<%-- 
    Document   : index
    Created on : Aug 19, 2015, 3:01:01 PM
    Author     : raffie.ismail
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bumiputera Contractor Management System v1.0</title>
        <link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/styles.css">   
        <script language="javascript">
    function loginpwd(form)
    {
       if (form.name.value === "") 
        {
            alert( "Please enter User ID." );
            form.name.focus();
            return false ;
        }	
       else if (form.pass.value === "") 
        {
            alert( "please enter Password." );
            form.pass.focus();
            return false ;
        }	
    }
    function login()
    {
    var javascriptVar="${login}";
    console.log(javascriptVar);
    if (javascriptVar === 'false') {
        alert("Please Try Again");   
        return false;
        }

    }
    </script>
    </head>
    <body onload="login()">
        <form name="form1" method="post" action="LoginServlet?action=login">
        <div id="container">
            <table width="420"  border="0" align="" cellpadding="0" cellspacing="0">
                <tbody>
                <img src="images/mrt corp logo.png" width="100" height="50" alt="AAC-Campaign-box4-300x182"/>Bumiputera Contractor Management System
                    <tr>
                        <label align="left" for="name">Username:</label>
                        <input type="name" name="name" id="name" value="" />
                        <label align="left" for="username">Password:</label>
                        <input type="password" name="pass" value="" />
                    </tr>
                    <tr>
                    <div id="">
                       <input type="submit" value="Login" onClick="return loginpwd(form)"/>
                    </div>
                    </tr>
                </tbody>
            </table><p><br></p>
        </div>
        </form>
</body>
</html>