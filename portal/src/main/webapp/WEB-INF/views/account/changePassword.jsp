<%@ page import="com.pingpong.portal.Constraints" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h1 class="page-header">
    Change password form
    <small>Please complete the form below</small>
</h1>
<%
    final String passwordMinLength ="Should be at least " +  Constraints.MIN_PASSWORD_LENGTH + " characters length";
%>
<form:form method="post" action="changePasswordProcess" commandName="command" cssClass="form-horizontal">

    <div class="control-group ">
        <label class="control-label">Old password:</label>

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
        <label class="control-label">New Password:</label>

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
        <label class="control-label">Retype new password:</label>
        <div class="controls">
            <div class="input-prepend">
                              <span class="add-on">
                                 <i class="icon-asterisk"></i>
                              </span>
                <form:password path="newPass2" maxlength="50" rel="tooltip" data-original-title="<%=passwordMinLength%>"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Save</button>
        &nbsp;
        <button type="reset" class="btn">Cancel</button>
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