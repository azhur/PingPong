<!doctype html>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title><g:layoutTitle default="Grails"/></title>
  <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon_new.ico')}">
  <g:layoutHead/>
  <r:layoutResources/>
</head>

<body>
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
      <g:link controller="home" class="brand"><g:message code="application.brand.name"/></g:link>
      <div class="nav-collapse collapse">
        <ul class="nav">
          <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-font icon-white"></i> <g:message code="language"/> <b class="caret"></b></a>
            <ul id="menu2" class="dropdown-menu">
              <li><a href="${application.contextPath}?lang=en"><g:img dir="images/flags" file="en.png"/> <g:message code="language.english"/></a></li>
              <li><a href="${application.contextPath}?lang=ru"><g:img dir="images/flags" file="ru.png"/> <g:message code="language.russian"/></a></li>
            </ul>
          </li>
          <sec:ifNotLoggedIn>
            <li><g:link controller="account" action="registration"><g:message code="user.register"/></g:link></li>
          </sec:ifNotLoggedIn>
          <sec:ifLoggedIn>
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-star icon-white"></i> <g:message code="tournament.plural"/> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><g:link controller="tournament" action="list" params="[status: 'REGISTRATION']"><i class="icon-share-alt"></i> <span class="badge badge-info"><g:message
                        code="tournament.status.REGISTRATION"/></span></g:link></li>
                <li><g:link controller="tournament" action="list" params="[status: 'ACTIVE']"><i class="icon-share-alt"></i> <span class="badge badge-success"><g:message
                        code="tournament.status.ACTIVE.plural"/></span></g:link></li>
                <li><g:link controller="tournament" action="list" params="[status: 'FINISHED']"><i class="icon-share-alt"></i> <span class="badge badge-important"><g:message
                        code="tournament.status.FINISHED.plural"/></span></g:link></li>
              </ul>
            </li>
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-th-list icon-white"></i> <g:message code="media"/> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><g:link controller="photoAlbum" action="list"><i class="icon-camera"></i> <g:message code="photoAlbum.label.plural"/></g:link></li>
              </ul>
            </li>
          </sec:ifLoggedIn>
        </ul>
        <sec:ifNotLoggedIn>
          <form action='${postUrl}' method='POST' id='loginForm' class="navbar-form pull-right form-inline">
            <input type="text" class="input-medium" name='j_username' id='username' placeholder="${message(code: 'springSecurity.login.username.label')}">
            <input type="password" class="input-small" name='j_password' id='password' placeholder="${message(code: 'springSecurity.login.password.label')}">
            <label class="checkbox">
              <input type="checkbox" name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>> <g:message
                    code="springSecurity.login.remember.me.label"/>
            </label>
            <button type="submit" class="btn"><g:message code="sign.in"/> <i class="icon-check"></i></button>
          </form>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
          <ul class="nav pull-right">
            <div class="btn-group">
              <button class="btn dropdown-toggle" data-toggle="dropdown"><i class="icon-user"></i> <sec:username/> <span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><g:link controller="account" action="changeProfile"><i class="icon-briefcase"></i> <g:message code="profile"/></g:link></li>
                <li><g:link controller="account" action="changePassword"><i class="icon-certificate"></i> <g:message code="changePassword"/></g:link></li>
                <li class="divider"></li>
                <li><g:link controller="logout"><i class="icon-off"></i> <g:message code="sign.out"/></g:link></li>
              </ul>
            </div>
          </ul>
        </sec:ifLoggedIn>
      </div>
    </div>
  </div>
</div>

<div class="content container">
  <g:if test="${flash.message}">
    <div class="alert">
      <button class="close" data-dismiss="alert">×</button>
      ${flash.message}
    </div>
  </g:if>
  <g:if test="${flash.info}">
    <div class="alert alert-info">
      <button class="close" data-dismiss="alert">×</button>
      ${flash.info}
    </div>
  </g:if>
  <g:if test="${flash.success}">
    <div class="alert alert-success">
      <button class="close" data-dismiss="alert">×</button>
      ${flash.success}
    </div>
  </g:if>
  <g:if test="${flash.error}">
    <div class="alert alert-error">
      <button class="close" data-dismiss="alert">×</button>
      ${flash.error}
    </div>
  </g:if>
  <sec:ifNotLoggedIn>
    <div class="alert alert-info">
      <g:message code="action.forgotPassword.question"/> &rarr;<g:link controller="account" action="forgotPassword" class="btn btn-link"><g:message code="action.reset"/> </g:link>
    </div>
  </sec:ifNotLoggedIn>
  <g:layoutBody/>
  <r:layoutResources/>
</div>
<footer class="navbar-fixed-bottom">
  &copy Uwiss 2012
</footer>
</body>
</html>