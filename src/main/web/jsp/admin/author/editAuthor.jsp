<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>

<html>
<head>
    <title>Редактирование</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body>

<c:set var="author" scope="request" value="${requestScope.author}"/>

<div class="container">

    <jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>
    <jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>


    <form role="form" action="/Controller" method="post">
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterSurnameAuthor" bundle="${local}"/></label>
            <input type="text" name="surname" class="form-control" value="${author.surname}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterNameAuthor" bundle="${local}"/></label>
            <input type="text" name="name" class="form-control" value="${author.name}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterMiddleNameAuthor" bundle="${local}"/></label>
            <input type="text" name="middle_name" class="form-control" value="${author.middleName}">
        </div>
        <div class="form-group">
            <label class="label"><fmt:message key="label.enterMiddleNameAuthor" bundle="${local}"/></label>
            <input type="text" name="country" class="form-control" value="${author.countryBirth}">
        </div>
        <input type="hidden" name="command" value="update_author"/>
        <input type="hidden" name="id_author" value="${author.id}"/>
        <input type="submit" name="add_book" class="btn btn-success" value="Отредактировать"/>
        <button type="reset" value="clear" onclick="clearForm()" class="btn btn-danger"><fmt:message key="label.clear" bundle="${local}"/></button>
    </form>


</div>

</body>
</html>
