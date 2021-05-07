<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
    <acme:form-textbox path="author" code="anonymous.shout.form.label.author" />
    <acme:form-textarea path="text" code="anonymous.shout.form.label.text" />
    <acme:form-textbox path="info" code="anonymous.shout.form.label.info" />

    <acme:form-submit action="/anonymous/shout/create" code="anonymous.shout.form.button.create" />
    <acme:form-return code="anonymous.shout.form.button.return" />
</acme:form>
