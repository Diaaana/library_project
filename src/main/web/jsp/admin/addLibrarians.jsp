<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Библиотекари</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap-theme.css.css">

</head>
<body>
<div class="container">

    <jsp:include page="../layout/layout.jsp"></jsp:include>
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <div class="heading">
        <h1>Добавление библиотекаря</h1>
    </div>

    <form role="form" action="/Controller" method="get">
        <div class="form-group">
            <label class="label">Введите фамилию: </label>
            <input type="text" name="surname" class="form-control" placeholder="Фамилия">
        </div>
        <div class="form-group">
            <label class="label">Введите имя: </label>
            <input type="text" name="name" class="form-control" placeholder="Имя">
        </div>
        <div class="form-group">
            <label class="label">Введите отчество: </label>
            <input type="text" name="middle_name" class="form-control" placeholder="Отчество">
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
            <label class="label">Введите логин: </label>
            <input type="text" name="login" class="form-control" placeholder="Логин">
        </div>
        <div class="form-group">
            <label class="label">Введите пароль: </label>
            <input type="text" name="password" class="form-control" placeholder="Пароль">
        </div>
        <div class="form-group">
            <input type="hidden" name="command" value="add_librarian"/>
            <input type="submit" name="add_librarian" class="btn btn-success" value="Добавить"/>
            <button type="reset" value="clear" onclick="clearForm()" class="btn btn-danger">Очистить</button>
        </div>
    </form>
</div>
</body>
<script src="/resource/js/bootstrap.js"></script>
</html>
