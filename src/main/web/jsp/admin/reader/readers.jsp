<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Читатели</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body>

<jsp:include page="/jsp/layout/layout.jsp"></jsp:include>
<jsp:include page="/jsp/layout/footer.jsp"></jsp:include>

<div class="heading">
    <h1>Читатели</h1>
</div>

<form action="Controller" method="post">

<table class="table table-hover">

    <thead>
    <tr>
        <th>Фото профиля</th>
        <th>Логин</th>
        <th>Номер билета</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Возраст</th>
        <th>Телефон</th>
        <th>Почта</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="user" items="${readers}">
        <tr>
            <td><img src="/resource/images/readers/${user.profilePhoto}" alt="${user.login}" class="imageBook"></td>
            <td>${user.login}</td>
            <td>${user.numberTicket}</td>
            <td>${user.surname}</td>
            <td>${user.name}</td>
            <td>${user.middleName}</td>
            <td>${user.age}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.mail}</td>
            <td>
                <div class="form-group">
                    <a class="btn-link" href="Controller?id=${user.numberTicket}&command=delete_reader">Удалить</a>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</form>

<a href="${pageContext.request.contextPath}/jsp/admin/registration.jsp" class="a1">Добавить читателя</a>

</body>
</html>
