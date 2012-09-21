<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sql_rt" uri="http://java.sun.com/jstl/sql_rt" %>
<ul class="breadcrumb">
    <li>
        <a href="<c:url value=""/>">Home</a> <span class="divider">/</span>
    </li>
    <li class="active">Tournaments</li>
</ul>
<h1 class="page-header">
    Tournaments &nbsp;
    <a href="<c:url value="/tournament/create"/>" class="btn btn-primary">Create new</a>
</h1>
<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th class="span1">Status</th>
        <th>Name</th>
        <th>Players count</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tournament" items="${tournaments}">
    <c:choose>
    <c:when test="${tournament.status == 'NEW'}">
                    <tr class="info">
                </c:when>
                <c:when test="${tournament.status == 'REGISTRATION'}">
                    <tr class="warning">
                </c:when>
                <c:when test="${tournament.status == 'ACTIVE'}">
                    <tr class="success">
                </c:when>
                <c:when test="${tournament.status == 'CANCELED'}">
                    <tr class="error">
                </c:when>
                <c:otherwise>
                    <tr>
                </c:otherwise>
                </c:choose>

            <td>
                <c:choose>
                <c:when test="${tournament.status == 'NEW'}">
                    <span class="label label-info">
                </c:when>
                <c:when test="${tournament.status == 'REGISTRATION'}">
                    <span class="label label-warning">
                </c:when>
                <c:when test="${tournament.status == 'ACTIVE'}">
                    <span class="label label-success">
                </c:when>
                <c:when test="${tournament.status == 'CANCELED'}">
                    <span class="label label-important">
                </c:when>
                <c:otherwise>
                    <span class="label">
                </c:otherwise>
                </c:choose>
                   ${tournament.status}
                </span>
            </td>
            <td><a href="<c:url value="/tournament/${tournament.id}/view"/>">${tournament.name}</a></td>
            <td>${tournament.maxParticipantsCount}</td>
            <td>
                <c:choose>
                <c:when test="${tournament.status == 'NEW'}">
                    <a href="<c:url value="/tournament/${tournament.id}/registration"/>">registration</a>
                    <a href="<c:url value="/tournament/${tournament.id}/delete"/>">delete</a>
                </c:when>
                <c:when test="${tournament.status == 'REGISTRATION'}">
                    <a href="<c:url value="/tournament/${tournament.id}/activate"/>">activate</a>
                    <a href="<c:url value="/tournament/${tournament.id}/cancel"/>">cancel</a>
                </c:when>
                <c:when test="${tournament.status == 'ACTIVE'}">
                    <a href="<c:url value="/tournament/${tournament.id}/finish"/>">finish</a>
                    <a href="<c:url value="/tournament/${tournament.id}/cancel"/>">cancel</a>
                </c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
