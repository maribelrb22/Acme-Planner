<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="Managers.task.list.label.title" path="title" width="40%"/>
	<acme:list-column code="Managers.task.list.label.executionPeriod" path="executionPeriod" width="30%"/>
	<acme:list-column code="Managers.task.list.label.workload" path="workload" width="30%"/>
	<acme:list-column code="Managers.task.list.label.isPublic" path="isPublic" width="10%"/>
</acme:list>