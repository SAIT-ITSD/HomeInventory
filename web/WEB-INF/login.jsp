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
        <form action="" method="post">
            <h3>User Name:<input type="text" name="username" value="${username}"></h3><br>
            <h3>Password: <input type="text" name="password" value="${password}"></h3><br>
            <input type="submit" name="submit" value="Submit">
        </form>
            <br><h3>${message}</h3>
    </body>
</html>
