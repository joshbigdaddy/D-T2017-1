

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="finder"/>  </h1>

<form:form  modelAttribute="finder" method="POST">
    <acme:textbox path="city" code="city"/>
    <acme:textbox path="minPrice" code="minPrice"/>
    <acme:textbox path="maxPrice" code="maxPrice"/>
    <acme:textbox path="keyword" code="keyword"/>
    <acme:submit name="edit" code="edit"/>
</form:form>
<br>
<a  class="button" href="actor/tenant/finder/search.do"><spring:message code="execute" /> <spring:message code="finder"/> </a>
