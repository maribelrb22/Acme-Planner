<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.workplan.form.label.title" path="title" />
	<acme:form-textbox code="anonymous.workplan.form.label.begin" path="begin" />
	<acme:form-textbox code="anonymous.workplan.form.label.end" path="end" />
	<acme:form-textbox code="anonymous.workplan.form.label.workload" path="workload" />
	<acme:form-textbox code="anonymous.workplan.form.label.executionPeriod" path="executionPeriod"/>
	<acme:form-return code="anonymous.workplan.form.button.return" />
</acme:form>

<div class="table-responsive">
	<table id="list" class="table table-striped table-condensed table-hover nowrap w-100">
		<caption>
			<acme:message code="anonymous.workplan.form.label.tasks" />
		</caption>
		<thead>
			<tr>
			    <th scope="col"></th>
				<th scope="col"><acme:message code="anonymous.workplan.form.label.tasks.id" /></th>
				<th scope="col"><acme:message code="anonymous.workplan.form.label.tasks.title" /></th>
				<th scope="col"><acme:message code="anonymous.workplan.form.label.tasks.public" /></th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tasks}" var="task">
				<tr class="table-light">
					<td></td>
					<td>${task.id}</td>
					<td><c:out value="${task.title}"/></td>
					<td><acme:message code="anonymous.workplan.form.label.tasks.public.${task.isPublic}" /></td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var table;
		table = $("#list").dataTable({
			  "lengthMenu": [5, 10, 25, 50],
			  "pageLength": 5,
			  "pagingType": "numbers",
 			   "stateSave": true,
 			  "columnDefs": [ { className: "control", orderable: false, targets: 0 } ],
			  "responsive": { details: { type: "column" } },
			         "dom": "<'row'<'col'f><'col'i>>" +
				            "<'row'<'col'tr>>" +
				            "<'row'<'col'l><'col'p>>",
				   "order": [[ 0, "asc" ]],
	    });
	});	
</script>