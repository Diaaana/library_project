<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body>

<c:if test="${requestScope.orders != null}"/>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Номер билета</th>
        <th>Название книги</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Изображение</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.numberTicket}</td>
            <td>${order.book.tittle}</td>
            <td>${order.author.surname}</td>
            <td>${order.author.name}</td>
            <td>${order.author.middleName}</td>
            <td><img src="/resource/images/books/${order.book.image}" alt="${order.book.tittle}" class="imageBook"></td>
            <td>
                <div class="form-group">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="check_order"/>
                        <input type="hidden" name="id_order" value="${order.id}"/>
                        <input type="hidden" name="number_ticket" value="${order.numberTicket}"/>
                        <input type="hidden" name="id_book" value="${order.book.id}"/>
                        <input type="submit" value="Принять заказ" class="btn btn-link nav-btn"/><br/>
                    </form>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
