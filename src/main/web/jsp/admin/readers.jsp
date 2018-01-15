<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Читатели</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css">

</head>
<body>

<jsp:include page="/jsp/layout/layout.jsp"></jsp:include>
<jsp:include page="/jsp/layout/footer.jsp"></jsp:include>

<div class="heading">
    <h1>Читатели</h1>
</div>

<table class="table table-hover">

    <thead>
    <tr>
        <th>Фото</th>
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
    <c:forEach var="reader" items="${readers}">
        <tr>
            <td><img src="/resource/images/readers/${reader.profilePhoto}" alt="${reader.login}" class="imageBook"></td>
            <td>${reader.login}</td>
            <td>${reader.numberTicket}</td>
            <td>${reader.surname}</td>
            <td>${reader.name}</td>
            <td>${reader.middleName}</td>
            <td>${reader.age}</td>
            <td>${reader.phoneNumber}</td>
            <td>${reader.mail}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<a href="/jsp/admin/registration.jsp" class="a1">Добавить читателя</a>

</body>
</html>
