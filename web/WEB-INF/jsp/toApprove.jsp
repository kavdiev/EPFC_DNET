<%-- 
    Document   : toApprove
    Created on : Aug 18, 2013, 1:53:41 AM
    Author     : baxter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tools.Consts"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Apparts à confirmer</title>
    </head>
    <body>
        <h1 align="center" >Apparts à  confirmer </h1>     
        <table width="700" height="100%" border="1"  align="center" >
            <tr><td  bgcolor="#3090C7">Id (juste pour info)</>
                <td  bgcolor="#3090C7">appart Id</>
                <td bgcolor="#3090C7">User Id</td>
                <td bgcolor="#3090C7">Action</td>
            </tr>
            <c:forEach items="${model.rents}" var="location">
                <tr> 
                    <td>${location.id}</td>
                    <td><a href="appart.htm?id=${location.getAppart().idA}">${location.getAppart().idA}</a> </td>
                    <td>${location.getLocataire().idU}</td>
                    <td>Buttons ok/not</td>
                </tr>
                <tr><td colspan="5" bgcolor="#DEDEDE"> </td></tr>
            </c:forEach>
        </table>
    </body>
</html>
