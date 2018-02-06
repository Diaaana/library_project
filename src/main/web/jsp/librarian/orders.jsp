<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<html>
<head>
    <title><fmt:message key="label.orders" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<c:if test="${requestScope.messageTakeOrder == 'success'}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successTakeOrder" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${sessionScope.orders != null}"/>
<table class="table table-hover">
    <thead>
    <tr>
        <th><fmt:message key="label.numberTicket" bundle="${local}"/></th>
        <th><fmt:message key="label.tittle" bundle="${local}"/></th>
        <th><fmt:message key="label.surname" bundle="${local}"/></th>
        <th><fmt:message key="label.name" bundle="${local}"/></th>
        <th><fmt:message key="label.middleName" bundle="${local}"/></th>
        <th><fmt:message key="label.image" bundle="${local}"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.user.numberTicket}</td>
            <td>${order.book.tittle}</td>
            <td>${order.author.surname}</td>
            <td>${order.author.name}</td>
            <td>${order.author.middleName}</td>
            <td><img src="/resource/images/book/${order.book.image}" alt="${order.book.tittle}" class="imageBook"></td>
            <td>
                <a href="${pageContext.request.contextPath}/Controller?id_order=${order.id}&id_reader=${order.user.id}&id_book=${order.book.id}&command=check_order" class="a-function">
                    <fmt:message key="label.takeOrder" bundle="${local}"/>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
