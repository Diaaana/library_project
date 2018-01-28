<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>
<fmt:message key="label.logIn" bundle="${local}" var="logIn"/>
<html>
<head>

    <title><fmt:message key="label.library" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body class="body">

<div class="container">

    <jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>
    <jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>

    <form role="form" action="Controller" method="post" class="form-login">

        <div class="form-group">
            <label class="label"><fmt:message key="label.login" bundle="${local}"/></label>
            <input type="text" name="login" class="form-control"/><br/>
        </div>

        <div class="form-group">
            <label class="label"><fmt:message key="label.password" bundle="${local}"/></label>
            <input type="password" name="password" class="form-control"/><br/>
        </div>

        <input type="hidden" name="command" value="login"/>
        <input type="submit" value="${logIn}" class="btn btn-lg button-login"/>

    </form>
</div>
</body>
<script src="${pageContext.request.contextPath}/resource/js/reader/script.js"></script>
</html>
