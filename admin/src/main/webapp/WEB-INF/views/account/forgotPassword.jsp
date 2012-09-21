<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="alert alert-info">
    <strong><spring:message code="page.forgotPassword.msg1"/></strong>
    <br> <spring:message code="page.forgotPassword.msg2"/>
</div>

<form:form method="post" action="forgotPasswordProcess" commandName="command" cssClass="form-horizontal">
    <div class="control-group ">
        <label class="control-label"><spring:message code="account.email"/></label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="username"/>
            </div>
            <div class="input-prepend">
                    <span class="add-on">
                        <i class="icon-envelope"></i>
                    </span>

                <form:input path="username"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary"><spring:message code="action.send"/></button>
        &nbsp;
        <button type="reset" class="btn"><spring:message code="action.cancel"/></button>
    </div>
</form:form>
