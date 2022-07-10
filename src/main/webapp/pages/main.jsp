<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtag" %>

<%@include file="fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="fragment/head.jsp" %>
    <title><fmt:message key="title.main.page"/></title>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <%@include file="fragment/header.jsp" %>
    </header>

    <main class="main">
        <div class="w3-row-padding">
            <div class="w3-quarter w3-section" style=" word-break: break-all">
                <div class="w3-card-4 w3-white">
                    <div class="w3-container w3-center w3-green">
                        <h2><fmt:message key="text.main.page.parameters"/></h2>
                    </div>
                    <form class="w3-container" action="${pageContext.request.contextPath}/controller" method="get">
                        <div class="w3-section">
                            <input type="hidden" name="command" value="search_vacancy"/>
                            <label>
                                <b class="w3-text-black"><fmt:message key="label.location.country"/></b>
                            </label>
                            <input class="w3-input w3-border w3-margin-bottom"
                                   type="text" name="${ParameterName.LOCATION_COUNTRY}"
                                   placeholder="<fmt:message key="label.location.country"/>"
                                   pattern="^[A-ZА-Я][a-zа-я]+(\s?'?-?[A-ZА-Я]?[a-zа-я]+)*$"
                                   title="<fmt:message key="message.incorrect.country.pattern"/>"
                                   value="${location_country}"/>

                            <label>
                                <b class="w3-text-black"><fmt:message key="label.location.city"/></b>
                            </label>
                            <input class="w3-input w3-border w3-margin-bottom"
                                   type="text" name="${ParameterName.LOCATION_CITY}"
                                   placeholder="<fmt:message key="label.location.city"/>"
                                   pattern="^[A-ZА-Я][a-zа-я]+(\s?'?-?[A-ZА-Я]?[a-zа-я]+)*$"
                                   title="<fmt:message key="message.incorrect.city.pattern"/>"
                                   value="${location_city}"/>

                            <label>
                                <b class="w3-text-black"><fmt:message key="label.user.profession"/></b>
                            </label>
                            <input class="w3-input w3-border w3-margin-bottom"
                                   type="text" name="${ParameterName.VACANCY_JOB_TITLE}"
                                   placeholder="<fmt:message key="label.user.profession"/>"
                                   pattern="^([a-zA-ZА-Яа-я]{1})([\sa-zа-я-]{1,20})([a-zа-я]{1})"
                                   title="<fmt:message key="message.incorrect.profession.pattern"/>"
                                   value="${vacancy_job_title}"/>

                            <label>
                                <b class="w3-text-black"><fmt:message key="label.vacancy.experience"/></b>
                            </label><br/>
                            <input type="radio" id="WITHOUT"
                                   name="${ParameterName.VACANCY_WORK_EXPERIENCE}"
                                   value="WITHOUT"
                                    <c:if test="${vacancy_work_experience=='WITHOUT'}">
                                        checked
                                    </c:if>/>
                            <label for="WITHOUT"><fmt:message key="label.vacancy.experience.without"/></label><br/>

                            <input type="radio" id="UP_TO_A_YEAR"
                                   name="${ParameterName.VACANCY_WORK_EXPERIENCE}"
                                   value="UP_TO_A_YEAR"
                                    <c:if test="${vacancy_work_experience=='UP_TO_A_YEAR'}">
                                        checked
                                    </c:if>/>
                            <label for="UP_TO_A_YEAR"><fmt:message
                                    key="label.vacancy.experience.uptoayear"/></label><br/>

                            <input type="radio" id="FROM_1_TO_3_YEARS"
                                   name="${ParameterName.VACANCY_WORK_EXPERIENCE}"
                                   value="FROM_1_TO_3_YEARS"
                                    <c:if test="${vacancy_work_experience=='FROM_1_TO_3_YEARS'}">
                                        checked
                                    </c:if>/>
                            <label for="FROM_1_TO_3_YEARS"><fmt:message key="label.vacancy.experience.from1to3"/></label><br/>

                            <input type="radio" id="FROM_3_TO_5_YEARS"
                                   name="${ParameterName.VACANCY_WORK_EXPERIENCE}"
                                   value="FROM_3_TO_5_YEARS"
                                    <c:if test="${vacancy_work_experience=='FROM_3_TO_5_YEARS'}">
                                        checked
                                    </c:if>/>
                            <label for="FROM_3_TO_5_YEARS"><fmt:message key="label.vacancy.experience.from3to5"/></label><br/>

                            <input type="radio" id="MORE_THAN_5_YEARS"
                                   name="${ParameterName.VACANCY_WORK_EXPERIENCE}"
                                   value="MORE_THAN_5_YEARS"
                                    <c:if test="${vacancy_work_experience=='MORE_THAN_5_YEARS'}">
                                        checked
                                    </c:if>/>
                            <label for="MORE_THAN_5_YEARS"><fmt:message
                                    key="label.vacancy.experience.morethan5"/></label><br/>

                            <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">
                                <fmt:message key="button.search"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>


            <div class="w3-threequarter w3-section" style=" word-break: break-all">
                <div class="w3-card-4 w3-white">

                    <div class="w3-container w3-center w3-green">
                        <h2><fmt:message key="text.main.page.search_result"/></h2>
                    </div>

                    <div class="w3-container">

                        <c:choose>
                        <c:when test="${vacancies.size()>0}">
                        <c:forEach var="vacancy" items="${vacancies}">
                            <div class="w3-bar w3-bottombar w3-border-green w3-padding"
                                 style="height: 150px">
                                <div class="w3-bar-item w3-border-right" style="width: 20%; padding: 8px; height: 130px">
                                    <a href="${pageContext.request.contextPath}/controller?command=get_vacancy_info&vacancy_id=${vacancy.key.getId()}"
                                       class="w3-hover-text-blue"
                                       target="_blank">
                                        <h6><b>${vacancy.key.getJobTitle()}</b></h6>
                                    </a>
                                    <br>
                                    <span>${vacancy.value.value.getUserName()}</span><br>
                                    <span>${vacancy.value.key.getCountry()}, ${vacancy.value.key.getCity()}</span>
                                </div>
                                <div class="w3-bar-item w3-border-right w3-center"
                                     style="width: 12%; padding: 8px; height: 130px">
                                    <span><h6><b>${vacancy.key.getSalary()} ${vacancy.key.getCurrency()}</b></h6></span><br>
                                    <span><fmt:message key="label.vacancy.experience"/></span><br>
                                    <c:if test="${vacancy_work_experience=='WITHOUT'}">
                                        <span><fmt:message key="label.vacancy.experience.without"/></span>
                                    </c:if>
                                    <c:if test="${vacancy_work_experience=='UP_TO_A_YEAR'}">
                                        <span><fmt:message key="label.vacancy.experience.uptoayear"/></span>
                                    </c:if>
                                    <c:if test="${vacancy_work_experience=='FROM_1_TO_3_YEARS'}">
                                        <span><fmt:message key="label.vacancy.experience.from1to3"/></span>
                                    </c:if>
                                    <c:if test="${vacancy_work_experience=='FROM_3_TO_5_YEARS'}">
                                        <span><fmt:message key="label.vacancy.experience.from3to5"/></span>
                                    </c:if>
                                    <c:if test="${vacancy_work_experience=='MORE_THAN_5_YEARS'}">
                                        <span><fmt:message key="label.vacancy.experience.morethan5"/></span>
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
                                <a href="${pageContext.request.contextPath}/controller?command=search_vacancy&location_country=${location_country}&location_city=${location_city}&vacancy_job_title=${vacancy_job_title}&vacancy_work_experience=${vacancy_work_experience}&page=${page - 1}"
                                   class="w3-button">&laquo;</a>
                            </c:if>
                            <c:forEach begin="1" end="${page_count}" var="i">
                                <c:choose>
                                    <c:when test="${page eq i}">
                                        <a class="w3-button w3-grey w3-disabled">${i}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/controller?command=search_vacancy&location_country=${location_country}&location_city=${location_city}&vacancy_job_title=${vacancy_job_title}&vacancy_work_experience=${vacancy_work_experience}&page=${i}"
                                           class="w3-button">${i}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${page lt page_count}">
                                <a href="${pageContext.request.contextPath}/controller?command=search_vacancy&location_country=${location_country}&location_city=${location_city}&vacancy_job_title=${vacancy_job_title}&vacancy_work_experience=${vacancy_work_experience}&page=${page + 1}"
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
        <ctg:footer/>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>