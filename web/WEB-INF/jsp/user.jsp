<%-- 
    Document   : user
    Created on : Jun 8, 2013, 10:22:04 PM
    Author     : baxter
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
    </head>
    <body>
        <h1>User!</h1>
        <form:form method="POST" action="user.htm" commandName="user">
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Password</th>
                </tr>
                <tr>
                    <td><form:input path="nom" /> </td>
                    <td><form:input path="postCode" /> </td>
                    <td> N° d'apparts : ${model.user.countApparts()} </td>
                <tr> 
                    <td><input name="update" value="update" type="submit" ></td>
                    <td><input name="setadmin" value="set admin" type="submit" ></td>
                </tr>
                <input name="userid" value="${model.userid}" type="hidden">
                </tr>
            </table> 
        </form:form>
    </body>
</html>
