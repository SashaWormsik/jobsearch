<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.chervyakovsky.jobsearch.controller.ParameterName" %>

<div class="w3-modal-content w3-card-4" style="max-width:600px">
    <div class="w3-center">
        <c:if test="${not empty key_message}">
            <div class="w3-center w3-red"> <!-- привести в порядок!!!!!-->
                <fmt:message key="${key_message}"/>
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
                <b><fmt:message key="label.user.login"/></b>
            </label>
            <input class="w3-input w3-border w3-margin-bottom"
                   type="text" name="${ParameterName.USER_LOGIN}"
                   placeholder=
                   <fmt:message key="label.user.login"/> required
                   pattern="^[\w&&\D]\w{4,20}$"> <!-- PATTERN?? -->

            <label>
                <b><fmt:message key="label.user.password"/></b>
            </label>
            <input class="w3-input w3-border"
                   type="password" name="${ParameterName.USER_PASSWORD}"
                   placeholder=
                   <fmt:message key="label.user.password"/> required
                   pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$"> <!-- PATTERN?? -->

            <button class="w3-button w3-block w3-green w3-section w3-padding"
                    type="submit">
                <fmt:message key="login.button"/>
            </button>
        </div>
    </form>
    <!--END LOGIN FORM-->

    <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
        <!-- REGISTRATION-->
        <span class="w3-left w3-hide-small">
                       <a href="${pageContext.request.contextPath}/pages/registration.jsp">
                           <fmt:message key="label.registration"/>
                       </a>
        </span>
        <!-- FORGOT PASSWORD-->
        <span class="w3-right w3-hide-small">
                       <a href="#"><fmt:message key="label.forgotpassword"/></a>
        </span>
    </div>
</div>