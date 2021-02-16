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
        <h2>Admin Summary</h2><br>
        <h3>Total value for all users: ${total}. Most expensive item is ${productName} owned by ${userOfExpense}</h3>
        <a href="login">Logout</a>
    </body>
</html>
