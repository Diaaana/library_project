<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="reader" scope="session" value="${sessionScope.reader}"/>

<html>
<head>
    <title>Книги</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body>
<div class="container">
<form action="Controller" method="post">
<c:if test="${requestScope.books != null}">
    <table class="table table-hover">
    <thead>
    <jsp:include page="/jsp/user/tableHeader/table.jsp"></jsp:include>
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
        <td>${book.author.surname}</td>
        <td>${book.author.name}</td>
        <td>${book.author.middleName}</td>
        <td>
            <div class="form-group">
                <a class="btn-link" href="Controller?number_ticket=${reader.numberTicket}&id_book=${book.id}&id_author=${book.author.id}&command=add_to_cart">В корзину</a>
            </div>
        </td>
    </c:forEach>
    </tr>
    </tbody>
    </table>
</c:if>
    </form>


    <div class="form-inline col-md-4 col-md-offset-4">
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
    </div>


    <c:if test="${requestScope.foundBooksByTittle != null}">
        <table class="table table-hover">
        <thead>
        <jsp:include page="/jsp/user/tableHeader/table.jsp"></jsp:include>
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
    </c:if>
    </tbody>
    </table>


    <c:if test="${requestScope.foundBooksByAuthor != null}">
        <table class="table table-hover">
        <thead>
        <jsp:include page="/jsp/user/tableHeader/table.jsp"></jsp:include>
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
                <td>
                    <img src="/resource/images/books/${foundBooksByAuthor.image}"
                         alt="${foundBooksByAuthor.tittle}" class="imageBook">
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
    </table>
    </div>
    </body>
    <script src="/resource/js/bootstrap.js"></script>
    </html>
