<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.Managers.form.label.company" path="company"/>
	<acme:form-textbox code="authenticated.Managers.form.label.sector" path="sector"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.Managers.form.button.create" action="/authenticated/managers/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.Managers.form.button.update" action="/authenticated/managers/update"/>
	<acme:form-return code="authenticated.Managers.form.button.return"/>
</acme:form>
