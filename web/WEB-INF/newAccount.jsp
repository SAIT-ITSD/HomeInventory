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
        <link rel="stylesheet" href="../style.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Karantina:wght@300&family=Noto+Serif:ital@1&family=Roboto+Condensed:ital,wght@1,300&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1>HOME nVentory </h1>
        <h2>Create a new account</h2><br>
         <h2>${username}</h2>
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
