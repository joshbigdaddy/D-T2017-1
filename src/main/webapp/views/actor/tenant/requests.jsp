

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="requests"/> </h1>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="requests" requestURI="${requestURI}" id="row">
    <spring:message code="tenant" var="tenantHeader"/>
    <display:column property="tenant.name" title="${tenantHeader}" sortable="true"/>

    <spring:message code="checkinDate" var="checkinDateHeader"/>
    <display:column property="checkinDate" title="${checkinDateHeader}" sortable="true"
                    format="{0,date,dd/MM/yyyy HH:mm}"/>

    <spring:message code="checkoutDate" var="checkinDateHeader"/>
    <display:column property="checkinDate" title="${checkinDateHeader}" sortable="true"
                    format="{0,date,dd/MM/yyyy HH:mm}"/>

    <spring:message code="smoker" var="smokerHeader"/>
    <display:column title="${smokerHeader}" sortable="true">
        <jstl:choose><jstl:when test="${row.smoker}"><spring:message code="yes"/></jstl:when><jstl:otherwise><spring:message code="no"/></jstl:otherwise></jstl:choose>
    </display:column>

    <spring:message code="state" var="stateHeader" />
    <display:column title="${stateHeader}" sortable="true">
        ${row.state}
    </display:column>

    <spring:message code="invoice" var="invoiceHeader" />
    <display:column title="${invoiceHeader}" sortable="true">
        <jstl:if test="${row.state=='ACCEPTED'}">
            <a class="button" href="actor/tenant/invoice/${row.id}.do">${invoiceHeader}</a>
        </jstl:if>
    </display:column>
</display:table>
