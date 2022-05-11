<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${sessionScope.language == null ? 'en_US' : sessionScope.language }"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="language" var="lg"/>

<html>
<head>
    <title><fmt:message key="title.login" bundle="${lg}"/></title>
    <c:import url="fragment/head.jsp"/>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <c:import url="fragment/menu.jsp"/>
    </header>

    <main class="main">

    </main>

    <footer class="footer">
        <c:import url="fragment/footer.jsp"/>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>