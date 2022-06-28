<%--@elvariable id="user_location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="vacancy_location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%--@elvariable id="vacancy" type="org.chervyakovsky.jobsearch.model.entity.Vacancy"--%>
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
                        <h4><b>${user.userName}</b></h4>
                        <h4>${user_location.country} ${user_location.city}</h4>
                    </div>
                </div>
            </div>

            <div class="w3-threequarter w3-section">
                <div class="w3-card-4 w3-white">
                    <div class="w3-container w3-center w3-green">
                        <h2><fmt:message key="label.vacancy"/>: ${temp_vacancy.key.jobTitle}</h2>
                    </div>
                    <form action="${pageContext.request.contextPath}/controller" method="post" class="w3-container">
                        <input type="hidden" name="${ParameterName.COMMAND}" value="update_vacancy"/>
                        <input type="hidden" name="${ParameterName.VACANCY_ID}" value="${temp_vacancy.key.id}"/>
                        <input type="hidden" name="${ParameterName.VACANCY_COMPANY_ID}" value="${user.id}"/>
                        <input type="hidden" name="${ParameterName.VACANCY_LOCATION_ID}" value="${temp_vacancy.key.locationId}"/>
                        <div class="w3-row">
                            <div class="w3-half" style="padding-right: 8px;">
                                <label><h4><b><fmt:message key="label.vacancy.jobtitle"/>: </b></h4></label>
                                <input class="w3-input w3-border"
                                       type="text"
                                       name="${ParameterName.VACANCY_JOB_TITLE}"
                                       value="${temp_vacancy.key.jobTitle}"
                                       placeholder="<fmt:message key="label.vacancy.jobtitle"/>"
                                       pattern="^([a-zA-ZА-Яа-я]{1})([\ha-zа-я-]{1,20})([a-zа-я]{1})$"
                                       title="<fmt:message key="form.input.title.vacancy.jobtitle"/>"
                                       required/>

                                <label><h4><b><fmt:message key="label.vacancy.workexperience"/>: </b></h4></label>
                                <select class="w3-input w3-border"
                                        name="${ParameterName.VACANCY_WORK_EXPERIENCE}"
                                        required>
                                    <option value="WITHOUT"
                                            <c:if test="${temp_vacancy.key.workExperienceStatus == 'WITHOUT'}">
                                                selected
                                            </c:if>>
                                        <fmt:message key="label.vacancy.experience.without"/></option>
                                    <option value="UP_TO_A_YEAR"
                                            <c:if test="${temp_vacancy.key.workExperienceStatus == 'UP_TO_A_YEAR'}">
                                                selected
                                            </c:if>>
                                        <fmt:message key="label.vacancy.experience.uptoayear"/></option>
                                    <option value="FROM_1_TO_3_YEARS"
                                            <c:if test="${temp_vacancy.key.workExperienceStatus == 'FROM_1_TO_3_YEARS'}">
                                                selected
                                            </c:if>>
                                        <fmt:message key="label.vacancy.experience.from1to3"/></option>
                                    <option value="FROM_3_TO_5_YEARS"
                                            <c:if test="${temp_vacancy.key.workExperienceStatus == 'FROM_3_TO_5_YEARS'}">
                                                selected
                                            </c:if>>
                                        <fmt:message key="label.vacancy.experience.from3to5"/></option>
                                    <option value="MORE_THAN_5_YEARS"
                                            <c:if test="${temp_vacancy.key.workExperienceStatus == 'MORE_THAN_5_YEARS'}">
                                                selected
                                            </c:if>>
                                        <fmt:message key="label.vacancy.experience.morethan5"/></option>
                                </select>

                                <label><h4><b><fmt:message key="label.vacancy.salary"/>: </b></h4></label>
                                <div class="w3-row">
                                    <div class="w3-right" style="width: 60px">
                                        <select class="w3-input w3-border"
                                                name="${ParameterName.VACANCY_CURRENCY}"
                                                required>
                                            <option value="BYN"
                                                    <c:if test="${temp_vacancy.key.currency == 'BYN'}">
                                                        selected
                                                    </c:if>>BYN</option>
                                            <option value="USD"
                                                    <c:if test="${temp_vacancy.key.currency == 'USD'}">
                                                        selected
                                                    </c:if>>USD</option>
                                            <option value="EUR"
                                                    <c:if test="${temp_vacancy.key.currency == 'EUR'}">
                                                        selected
                                                    </c:if>>EUR</option>
                                        </select>
                                    </div>
                                    <div class="w3-rest">
                                        <input class="w3-input w3-border"
                                               type="number"
                                               min="0"
                                               name="${ParameterName.VACANCY_SALARY}"
                                               value="${temp_vacancy.key.salary}"
                                               placeholder="<fmt:message key="label.vacancy.salary"/>"
                                               pattern="^[1-9][\d]{0,8}(\.\d{2}$)?"
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
                                       value="${temp_vacancy.value.key.country}"
                                       placeholder="<fmt:message key="label.vacancy.country"/>"
                                       pattern="[A-Za-zА-Яа-я]+"
                                       title="<fmt:message key="form.input.title.country.pattern"/>"
                                       required/>

                                <label><h4><b><fmt:message key="label.vacancy.city"/>: </b></h4></label>
                                <input class="w3-input w3-border"
                                       type="text"
                                       name="${ParameterName.LOCATION_CITY}"
                                       value="${temp_vacancy.value.key.city}"
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
                                          maxlength="300">${temp_vacancy.key.responsibilities}</textarea>
                            </label>

                            <label><h4><b><fmt:message key="label.vacancy.requirements"/>: </b></h4>
                                <textarea class="w3-input w3-border"
                                          style="height:100px;"
                                          placeholder="<fmt:message key="label.vacancy.requirements"/>"
                                          name="${ParameterName.VACANCY_REQUIREMENTS}"
                                          maxlength="300">${temp_vacancy.key.requirement}</textarea>
                            </label>
                            <label><h4><b><fmt:message key="label.vacancy.workingconditions"/>: </b></h4>
                                <textarea class="w3-input w3-border"
                                          style="height:100px;"
                                          placeholder="<fmt:message key="label.vacancy.workingconditions"/>"
                                          name="${ParameterName.VACANCY_WORKING_CONDITIONS}"
                                          maxlength="300">${temp_vacancy.key.workingConditions}</textarea>
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