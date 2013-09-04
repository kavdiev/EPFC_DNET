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
        <title> ${model.label}</title>
    </head>
    <body>
        <div align="center"> 
            <%@include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <h1 align="center" >${model.label} </h1>     
        <table width="700" height="100%" border="1"  align="center">
            <c:choose>
                <c:when test="${not empty model.rents}">
                    <tr>
                        <td  bgcolor="#3090C7">appart Id</>
                        <td colspan="2" bgcolor="#3090C7">Periode de reservation</>
                        <td bgcolor="#3090C7">Status</td>
                        <td bgcolor="#3090C7">Message</td>
                    </tr>
                    <tr>
                        <td colspan="5"  bgcolor="#3090C7">en attante</>
                    </tr>
                    <c:forEach items="${model.pending}" var="location">
                        <tr> 
                            <td><a href="appart.htm?id=${location.getAppart().idA}">${location.getAppart().idA}</a> </td>
                            <td>Semaine de debut: ${location.weekIn} </td>
                            <td> Semaine de fin: ${location.weekOut} </td>
                            <td>${location.getLocataire().nom}</td>
                            <td><a href="toApprove.htm?tool=approuve&id=${location.id}">Approuver</a> - <a href="toApprove.htm?tool=refuser&id=${location.id}"> refuser</a></td>
                        </tr>
                        <tr><td colspan="5" bgcolor="#DEDEDE"> </td></tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5"  bgcolor="#3090C7">acceptées</>
                    </tr>
                    <c:forEach items="${model.reserved}" var="location">
                        <tr> 
                            <td><a href="appart.htm?id=${location.getAppart().idA}">${location.getAppart().idA}</a> </td>
                            <td>Semaine de debut: ${location.weekIn} </td>
                            <td> Semaine de fin: ${location.weekOut} </td>
                            <td>${location.getLocataire().nom}</td>
                            <td><a href="toApprove.htm?tool=approuve&id=${location.id}">Approuver</a> - <a href="toApprove.htm?tool=refuser&id=${location.id}"> refuser</a></td>
                        </tr>
                        <tr><td colspan="5" bgcolor="#DEDEDE"> </td></tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5"  bgcolor="#3090C7">refusées</>
                    </tr>
                    <c:forEach items="${model.refused}" var="location">
                        <tr> 
                            <td><a href="appart.htm?id=${location.getAppart().idA}">${location.getAppart().idA}</a> </td>
                            <td>Semaine de debut: ${location.weekIn} </td>
                            <td> Semaine de fin: ${location.weekOut} </td>
                            <td>${location.getLocataire().nom}</td>
                            <td><a href="toApprove.htm?tool=approuve&id=${location.id}">Approuver</a> - <a href="toApprove.htm?tool=refuser&id=${location.id}"> refuser</a></td>
                        </tr>
                        <tr><td colspan="5" bgcolor="#DEDEDE"> </td></tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
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
                            <td><a href="toApprove.htm?tool=approuve&id=${location.id}">Approuver</a> - <a href="toApprove.htm?tool=refuser&id=${location.id}"> refuser</a></td>
                        </tr>
                        <tr><td colspan="5" bgcolor="#DEDEDE"> </td></tr>
                    </c:forEach>
                </c:otherwise>

            </c:choose>
            <tr>
                <td bgcolor="#C0C0C0" width="30px" height="26px"><img src="img/i_mesg.gif"></td>
                <td align="left" bgcolor="#C0C0C0" colspan="4"  height="26px"> Message: ${model.msg} </td>
            </tr>
        </table>
    </body>
</html>
