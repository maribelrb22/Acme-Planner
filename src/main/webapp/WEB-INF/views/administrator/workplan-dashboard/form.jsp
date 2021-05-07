<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<h2>
	<acme:message code="administrator.workplan.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.workplan.dashboard.form.title.general-indicators"/>
	</caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.total-number-public-workplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPublicWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.total-number-private-workplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrivateWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.total-number-finished-workplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfFinishedWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.total-number-non-finished-workplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfNonFinishedWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.average-periods"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${averageNumberOfPeriods}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.deviation-periods"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationOfExecutionPeriods}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.minimum-periods"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${minimumNumberOfPeriods}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.maximum-periods"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${maximumNumberOfPeriods}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.average-workloads"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${averageNumberOfWorkloads}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.deviation-workplan"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationOfWorkloads}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.minimum-number-workloads"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${minimumNumberOfWorkloads}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.workplan.dashboard.form.label.maximum-number-workloads"/>
		</th>
		<td>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${maximumNumberOfWorkloads}"/>
			<acme:message code="administrator.workplan.dashboard.form.label.hours"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message
		code="administrator.workplan.dashboard.form.title.application-statuses" />
</h2>
<div>
	<canvas id="canvas"></canvas>
</div>
<script type="text/javascript">
	$(document).ready(
			function() {
				var data = {
					labels : [
							"PRIVATE", "PUBLIC"
					],
					datasets : [
						{
							data : [
									<jstl:out value="${totalNumberOfPrivateWorkplans}" />,
									<jstl:out value="${totalNumberOfPublicWorkplans}" />
							],
							backgroundColor : [
									'rgba(45, 144, 221, 1)', 'rgba(205, 205, 3, 1)'
							]
						}
					]
				};
				
				var total = <jstl:out value="${totalNumberOfPublicWorkplans} + ${totalNumberOfPrivateWorkplans}" />;
				var options = {
					legend : {
						display : true
					},
					title : {
						display : true,
						text : "WorkPlans: " + total
					}
				};
				var canvas, context;
				canvas = document.getElementById("canvas");
				context = canvas.getContext("2d");
				new Chart(context, {
					type : "pie",
					data : data,
					options : options
				});
			});
</script>
