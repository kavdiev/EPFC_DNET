<%-- 
    Document   : newRdv
    Created on : 30-mai-2010, 23:39:24
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rendez-vous</title>
    </head>
    <body>
        <h1>Ajout rendez-vous</h1>
        <form action="fiche.htm" method="POST">
            <table>
                <tr>
                    <td>Date <input name="dateRdv"></td>
                </tr>
                <tr>
                    <td>Heure <input name="heureRdv"></td>
                </tr>
                <tr>
                    <td>Lieu <input name="lieuRdv"></td>
                </tr>
                <tr>
                    <td>
                        <input name="Enregistrer" value="Enregistrer" type="submit" >
                        <input name="Annuler" value="Annuler" type="submit" >
                        <input name="addedRdv" type="hidden">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
