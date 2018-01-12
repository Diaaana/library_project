<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>
<html>
<head>

    <title>Библиотека</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css">

</head>
<body>

<jsp:include page="layout/layout.jsp"></jsp:include>
<jsp:include page="layout/footer.jsp"></jsp:include>

<div class="container">
<form role="form" action="Controller" method="get" class="form-login">

    <div class="form-group">
    <label class="label"><fmt:message key="label.login" bundle="${local}"/></label>
    <input type="text" name="login" class="form-control"/><br />
    </div>

    <div class="form-group">
    <label class="label">Пароль:</label>
    <input type="password" name="password" class="form-control"/><br />
    </div>

    <input type="hidden" name="command" value="login"/>
    <input type="submit" value="Вход" class="btn btn-primary btn-lg btn-success"/><br />

</form>
</div>
</body>
<script src = "/resource/js/script.js"></script>
</html>
