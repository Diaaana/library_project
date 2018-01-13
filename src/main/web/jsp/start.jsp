<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    
    <title>Main page</title>
    <link rel="shortcut icon" href="resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/style.css">
</head>
<body>
<div class="form">
<form action="Controller" method="get">
    Введите логин:
    <input type="text" name="login" value="" class="field"/><br />
    Введите пароль:
    <input type="password" name="password" value="" class="field"/><br />
    <input type="hidden" name="command" value="login"/>
    <input type="submit" value="Вход" class="button"/><br />
    <input type="button" value="Регистрация" onclick="javascript:window.location='jsp/registration.jsp'" class="button"/><br />
</form>
</div>
</body>
</html>
