<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
    <acme:form-textbox code="anonymous.workplan.form.label.title" path="title"/>
    <acme:form-textbox code="anonymous.workplan.form.label.begin" path="begin"/>
    <acme:form-textbox code="anonymous.workplan.form.label.end" path="end"/>
    <acme:form-textbox code="anonymous.workplan.form.label.workload" path="workload"/>


    <acme:form-return code="anonymous.workplan.form.button.return"/>
</acme:form>