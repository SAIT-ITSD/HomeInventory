<%-- 
    Document   : edit
    Created on : Apr 12, 2021, 5:25:57 AM
    Author     : 828200
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edit</title>
    </head>
    <body>
        <h1>Home Inventory</h1><br>
           <h3><b>menu</b></h3>
        <ul>
            <li><a href="inventory">inventory</a></li>
            <li><a href="login">login</a></li>
            <li><a href="admin">admin</a></li>
            <li><a href="edit">edit</a></li>
        </ul>
        <form action="edit" method="post">
             <h3>First Name: <input type="text" name="EditFirstName" value="${EditFirstName}"></h3>
              <h3>Last Name: <input type="text" name="EditLastName" value="${EditLastName}"></h3>
               <h3>Password: <input type="text" name="EditPassword" value="${EditPassword}"></h3> 
               <h3>Email: <input type="email" name="EditEmail" value="${EditEmail}"></h3>
               
            <input type="submit" value="edit">
        </form>
               <a href='login'>login</a>
    </body>
</html>
