<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<spring:url  value="/resources/css/template.css" var="cssPath" htmlEscape="true"/>
<html>
<head>
    <tags:title/>
    <link rel="stylesheet" href="${cssPath}" type="text/css" media="screen">
</head>
<body>

<div id="page">
    <h1>My Finance</h1>

    <form action="${contextPath}/loginProcessing" method="post">
        <fieldset>

            <label for="email">Login:</label>
            <input name="username" id="email" type="text" placeholder="Email" required/>

            <label for="password">Password:</label>
            <input name="password" id="password" type="password" placeholder="password" required/>

            <sec:authorize access="isAnonymous()"><a class="forgotPassword" href="${contextPath}/passwordForgot">I forgot my password ....</a></sec:authorize>

            <input type="submit" value="Sign in"/>

        </fieldset>
        </form>

    <sec:authorize access="isAnonymous()"><p><a href="${contextPath}/registration">Create my account! </a></p></sec:authorize>

</div> <%--  id="page"  --%>
</body>
</html>
