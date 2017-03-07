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


<article class="col s7" style="margin-left:2%">
    <form:form action="actor/edit.do" modelAttribute="actor" method="POST">
        <form:hidden path="id"/>
        <form:hidden path="version"/>
        <h2><spring:message code="edit" />  <spring:message code="profile"/> </h2>
        <input type="hidden" value="${role}" name="role"/>
        <div class="form-group-2">
          <acme:submit name="email" code="email" />
            <br>
            <acme:textbox path="name" code="name" />
            <acme:textbox path="surname" code="surnames" />
            <br>
        </div>
        <div class="form-group-2">
            <acme:textbox path="phone" code="phone" />
        </div>

        <input name="save" type="submit" value="<spring:message code="save" />"/>
        <a href="/" class="button cancel_button"><spring:message code="cancel"/></a>
    </form:form>
</article>
