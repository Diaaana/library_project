<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>

<fmt:message key="label.isbn" bundle="${local}" var="ISBN"/>
<fmt:message key="label.tittle" bundle="${local}" var="tittle"/>
<fmt:message key="label.surname" bundle="${local}" var="surname"/>
<fmt:message key="label.name" bundle="${local}" var="name"/>
<fmt:message key="label.middleName" bundle="${local}" var="middleName"/>
<fmt:message key="label.country" bundle="${local}" var="country"/>
<fmt:message key="label.genre" bundle="${local}" var="genre"/>
<fmt:message key="label.dateEdition" bundle="${local}" var="dateEdition"/>
<fmt:message key="label.placeEdition" bundle="${local}" var="placeEdition"/>
<fmt:message key="label.publisher" bundle="${local}" var="publisher"/>
<fmt:message key="label.numberCopies" bundle="${local}" var="numberCopies"/>
<fmt:message key="label.add" bundle="${local}" var="add"/>

<html>
<head>
    <title><fmt:message key="label.addBook" bundle="${local}"/></title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css.css">

</head>
<body class="body-add-books">

<div class="container">

    <jsp:include page="../layout/layout.jsp"></jsp:include>
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <h1 class="header"><fmt:message key="label.addBook" bundle="${local}"/></h1>

    <form role="form" action="/Controller" method="get">
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterIsbn" bundle="${local}"/></label>
            <input type="text" name="isbn" class="form-control" placeholder="${ISBN}">
            <p class="help-block">13 знаков</p>
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterTittle" bundle="${local}"/></label>
            <input type="text" name="tittle" class="form-control" placeholder="${tittle}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterSurnameAuthor" bundle="${local}"/></label>
            <input type="text" name="surname" class="form-control" placeholder="${surname}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterNameAuthor" bundle="${local}"/></label>
            <input type="text" name="name" class="form-control" placeholder="${name}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterMiddleNameAuthor" bundle="${local}"/></label>
            <input type="text" name="middle_name" class="form-control" placeholder="${middleName}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterCountryAuthor" bundle="${local}"/></label>
            <input type="text" name="country" class="form-control" placeholder="${country}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterGenre" bundle="${local}"/></label>
            <input type="text" name="genre" class="form-control" placeholder="${genre}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterDateEdition" bundle="${local}"/></label>
            <input type="text" name="date_edition" class="form-control" placeholder="${dateEdition}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterPlaceEdition" bundle="${local}"/></label>
            <input type="text" name="place_edition" class="form-control" placeholder="${placeEdition}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterPublisher" bundle="${local}"/></label>
            <input type="text" name="publisher" class="form-control" placeholder="${publisher}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterNumberCopies" bundle="${local}"/></label>
            <input type="text" name="number_copies" class="form-control" placeholder="${numberCopies}">
        </div>
        <div class="form-group">
            <label class="label">Добавьте фото</label>
            <input type="file" name="image" class="form-control" placeholder="Фото">
        </div>
        <input type="hidden" name="command" value="add_book"/>
        <input type="submit" name="add_book" class="btn btn-success" value="${add}"/>
        <button type="reset" value="clear" onclick="clearForm()" class="btn btn-danger"><fmt:message key="label.clear" bundle="${local}"/></button>
    </form>
</div>
</body>
<script src = "/resource/js/bootstrap.js"></script>
</html>
