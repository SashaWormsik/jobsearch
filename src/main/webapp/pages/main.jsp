<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="fragment/lang_and_user_role.jsp" %>

<html>
<head>
    <%@ include file="fragment/head.jsp" %>
    <title><fmt:message key="title.mainpage"/></title>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <%@include file="fragment/header.jsp" %>
    </header>

    <main class="main">
        ${MESSAGE}
        <br/>
          FJFJFJFLF::
        ${user.userName}
        ${user_location.city}
        ${user_location.country}
    </main>

    <footer class="footer">
        <%@include file="fragment/footer.jsp" %>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>