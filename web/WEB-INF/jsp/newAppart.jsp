<%-- 
    Document   : appart
    Created on : Apr 22, 2013, 9:27:39 PM
    Author     : baxter
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Appart</title>
    </head>
    <body>
        <h1>New Appart Creation page</h1>
        <form:form method="POST" action="appart.htm" commandName="appart">
            <table>
                <tr>
                    <th>N° pieces</th>
                    <th>superficie (m²)</th>
                    <th>loyer</th>
                    <th>postCode</th>
                </tr>
                <tr>
                    <td><form:select path="pieces">
                    <option value="1" selected="selected">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                </form:select> 
            </td>

            <td><form:input path="superficie" /> </td>
            <td><form:input path="loyer" /> </td>
            <td><form:input path="postCode" /> </td>
            <td><input name="Creer" value="Créer" type="submit" ></td>
            <input name="action" value="appartAdded" type="hidden">
            <input name="userid" value="${model.userid}" type="hidden">
            </tr>
        </table> 
    </form:form>
</body>
</html>
