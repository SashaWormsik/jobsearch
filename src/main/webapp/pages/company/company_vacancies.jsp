<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="user_location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="../fragment/head.jsp" %>
    <title><fmt:message key="title.account.page"/></title>
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
                        <h4>${user_location.country} ${user_location.city}</h4>
                    </div>
                </div>
            </div>

            <div class="w3-threequarter w3-section" style=" word-break: break-all">
                <div class="w3-card-4 w3-white">

                    <div class="w3-container w3-center w3-green w3-margin-bottom">
                        <h2><fmt:message key="text.company_vacancies.page"/></h2>
                    </div>

                    <div class="w3-container w3-center">
                        <a href="${pageContext.request.contextPath}/pages/company/create_vacancy.jsp"
                           class="w3-button w3-green w3-section w3-padding">
                            <fmt:message key="button.create.new.vacancy"/>
                        </a>
                    </div>

                    <div class="w3-container">
                        <c:choose>
                        <c:when test="${vacancies.size()>0}">
                        <c:forEach var="vacancy" items="${vacancies}">
                            <div class="w3-bar w3-bottombar w3-border-green w3-padding"
                                 style="height: 150px">
                                <div class="w3-bar-item w3-border-right"
                                     style="width: 20%; padding: 8px; height: 130px">
                                    <a href="${pageContext.request.contextPath}/controller?command=get_vacancy_info&vacancy_id=${vacancy.key.getId()}"
                                       class="w3-hover-text-blue"
                                       target="_blank">
                                        <h6><b>${vacancy.key.getJobTitle()}</b></h6>
                                    </a><br>
                                    <span>${vacancy.value.getCountry()}, ${vacancy.value.getCity()}</span>
                                </div>
                                <div class="w3-bar-item w3-border-right w3-center"
                                     style="width: 12%; padding: 8px; height: 130px">
                                    <span><h6><b>${vacancy.key.getSalary()} ${vacancy.key.getCurrency()}</b></h6></span><br>
                                    <span>Опыт работы</span><br>
                                    <c:if test="${vacancy.key.getWorkExperienceStatus()=='WITHOUT'}">
                                        <span><fmt:message key="label.search.experience.without"/></span>
                                    </c:if>
                                    <c:if test="${vacancy.key.getWorkExperienceStatus()=='UP_TO_A_YEAR'}">
                                        <span><fmt:message key="label.search.experience.uptoayear"/></span>
                                    </c:if>
                                    <c:if test="${vacancy.key.getWorkExperienceStatus()=='FROM_1_TO_3_YEARS'}">
                                        <span><fmt:message key="label.search.experience.from1to3"/></span>
                                    </c:if>
                                    <c:if test="${vacancy.key.getWorkExperienceStatus()=='FROM_3_TO_5_YEARS'}">
                                        <span><fmt:message key="label.search.experience.from3to5"/></span>
                                    </c:if>
                                    <c:if test="${vacancy.key.getWorkExperienceStatus()=='MORE_THAN_5_YEARS'}">
                                        <span><fmt:message key="label.search.experience.morethan5"/></span>
                                    </c:if>
                                </div>
                                <div class="w3-bar-item " style="width: 68%; padding: 0 0 0 8px; height: 130px">
                                    <div style="-webkit-line-clamp: 6; overflow: hidden; display: -webkit-box; -webkit-box-orient: vertical; margin-top: 10px">
                                            ${vacancy.key.getWorkingConditions()}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="w3-center w3-container">
                        <div class="w3-bar w3-border w3-border-green">
                            <c:if test="${page != 1 && (not empty page)}">
                                <a href="${pageContext.request.contextPath}/controller?command=get_all_vacancies_for_company&page=${page - 1}"
                                   class="w3-button">&laquo;</a>
                            </c:if>
                            <c:forEach begin="1" end="${page_count}" var="i">
                                <c:choose>
                                    <c:when test="${page eq i}">
                                        <a class="w3-button w3-grey w3-disabled">${i}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/controller?command=get_all_vacancies_for_company&page=${i}"
                                           class="w3-button">${i}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${page lt page_count}">
                                <a href="${pageContext.request.contextPath}/controller?command=get_all_vacancies_for_company&page=${page + 1}"
                                   class="w3-button">&raquo;</a>
                            </c:if>
                        </div>
                    </div>
                    </c:when>
                    <c:otherwise>
                        <div class="w3-center">
                            <h5><b><fmt:message key="text.company_vacancies.page.no_vacancies"/></b></h5>
                        </div>
                    </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </main>
    <footer class="footer">
        <%@include file="../fragment/footer.jsp" %>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>