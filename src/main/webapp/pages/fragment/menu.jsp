<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.chervyakovsky.jobsearch.model.entity.status.EnumUserRoleStatus" %>
<%@ page import="org.chervyakovsky.jobsearch.controller.ParameterName" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="userRole" value="${sessionScope.user.role}"/>
<c:set var="language" value="${sessionScope.language == null ? 'en_US' : sessionScope.language }"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="language" var="lg"/>

<div class="w3-container w3-bar w3-black">
    <!-- GO to main page-->
    <a href="#" class="w3-bar-item w3-button w3-mobile w3-green">
        <fmt:message key="menu.button.main" bundle="${lg}"/>
    </a>

    <!-- MENU for company-->
    <c:if test="${userRole == EnumUserRoleStatus.COMPANY}">
        <a href="#" class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.profile" bundle="${lg}"/>
        </a>
        <a href="#" class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.vacancy" bundle="${lg}"/>
        </a>
        <a href="#" class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.interview" bundle="${lg}"/>
        </a>
    </c:if>

    <!-- MENU for worker-->
    <c:if test="${userRole == EnumUserRoleStatus.WORKER}">
        <a href="#" class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.profile" bundle="${lg}"/>
        </a>
        <a href="#" class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.vacancy" bundle="${lg}"/>
        </a>
        <a href="#" class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.interview" bundle="${lg}"/>
        </a>
    </c:if>

    <!-- MENU for admin-->
    <c:if test="${userRole == EnumUserRoleStatus.ADMIN}">
    <div class="w3-dropdown-hover w3-mobile">
        <button class="w3-button"><fmt:message key="menu.button.dropdown" bundle="${lg}"/>
            <i class="fa fa-caret-down"></i>
        </button>
        <div class="w3-dropdown-content w3-bar-block w3-dark-grey">
            <a href="#" class="w3-bar-item w3-button w3-mobile">
                <fmt:message key="menu.button.dropdown.users" bundle="${lg}"/>
            </a>
            <a href="#" class="w3-bar-item w3-button w3-mobile">
                <fmt:message key="menu.button.dropdown.companies" bundle="${lg}"/>
            </a>
            <a href="#" class="w3-bar-item w3-button w3-mobile">
                <fmt:message key="menu.button.dropdown.vacancies" bundle="${lg}"/>
            </a>
            <a href="#" class="w3-bar-item w3-button w3-mobile">
                <fmt:message key="menu.button.dropdown.addadmin" bundle="${lg}"/>
            </a>
        </div>
    </div>
    </c:if>


    <!-- LOGIN and LOGOUT BUTTON-->
    <div class="w3-right">
        <!-- СДЕЛАТЬ НАПРАВЛЕНИЕ НА КОНТРОЛЛЕР С КОМАНДОЙ logout -->
        <!-- LOGOUT -->
        <c:if test="${userRole != null}">
            <a href="#">
                <button class="w3-bar-item w3-button w3-mobile w3-red">
                    <fmt:message key="menu.button.logout" bundle="${lg}"/>
                </button>
            </a>
        </c:if>
        <!--END LOGOUT-->
        <!-- LOGIN -->
        <c:if test="${userRole == null}">
            <a class="w3-green">
                <button onclick="document.getElementById('id01').style.display='block'"
                        class="w3-bar-item w3-button w3-mobile">
                    <fmt:message key="menu.button.login" bundle="${lg}"/>
                </button>
            </a>
        </c:if>
        <!-- END LOGIN -->
    </div>
    <!--END LOGIN and LOGOUT BUTTON-->
</div>


<!-- LOGIN-->
<div id="id01" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">

        <div class="w3-center"><br>
            <!-- КРЕСТИК CLOSE FORM -->
            <span onclick="document.getElementById('id01').style.display='none'"
                  class="w3-button w3-xlarge w3-white w3-display-topright" title="close">
                &times;
            </span>
            <!-- END КРЕСТИК CLOSE FORM -->
            <!-- IMAGE -->
            <!-- !!!!! -->
            <img src="${pageContext.request.contextPath}/image/img.png" alt="Avatar"
                 style="width:30%" class="w3-circle w3-margin-top"/>
            <!--END IMAGE -->
        </div>

        <!-- LOGIN FORM-->
        <form class="w3-container" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="login"/>
            <div class="w3-section">

                <label>
                    <fmt:message key="label.user.login" bundle="${lg}"/>
                </label>
                <input class="w3-input w3-border w3-margin-bottom"
                       type="text" name="${ParameterName.LOGIN}"
                       placeholder=
                       <fmt:message key="label.user.login" bundle="${lg}"/> required
                       pattern="^[\w&&\D]\w{4,20}$"> <!-- PATTERN?? -->

                <label>
                    <fmt:message key="label.user.password" bundle="${lg}"/>
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
</div>
<!--END LOGIN-->
