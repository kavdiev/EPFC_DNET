<%-- 
    Document   : newbienimmo
    Created on : May 5, 2013, 8:26:05 PM
    Author     : baxter
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appart Creation</title>
    </head>
    <body>
        <h1>New Appart</h1>

        <form:form method="POST" commandName="bienimmo">
            <table>
                <tr>
                    <th>N° chambres</th>
                    <th>garage</th>
                    <th>piscine</th>
                    <th>postcode</th>
                </tr>
                <tr>
                    <td><form:input path="nbChambres" /> </td>
                    <td><form:input path="garage" /> </td>
                    <td><form:input path="piscine" /> </td>
                    <td><form:input path="postcode" /> </td>
                    <td><input name="Creer" value="Créer" type="submit" ></td>
                <input name="appartAdded" type="hidden">
                </tr>
            </table> 
        </form:form>
    </body>
</html>