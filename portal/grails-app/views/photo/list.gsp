<%@ page import="com.pingpong.portal.Constants;" %>
<!doctype html>
<html>
<head>
  <meta name="layout" content="main">
  <r:require module="application"/>
  <title><g:message code="photo.label.plural"/></title>
</head>

<body>
<ul class="breadcrumb">
  <li><a href="${application.getContextPath()}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
  <li><g:link controller="photoAlbum" action="list"><g:message code="albums"/></g:link> <span class="divider">/</span></li>
  <li>
<g:link controller="photoAlbum" action="show" id="${photoAlbumId}">${photoAlbumName}</g:link>
  </li>
  <span class="divider">/</span></li>
  <li class="active">
    <g:message code="photo.label.plural"/>
  </li>
</ul>

<div>
  <h1>
    <g:link class="create btn btn-primary" action="create" params="[photoAlbumId: photoAlbumId]">
      <i class="icon-plus icon-white"></i> <g:message code="default.add.label" args="['']"/>
    </g:link>
  </h1>

</div>
<g:if test="${photos}">
  <div id="myCarousel" class="carousel slide">
    <!-- Carousel items -->
    <div class="carousel-inner">
      <g:each in="${photos}" status="i" var="photo">
        <div class="${i == 0 ? 'item active' : 'item'}">
          <img src="${application.contextPath}/photo/image?id=${photo.id}"/>

          <div class="carousel-caption">

            <div class="row-fluid">
              <div class="span2">
                  <g:link action="edit" id="${photo.id}" rel="tooltip" data-original-title="${message(code: 'default.button.edit.label')}"><i
                          class="icon-pencil icon-white"></i></g:link>
                  <g:link action="delete" id="${photo.id}" params="[photoAlbumId: photoAlbumId]" rel="tooltip" data-original-title="${message(code: 'default.button.delete.label')}"
                          onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><i
                          class="icon-trash icon-white"></i></g:link>
                  <i rel="popover" data-content="<p>
            ${photo.description}
              </p>
                <p>
                <i>${message(code: 'uploaded')}:</i> <strong>${photo.uploadingDate?.toDate()?.format(com.pingpong.portal.Constants.DATE_TIME_FORMAT)}</strong> <i>${message(code: 'by')}:</i> <strong>${photo.uploader?.player?.name}</strong>
                </p>
          " data-original-title="${photo.name}" class="icon-info-sign icon-white"></i>
              <g:if test="${photo.id != coverId}">
                 <g:link action="setCover" id="${photo.id}" params="[photoAlbumId: photoAlbumId]" rel="tooltip" data-original-title="${message(code: 'photo.setCover')}"><i class="icon-camera icon-white"></i></g:link>
              </g:if>
              </div>
              <div class="span2 offset8">
                <p>${i+1} ${message(code: 'of')} ${photos.size()}</p>
              </div>
            </div>
          </div>
        </div>
      </g:each>
    </div>

    <!-- Carousel nav -->
    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
  </div>
</g:if>
<script>
  $(document).ready($(function () {
    $("#myCarousel").carousel();
    $("[rel=tooltip]").tooltip();
    $("[rel=popover]").popover({
      trigger:'hover'
    })
  }))
</script>
</body>
</html>