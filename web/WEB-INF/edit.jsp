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
        <title>Edit Account</title>
        <link rel="stylesheet" href="../style.css">
       <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Karantina:wght@300&family=Noto+Serif:ital@1&family=Roboto+Condensed:ital,wght@1,300&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1>HOME nVentory </h1>
        <h2>Edit Account</h2>
         <h2>${username}</h2>
           <h3><b>menu</b></h3>
           <h3>type into any of the text fields to edit an attribute click edit to save you're account changes.</h3>
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
               <a href="login?log=inactive">inactive</a> <a href="login?log=out">logout</a>
    </body>
</html>
