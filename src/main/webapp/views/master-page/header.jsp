<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

	<header>
		<h1>
			<a href="${pageContext.request.contextPath}/">Acme BnB</a>
		</h1>
        <article class="user-section">
            <ul>
                <li><a href="#">User</a>
                <ul>
                    <li><a href="/actor/edit.do"><spring:message code="edit" /><spring:message code="profile"/> </a> </li>
                </ul></li>
                <li><a href="property/list.do"><spring:message code="properties"/> </a> </li>
                <security:authorize access="hasAnyRole('LESSOR')">
                <li><a href="#"><spring:message code="lessor"/></a>
                <ul>
                    <li><a href="/actor/lessor/property/list.do"><spring:message code="listofproperties"/> </a> </li>
                </ul></li>
                </security:authorize>
            </ul>
        </article>
        <security:authorize access="!isAuthenticated()">
		<article class="user-section">
		<ul>
				<li><a href="security/login.do"><spring:message code="master.page.login"/></a></li>
                <li><a href="#"><spring:message code="register"/> </a>
                    <ul>
                    <li><a href="actor/register/lessor.do"><spring:message code="register"/> <spring:message code="aslessor"/> </a></li>
                    <li><a href="actor/register/lessor.do"><spring:message code="register"/> <spring:message code="astenant"/> </a></li>
                    </ul>
                </li>
		</ul>
		</article>
        </security:authorize>
	</header>
