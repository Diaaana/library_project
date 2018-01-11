<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление книги</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css.css">

</head>
<body class="body-add-books">

<div class="container">

    <jsp:include page="../layout/layout.jsp"></jsp:include>
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <h1 class="header">Добавление книги</h1>

    <form role="form" action="/Controller" method="get">
        <div class="form-group">
            <label>Введите международный номер: </label>
            <input type="text" name="isbn" class="form-control" placeholder="Международный номер">
            <p class="help-block">13 знаков</p>
        </div>
        <div class="form-group">
            <label class="control-label">Введите название книги: </label>
            <input type="text" name="tittle" class="form-control" placeholder="Название">
        </div>
        <div class="form-group">
            <label>Введите фамилию автора книги: </label>
            <input type="text" name="surname" class="form-control" placeholder="Фамилия">
        </div>
        <div class="form-group">
            <label>Введите имя автора книги: </label>
            <input type="text" name="name" class="form-control" placeholder="Имя">
        </div>
        <div class="form-group">
            <label>Введите отчество автора книги: </label>
            <input type="text" name="middle_name" class="form-control" placeholder="Отчество">
        </div>
        <div class="form-group">
            <label>Введите страну рождения автора книги: </label>
            <input type="text" name="country" class="form-control" placeholder="Страна">
        </div>
        <div class="form-group">
            <label>Введите жанр книги: </label>
            <input type="text" name="genre" class="form-control" placeholder="Жанр">
        </div>
        <div class="form-group">
            <label>Введите дату издания книги: </label>
            <input type="text" name="date_edition" class="form-control" placeholder="Дата издания">
        </div>
        <div class="form-group">
            <label>Введите место издания книги: </label>
            <input type="text" name="place_edition" class="form-control" placeholder="Место издания">
        </div>
        <div class="form-group">
            <label>Введите название издательства: </label>
            <input type="text" name="publisher" class="form-control" placeholder="Издательство">
        </div>
        <div class="form-group">
            <label>Введите число экземпляров: </label>
            <input type="text" name="number_copies" class="form-control" placeholder="Экземпляры">
        </div>
        <input type="hidden" name="command" value="add_book"/>
        <input type="submit" name="add_book" class="btn btn-success" value="Добавить"/>
        <button type="reset" value="clear" onclick="clearForm()" class="btn btn-danger">Очистить</button>
    </form>
</div>
</body>
<script src = "/resource/js/bootstrap.js"></script>
</html>
