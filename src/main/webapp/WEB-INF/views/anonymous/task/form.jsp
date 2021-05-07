<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.task.form.label.title" path="title" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.begin" path="begin" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.end" path="end" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.description" path="description" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.link" path="link" readonly="true"/>
	<acme:form-textbox code="anonymous.task.form.label.workload" path="workload" readonly="true"/>
	<acme:form-double  code="anonymous.task.form.label.executionPeriod" path="executionPeriod" readonly="true"/>
	
	<acme:form-return code="anonymous.task.form.button.return"/>
</acme:form>
