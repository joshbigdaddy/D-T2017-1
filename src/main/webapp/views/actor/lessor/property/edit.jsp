

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="edit"/> <spring:message code="property" /> </h1>
    <form:form  modelAttribute="form" method="POST">
        <acme:textbox path="property.name" code="name"/>
        <form:hidden path="property.id"/>
        <acme:textbox path="property.description" code="description"/>
        <acme:textbox path="property.address" code="address"/>
        <acme:textbox path="property.rate" code="rate"/>
        <jstl:forEach items="${form.attributesValue}" var="attribute" varStatus="i">
            <form:label path="attributesValue[${i.index}].value">
                ${form.attributesValue[i.index].attribute.name}
            </form:label>
            <form:hidden path="attributesValue[${i.index}].id" />
            <acme:textbox path="attributesValue[${i.index}].value" code="blank"/>
            <form:hidden path="attributesValue[${i.index}].attribute" />
        </jstl:forEach>
        <acme:submit name="edit" code="edit"/>
    </form:form>
