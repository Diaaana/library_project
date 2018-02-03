<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>

<c:set var="reader" scope="session" value="${sessionScope.reader}"></c:set>

<html>
<head>
    <title><fmt:message key="label.orderCart" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>

<c:if test="${requestScope.messageDelete == 'success'}">
    <div id="error" class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successDeleteOrder" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${requestScope.messageOrders == 'empty'}">
    <div id="error" class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.emptyOrders" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${requestScope.messageApprovedOrders == 'empty'}">
    <div id="error" class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.emptyApprovedOrders" bundle="${local}"/>
    </div>
</c:if>

<div class="container">

    <c:if test="${requestScope.orders != null}"/>

    <a class="a-function" href="/Controller?id_reader=${reader.id}&command=get_approved_orders"><fmt:message key="label.watchApprovedOrders" bundle="${local}"/></a>

    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message key="label.numberTicket" bundle="${local}"/></th>
            <th><fmt:message key="label.tittle" bundle="${local}"/></th>
            <th><fmt:message key="label.surname" bundle="${local}"/></th>
            <th><fmt:message key="label.name" bundle="${local}"/></th>
            <th><fmt:message key="label.middleName" bundle="${local}"/></th>
            <th><fmt:message key="label.image" bundle="${local}"/></th>
            <td></td>
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
                <td><img src="/resource/images/book/${order.book.image}" alt="${order.book.tittle}" class="imageBook">
                </td>
                <td>
                    <a class="a-function"
                       href="/Controller?id_order=${order.id}&id_reader=${order.user.id}&command=delete_order"><fmt:message key="label.delete" bundle="${local}"/>
                        <span class="glyphicon glyphicon-trash"></span></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
</html>
