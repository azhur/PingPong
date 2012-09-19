<%@ page import="com.pingpong.portal.Constants;" %>
<!doctype html>
<html>
<head>
  <meta name="layout" content="main">
  <r:require module="application"/>
  <title><g:message code="default.button.view.label"/> <g:message code="photoAlbum.label"/></title>
</head>

<body>

<ul class="breadcrumb">
  <li><a href="${application.getContextPath()}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
  <li><g:link action="list"><g:message code="albums"/></g:link> <span class="divider">/</span></li>
  <li class="active">${photoAlbum.name} <span class="divider">/</span></li>
  <li class="active">
    <g:message code="default.button.view.label"/>
  </li>
</ul>
<div class="page-header">
  <h1>
    <g:message code="default.button.view.label"/> <g:message code="photoAlbum.label"/>
  </h1>
</div>

<g:form class="form-horizontal entity-create-form pull-left">
  <fieldset class="container">
    <div class="control-group">
      <label class="control-label">
        <g:message code="photoAlbum.name.label" default="Name"/>
      </label>

      <div class="controls">
        <span class="uneditable-input">${photoAlbum?.name}</span>
      </div>
    </div>

    <div class="control-group">
      <label class="control-label">
        <g:message code="photoAlbum.description.label" default="Description"/>
      </label>

      <div class="controls">
        <span class="uneditable-textarea">${photoAlbum?.description}</span>
      </div>
    </div>

    <div class="control-group">
      <label class="control-label">
        <g:message code="photoAlbum.creator.label" default="Creator"/>
      </label>

      <div class="controls">
        <span class="uneditable-input">${photoAlbum?.creator?.player.name}</span>
      </div>
    </div>

    <div class="control-group">
      <label class="control-label">
        <g:message code="photoAlbum.creationDate.label" default="Creation date"/>
      </label>

      <div class="controls">
        <span class="uneditable-input">${photoAlbum?.creationDate?.toDate()?.format(com.pingpong.portal.Constants.DATE_TIME_FORMAT)}</span>
      </div>
    </div>

    <div class="control-group">
      <label class="control-label">
        <g:message code="photoAlbum.lastUpdatedBy.label" default="Last updated by"/>
      </label>

      <div class="controls">
        <span class="uneditable-input">${photoAlbum?.lastUpdatedBy?.player.name}</span>
      </div>
    </div>

    <div class="control-group">
      <label class="control-label">
        <g:message code="photoAlbum.lastUpdatedDate.label" default="Last updated date"/>
      </label>

      <div class="controls">
        <span class="uneditable-input">${photoAlbum?.lastUpdatedDate?.toDate()?.format(com.pingpong.portal.Constants.DATE_TIME_FORMAT)}</span>
      </div>
    </div>

    <div class="form-actions">
      <g:link controller="photo" action="list" params="[photoAlbumId: photoAlbum?.id]" class="btn btn-success"><i class="icon-camera icon-white"></i>&nbsp;<g:message code="view.photos" /></g:link>
      <g:link action="edit" class="btn btn-primary" id="${photoAlbum?.id}"><i class="icon-pencil icon-white"></i>&nbsp;<g:message code="default.button.edit.label" /></g:link>
      <g:link action="delete" class="btn btn-danger" id="${photoAlbum?.id}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><i class="icon-trash icon-white"></i>&nbsp;<g:message code="default.button.delete.label" /></g:link>
      <g:link action="list" class="btn"><i class="icon-thumbs-down"></i>&nbsp;<g:message code="cancel" /></g:link>
    </div>
  </fieldset>
</g:form>
</body>
</html>