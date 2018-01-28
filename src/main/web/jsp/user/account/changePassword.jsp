<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Изменение пароля</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>

<c:set var="user" scope="session" value="${sessionScope.reader}"/>

<div class="container">
    <form role="form" action="/Controller" method="post" class="form-group">

        <div class="form-group">
            <label class="label">Введите старый пароль: </label>
            <input type="text" name="old_password" class="form-control"/><br/>
        </div>

        <div class="form-group">
            <label class="label">Введите новый пароль: </label>
            <input type="password" name="new_password" class="form-control"/><br/>
        </div>

        <div class="form-group">
            <label class="label">Повторите новый пароль: </label>
            <input type="password" name="repeat_new_password" class="form-control"/><br/>
        </div>

        <input type="hidden" name="number_ticket" value="${user.numberTicket}"/>
        <input type="hidden" name="command" value="change_password"/>
        <input type="submit" value="Изменить" class="btn btn-lg button-login"/>

    </form>
</div>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
<script src = "${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
</html>
