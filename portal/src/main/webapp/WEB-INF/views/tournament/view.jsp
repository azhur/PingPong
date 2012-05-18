<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<ul class="breadcrumb">
    <li>
        <a href="${pageContext.servletContext.contextPath}">Home</a> <span class="divider">/</span>
    </li>
    <li>
        <a href="${pageContext.servletContext.contextPath}/tournaments/registration">Registration tournaments</a> <span class="divider">/</span>
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
            <label class="info-label"><span class="label label-info">${tournament.status}</span></label>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label"><strong>Participant:</strong></label>

        <div class="controls">
            <label class="info-label">
                <c:choose>
                    <c:when test="${tournament.currentPlayerRegistered == true}">
                        <span class="badge badge-success">YES</span>
                    </c:when>
                    <c:otherwise>
                        <span class="label badge-warning">NO</span>
                    </c:otherwise>
                </c:choose>
            </label>
        </div>
    </div>

    <div class="form-actions">
        <c:choose>
            <c:when test="${tournament.currentPlayerRegistered == true && tournament.status == 'REGISTRATION'}">
                <a class="btn btn-inverse btn-small" href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/giveUp"><i class="icon-thumbs-down icon-white"></i> give up</a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-small btn-success" href="${pageContext.servletContext.contextPath}/tournament/${tournament.id}/participate"><i class="icon-thumbs-up icon-white"></i> participate</a>
            </c:otherwise>
        </c:choose>
        <a data-toggle="modal" href="#participants" class="btn btn-small btn-info"><i class="icon-user icon-white"></i> participants</a>
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

