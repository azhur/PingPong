<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h1 class="page-header">
    Change profile form
</h1>
<form:form method="post" action="changeProfileProcess" commandName="command" cssClass="form-horizontal">
    <div class="control-group ">
        <label class="control-label">Email:</label>

        <div class="controls">
            <div class="input-prepend">
                    <span class="add-on">
                        <i class="icon-envelope"></i>
                    </span>

                <form:input path="email" disabled="true"/>
            </div>
        </div>
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

    <div class="control-group">
        <label class="control-label">Gender:</label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="gender"/>
            </div>
            <div class="radio">
                <form:radiobuttons path="gender" items="${genderItems}"/>
            </div>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">Birth:</label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="birth"/>
            </div>
            <div class="input-prepend date">
                <span class="add-on">
                    <i class="icon-calendar"></i>
                </span>
                <form:input path="birth" placeholder="example: 20/12/2000"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Save</button>
        &nbsp;
        <button type="reset" class="btn">Cancel</button>
    </div>
</form:form>

<script type="text/javascript">
    jQuery(document).ready(function () {
        $("#birth").datepicker({
            format: 'dd/mm/yyyy'
        });
    });
</script>