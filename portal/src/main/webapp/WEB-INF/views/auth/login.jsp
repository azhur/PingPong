<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h1 class="page-header">
    Sign in
</h1>

<form method="post" action="j_spring_security_check" class="form-horizontal">
    <fieldset>
        <div class="control-group ">
            <label class="control-label" for="j_username">Email:</label>

            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on">
                        <i class="icon-envelope"></i>
                    </span>
                    <input id="j_username" name="j_username" type="text" class="input-large"/>
                </div>
            </div>
        </div>
        <div class="control-group ">
            <label class="control-label" for="j_password">Password:</label>

            <div class="controls">
                <div class="input-prepend">
                      <span class="add-on">
                         <i class="icon-asterisk"></i>
                      </span>
                    <input id="j_password" name="j_password" type="password" class="input-large"/>
                </div>
            </div>
        </div>
        <div class="control-group ">
            <label class="control-label" for="_spring_security_remember_me"></label>

            <div class="controls">
                <label class="checkbox">
                    <input type="checkbox" name="_spring_security_remember_me" id="_spring_security_remember_me" value="_spring_security_remember_me"/>
                    Remember me
                </label>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Login</button>
            &nbsp;or&nbsp;
            <a href="account/forgot_password">Forgot password</a>
        </div>
    </fieldset>
</form>