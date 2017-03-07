<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h1>${property.name}</h1>

<p>${property.description}</p>
<div class="recipe-info">
	<div>
		<b><spring:message code="address" /> :</b> ${property.address}
	</div>
	<div>
		<b><spring:message code="rate" />:</b> ${property.rate}
	</div>
	<div>
		<b><spring:message code="lessor" />:</b> ${property.lessor.name}
	</div>

	<jstl:forEach items="${property.attributeValues}" var="item">
		<div>
			<b><spring:message code="${item.attribute.name}" />:</b>
			${item.value}
		</div>
	</jstl:forEach>
	<security:authorize access="isAuthenticated()">
		<jstl:if test="${property.audits.size()!=0}">
			<div>
				<a href="property/audits/${property.id}.do"><spring:message
						code="audits" /></a>
			</div>
		</jstl:if>
	</security:authorize>
	<security:authorize access="hasAnyRole('AUDITOR')">
	<br/>
	<br/>
			<div>
				<a href="property/${property.id}/audit.do"><spring:message
						code="writeAudit" /></a>
			</div>
	</security:authorize>

</div>

<security:authorize access="hasAnyRole('TENANT')">
	<jstl:if test="${not empty user.creditCard}">
		<form:form modelAttribute="request">
			<acme:textbox path="checkinDate" code="checkinDate" />
			<acme:textbox path="checkoutDate" code="checkoutDate" />
			<acme:checkbox path="smoker" code="smoker" />

			<acme:submit name="submit" code="send" />
		</form:form>
	</jstl:if>
	<jstl:if test="${empty user.creditCard}">
		<p>
			<spring:message code="registercreditmakerequests" />
			<br> <a href="http://localhost:8080/actor/creditcard/edit.do">http://localhost:8080/actor/creditcard/edit.do</a>
		</p>
	</jstl:if>
</security:authorize>
