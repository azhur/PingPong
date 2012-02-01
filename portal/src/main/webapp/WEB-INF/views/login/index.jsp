<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="utf-8">
<head>
    <title>PingPong Portal</title>

    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/main.css" type="text/css"/>

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
            <a class="brand" href="${pageContext.servletContext.contextPath}">PingPong Portal</a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li><a href="login">Login</a></li>
                    <li><a href="registration">Register</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div id="content" class="container">
    <h1 class="page-header">
        Sign in
    </h1>
    <form:form method="post" action="login" commandName="login" cssClass="form-horizontal">
        <div class="control-group ">
            <label class="control-label">Email:</label>

            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on">
                        <i class="icon-envelope"></i>
                    </span>
                    <form:input path="email"/>
                </div>
            </div>
        </div>
        <div class="control-group ">
            <label class="control-label">Password:</label>

            <div class="controls">
                <div class="input-prepend">
                      <span class="add-on">
                         <i class="icon-asterisk"></i>
                      </span>
                    <form:password path="pass"/>
                </div>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Login</button>
            &nbsp;or&nbsp;
            <a href="forgot_password">Forgot password</a>
        </div>
    </form:form>
</div>


<script src="${pageContext.servletContext.contextPath}/resources/js/jquery/jquery-1.7.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap.min.js.js"></script>
</body>
</html>