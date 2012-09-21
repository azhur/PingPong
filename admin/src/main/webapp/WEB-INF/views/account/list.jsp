<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1 class="page-header">
    <fmt:message key="account.admin.plural"/>&nbsp;
    <a href="<c:url value="/account/create"/>" class="btn btn-primary"><fmt:message key="action.create.new"/></a>
</h1>
<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th class="span1"><fmt:message key="account.status"/></th>
        <th><fmt:message key="account.email"/></th>
        <th><fmt:message key="actions"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="admin" items="${admins}">
    <c:choose>
    <c:when test="${admin.enabled == true}">
        <tr class="success">
                </c:when>
                <c:otherwise>
                    <tr class="error">
                </c:otherwise>
                </c:choose>

            <td>
                <c:choose>
                <c:when test="${admin.enabled == true}">
                    <span class="label label-success">
                    <fmt:message key="account.status.ACTIVE"/>
                </c:when>
                <c:otherwise>
                    <span class="label label-important">
                    <fmt:message key="account.status.BLOCKED"/>
                </c:otherwise>
                </c:choose>
            </td>
            <td>${admin.email}</td>
            <td>
                <c:choose>
                    <c:when test="${admin.enabled == true}">
                        <a href="<c:url value="/account/${admin.id}/block"/>"><fmt:message key="account.action.block"/></a>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value="/account/${admin.id}/unblock"/>"><fmt:message key="account.action.unblock"/></a>
                    </c:otherwise>
                </c:choose>
                <a href="<c:url value="/account/${admin.id}/delete"/>"><fmt:message key="account.action.delete"/></a>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
