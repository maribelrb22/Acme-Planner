<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!-- Comparo la url para saber si estoy en los gritos recientes o en los que se muestran todos -->
<%
	if(request.getAttribute("javax.servlet.forward.request_uri").toString().equals("/Acme-One/anonymous/shout/list_recent")){
%>
	<acme:form>
    	<acme:form-return action="/anonymous/shout/list" code="anonymous.shout.list.button.all" />
	</acme:form>
<%
	}else{
%>
	<acme:form>
    	<acme:form-return action="/anonymous/shout/list_recent" code="anonymous.shout.list.button.recent" />
	</acme:form>
<% 
	}
%>

<br><br>
<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="60%"/>	
</acme:list>

