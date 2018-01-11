<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css">

</head>
<body>
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
    <c:forEach var="book" items="${books}">
    <tr>
        <td>${book.isbn}</td>
        <td>${book.tittle}</td>
        <td>${book.genre}</td>
        <td>${book.dateEdition}</td>
        <td>${book.placeEdition}</td>
        <td>${book.publisher}</td>
        <td>${book.numberCopies}</td>
    </tr>
    </tbody>
</c:forEach>
</table>
</body>
<script src = "/resource/js/script.js"></script>
</html>
