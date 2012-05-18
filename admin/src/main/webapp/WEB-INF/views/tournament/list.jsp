<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<ul class="breadcrumb">
    <li>
        <a href="${pageContext.servletContext.contextPath}">Home</a> <span class="divider">/</span>
    </li>
    <li class="active">Tournaments</li>
</ul>
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
            <td><a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/view">${tournament.name}</a></td>
            <td>${tournament.maxParticipantsCount}</td>
            <td>
                <c:choose>
                <c:when test="${tournament.status == 'NEW'}">
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/registration">registration</a>
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/delete">delete</a>
                </c:when>
                <c:when test="${tournament.status == 'REGISTRATION'}">
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/activate">activate</a>
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/cancel">cancel</a>
                </c:when>
                <c:when test="${tournament.status == 'ACTIVE'}">
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/finish">finish</a>
                    <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/cancel">cancel</a>
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
