<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Подробнее</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>

<c:set var="book" scope="request" value="${requestScope.personalBook}"/>

<div class="container">

    <div class="col-md-4 col-md-offset-5">
        <a class="a-cart"
           href="Controller?number_ticket=${user.numberTicket}&id_book=${book.id}&id_author=${book.author.id}&command=add_to_cart">Добавить в корзину</a>
    </div>

    <form action="Controller" method="post">
        <c:if test="${book != null}">

            <div class="image">
                <img src="/resource/images/book/${book.image}" alt="${book.tittle}" class="persImageBook">
            </div>

            <div class="col-md-4">
                <p class="parameter-book">Международный номер: ${book.isbn}</p>
                <p class="parameter-book">Название книги: ${book.tittle}</p>
                <p class="parameter-book">Дата публикации: ${book.dateEdition}</p>
                <p class="parameter-book">Место публикации: ${book.placeEdition}</p>
            </div>
            <div class="col-md-4 col-md-offset-4">
                <p class="parameter-book">Издательство: ${book.publisher}</p>
                <p class="parameter-book">Количество экземпляров: ${book.numberCopies}</p>
                <p class="parameter-book">Фамилия автора: ${book.author.surname}</p>
                <p class="parameter-book">Имя автора: ${book.author.name}</p>
                <p class="parameter-book">Отчество автора: ${book.author.middleName}</p>
            </div>
        </c:if>
    </form>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
</html>
