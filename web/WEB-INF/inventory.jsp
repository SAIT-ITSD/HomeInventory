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
        <h2>add item</h2>
        <br><br>
        <form action="inventory" method="post">
        <h3>Category <select name="category">
            <option value="living room">living room</option>
            <option value="bedroom">bedroom</option>
            <option value="kitchen">kitchen</option>
            <option value="garage">garage</option>
          </select>
          </h3>
            <h3>Item Name: <input type="text" name="Item_name"></h3>
        <h3>Price: <input type="text" name="price"></h3><br>
        <input type="submit" value="Add">
        </form>
        <h3>${message}</h3><br>
        <h3>Total value in inventory: $ ${inventory}.</h3><br>
        <a href="login">Logout</a>
    </body>
</html>
