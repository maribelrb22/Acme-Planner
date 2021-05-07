<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
    <acme:form-double path="thresholdNumber" code="administrator.threshold.form.label.threshold" />

    <jstl:if test="${readonly}">
	    <acme:form-submit method="get" action="/administrator/threshold/update-show" code="administrator.threshold.form.button.update.show" />
	    <acme:form-return action="/administrator/word/list" code="administrator.threshold.form.button.return" />
    </jstl:if>
    <jstl:if test="${!readonly}">
		<acme:form-submit action="/administrator/threshold/update" code="administrator.threshold.form.button.update" />
		<acme:form-submit action="/administrator/threshold/reset" code="administrator.threshold.form.button.reset" />
		<acme:form-return action="/administrator/threshold/show" code="administrator.threshold.form.button.return" />
	</jstl:if>
</acme:form>