<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>

<html>
<head>
    <title><fmt:message key="label.forgotPassword" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>

<c:if test="${requestScope.messageChangePassword == 'error'}">
    <div class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.errorChangePassword" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${requestScope.messageRepeatPassword == 'error'}">
    <div class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.wrongRepeatPassword" bundle="${local}"/>
    </div>
</c:if>

<div class="container">
    <form role="form" action="/Controller" method="post" class="form-group">

        <div class="form-group">
            <label class="label"><fmt:message key="label.enterNumberTicket" bundle="${local}"/> </label>
            <input type="text" name="number_ticket" class="form-control"/><br/>
        </div>

        <div class="form-group">
            <label class="label"><fmt:message key="label.enterLogin" bundle="${local}"/> </label>
            <input type="text" name="login" class="form-control"/><br/>
        </div>

        <div class="form-group">
            <label class="label"><fmt:message key="label.enterNewPassword" bundle="${local}"/> </label>
            <input type="password" name="new_password" class="form-control"/><br/>
        </div>

        <div class="form-group">
            <label class="label"><fmt:message key="label.repeatNewPassword" bundle="${local}"/> </label>
            <input type="password" name="repeat_new_password" class="form-control"/><br/>
        </div>

        <input type="hidden" name="command" value="forgot_password"/>
        <input type="submit" value="Нажать" class="button"/>
    </form>

</div>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
</html>
