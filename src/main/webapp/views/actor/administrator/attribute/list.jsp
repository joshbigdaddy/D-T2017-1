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




<h1><spring:message code="attribute"/>s </h1>

<a class="button" href="actor/administrator/attribute/new.do"><spring:message code="new"/> <spring:message code="attribute"/> </a>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="attributes" requestURI="${requestURI}" id="row">
    <spring:message code="name" var="nameHeader"/>
    <display:column property="name" title="${nameHeader}" sortable="true"/>

    <spring:message code="delete" var="deleteHeader"/>
    <display:column title="${deleteHeader}">
        <a href="actor/administrator/attribute/delete/${row.id}.do"><spring:message code="delete"/> </a>
    </display:column>
</display:table>

