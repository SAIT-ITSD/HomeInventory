<%-- 
    Document   : admin
    Created on : Feb 16, 2021, 2:50:21 PM
    Author     : 828200
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
         <h1>Home Inventory</h1><br>
        <h3><b>menu</b></h3>
        <ul>
            <li><a href="inventory">inventory</a></li>
            <li><a href="login">login</a></li>
            <li><a href="admin">admin</a></li>
        </ul>
        <h2>Manage Users</h2>
        <table name="users">
            
        </table>
        <h3><b>Add User</b></h3>
        <form action="admin" method="post">
        
        <h3>Email: <input type="text" name="email"></h3>
        <h3>First Name: <input type="text" name="firstName"></h3>
        <h3>Last Name: <input type="text" name="lastName"></h3>
        <h3>Password: <input type="text" name="password"></h3>
        <input type="submit" value="Save">
        </form>
    </body>
</html>
