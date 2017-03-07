<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="lessorApprovedMoreRequest" requestURI="${requestURI}"
	id="lessorApprovedMoreRequest">
	<spring:message code="administrator.lessorApprovedMoreRequest"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="propertiesOrderedByAudits" requestURI="${requestURI}"
	id="propertiesOrderedByAudits">
	<spring:message code="administrator.propertiesOrderedByAudits" var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="propertiesOrderedByDeniedRequest" requestURI="${requestURI}"
	id="propertiesOrderedByDeniedRequest">
	<spring:message code="administrator.propertiesOrderedByDeniedRequest"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>



<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="propertiesOrderedByAcceptedRequest" requestURI="${requestURI}"
	id="propertiesOrderedByAcceptedRequest">
	<spring:message code="administrator.propertiesOrderedByAcceptedRequest"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>






<br>
