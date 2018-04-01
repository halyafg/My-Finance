<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <tags:title/>
    <link rel="stylesheet" href="<c:url value="/resources/css/table.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/template.css"/>" type="text/css" media="screen">

</head>
<body>
<div id="page">
    <h2> Edit my profile</h2>
    <form:form commandName="user" acceptCharset="${contextPath}/userEdit">
        <fieldset>
            <form:hidden path="id"/>

            <form:label path="name">Name:</form:label>
            <form:input path="name" value="${user.name}"/>

            <form:label path="secondName">SecondName:</form:label>
            <form:input path="secondName" value="${user.secondName}"/>

            <form:label path="email">Email:</form:label>
            <form:input path="email" value="${user.email}"/>

            <form:hidden path="password"/>

            <input type="submit" value="Edit!">
        </fieldset>
    </form:form>
</div>

</body>
</html>
