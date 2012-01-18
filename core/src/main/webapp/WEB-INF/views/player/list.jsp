<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
</head>
<body>

<h3>Players</h3><h2><a href="player/add">Add new</a></h2>
<c:if test="${!empty players}">
    <table class="data" border="1">
        <tr>
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${players}" var="player">
            <tr>
                <td><a href="players/${player.id}">${player.name}</a></td>
                <td>${player.email}</td>
                <td>${player.gender}</td>
                <td><a href="player/delete/${player.id}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>