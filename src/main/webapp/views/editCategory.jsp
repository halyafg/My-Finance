<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <h2>Edit Category</h2>

    <%--@elvariable id="category" type=""--%>
    <form:form commandName="category" action="${contextPath}/category/add">
        <fieldset>
            <form:input path="name" placeholder="New Category"/>
            <form:hidden path="status" value="${status}"/>
            <button type="submit">Add!</button>
        </fieldset>
    </form:form>

    <br><br>


    <form action="${contextPath}/category/rename" method="post">
        <fieldset>
            <select name="idCategory">
                <option value="-" class="option">Categry to rename:</option>
                <c:forEach items="${categories}" var="d">
                    <option name="idCategory" class="option" value="${d.idCategory}">${d.name}</option>
                </c:forEach>
            </select>

            <input name="newName" type="text" placeholder="New Name" required>

            <button type="submit">Rename!</button>
        </fieldset>
    </form>

    <br><br>

    <form action="${contextPath}/category/delete/${status}" method="post">
        <fieldset>
            <select name="idCategory" id="">
                <option value="-" class="option">Category to delete:</option>
                <c:forEach items="${categories}" var="d">
                    <option class="option" value="${d.idCategory}">${d.name}</option>
                </c:forEach>
            </select>
            <button type="submit">Delete!</button>
        </fieldset>
    </form>

    <br>
    <a href="<c:url value="${contextPath}/cabinet"/>"><-- Back</a>

</div>
</body>
</html>
