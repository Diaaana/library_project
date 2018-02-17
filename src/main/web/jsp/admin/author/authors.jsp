<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>
<html>
<head>
    <title><fmt:message key="label.authors" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"></jsp:include>

<c:if test="${requestScope.messageEdit == 'success'}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successEditAuthor" bundle="${local}"/>
    </div>
</c:if>

<c:if test="${requestScope.messageDelete == 'success'}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert">×</a>
        <fmt:message key="message.successDeleteAuthor" bundle="${local}"/>
    </div>
</c:if>

<div class="container">

    <form action="Controller" method="post">
        <table class="table table-hover table-condensed">

            <thead>
            <tr>
                <th><fmt:message key="label.surnameAuthor" bundle="${local}"/></th>
                <th><fmt:message key="label.nameAuthor" bundle="${local}"/></th>
                <th><fmt:message key="label.middleNameAuthor" bundle="${local}"/></th>
                <th><fmt:message key="label.country" bundle="${local}"/></th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="author" items="${authors}">
                <tr>
                    <td>${author.surname}</td>
                    <td>${author.name}</td>
                    <td>${author.middleName}</td>
                    <td>${author.countryBirth}</td>
                    <td>
                        <div class="functions">
                            <a class="a-function"
                               href="/Controller?id_author=${author.id}&command=delete_author"><fmt:message
                                    key="label.delete" bundle="${local}"/> <span
                                    class="glyphicon glyphicon-trash"></span></a>
                            <a class="a-function"
                               href="/Controller?id_author=${author.id}&command=edit_author"><fmt:message
                                    key="label.edit" bundle="${local}"/> <span
                                    class="glyphicon glyphicon-pencil"></span></a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </form>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
</html>
