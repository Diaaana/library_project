<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<div class="container">

    <jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>
    <jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>

    <form role="form" action="/Controller" method="get">
        <div class="form-group">
            <label class="label">Введите фамилию: </label>
            <input type="text" name="surname" class="form-control" value="${librarian.surname}">
        </div>
        <div class="form-group">
            <label class="label">Введите имя: </label>
            <input type="text" name="name" class="form-control" value="${librarian.surname}">
        </div>
        <div class="form-group">
            <label class="label">Введите отчество: </label>
            <input type="text" name="middle_name" class="form-control" value="${librarian.surname}">
        </div>
        <div class="form-group">
            <label class="label">Выберите смену: </label><br/>
            <label class="radio-inline">
                <input type="radio" name="shift" value="1">1
            </label>
            <label class="radio-inline">
                <input type="radio" name="shift" value="2">2
            </label>
            <label class="radio-inline">
                <input type="radio" name="shift" value="3">3
            </label>
        </div>
        <div class="form-group">
            <input type="hidden" name="command" value="update_librarian"/>
            <input type="hidden" name="id_librarian" value="${librarian.id}"/>
            <input type="submit" name="add_librarian" class="btn btn-success" value="Добавить"/>
            <button type="reset" value="clear" onclick="clearForm()" class="btn btn-danger">Очистить</button>
        </div>
    </form>
</div>

</body>
</html>
