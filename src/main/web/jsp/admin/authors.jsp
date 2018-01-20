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

<jsp:include page="/jsp/layout/layout.jsp"></jsp:include>
<jsp:include page="/jsp/layout/footer.jsp"></jsp:include>

<div class="heading">
    <h1>Авторы</h1>
</div>

<form action="Controller" method="post">
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
                <td>
                    <div class="form-group">
                        <a class="btn-link" href="Controller?id=${author.id}&command=delete_author">Удалить</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</form>
</body>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
</html>
