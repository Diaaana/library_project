<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Личный кабинет</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>

<c:set var="reader" scope="request" value="${requestScope.user}"/>

<div class="container">

    <a href="${pageContext.request.contextPath}/jsp/user/account/changePassword.jsp">Поменять пароль</a>

    <form action="Controller" method="post">
        <c:if test="${reader != null}">

            <div class="image">
                <img src="/resource/images/reader/${reader.profilePhoto}" alt="${reader.login}" class="persImageBook">
            </div>

            <div class="col-md-4">
                <p class="parameter-book">Номер билета: ${reader.numberTicket}</p>
                <p class="parameter-book">Фамилия: ${reader.surname}</p>
                <p class="parameter-book">Имя: ${reader.name}</p>
                <p class="parameter-book">Отчество: ${reader.middleName}</p>
            </div>
            <div class="col-md-4 col-md-offset-4">
                <p class="parameter-book">Возраст: ${reader.age}</p>
                <p class="parameter-book">Номер телефона: ${reader.phoneNumber}</p>
                <p class="parameter-book">Почта: ${reader.mail}</p>
                <p class="parameter-book">Логин: ${reader.login}</p>
                <p class="parameter-book">Пароль: ${reader.password}</p>
            </div>
        </c:if>
    </form>

</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
<script src = "${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
</html>
