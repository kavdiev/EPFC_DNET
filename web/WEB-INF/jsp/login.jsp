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
        <table align="center" width="100%" height="100%" border="1">
            <tr>
                <td colspan="2" align="center" bgcolor="#00FFFF"> <h2>Log in </h2></td> 
            </tr>
            <tr><td colspan="2">
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
                </td> 
            </tr>
            <tr>
                <td bgcolor="#C0C0C0" width="30px" height="26px"><img src="img/i_mesg.gif"></td>
                <td align="left" bgcolor="#C0C0C0" colspan="4"  height="26px"> Message: ${model.msg} </td>
            </tr>
            <tr> 
                <td colspan="2" > <a href="user.htm?tool=newUser"> Inscription</a> </td>
            </tr>
        </table>
    </body>
</html>
