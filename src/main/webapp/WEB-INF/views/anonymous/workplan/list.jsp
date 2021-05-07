<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.workplan.list.label.title" path="title" width="30%"/>
	<acme:list-column code="anonymous.workplan.list.label.begin" path="begin" width="30%"/>
	<acme:list-column code="anonymous.workplan.list.label.end" path="end" width="30%"/>

</acme:list>