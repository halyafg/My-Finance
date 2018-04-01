<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <tags:title/>
    <link rel="stylesheet" href="<c:url value="/resources/css/template.css"/>" type="text/css" media="screen">
    <link rel="stylesheet" href="<c:url value="/resources/css/menu.css"/> ">
</head>
<body>
    <div id="page">

        <tags:menu/>

        <h2 class="heading">Set a new period</h2>

        <form action="${contextPath}/period" method="post">
            <fieldset>
                <label for="dateFrom">Period From:</label>
                <input name="dateFrom" id="dateFrom" type="date" value="${dateFrom}" required/>

                <label for="dateTo" >Period To:</label>
                <input name="dateTo" id="dateTo" type="date" value="${dateTo}"  required/>

                <input type="submit" value="Apply!"/>
            </fieldset>
        </form>

        <c:if test="${!empty error}">
            <p class="errors"> ${error}</p>
        </c:if>

    </div>
</body>
</html>
