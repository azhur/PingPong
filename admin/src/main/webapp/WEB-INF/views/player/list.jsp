<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Players</title>

    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/bootstrap/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/main.css" type="text/css"/>

    <script src="${pageContext.servletContext.contextPath}/resources/js/jquery/jquery-1.7.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap-alerts.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap-dropdown.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap-twipsy.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap-popover.js"></script>
</head>
<body>
<div class="topbar-wrapper">
    <div class="topbar">
        <div class="topbar-inner">
            <div class="container">
                <h3><a href="${pageContext.servletContext.contextPath}">PingPong</a></h3>
                <ul class="nav">
                    <li><a href="players" class="active">Players</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <h3>Players</h3>

    <h2><a href="player/add">Add new</a></h2>
    <c:if test="${!empty players}">
        <table class="zebra-striped">
            <tr>
                <th>Name</th>
                <th>Birth</th>
                <th>Gender</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${players}" var="player">
                <tr>
                    <td><a href="players/${player.id}">${player.name}</a></td>
                    <td>${player.birth}</td>
                    <td>${player.gender}</td>
                    <td><a href="player/delete/${player.id}">delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>