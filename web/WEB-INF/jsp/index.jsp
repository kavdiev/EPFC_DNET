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
                        <%@include file="/WEB-INF/jspf/header.jspf" %>
                    </div> </td>
            </tr>
            <tr>
                <td width="10%">  <div align="left"> <%@include file="/WEB-INF/jspf/adminMenu.jspf" %> 
                    </div></td>
                <td> <a> bla bla </a> </td>
            </tr>
        </table>

        <br>
        <form method="POST" action="liste.htm">
            <input name="liste" type="submit" value="Liste des personnes">
            <input name="initListe" type="submit" value="Ré-initialiser Liste">
        </form>

    </body>
</html>
