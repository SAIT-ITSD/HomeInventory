<%-- 
    Document   : newAccount
    Created on : Apr 12, 2021, 6:38:34 AM
    Author     : 828200
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1><br>
        <form action="newAccount" method="post">
             <h3>First Name: <input type="text" name="RegisterFirstName" ></h3>
              <h3>Last Name: <input type="text" name="RegisterLastName" ></h3>
               <h3>Password: <input type="text" name="RegisterPassword" ></h3> 
               <h3>Email: <input type="email" name="RegisterEmail" ></h3>
            <input type="submit" value="submit">
        </form>
        <div>${dope}</div>
    </body>
</html>
