<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="propertieslist"/> </h1>

<a class="button" href="actor/lessor/property/new.do"><spring:message code="new"/> <spring:message code="property"/> </a>
<display:table pagesize="5" class="displaytag" keepStatus="true" name="properties" requestURI="${requestURI}" id="row">
    <spring:message code="name" var="nameHeader"/>
    <display:column property="name" title="${nameHeader}" sortable="true"/>

    <spring:message code="name" var="nameHeader"/>
    <display:column property="name" title="${nameHeader}" sortable="true"/>

    <spring:message code="address" var="addressHeader"/>
    <display:column property="address" title="${addressHeader}" sortable="true"/>

    <spring:message code="description" var="descriptionHeader"/>
    <display:column property="description" title="${descriptionHeader}" sortable="true"/>

    <spring:message code="rate" var="rateHeader"/>
    <display:column property="rate" title="${rateHeader}" sortable="true"/>

    <spring:message code="edit" var="editHeader"/>
    <display:column title="${editHeader}">
        <a href="actor/lessor/property/edit/${row.id}.do"><spring:message code="edit"/> </a>
    </display:column>

    <spring:message code="delete" var="deleteHeader"/>
    <display:column title="${deleteHeader}">
        <a href="actor/lessor/property/delete/${row.id}.do"><spring:message code="delete"/> </a>
    </display:column>
</display:table>

<h1><spring:message code="requests"/> </h1>

<h2><spring:message code="feetopay" /> ${fee} </h2>

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

    <spring:message code="actions" var="actionsHeader"/>
    <display:column title="${actionsHeader}" sortable="true">
        <jstl:if test="${row.state=='PENDING' and creditCard}">
        <select title="<spring:message code="requests"/> " onchange="location = this.value;">
            <option value="">
                ---------------------
            </option>
            <option value="actor/lessor/property/request/accept/${row.id}.do">
                    <spring:message code="accept" />
            </option>
            <option value="actor/lessor/property/request/reject/${row.id}.do">
                <spring:message code="reject" />
            </option>
        </select>
        </jstl:if>
    </display:column>

</display:table>


<small><spring:message code="youneedcreditcard" /> </small>