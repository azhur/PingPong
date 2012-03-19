<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>
        <tiles:getAsString name="title"/>
    </title>
    <link rel="icon" href="${pageContext.servletContext.contextPath}/resources/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/resources/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/main.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/jquery-ui-1.8.17.datepicker.css" type="text/css"/>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="i-bar"></span>
                <span class="i-bar"></span>
                <span class="i-bar"></span>
            </a>
            <a class="brand" href="${pageContext.servletContext.contextPath}/index">PingPong Portal</a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li><a href="login">Login</a></li>
                    <li><a href="registration">Register</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery/jquery-1.7.1.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery/jquery-ui-1.8.17.datepicker.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<div id="content" class="container">
    <c:if test="${not empty errorMsg}">
        <div class="alert alert-error">
            <a class="close" data-dismiss="alert">×</a>
                ${errorMsg}
        </div>
    </c:if>
    <c:if test="${not empty infoMsg}">
        <div class="alert alert-info">
            <a class="close" data-dismiss="alert">×</a>
                ${infoMsg}
        </div>
    </c:if>
    <c:if test="${not empty successMsg}">
        <div class="alert alert-success">
            <a class="close" data-dismiss="alert">×</a>
                ${successMsg}
        </div>
    </c:if>
    <c:if test="${not empty warningMsg}">
        <div class="alert alert-block">
            <a class="close" data-dismiss="alert">×</a>
                ${warningMsg}
        </div>
    </c:if>
    <tiles:insertAttribute name="content"/>
</div>
</body>
</html>