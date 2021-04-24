<%-- 
    Document   : confirm
    Created on : Apr 17, 2021, 3:03:34 PM
    Author     : 828200
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot password</title>
        <link rel="stylesheet" href="../style.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Karantina:wght@300&family=Noto+Serif:ital@1&family=Roboto+Condensed:ital,wght@1,300&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1>HOME nVentory </h1>
        <h2>Forgot password</h2>
         <h2>${username}</h2>
        <h3> enter you're confirmation code and a new password for you're account.</h3>
        <form action="forgot" method="post">
            <h3>enter your confirmation code: <input type="text" name="confirmation">
                
            </h3>
            <h3>new password: <input type="text" name="newPassword">
                
            </h3>
            <input type="hidden" name="stage" value="set">
            <input type="submit" value="submit">
        </form>
        <h3>${passwordMessage}</h3>
        <a href="login">login</a>
    </body>
</html>
