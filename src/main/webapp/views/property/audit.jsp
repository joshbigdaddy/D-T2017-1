

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="writeAudit"/></h1>
<jstl:if test="${canaudit eq true}">


    <form:form id="confirm_final" modelAttribute="form" method="POST">
        <form:hidden path="propertyId"/>
        <acme:textbox path="audit.text" code="text"/>
        <acme:textbox path="audit.attachment" code="attachment"/>
        <acme:checkbox path="audit.final" code="publish"/>
        <input  type="submit" id="wait" name="edit" value="<spring:message code="send"/> " />

        <br>
        <a charset="button" href="${uri}/delete.do"><spring:message code="delete"/> </a>
    </form:form>
</jstl:if>

<jstl:if test="${canaudit eq false}">
<p><spring:message code="cantaudit" /> </p>
    </jstl:if>

<script>
    $("#confirm_final").on("submit",function(ev){
        console.log($("input[name='audit.final']").val());
        if (($("input[name='audit.final']").val() == "true")){
            if (confirm('<spring:message code="areyousure"/>')) {
                return true;
            } else {
                $("input[name='audit.final']").prop("checked", false);
                $("input[name='audit.final']").val(false);
                return false;
            }
        }
    })
</script>