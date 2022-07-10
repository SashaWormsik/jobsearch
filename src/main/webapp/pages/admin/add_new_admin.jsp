<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtag" %>

<%@include file="../fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="../fragment/head.jsp" %>
    <title><fmt:message key="title.add_new_admin.page"/></title>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <%@include file="../fragment/header.jsp" %>
    </header>

    <main class="main">
        <div class="w3-row-padding">
            <div class="w3-quarter w3-section" style=" word-break: break-all">
                <div class="w3-card-4 w3-center w3-white">
                    <div class="w3-container">
                        <img src="${pageContext.request.contextPath}/image/img.png" alt="Avatar"
                             class="w3-circle w3-margin-top" style="width:100%"/>
                        <h4><b>${user.userName} ${user.userSurName}</b></h4>
                    </div>
                </div>
            </div>

            <div class="w3-threequarter w3-section" style=" word-break: break-all">
                <div class="w3-card-4 w3-white">

                    <div class="w3-container w3-center w3-green">
                        <h2><fmt:message key="text.add_new_admin.page"/></h2>
                    </div>
                    <c:if test="${temp_successful_registration}">
                        <div class="w3-center w3-blue w3-block">
                            <fmt:message key="message.successful.registration"/>
                        </div>
                        <br/>
                    </c:if>
                    <c:if test="${exist_login_or_email}">
                        <div class="w3-center w3-red w3-block">
                            <fmt:message key="message.exist.login.email"/>
                        </div>
                        <br/>
                    </c:if>
                    <div class="w3-container">
                        <form id="form2" class="w3-container" action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="create_new_admin"/>
                            <input type="hidden" name="${ParameterName.USER_ROLE}" value="ADMIN"/>
                            <div class="w3-section">

                                <!-- LOGIN -->
                                <c:if test="${incorrect_login == true}">
                                    <div class="w3-left w3-red w3-block">
                                        <fmt:message key="message.incorrect.login.pattern"/>
                                    </div>
                                    <br/>
                                </c:if>
                                <label>
                                    <b><fmt:message key="label.user.login"/></b>
                                </label>
                                <input class="w3-input w3-border w3-margin-bottom"
                                       type="text" name="${ParameterName.USER_LOGIN}"
                                       placeholder="<fmt:message key="label.user.login"/>" required
                                       pattern="^[@A-Za-z]\w{4,20}$"
                                       title="<fmt:message key="message.incorrect.login.pattern"/>"
                                       value="${user_login}"/>

                                <!-- USER EMAIL-->
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
                                       type="email" name="${ParameterName.USER_EMAIL}"
                                       placeholder="<fmt:message key="label.user.email"/>" required
                                       pattern="^[A-Za-z0-9_!#$%&'*+/=?`}{|~^.-]+@[A-Za-z0-9.-]+$"
                                       title="<fmt:message key="message.incorrect.email.pattern"/>"
                                       value="${user_email}"/>

                                <!-- PASSWORD -->
                                <c:if test="${incorrect_password == true}">
                                    <div class="w3-left w3-red w3-block">
                                        <fmt:message key="message.incorrect.password.pattern"/>
                                    </div>
                                    <br/>
                                </c:if>
                                <label>
                                    <b><fmt:message key="label.user.password"/></b>
                                </label>
                                <input class="w3-input w3-border w3-margin-bottom"
                                       type="password" name="${ParameterName.CREDENTIAL_PASSWORD}"
                                       placeholder="<fmt:message key="label.user.password"/>" required
                                       pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$"
                                       title="<fmt:message key="message.incorrect.password.pattern"/>"/>

                                <!-- PASSWORD CONFIRM-->
                                <c:if test="${incorrect_confirm_password == true}">
                                    <div class="w3-left w3-red w3-block">
                                        <fmt:message key="message.incorrect.confirm.password.pattern"/>
                                    </div>
                                    <br/>
                                </c:if>
                                <label>
                                    <b><fmt:message key="label.user.password.confirm"/></b>
                                </label>
                                <input class="w3-input w3-border w3-margin-bottom"
                                       type="password" name="${ParameterName.CREDENTIAL_CONFIRM_PASSWORD}"
                                       placeholder="<fmt:message key="label.user.password.confirm"/>" required
                                       pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$"
                                       title="<fmt:message key="message.incorrect.password.pattern"/>"/>

                                <!-- ADMIN NAME-->
                                <c:if test="${incorrect_user_name == true}">
                                    <div class="w3-left w3-red w3-block">
                                        <fmt:message key="message.incorrect.name.pattern"/>
                                    </div>
                                    <br/>
                                </c:if>
                                <label>
                                    <b><fmt:message key="label.user.name"/></b>
                                </label>
                                <input class="w3-input w3-border w3-margin-bottom"
                                       type="text" name="${ParameterName.USER_NAME}"
                                       placeholder="<fmt:message key="label.user.name"/>" required
                                       pattern="^[\p{L}][\p{L}`-]?[\p{L}`]{1,20}$"
                                       title="<fmt:message key="message.incorrect.name.pattern"/>"
                                       value="${user_name}"/>

                                <!-- ADMIN SURNAME-->
                                <c:if test="${incorrect_user_surname == true}">
                                    <div class="w3-left w3-red w3-block">
                                        <fmt:message key="message.incorrect.name.pattern"/>
                                    </div>
                                    <br/>
                                </c:if>
                                <label>
                                    <b><fmt:message key="label.user.surname"/></b>
                                </label>
                                <input class="w3-input w3-border w3-margin-bottom"
                                       type="text"
                                       name="${ParameterName.USER_SURNAME}"
                                       placeholder="<fmt:message key="label.user.surname"/>" required
                                       pattern="^[\p{L}][\p{L}`-]?[\p{L}`]{1,20}$"
                                       title="<fmt:message key="message.incorrect.name.pattern"/>"
                                       value="${user_surname}"/>

                                <!-- BUTTON -->
                                <button class="w3-button w3-block w3-green w3-section w3-padding" form="form2"
                                        type="submit" formmethod="post" formaction="${pageContext.request.contextPath}/controller">
                                    <fmt:message key="button.save"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer class="footer">
        <ctg:footer/>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>