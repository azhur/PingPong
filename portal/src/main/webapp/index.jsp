<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>PingPong Portal</title>

    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/bootstrap.min.css" type="text/css"/>
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
     <h1>Welcome to PingPong</h1>
</div>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery/jquery-1.7.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap.min.js.js"></script>
</body>
</html>