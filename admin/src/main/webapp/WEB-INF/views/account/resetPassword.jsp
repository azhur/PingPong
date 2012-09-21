<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1 class="page-header">
    <spring:message code="account.action.resetPassword"/>
</h1>

<form:form method="post" action="resetPasswordProcess" commandName="command" cssClass="form-horizontal">
    <form:hidden path="forgotPasswordId" />

    <div class="control-group ">
        <label class="control-label"><spring:message code="account.email"/></label>

        <div class="controls">
            <div class="input-prepend">
                    <span class="add-on">
                        <i class="icon-envelope"></i>
                    </span>

                <input class="input-large" type="text" placeholder="${account.email}" disabled="true" />
            </div>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label"><spring:message code="springSecurity.login.password.label"/></label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="pass1"/>
            </div>
            <div class="input-prepend">
                              <span class="add-on">
                                 <i class="icon-asterisk"></i>
                              </span>
                <form:password path="pass1" maxlength="50"/>
            </div>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label"><spring:message code="account.retypePassword"/></label>

        <div class="controls">
            <div class="input-prepend">
                              <span class="add-on">
                                 <i class="icon-asterisk"></i>
                              </span>
                <form:password path="pass2"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary"><spring:message code="action.reset"/></button>
        &nbsp;
        <button type="reset" class="btn"><spring:message code="action.cancel"/></button>
    </div>
</form:form>
