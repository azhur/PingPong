<%@ page import="com.pingpong.shared.Constraints" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/fmt_rt" %>
<h1 class="page-header">
    <spring:message key="action.changePassword"/>
</h1>
<%
    final String passwordMinLength ="Should be at least " +  Constraints.MIN_PASSWORD_LENGTH + " characters length";
%>
<form:form method="post" action="changePasswordProcess" commandName="command" cssClass="form-horizontal">

    <div class="control-group ">
        <label class="control-label"><spring:message key="account.oldPassword"/></label>

        <div class="controls">
            <div class="input-prepend">
                    <span class="add-on">
                         <i class="icon-asterisk"></i>
                    </span>

                <form:password path="oldPass" maxlength="50"/>
            </div>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label"><spring:message key="account.newPassword"/></label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="newPass1"/>
            </div>
            <div class="input-prepend">
                              <span class="add-on">
                                 <i class="icon-asterisk"></i>
                              </span>

                <form:password path="newPass1" maxlength="50" rel="tooltip" data-original-title="<%=passwordMinLength%>"/>
            </div>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label"><spring:message key="account.retype.newPassword"/></label>
        <div class="controls">
            <div class="input-prepend">
                              <span class="add-on">
                                 <i class="icon-asterisk"></i>
                              </span>
                <form:password path="newPass2" maxlength="50" rel="tooltip" data-original-title="${pageContext.servletContext.m}"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary"><spring:message key="action.save"/></button>
        &nbsp;
        <button type="reset" class="btn"><spring:message key="action.cancel"/></button>
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