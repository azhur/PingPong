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
<g:form url="[action: 'forgotPasswordProcess', controller: 'account']" class="form-horizontal">
  <div class="control-group fieldcontain ${hasErrors(bean: command, field: 'email', 'error')} required">
    <label for="email" class="control-label">
      <g:message code="account.email" default="Email"/>

      <span class="required-indicator">*</span>
    </label>

    <div class="controls">
      <g:textField name="email" required='' value="${command?.email}"/>
      <g:hasErrors bean="${command}" field="email">
        <span class="help-block">
          <g:eachError var="err" bean="${command}" field="email">
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