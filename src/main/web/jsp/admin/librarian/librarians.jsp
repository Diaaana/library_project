<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="suc" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="err" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="message.emptyData" bundle="${local}" var="empyData"/>

<html>
<head>
    <title><fmt:message key="label.librarians" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<suc:success successMessage="${messageDeleteLibrarian}"/>

<div class="container">
    <c:choose>
        <c:when test="${not empty sessionScope.librarians}">
            <form action="${pageContext.request.contextPath}/Controller" method="post">
                <table class="table table-hover">

                    <thead class="table-thead">
                    <tr>
                        <th><fmt:message key="label.surname" bundle="${local}"/></th>
                        <th><fmt:message key="label.name" bundle="${local}"/></th>
                        <th><fmt:message key="label.middleName" bundle="${local}"/></th>
                        <th><fmt:message key="label.loginValue" bundle="${local}"/></th>
                        <th></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="librarian" items="${librarians}">
                        <tr>
                            <td>${librarian.surname}</td>
                            <td>${librarian.name}</td>
                            <td>${librarian.middleName}</td>
                            <td>${librarian.login}</td>
                            <td>
                                <a class="a-function"
                                   href="${pageContext.request.contextPath}/Controller?id_librarian=${librarian.id}&command=delete_librarian"><fmt:message
                                        key="label.delete" bundle="${local}"/>
                                    <span class="glyphicon glyphicon-trash"></span></a>
                                <a class="a-function"
                                   href="${pageContext.request.contextPath}/Controller?id_librarian=${librarian.id}&command=edit_librarian"><fmt:message
                                        key="label.edit" bundle="${local}"/> <span
                                        class="glyphicon glyphicon-pencil"></span></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </c:when>
        <c:otherwise>
            <err:error errorMessage="${empyData}"/>
        </c:otherwise>
    </c:choose>

</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>
