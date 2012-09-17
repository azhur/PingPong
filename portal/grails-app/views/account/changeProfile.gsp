<%@ page import="com.pingpong.domain.enumeration.Gender; com.pingpong.shared.Constraints" %>
<html>
<head>
  <meta name="layout" content="main"/>
  <r:require module="application"/>
  <title><g:message code="changeProfile"/></title>
</head>
<body>
<div class="page-header">
  <h1>
    <g:message code="changeProfile"/>
  </h1>
</div>
<g:form url="[action: 'changeProfileProcess', controller: 'account']" class="form-horizontal">
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

  <div class="control-group fieldcontain ${hasErrors(bean: command, field: 'name', 'error')} required">
    <label for="name" class="control-label">
      <g:message code="account.name" default="Name"/>

      <span class="required-indicator">*</span>
    </label>

    <div class="controls">
      <g:textField name="name" required='' value="${command?.name}"/>
      <g:hasErrors bean="${command}" field="name">
        <span class="help-block">
          <g:eachError var="err" bean="${command}" field="name">
            <g:message error="${err}"/>
          </g:eachError>
        </span>
      </g:hasErrors>
    </div>
  </div>

  <div class="control-group fieldcontain ${hasErrors(bean: command, field: 'gender', 'error')} required">
    <label for="gender" class="control-label">
      <g:message code="account.gender" default="Gender"/>

      <span class="required-indicator">*</span>
    </label>

    <div class="controls">
      <div class="radio">
        <g:radioGroup
                labels="${Gender.values()*.name()}"
                values="${Gender.values()}"
                name="gender"
                value="${command?.gender?.name()}">
          <p>${it.radio} <g:message code="${'account.gender.' + it.label}" /></p>
        </g:radioGroup>
        <g:hasErrors bean="${command}" field="gender">
          <span class="help-block">
            <g:eachError var="err" bean="${command}" field="gender">
              <g:message error="${err}"/>
            </g:eachError>
          </span>
        </g:hasErrors>
      </div>
    </div>
  </div>

  <div class="control-group fieldcontain ${hasErrors(bean: command, field: 'birth', 'error')} required">
    <label for="birth" class="control-label">
      <g:message code="account.birth" default="Birth"/>

      <span class="required-indicator">*</span>
    </label>

    <div class="controls">
      <joda:datePicker name="birth" value="${command?.birth}" precision="day" years="${1920..2012}"/>
      <g:hasErrors bean="${command}" field="birth">
        <span class="help-block">
          <g:eachError var="err" bean="${command}" field="birth">
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