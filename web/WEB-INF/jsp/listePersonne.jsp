<!DOCTYPE html>
<html>
    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Liste des personnes</h1>
        <table>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
            </tr>
            <c:forEach items="${ personnes }" var="pers">
                <tr>
                    <c:url value="fiche.htm" var="mavar">
                        <c:param name="id" value ="${ pers.id }"/>
                    </c:url>
                    <td><a href='<c:out value="${mavar}"/>'>${ pers.nom }</a></td>
                    <td><a href='<c:out value="${mavar}"/>'>${ pers.prenom }</a></td>
                </tr>
            </c:forEach>
                
            <tr>
                <c:url value="fiche.htm" var="mavar" />
                <td><a href='<c:out value="${mavar}"/>'>Nouveau</a></td>
                <td></td>
            </tr>

        </table>
        <a href='index.htm'>Retour au menu principal</a>
    </body>
</html>
