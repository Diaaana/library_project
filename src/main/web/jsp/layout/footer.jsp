<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>
<html>
<head>
</head>
<body class="body">
<footer class="footer">
    <p><fmt:message key="label.footer" bundle="${local}"/></p>
</footer>
</body>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
</html>
