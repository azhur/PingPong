<!doctype html>
<html>
<head>
  <meta name="layout" content="main">
  <r:require module="application"/>
  <title><g:message code="default.edit.label" args="[message(code: 'photoAlbum.label')]"/></title>
</head>

<body>

<ul class="breadcrumb">
  <li><a href="${application.getContextPath()}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
  <li><g:link action="list"><g:message code="albums"/></g:link> <span class="divider">/</span></li>
  <li class="active"><g:link action="show" id="${photoAlbum.id}">${photoAlbum.name}</g:link> <span class="divider">/</span></li>
  <li class="active">
    <g:message code="default.button.edit.label"/>
  </li>
</ul>
<div class="page-header">
  <h1>
    <g:message code="default.edit.label" args="[message(code: 'photoAlbum.label')]"/>
  </h1>
</div>

<g:form action="update" method="post" class="form-horizontal entity-create-form pull-left">
  <fieldset class="container">
    <div class="control-group fieldcontain ${hasErrors(bean: photoAlbum, field: 'name', 'error')} required">
      <label for="name" class="control-label">
        <g:message code="photoAlbum.name.label" default="Name"/>

        <span class="required-indicator">*</span>
      </label>

      <div class="controls">
        <g:textField name="name" required="" value="${photoAlbum?.name}"/>
      </div>
    </div>

    <div class="control-group">
      <label for="description" class="control-label">
        <g:message code="photoAlbum.description.label" default="Description"/>
      </label>

      <div class="controls">
        <g:textArea cols="50" rows="10" name="description" value="${photoAlbum?.description}"/>
      </div>
    </div>

    <div class="form-actions">
      <g:hiddenField name="id" value="${photoAlbum?.id}"/>
      <button type="submit" class="btn btn-primary" name="create"><i class="icon-refresh icon-white"></i>&nbsp;<g:message code="default.button.update.label"/></button>
      <g:link action="list" class="btn"><i class="icon-thumbs-down"></i>&nbsp;<g:message code="cancel"/></g:link>
    </div>
  </fieldset>
</g:form>
</body>
</html>
