<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<h1 class="page-header">
    Create tournament
</h1>
<form:form method="post" action="createProcess" commandName="command" cssClass="form-horizontal">
    <div class="control-group ">
        <label class="control-label">Name:</label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="name"/>
            </div>
            <div class="input-prepend">
                            <span class="add-on">
                                <i class="icon-file"></i>
                            </span>

                <form:input path="name" class="input-xlarge"/>
            </div>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label">Max participants:</label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="max"/>
            </div>
            <div class="input-prepend">
                            <span class="add-on">
                                <i class="icon-file"></i>
                            </span>

                <form:input path="max" class="input-xlarge"/>
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