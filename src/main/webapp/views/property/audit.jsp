

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="writeAudit"/></h1>
    <form:form  modelAttribute="form" method="POST">
        <form:hidden path="propertyId"/>
        <acme:textbox path="audit.text" code="text"/>
        <acme:textbox path="audit.moment" code="moment"/>
        <acme:textbox path="audit.attachment" code="attachment"/>
        <acme:submit name="edit" code="edit"/>
    </form:form>
