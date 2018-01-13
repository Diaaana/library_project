<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href = "/resource/css/style.css">
</head>
<body>
<h1>РЕГИСТРАЦИЯ</h1>
<div class="form">
    <form action="/Controller" name="form" onsubmit="return validationForm();">
        <div class="input">
            <input type="text" name="surname" placeholder="Фамилия" class="field">
        </div>
        <div class="input">
            <input type="text" name="name" placeholder="Имя" class="field">
        </div>
        <div class="input">
            <input type="text" name="middleName" placeholder="Отчество" class="field">
        </div>
        <div class="input">
            <input type="text" name="age" placeholder="Возраст" class="field">
        </div>
        <div class="input">
            <input type="text" name="phone" placeholder="Телефон" class="field">
        </div>
        <div class="input">
            <input type="text" name="mail" placeholder="Электронная почта" class="field">
        </div>
        <div class="input">
            <input type="text" name="login" placeholder="Логин" class="field">
        </div>
        <div class="input">
            <input type="password" name="password" placeholder="Пароль" class="field">
        </div>
        <div class="input">
            <input type="password" name="password2" placeholder="Повторите пароль" class="field">
        </div>
        <div class="buttonInput">
            <input type="hidden" name="command" value="registration"/>
            <input type="submit" name="registration" value="Зарегистрироваться" class="button">
            <button type="reset" value="clear" onclick="clearForm()" class="button">Очистить</button>
        </div>
    </form>
</div>
</body>
<script src = "/resource/js/script.js"></script>
</html>
