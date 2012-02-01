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
        Registration form
        <small>Please complete the form below</small>
    </h1>
    <form:form method="post" action="register" commandName="registration" cssClass="form-horizontal">
        <div class="alert alert-info centered_text">
            <p>All fields are required</p>
        </div>
        <div class="control-group ">
                    <label class="control-label">Name:</label>

                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on">
                                <i class="icon-tag"></i>
                            </span>
                            <form:input path="name"/>
                        </div>
                    </div>
                </div>

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
                    <form:password path="pass1"/>
                </div>
            </div>
        </div>

        <div class="control-group ">
            <label class="control-label">Retype password:</label>

            <div class="controls">
                <div class="input-prepend">
                              <span class="add-on">
                                 <i class="icon-asterisk"></i>
                              </span>
                    <form:password path="pass2"/>
                </div>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">Gender:</label>

            <div class="controls">
                <div class="radio">
                    <form:radiobuttons path="gender" items="${genders}"/>
                </div>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Register</button>
            &nbsp;
            <button type="reset" class="btn">Cancel</button>
        </div>
    </form:form>
</div>


<script src="${pageContext.servletContext.contextPath}/resources/js/jquery/jquery-1.7.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap.min.js.js"></script>
</body>
</html>