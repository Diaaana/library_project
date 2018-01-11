<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css">

</head>
<body class="body-registration">

<div class="container">

    <h1 class="header">РЕГИСТРАЦИЯ</h1>

        <form role="form" action="/Controller" name="form" onsubmit="return validationForm();" method="get">
            <div class="form-group field">
                <input type="text" name="surname" placeholder="Фамилия" class="form-control">
            </div>
            <div class="form-group field">
                <input type="text" name="name" placeholder="Имя" class="form-control">
            </div>
            <div class="form-group field">
                <input type="text" name="middleName" placeholder="Отчество" class="form-control">
            </div>
            <div class="form-group field">
                <input type="text" name="age" placeholder="Возраст" class="form-control">
            </div>
            <div class="form-group field">
                <input type="text" name="phone" placeholder="Телефон" class="form-control">
            </div>
            <div class="form-group field">
                <input type="text" name="mail" placeholder="Электронная почта" class="form-control">
            </div>
            <div class="form-group field">
                <input type="text" name="login" placeholder="Логин" class="form-control">
            </div>
            <div class="form-group field">
                <input type="password" name="password" placeholder="Пароль" class="form-control">
            </div>
            <div class="form-group field">
                <input type="password" name="password2" placeholder="Повторите пароль" class="form-control">
            </div>
            <div class="form-group">
                <input type="hidden" name="command" value="registration"/>
                <input type="submit" name="registration" value="Зарегистрироваться" class="btn btn-success">
                <button type="reset" value="clear" onclick="clearForm()" class="btn btn-danger">Очистить</button>
            </div>
        </form>

<jsp:include page="../layout/footer.jsp"></jsp:include>
</div>

</body>
<script src = "/resource/js/script.js"></script>
</html>
