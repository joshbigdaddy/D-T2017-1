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

<h1>
	<spring:message code="audits" />
</h1>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="audits" requestURI="${requestURI}" id="row">

	<spring:message code="text" var="nameHeader" />
	<display:column property="text" title="${nameHeader}" sortable="true" />

	<spring:message code="moment" var="addressHeader" />
	<display:column property="moment" title="${addressHeader}"
		sortable="true" />

	<spring:message code="attachment" var="descriptionHeader" />
	<display:column property="attachment" title="${descriptionHeader}"
		sortable="true" />
</display:table>
