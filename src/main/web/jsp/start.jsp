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
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>

<c:if test="${requestScope.messageRegAdmin == 'success'}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successRegAdmin" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${requestScope.messageRegUser == 'success'}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successRegReader" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${requestScope.messageRegLibrarian == 'success'}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successRegLibrarian" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${requestScope.messageChangePassword == 'success'}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successChangePassword" bundle="${local}"/>
    </div>
</c:if>

<div class="container">

    <c:if test="${requestScope.messageLogin == 'error'}">
        <div class="alert alert-danger pos-alert">
            <a href="#" class="close" data-dismiss="alert">×</a>
            <fmt:message key="message.errorLogin" bundle="${local}"/>
        </div>
    </c:if>

    <form role="form" action="/Controller" method="post" class="form-login">
        <div class="form-group">
            <label class="label"><fmt:message key="label.login" bundle="${local}"/></label>
            <input type="text" name="login" class="form-control"/><br/>
        </div>

        <div class="form-group">
            <label class="label"><fmt:message key="label.password" bundle="${local}"/></label>
            <input type="password" name="password" class="form-control"/><br/>
        </div>

        <input type="hidden" name="command" value="login"/>
        <input type="submit" value="${logIn}" class="button"/>
    </form>

    <a href="${pageContext.request.contextPath}/jsp/forgotPassword.jsp" class="a-forgot-password"><fmt:message key="label.questionForgotPassword" bundle="${local}"/></a>

</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
</html>
