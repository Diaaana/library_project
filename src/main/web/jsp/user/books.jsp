<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap-theme.css">

</head>
<body>
<div class="container">
    <div class="form-inline">
        <form action="/Controller" method="post">
            <div class="input-group">
                <input type="text" name="book" placeholder="Название книги">
                <span class="glyphicon glyphicon-search"></span>
            </div>
            <div class="input-group">
                <input type="hidden" name="command" value="find_book_by_tittle"/>
                <input type="submit" value="Поиск книги по названию"/> <br/>
            </div>
        </form>

        <form action="/Controller" method="post">

            <div class="input-group">
                <input type="text" name="author" placeholder="Фамилия автора">
                <span class="glyphicon glyphicon-search"></span>
            </div>
            <div class="input-group">
                <input type="hidden" name="command" value="find_book_by_author"/>
                <input type="submit" value="Поиск книги по автору"/>
            </div>
        </form>

            <c:if test="${requestScope.foundBooksByTittle != null}">
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
                    <c:forEach var="foundBooksByTittle" items="${foundBooksByTittle}">
                        <tr>
                            <td>${foundBooksByTittle.isbn}</td>
                            <td>${foundBooksByTittle.tittle}</td>
                            <td>${foundBooksByTittle.genre}</td>
                            <td>${foundBooksByTittle.dateEdition}</td>
                            <td>${foundBooksByTittle.placeEdition}</td>
                            <td>${foundBooksByTittle.publisher}</td>
                            <td>${foundBooksByTittle.numberCopies}</td>
                            <td><img src="/resource/images/books/${foundBooksByTittle.image}"
                                     alt="${foundBooksByTittle.tittle}" class="imageBook"></td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </c:if>

            <c:if test="${requestScope.foundBooksByAuthor != null}">
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
                    <c:forEach var="foundBooksByAuthor" items="${foundBooksByAuthor}">
                        <tr>
                            <td>${foundBooksByAuthor.isbn}</td>
                            <td>${foundBooksByAuthor.tittle}</td>
                            <td>${foundBooksByAuthor.genre}</td>
                            <td>${foundBooksByAuthor.dateEdition}</td>
                            <td>${foundBooksByAuthor.placeEdition}</td>
                            <td>${foundBooksByAuthor.publisher}</td>
                            <td>${foundBooksByAuthor.numberCopies}</td>
                            <td><img src="/resource/images/books/${foundBooksByAuthor.image}"
                                     alt="${foundBooksByAuthor.tittle}" class="imageBook"></td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </c:if>

        </form>
    </div>
</div>


</body>
<script src="/resource/js/bootstrap.js"></script>
</html>
