<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <tags:title/>
    <link rel="stylesheet" href="<c:url value="/resources/css/template.css"/>" type="text/css" media="screen">
    <link rel="stylesheet" href="<c:url value="/resources/css/menu.css"/> ">
</head>
<body>

<div id="page">
    <tags:menu/>
    <h2>Edit Currency</h2>

    <form action="${contextPath}/currency/add" method="post">
        <fieldset>

            <input name="name" id="name" type="text" placeholder="New Currency">
            <input name="rate" id="rate" type="text" placeholder="Exchange rate">

            <button type="submit">Add!</button>

        </fieldset>
    </form>

    <br><br>

    <form action="${contextPath}/currency/delete" method="post">
        <fieldset>
            <select name="id">
                <option class="option" value="-1">Currency to delete:</option>
                <c:forEach items="${currencies}" var="c">
                    <option class="option" value="${c.id}">${c.name}</option>
                </c:forEach>
            </select>

            <button type="submit">Delete!</button>
        </fieldset>
    </form>

    <br><br>

    <form action="${contextPath}/currency/changeRate" method="post">
        <fieldset>
            <select name="currencyId" >
                <option class="option" value="-">Currency to change the rate:</option>
                <c:forEach items="${currencies}" var="c">
                    <option class="option" value="${c.id}"> ${c.name} / ${c.rate}</option>
                </c:forEach>
            </select>

            <input name="rate" type="text" placeholder="New Rate" required>

            <button type="submit">Change rate!</button>
        </fieldset>
    </form>

    <br><br>

    <a href="<c:url value="${contextPath}/cabinet"/>"><-- Back...</a>

</div>
</body>
</html>
