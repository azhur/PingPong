<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<ul class="breadcrumb">
    <li>
        <a href="<c:url value="/"/>"><spring:message code="action.home"/></a> <span class="divider">/</span>
    </li>
    <li>
        <a href="<c:url value="/tournament/list"/>"><spring:message code="tournament.plural"/></a> <span class="divider">/</span>
    </li>
    <li class="active">${tournament.name}</li>
</ul>
<h1 class="page-header">
    <spring:message code="tournament.info"/>
</h1>

<form class="form-horizontal">
    <div class="control-group ">
        <label class="control-label"><strong><spring:message code="tournament.name"/></strong></label>

        <div class="controls">
            <label class="info-label">${tournament.name}</label>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label"><strong><spring:message code="tournament.status"/></strong></label>

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
                   <spring:message code="tournament.status.${tournament.status}"/>
                </span>
            </label>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label"><strong><spring:message code="tournament.max.participants"/></strong></label>

        <div class="controls">
            <label class="info-label">
               <span class="badge badge-info">${tournament.maxParticipantsCount}</span>
            </label>
        </div>
    </div>

    <c:if test="${tournament.status == 'REGISTRATION' and tournament.participants.size() == tournament.maxParticipantsCount}">
        <div class="control-group ">
            <label class="control-label"><strong><spring:message code="tournament.info.label"/></strong></label>

            <div class="controls">
                <label class="info-label">
                    <span class="label label-warning"><spring:message code="tournament.info.text"/></span>
                </label>
            </div>
        </div>
    </c:if>

    <div class="form-actions">
        <c:choose>
            <c:when test="${tournament.status == 'NEW'}">
                <a class="btn btn-success" href="<c:url value="/tournament/${tournament.id}/registration"/>"><i class="icon-ok icon-white"></i> <spring:message code="tournament.action.registration" /></a>
                <a class="btn btn-danger" href="<c:url value="/tournament/${tournament.id}/delete"/>"><i class="icon-trash icon-white"></i> <spring:message code="tournament.action.delete" /></a>
            </c:when>
            <c:when test="${tournament.status == 'REGISTRATION'}">
                <a class="btn btn-success" href="<c:url value="/tournament/${tournament.id}/activate"/>"><i class="icon-thumbs-up icon-white"></i> <spring:message code="tournament.action.activate" /></a>
                <a class="btn btn-inverse" href="<c:url value="/tournament/${tournament.id}/cancel"/>"><i class="icon-ban-circle icon-white"></i> <spring:message code="tournament.action.cancel" /></a>
            </c:when>
            <c:when test="${tournament.status == 'ACTIVE'}">
                <a class="btn btn-success" href="<c:url value="/tournament/${tournament.id}/finish"/>"><i class="icon-bell icon-white"></i> <spring:message code="tournament.action.finish" /></a>
                <a class="btn btn-inverse" href="<c:url value="/tournament/${tournament.id}/cancel"/>"><i class="icon-ban-circle icon-white"></i> <spring:message code="tournament.action.cancel" /></a>
            </c:when>
        </c:choose>
        <a data-toggle="modal" href="#participants" class="btn btn-info"><i class="icon-user icon-white"></i> <spring:message code="tournament.participants" /></a>
    </div>
</form>

<div id="participants" class="modal hide fade" style="display: none;">
    <div class="modal-header">
        <button class="close" data-dismiss="modal">&times;</button>
        <h3><spring:message code="tournament.participants" /></h3>

        <div class="modal-body">
            <c:choose>
                <c:when test="${empty tournament.participants}">
                    <span class="label label-info"><spring:message code="collection.empty" /></span>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="span3"><spring:message code="tournament.participant.name" /></th>
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
            <a href="#" class="btn" data-dismiss="modal"><spring:message code="action.close" /></a>
        </div>
    </div>

</div>

