<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="w3-container w3-bar w3-black"
     style="padding-left: 32px; padding-right: 32px;">
    <!-- GO to main page-->
    <a href="#" class="w3-bar-item w3-button w3-mobile w3-green">
        <fmt:message key="menu.button.main"/>
    </a>

    <!-- MENU for company-->
    <c:if test="${user.role == UserRoleStatus.COMPANY}">
        <a href="${pageContext.request.contextPath}/pages/common/account.jsp"
           class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.profile"/>
        </a>
        <a href="#" class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.vacancy"/>
        </a>
        <a href="#" class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.interview"/>
        </a>
    </c:if>

    <!-- MENU for worker-->
    <c:if test="${user.role == UserRoleStatus.WORKER}">
        <a href="${pageContext.request.contextPath}/pages/common/account.jsp"
           class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.profile"/>
        </a>
        <a href="#" class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.vacancy"/>
        </a>
        <a href="#" class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.interview"/>
        </a>
    </c:if>

    <!-- MENU for admin-->
    <c:if test="${user.role == UserRoleStatus.ADMIN}">
        <a href="${pageContext.request.contextPath}/pages/admin/admin_account.jsp"
           class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="menu.button.profile"/>
        </a>
        <div class="w3-dropdown-hover w3-mobile">
            <button class="w3-button"><fmt:message key="menu.button.dropdown"/>
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="w3-dropdown-content w3-bar-block w3-dark-grey">
                <a href="${pageContext.request.contextPath}/controller?command=get_all_users&user_role=WORKER&page=1"
                   class="w3-bar-item w3-button w3-mobile">
                    <fmt:message key="menu.button.dropdown.users"/>
                </a>
                <a href="${pageContext.request.contextPath}/controller?command=get_all_users&user_role=COMPANY&page=1"
                   class="w3-bar-item w3-button w3-mobile">
                    <fmt:message key="menu.button.dropdown.companies"/>
                </a>
                <a href="${pageContext.request.contextPath}/controller?command=get_all_users&user_role=ADMIN&page=1"
                   class="w3-bar-item w3-button w3-mobile">
                    <fmt:message key="menu.button.dropdown.admins"/>
                </a>
                <a href="${pageContext.request.contextPath}/pages/admin/add_new_admin.jsp"
                   class="w3-bar-item w3-button w3-mobile">
                    <fmt:message key="menu.button.dropdown.addadmin"/>
                </a>
            </div>
        </div>
    </c:if>

    <!-- LOGIN and LOGOUT BUTTON-->
    <!-- LOGOUT BUTTON-->
    <c:if test="${user != null}">
        <div class="w3-right w3-mobile">
            <a href="${pageContext.request.contextPath}/controller?command=logout"
               class="w3-button w3-block w3-red">
                <fmt:message key="menu.button.logout"/>
            </a>
        </div>
    </c:if>
    <!--END LOGOUT BUTTON-->
    <!-- LOGIN BUTTON-->
    <c:if test="${user == null}">
        <div class="w3-right w3-mobile">
            <button onclick="document.getElementById('id01').style.display='block'"
                    class="w3-block w3-button w3-green">
                <fmt:message key="menu.button.login"/>
            </button>
            <!-- END LOGIN BUTTON-->
        </div>

        <!-- LOGIN-->
        <div id="id01" class="w3-modal w3-animate-zoom">
            <%@include file="loginForm.jsp" %>
        </div>
        <!--END -->
    </c:if>
    <!--END LOGIN and LOGOUT BUTTON-->
</div>


<script>
    // Get the modal
    const modal = document.getElementById('id01');
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>