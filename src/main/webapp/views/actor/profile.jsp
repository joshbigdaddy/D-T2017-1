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

<h1><spring:message code="profile"/> </h1>
<img src="${actor.picture}" alt="Avatar"/><br>
<b><spring:message code="name"/> </b> ${actor.name}<br>
<b><spring:message code="surnames"/> </b> ${actor.surname}<br>
<b><spring:message code="email"/> </b> ${actor.email}<br>
<b><spring:message code="phone"/> </b> ${actor.phone}<br>

<h2><spring:message code="socialidentities" /> </h2>

<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="actor.socialIdentities" requestURI="${requestURI}" id="row">
    <spring:message code="name" var="nameHeader" />
    <display:column property="name" title="${nameHeader}" sortable="true" />

    <spring:message code="nick" var="nickHeader" />
    <display:column property="nick" title="${nickHeader}" sortable="true" />

    <spring:message code="link" var="linkHeader" />
    <display:column property="link" title="${linkHeader}" sortable="true" />
</display:table>

<jstl:if test="${not empty cancomment}">
    <h1><spring:message code="comment"/> </h1>
    <form:form modelAttribute="comment" action="${commentUri}">
        <acme:textbox path="title" code="title"/>
        <acme:textarea path="text" code="text"/>
        <br>
        <acme:submit name="submit" code="send"/>
    </form:form>
</jstl:if>
<jstl:if test="${not empty comments}">
<h1><spring:message code="comments"/> </h1>
    <jstl:forEach items="${comments}" var="item">
        <h3>${item.title}</h3>
        <div><small>${item.moment} <spring:message code="by"/> <b>${item.author.name}</b></small> ${item.starRating} <spring:message code="stars"/> </div>
        <p>${item.text}</p>
        <hr />
    </jstl:forEach>
</jstl:if>