<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<h1 class="page-header">
    Players
</h1>
<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th class="span1">Status</th>
        <th>Name</th>
        <th>Gender</th>
        <th>Actions</th>
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
                </c:when>
                <c:when test="${player.status == 'ACTIVE'}">
                    <span class="label label-success">
                </c:when>
                <c:otherwise>
                    <span class="label label-important">
                </c:otherwise>
                </c:choose>
                   ${player.status}
                </span>
            </td>

            <td>${player.name}</td>
            <td>${player.gender}</td>
            <td>
                <c:choose>
                <c:when test="${player.status == 'REGISTRATION'}">
                    <a href="${pageContext.servletContext.contextPath}/player/${player.id}/activate">activate</a>
                    <a href="${pageContext.servletContext.contextPath}/player/${player.id}/delete">delete</a>
                </c:when>
                <c:when test="${player.status == 'ACTIVE'}">
                    <a href="${pageContext.servletContext.contextPath}/player/${player.id}/block">block</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.servletContext.contextPath}/player/${player.id}/unblock">unblock</a>
                </c:otherwise>
                </c:choose>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
