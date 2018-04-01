<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <tags:title/>
    <link rel="stylesheet" href="${contextPath}/resources/css/template.css"/>">
</head>
<body>

<div id="page">
    <h1>My Finance</h1>
    <p class="principal"><sec:authentication property="name"/></p>

    <sec:authorize access="isAnonymous()"><p><a href="${contextPath}/registration">Registration</a></p></sec:authorize>
    <sec:authorize access="isAnonymous()"><p><a href="${contextPath}/loginPage">Sign in</a></p></sec:authorize>
    <sec:authorize access="isAuthenticated()"><P><a href="${contextPath}/logout">Sign Out</a></p></sec:authorize>
    <sec:authorize access="isAuthenticated()"><P><a href="${contextPath}/cabinet">Cabinet</a></p></sec:authorize>

</div> <%--page--%>

</body>
</html>
