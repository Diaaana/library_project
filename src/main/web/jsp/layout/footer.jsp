<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>
<html>
<head>

    <link rel="stylesheet" type="text/css" href = "/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css">

</head>
<body>
<footer class="footer">
    <p>&copy; 2018 Diana Radomskaya</p>
</footer>
<form action="/Controller" method="get">
    <input type="hidden" name="command" value="locale">
    <input type="hidden" name="url" value="${pageContext.request.requestURI}">
    <div class="form-group footer-button">
        <button class="button-footer" type="submit" name="locale" value="en">EN</button>
        <button class="button-footer" type="submit" name="locale" value="ru">RU</button>
    </div>
</form>
</body>
<script src = "/resource/js/bootstrap.js"></script>
</html>
