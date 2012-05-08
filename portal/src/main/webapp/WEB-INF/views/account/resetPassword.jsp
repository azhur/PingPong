<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h1 class="page-header">
    Reset password form
</h1>

<form:form method="post" action="resetPasswordProcess" commandName="command" cssClass="form-horizontal">
    <form:hidden path="forgotPasswordId" />

    <div class="control-group ">
        <label class="control-label">Email:</label>

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
        <label class="control-label">Password:</label>

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
        <label class="control-label">Retype password:</label>

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
        <button type="submit" class="btn btn-primary">Reset</button>
        &nbsp;
        <button type="reset" class="btn">Cancel</button>
    </div>
</form:form>
