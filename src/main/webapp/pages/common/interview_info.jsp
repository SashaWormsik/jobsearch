<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="user_location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%--@elvariable id="temp_user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="temp_interview" type="org.chervyakovsky.jobsearch.model.entity.Interview"--%>
<%--@elvariable id="temp_location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="../fragment/head.jsp" %>
    <title><fmt:message key="title.user_info.page"/></title>
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
                    <div class="w3-container w3-green w3-center">
                        <h4><fmt:message key="label.vacancy"/>: ${temp_vacancy.key.jobTitle}</b></h4>
                    </div>
                    <div class="w3-container">
                        <div class="w3-row">
                            <div class="w3-half">
                                <h4><b><fmt:message key="label.vacancy.salary"/>: </b>
                                    ${temp_vacancy.key.salary} ${temp_vacancy.key.currency}</h4>
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
                                    </c:if>
                                </h4>
                            </div>
                            <div class="w3-half">
                                <h4><b><fmt:message key="label.vacancy.country"/>: </b>${temp_vacancy.value.key.country}
                                </h4>
                                <h4><b><fmt:message key="label.vacancy.city"/>: </b>${temp_vacancy.value.key.city}</h4>
                            </div>
                        </div>
                    </div>

                    <c:if test="${user.role == 'COMPANY'}">
                        <div class="w3-container w3-green w3-center">
                            <h4><fmt:message key="text.user_info.page.worker"/></h4>
                        </div>
                        <div class="w3-container">
                            <div class="w3-row w3-bottombar w3-border-green">
                                <div class="w3-half">
                                    <h4><b><fmt:message key="label.user.name"/> <fmt:message
                                            key="label.user.surname"/>: </b>
                                        <a href="${pageContext.request.contextPath}/controller?command=get_user_info&user_id=${temp_user.id}"
                                           class="w3-hover-text-blue"
                                           target="_blank">${temp_user.userName} ${temp_user.userSurName}</a></h4>
                                    <h4><b><fmt:message key="label.user.profession"/>: </b>${temp_user.profession}</h4>
                                </div>
                            </div>
                            <h4><b><fmt:message key="text.interview_info.page.time"/>: </b>
                                <fmt:formatDate type="both"
                                                dateStyle="short"
                                                timeStyle="short"
                                                value="${temp_interview.appointedDateTime}"/></h4>
                            <h4><b><fmt:message key="text.interview_info.page.communication_method"/>: </b>
                                    ${temp_interview.communicationMethod}</h4>

                            <c:if test="${temp_interview.interviewStatus == 'IS_REJECTED'}">
                                <div class="w3-container w3-red w3-center">
                                    <h4><b><fmt:message key="text.interview_info.page.rejected"/></b></h4>
                                </div>
                            </c:if>

                            <c:if test="${temp_interview.interviewStatus == 'IS_COMPLETED'}">
                                <div class="w3-container w3-blue w3-center">
                                    <h4><b><fmt:message key="text.interview_info.page.completed"/></b></h4>
                                </div>
                            </c:if>

                            <c:if test="${temp_interview.interviewStatus == 'IS_SCHEDULED'}">
                                <div class="w3-container w3-blue w3-center">
                                    <h4><b><fmt:message key="text.interview_info.page.scheduled"/></b></h4>
                                </div>
                            </c:if>

                            <c:if test="${temp_interview.interviewStatus == 'IN_WAITING'}">
                                <div class="w3-container w3-green w3-center">
                                    <h4><b><fmt:message key="text.interview_info.page.waiting"/></b></h4>
                                </div>
                            </c:if>

                            <c:if test="${temp_interview.interviewStatus == 'IN_WAITING' or temp_interview.interviewStatus == 'IS_SCHEDULED'}">
                                <div class="w3-row-padding">
                                    <div class="w3-third">
                                        <form class="w3-container"
                                              action="${pageContext.request.contextPath}/controller"
                                              method="post"
                                              id="reject">
                                            <input type="hidden"
                                                   name="${ParameterName.COMMAND}"
                                                   value="change_interview_status"/>
                                            <input type="hidden"
                                                   name="${ParameterName.INTERVIEW_ID}"
                                                   value="${temp_interview.id}"/>
                                            <input type="hidden"
                                                   name="${ParameterName.INTERVIEW_STATUS}"
                                                   value="IS_REJECTED"/>
                                            <input type="hidden"
                                                   name="${ParameterName.VACANCY_ID}"
                                                   value="${temp_vacancy.key.id}"/>
                                            <input type="hidden"
                                                   name="${ParameterName.USER_ID}"
                                                   value="${temp_user.id}"/>
                                            <button class="w3-button w3-block w3-red w3-section w3-padding"
                                                    type="submit"
                                                    form="reject">
                                                <fmt:message key="button.reject"/>
                                            </button>
                                        </form>
                                    </div>
                                    <div class="w3-third">
                                        <button onclick="document.getElementById('id02').style.display='block'"
                                                class="w3-button w3-block w3-green w3-section w3-padding">
                                            <fmt:message key="button.appoint"/>
                                        </button>
                                    </div>
                                    <div class="w3-third">
                                        <form class="w3-container"
                                              action="${pageContext.request.contextPath}/controller"
                                              method="post"
                                              id="completed">
                                            <input type="hidden"
                                                   name="${ParameterName.COMMAND}"
                                                   value="change_interview_status"/>
                                            <input type="hidden"
                                                   name="${ParameterName.INTERVIEW_ID}"
                                                   value="${temp_interview.id}"/>
                                            <input type="hidden"
                                                   name="${ParameterName.INTERVIEW_STATUS}"
                                                   value="IS_COMPLETED"/>
                                            <input type="hidden"
                                                   name="${ParameterName.VACANCY_ID}"
                                                   value="${temp_vacancy.key.id}"/>
                                            <input type="hidden"
                                                   name="${ParameterName.USER_ID}"
                                                   value="${temp_user.id}"/>
                                            <button class="w3-button w3-block w3-blue w3-section w3-padding"
                                                    type="submit"
                                                    form="completed">
                                                <fmt:message key="button.completed"/>
                                            </button>
                                        </form>
                                    </div>
                                    <div id="id02" class="w3-modal">
                                        <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                                            <form class="w3-container"
                                                  action="${pageContext.request.contextPath}/controller"
                                                  method="post"
                                                  id="appoint">
                                                <div class="w3-section">
                                                    <input type="hidden"
                                                           name="${ParameterName.COMMAND}"
                                                           value="update_interview"/>
                                                    <input type="hidden"
                                                           name="${ParameterName.INTERVIEW_ID}"
                                                           value="${temp_interview.id}"/>
                                                    <input type="hidden"
                                                           name="${ParameterName.INTERVIEW_STATUS}"
                                                           value="IS_SCHEDULED"/>
                                                    <input type="hidden"
                                                           name="${ParameterName.VACANCY_ID}"
                                                           value="${temp_vacancy.key.id}"/>
                                                    <input type="hidden"
                                                           name="${ParameterName.USER_ID}"
                                                           value="${temp_user.id}"/>
                                                    <label><b><fmt:message
                                                            key="text.interview_info.page.time"/></b></label>
                                                    <input class="w3-input w3-border w3-margin-bottom"
                                                           type="datetime-local"
                                                           id="idD"
                                                           name="${ParameterName.INTERVIEW_APPOINTED_DATE}" required/>
                                                    <label><b><fmt:message
                                                            key="text.interview_info.page.communication_method"/></b></label>
                                                    <!--ДОБАВИТЬ ПАТТЕРН -->
                                                    <input class="w3-input w3-border" type="text"
                                                           placeholder="<fmt:message key="text.interview_info.page.communication_method"/>"
                                                           value="${temp_interview.communicationMethod}"
                                                           name="${ParameterName.INTERVIEW_COMMUNICATION_METHOD}"
                                                           required/>
                                                    <button class="w3-button w3-block w3-green w3-section w3-padding"
                                                            type="submit"
                                                            form="appoint">
                                                        <fmt:message key="button.appoint"/>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </c:if>


                    <c:if test="${user.role == 'WORKER'}">
                        <div class="w3-container w3-green w3-center">
                            <h4><fmt:message key="text.user_info.page.company"/></h4>
                        </div>
                        <div class="w3-container">
                            <div class="w3-row w3-bottombar w3-border-green">
                                <div class="w3-half">
                                    <h4><b><fmt:message key="label.user.companyname"/> :</b>
                                        <a href="${pageContext.request.contextPath}/controller?command=get_user_info&user_id=${temp_vacancy.key.getCompanyId()}"
                                           class="w3-hover-text-blue"
                                           target="_blank">${temp_vacancy.value.value.userName}</a></h4>
                                </div>
                            </div>
                            <h4><b><fmt:message key="text.interview_info.page.time"/>: </b>
                                <fmt:formatDate type="both"
                                                dateStyle="short"
                                                timeStyle="short"
                                                value="${temp_interview.appointedDateTime}"/></h4>
                            <h4><b><fmt:message key="text.interview_info.page.communication_method"/>:</b>
                                    ${temp_interview.communicationMethod}</h4>

                            <c:if test="${temp_interview.interviewStatus == 'IS_REJECTED'}">
                                <div class="w3-container w3-red w3-center">
                                    <h4><b><fmt:message key="text.interview_info.page.rejected"/></b></h4>
                                </div>
                            </c:if>

                            <c:if test="${temp_interview.interviewStatus == 'IS_COMPLETED'}">
                                <div class="w3-container w3-blue w3-center">
                                    <h4><b><fmt:message key="text.interview_info.page.completed"/></b></h4>
                                </div>
                            </c:if>

                            <c:if test="${temp_interview.interviewStatus == 'IS_SCHEDULED'}">
                                <div class="w3-container w3-blue w3-center">
                                    <h4><b><fmt:message key="text.interview_info.page.scheduled"/></b></h4>
                                </div>
                            </c:if>

                            <c:if test="${temp_interview.interviewStatus == 'IN_WAITING'}">
                                <div class="w3-container w3-green w3-center">
                                    <h4><b><fmt:message key="text.interview_info.page.waiting"/></b></h4>
                                </div>
                            </c:if>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </main>

    <footer class="footer">
        <%@include file="../fragment/footer.jsp" %>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/js/js.js">
</script>
</body>
</html>