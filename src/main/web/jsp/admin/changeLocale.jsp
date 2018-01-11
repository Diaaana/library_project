<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href = "/resource/css/style.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "/resource/css/bootstrap-theme.css">

</head>
<body>
<form action="/Controller" method="get">
    <input type="hidden" name="command" value="local" />
    <input type="hidden" name="back" value="/jsp/admin/changeLocale.jsp">
    <input type="hidden" name="local" value="ru" />
    <button type="submit" class="linkButton">${rusButton}</button>
</form>

<form action="/Controller" method="get">
    <input type="hidden" name="command" value="local" />
    <input type="hidden" name="back" value="/jsp/admin/changeLocale.jsp">
    <input type="hidden" name="local" value="en" />
    <button type="submit" class="linkButton">${engButton}</button>
</form>
</body>
<script src = "/resource/js/bootstrap.js"></script>
</html>
