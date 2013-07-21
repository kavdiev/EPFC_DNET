<%-- 
    Document   : login
    Created on : May 26, 2013, 12:02:00 AM
    Author     : baxter
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In</title>
    </head>
    <body>
        <h1>Log in</h1>
        <form name="login" method="POST">
            <fieldset>
                <label>Identifiant :</label>
                <input type="text" name="username"/>
                <hr />
                <label>Mot de passe :</label>
                <input type="password" name="password"/>
                <hr />
                <input name="login" value="Valider" type="submit"/>
            </fieldset>
        </form>
        <br>
        <table border="1">
            <tr><td>
                    ${model.msg}
                </td> </tr>
            <tr> <td> <a href="user.htm?tool=newUser"> Inscription</a> </td></tr>
        </table>

    </body>
</html>
