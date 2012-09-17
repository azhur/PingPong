<%@ page import="com.pingpong.domain.Tournament" %>
<html>
<head>
  <meta name="layout" content="main"/>
  <r:require module="application"/>
  <title><g:message code="tournament.view"/></title>
</head>

<body>
<div class="page-header">
  <h1>
    <g:message code="tournament.view"/>
  </h1>
</div>

<g:form class="form-horizontal">
  <div class="control-group">
    <label class="control-label"><strong><g:message code="tournament.name"/></strong></label>

    <div class="controls">
      <label class="info-label">${tournament.name}</label>
    </div>
  </div>

  <div class="control-group ">
    <label class="control-label"><strong><g:message code="tournament.status"/></strong></label>

    <div class="controls">
      <label class="info-label">
        <g:if test="${tournament.status == Tournament.Status.REGISTRATION}">
          <span class="label label-warning">
        </g:if>
        <g:elseif test="${tournament.status == Tournament.Status.ACTIVE}">
          <span class="label label-success">
        </g:elseif>
        <g:elseif test="${tournament.status == Tournament.Status.FINISHED}">
          <span class="label label-important">
        </g:elseif>
        <g:message code="${'tournament.status.' + tournament.status}"/>
      </span>
      </label>
    </div>
  </div>

  <div class="control-group ">
    <label class="control-label"><strong><g:message code="tournament.participant"/></strong></label>

    <div class="controls">
      <label class="info-label">
        <g:if test="${tournament.currentPlayerRegistered}">
          <span class="badge badge-success"><g:message code="answer.yes"/></span>
        </g:if>
        <g:else>
          <span class="badge badge-warning"><g:message code="answer.no"/></span>
        </g:else>
      </label>
    </div>
  </div>

  <g:if test="${tournament.status == Tournament.Status.REGISTRATION && tournament.participants.size() == tournament.maxParticipantsCount}">
    <div class="control-group ">
      <label class="control-label"><strong><g:message code="information.label"/></strong></label>

      <div class="controls">
        <label class="info-label">
          <span class="label label-warning"><g:message code="tournament.info.all.in"/></span>
        </label>
      </div>
    </div>
  </g:if>

  <div class="form-actions">
    <g:if test="${tournament.status == Tournament.Status.REGISTRATION && tournament.currentPlayerRegistered && tournament.participants.size() != tournament.maxParticipantsCount}">
      <g:link class="btn btn-warning btn-mini"  controller="tournament" action="giveUp" id="${tournament.id}"><i class="icon-thumbs-down icon-white"></i> <g:message code="tournament.giveUp"/> </g:link>
    </g:if>
    <g:elseif test="${tournament.status == Tournament.Status.REGISTRATION && !tournament.currentPlayerRegistered && tournament.participants.size() != tournament.maxParticipantsCount}">
      <g:link class="btn btn-success btn-mini"  controller="tournament" action="participate" id="${tournament.id}"><i class="icon-thumbs-up icon-white"></i> <g:message code="tournament.participate"/> </g:link>
    </g:elseif>
    <a data-toggle="modal" href="#participants" class="btn btn-small btn-info"><i class="icon-user icon-white"></i> <g:message code="tournament.participants"/></a>
  </div>
</g:form>

<div id="participants" class="modal hide fade" style="display: none;">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

    <h3><g:message code="tournament.participants"/> (${tournament.participants.size()}/${tournament.maxParticipantsCount})</h3>
  </div>

  <div class="modal-body">
    <g:if test="${tournament.participants}">
      <table class="table">
        <thead>
        <tr>
          <th class="span3"><g:message code="account.name" /></th>
        </tr>
        </thead>
        <tbody>
        <g:each status="i" var="participant" in="${tournament.participants}">
          <tr>
            <td>
              ${participant.name}
            </td>
          </tr>
        </g:each>
        </tbody>
      </table>
    </g:if>
    <g:else>
      <div class="alert alert-block">
        <g:message code="collection.empty"/>
      </div>
    </g:else>

  </div>

  <div class="modal-footer">
    <a href="#" class="btn" data-dismiss="modal"><g:message code="action.close"/></a>
  </div>
</div>
</body>
</html>