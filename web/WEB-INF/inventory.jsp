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
        <h1>Home Inventory</h1><br>
        <h3><b>menu</b></h3>
        <ul>
            <li><a href="inventory">inventory</a></li>
            <li><a href="login">login</a></li>
            <li><a href="admin">admin</a></li>
        </ul>
        <h2>Inventory for ${username}</h2>
        <table name="users">
            
        </table>
        <h3><b>Add item</b></h3>
        <form action="inventory" method="post">
        <h3>Category: <select name="categorys"></h3>
        <h3>Name: <input type="text" name="name"></h3>
        <h3>Price: <input type="text" name="price"></h3>
        <input type="submit" value="Save">
        </form>
    </body>
</html>
