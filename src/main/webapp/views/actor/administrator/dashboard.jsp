<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<p>
	<spring:message code="administrator.avgAcceptedRequestLessor" />
	${avgAcceptedRequestLessor}
</p>
<br>
<p>
	<spring:message code="administrator.avgDeniedRequestLessor" />
	${avgDeniedRequestLessor}
</p>
<br>
<p>
	<spring:message code="administrator.avgDeniedRequestTenant" />
	${avgDeniedRequestTenant}
</p>
<br>
<p>
	<spring:message code="administrator.avgAcceptedRequestTenant" />
	${avgAcceptedRequestTenant}
</p>
<br>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="lessorApprovedMoreRequest" requestURI="${requestURI}"
	id="lessorApprovedMoreRequest">
	<spring:message code="administrator.lessorApprovedMoreRequest"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="lessorDeniedMoreRequest" requestURI="${requestURI}"
	id="lessorDeniedMoreRequest">
	<spring:message code="administrator.lessorDeniedMoreRequest" var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="lessorPendingMoreRequest" requestURI="${requestURI}"
	id="lessorPendingMoreRequest">
	<spring:message code="administrator.lessorPendingMoreRequest"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>



<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="tenantPendingMoreRequest" requestURI="${requestURI}"
	id="tenantPendingMoreRequest">
	<spring:message code="administrator.tenantPendingMoreRequest"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="tenantApprovedMoreRequest" requestURI="${requestURI}"
	id="tenantApprovedMoreRequest">
	<spring:message code="administrator.tenantApprovedMoreRequest"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="tenantDeniedMoreRequest" requestURI="${requestURI}"
	id="tenantDeniedMoreRequest">
	<spring:message code="administrator.tenantDeniedMoreRequest" var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="lessorRatioMax" requestURI="${requestURI}"
	id="lessorRatioMax">
	<spring:message code="administrator.lessorRatioMax" var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="lessorRatioMin" requestURI="${requestURI}"
	id="lessorRatioMin">
	<spring:message code="administrator.lessorRatioMin" var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="tenantRatioMax" requestURI="${requestURI}"
	id="tenantRatioMax">
	<spring:message code="administrator.tenantRatioMax" var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="tenantRatioMin" requestURI="${requestURI}"
	id="tenantRatioMin">
	<spring:message code="administrator.tenantRatioMin" var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>


<p>
	<spring:message code="administrator.minResultsPerFinder" />
	${minResultsPerFinder}
</p>
<br>
<p>
	<spring:message code="administrator.maxResultsPerFinder" />
	${maxResultsPerFinder}
</p>
<br>
<p>
	<spring:message code="administrator.avgResultsPerFinder" />
	${avgResultsPerFinder}
</p>
<br>

<p>
	<spring:message code="administrator.minAuditPerProperty" />
	${minAuditPerProperty}
</p>
<br>
<p>
	<spring:message code="administrator.maxAuditPerProperty" />
	${maxAuditPerProperty}
</p>
<br>
<p>
	<spring:message code="administrator.avgAuditPerProperty" />
	${avgAuditPerProperty}
</p>
<br>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="attributeDescribePropertyDesc" requestURI="${requestURI}"
	id="attributeDescribePropertyDesc">
	<spring:message code="administrator.attributeDescribePropertyDesc"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>


<p>
	<spring:message code="administrator.minSocialIdentityPerActor" />
	${minSocialIdentityPerActor}
</p>
<br>
<p>
	<spring:message code="administrator.maxSocialIdentityPerActor" />
	${maxSocialIdentityPerActor}
</p>
<br>
<p>
	<spring:message code="administrator.avgSocialIdentityPerActor" />
	${avgSocialIdentityPerActor}
</p>
<br>

<p>
	<spring:message code="administrator.maxInvoicePerTenant" />
	${maxInvoicePerTenant}
</p>
<br>
<p>
	<spring:message code="administrator.minInvoicePerTenant" />
	${minInvoicePerTenant}
</p>
<br>
<p>
	<spring:message code="administrator.avgInvoicePerTenant" />
	${avgInvoicePerTenant}
</p>
<br>

<p>
	<spring:message code="administrator.invoicesAmount" />
	${invoicesAmount}
</p>
<br>
<p>
	<spring:message code="administrator.avgRequestPerPropertyWithAudits" />
	${avgRequestPerPropertyWithAudits}
</p>
<br>
<p>
	<spring:message code="administrator.avgRequestPerPropertyWithoutAudits" />
	${avgRequestPerPropertyWithoutAudits}
</p>




<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="lessor" requestURI="${requestURI}" id="row">

	<spring:message code="administrator.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="administrator.display" var="lessors" />
	<display:column title="${lessors}">
		<a href="actor/administrator/dashboard/display/${row.id}.do"> <spring:message
				code="administrator.display" />
		</a>
	</display:column>




</display:table>





<br>
