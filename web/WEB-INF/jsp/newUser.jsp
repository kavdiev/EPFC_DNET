<%-- 
    Document   : newUser
    Created on : Apr 22, 2013, 9:27:39 PM
    Author     : baxter
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New User</title>
    </head>
    <body>
        <h1>New User Creation page</h1>
        <form:form method="POST" commandName="user">
        <table>
            <tr>
                <th>Nom</th>
                <th>Password</th>
                <th></th>
            </tr>
            <tr>
                <td><form:input path="nom" /> </td>
                <td><form:input path="password" /> </td>
                <td><input name="Creer" value="Créer" type="submit" ></td>
            </tr>
        </table> 
        </form:form>
    </body>
</html>
