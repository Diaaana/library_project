<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Библиотека</title>

    <link rel="stylesheet" type="text/css" href = "/resource/css/style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css.css">

</head>
<body>
<div class="container">
    <form action="Controller" method="get">
    <nav>
        <div class="layout">
            <ul class="nav">

                <c:if test="${sessionScope.role == null}">
                    <li><a href="/jsp/user/registration.jsp" class="a1">Регистрация</a></li>
                </c:if>

                <c:if test="${sessionScope.role == 'user'}">
                    <li><a href="/jsp/user/main.jsp" class="a1">Главная</a></li>
                    <li><a href="/jsp/user/books.jsp" class="a1">Книги</a></li>
                    <li><a href="/jsp/user/authors.jsp" class="a1">Писатели</a></li>
                    <li><a href="/jsp/user/account.jsp" class="a1">Личный кабинет</a></li>
                    <li>
                        <form action="Controller" method="get">
                            <input type="hidden" name="command" value="logout"/>
                            <input type="submit" value="Выход" class="button"/><br />
                        </form>
                    </li>
                </c:if>

                <c:if test="${sessionScope.role == 'admin'}">
                    <li><a href="/jsp/admin/main.jsp" class="a1">Главная</a></li>
                    <li><a href="/jsp/admin/books.jsp" class="a1">Книги</a></li>
                    <li><a href="/jsp/admin/authors.jsp" class="a1">Писатели</a></li>
                    <li>
                        <form action="Controller" method="get">
                            <input type="hidden" name="command" value="logout"/>
                            <input type="submit" value="Выход" class="button"/><br />
                        </form>
                    </li>
                </c:if>

            </ul>
        </div>
    </nav>
    </form>
</div>
</body>
<script src = "/resource/js/bootstrap.js"></script>
</html>
