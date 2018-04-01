<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
    <h2>Add ${status}</h2>

    <form action="${contextPath}/transaction/add" method="post">
        <fieldset>
            <label for="date">Date</label>
            <input name="date" id="date" type="date" value="${currentDate}">

            <label for="category">Category</label>
            <select name="idCategory" id="category">
                <c:forEach items="${categories}" var="d">
                    <option class="option" value="${d.idCategory}">${d.name}</option>
                </c:forEach>
            </select>
            <a href="/category/editPage/${status}">Edit</a>

            <label for="amount">Amount</label>
            <input name="amount" id="amount" type="text" placeholder="Введіть суму">

            <label for="currency">Currency</label>
            <select name="idCurrency" id="currency">
                <c:forEach items="${currencies}" var="c">
                    <option class="option" value="${c.id}">${c.name}</option>
                </c:forEach>
            </select>
            <a href="${contextPath}/currency/editPage">Edit</a>

            <label for="comment">Comment</label>
            <input name="comment" id="comment" type="text" placeholder="коментар...">

            <input type="submit" value="Add"/>
        </fieldset>
    </form>


</div>
</body>
</html>
