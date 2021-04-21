<%-- 
    Document   : confirm
    Created on : Apr 17, 2021, 3:03:34 PM
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
        <h1>Home Inventory</h1>
        <form action="forgot" method="post">
            <h3>enter your confirmation code: <input type="text" name="confirmation">
                
            </h3>
            <h3>new password: <input type="text" name="newPassword">
                
            </h3>
            <input type="hidden" name="stage" value="set">
            <input type="submit" value="submit">
        </form>
        <h3>${passwordMessage}</h3>
        <a href="login">login</a>
    </body>
</html>
