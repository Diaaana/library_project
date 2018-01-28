<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="user" scope="session" value="${sessionScope.reader}"/>

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
</head>
<body>
<div class="container">

    <c:if test="${sessionScope.role == null || sessionScope.role == 'guest'}">
        <a href="${pageContext.request.contextPath}/jsp/user/registration.jsp"
           class="a-registration">${registration}</a>
    </c:if>

    <c:if test="${sessionScope.role == 'admin'}">
    <nav role="navigation" class="nav-justified">
        <ul class="nav menu-style">
            <li>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="show_books"/>
                    <input type="submit" value="${books}" class="nav-btn"/>
                </form>
            </li>
            <li>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="show_authors"/>
                    <input type="submit" value="${authors}" class="nav-btn"/>
                </form>
            </li>
            <li>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="show_librarians"/>
                    <input type="submit" value="${librarians}" class="nav-btn"/>
                </form>
            </li>
            <li>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="show_readers"/>
                    <input type="submit" value="${readers}" class="nav-btn"/>
                </form>
            </li>
            <li>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value="${logout}" class="nav-btn"/>
                </form>
            </li>
        </ul>
        </c:if>

        <c:if test="${sessionScope.role == 'reader'}">
            <ul class="nav menu-style">
            <li>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="get_books"/>
                    <input type="submit" value="${books}" class="nav-btn"/>
                </form>
            </li>
            <li>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="get_personal_orders"/>
                    <input type="hidden" name="number_ticket" value="${user.numberTicket}"/>
                    <input type="submit" value="Корзина" class="nav-btn"/><br/>
                </form>
            </li>
            <li>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="account"/>
                    <input type="hidden" name="number_ticket" value="${user.numberTicket}"/>
                    <input type="submit" value="Личный кабинет" class="nav-btn"/><br/>
                </form>
            </li>
            <li>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value="${logout}" class="nav-btn"/><br/>
                </form>
            </li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.role == 'librarian'}">
            <ul class="nav menu-style">
                <li>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="show_orders"/>
                        <input type="submit" value="Каталог книг" class="nav-btn"/>
                    </form>
                </li>
                <li>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="show_orders"/>
                        <input type="submit" value="Заказы" class="nav-btn"/>
                    </form>
                </li>
                <li>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="logout"/>
                        <input type="submit" value="${logout}" class="nav-btn"/><br/>
                    </form>
                </li>
            </ul>
        </c:if>
    </nav>
</div>
</body>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
</html>
