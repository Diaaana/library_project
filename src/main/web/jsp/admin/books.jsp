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
<form action="/Controller" method="get">
<a href="/jsp/admin/addBooks.jsp" class="a1">Добавить книгу</a>

    <input type="hidden" name="command" value="add_book"/>
    <input type="submit" value="Книги" class="btn btn-success">
</form>
<form action="/Controller" method="post">
    <input type="hidden" name="command" value="show_books"/>
    <input type="submit" value="Показать Книги" class="btn btn-success">
</form>
</body>
<script src = "/resource/js/bootstrap.js"></script>
</html>
