<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

    <h2>My Finance</h2>

    <p class="period"><a href="${contextPath}/period" style="color: forestgreen">${periodFrom}  --  ${periodTo}</a></p>

    <fieldset>

            <table class=" table left">
                <thead>
                    <tr >
                        <th><a href="${contextPath}/transaction/addPage/${statusOutcome}" class="th">+ Spending</a></th>
                        <th>${userOutcomes}</th>
                    </tr>
                </thead>

                <tbody>
                <c:forEach items="${categoriesOut}" var="d">
                    <tr>
                        <td><a href="${contextPath}/category/inf/${d.key}/${d.value*100}">${d.key}</a> </td>
                        <td><a href="${contextPath}/category/inf/${d.key}/${d.value*100}">${d.value}</a> </td>

                    </tr>
                </c:forEach>
                </tbody>

            </table>

            <table class="table">
                <thead>
                <tr>
                    <th><a class="th" href="/transaction/addPage/${statusIncome}">+ Income</a></th>
                    <th>${userIncomes}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categoriesIn}" var="d">
                    <tr>
                        <td><a href="/category/inf/${d.key}/${d.value}">${d.key}</a> </td>
                        <td>${d.value}</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
    </fieldset>




    <h3>Balance =  ${balance}</h3>



<%--  Try to do scrolling in table

    <table >
        <tr>
            <td>
                <table  >
                    <tr>
                        <th  style="width: 60px">Header  1</th>
                        <th  style="width: 60px">Header 2</th>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div style="height:60px; overflow-y:auto;">
                    <table >
                        <tr>
                            <td style="width: 60px">new item mmmm mmmm</td>
                            <td  style="width: 60px">50</td>
                        </tr>
                        <tr>
                            <td>new item</td>
                            <td>150</td>
                        </tr>
                        <tr>
                            <td>new item</td>
                            <td>50000</td>
                        </tr>
                        <tr>
                            <td>new item</td>
                            <td>3580.56</td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
    </table>--%>

</div> <%--page--%>
</body>
</html>
