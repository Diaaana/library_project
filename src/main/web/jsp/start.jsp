<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale" prefix="label.">
<html>
<head>
    <title>Main page</title>
    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
</head>
<body>

<jsp:include page="layout/layout.jsp"></jsp:include>
<jsp:include page="layout/footer.jsp"></jsp:include>

<div class="container">
<form action="Controller" method="get">
    <label class="label">Логин:</label>
    <input type="text" name="login" value="" class="field"/><br />

    <label class="label">Пароль:</label>
    <input type="password" name="password" value="" class="field"/><br />
    <input type="hidden" name="command" value="login"/>
    <input type="submit" value="Вход" class="button"/><br />
</form>
</div>
</body>
</html>
</fmt:bundle>