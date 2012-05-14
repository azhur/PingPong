<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<h1 class="page-header">
    Tournaments &nbsp;
    <a href="${pageContext.servletContext.contextPath}/tournament/create" class="btn btn-primary">Create new</a>
</h1>
<table class="table table-bordered">
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
        <tr>
            <td>
                <c:choose>
                <c:when test="${tournament.status == 'NEW'}">
                    <span class="label label-info">
                </c:when>
                <c:when test="${tournament.status == 'PENDING'}">
                    <span class="label label-warning">
                </c:when>
                <c:when test="${tournament.status == 'ACTIVE'}">
                    <span class="label label-success">
                </c:when>
                <c:when test="${tournament.status == 'BLOCKED'}">
                    <span class="label label-important">
                </c:when>
                <c:otherwise>
                    <span class="label">
                </c:otherwise>
                </c:choose>
                   ${tournament.status}
                </span>
            </td>
            <td>${tournament.name}</td>
            <td>${tournament.maxParticipantsCount}</td>
            <td>
                <c:choose>
                <c:when test="${tournament.status == 'NEW'}">
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/registration">registration</a>
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/delete">delete</a>
                </c:when>
                <c:when test="${tournament.status == 'REGISTRATION'}">
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/activate">activate</a>
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/block">block</a>
                </c:when>
                <c:when test="${tournament.status == 'ACTIVE'}">
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/close">close</a>
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/block">block</a>
                </c:when>
                <c:when test="${tournament.status == 'BLOCKED'}">
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/unblock">unblock</a>
                </c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    $(function () {
        $("[rel=tooltip]").tooltip({
            trigger:'focus',
            placement:'right'
        });
        $("[data-spy=scroll]").scrollspy();
    })
</script>
