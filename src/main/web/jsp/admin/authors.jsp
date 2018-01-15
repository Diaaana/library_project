<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css">

</head>
<body>

<jsp:include page="/jsp/layout/layout.jsp"></jsp:include>
<jsp:include page="/jsp/layout/footer.jsp"></jsp:include>

<div class="heading">
    <h1>Авторы</h1>
</div>

<table class="table table-hover">

    <thead>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Страна рождения</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="author" items="${authors}">
        <tr>
            <td>${author.surname}</td>
            <td>${author.name}</td>
            <td>${author.middleName}</td>
            <td>${author.countryBirth}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>

</body>
<script src = "/resource/js/bootstrap.js"></script>
</html>
