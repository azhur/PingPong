<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1 class="page-header">
    <spring:message code="account.admin.plural"/>&nbsp;
    <a href="<c:url value="/account/create"/>" class="btn btn-primary"><spring:message code="action.create.new"/></a>
</h1>
<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th class="span1"><spring:message code="account.status"/></th>
        <th><spring:message code="account.email"/></th>
        <th><spring:message code="actions"/></th>
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
                    <spring:message code="account.status.ACTIVE"/>
                </c:when>
                <c:otherwise>
                    <span class="label label-important">
                    <spring:message code="account.status.BLOCKED"/>
                </c:otherwise>
                </c:choose>
            </td>
            <td>${admin.email}</td>
            <td>
                <c:choose>
                    <c:when test="${admin.enabled == true}">
                        <a href="<c:url value="/account/${admin.id}/block"/>"><spring:message code="account.action.block"/></a>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value="/account/${admin.id}/unblock"/>"><spring:message code="account.action.unblock"/></a>
                    </c:otherwise>
                </c:choose>
                <a href="<c:url value="/account/${admin.id}/delete"/>"><spring:message code="account.action.delete"/></a>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
