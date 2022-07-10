<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.chervyakovsky.jobsearch.controller.ParameterName" %>


<div class="w3-modal-content w3-card-4" style="max-width:600px">
    <div class="w3-center">
        <c:if test="${incorrect_login_or_password == true}">
            <div class="w3-center w3-red">
                <fmt:message key="message.login.incorrect"/>
            </div>
        </c:if>
        <c:if test="${account_is_blocked == true}">
            <div class="w3-center w3-red">
                <fmt:message key="message.login.account.blocked"/>
            </div>
        </c:if>

        <c:if test="${activate_user == true}">
            <div class="w3-center w3-blue">
                <fmt:message key="message.activation.successful"/>
            </div>
        </c:if>
        <c:if test="${activate_user == false}">
            <div class="w3-center w3-red">
                <fmt:message key="message.activation.unsuccessful"/>
            </div>
        </c:if>

        <c:if test="${temp_successful_registration == true}">
            <div class="w3-center w3-blue">
                <fmt:message key="message.successful.registration"/>
            </div>
        </c:if>

        <c:if test="${temp_password_changed == true}">
            <div class="w3-center w3-blue">
                <fmt:message key="message.password_changed"/>
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
                <b class="w3-text-black"><fmt:message key="label.user.login"/></b>
            </label>
            <input class="w3-input w3-border w3-margin-bottom"
                   type="text" name="${ParameterName.USER_LOGIN}"
                   placeholder="<fmt:message key="label.user.login"/>" required
                   pattern="^[@A-Za-z]\w{4,20}$"
                   title="<fmt:message key="message.incorrect.login.pattern"/>"/>

            <label>
                <b class="w3-text-black"><fmt:message key="label.user.password"/></b>
            </label>
            <input class="w3-input w3-border"
                   type="password" name="${ParameterName.CREDENTIAL_PASSWORD}"
                   placeholder="<fmt:message key="label.user.password"/>" required
                   pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$"
                   title="<fmt:message key="message.incorrect.password.pattern"/>"/>

            <button class="w3-button w3-block w3-green w3-section w3-padding"
                    type="submit">
                <fmt:message key="button.login"/>
            </button>
        </div>
    </form>
    <!--END LOGIN FORM-->

    <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
        <!-- TO REGISTRATION-->
        <span class="w3-left w3-hide-small">
                       <a href="${pageContext.request.contextPath}/pages/registration.jsp">
                           <fmt:message key="button.registration.login"/>
                       </a>
        </span>
        <!-- TO FORGOT PASSWORD-->
        <span class="w3-right w3-hide-small">
                       <a href="${pageContext.request.contextPath}/pages/forgot_password.jsp">
                           <fmt:message key="button.forgotpassword.login"/>
                       </a>
        </span>
    </div>
</div>
