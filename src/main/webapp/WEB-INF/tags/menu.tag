<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<ul class="menu">
    <li><p class="user">${principal.name} ${principal.secondName} </p>
        <ul class="submenu">
            <li><a href="<c:url value="${contextPath}/cabinet"/>">Home page</a></li>
            <li><a href="${contextPath}/userEdit">Edit my profile</a></li>
            <li><a href="${contextPath}/logout">Sign out</a></li>
        </ul>
    </li>
</ul>