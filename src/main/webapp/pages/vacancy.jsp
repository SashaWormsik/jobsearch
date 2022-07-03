<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="user_location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%--@elvariable id="vacancy" type="org.chervyakovsky.jobsearch.model.entity.Vacancy"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="fragment/head.jsp" %>
    <title><fmt:message key="title.mainpage"/></title>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <%@include file="fragment/header.jsp" %>
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
                <div class="w3-card-4 w3-white" style="padding-bottom: 10px">
                    <div class="w3-container w3-center w3-green">
                        <h2><fmt:message key="label.vacancy"/>: ${temp_vacancy.key.jobTitle}</h2>
                    </div>

                    <div class="w3-container">
                        <h4><b><fmt:message key="label.vacancy.salary"/>: </b>
                            ${temp_vacancy.key.salary} ${temp_vacancy.key.currency}
                        </h4>
                        <h4><b><fmt:message key="label.vacancy.workexperience"/>: </b>
                            <c:if test="${temp_vacancy.key.workExperienceStatus == 'WITHOUT'}">
                                <fmt:message key="label.vacancy.experience.without"/>
                            </c:if>
                            <c:if test="${temp_vacancy.key.workExperienceStatus == 'UP_TO_A_YEAR'}">
                                <fmt:message key="label.vacancy.experience.uptoayear"/>
                            </c:if>
                            <c:if test="${temp_vacancy.key.workExperienceStatus == 'FROM_1_TO_3_YEARS'}">
                                <fmt:message key="label.vacancy.experience.from1to3"/>
                            </c:if>
                            <c:if test="${temp_vacancy.key.workExperienceStatus == 'FROM_3_TO_5_YEARS'}">
                                <fmt:message key="label.vacancy.experience.from3to5"/>
                            </c:if>
                            <c:if test="${temp_vacancy.key.workExperienceStatus == 'MORE_THAN_5_YEARS'}">
                                <fmt:message key="label.vacancy.experience.morethan5"/>
                            </c:if></h4>
                        <h4><b><fmt:message key="label.user.companyname"/>:</b>
                            <a href="${pageContext.request.contextPath}/controller?command=get_user_info&user_id=${temp_vacancy.key.getCompanyId()}"
                               class="w3-hover-text-blue"
                               target="_blank">${temp_vacancy.value.value.userName}
                            </a>
                        </h4>


                        <h4><b><fmt:message key="label.vacancy.country"/>: </b>${temp_vacancy.value.key.country}</h4>
                        <h4><b><fmt:message key="label.vacancy.city"/>: </b>${temp_vacancy.value.key.city}</h4>
                        <h4 style="margin-bottom: 0px"><b><fmt:message key="label.vacancy.responsibilities"/>: </b></h4>
                        <p style="margin-top: 0px">${temp_vacancy.key.responsibilities}</p>

                        <h4 style="margin-bottom: 0px"><b><fmt:message key="label.vacancy.requirements"/>: </b></h4>
                        <p style="margin-top: 0px">${temp_vacancy.key.requirement}</p>

                        <h4 style="margin-bottom: 0px"><b><fmt:message key="label.vacancy.workingconditions"/>: </b>
                        </h4>
                        <p style="margin-top: 0px">${temp_vacancy.key.workingConditions}</p>

                        <c:if test="${user.role == 'WORKER'}">
                            <c:if test="${temp_response_to_a_vacancy==true}">
                                <div class="w3-center w3-blue">
                                    <fmt:message key="message.respond.interview.successful"/>
                                </div>
                            </c:if>
                            <c:if test="${temp_response_to_a_vacancy==false}">
                                <div class="w3-center w3-red">
                                    <fmt:message key="message.respond.interview.unsuccessful"/>
                                </div>
                            </c:if>
                            <c:if test="${empty temp_response_to_a_vacancy}">
                                <div>
                                    <form action="${pageContext.request.contextPath}/controller" method="post"
                                          id="respond">
                                        <input type="hidden" name="${ParameterName.COMMAND}"
                                               value="respond"/>
                                        <input type="hidden" name="${ParameterName.VACANCY_ID}"
                                               value="${temp_vacancy.key.id}"/>
                                        <input type="hidden" name="${ParameterName.USER_ID}" value="${user.id}">
                                        <button class="w3-button w3-block w3-blue w3-section w3-padding"
                                                type="submit"
                                                form="respond">
                                            <fmt:message key="button.respond"/>
                                        </button>
                                    </form>
                                </div>
                            </c:if>
                        </c:if>

                        <c:if test="${user.role == 'COMPANY' and user.id == temp_vacancy.key.companyId}">
                            <div class="w3-row">
                                <div class="w3-half" style="padding-right: 8px;">
                                    <form action="${pageContext.request.contextPath}/controller" method="get"
                                          id="update">
                                        <input type="hidden" name="${ParameterName.COMMAND}"
                                               value="go_to_update_the_vacancy"/>
                                        <input type="hidden" name="${ParameterName.VACANCY_ID}"
                                               value="${temp_vacancy.key.id}"/>
                                        <button class="w3-button w3-block w3-green w3-section w3-padding"
                                                type="submit"
                                                form="update">
                                            <fmt:message key="update.button"/>
                                        </button>
                                    </form>
                                </div>

                                <div class="w3-half">
                                    <form action="${pageContext.request.contextPath}/controller" method="post"
                                          id="change_status">
                                        <input type="hidden" name="${ParameterName.COMMAND}"
                                               value="change_vacancy_status"/>
                                        <input type="hidden" name="${ParameterName.VACANCY_ID}"
                                               value="${temp_vacancy.key.id}"/>
                                        <c:if test="${temp_vacancy.key.vacancyStatus == true}">
                                            <button class="w3-button w3-block w3-pink w3-section w3-padding"
                                                    type="submit"
                                                    form="change_status">
                                                <fmt:message key="change.vacancy.status.button.false"/>
                                            </button>
                                        </c:if>
                                        <c:if test="${temp_vacancy.key.vacancyStatus == false}">
                                            <button class="w3-button w3-block w3-blue w3-section w3-padding"
                                                    type="submit"
                                                    form="change_status">
                                                <fmt:message key="change.vacancy.status.button.true"/>
                                            </button>
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <footer class="footer">
        <%@include file="fragment/footer.jsp" %>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>