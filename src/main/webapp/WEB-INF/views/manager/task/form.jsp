<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
    <acme:form-hidden path="id"/>
    <acme:form-textbox path="title" code="Managers.task.form.label.title"/>
    <acme:form-moment path="begin" code="Managers.task.form.label.begin"/>
    <acme:form-moment path="end" code="Managers.task.form.label.end"/>
    <acme:form-double path="workload" code="Managers.task.form.label.workload"/>
    <acme:form-url path="link" code="Managers.task.form.label.link"/>
    <acme:form-textarea path="description" code="Managers.task.form.label.description"/>
    <jstl:if test="${command!='create'}">
    <acme:form-hidden path="isPublic"/>
    </jstl:if>
    <jstl:if test="${command!='create'}">
    <acme:form-double  code="Managers.task.form.label.executionPeriod" path="executionPeriod" readonly="true"/>
    </jstl:if>
    <jstl:if test="${command=='create'}">
    <acme:form-checkbox code="Managers.task.form.label.isPublic" path="isPublic"/>
    </jstl:if>
    <acme:form-submit test="${command=='create'}" code="Managers.task.form.button.create" 
    action="/managers/task/create"/>
    <acme:form-submit test= "${command=='show'}" code="Managers.task.form.button.update" action="/managers/task/update"/>
    <acme:form-submit test= "${command=='show'}" code="Managers.task.form.button.delete" action="/managers/task/delete"/>
    <acme:form-submit test= "${command=='show' && isPublic=='false'}" code="Managers.task.form.button.publish" action="/managers/task/publish"/>
    <acme:form-submit test= "${command=='show' && isPublic=='true'}" code="Managers.task.form.button.unpublish" action="/managers/task/publish"/>
    <acme:form-submit test="${command == 'update'}" code="Managers.task.form.button.update" action="/managers/task/update"/>
    <acme:form-submit test="${command == 'update'}" code="Managers.task.form.button.delete" action="/managers/task/delete"/>
    <acme:form-submit test="${command == 'update' && isPublic=='false'}" code="Managers.task.form.button.publish" action="/managers/task/publish"/>
    <acme:form-submit test= "${command=='update' && isPublic=='true'}" code="Managers.task.form.button.unpublish" action="/managers/task/publish"/>
    <acme:form-submit test="${command=='publish'}" code="Managers.task.form.button.update" action="/managers/task/update"/>
    <acme:form-submit test="${command=='publish'}" code="Managers.task.form.button.delete" action="/managers/task/delete"/>
    <acme:form-submit test= "${command=='publish' && isPublic=='false'}" code="Managers.task.form.button.publish" action="/managers/task/publish"/>
    <acme:form-submit test= "${command=='publish' && isPublic=='true'}" code="Managers.task.form.button.unpublish" action="/managers/task/publish"/>		
    <acme:form-return code="Managers.task.form.button.return" />
    
</acme:form>
