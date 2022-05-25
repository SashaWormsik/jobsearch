<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.chervyakovsky.jobsearch.controller.ParameterName" %>

<div class="w3-modal-content w3-card-4 w3-auto" style="max-width:600px">

    <div class="w3-center w3-padding">
        <span onclick="document.getElementById('company').style.display='none'
                       document.getElementById('buttonWorker').style.display='block'"
              class="w3-button w3-xlarge w3-transparent w3-display-topright"
              title="Close Modal">
            ×
        </span>
        <h1><fmt:message key="title.registration"/></h1>
    </div>

    <form id="form2" class="w3-container" action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="registration"/>
        <input type="hidden" name="${ParameterName.USER_ROLE}" value="COMPANY"/>
        <div class="w3-section">
            <!-- LOGIN -->
            <label>
                <b><fmt:message key="label.user.login"/></b>
            </label>
            <input class="w3-input w3-border w3-margin-bottom"
                   type="text" name="${ParameterName.USER_LOGIN}"
                   placeholder="<fmt:message key="label.user.login"/>" required
                   pattern="^[@\p{Alpha}]\w{4,20}$"
                   title="<fmt:message key="form.input.title.login.pattern"/>"/>

            <!-- USER EMAIL-->
            <label>
                <b><fmt:message key="label.user.email"/></b>
            </label>
            <input class="w3-input w3-border w3-margin-bottom"
                   type="email" name="${ParameterName.USER_EMAIL}"
                   placeholder="<fmt:message key="label.user.email"/>" required
                   pattern="^[\p{Alpha}\p{Digit}_!#$%&'*+/=?`{|}~^.-]+@[\p{Alpha}\p{Digit}.-]+$"
                   title="<fmt:message key="form.input.title.email.pattern"/>"/>

            <!-- PASSWORD -->
            <label>
                <b><fmt:message key="label.user.password"/></b>
            </label>
            <input class="w3-input w3-border w3-margin-bottom"
                   type="password" name="${ParameterName.CREDENTIAL_PASSWORD}"
                   placeholder="<fmt:message key="label.user.password"/>" required
                   pattern="^(?=.*[\p{Alpha}])(?=.*\d)[\p{Alpha}\d]{8,20}$"
                   title="<fmt:message key="form.input.title.password.pattern"/>"/>

            <!-- PASSWORD CONFIRM-->
            <label>
                <b><fmt:message key="label.user.password.confirm"/></b>
            </label>
            <input class="w3-input w3-border w3-margin-bottom"
                   type="password" name="${ParameterName.CREDENTIAL_CONFIRM_PASSWORD}"
                   placeholder="<fmt:message key="label.user.password.confirm"/>" required
                   pattern="^(?=.*[\p{Alpha}])(?=.*\d)[\p{Alpha}\d]{8,20}$"
                   title="<fmt:message key="form.input.title.password.pattern"/>"/>

            <!-- COMPANY NAME-->
            <label>
                <b><fmt:message key="label.user.companyname"/></b>
            </label>
            <input class="w3-input w3-border w3-margin-bottom"
                   type="text" name="${ParameterName.USER_NAME}"
                   placeholder="<fmt:message key="label.user.companyname"/>" required
                   pattern="^[\p{L}][\p{L}`-]?[\p{L}`]{1,20}$"
                   title="<fmt:message key="form.input.title.name.pattern"/>"/>

            <!-- BUTTON -->
            <button class="w3-button w3-block w3-green w3-section w3-padding" form="form2"
                    type="submit" formmethod="post" formaction="${pageContext.request.contextPath}/controller" >
                <fmt:message key="registration.button"/>
            </button>
        </div>
    </form>
</div>