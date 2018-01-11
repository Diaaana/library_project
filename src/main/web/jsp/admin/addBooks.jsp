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

    <form role="form">
        <div class="form-group">
            <label>Введите международный номер: </label>
            <input type="text" id="isbn" class="form-control" placeholder="Международный номер">
            <p class="help-block">13 знаков</p>
        </div>
        <div class="form-group">
            <label class="control-label">Введите название книги: </label>
            <input type="text" id="tittle" class="form-control" placeholder="Название">
        </div>
        <div class="form-group">
            <label>Введите фамилию автора книги: </label>
            <input type="text" id="author" class="form-control" placeholder="Автор">
        </div>
        <div class="form-group">
            <label>Введите жанр книги: </label>
            <input type="text" id="genre" class="form-control" placeholder="Жанр">
        </div>
        <div class="form-group">
            <label>Введите дату издания книги: </label>
            <input type="text" id="date" class="form-control" placeholder="Дата издания">
        </div>
        <div class="form-group">
            <label>Введите место издания книги: </label>
            <input type="text" id="place" class="form-control" placeholder="Место издания">
        </div>
        <div class="form-group">
            <label>Введите название издательства: </label>
            <input type="text" id="publisher" class="form-control" placeholder="Издательство">
        </div>
        <div class="form-group">
            <label>Введите число экземпляров: </label>
            <input type="text" id="number" class="form-control" placeholder="Экземпляры">
        </div>
        <button type="submit" class="btn btn-success">Добавить</button>
        <button type="reset" value="clear" onclick="clearForm()" class="btn btn-danger">Очистить</button>
    </form>
</div>
</body>
<script src = "/resource/js/bootstrap.js"></script>
</html>
