<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="alert alert-info">
    <strong>Enter please your username below and a link for password reset will be sent to you via email.</strong>
    <br> (Contact your administrator in case if you don't remember account data.)
</div>

<form:form method="post" action="forgotPasswordProcess" commandName="command" cssClass="form-horizontal">
    <div class="control-group ">
        <label class="control-label">Email:</label>

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
        <button type="submit" class="btn btn-primary">Send</button>
        &nbsp;
        <button type="reset" class="btn">Cancel</button>
    </div>
</form:form>
