<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <tags:title/>
    <link rel="stylesheet" href="<c:url value="/resources/css/table.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/template.css"/>" type="text/css" media="screen">
    <link rel="stylesheet" href="<c:url value="/resources/css/menu.css"/> ">
</head>
<body>
<div id="page">

    <tags:menu/>
    <h2>${categoryName} </h2>
    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Total amount = ${suma/100}</th>

            </tr>
        </thead>
        <tbody>
            <jsp:useBean id="categories" scope="request" type="java.util.List"/>
            <c:forEach items="${categories}" var="d">
                <tr>
                    <td>${d.date}<p class="comment">${d.comment}</p></td>
                    <td>${d.amount} ${d.currency.name}</td>
                    <td><a href="${contextPath}/transaction/editPage/${d.category.status}/${d.idTransaction}"> >> </a> </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
