<%-- 
    Document   : mainPage
    Created on : May 26, 2013, 12:14:23 AM
    Author     : baxter
--%>
<%@page import="tools.Consts"%>
<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
        <% User u = (User) request.getSession().getAttribute(Consts.CURRENT_USER);%>
        <style type="text/css">
            body p {
                text-align: center;
            }
        </style>
    </head>
    <body>
        
        <div align="center"> 
            <%@include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <%-- Welcome to ${sessionScope.cuser.getNom()}--%>
        <table width="100%" height="100%" border="1"  align="center" >
            <tr> <td colspan="3" bgcolor="#00FFFF" align="center"> <b>Main page </b></td></tr>
            <tr>
                <td bgcolor="#00FFFF" >User</td>
                <td bgcolor="#00FFFF" >Search</td>
                <td bgcolor="#00FFFF" >Menu</td>
            </tr>
            <tr>
                <td valign="top">
            <h> Nom: <%=u.getNom()%></h> <br>
                <% if (!u.isAnonymus()) {%>
            <h> Post Code: <%=u.getPostCode()%></h> <br>
            <h> Mes apparts: <%=u.countMyApparts()%></h> <br>
            <h> Reservations : <%=u.countRentRequests()%></h> <br>
            <a href="index.htm?logout=true"> Log out </a> <br>
            <% } else {%>
            <a href="user.htm?tool=login"> Log in </a> <br>
            <% }%>
            <a href="<%=Consts.LIST_APPART_URL%>?tool=lasts">Derniers appars consultes</a> <br>
            </td>
            <td  width="70%" align="center">
                <table width="100%" height="100%"  border="1">
                    <form:form method="POST" action="search.htm" commandName="searchForm">
                        <tr>
                            <td>Nombre de pieces <form:select path="pieces">
                            <option value="0">Tous</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                        </form:select> 
                </td> 
            </tr>

            <tr><td>Surface <form:input path="superficie"/> </td></tr>
            <tr><td>Loyer Min<form:input path="loyerMin" /> </td></tr>
            <tr><td>Loyer MAx<form:input path="loyerMax" /> </td></tr>
            <tr><td>Post Code<form:input path="postCode" /> </td></tr>
            <tr>
                <td>Semaine debut 
                    <form:select path="weekIn">
                        <form:options items="${searchForm.weeks}"/>
                    </form:select>

                    - Semaine fin 
                    <form:select path="weekOut">
                        <form:options items="${searchForm.weeks}"/>
                    </form:select>
                </td>
            </tr>        
            <td>Strict: <form:checkbox path="strict" />  <input name="doSearch" value="Search" type="submit" ></td>
        </tr>

    </form:form>
</table>
<table border="1" width="100%" height="100%" >
    <tr><td bgcolor="#C0C0C0" width="30px" height="26px"><img src="img/i_mesg.gif"></td>
        <td align="left" bgcolor="#C0C0C0" colspan="4"  height="26px"> Message: ${model.msg} </td></tr>
    <tr><td align="center" colspan="5" bgcolor="#00FFFF" > Resultat de recherche: </td></tr>
    <tr><td bgcolor="#3090C7">Id</>
        <td bgcolor="#3090C7">pieces</td>
        <td bgcolor="#3090C7">Superficie</td>
        <td bgcolor="#3090C7">Loyer</td>
        <td bgcolor="#3090C7">Post Code</td>
    </tr>
    <tr ><td bgcolor="#E8E8E8" colspan="5"> </td></tr>

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
</td>
<%-- il faut un if users is anonymus Menu pour se loguer ou alors menu classique --%>
<td valign="top">
    <% if (!u.isAnonymus()) {%>
    <a href="<%=Consts.NEW_APPART_URL%>">New Appart</a> <br>
    <a href="<%=Consts.LIST_APPART_URL%>?tool=myapparts">Mes Apparts</a> <br>
    <a href="<%=Consts.TO_APPROVE_URL%>">Apparts a confirmer </a> <br>
    <% }%>
    <% if (u.isAdmin()) {%>
    <a href="<%=Consts.LIST_USERS_URL%>?tool=showall"> Show Users </a> <br>
    <a href="<%=Consts.LIST_USERS_URL%>?tool=showadmins">Show Admins </a> <br>
    <a href="<%=Consts.LIST_USERS_URL%>?tool=showproprios">Show Proprios </a> <br>
    <a href="<%=Consts.STATS_URL%>">Stats </a> <br>
    <% }%>
</td>
</tr>
</table>
</body>
</html>
