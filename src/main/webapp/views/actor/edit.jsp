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
<article class="col s7" style="margin-left:2%">
    <form:form action="actor/edit.do" modelAttribute="actor" method="POST">
        <form:hidden path="id"/>
        <form:hidden path="version"/>
        <h2><spring:message code="edit" />  <spring:message code="profile"/> </h2>
        <input type="hidden" value="${role}" name="role"/>
        <div class="form-group-2">
            <form:label path="email">
                <spring:message code="email"/>:
            </form:label>
            <form:input path="email" type="email" required="required"/>
            <form:errors cssClass="error" path="email"/>
            <br>
            <form:label path="name">
                <spring:message code="name"/>:
            </form:label>
            <form:input path="name" required="required"/>
            <form:errors cssClass="error" path="name"/>
            <br>
            <form:label path="surname">
                <spring:message code="surnames"/>:
            </form:label>
            <form:input path="surname" required="required"/>
            <form:errors cssClass="error" path="surname"/>
            <br>
        </div>
        <div class="form-group-2">
            <form:label path="phone">
                <spring:message code="phone"/>:
            </form:label>
            <form:input path="phone" pattern="((\+[0-9]{1,3})?\s*(\([0-9]{3}\))?\s*([a-zA-Z0-9\- ]{4,}))$"/>
            <form:errors cssClass="error" path="phone"/>
        </div>

        <input name="save" type="submit" value="<spring:message code="save" />"/>
        <a href="/" class="button cancel_button"><spring:message code="cancel"/></a>
    </form:form>
</article>
