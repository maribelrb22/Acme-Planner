<%@page language="java"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<acme:form readonly="${!ItsMine}">
    <acme:form-hidden path="id"/>
    <acme:form-textbox code="Managers.workplan.form.label.title" path="title"/>
    <jstl:if test="${command!='create'}">    
   		<acme:form-textbox readonly="true" code="Managers.workplan.form.label.workload" path="workload"/> 	
   		<acme:form-textbox readonly="true" code="Managers.workplan.form.label.executionPeriod" path="executionPeriod"/> 	   		
    </jstl:if>   
    
    <acme:form-moment code="Managers.workplan.form.label.begin" path="begin"/>
     <jstl:if test="${recommendedInitialDate != null && ItsMine}">	    
	    <p class="text-success"><acme:message code="Managers.workplan.form.label.begin.recommend"/><acme:message code="${recommendedInitialDate}"/></p>
    </jstl:if>
    <acme:form-moment code="Managers.workplan.form.label.end" path="end"/>   
    
    <jstl:if test="${recommendedEndDate != null && ItsMine}">
	   	<p class="text-success"><acme:message code="Managers.workplan.form.label.end.recommend"/><acme:message code="${recommendedEndDate}"/></p>
    </jstl:if>
    
    <jstl:if test="${command=='create'}">    
    	<acme:form-checkbox code="Managers.workplan.form.label.isPublic" path="isPublic"/>
    </jstl:if>

    <jstl:if test="${command=='create'}">  		   		   	   		    		
	    <acme:form-submit code="Managers.workplan.form.button.create" action="/managers/work-plan/create"/>    
    </jstl:if>  
   

   	<jstl:if test="${ItsMine && !isPublic && !canPublish && (command=='show'|| command =='update' || errorsAdd)}">
    	<button disabled type="submit" formmethod="post" onclick="javascript: form.action = getAbsoluteUrl('/managers/work-plan/publish')" class="btn btn-primary">
			<acme:message code="Managers.workplan.form.button.publish"/>
		</button>
   	</jstl:if>
    <acme:form-submit test="${ItsMine && !isPublic && canPublish && (command=='show'|| command =='update' || errorsAdd)}" code="Managers.workplan.form.button.publish" action="/managers/work-plan/publish"/>
    <acme:form-submit test="${isPublic && ItsMine}" code="Managers.workplan.form.button.privatize" action="/managers/work-plan/privatize"/>
    <acme:form-submit test="${ItsMine && (command=='show' || command =='update' || errorsAdd)}" code="Managers.workplan.form.button.delete" action="/managers/work-plan/delete"/>
    <acme:form-submit test="${ItsMine && (command=='show'|| command =='update' || errorsAdd)}" code="Managers.workplan.form.button.update" action="/managers/work-plan/update"/>
    <acme:form-return code="Managers.workplan.form.button.return"/>
</acme:form>
<br><br>

<!-- TASK TABLE -->
<jstl:if test="${command=='show'|| command =='update' || errorsAdd}">    
		   <div class="table-responsive">
		   <table id="list" class="table table-striped table-condensed table-hover nowrap w-100">
		   	  <caption><acme:message code="Managers.workplan.form.label.tasks"/></caption>
			  <thead>
				    <tr>
				      <th scope="col"></th>
				      <th scope="col"><acme:message code="Managers.workplan.form.label.tasks.id"/></th>
				      <th scope="col"><acme:message code="Managers.workplan.form.label.tasks.title"/></th>
				      <th scope="col"><acme:message code="Managers.workplan.form.label.tasks.public"/></th>
				      <th scope="col"></th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach items="${tasks}" var="task">
					  	<tr class="table-light">
					      <td></td>
					      <td>${task.id}</td>
					      <td><c:out value="${task.title}"/></td>
					      <td><acme:message code="Managers.workplan.form.label.tasks.public.${task.isPublic}"/></td>
					      <td>
					      <jstl:if test="${ItsMine}">
					      	<acme:form>
					      		<input type="hidden" name="taskId" value="${task.id}">
					      		<input type="hidden" name="workplanId" value="${id}">
					      		<acme:form-submit code="Managers.workplan.form.button.removeTask" action="/managers/work-plan/remove_task"/>   
					      	</acme:form>
					      </jstl:if>
					      </td>
					    </tr>
					</c:forEach>
				    </tbody>
			   </table>
		    </div>
    </jstl:if>

<!-- ADD TASK  -->
<jstl:if test="${(ItsMine && (command=='show'|| command =='update')) || errorsAdd}">    
	<center>
	<acme:form>
		<acme:form-select code="Managers.workplan.form.select.addTask" path="taskSelected">
			<c:forEach items="${tasksEneabled}" var="task">	
				<option value="${task.id}">
					<c:out value="${task.title} - ${task.description}"/>
				</option>
			</c:forEach>
		</acme:form-select>
		<acme:form-submit code="Managers.workplan.form.button.addTask" action="/managers/work-plan/add_task"/>
	</acme:form>
	</center>
</jstl:if>

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

