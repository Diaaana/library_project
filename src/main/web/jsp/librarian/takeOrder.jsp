<%@ page contentType="text/html;charset=UTF-8" %>
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
<c:out value="${sessionScope.order.numberTicket}"/>
<div class="container">
    <form action="Controller" method="post">
        <div class="form-group">
            <label class="label">Дата взятия книги: </label>
            <input type="date" name="date_borrow" class="form-control">
        </div>
        <div class="form-group">
            <label class="label">Дата возврата книги: </label>
            <input type="date" name="date_return" class="form-control" placeholder="Дата возврата">
        </div>
        <div class="form-group">
            <label class="label">Выберите способ: </label><br/>
            <label class="radio-inline">
                <input type="radio" name="method_borrow" value="Абонемент">Абонемент
            </label>
            <label class="radio-inline">
                <input type="radio" name="method_borrow" value="Читательный зал">Читательный зал
            </label>
        </div>
    <div class="form-group">
        <input type="hidden" name="command" value="take_order"/>
        <input type="hidden" name="id_order" value="${order.id}"/>
        <input type="hidden" name="number_ticket" value="${order.numberTicket}"/>
        <input type="hidden" name="id_book" value="${order.book.id}"/>
        <input type="submit" value="Принять заказ" class="btn btn-link nav-btn"/><br/>
    </div>
        <button type="reset" value="clear" onclick="clearForm()" class="btn btn-danger"><fmt:message key="label.clear" bundle="${local}"/></button>
    </form>
</div>

</body>
</html>
