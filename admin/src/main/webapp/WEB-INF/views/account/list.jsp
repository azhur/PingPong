<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<h1 class="page-header">
    Administrators
</h1>
<table class="table table-bordered">
    <thead>
    <tr>
        <th class="span1">Status</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="admin" items="${admins}">
        <tr>
            <td>
                <c:choose>
                <c:when test="${admin.enabled == true}">
                    <span class="label label-success">
                    Active
                </c:when>
                <c:otherwise>
                    <span class="label label-important">
                    Blocked
                </c:otherwise>
                </c:choose>
            </td>
            <td>${admin.email}</td>
            <td>
                <c:choose>
                <c:when test="${admin.enabled == true}">
                    <a href="${pageContext.servletContext.contextPath}/account/${admin.id}/block">block</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.servletContext.contextPath}/account/${admin.id}/unblock">unblock</a>
                </c:otherwise>
                </c:choose>
                <a href="${pageContext.servletContext.contextPath}/account/${admin.id}/delete">delete</a>
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
