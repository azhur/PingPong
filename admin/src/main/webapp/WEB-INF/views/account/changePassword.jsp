<%@ page import="com.pingpong.shared.Constraints" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<h1 class="page-header">
    <spring:message code="action.changePassword"/>
</h1>
<c:set var="key">
    <spring:message code="account.password.length.constraint" arguments="<%=Constraints.MIN_PASSWORD_LENGTH%>"/>
</c:set>

<form:form method="post" action="changePasswordProcess" commandName="command" cssClass="form-horizontal">

    <div class="control-group ">
        <label class="control-label">
            <spring:message code="account.oldPassword"/>
            <span class="required-indicator">*</span>
        </label>

        <div class="controls">
            <div class="input-prepend">
                    <span class="add-on">
                         <i class="icon-asterisk"></i>
                    </span>

                <form:password path="oldPass" maxlength="50" required='true'/>
            </div>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label">
            <spring:message code="account.newPassword"/>
            <span class="required-indicator">*</span>
        </label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="newPass1"/>
            </div>
            <div class="input-prepend">
                              <span class="add-on">
                                 <i class="icon-asterisk"></i>
                              </span>

                <form:password path="newPass1" maxlength="50" rel="tooltip" data-original-title="${key}" required='true'/>
            </div>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label">
            <spring:message code="account.retype.newPassword"/>
            <span class="required-indicator">*</span>
        </label>
        <div class="controls">
            <div class="input-prepend">
                              <span class="add-on">
                                 <i class="icon-asterisk"></i>
                              </span>

                <form:password path="newPass2" maxlength="50" rel="tooltip" title='${key}' required='true'/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary"><spring:message code="action.save"/></button>
        &nbsp;
        <button type="reset" class="btn"><spring:message code="action.cancel"/></button>
    </div>
</form:form>
<script>
    $(function () {
        $("[rel=tooltip]").tooltip({
            trigger:'focus',
            placement:'right'
        });
    })
</script>