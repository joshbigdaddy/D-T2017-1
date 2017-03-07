
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<article>
	<form:form modelAttribute="socialidentity" method="POST">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<acme:textbox path="name" code="name" />
		<acme:textbox path="nick" code="nick" />
		<acme:textbox path="link" code="link" />

		<acme:submit name="save" code="save" />
		<acme:cancel url="actor/socialidentities/list.do" code="cancel"/>
	</form:form>
</article>
