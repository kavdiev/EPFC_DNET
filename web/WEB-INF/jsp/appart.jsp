<%-- 
    Document   : appart
    Created on : Jun 2, 2013, 8:01:00 PM
    Author     : baxter
--%>
<%@page import="model.Appart"%>
<%@page import="tools.Consts"%>
<%@page import="model.User"%>
<%@page import="tools.HTMLTool"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <%HTMLTool tool = new HTMLTool();%>
        <% User u = (User) request.getSession().getAttribute(Consts.CURRENT_USER);%>
        <% Appart a = (Appart) request.getAttribute("appart");%>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appart : ${model.appart.idA} </title>
    </head>
    <body>
        <table width="700" height="100%" border="1"  align="center"> 
            <tr>
                <td colspan="5" bgcolor="#00FFFF" > <h1 align="center" >Fiche Appartement</h1></td>
            </tr>
            <tr>
                <td align="left" >ID :</td> <td align="left" >${model.appart.idA}</td> 
            </tr>
            <tr>
                <td align="left" >Propriétaire :</td> <td align="left" >${model.appart.proprio.nom}</td> 
            </tr>
            <tr>
                <td align="left" >Locataire :</td> <td align="left" >--</td> 
            </tr>
            <tr><td bgcolor="#C0C0C0" width="30px" height="26px"><img src="img/i_mesg.gif"></td>
                <td align="left" bgcolor="#C0C0C0" colspan="4"  height="26px"> Message: ${model.msg} </td></tr>
        </table>
        <form:form method="POST" action="appart.htm" commandName="appart">
            <table  width="700" height="100%" border="1"  align="center">
                <tr>
                    <th>N° pieces</th>
                    <th>superficie (m²)</th>
                    <th>loyer</th>
                    <th>postCode</th>
                </tr>
                <tr>
                    <td>
                        <form:select path="pieces">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                </form:select>
                    ${appart.pieces}
            </td>
            <td><form:input path="superficie" /> </td>
            <td><form:input path="loyer" /> </td>
            <td><form:input path="postCode" /> </td>
            <input name="id" value="${appart.idA}"  type="hidden">
            </tr>
            <tr> 
                <td colspan="5" align="center" >
                    <input type="submit" name="action" value="Update"> &nbsp;
                    <input type="submit" name="action" value="Supprimer"></td>
            </tr>
        </table> 
    </form:form>

</body>
</html>
