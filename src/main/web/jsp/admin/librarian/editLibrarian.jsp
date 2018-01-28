<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap-theme.css.css">

</head>
<body>

<c:set var="librarian" scope="request" value="${requestScope.librarian}"/>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>

<div class="container">

    <form role="form" action="/Controller" method="post">
        <div class="form-group">
            <label class="label">Введите фамилию: </label>
            <input type="text" name="surname" class="form-control" value="${librarian.surname}">
        </div>
        <div class="form-group">
            <label class="label">Введите имя: </label>
            <input type="text" name="name" class="form-control" value="${librarian.name}">
        </div>
        <div class="form-group">
            <label class="label">Введите отчество: </label>
            <input type="text" name="middle_name" class="form-control" value="${librarian.middleName}">
        </div>
        <div class="form-group">
            <label class="label">Введите логин: </label>
            <input type="text" name="login" class="form-control" value="${librarian.login}">
        </div>
        <div class="form-group">
            <input type="hidden" name="command" value="update_librarian"/>
            <input type="hidden" name="id_librarian" value="${librarian.numberTicket}"/>
            <input type="submit" name="add_librarian" class="btn btn-success" value="Отредактировать"/>
            <button type="reset" value="clear" onclick="clearForm()" class="btn btn-danger">Очистить</button>
        </div>
    </form>
</div>

</body>
</html>
