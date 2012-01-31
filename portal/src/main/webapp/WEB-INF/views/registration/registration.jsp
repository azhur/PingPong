<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>PingPong Portal</title>

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
                <h3><a href="${pageContext.servletContext.contextPath}">PingPong Portal</a></h3>
                <ul class="nav">
                    <li><a href="login" class="active">Login</a></li>
                    <li><a href="registration" class="active">Register</a></li>
                </ul>

            </div>
        </div>
    </div>
</div>
<div id="content" class="container">
    <h1 class="page-header">
        Registration form
        <small>Please complete the form below</small>
    </h1>
    <form:form method="post" action="register" commandName="registration">
        <div class="alert-message block-message info centered_text">
            <p>All fields are required</p>
        </div>
        <div class="clearfix ">
            <label for="name">Name:</label>

            <div class="input">
                <form:input path="name"/>
            </div>
        </div>

        <div class="clearfix ">
            <label>Email:</label>

            <div class="input">
                <form:input path="email"/>
            </div>
        </div>
        <div class="clearfix ">
            <label>Password:</label>

            <div class="input">
                <form:password path="pass1"/>
            </div>
        </div>

        <div class="clearfix ">
            <label>Retype password:</label>

            <div class="input">
                <form:password path="pass2"/>
            </div>
        </div>

        <div class="actions">
            <input type="submit" class="btn primary" value="Register">
            &nbsp;
            <button type="reset" class="btn">Cancel</button>
        </div>
    </form:form>
</div>

</body>
</html>