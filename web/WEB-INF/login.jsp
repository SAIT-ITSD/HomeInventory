<%-- 
    Document   : login
    Created on : Feb 16, 2021, 2:50:46 PM
    Author     : 828200
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
         <link rel="stylesheet" href="../style.css">
       <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Karantina:wght@300&family=Noto+Serif:ital@1&family=Roboto+Condensed:ital,wght@1,300&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h2>Login</h2>
        <form action="login" method="post">
            <h3>User Name:<input type="text" name="email" value="${session.username}"></h3>
            <h3>Password: <input type="text" name="password" value="${session.password}"></h3>
            <input type="submit" name="submit" value="Log in" >
        </form>
            <a href="newAccount">sighn up</a><br>
            <a href="forgot">forgot password</a>
            <h3>${message}</h3>
    </body>
</html>
