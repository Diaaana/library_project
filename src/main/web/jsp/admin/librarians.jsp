<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Библиотекари</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap-theme.css">

</head>
<body>
<jsp:include page="/jsp/layout/layout.jsp"></jsp:include>

<div class="heading">
    <h1>Библиотекари</h1>
</div>

<table class="table table-hover">

    <thead>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Смена</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="librarian" items="${librarians}">
        <tr>
            <td>${librarian.surname}</td>
            <td>${librarian.name}</td>
            <td>${librarian.middleName}</td>
            <td>${librarian.shift}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<a href="/jsp/admin/addLibrarians.jsp" class="a1">Добавить библиотекаря</a>


<jsp:include page="/jsp/layout/footer.jsp"></jsp:include>
</body>
<script src="/resource/js/bootstrap.js"></script>
</html>
