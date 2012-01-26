<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>PingPong</title>

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

</div>
<footer class="footer">
    <div class="container">
        <p class="pull-right">
            <small>2012</small>
        </p>
        <p>
            All copyrights Artur Zhurat
        </p>
    </div>
</footer>

</body>
</html>