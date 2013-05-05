<%-- 
    Document   : index
    Created on : Apr 17, 2013, 11:08:10 PM
    Author     : baxter
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get your Immo</title>
    </head>


    <body>
        <br>
        <table width="750" border="1" height="90%" align="center">
            <tr>
                <td colspan="2"  height="5%"> <div align="center"> 
                        <%@include file="/WEB-INF/jsp/header.jspf" %>
                    </div> </td>
            </tr>
            <tr>
                <td>Menu</td>
                <td>        <table border="1" align="center" wight="40%">
                        <tr>
                            <td>Users</td>
                        </tr>
                        <tr>
                            <td>
                                <c:url value="user.htm" var="mavar" />
                                <a href='<c:out value="${mavar}"/>'>New User</a>

                                <form method="POST" action="user.htm">
                                    <input name="listUser" type="submit" value="List Users">
                                </form>
                            </td>
                        </tr>

                        <tr>
                            <td>Bien Immo</td>
                        </tr>
                        <tr>
                            <td>
                                <form method="GET" action="bienimmo.htm">
                                    <input name="newBienImmo" type="submit" value="New bienImmo">
                                </form>
                                <form method="POST" action="bienimmo.htm">
                                    <input name="listBienImmo" type="submit" value="List bienImmo">
                                </form>
                            </td>
                        </tr>

                    </table></td>
            </tr>
        </table>

        <br>
        <form method="POST" action="liste.htm">
            <input name="liste" type="submit" value="Liste des personnes">
            <input name="initListe" type="submit" value="Ré-initialiser Liste">
        </form>

    </body>
</html>
