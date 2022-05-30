<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.chervyakovsky.jobsearch.controller.ParameterName" %>

<div class="w3-modal-content w3-card-4 w3-auto" style="max-width:600px">
    <div class="w3-center w3-padding">
        <c:if test="${empty user_role}">
        <span onclick="document.getElementById('company').style.display='none'
                       document.getElementById('buttonWorker').style.display='block'"
              class="w3-button w3-xlarge w3-transparent w3-display-topright"
              title="Close">
            ×
        </span>
        </c:if>
        <h1><fmt:message key="title.registration"/></h1>
        <c:if test="${not empty exist_login_or_email}">
            <div class="w3-red w3-block">
                <fmt:message key="message.exist.login.email"/>
            </div>
        </c:if>
    </div>
    <form id="form2" class="w3-container" action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="registration"/>
        <input type="hidden" name="${ParameterName.USER_ROLE}" value="COMPANY"/>
        <div class="w3-section">

            <!-- LOGIN -->
            <c:if test="${incorrect_login == true}">
                <div class="w3-left w3-red w3-block">
                    <fmt:message key="form.input.title.login.pattern"/>
                </div>
                <br/>
            </c:if>
            <label>
                <b><fmt:message key="label.user.login"/></b>
            </label>
            <input class="w3-input w3-border w3-margin-bottom"
                   type="text" name="${ParameterName.USER_LOGIN}"
                   placeholder="<fmt:message key="label.user.login"/>" required
                   pattern="^[@\p{Alpha}]\w{4,20}$"
                   title="<fmt:message key="form.input.title.login.pattern"/>"
                   value="${user_login}"/>

            <!-- USER EMAIL-->
            <c:if test="${incorrect_email == true}">
                <div class="w3-left w3-red w3-block">
                    <fmt:message key="form.input.title.email.pattern"/>
                </div>
                <br/>
            </c:if>
            <label>
                <b><fmt:message key="label.user.email"/></b>
            </label>
            <input class="w3-input w3-border w3-margin-bottom"
                   type="email" name="${ParameterName.USER_EMAIL}"
                   placeholder="<fmt:message key="label.user.email"/>" required
                   pattern="^[\p{Alpha}\p{Digit}_!#$%&'*+/=?`{|}~^.-]+@[\p{Alpha}\p{Digit}.-]+$"
                   title="<fmt:message key="form.input.title.email.pattern"/>"
                   value="${user_email}"/>

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
                   pattern="^(?=.*[\p{Alpha}])(?=.*\d)[\p{Alpha}\d]{8,20}$"
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
                   pattern="^(?=.*[\p{Alpha}])(?=.*\d)[\p{Alpha}\d]{8,20}$"
                   title="<fmt:message key="form.input.title.password.pattern"/>"/>

            <!-- COMPANY NAME-->
            <c:if test="${incorrect_user_name == true}">
                <div class="w3-left w3-red w3-block">
                    <fmt:message key="form.input.title.name.pattern"/>
                </div>
                <br/>
            </c:if>
            <label>
                <b><fmt:message key="label.user.companyname"/></b>
            </label>
            <input class="w3-input w3-border w3-margin-bottom"
                   type="text" name="${ParameterName.USER_NAME}"
                   placeholder="<fmt:message key="label.user.companyname"/>" required
                   pattern="^[\p{L}][\p{L}`-]?[\p{L}`]{1,20}$"
                   title="<fmt:message key="form.input.title.name.pattern"/>"
                   value="${user_name}"/>

            <!-- BUTTON -->
            <button class="w3-button w3-block w3-green w3-section w3-padding" form="form2"
                    type="submit" formmethod="post" formaction="${pageContext.request.contextPath}/controller">
                <fmt:message key="registration.button"/>
            </button>
        </div>
    </form>
</div>