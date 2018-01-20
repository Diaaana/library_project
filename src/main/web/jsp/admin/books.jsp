<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Книги</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body>

<a href="${pageContext.request.contextPath}/jsp/admin/addBooks.jsp" class="a1">Добавить книгу</a>

<form action="Controller" method="post">
    <table class="table table-hover">

        <thead>
        <tr>
            <th>Международный номер</th>
            <th>Название</th>
            <th>Жанр</th>
            <th>Дата издания</th>
            <th>Место издания</th>
            <th>Издательство</th>
            <th>Количество копий</th>
            <th>Изображение</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.isbn}</td>
                <td>${book.tittle}</td>
                <td>${book.genre}</td>
                <td>${book.dateEdition}</td>
                <td>${book.placeEdition}</td>
                <td>${book.publisher}</td>
                <td>${book.numberCopies}</td>
                <td><img src="/resource/images/books/${book.image}" alt="${book.tittle}" class="imageBook"></td>
                <td>
                    <div class="form-group">
                        <a class="btn-link" href="Controller?id=${book.id}&command=delete_book">Удалить</a>
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
