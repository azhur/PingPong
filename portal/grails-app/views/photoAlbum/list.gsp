<!doctype html>
<html>
<head>
  <meta name="layout" content="main">
  <r:require module="application"/>
  <title><g:message code="photoAlbum.label.plural"/></title>
</head>

<body>
<ul class="breadcrumb">
  <li><a href="${application.getContextPath()}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
  <li class="active">
    <g:message code="photoAlbum.label.plural"/>
  </li>
</ul>

<h1>
  <g:link class="create btn btn-primary" action="create">
    <i class="icon-plus icon-white"></i> <g:message code="default.button.create.label"/>
  </g:link>
</h1>

<br>

<g:if test="${photoAlbums}">

  <table class="table table-hover">
    <thead>
    <tr>
      <th><g:message code="actions"/></th>
      <th><g:message code="photoAlbum.cover.label"/></th>
      <g:sortableColumn property="name" title="${message(code: 'photoAlbum.name.label')}"/>
      <th><g:message code="photoAlbum.description.label"/></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${photoAlbums}" status="i" var="album">
      <tr>
        <td>
          <g:link action="show" id="${album.id}" rel="tooltip" data-original-title="${message(code: 'default.button.view.label')}"><i class="icon-eye-open"></i></g:link>
          <g:link action="edit" id="${album.id}" rel="tooltip" data-original-title="${message(code: 'default.button.edit.label')}"><i class="icon-pencil"></i></g:link>
          <g:link action="delete" id="${album.id}" rel="tooltip" data-original-title="${message(code: 'default.button.delete.label')}"
                  onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><i
                  class="icon-trash"></i></g:link>
          <g:link controller="photo" action="list" params="[photoAlbumId: album.id]" rel="tooltip" data-original-title="${message(code: 'photo.label.plural')}"><i
                  class="icon-picture"></i></g:link>
        </td>
        <td>
          <g:link controller="photo" action="list" params="[photoAlbumId: album.id]">
            <img rel="tooltip-right" data-original-title="${album.photoCount} ${message(code: 'photo.label')}"
                 src="${application.contextPath + '/photo/image?id=' + (album.cover == null ? '0' : album.cover.id)}"
                 style="width: 140px; height: 100px" class="img-polaroid">
          </g:link>
        </td>
        <td>
          ${album.name}
        </td>
        <td>
          ${album.description}
        </td>
      </tr>
    </g:each>
    </tbody>
  </table>
  <g:if test="${total > max}">
    <div class="page-header"></div>

    <div class="pagination pagination-centered">
      <g:paginateBootstrap maxsteps="4" total="${total}"/>
    </div>
  </g:if>
</g:if>
<script>
  $(document).ready($(function () {
    $("[rel=tooltip]").tooltip();
    $("[rel=tooltip-right]").tooltip({
      placement:'right'
    })
  }))
</script>
</body>
</html>