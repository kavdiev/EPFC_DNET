<%-- 
    Document   : testargs
    Created on : 30-mai-2010, 15:11:53
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Arguments du formulaire</h1>
        <c:forEach items="${lstargs}" var="arg">
            <c:out value="${arg}"/><br>
        </c:forEach>
    </body>
</html>
