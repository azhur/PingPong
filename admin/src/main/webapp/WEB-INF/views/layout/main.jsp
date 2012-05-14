<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/datepicker.css" type="text/css"/>

    <%--jquery--%>
    <script src="${pageContext.servletContext.contextPath}/resources/js/jquery/jquery-1.7.1.min.js"></script>

    <%--bootstrap--%>
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap-alert.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap-dropdown.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap-modal.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap-tooltip.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap-scrollspy.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap-datepicker.js"></script>
</head>
<body data-spy="scroll">
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="i-bar"></span>
                <span class="i-bar"></span>
                <span class="i-bar"></span>
            </a>
            <a class="brand" href="${pageContext.servletContext.contextPath}/index">PingPong Administration</a>

            <div class="nav-collapse">
                <ul class="nav">
                    <sec:authorize access="isAnonymous()">
                        <li><a href="${pageContext.servletContext.contextPath}/login">Login</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN_USER')">
                        <li><a href="${pageContext.servletContext.contextPath}/account/list">Administrators</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/player/list">Players</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/tournament/list">Tournaments</a></li>
                    </sec:authorize>
                </ul>

                <sec:authorize access="hasRole('ROLE_ADMIN_USER')">
                    <ul class="nav pull-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><sec:authentication property="principal.username"/><b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="${pageContext.servletContext.contextPath}/account/changePassword">
                                        <span class="add-on">
                                           <i class="icon-pencil"></i>
                                        </span>
                                        Change Password
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="${pageContext.servletContext.contextPath}/logout">
                                    <span class="add-on">
                                     <i class="icon-remove"></i>
                                    </span>
                                        Log out
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>
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