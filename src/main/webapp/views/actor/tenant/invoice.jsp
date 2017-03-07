

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="invoice"/> </h1>

<div class="recipe-info">
    <div><b><spring:message code="property"/>: </b> ${invoice.request.property.name}</div>
    <div><b>VAT: </b> ${invoice.vat}</div>
    <div><b><spring:message code="quantity"/> </b> ${invoice.amount}</div>
    <p> <h2><spring:message code="details"/> </h2>
    <div><b><spring:message code="checkinDate" /></b>: ${invoice.request.checkinDate}</div>
    <div><b><spring:message code="checkoutDate" /></b>: ${invoice.request.checkoutDate}</div>
    <div><b><spring:message code="smoker" /></b>:
        <jstl:choose><jstl:when test="${smoker}"><spring:message code="yes"/></jstl:when>
            <jstl:otherwise><spring:message code="no"/></jstl:otherwise></jstl:choose>
    </div>
    <div><b><spring:message code="lessor" /></b>: ${invoice.request.property.lessor.name} ${invoice.request.property.lessor.surname}</div>
    </p>
    <div><b><spring:message code="date"/> </b> ${invoice.moment}</div>
    <div><b><spring:message code="creditcard"/> </b> ${creditcard}</div>

    <p></p>

</div>