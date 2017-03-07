
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="registernewaccount"/> </h1>

<form:form modelAttribute="actor" method="post">
    <div class="form-group-1">
        <h2><spring:message code="useraccountdata"/> </h2>

        <acme:textbox path="userAccount.username" code="userAccount.username"/>

        <br>
        <form:errors cssClass="error" path="email"/>
    </div>
    <h2><spring:message code="personalinfo" /> </h2>
    <div class="form-group-2">
        <acme:textbox path="email" code="email" />
        <acme:textbox path="name" code="name" />
        <acme:textbox path="surname" code="surnames" />
        <br>
    </div>
    <div class="form-group-2">
        <acme:textbox path="phone" code="phone" />
    </div>
    <acme:submit name="submit" code="submit" />
</form:form>
