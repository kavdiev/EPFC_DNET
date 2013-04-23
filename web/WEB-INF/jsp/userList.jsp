<%-- 
    Document   : user
    Created on : Apr 22, 2013, 11:18:37 PM
    Author     : baxter
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List Page</title>
    </head>
    <body>
        <h1>Liste des utilisateurs</h1>
        <table>
            <c:forEach items="${users}" var="user"> 
                <tr>
                    <td><input name="id${user.idUser}" value="${user.idUser}"></td>
                    <td><input name="nom${user.idUser}" value="${user.nom}"></td>
                    
                    <!--  <td><input type="submit" name="rdv_suppr${rdv.id}" value="Supprimer"></td> -->
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
