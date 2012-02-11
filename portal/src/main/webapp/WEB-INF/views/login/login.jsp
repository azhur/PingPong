<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h1 class="page-header">
    Sign in
</h1>
<form:form method="post" action="login" commandName="login" cssClass="form-horizontal">
    <div class="control-group ">
        <label class="control-label">Email:</label>

        <div class="controls">
            <div class="input-prepend">
                    <span class="add-on">
                        <i class="icon-envelope"></i>
                    </span>
                <form:input path="email"/>
            </div>
        </div>
    </div>
    <div class="control-group ">
        <label class="control-label">Password:</label>

        <div class="controls">
            <div class="input-prepend">
                      <span class="add-on">
                         <i class="icon-asterisk"></i>
                      </span>
                <form:input path="pass"/>
            </div>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label">Password1:</label>

        <div class="controls">
            <form:password path="pass"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Login</button>
        &nbsp;or&nbsp;
        <a href="forgot_password">Forgot password</a>
    </div>
</form:form>