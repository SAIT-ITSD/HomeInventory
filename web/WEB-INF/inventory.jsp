<%-- 
    Document   : inventory
    Created on : Feb 16, 2021, 2:50:34 PM
    Author     : 828200
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
        <link rel="stylesheet" href="../style.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Karantina:wght@300&family=Noto+Serif:ital@1&family=Roboto+Condensed:ital,wght@1,300&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1>HOME nVentory </h1>
        <h2> Inventory</h2><br>
        <h2>Inventory for ${username}</h2>
        <h3><b>menu</b></h3>
        <ul>
            <li><a href="inventory">inventory</a></li>
            <li><a href="login">login</a></li>
            <li><a href="admin">admin</a></li>
             <li><a href="edit">edit</a></li>
        </ul>
        <h3>${invMessage}</h3>
             <table name="items"><tr>
                    <th>Category</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th> </th>
                    
                  </tr>
              <c:forEach var="item" items="${items}">  
                 <tr>
                    <td>${item.category}</td>
                    <td>${item.itemName}</td>
                    <td>${item.price}</td>
                    <td>
                        <form action="inventory" method="post">
                            <input type="submit" value="delete">
                            <input type="hidden"  name="method" value="delete">
                            <input type="hidden" name="deleteItem" value="${item.itemID}"> 
                        </form>
                    </td>
                 <td>
                        <form action="inventory" method="post">
                            <input type="submit" value="edit">
                            <input type="hidden"  name="method" value="edit">
                            <input type="hidden" name="editItem" value="${item.itemID}">
                        </form>
                    </td>
                  </tr>
            </c:forEach>
        </table>
        <h3><b>Add item</b></h3>
        <form action="inventory" method="post">
             <input type="hidden"  name="method" value="insert">
            <h3>Category: 
                <select name="categorys">
                    <c:forEach var="category" items="${categorys}">
                        <option value=${category.categoryId}>${category.category}</option>
                    </c:forEach>
                </select>
            </h3>
            <h3>Name: <input type="text" name="addName" value="${updatedName}"></h3>
            <h3>Price: <input type="text" name="addPrice" value="${updatedPrice}"></h3>
            <input type="submit" value="Save">
        </form>
            <a href="login?log=out">logout</a>
             <a href="login?log=inactive">inactive</a>
             
             <a href="login?log=out">logout</a>
    </body>
</html>
