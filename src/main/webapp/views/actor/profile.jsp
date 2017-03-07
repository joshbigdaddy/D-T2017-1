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

<jstl:if test="${not empty cancomment}">
    <form:form modelAttribute="comment">
        <acme:textbox path="title" code="title"/>
        <acme:textarea path="text" code="text"/>
        <acme:submit name="submit" code="send"/>
    </form:form>
</jstl:if>
<jstl:if test="${not empty comments}">
<h1><spring:message code="comments"/> </h1>
    <jstl:forEach items="${comments}" var="item">
        <h3>${item.title}</h3>
        <div><small>${item.moment} <spring:message code="by"/> <b>${item.socialUser.name}</b></small> ${item.starRating} <spring:message code="stars"/> </div>
        <p>${item.text}</p>
        <hr />
    </jstl:forEach>
</jstl:if>