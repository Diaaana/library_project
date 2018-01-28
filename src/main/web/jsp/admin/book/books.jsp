<%@ page contentType="text/html;charset=UTF-8" %>
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

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>


<form action="Controller" method="post">
    <input type="hidden" name="command" value="get_genres"/>
    <input type="submit" value="Добавить новую книгу" class="btn-function"/>
</form>

<form action="Controller" method="post">
    <table class="table table-hover">

        <thead>
        <tr>
            <th></th>
            <th>Международный номер</th>
            <th>Название</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Дата издания</th>
            <th>Место издания</th>
            <th>Издательство</th>
            <th>Количество копий</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td><img src="/resource/images/book/${book.image}" alt="${book.tittle}" class="imageBook"></td>
                <td>${book.isbn}</td>
                <td>${book.tittle}</td>
                <td>${book.author.surname}</td>
                <td>${book.author.name}</td>
                <td>${book.author.middleName}</td>
                <td>${book.dateEdition}</td>
                <td>${book.placeEdition}</td>
                <td>${book.publisher}</td>
                <td>${book.numberCopies}</td>
                <td>
                    <a class="btn-book-delete" href="Controller?id_book=${book.id}&command=delete_book">Удалить</a>
                    <a class="btn-book-edit" href="Controller?id_book=${book.id}&command=edit_book">Редактировать</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>

</body>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
</html>
