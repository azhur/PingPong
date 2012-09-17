<%@ page import="com.pingpong.shared.Constraints" contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta name="layout" content="main"/>
  <r:require module="application"/>
  <title><g:message code="account.resetPassword.title"/></title>
</head>
<body>
<div class="page-header">
  <h1>
    <g:message code="account.resetPassword.title"/>
  </h1>
</div>
<g:form url="[action: 'resetPasswordProcess', controller: 'account']" class="form-horizontal">
  <g:hiddenField name="forgotPasswordId" value="${command.forgotPasswordId}"/>
  <g:hiddenField name="email" value="${command.email}"/>
  <div class="control-group fieldcontain ${hasErrors(bean: command, field: 'email', 'error')} required">
    <label for="email" class="control-label">
      <g:message code="account.email" default="Email"/>

      <span class="required-indicator">*</span>
    </label>

    <div class="controls">
      <g:textField name="email" required='' value="${command?.email}" disabled="true"/>
    </div>
  </div>
  <div class="control-group fieldcontain ${hasErrors(bean: command, field: 'pass1', 'error')} required">
    <label for="pass1" class="control-label">
      <g:message code="account.password" default="Password"/>

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
      <g:message code="account.retypePassword" default="Retype password"/>

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
    <button type="submit" class="btn btn-primary" name="create"><g:message code="action.reset"/> &nbsp;<i class="icon-ok-sign icon-white"></i></button>
    <g:link controller="home" action="index" class="btn"><g:message code="cancel"/>  &nbsp;<i class="icon-thumbs-down"></i></g:link>
  </div>
</g:form>
</body>
</html>