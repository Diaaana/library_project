<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Библиотекари</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body>
<jsp:include page="/jsp/layout/layout.jsp"></jsp:include>

<div class="heading">
    <h1>Библиотекари</h1>
</div>

<form action="Controller" method="post">

<table class="table table-hover">

    <thead>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Логин</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="librarian" items="${librarians}">
        <tr>
            <td>${librarian.surname}</td>
            <td>${librarian.name}</td>
            <td>${librarian.middleName}</td>
            <td>${librarian.login}</td>
            <td>
                <div class="form-group">
                    <a class="btn-link" href="Controller?id_librarian=${librarian.numberTicket}&command=delete_librarian">Удалить</a>
                    <a class="btn-link" href="Controller?id_librarian=${librarian.numberTicket}&command=edit_librarian">Редактировать</a>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</form>

<a href="${pageContext.request.contextPath}/jsp/admin/librarian/addLibrarians.jsp" class="a1">Добавить библиотекаря</a>


<jsp:include page="/jsp/layout/footer.jsp"></jsp:include>
</body>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
</html>
