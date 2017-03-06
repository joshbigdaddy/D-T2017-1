
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<article>
	<form:form modelAttribute="creditcard" method="POST">
		<acme:textbox path="creditcard.brandName" code="brandName" />
		<acme:textbox path="creditcard.holderName" code="holderName" />
		<acme:textbox path="creditcard.number" code="number" />
		<acme:textbox path="creditcard.expirationMonth" code="expirationMonth" />
		<acme:textbox path="creditcard.expirationYear" code="expirationYear" />
		<acme:textbox path="creditcard.cvvCode" code="cvvCode" />
		<acme:textbox path="creditcard.fee" code="feetopay" />
		
		<input name="save" type="submit"
			value="<spring:message code="save" />" />
		<a href="/" class="button cancel_button"><spring:message
				code="cancel" /></a>
	</form:form>
</article>
