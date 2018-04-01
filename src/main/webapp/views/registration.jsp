<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <tags:title/>
    <link rel="stylesheet" href="${contextPath}/resources/css/template.css">
</head>
<body>
<div id="page">
    <h1>Registration page</h1>

    <%--@elvariable id="user" type=""--%>
    <form:form commandName="user" action="${contextPath}/registration">
      <fieldset>
          <spring:bind path="name">
              <form:errors path="name" class="errors"/>
              <form:label path="name">Name:</form:label>
              <form:input path="name" autofocus="true"/>
          </spring:bind>

          <spring:bind path="secondName">
              <form:errors path="secondName" class="errors"/>
              <form:label path="secondName">SecondName:</form:label>
              <form:input path="secondName"/>
          </spring:bind>

          <spring:bind path="email">
              <form:errors path="email" class="errors"/>
              <form:label path="email">Email:</form:label>
              <form:input path="email"/>
          </spring:bind>

          <spring:bind path="password">
              <form:errors path="password" class="errors"/>
              <form:label path="password">Password:</form:label>
              <form:password path="password"/>
          </spring:bind>

          <spring:bind path="confirmPassword">
              <form:errors path="confirmPassword" class="errors"/>
              <form:label path="confirmPassword">Confirm:</form:label>
              <form:input type="password" path="confirmPassword"  placeholder="Confirm your password"/>
          </spring:bind>

        <input type="submit" value="Registration!"/>
      </fieldset>
    </form:form>
</div>
</body>
</html>
