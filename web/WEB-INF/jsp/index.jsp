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
        
        <table border="1" align="center">
        <tr>
           <td>Users</td>
           <td>Apparts</td>
        </tr>
        <tr>
            <td>
                <form method="POST" action="liste.htm">
                    <input name="liste" type="submit" value="New User">
                    <input name="initListe" type="submit" value="Log in">
                </form>
            </td>
            <td>
                <form method="POST" action="liste.htm">
                    <input name="liste" type="submit" value="New Appart">
                    <input name="initListe" type="submit" value="Search apparts">
                </form>                
            </td>
        </tr>
        </table>
        
    </body>
</html>
