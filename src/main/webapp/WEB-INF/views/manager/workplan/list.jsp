<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="Managers.workplan.list.label.title" path="title" width="40%"/>
	<acme:list-column code="Managers.workplan.list.label.isPublic" path="isPublic" width="20%"/>
	<acme:list-column code="Managers.workplan.list.label.workload" path="workload" width="20%"/>
	<acme:list-column code="Managers.workplan.list.label.executionPeriod" path="executionPeriod" width="20%"/>
</acme:list>