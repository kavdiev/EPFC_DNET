<%-- 
    Document   : newRdv
    Created on : 30-mai-2010, 23:39:24
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
        <h1>Fiche personne</h1>
        <form:form method="POST" commandName="myPersonne" action="fiche.htm">
            <form:hidden path="id"/>
            <form:input path="nom" /><br>
            <form:input path="prenom" /><br>
            
            <h2>Rendez-vous :</h2>
            <table>
            <tr>
                <th>Date</th>
                <th>Heure</th>
                <th>Lieu</th>
                <th></th>
            </tr>
            <c:forEach items="${myPersonne.rendez_vous}" var="rdv">
                <tr>
                    <td><input name="date${rdv.id}" value="${rdv.dateToString()}"></td>
                    <td><input name="heure${rdv.id}" value="${rdv.heure}"></td>
                    <td><input name="lieu${rdv.id}" value="${rdv.lieu.description}"></td>
                    <td><input type="submit" name="rdv_suppr${rdv.id}" value="Supprimer"></td>
                </tr>
            </c:forEach>
                    
            <tr>
                
                <td><input name="Enregistrer" value="Enregistrer" type="submit" ></td>
                <td><input name="Supprimer" value="Supprimer" type="submit" ></td>
                <td><input name="RetourListe" value="Retour Liste" type="submit" ></td>
                <td><input name="AjoutRdv" value="Ajouter rendez-vous" type="submit" ></td>
                
            </tr>
        </table>
        </form:form>
    </body>
</html>
