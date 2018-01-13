<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="local"/>

<fmt:message key="label.registration" bundle="${local}" var="registration"/>
<fmt:message key="label.surname" bundle="${local}" var="surname"/>
<fmt:message key="label.name" bundle="${local}" var="name"/>
<fmt:message key="label.middleName" bundle="${local}" var="middleName"/>
<fmt:message key="label.age" bundle="${local}" var="age"/>
<fmt:message key="label.phone" bundle="${local}" var="phone"/>
<fmt:message key="label.mail" bundle="${local}" var="mail"/>
<fmt:message key="label.login" bundle="${local}" var="login"/>
<fmt:message key="label.password" bundle="${local}" var="password"/>
<fmt:message key="label.repeatPassword" bundle="${local}" var="repeatPassword"/>
<fmt:message key="label.toRegister" bundle="${local}" var="toRegistr"/>
<fmt:message key="label.clear" bundle="${local}" var="clear"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>${registration}</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css">

</head>
<body class="body-registration">

<div class="container">

    <h1 class="header">${registration}</h1>

        <form role="form" action="/Controller" name="form" onsubmit="return validationForm();" method="get">
            <div class="form-group">
                <input type="text" name="surname" placeholder="${surname}" class="form-control field"><br/>
            </div>
            <div class="form-group">
                <input type="text" name="name" placeholder="${name}" class="form-control field"><br/>
            </div>
            <div class="form-group">
                <input type="text" name="middle_name" placeholder="${middleName}" class="form-control field"><br/>
            </div>
            <div class="form-group">
                <input type="text" name="age" placeholder="${age}" class="form-control field"><br/>
            </div>
            <div class="form-group">
                <input type="text" name="phone" placeholder="${phone}" class="form-control field"><br/>
            </div>
            <div class="form-group">
                <input type="text" name="mail" placeholder="${mail}" class="form-control field"><br/>
            </div>
            <div class="form-group">
                <input type="text" name="login" placeholder="${login}" class="form-control field"><br/>
            </div>
            <div class="form-group">
                <input type="password" name="password" placeholder="${password}" class="form-control field"><br/>
            </div>
            <div class="form-group">
                <input type="password" name="password2" placeholder="${repeatPassword}" class="form-control field"><br/>
            </div>
            <div class="form-group">
                <input type="hidden" name="command" value="registration"/>
                <input type="submit" name="registration" value="${toRegistr}" class="button-registr">
                <button type="reset" value="clear" onclick="clearForm()" class="button-registr">${clear}</button>
            </div>
        </form>

<jsp:include page="../layout/footer.jsp"></jsp:include>
</div>

</body>
<script src = "/resource/js/script.js"></script>
</html>
