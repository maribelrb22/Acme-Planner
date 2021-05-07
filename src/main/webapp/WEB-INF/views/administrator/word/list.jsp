<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.word.list.label.word" path="word" width="100%"/>
</acme:list>

<acme:form>
    	<acme:form-submit method="get" action="/administrator/threshold/show" code="administrator.word.form.threshold" />
</acme:form>