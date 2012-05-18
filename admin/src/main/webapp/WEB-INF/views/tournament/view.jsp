<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<ul class="breadcrumb">
    <li>
        <a href="${pageContext.servletContext.contextPath}">Home</a> <span class="divider">/</span>
    </li>
    <li>
        <a href="${pageContext.servletContext.contextPath}/tournament/list">Tournaments</a> <span class="divider">/</span>
    </li>
    <li class="active">${tournament.name}</li>
</ul>
<h1 class="page-header">
    Tournament info
</h1>

<form class="form-horizontal">
    <div class="control-group ">
        <label class="control-label"><strong>Name:</strong></label>

        <div class="controls">
            <label class="info-label">${tournament.name}</label>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label"><strong>Status:</strong></label>

        <div class="controls">
            <label class="info-label">
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
            </label>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label"><strong>Max participant count:</strong></label>

        <div class="controls">
            <label class="info-label">
               <span class="badge badge-info">${tournament.maxParticipantsCount}</span>
            </label>
        </div>
    </div>

    <div class="form-actions">
        <c:choose>
            <c:when test="${tournament.status == 'NEW'}">
                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/registration"><i class="icon-ok icon-white"></i> Registration</a>
                <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/delete"><i class="icon-trash icon-white"></i> Delete</a>
            </c:when>
            <c:when test="${tournament.status == 'REGISTRATION'}">
                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/activate"><i class="icon-thumbs-up icon-white"></i> Activate</a>
                <a class="btn btn-inverse" href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/cancel"><i class="icon-ban-circle icon-white"></i> Cancel</a>
            </c:when>
            <c:when test="${tournament.status == 'ACTIVE'}">
                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/finish"><i class="icon-bell icon-white"></i> Finish</a>
                <a class="btn btn-inverse" href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/cancel"><i class="icon-ban-circle icon-white"></i> Cancel</a>
            </c:when>
        </c:choose>
        <a data-toggle="modal" href="#participants" class="btn btn-info"><i class="icon-user icon-white"></i> Participants</a>
    </div>
</form>

<div id="participants" class="modal hide fade" style="display: none;">
    <div class="modal-header">
        <button class="close" data-dismiss="modal">&times;</button>
        <h3>Participants</h3>

        <div class="modal-body">
            <c:choose>
                <c:when test="${empty tournament.participants}">
                    <span class="label label-info">No data!</span>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="span3">Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="participant" items="${tournament.participants}">
                            <tr>
                                <td>
                                   ${participant.name}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>

        </div>

        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal">Close</a>
        </div>
    </div>

</div>

