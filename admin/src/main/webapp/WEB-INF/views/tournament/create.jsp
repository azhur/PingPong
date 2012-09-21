<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.pingpong.shared.Constraints" %>

<c:set var="argPartCount">
 <spring:message code="tournament.participants.count.constraint" arguments="<%=Constraints.MIN_PARTICIPANT_COUNT%>"/>
</c:set>
<ul class="breadcrumb">
    <li>
        <a href="<c:url value="/"/>"><spring:message code="action.home"/></a> <span class="divider">/</span>
    </li>
    <li class="active">
        <a href="<c:url value="/tournament/list"/>"><spring:message code="tournament.plural"/></a><span class="divider">/</span>
    </li>
    <li class="active">
        <spring:message code="action.create" arguments=","/>
    </li>
</ul>
<h1 class="page-header">
    <c:set var="tourKey">
         <spring:message code="tournament"/>
    </c:set>
    <spring:message code="action.create" arguments="${tourKey}"/>
</h1>
<form:form method="post" action="createProcess" commandName="command" cssClass="form-horizontal">
    <div class="control-group ">
        <label class="control-label">
           <spring:message code="tournament.name"/>
            <span class="required-indicator">*</span>
        </label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="name"/>
            </div>
            <div class="input-prepend">
                            <span class="add-on">
                                <i class="icon-file"></i>
                            </span>

                <form:input path="name" class="input-xlarge" required="true"/>
            </div>
        </div>
    </div>

    <div class="control-group ">
        <label class="control-label">
            <spring:message code="tournament.max.participants"/>
            <span class="required-indicator">*</span>
        </label>

        <div class="controls">
            <div class="alert-error">
                <form:errors path="max"/>
            </div>
            <div class="input-prepend">
                            <span class="add-on">
                                <i class="icon-file"></i>
                            </span>

                <form:input path="max" class="input-xlarge" rel="tooltip" data-original-title="${argPartCount}" required="true"/>
            </div>
        </div>
    </div>
    <div class="form-actions">
        <button type="submit" class="btn btn-primary">
            <spring:message code="action.create" arguments=","/>
        </button>
        &nbsp;
        <button type="reset" class="btn"><spring:message code="action.cancel"/></button>
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
