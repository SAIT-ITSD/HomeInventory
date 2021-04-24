<%-- 
    Document   : forgotPassword
    Created on : Apr 17, 2021, 2:58:41 PM
    Author     : 828200
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot password</title>
    </head>
    <body>
        <h1>Home Inventory</h1> 
        <h2>forgot password</h2>
        <h3>Please enter your gmail email and subsequent password to get a link which will allow you to change you're password.</h3>
        <form action="forgot" method="post">
           
            <h3>email: <input type="email" name="forgotEmail"></h3>
            <h3>gmail password: <input type="text" name="gmailPassword"></h3>
            <input type="submit" value="submit">
            <input type="hidden" name="stage" value="get">
        </form>
        <h3>${passwordMessage}</h3>
         <a href="login">login</a>
    </body>
</html>
