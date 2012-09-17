<html>
<head>
  <meta name="layout" content="main"/>
  <r:require module="application"/>
  <title><g:message code="tournament.plural"/></title>
</head>

<body>
<div class="page-header">
  <h1>
    ${title}
  </h1>
</div>
<g:if test="${tournaments}">

  <table class="table table-hover">
    <thead>
    <tr>
      <th><g:message code="actions"/></th>
      <th><g:message code="tournament.name"/></th>
    </tr>
    </thead>
    <tbody>
    <g:each status="i" var="tournament" in="${tournaments}">
      <tr>
        <td>
          <g:if test="${tournament.currentPlayerRegistered}">
            <g:link class="btn btn-warning btn-mini"  controller="tournament" action="giveUp" id="${tournament.id}"><g:message code="tournament.giveUp"/> </g:link>
          </g:if>
          <g:else>
            <g:link class="btn btn-success btn-mini"  controller="tournament" action="participate" id="${tournament.id}"><g:message code="tournament.participate"/> </g:link>
          </g:else>
        </td>
        <td>
          <g:link controller="tournament" action="view" id="${tournament.id}">${tournament.name}</g:link>
        </td>
      </tr>
    </g:each>
    </tbody>
  </table>
  <g:if test="${total > max}">
    <div class="page-header"></div>

    <div class="pagination pagination-centered">
      <g:paginateBootstrap maxsteps="10" total="${total}"/>
    </div>
  </g:if>
</g:if>
<g:else>
  <div class="alert alert-block">
    <g:message code="collection.empty"/>
  </div>
</g:else>
</body>
</html>