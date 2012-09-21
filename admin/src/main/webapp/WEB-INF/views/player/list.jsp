<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1 class="page-header">
    <spring:message code="player.plural"/>
</h1>
<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th class="span1"><spring:message code="player.status"/></th>
        <th><spring:message code="player.name"/></th>
        <th><spring:message code="player.gender"/></th>
        <th><spring:message code="actions"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="player" items="${players}">
        <c:choose>
            <c:when test="${player.status == 'REGISTRATION'}">
                <tr class="warning">
            </c:when>
            <c:when test="${player.status == 'ACTIVE'}">
                <tr class="success">
            </c:when>
            <c:otherwise>
                <tr class="error">
            </c:otherwise>
        </c:choose>
        <td>
            <c:choose>
            <c:when test="${player.status == 'REGISTRATION'}">
                    <span class="label label-warning">
                    <spring:message code="player.status.REGISTRATION"/>
                </c:when>
                <c:when test="${player.status == 'ACTIVE'}">
                    <span class="label label-success">
                    <spring:message code="player.status.ACTIVE"/>
                </c:when>
                <c:otherwise>
                    <span class="label label-important">
                    <spring:message code="player.status.BLOCKED"/>
                </c:otherwise>
                </c:choose>
                </span>
        </td>

        <td>${player.name}</td>
        <td>
            <spring:message code="player.gender.${player.gender}" />
        </td>
        <td>
            <c:choose>
                <c:when test="${player.status == 'REGISTRATION'}">
                    <a href="<c:url value="/player/${player.id}/activate"/>"><spring:message code="player.action.activate"/></a>
                    <a href="<c:url value="/player/${player.id}/delete"/>"><spring:message code="player.action.delete"/></a>
                </c:when>
                <c:when test="${player.status == 'ACTIVE'}">
                    <a href="<c:url value="/player/${player.id}/block"/>"><spring:message code="player.action.block"/></a>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value="/player/${player.id}/unblock"/>"><spring:message code="player.action.unblock"/></a>
                </c:otherwise>
            </c:choose>
        </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
