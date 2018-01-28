<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="user" scope="session" value="${sessionScope.user}"/>

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

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>

<div class="container">

        <div class="col-md-4">
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
        </div>

        <div class="col-md-4 col-md-offset-4">
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

        <div class="col-md-4 col-md-offset-6">
            <form action="/Controller" method="post">
                <div class="input-group">
                    <input type="text" name="genre" placeholder="Жанр">
                    <span class="glyphicon glyphicon-search"></span>
                </div>
                <div class="input-group">
                    <input type="hidden" name="command" value="find_book_by_genre"/>
                    <input type="submit" value="Поиск книг по жанру"/>
                </div>
            </form>
        </div>

    <form action="Controller" method="post">
        <c:if test="${requestScope.books != null}">
            <table class="table table-hover">
                <thead>
                <jsp:include page="${pageContext.request.contextPath}/jsp/user/book/tableHead.jsp"></jsp:include>
                </thead>
                <tbody>

                <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.tittle}</td>
                    <td>${book.author.surname}</td>
                    <td>${book.author.name}</td>
                    <td>${book.author.middleName}</td>
                    <td><img src="/resource/images/book/${book.image}" alt="${book.tittle}" class="imageBook"></td>
                    <td>
                        <div class="form-group">
                            <a class="a-cart"
                               href="Controller?number_ticket=${user.numberTicket}&id_book=${book.id}&id_author=${book.author.id}&command=add_to_cart">В
                                корзину</a>
                            <a class="a-cart"
                               href="Controller?id_book=${book.id}&command=get_personal_book">Подробнее</a>
                        </div>
                    </td>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </c:if>
    </form>

    <c:if test="${requestScope.foundBooksByTittle != null}">
    <table class="table table-hover">
        <thead>
        <jsp:include page="${pageContext.request.contextPath}/jsp/user/book/tableHead.jsp"></jsp:include>
        </thead>
        <tbody>
        <c:forEach var="foundBooksByTittle" items="${foundBooksByTittle}">
            <tr>
                <td>${foundBooksByTittle.isbn}</td>
                <td>${foundBooksByTittle.tittle}</td>
                <td>${foundBooksByTittle.author.surname}</td>
                <td>${foundBooksByTittle.author.name}</td>
                <td>${foundBooksByTittle.author.middleName}</td>
                    <%--<td>${foundBooksByTittle.genre}</td>--%>
                <td>${foundBooksByTittle.dateEdition}</td>
                <td>${foundBooksByTittle.placeEdition}</td>
                <td>${foundBooksByTittle.publisher}</td>
                <td>${foundBooksByTittle.numberCopies}</td>
                <td><img src="/resource/images/book/${foundBooksByTittle.image}"
                         alt="${foundBooksByTittle.tittle}" class="imageBook"></td>
            </tr>
        </c:forEach>
        </c:if>
        </tbody>
    </table>


    <c:if test="${requestScope.foundBooksByAuthor != null}">
    <table class="table table-hover">
        <thead>
        <jsp:include page="${pageContext.request.contextPath}/jsp/user/book/tableHead.jsp"></jsp:include>
        </thead>
        <tbody>
        <c:forEach var="foundBooksByAuthor" items="${foundBooksByAuthor}">
            <tr>
                <td>${foundBooksByAuthor.isbn}</td>
                <td>${foundBooksByAuthor.tittle}</td>
                <td>${foundBooksByAuthor.author.surname}</td>
                <td>${foundBooksByAuthor.author.name}</td>
                <td>${foundBooksByAuthor.author.middleName}</td>
                    <%--<td>${foundBooksByAuthor.genre}</td>--%>
                <td>${foundBooksByAuthor.dateEdition}</td>
                <td>${foundBooksByAuthor.placeEdition}</td>
                <td>${foundBooksByAuthor.publisher}</td>
                <td>${foundBooksByAuthor.numberCopies}</td>
                <td>
                    <img src="/resource/images/book/${foundBooksByAuthor.image}"
                         alt="${foundBooksByAuthor.tittle}" class="imageBook">
                </td>
            </tr>
        </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${requestScope.foundBooksByGenre != null}">
    <table class="table table-hover">
        <thead>
        <jsp:include page="${pageContext.request.contextPath}/jsp/user/book/tableHead.jsp"></jsp:include>
        </thead>
        <tbody>
        <c:forEach var="foundBooksByGenre" items="${foundBooksByGenre}">
            <tr>
                <td>${foundBooksByGenre.isbn}</td>
                <td>${foundBooksByGenre.tittle}</td>
                <td>${foundBooksByGenre.author.surname}</td>
                <td>${foundBooksByGenre.author.name}</td>
                <td>${foundBooksByGenre.author.middleName}</td>
                    <%--<td>${foundBooksByGenre.genre}</td>--%>
                <td>${foundBooksByGenre.dateEdition}</td>
                <td>${foundBooksByGenre.placeEdition}</td>
                <td>${foundBooksByGenre.publisher}</td>
                <td>${foundBooksByGenre.numberCopies}</td>
                <td>
                    <img src="/resource/images/book/${foundBooksByGenre.image}"
                         alt="${foundBooksByGenre.tittle}" class="imageBook">
                </td>
            </tr>
        </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
<script src="/resource/js/bootstrap.js"></script>
</html>
