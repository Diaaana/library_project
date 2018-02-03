<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>

<html>
<head>
    <title><fmt:message key="label.books" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>

<c:if test="${sessionScope.messageAdd == 'success'}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successAddBook" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${requestScope.messageEdit == 'success'}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successEditBook" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${requestScope.messageDelete == 'success'}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successDeleteBook" bundle="${local}"/>
    </div>
</c:if>


<div class="container">
    <form action="/Controller" method="post">
        <a href="Controller?command=get_genres" class="a-function"><fmt:message key="label.addNewBook" bundle="${local}"/> <span
                class="glyphicon glyphicon-plus"></span></a>
    </form>

    <form action="/Controller" method="post">
        <table class="table table-hover">

            <thead>
            <tr>
                <th><fmt:message key="label.image" bundle="${local}"/></th>
                <th><fmt:message key="label.isbn" bundle="${local}"/></th>
                <th><fmt:message key="label.tittle" bundle="${local}"/></th>
                <th><fmt:message key="label.genre" bundle="${local}"/></th>
                <th><fmt:message key="label.surnameAuthor" bundle="${local}"/></th>
                <th><fmt:message key="label.nameAuthor" bundle="${local}"/></th>
                <th><fmt:message key="label.middleNameAuthor" bundle="${local}"/></th>
                <th><fmt:message key="label.dateEdition" bundle="${local}"/></th>
                <th><fmt:message key="label.placeEdition" bundle="${local}"/></th>
                <th><fmt:message key="label.publisher" bundle="${local}"/></th>
                <th><fmt:message key="label.numberCopies" bundle="${local}"/></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td><img src="/resource/images/book/${book.image}" alt="${book.tittle}" class="imageBook"></td>
                    <td>${book.isbn}</td>
                    <td>${book.tittle}</td>
                    <td>${book.genres}</td>
                    <td>${book.author.surname}</td>
                    <td>${book.author.name}</td>
                    <td>${book.author.middleName}</td>
                    <td>${book.dateEdition}</td>
                    <td>${book.placeEdition}</td>
                    <td>${book.publisher}</td>
                    <td>${book.numberCopies}</td>
                    <td>
                        <a class="a-function" href="/Controller?id_book=${book.id}&command=delete_book"><fmt:message key="label.delete" bundle="${local}"/>
                            <span class="glyphicon glyphicon-trash"></span></a>
                        <a class="a-function" href="/Controller?id_book=${book.id}&command=edit_book"><fmt:message key="label.edit" bundle="${local}"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
</html>
