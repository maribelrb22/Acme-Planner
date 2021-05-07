<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
    
    <acme:form-textbox path="title" code="authenticated.task.form.label.title" readonly="true"/>
    <acme:form-moment path="begin" code="authenticated.task.form.label.begin" readonly="true"/>
    <acme:form-moment path="end" code="authenticated.task.form.label.end" readonly="true"/>
    <acme:form-double path="workload" code="authenticated.task.form.label.workload" readonly="true"/>
    <acme:form-url path="link" code="authenticated.task.form.label.link" readonly="true"/>
    <acme:form-textarea path="description" code="authenticated.task.form.label.description" readonly="true"/>
    <acme:form-double  code="authenticated.task.form.label.executionPeriod" path="executionPeriod" readonly="true"/>
    <acme:form-return code="authenticated.task.form.button.return" />
    
</acme:form>