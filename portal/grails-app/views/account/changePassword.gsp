<%@ page import="com.pingpong.shared.Constraints" contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta name="layout" content="main"/>
  <r:require module="application"/>
  <title><g:message code="changePassword"/></title>
</head>
<body>
<div class="page-header">
  <h1>
    <g:message code="changePassword"/>
  </h1>
</div>
<g:form url="[action: 'changePasswordProcess', controller: 'account']" class="form-horizontal">
  <div class="control-group fieldcontain ${hasErrors(bean: command, field: 'oldPass', 'error')} required">
    <label for="oldPass" class="control-label">
      <g:message code="account.oldPassword" default="Old password"/>

      <span class="required-indicator">*</span>
    </label>

    <div class="controls">
      <g:passwordField name="oldPass" required='' value="${command?.oldPass}"/>
    </div>
  </div>
  <div class="control-group fieldcontain ${hasErrors(bean: command, field: 'pass1', 'error')} required">
    <label for="pass1" class="control-label">
      <g:message code="account.newPassword" default="New password"/>

      <span class="required-indicator">*</span>
    </label>

    <div class="controls">
      <g:passwordField name="pass1" required='' value="${command?.pass1}" placeholder="${message(code: 'minsize.constraint', args: [Constraints.MIN_PASSWORD_LENGTH])}"/>
      <g:hasErrors bean="${command}" field="pass1">
        <span class="help-block">
          <g:eachError var="err" bean="${command}" field="pass1">
            <g:message error="${err}"/>
          </g:eachError>
        </span>
      </g:hasErrors>
    </div>
  </div>

  <div class="control-group fieldcontain ${hasErrors(bean: command, field: 'pass2', 'error')} required">
    <label for="pass2" class="control-label">
      <g:message code="account.retype.newPassword" default="Retype new password"/>

      <span class="required-indicator">*</span>
    </label>

    <div class="controls">
      <g:passwordField name="pass2" required='' value="${command?.pass2}"/>
      <g:hasErrors bean="${command}" field="pass2">
        <span class="help-block">
          <g:eachError var="err" bean="${command}" field="pass2">
            <g:message error="${err}"/>
          </g:eachError>
        </span>
      </g:hasErrors>
    </div>
  </div>
  <div class="form-actions">
    <button type="submit" class="btn btn-primary" name="create"><g:message code="save"/> &nbsp;<i class="icon-ok-sign icon-white"></i></button>
    <g:link controller="home" action="index" class="btn"><g:message code="cancel"/>  &nbsp;<i class="icon-thumbs-down"></i></g:link>
  </div>
</g:form>
</body>
</html>