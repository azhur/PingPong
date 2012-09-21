<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/fmt_rt" %>
<h1 class="page-header">
    <fmt:message key="action.create">
        <fmt:param><fmt:message key="account.admin"/></fmt:param>
    </fmt:message>
</h1>
<form:form method="post" action="createProcess" commandName="command" cssClass="form-horizontal">
    <div class="control-group required">
        <label class="control-label">
            <fmt:message key="account.email"/>
            <span class="required-indicator">*</span>
        </label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="email"/>
            </div>
            <div class="input-prepend">
                            <span class="add-on">
                                <i class="icon-envelope"></i>
                            </span>

                <form:input path="email" required="true"/>
            </div>
        </div>
    </div>
    <div class="control-group required">
        <label class="control-label">
            <fmt:message key="springSecurity.login.password.label"/>
            <span class="required-indicator">*</span>
        </label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="pass1"/>
            </div>
            <div class="input-prepend">
                                      <span class="add-on">
                                         <i class="icon-asterisk"></i>
                                      </span>
                <form:password path="pass1" maxlength="50" required="true"/>
            </div>
        </div>
    </div>

    <div class="control-group required">
        <label class="control-label">
            <fmt:message key="account.retypePassword"/>
            <span class="required-indicator">*</span>
        </label>

        <div class="controls">
            <div class="input-prepend">
                                      <span class="add-on">
                                         <i class="icon-asterisk"></i>
                                      </span>
                <form:password path="pass2" required="true"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="action.create">
                <fmt:param value=""/>
            </fmt:message>
        </button>
        &nbsp;
        <button type="reset" class="btn"><fmt:message key="action.cancel"/></button>
    </div>
</form:form>
<script>
    $(function () {
        $("[rel=tooltip]").tooltip({
            trigger:'focus',
            placement:'right'
        });
        $("[data-spy=scroll]").scrollspy();
    })
</script>
