<%-- 
    Document   : bienimmolist
    Created on : May 5, 2013, 1:36:45 PM
    Author     : baxter
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All bien immo</title>
    </head>

    <body>

        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <br>
        <h1>Liste des Biens</h1>
        <table>
            <c:forEach items="${apparts}" var="appart"> 
                <tr>
                    <td> ID <input name="id${appart.idBienImmo}" value="${appart.idBienImmo}"></td>
                    <td> nb chambres <input name="chambres${appart.idBienImmo}" value="${appart.nbChambres}"></td> 
                    <td>Owner <input name="owner${appart.idBienImmo}" value="${appart.user.nom}"></td>

<!--  <td><input type="submit" name="rdv_suppr${rdv.id}" value="Supprimer"></td> -->
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
