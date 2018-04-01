<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url  value="/resources/css/template.css" var="cssPath" htmlEscape="true"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <tags:title/>
    <link rel="stylesheet" href="${cssPath}" type="text/css" media="screen">
    <link rel="stylesheet" href="<c:url value="/resources/css/menu.css"/> ">
</head>
<body>

<div id="page">
    <tags:menu/>
    <h2>Edit Transaction</h2>

    <form action="${contextPath}/transaction/edit" method="post">
        <fieldset>

            <input name="transactionId" type="text" value="${transaction.idTransaction}" hidden >

            <label for="date">Date</label>
            <input name="date" id="date" type="text" value="${transaction.date}">

            <label for="category">Name</label>
            <select name="categoryId" id="category">
                <option class="option" value="${transaction.category.idCategory}">${transaction.category.name}</option>
                <c:forEach items="${categories}" var="d">
                    <option class="option" value="${d.idCategory}">${d.name}</option>
                </c:forEach>
            </select>
            <a href="${contextPath}/category/editPage/${status}"> >> </a>


            <label for="amount">Amount</label>
            <input name="amount" id="amount" type="text" value="${transaction.amount}">

            <label for="currency">Currency</label>
            <select name="idCurrency" id="currency">
                <option class="option" value="${transaction.currency.id}">${transaction.currency.name}</option>
                <c:forEach items="${currencies}" var="c">
                    <option class="option" value="${c.id}">${c.name}</option>
                </c:forEach>
            </select>
            <a href="${contextPath}/currency/editPage"> >> </a>

            <label for="comment">Comment</label>
            <input name="comment" id="comment" type="text" value="${transaction.comment}">

            <input type="submit" value="Edit!"/>
        </fieldset>
    </form>
<br>
    <form action="${contextPath}/transaction/delete/${transaction.idTransaction}" method="post">
        <fieldset>
            <input type="submit" value="Delete!"/>
        </fieldset>
    </form>

</div>
</body>
</html>
