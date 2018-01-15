<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css">

</head>
<body>
<div class="container">
<form action="/Controller" method="post">
    <div class="input-group">
        <input type="text" name="book" placeholder="Название книги">
        <span class="glyphicon glyphicon-search"></span>
    </div>
    <input type="hidden" name="command" value="find_book"/>
    <input type="submit" value="Поиск книги"/>


    <c:if test="${requestScope.book != null}">
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
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${book}">
            <tr>
                <td>${book.isbn}</td>
                <td>${book.tittle}</td>
                <td>${book.genre}</td>
                <td>${book.dateEdition}</td>
                <td>${book.placeEdition}</td>
                <td>${book.publisher}</td>
                <td>${book.numberCopies}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    </c:if>
</form>
</div>


</body>
<script src = "/resource/js/bootstrap.js"></script>
</html>
