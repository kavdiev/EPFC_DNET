<%-- 
    Document   : appart
    Created on : Apr 22, 2013, 9:27:39 PM
    Author     : baxter
--%>
<%@page import="tools.Consts"%>
<%@page import="model.User"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% User u = (User) request.getSession().getAttribute(Consts.CURRENT_USER);%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% if (request.getAttribute(Consts.LABEL) == null) {%>
        <title><%=u.getNom()%>'s Apparsts</title>
        <% } else {%>
        <title>Last visited Apparsts</title>
        <% }%>
    </head>
    <body>
         <div align="center"> 
            <%@include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <h1 align="center">
            <% if (request.getAttribute(Consts.LABEL) == null) {%>
            <%=u.getNom()%>'s Apparsts
            <% } else {
            %>
            Last visited Apparsts
            <% }%>    
        </h1>
        <br>
        <table width="700" height="100%" border="1"  align="center" >
            <tr><td  bgcolor="#3090C7">Id</>
                <td bgcolor="#3090C7">pieces</td>
                <td bgcolor="#3090C7">Superficie</td>
                <td bgcolor="#3090C7">Loyer</td>
                <td bgcolor="#3090C7">Post Code</td>
            </tr>
            <c:forEach items="${model.apparts}" var="appart">
                <tr> 
                    <td bgcolor="#808080"><a href="appart.htm?id=${appart.idA}">${appart.idA}</a> </td>
                    <td>${appart.pieces}</td>
                    <td>${appart.superficie}</td>
                    <td>${appart.loyer} </td>
                    <td>${appart.postCode} </td>
                </tr>
                <tr><td bgcolor="#DEDEDE"> </td></tr>
            </c:forEach>
        </table>
    </body>
</html>