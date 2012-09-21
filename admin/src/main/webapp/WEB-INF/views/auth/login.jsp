<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1 class="page-header">
    <fmt:message key="springSecurity.login.header"/>
</h1>

<form method="post" action="j_spring_security_check" class="form-horizontal">
        <div class="control-group ">
            <label class="control-label" for="j_username"><fmt:message key="springSecurity.login.username.label"/></label>

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
            <label class="control-label" for="j_password"><fmt:message key="springSecurity.login.password.label"/></label>

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
                    <fmt:message key="springSecurity.login.remember.me.label"/>
                </label>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary"><fmt:message key="springSecurity.login.button"/></button>
            &nbsp;<fmt:message key="word.or"/>&nbsp;
            <a href="account/forgot_password"><fmt:message key="account.forgot.password"/></a>
        </div>
</form>