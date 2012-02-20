<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1 class="page-header">
    Registration form
    <small>Please complete the form below</small>
</h1>
<form:form method="post" action="registration" commandName="registration" cssClass="form-horizontal">
    <div class="alert alert-info centered_text">
        <p>All fields are required</p>
    </div>
    <div class="control-group ">
        <label class="control-label">Name:</label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="name*"/>
            </div>
            <div class="input-prepend">
                            <span class="add-on">
                                <i class="icon-tag"></i>
                            </span>

                <form:input path="name"/>
            </div>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label">Email:</label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="email"/>
            </div>
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

    <div class="control-group">
        <label class="control-label">Gender:</label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="gender"/>
            </div>
            <div class="radio">
                <form:radiobuttons path="gender" items="${genders}"/>
            </div>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">Birth:</label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="birth"/>
            </div>
            <div class="input-prepend">
                <span class="add-on">
                    <i class="icon-calendar"></i>
                </span>
                <form:input path="birth" readonly="true"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Register</button>
        &nbsp;
        <button type="reset" class="btn">Cancel</button>
    </div>
</form:form>

<script type="text/javascript">
    jQuery(document).ready(function () {
        $("#birth").datepicker({ dateFormat:'dd/mm/yy' });
        //getter
        var dateFormat = $("#birth").datepicker("option", "dateFormat");
        //setter
        $("#birth").datepicker("option", "dateFormat", 'dd/mm/yy');

        $("#birth").datepicker({ maxDate:'+0d' });
        //getter
        var maxDate = $("#birth").datepicker("option", "maxDate");
        //setter
        $("#birth").datepicker("option", "maxDate", '+0d');
    });
</script>