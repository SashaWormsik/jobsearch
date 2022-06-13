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
                        <h4>${location.country}, ${location.city}</h4>
                    </div>
                </div>
            </div>


            <div class="w3-threequarter w3-section" style=" word-break: break-all">
                <div class="w3-card-4 w3-white">

                    <div class="w3-container w3-center w3-green">
                        <h2><fmt:message key="label.vacancy"/>: ${vacancy.jobTitle}</h2>
                    </div>
                    <div class="w3-container">
                        <h4><b>
                            <fmt:message key="label.vacancy.salary"/>: </b>${vacancy.salary} ${vacancy.currency}</h4>
                        <h4><b>
                            <fmt:message key="label.vacancy.workexperience"/>: </b>
                            <c:if test="${vacancy.workExperienceStatus == 'WITHOUT'}">
                                <fmt:message key="label.vacancy.experience.without"/>
                            </c:if>
                            <c:if test="${vacancy.workExperienceStatus == 'UP_TO_A_YEAR'}">
                                <fmt:message key="label.vacancy.experience.uptoayear"/>
                            </c:if>
                            <c:if test="${vacancy.workExperienceStatus == 'FROM_1_TO_3_YEARS'}">
                                <fmt:message key="label.vacancy.experience.from1to3"/>
                            </c:if>
                            <c:if test="${vacancy.workExperienceStatus == 'FROM_3_TO_5_YEARS'}">
                                <fmt:message key="label.vacancy.experience.from3to5"/>
                            </c:if>
                            <c:if test="${vacancy.workExperienceStatus == 'MORE_THAN_5_YEARS'}">
                                <fmt:message key="label.vacancy.experience.morethan5"/>
                            </c:if>
                        </h4>
                        <h4><b><fmt:message key="label.vacancy.country"/>: </b>${vacancy_location.country}</h4>
                        <h4><b><fmt:message key="label.vacancy.city"/>: </b>${vacancy_location.city}</h4>
                        <h4 style="margin-bottom: 0px"><b><fmt:message key="label.vacancy.responsibilities"/>: </b></h4>
                        <p style="margin-top: 0px">${vacancy.responsibilities}</p>

                        <h4 style="margin-bottom: 0px"><b><fmt:message key="label.vacancy.requirements"/>: </b></h4>
                        <p style="margin-top: 0px">${vacancy.requirement}</p>

                        <h4 style="margin-bottom: 0px"><b><fmt:message key="label.vacancy.workingconditions"/>: </b>
                        </h4>
                        <p style="margin-top: 0px">${vacancy.workingConditions}</p>

                        <div class="w3-row">
                            <div class="w3-half" style="padding-right: 8px;">
                                <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">
                                    <fmt:message key="update.button"/>
                                </button>
                            </div>
                            <div class="w3-half">
                                <button class="w3-button w3-block w3-pink w3-section w3-padding" type="submit">
                                    <fmt:message key="change.vacancy.status.button"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="w3-row w3-section">
                    <div class="w3-half" style="padding-right: 8px;">
                        <div class="w3-card-4 w3-white w3-padding">
                            <table class="w3-table w3-bordered w3-hoverable w3-centered">
                                <caption><fmt:message key="label.vacancy.table.responses"/></caption>
                                <thead>
                                <tr class="w3-pink">
                                    <th class="w3-border w3-border-black"><fmt:message
                                            key="label.vacancy.table.column.user"/></th>
                                    <th class="w3-border w3-border-black"><fmt:message
                                            key="label.vacancy.table.column.date.response"/></th>
                                    <th class="w3-border w3-border-black"><fmt:message
                                            key="label.vacancy.table.column.action"/></th>
                                </tr>
                                </thead>
                                <c:forEach var="user_response" items="${users_responses}">
                                    <tr>
                                        <td class="w3-border w3-border-black">
                                            <a href="#">
                                                <!--добавить ссылку на переход к юзеру -->
                                                    ${user_response.key.userName} ${user_response.key.userSurName}
                                            </a>
                                        </td>
                                        <td class="w3-border w3-border-black">${user_response.value.appointedDateTime}</td>
                                        <td class="w3-border w3-border-black">
                                            <button class="w3-button w3-green w3-hover-blue">
                                                <fmt:message key="table.vacancy.responses.action.appoint"/>
                                            </button>
                                            <button class="w3-button w3-pink w3-hover-black">
                                                <fmt:message key="table.vacancy.responses.action.reject"/>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    <div class="w3-half">
                        <div class="w3-card-4 w3-white w3-padding">
                            <table class="w3-table w3-bordered w3-hoverable w3-centered">
                                <caption><fmt:message key="label.vacancy.table.interview"/></caption>
                                <thead>
                                <tr class="w3-blue">
                                    <th class="w3-border w3-border-black"><fmt:message
                                            key="label.vacancy.table.column.user"/></th>
                                    <th class="w3-border w3-border-black"><fmt:message
                                            key="label.vacancy.table.column.date.interview"/></th>
                                    <th class="w3-border w3-border-black"><fmt:message
                                            key="label.vacancy.table.column.action"/></th>
                                </tr>
                                </thead>
                                <c:forEach var="user_interview" items="${users_interviews}">
                                    <tr>
                                        <td class="w3-border w3-border-black">
                                            <a href="#">
                                                <!--добавить ссылку на переход к юзеру -->
                                                    ${user_interview.key.userName} ${user_interview.key.userSurName}
                                            </a>
                                        </td>
                                        <td class="w3-border w3-border-black">${user_interview.value.appointedDateTime}</td>
                                        <td class="w3-border w3-border-black">
                                            <button class="w3-button w3-green w3-hover-blue">+</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
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