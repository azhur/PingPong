<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>
        <tiles:getAsString name="title"/>
    </title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="icon" href="<c:url value="/resources/images/favicon.ico"/>" type="image/x-icon">
    <link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico"/>" type="image/x-icon">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>" type="text/css"/>

    <%--jquery--%>
    <script src="<c:url value="/resources/js/jquery/jquery-1.7.1.min.js"/>"></script>

    <%--bootstrap--%>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
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
            <a class="brand" href="<c:url value=""/>"><fmt:message key="app.brand"/></a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-font"></i> <fmt:message key="language"/> <b class="caret"></b></a>
                        <ul id="menu2" class="dropdown-menu">
                            <li><a href="<c:url value="?lang=en"/>"><img src="<c:url value="/resources/images/flags/en.png"/>" alt=""/> <fmt:message key="language.english"/></a></li>
                            <li><a href="<c:url value="?lang=ru"/>"><img src="<c:url value="/resources/images/flags/ru.png"/>" alt=""/> <fmt:message key="language.russian"/></a></li>
                        </ul>
                    </li>
                    <sec:authorize access="isAnonymous()">
                        <li><a href="<c:url value="/login"/>"><fmt:message key="springSecurity.login.button"/></a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN_USER')">
                        <li><a href="<c:url value="/account/list"/>"><fmt:message key="account.admin.plural"/></a></li>
                        <li><a href="<c:url value="/player/list"/>"><fmt:message key="account.player.plural"/></a></li>
                        <li><a href="<c:url value="/tournament/list"/>"><fmt:message key="tournament.plural"/></a></li>
                    </sec:authorize>
                </ul>

                <sec:authorize access="hasRole('ROLE_ADMIN_USER')">
                    <ul class="nav pull-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><sec:authentication property="principal.username"/><b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="<c:url value="/account/changePassword"/>">
                                        <span class="add-on">
                                           <i class="icon-pencil"></i>
                                        </span>
                                        <fmt:message key="action.changePassword"/>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="<c:url value="/logout"/>">
                                    <span class="add-on">
                                     <i class="icon-remove"></i>
                                    </span>
                                        <fmt:message key="sign.out"/>
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