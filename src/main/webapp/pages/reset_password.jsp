<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="fragment/head.jsp" %>
    <title><fmt:message key="title.reset_password"/></title>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <%@include file="fragment/header.jsp" %>
    </header>

    <main class="main">
        <div class="w3-auto w3-padding" style="max-width:600px">
            <div class="w3-center">
                <h5><fmt:message key="text.reset_password.page"/></h5>
            </div>
        </div>


        <div class="w3-white w3-card-4 w3-auto" style="max-width:600px">
            <form class="w3-container" action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="update_password"/>
                <input type="hidden" name="${ParameterName.USER_TOKEN}" value="${user_token}"/>
                <div class="w3-section">

                    <!-- PASSWORD -->
                    <c:if test="${incorrect_password == true}">
                        <div class="w3-left w3-red w3-block">
                            <fmt:message key="form.input.title.password.pattern"/>
                        </div>
                        <br/>
                    </c:if>
                    <label>
                        <b><fmt:message key="label.user.password"/></b>
                    </label>
                    <input class="w3-input w3-border w3-margin-bottom"
                           type="password" name="${ParameterName.CREDENTIAL_PASSWORD}"
                           placeholder="<fmt:message key="label.user.password"/>" required
                           pattern="^(?=.*[\p{A-Za-z}])(?=.*\d)[\p{A-Za-z}\d]{8,20}$"
                           title="<fmt:message key="form.input.title.password.pattern"/>"/>

                    <!-- PASSWORD CONFIRM-->
                    <c:if test="${incorrect_confirm_password == true}">
                        <div class="w3-left w3-red w3-block">
                            <fmt:message key="form.input.title.confirm.password.pattern"/>
                        </div>
                        <br/>
                    </c:if>
                    <label>
                        <b><fmt:message key="label.user.password.confirm"/></b>
                    </label>
                    <input class="w3-input w3-border w3-margin-bottom"
                           type="password" name="${ParameterName.CREDENTIAL_CONFIRM_PASSWORD}"
                           placeholder="<fmt:message key="label.user.password.confirm"/>" required
                           pattern="^(?=.*[\p{A-Za-z}])(?=.*\d)[\p{A-Za-z}\d]{8,20}$"
                           title="<fmt:message key="form.input.title.password.pattern"/>"/>

                    <button class="w3-button w3-block w3-green w3-section w3-padding"
                            type="submit">
                        <fmt:message key="reset_password.button.update"/>
                    </button>
                </div>
            </form>
        </div>

    </main>

    <footer class="footer">
        <%@include file="fragment/footer.jsp" %>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>