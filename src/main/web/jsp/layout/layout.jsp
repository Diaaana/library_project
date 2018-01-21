<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="reader" scope="session" value="${sessionScope.reader}"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>

<fmt:message key="label.registration" bundle="${local}" var="registration"/>
<fmt:message key="label.main" bundle="${local}" var="main"/>
<fmt:message key="label.books" bundle="${local}" var="books"/>
<fmt:message key="label.authors" bundle="${local}" var="authors"/>
<fmt:message key="label.librarians" bundle="${local}" var="librarians"/>
<fmt:message key="label.readers" bundle="${local}" var="readers"/>
<fmt:message key="label.account" bundle="${local}" var="account"/>
<fmt:message key="label.logout" bundle="${local}" var="logout"/>

<html>
<head>
    <title><fmt:message key="label.library" bundle="${local}"/></title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body>
<div class="container">

    <c:if test="${sessionScope.role == null || sessionScope.role == 'guest'}">
        <a href="${pageContext.request.contextPath}/jsp/user/registration.jsp" class="a-registration">${registration}</a>
    </c:if>

    <nav class="my-nav">

        <c:if test="${sessionScope.role == 'admin'}">

            <div class="btn-group nav-button-group">
                <input type="submit" class="btn btn-link nav-btn" onclick='location.href="/jsp/admin/main.jsp"' value="${main}"/>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="show_books"/>
                    <input type="submit" value="${books}" class="btn btn-link nav-btn"/>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="show_authors"/>
                    <input type="submit" value="${authors}" class="btn btn-link nav-btn"/>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="show_librarians"/>
                    <input type="submit" value="${librarians}" class="btn btn-link nav-btn"/>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="show_readers"/>
                    <input type="submit" value="${readers}" class="btn btn-link nav-btn"/>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value="${logout}" class="btn btn-link nav-btn"/>
                </form>
            </div>
        </c:if>

        <c:if test="${sessionScope.role == 'user'}">
            <div class="btn-group">
                <input type="submit" class="btn btn-link nav-btn" onclick='location.href="/jsp/user/main.jsp"' value="${main}"/>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="get_books"/>
                    <input type="submit" value="${books}" class="btn btn-link nav-btn"/>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="get_personal_orders"/>
                    <input type="hidden" name="number_ticket" value="${reader.numberTicket}"/>
                    <input type="submit" value="Корзина" class="btn btn-link nav-btn"/><br/>
                </form>
                <button class="btn btn-link nav-btn" onclick='location.href="/jsp/user/account.jsp"'>${account}</button>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value="${logout}" class="button"/><br/>
                </form>
            </div>
        </c:if>

        <c:if test="${sessionScope.role == 'librarian'}">
            <div class="btn-group">
                <button class="btn btn-link">${main}</button>
                <button class="btn btn-link">${books}</button>
                <button class="btn btn-link">${authors}</button>
                <button class="btn btn-link">${account}</button>
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value="${logout}" class="button"/><br/>
                </form>
            </div>
        </c:if>
    </nav>
</div>
</body>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
</html>
