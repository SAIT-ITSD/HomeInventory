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
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h2>Login</h2>
        <form action="login" method="post">
            <h3>User Name:<input type="text" name="email" value="${session.username}"></h3>
            <h3>Password: <input type="text" name="password" value="${session.password}"></h3>
            <input type="submit" name="submit" value="Log in">
        </form>
            <h3>${message}</h3>
    </body>
</html>
