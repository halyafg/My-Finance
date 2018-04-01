<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <tags:title/>
    <link rel="stylesheet" href="<c:url value="${contextPath}/resources/css/template.css"/>">
</head>
<body>

<div id="page">
    <h1>Restore password</h1>
    <form action="${contextPath}/restorePassword/${oldPassword}" method="POST">
        <label for="email"> Input email used in MyFinance:</label>
        <input name="email" id="email" type="email" required placeholder="email@gmail.com">

        <label for="password"> Input new password:</label>
        <input name="password" id="password" type="text" required placeholder="new password">

        <input type="submit" value="Ok">
    </form>

</div>

</body>
</html>
