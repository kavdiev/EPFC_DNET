<%-- 
    Document   : users
    Created on : Jun 2, 2013, 10:49:30 PM
    Author     : baxter
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
                <div align="center"> 
            <%@include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <h1 align="center" >Liste: ${model.label}</h1>     
        <table width="700" height="100%" border="1"  align="center" >
                <tr><td  bgcolor="#3090C7">Id</>
                <td bgcolor="#3090C7">Nom</td>
                <td bgcolor="#3090C7">Post Code</td>
                <td bgcolor="#3090C7">N° d'apparts :</td>
            </tr>
            <c:forEach items="${model.users}" var="user">
                <tr> 
                    <td bgcolor="#808080"><a href="user.htm?id=${user.idU}&tool=show">${user.idU}</a> </td>
                    <td>${user.nom}</td>
                    <td>${user.postCode}</td>
                    <td>${user.countApparts()} </td>
                </tr>
                <tr><td bgcolor="#DEDEDE"> </td></tr>
            </c:forEach>
        </table>
    </body>
</html>
