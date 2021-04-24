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
        <title>New Account</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h2>Create a new account</h2><br>
        <h3>fill in the fields below to create you're account.</h3>
        <h3>note if you're account already exists login or if you forgot you're password click the appropriate link below.</h3>
        <form action="newAccount" method="post">
             <h3>First Name: <input type="text" name="RegisterFirstName" ></h3>
              <h3>Last Name: <input type="text" name="RegisterLastName" ></h3>
               <h3>Password: <input type="text" name="RegisterPassword" ></h3> 
               <h3>Email: <input type="email" name="RegisterEmail" ></h3>
              
            <input type="submit" value="submit">
        </form>
        <a href='login'>login</a><br>
        <a href="forgot">forgot password</a>
        <div>${dope}</div>
    </body>
</html>
