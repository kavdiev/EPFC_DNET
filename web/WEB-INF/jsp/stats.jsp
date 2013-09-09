<%-- 
    Document   : stats
    Created on : Aug 16, 2013, 12:09:51 AM
    Author     : baxter
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stats</title>
    </head>
    <body>
                <div align="center"> 
            <%@include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <table width="700" height="100%" border="1"  align="center"> 
            <tr>
                <td colspan="2" bgcolor="#00FFFF" > <h1 align="center" >Stats</h1></td>
            </tr>
            <tr>
                <td > <h4 align="left" >Nombre d'utilisateurs inscrit:</h4></td>
                <td > <h4 align="center" >${model.countUsers}</h4></td>
            </tr>
            <tr>
                <td > <h4 align="left" >Nombre d'admins:</h4></td>
                <td > <h4 align="center" >${model.countAdmins}</h4></td>
            </tr>
            <tr>
                <td > <h4 align="left" >Dernier inscrit (Nom):</h4></td>
                <td > <h4 align="center" > <a href="user.htm?id=${model.lastUser.idU}&tool=show">${model.lastUser.nom}</a></h4></td>
            </tr>
            <tr>
                <td > <h4 align="left" >Nombre d'appartements inscrit:</h4></td>
                <td > <h4 align="center" >${model.countApparts}</h4></td>
            </tr>
            <tr>
                <td > <h4 align="left" >Nombre d'appartements loués:</h4></td>
                <td > <h4 align="center" >${model.countRents}</h4></td>
            </tr>
        </table>  
    </body>
</html>
