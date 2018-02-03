<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>

<fmt:message key="label.edit" bundle="${local}" var="edit"/>

<html>
<head>
    <title><fmt:message key="label.changePassword" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>

<c:if test="${sessionScope.messageWrongTruePassword == 'error'}">
    <div id="error" class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.wrongTruePassword" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${sessionScope.messageWrongRepeatPassword == 'error'}">
    <div id="error" class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.wrongRepeatPassword" bundle="${local}"/>
    </div>
</c:if>

<c:set var="user" scope="session" value="${sessionScope.reader}"/>

<div class="container">
    <form role="form" action="/Controller" method="post" class="form-group">

        <div class="form-group">
            <label class="label"><fmt:message key="label.enterOldPassword" bundle="${local}"/> </label>
            <input type="text" name="old_password" class="form-control"/><br/>
        </div>

        <div class="form-group">
            <label class="label"><fmt:message key="label.enterNewPassword" bundle="${local}"/> </label>
            <input type="password" name="new_password" class="form-control"/><br/>
        </div>

        <div class="form-group">
            <label class="label"><fmt:message key="label.repeatNewPassword" bundle="${local}"/> </label>
            <input type="password" name="repeat_new_password" class="form-control"/><br/>
        </div>

        <input type="hidden" name="number_ticket" value="${user.numberTicket}"/>
        <input type="hidden" name="command" value="change_password"/>
        <input type="submit" value="${edit}" class="button"/>
    </form>

</div>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
</html>
