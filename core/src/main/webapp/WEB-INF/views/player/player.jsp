<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>New player</title>
</head>
<body>

<h2>Add new player</h2>

<form:form method="post" action="add" commandName="player">
    <table>
        <tr>
            <td><spring:message text="name"/></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><spring:message text="email"/></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td><spring:message text="login"/></td>
            <td><form:input path="login"/></td>
        </tr>
        <tr>
            <td><spring:message text="password"/></td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="<spring:message text="insert"/>"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>