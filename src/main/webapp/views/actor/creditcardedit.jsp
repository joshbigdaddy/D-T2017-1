
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<article>
	<form:form modelAttribute="creditcard" method="POST">
		<form:hidden path="id"/>
        <form:hidden path="version"/>
		<acme:textbox path="brandName" code="brandName" />
		<acme:textbox path="holderName" code="holderName" />
		<acme:textbox path="number" code="number" />
		<acme:textbox path="expirationMonth" code="expirationMonth" />
		<acme:textbox path="expirationYear" code="expirationYear" />
		<acme:textbox path="cvvCode" code="cvvCode" />
		<acme:textbox path="fee" code="feetopay" />
		<acme:submit name="save" code="save"/>
		<a href="" class="button cancel_button"><spring:message
				code="cancel" /></a>
	</form:form>
</article>
