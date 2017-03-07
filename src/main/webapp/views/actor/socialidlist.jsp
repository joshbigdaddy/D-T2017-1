<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h1>
	<spring:message code="socialidlist" />
</h1>

<a class="button" href="actor/socialidentities/new.do"><spring:message
		code="new" /> <spring:message code="socialid" /> </a>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="socialidentities" requestURI="${requestURI}" id="row">
	<spring:message code="name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader}" sortable="true" />

	<spring:message code="link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}" sortable="true" />


	<spring:message code="edit" var="editHeader" />
	<display:column title="${editHeader}">
		<a href="actor/socialidentities/edit.do?socialidentityid=${row.id}"><spring:message
				code="edit" /> </a>
	</display:column>

	<spring:message code="delete" var="deleteHeader" />
	<display:column title="${deleteHeader}">
		<a href="actor/socialidentities/delete.do?socialidentityid=${row.id}"><spring:message
				code="delete" /> </a>
	</display:column>
</display:table>

