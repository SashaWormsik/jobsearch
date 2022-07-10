<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="w3-container w3-bar w3-black"
     style="padding-left: 32px; padding-right: 32px;">
    <!-- GO to main page-->
    <a href="${pageContext.request.contextPath}/controller?command=search_vacancy&location_country=&location_city=&vacancy_job_title=&vacancy_work_experience="
       class="w3-bar-item w3-button w3-mobile w3-green">
        <fmt:message key="button.main"/>
    </a>

    <!-- MENU for company-->
    <c:if test="${user.role == UserRoleStatus.COMPANY}">
        <a href="${pageContext.request.contextPath}/pages/common/account.jsp"
           class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="button.profile"/>
        </a>
        <a href="${pageContext.request.contextPath}/controller?command=get_all_vacancies_for_company"
           class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="button.vacancy"/>
        </a>
        <a href="${pageContext.request.contextPath}/controller?command=get_all_interviews"
           class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="button.interview"/>
        </a>
    </c:if>

    <!-- MENU for worker-->
    <c:if test="${user.role == UserRoleStatus.WORKER}">
        <a href="${pageContext.request.contextPath}/pages/common/account.jsp"
           class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="button.profile"/>
        </a>
        <a href="${pageContext.request.contextPath}/controller?command=get_all_interviews"
           class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="button.interview"/>
        </a>
    </c:if>

    <!-- MENU for admin-->
    <c:if test="${user.role == UserRoleStatus.ADMIN}">
        <a href="${pageContext.request.contextPath}/pages/common/account.jsp"
           class="w3-bar-item w3-button w3-mobile">
            <fmt:message key="button.profile"/>
        </a>
        <div class="w3-dropdown-hover w3-mobile">
            <button class="w3-button"><fmt:message key="button.dropdown"/>
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="w3-dropdown-content w3-bar-block w3-dark-grey">
                <a href="${pageContext.request.contextPath}/controller?command=get_all_users&user_role=WORKER&page=1"
                   class="w3-bar-item w3-button w3-mobile">
                    <fmt:message key="button.dropdown.users"/>
                </a>
                <a href="${pageContext.request.contextPath}/controller?command=get_all_users&user_role=COMPANY&page=1"
                   class="w3-bar-item w3-button w3-mobile">
                    <fmt:message key="button.dropdown.companies"/>
                </a>
                <a href="${pageContext.request.contextPath}/controller?command=get_all_users&user_role=ADMIN&page=1"
                   class="w3-bar-item w3-button w3-mobile">
                    <fmt:message key="button.dropdown.admins"/>
                </a>
                <a href="${pageContext.request.contextPath}/pages/admin/add_new_admin.jsp"
                   class="w3-bar-item w3-button w3-mobile">
                    <fmt:message key="button.dropdown.addadmin"/>
                </a>
            </div>
        </div>
    </c:if>

    <div class="w3-right">
        <div class="w3-show-inline-block">
            <div class="w3-bar">
                <form action="${pageContext.request.contextPath}/controller" method="get" id="ru"
                class="w3-bar-item">
                    <input type="hidden" name="command" value="set_localization_type">
                    <input type="hidden" name="locale" value="ru_RU">
                    <input type="hidden" name="page_path" value="${pageContext.request.requestURI}">
                    <input type="hidden" name="query_string" value="${pageContext.request.queryString}">
                    <button class="w3-black w3-hover-blue" type="submit" form="ru">
                        RU
                    </button>
                </form>
                <form action="${pageContext.request.contextPath}/controller" method="get" id="en"
                class="w3-bar-item">
                    <input type="hidden" name="command" value="set_localization_type">
                    <input type="hidden" name="locale" value="en_US">
                    <input type="hidden" name="page_path" value="${pageContext.request.requestURI}">
                    <input type="hidden" name="query_string" value="${pageContext.request.queryString}">
                    <button class="w3-black w3-hover-blue" type="submit" form="en">
                        EN
                    </button>
                </form>
            </div>
        </div>
        <div class="w3-show-inline-block">
            <div class="w3-bar">
                <c:if test="${user != null}">
                    <div class="w3-mobile">
                        <a href="${pageContext.request.contextPath}/controller?command=logout"
                           class="w3-button w3-block w3-red">
                            <fmt:message key="button.logout"/>
                        </a>
                    </div>
                </c:if>

                <c:if test="${user == null}">
                    <div class="w3-mobile">
                        <button onclick="document.getElementById('id01').style.display='block'"
                                class="w3-block w3-button w3-green">
                            <fmt:message key="button.login"/>
                        </button>
                    </div>
                    <div id="id01" class="w3-modal w3-animate-zoom">
                        <%@include file="loginForm.jsp" %>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

</div>
