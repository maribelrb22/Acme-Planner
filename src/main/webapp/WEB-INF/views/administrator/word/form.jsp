<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<acme:form>
    <acme:form-textbox path="word" code="administrator.word.form.label.word" />
    <acme:form-submit test="${command=='show' || command=='update'}" action="/administrator/word-class/update" code="administrator.word.form.button.update" />
	<acme:form-submit test="${command=='create'}" action="/administrator/word-class/create" code="administrator.word.form.button.create" />
	<acme:form-submit test="${command=='show' || command=='update'}" action="/administrator/word-class/delete" code="administrator.word.form.button.delete" />

    <acme:form-return action="/administrator/word-class/list" code="administrator.word.form.button.return" />
</acme:form>