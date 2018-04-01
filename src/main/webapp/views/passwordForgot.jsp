<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <tags:title/>
    <link rel="stylesheet" href="<c:url value="/resources/css/template.css"/>">
</head>
<body>
<div id="page">
    <form action="${contextPath}/passwordForgot" method=post>
        <fieldset>
            <label for="email"> Input your email in MyFinance</label>
            <input name="email" id="email" type="email" placeholder="Email">

            <input onclick="message()" type="submit" value="Send link">
        </fieldset>
    </form>
</div>

<script>
    function message(){
        alert("Go to the link that was just sent to your email.")
    }

</script>

</body>
</html>
