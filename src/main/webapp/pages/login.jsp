<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.chervyakovsky.jobsearch.controller.AttributeName" %>
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

        <div class="w3-modal-content w3-card-4" style="max-width:600px">

            <div class="w3-center">
                <c:if test="${not empty key_message}">
                    <div class="w3-center w3-red">
                        <fmt:message key="${key_message}" bundle="${ lg }"/>
                    </div>
                </c:if>
                <!-- IMAGE -->
                <img src="${pageContext.request.contextPath}/image/img.png" alt="Avatar"
                     style="width:30%" class="w3-circle w3-margin-top"/>
                <!--END IMAGE -->
            </div>

            <!-- LOGIN FORM-->
            <form class="w3-container" action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="login"/>
                <div class="w3-section">
                    <label>
                        <b>
                        <fmt:message key="label.user.login" bundle="${lg}"/>
                        </b>
                    </label>
                    <input class="w3-input w3-border w3-margin-bottom"
                           type="text" name="${ParameterName.LOGIN}"
                           placeholder=
                           <fmt:message key="label.user.login" bundle="${lg}"/> required
                           pattern="^[\w&&\D]\w{4,20}$"> <!-- PATTERN?? -->
                    <label>
                        <b><fmt:message key="label.user.password" bundle="${lg}"/></b>
                    </label>

                    <input class="w3-input w3-border"
                           type="password" name="${ParameterName.PASSWORD}"
                           placeholder=
                           <fmt:message key="label.user.password" bundle="${lg}"/> required
                           pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$"> <!-- PATTERN?? -->
                    <button class="w3-button w3-block w3-green w3-section w3-padding"
                            type="submit">
                        <fmt:message key="login.button" bundle="${lg}"/>
                    </button>

                </div>
            </form>
            <!--END LOGIN FORM-->

            <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
                <!-- REGISTRATION-->
                <span class="w3-left w3-hide-small">
                       <a href="#">Registration</a>
                    </span>
                <!-- FORGOT PASSWORD-->
                <span class="w3-right w3-hide-small">
                       <a href="#">Forgot password?</a>
                    </span>
            </div>
        </div>

    </main>

    <footer class="footer">
        <c:import url="fragment/footer.jsp"/>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>