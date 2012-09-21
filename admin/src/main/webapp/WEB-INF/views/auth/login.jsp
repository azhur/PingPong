<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h1 class="page-header">
    <spring:message code="springSecurity.login.header"/>
</h1>

<form method="post" action="j_spring_security_check" class="form-horizontal">
        <div class="control-group ">
            <label class="control-label" for="j_username"><spring:message code="springSecurity.login.username.label"/></label>

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
            <label class="control-label" for="j_password"><spring:message code="springSecurity.login.password.label"/></label>

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
            <label class="control-label" ></label>

            <div class="controls">
                <label class="checkbox">
                    <input type="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me" checked/>
                    <spring:message code="springSecurity.login.remember.me.label"/>
                </label>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary"><spring:message code="springSecurity.login.button"/></button>
            &nbsp;<spring:message code="word.or"/>&nbsp;
            <a href="account/forgot_password"><spring:message code="account.forgot.password"/></a>
        </div>
</form>