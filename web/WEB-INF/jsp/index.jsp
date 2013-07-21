<%-- 
    Document   : index
    Created on : May 23, 2013, 9:46:33 PM
    Author     : baxter
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rent It</title>
    </head>
    <body>
        <table width="200" border="1" align="center">
            <tr>
                <td>
                    <h1>Rent it </h1> 
                    
                </td> 
            </tr>
            <tr>
                <td align="center"> 
                    <a href="user.htm?tool=noUser"><img src="img/nouser.png" width="57" height="58"></a>
                    <a href="user.htm?tool=login"><img src="img/login.png" width="57" height="58"></a>
                    <a href="user.htm?tool=newUser"><img src="img/newuser.png" width="57" height="58"></a>
                </td>
            </tr>
            <tr><td align="center" bgcolor="#C0C0C0" colspan="5">  ${model.msg} </td></tr>
        </table>

    </body>
</html>
