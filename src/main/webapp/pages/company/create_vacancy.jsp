<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="../fragment/head.jsp" %>
    <title><fmt:message key="title.mainpage"/></title>
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
                        <h4><b>${user.getUserName()}</b></h4>
                        <h4>${user_location.getCountry()} ${user_location.getCity()}</h4>
                    </div>
                </div>
            </div>

            <div class="w3-threequarter w3-section">
                <div class="w3-card-4 w3-white">
                    <div class="w3-container w3-center w3-green">
                        <h2><fmt:message key="label.vacancy"/></h2>
                    </div>
                    <form action="${pageContext.request.contextPath}/controller" method="post" class="w3-container">
                        <input type="hidden" name="${ParameterName.COMMAND}" value="CREATE_VACANCY"/>
                        <input type="hidden" name="${ParameterName.VACANCY_COMPANY_ID}" value="${user.id}"/>
                        <div class="w3-row">
                            <div class="w3-half" style="padding-right: 8px;">
                                <label><h4><b><fmt:message key="label.vacancy.jobtitle"/>: </b></h4></label>
                                <input class="w3-input w3-border"
                                       type="text"
                                       name="${ParameterName.VACANCY_JOB_TITLE}"
                                       value="${vacancy_job_title}"
                                       placeholder="<fmt:message key="label.vacancy.jobtitle"/>"
                                       pattern="[A-Za-zА-Яа-я]+"
                                       title="<fmt:message key="form.input.title.vacancy.jobtitle"/>"
                                       required/>

                                <label><h4><b><fmt:message key="label.vacancy.workexperience"/>: </b></h4></label>
                                <select class="w3-input w3-border"
                                        name="${ParameterName.VACANCY_WORK_EXPERIENCE}"
                                        required>
                                    <option value="WITHOUT"
                                            <c:if test="${vacancy_work_experience == 'WITHOUT'}">
                                                selected
                                            </c:if>>
                                        <fmt:message key="label.vacancy.experience.without"/>
                                    </option>
                                    <option value="UP_TO_A_YEAR"
                                            <c:if test="${vacancy_work_experience == 'UP_TO_A_YEAR'}">
                                                selected
                                            </c:if>>
                                        <fmt:message key="label.vacancy.experience.uptoayear"/>
                                    </option>
                                    <option value="FROM_1_TO_3_YEARS"
                                            <c:if test="${vacancy_work_experience == 'FROM_1_TO_3_YEARS'}">
                                                selected
                                            </c:if>>
                                        <fmt:message key="label.vacancy.experience.from1to3"/>
                                    </option>
                                    <option value="FROM_3_TO_5_YEARS"
                                            <c:if test="${vacancy_work_experience == 'FROM_3_TO_5_YEARS'}">
                                                selected
                                            </c:if>>
                                        <fmt:message key="label.vacancy.experience.from3to5"/>
                                    </option>
                                    <option value="MORE_THAN_5_YEARS"
                                            <c:if test="${vacancy_work_experience == 'MORE_THAN_5_YEARS'}">
                                                selected
                                            </c:if>>
                                        <fmt:message key="label.vacancy.experience.morethan5"/>
                                    </option>
                                </select>

                                <label><h4><b><fmt:message key="label.vacancy.salary"/>: </b></h4></label>
                                <div class="w3-row">
                                    <div class="w3-right" style="width: 60px">
                                        <select class="w3-input w3-border"
                                                name="${ParameterName.VACANCY_CURRENCY}"
                                                required>
                                            <option value="BYN"
                                                    <c:if test="${vacancy_currency == 'BYN'}">
                                                        selected
                                                    </c:if>>
                                                BYN
                                            </option>
                                            <option value="USD"
                                                    <c:if test="${vacancy_currency == 'USD'}">
                                                        selected
                                                    </c:if>>
                                                USD
                                            </option>
                                            <option value="EUR"
                                                    <c:if test="${vacancy_currency == 'EUR'}">
                                                        selected
                                                    </c:if>>
                                                EUR
                                            </option>
                                        </select>
                                    </div>
                                    <div class="w3-rest">
                                        <input class="w3-input w3-border"
                                               type="number"
                                               min="0"
                                               name="${ParameterName.VACANCY_SALARY}"
                                               value="${vacancy_salary}"
                                               placeholder="<fmt:message key="label.vacancy.salary"/>"
                                               pattern="[0-9]+"
                                               title="<fmt:message key="form.input.title.vacancy.salary"/>"
                                               required/>
                                    </div>
                                </div>
                            </div>

                            <div class="w3-half">
                                <label><h4><b><fmt:message key="label.vacancy.country"/>: </b></h4></label>
                                <input class="w3-input w3-border"
                                       type="text"
                                       name="${ParameterName.LOCATION_COUNTRY}"
                                       value="${location_country}"
                                       placeholder="<fmt:message key="label.vacancy.country"/>"
                                       pattern="[A-Za-zА-Яа-я]+"
                                       title="<fmt:message key="form.input.title.country.pattern"/>"
                                       required/>

                                <label><h4><b><fmt:message key="label.vacancy.city"/>: </b></h4></label>
                                <input class="w3-input w3-border"
                                       type="text"
                                       name="${ParameterName.LOCATION_CITY}"
                                       value="${location_city}"
                                       placeholder="<fmt:message key="label.vacancy.city"/>"
                                       pattern="[A-Za-zА-Яа-я]+"
                                       title="<fmt:message key="form.input.title.city.pattern"/>"
                                       required/>
                            </div>
                        </div>
                        <div>
                            <label><h4><b><fmt:message key="label.vacancy.responsibilities"/>: </b></h4>
                                <textarea class="w3-input w3-border"
                                          style="height:100px;"
                                          name="${ParameterName.VACANCY_RESPONSIBILITIES}"
                                          placeholder="<fmt:message key="label.vacancy.responsibilities"/>"
                                          maxlength="300">${vacancy_responsibilities}</textarea>
                            </label>

                            <label><h4><b><fmt:message key="label.vacancy.requirements"/>: </b></h4>
                                <textarea class="w3-input w3-border"
                                          style="height:100px;"
                                          placeholder="<fmt:message key="label.vacancy.requirements"/>"
                                          name="${ParameterName.VACANCY_REQUIREMENTS}"
                                          maxlength="300">${vacancy_requirements}</textarea>
                            </label>
                            <label><h4><b><fmt:message key="label.vacancy.workingconditions"/>: </b></h4>
                                <textarea class="w3-input w3-border"
                                          style="height:100px;"
                                          placeholder="<fmt:message key="label.vacancy.workingconditions"/>"
                                          name="${ParameterName.VACANCY_WORKING_CONDITIONS}"
                                          maxlength="300">${vacancy_working_conditions}</textarea>
                            </label>
                        </div>
                        <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">
                            <fmt:message key="save.button"/>
                        </button>
                    </form>
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