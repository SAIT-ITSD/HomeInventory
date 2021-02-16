<%-- 
    Document   : inventory
    Created on : Feb 16, 2021, 2:50:34 PM
    Author     : 828200
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
    </head>
    <body>
        <h1>Home Inventory for ${username}</h1><br>
        <h2>Login</h2>
        <br><br>
        <form action="inventory" method="post">
        <h3>Category</h3><br>
        <h3>Item Name:</h3><br>
        <h3>Price:</h3><br>
        <input type="submit" value="Add"><br>
        </form>
        <h3>${message}</h3><br>
        <h3>${inventory}</h3><br>
        <a href="login">Logout</a>
    </body>
</html>
