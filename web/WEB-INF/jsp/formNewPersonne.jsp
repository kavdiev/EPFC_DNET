<%-- 
    Document   : formPersonne
    Created on : 15-mai-2010, 13:59:39
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Fiche nouvelle personne</h1>
        <form:form method="POST" action="nouvPers.htm" commandName="myPersonne">
        <table>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
            </tr>
            <tr>
                <td><form:input path="nom" /> </td>
                <td><form:input path="prenom" /> </td>
            </tr>
            <tr>
                <td><input name="Creer" value="Créer" type="submit" ></td>
                <td><input name="Annuler" value="Annuler" type="submit" ></td>
            </tr>
        </table> 
        </form:form>
    </body>
</html>
