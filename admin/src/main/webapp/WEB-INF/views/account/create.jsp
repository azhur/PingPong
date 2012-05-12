<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<h1 class="page-header">
    Create administrator
</h1>
<form:form method="post" action="createProcess" commandName="command" cssClass="form-horizontal">
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

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Create</button>
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
        $("[data-spy=scroll]").scrollspy();
    })
</script>
