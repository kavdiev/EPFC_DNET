<%-- 
    Document   : appart
    Created on : Jun 2, 2013, 8:01:00 PM
    Author     : baxter
--%>
<%@page import="model.Appart"%>
<%@page import="tools.Consts"%>
<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <% User u = (User) request.getSession().getAttribute(Consts.CURRENT_USER);%>
        <% Appart a = (Appart) request.getAttribute("appart");%>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservation d'appart: ${model.appart.idA}</title>
    </head>
    <body>
        <table width="700" height="100%" border="1"  align="center"> 
            <tr>
                <td colspan="5" bgcolor="#00FFFF" > <h1 align="center" >Reserver Appartement ${model.appart.idA}</h1></td>
            </tr>
            <tr>
                <td align="left" >ID :</td> <td align="left" >${model.appart.idA}</td> 
            </tr>
            <tr>
                <td align="left" >Propriétaire :</td> <td align="left" >${model.appart.proprio.nom}</td> 
            </tr>
            <tr><td bgcolor="#C0C0C0" width="30px" height="26px"><img src="img/i_mesg.gif"></td>
                <td align="left" bgcolor="#C0C0C0" colspan="4"  height="26px"> Message: ${model.msg} </td></tr>
        </table>
        <table  width="700" height="100%" border="1"  align="center">
            <tr>
                <th>N° pieces</th>
                <th>superficie (m²)</th>
                <th>loyer</th>
                <th>postCode</th>
            </tr>
            <tr>
                <td>${appart.pieces}</td>
                <td>${appart.superficie}</td>
                <td>${appart.loyer} </td>
                <td>${appart.postCode} </td>
            </tr>
        </table> 
        <form:form method="POST" action="rentRequest.htm" commandName="rent">
            <input name="idR" value="${model.appart.idA}"  type="hidden">
            <table  width="700" height="100%" border="1"  align="center">
                <tr>
                    <td>Semaine de debut <form:select path="weekIn">
                            <form:options items="${model.rent.weeks}"/>
                        </form:select> 
                    </td>
                    <td>Semaine de fin <form:select path="weekOut">
                            <form:options items="${model.rent.weeks}"/>
                        </form:select>  </td>
                </tr>
                <tr> 
                    <td colspan="5" > <input type="submit" name="requestRent" value="Reserver"></td>
                </tr>
            </table> 
        </form:form>
        <table  width="700" height="100%" border="1"  align="center">
            <tr>
                <th colspan="2">Reservations foutures </th>
            </tr>
            <c:forEach items="${reservations}" var="reservation">
                <tr> 
                    <td>debut: ${reservation.weekIn}</td>
                    <td>fin: ${reservation.weekOut}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
