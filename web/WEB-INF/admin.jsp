<%-- 
    Document   : admin
    Created on : Feb 16, 2021, 2:50:21 PM
    Author     : 828200
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="../style.css">
       <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Karantina:wght@300&family=Noto+Serif:ital@1&family=Roboto+Condensed:ital,wght@1,300&display=swap" rel="stylesheet">
    </head>
    <body>
         <h1>Home Inventory</h1>
         <h2> Admin</h2><br>
        <h3><b>menu</b></h3>
        <ul>
            <li><a href="inventory">inventory</a></li>
            <li><a href="login">login</a></li>
            <li><a href="admin">admin</a></li>
             <li><a href="edit">edit</a></li>
        </ul>
        <h3>${adminMsg}</h3>
        <br>
        <h2>Manage Users</h2>
        <table name="users"><tr>
                    <th>Email</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Delete</th>
                    <th>Edit</th>
                    
                  </tr>
            <c:forEach var="user" items="${users}">
               
                 <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <form action="admin" method="post">
                            <input type="submit" value="delete">
                            <input type="hidden"  name="method" value="delete">
                            <input type="hidden" name="thisEmail" value="${user.email}">
                        </form>
                    </td>
                    <td>
                        <form action="admin" method="post">
                            <input type="submit" value="edit">
                            <input type="hidden"  name="method" value="edit">
                            <input type="hidden" name="thisEmail" value="${user.email}">
                        </form>
                    </td>
                  </tr>
            </c:forEach>
        </table>
        <h3><b>Add User</b></h3>
        <form action="admin?method=add" method="post">
        <h3>Email: <input type="text" name="newEmail" value="${updatedEmail}"></h3>
        <h3>First Name: <input type="text" name="newFirstName" value="${updatedFirstName}"></h3>
        <h3>Last Name: <input type="text" name="newLastName" value="${updatedLastName}"></h3>
        <h3>Password: <input type="text" name="newPassword" value="${updatedPassword}"></h3>
        
        <h3>
            <select name="newActive">
                <option value="on">on</option>
                <option value="off">off</option>
            </select>
        </h3> 
        <h3>
            <select name="newRole">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
            </select>
        </h3>
        <input type="submit" value="Save">
        </form>
        <table name="categorys">
            <tr>
                    <th>category id</th>
                    <th>category</th>
                    <th></th>
                    
                    
                  </tr>
         <c:forEach var="category" items="${categorys}">
               
                 <tr>
                    <td>${category.categoryId}</td>
                    <td>${category.category}</td>
                    
                    <td>
                        <form action="admin" method="post">
                            <input type="submit" value="editCategory">
                            <input type="hidden"  name="method" value="editCategory">
                            <input type="hidden" name="thisCategoryId" value="${category.categoryId}">
                             <input type="hidden" name="thisCategory" value="${category.category}">
                        </form>
                    </td>
                  </tr>
            </c:forEach>
        </table>
        <form action="admin" method="post">
               <input type="hidden"  name="method" value="addCategory">
                 <h3>category Id: <input type="text" name="newCategoryId" value="${newCategoryId}"></h3> 
        <h3>category Name: <input type="text" name="newCategoryName" value="${newCategoryName}"></h3> 
          <input type="submit" value="submit">
        </form>
          <form action='admin' method='post'>
              <input type="hidden"  name="method" value="txtList">
              <h3>update customer.csv file: <input type="submit" value="submit"></h3>
          </form>
         <a href="login?log=out">logout</a>
         
    </body>
</html>
