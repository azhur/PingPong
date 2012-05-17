<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<ul class="breadcrumb">
    <li>
        <a href="${pageContext.servletContext.contextPath}">Home</a> <span class="divider">/</span>
    </li>
    <li class="active">Registration tournaments</li>
</ul>
<c:choose>
    <c:when test="${empty tournaments}">
        <h2>
            <div class="alert alert-info">
                   No data!
            </div>
        </h2>
    </c:when>
    <c:otherwise>
        <h1 class="page-header">
            Register in
        </h1>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th class="span3">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tournament" items="${tournaments}">
                <tr>
                    <td>
                        <a href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/view">${tournament.name}</a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${tournament.currentPlayerRegistered == true}">
                                <a class="btn btn-inverse btn-mini" href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/giveUp">give up</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-mini btn-primary" href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/participate">participate</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>


<script>
    $(function () {
        $("[rel=tooltip]").tooltip({
        });
        $("[data-spy=scroll]").scrollspy();
    })
</script>
