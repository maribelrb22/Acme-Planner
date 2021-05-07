<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-public"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPublicTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-private"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrivateTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-finished"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-pending"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPendingTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-workload"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${averageNumberOfWorkload}" />
			<acme:message code="administrator.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.min-workload"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${minWorkload}" />
			<acme:message code="administrator.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.max-workload"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${maxWorkload}" />
			<acme:message code="administrator.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-workload"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationOfWorkload}" />
			<acme:message code="administrator.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-period"/>
		</th>
		<td>
		    <fmt:formatNumber type="number" maxFractionDigits="2" value="${averageNumberOfTaskExecutionPeriod}" />
			<acme:message code="administrator.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.min-period"/>
		</th>
		<td>
		    <fmt:formatNumber type="number" maxFractionDigits="2" value="${minTaskExecutionPeriod}" />
			<acme:message code="administrator.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.max-period"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${maxTaskExecutionPeriod}" />
			<acme:message code="administrator.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-period"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationOfTaskExecutionPeriod}" />
			<acme:message code="administrator.dashboard.form.label.hours"/>
		</td>
	</tr>	
</table>