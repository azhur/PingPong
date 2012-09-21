<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="alert alert-info">
    <strong><fmt:message key="page.forgotPassword.msg1"/></strong>
    <br> <fmt:message key="page.forgotPassword.msg2"/>
</div>

<form:form method="post" action="forgotPasswordProcess" commandName="command" cssClass="form-horizontal">
    <div class="control-group ">
        <label class="control-label"><fmt:message key="account.email"/></label>

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
        <button type="submit" class="btn btn-primary"><fmt:message key="action.send"/></button>
        &nbsp;
        <button type="reset" class="btn"><fmt:message key="action.cancel"/></button>
    </div>
</form:form>
