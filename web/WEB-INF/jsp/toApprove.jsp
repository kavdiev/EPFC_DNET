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
        <div align="center"> 
            <%@include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <h1 align="center" >Apparts à  confirmer </h1>     
        <table width="700" height="100%" border="1"  align="center" >
            <tr>
                <td  bgcolor="#3090C7">appart Id</>
                <td colspan="2" bgcolor="#3090C7">Periode de reservation</>
                <td bgcolor="#3090C7">User</td>
                <td bgcolor="#3090C7">Action</td>
            </tr>
            <c:forEach items="${model.rents}" var="location">
                <tr> 
                <!--    <td>${location.id}</td> -->
                    <td><a href="appart.htm?id=${location.getAppart().idA}">${location.getAppart().idA}</a> </td>
                    <td>Semaine de debut: ${location.weekIn} </td>
                    <td> Semaine de fin: ${location.weekOut} </td>
                    <td>${location.getLocataire().nom}</td>
                    <td>Buttons ok/not</td>
                </tr>
                <tr><td colspan="5" bgcolor="#DEDEDE"> </td></tr>
            </c:forEach>
        </table>
    </body>
</html>
