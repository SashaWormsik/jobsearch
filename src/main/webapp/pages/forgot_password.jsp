<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtag" %>

<%@include file="fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="fragment/head.jsp" %>
    <title><fmt:message key="title.forgot_password.page"/></title>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <%@include file="fragment/header.jsp" %>
    </header>

    <main class="main">
        <div class="w3-auto w3-padding" style="max-width:600px">
            <div class="w3-center">
                <h5><fmt:message key="text.forgot_password.page"/></h5>
            </div>
        </div>


        <div class="w3-white w3-card-4 w3-auto" style="max-width:600px">
            <form class="w3-container" action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="forgot_password"/>
                <div class="w3-section">
                    <c:if test="${forgot_password == false}">
                        <div class="w3-left w3-red w3-block">
                            <fmt:message key="message.forgot_password.not.send"/>
                        </div>
                        <br/>
                    </c:if>
                    <c:if test="${forgot_password == true}">
                        <div class="w3-left w3-blue w3-block">
                            <fmt:message key="message.forgot_password.send"/>
                        </div>
                        <br/>
                    </c:if>
                    <c:if test="${incorrect_email == true}">
                        <div class="w3-left w3-red w3-block">
                            <fmt:message key="message.incorrect.email.pattern"/>
                        </div>
                        <br/>
                    </c:if>
                    <label>
                        <b><fmt:message key="label.user.email"/></b>
                    </label>
                    <input class="w3-input w3-border w3-margin-bottom"
                           type="text"
                           value="${user_email}"
                           name="${ParameterName.USER_EMAIL}"
                           placeholder="<fmt:message key="label.user.email"/>" required
                           pattern="^[A-Za-z0-9_!#$%&'*+/=?`}{|~^.-]+@[A-Za-z0-9.-]+$"
                           title="<fmt:message key="message.incorrect.email.pattern"/>"/>

                    <button class="w3-button w3-block w3-green w3-section w3-padding"
                            type="submit">
                        <fmt:message key="button.send.email"/>
                    </button>
                </div>
            </form>
        </div>

    </main>

    <footer class="footer">
        <ctg:footer/>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>