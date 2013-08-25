<%-- 
    Document   : newUser
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
        <title>New User</title>
    </head>
    <body><table align="center" width="100%" height="100%" border="1">
            <tr>
                <td colspan="2" align="center" bgcolor="#00FFFF"> <h2>New User Creation page </h2></td> 
            </tr>
            <tr><td colspan="2">
                    <form:form method="POST" action="user.htm" commandName="user">
                        <table> 
                            <tr>
                                <th>Nom</th>
                                <th>Password</th>
                                <th>PostCode</th>
                            </tr>
                            <tr>
                                <td><form:input path="nom" /> </td>
                                <td><form:input path="password" /> </td>
                                <td><form:input path="postCode" /> </td>
                                <td><input name="Creer" value="Créer" type="submit" ></td>
                            <input name="userAdded" value="userAdded" type="hidden">
                            </tr>
                        </table> 
                    </form:form> 
                </td> 
            </tr>
            <tr>
                <td bgcolor="#C0C0C0" width="30px" height="26px"><img src="img/i_mesg.gif"></td>
                <td align="left" bgcolor="#C0C0C0" colspan="4"  height="26px"> Message: ${model.msg} </td>
           </tr>
        </table>
    </body>
</html>
