<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragment/lang_and_user.jsp"%>

<html>
<head>
    <%@ include file="fragment/head.jsp" %>
    <title><fmt:message key="title.login.page"/></title>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <%@include file="fragment/header.jsp" %>
    </header>

    <main class="main">
        <%@ include file="fragment/loginForm.jsp"%>
    </main>

    <footer class="footer">
        <%@include file="fragment/footer.jsp" %>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>