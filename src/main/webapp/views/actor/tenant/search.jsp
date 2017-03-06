

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<h1><spring:message code="finderresult"/> </h1>

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

    <spring:message code="author" var="authorHeader"/>
    <display:column title="${authorHeader}">
        <a href="actor/profile/${row.lessor.id}.do">${row.lessor.userAccount.username}</a>
    </display:column>
</display:table>