<%-- 
    Document   : index
    Created on : Apr 17, 2013, 11:08:10 PM
    Author     : baxter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div align="center"> 
            <h1> hello sur le site de location d'apparts </h1>
        </div>

        <table border="1" align="center" wight="40%">
            <tr>
                <td>Users</td>
            </tr>
            <tr>
                <td>
                    <form method="POST" action="user.htm">
                        <input name="newUser" type="submit" value="New User">
                        <input name="listUser" type="submit" value="List Users">
                    </form>
                </td>
            </tr>

            <tr>
                <td>Bien Immo</td>
            </tr>
            <tr>
                <td>
                    <form method="POST" action="bienimmo.htm">
                        <input name="newBienImmo" type="submit" value="New bienImmo">
                        <input name="listBienImmo" type="submit" value="List bienImmo">
                    </form>
                </td>
            </tr>

        </table>

    </body>
</html>
