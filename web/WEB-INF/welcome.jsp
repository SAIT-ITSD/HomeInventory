<%-- 
    Document   : welcome
    Created on : Apr 18, 2021, 6:12:04 AM
    Author     : 828200
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activate account</title>
    </head>
    <body>
        <h1>Home inventory</h1>
        <h2>Activate account</h2>
        <form action='welcome' method='post'>
        <h3>to activate you're account enter the activation passcode: <input type='text' name='welcomePasscode'></h3>
        <input type='submit' value='submit'>
        </form>
        <h3>${welcomeLog}</h3>
    </body>
</html>
