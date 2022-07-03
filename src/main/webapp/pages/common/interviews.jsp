<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="user_location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%--@elvariable id="temp_user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="interview" type="org.chervyakovsky.jobsearch.model.entity.Interview"--%>
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
                    <div class="w3-container w3-center w3-green">
                        <h2><fmt:message key="text.interviews.page"/></h2>
                    </div>
                    <div class="w3-container w3-padding-24">
                        <c:choose>
                            <c:when test="${list_interviews.size() > 0}">
                                <table class="w3-table w3-bordered w3-hoverable w3-centered">
                                    <thead>
                                    <tr class="w3-pink">
                                        <th class="w3-border w3-border-black"><fmt:message
                                                key="table.column.number"/></th>
                                        <th class="w3-border w3-border-black"><fmt:message
                                                key="table.column.date_and_time"/></th>
                                        <th class="w3-border w3-border-black"><fmt:message
                                                key="table.column.status"/></th>
                                        <th class="w3-border w3-border-black"><fmt:message
                                                key="table.column.action"/></th>
                                    </tr>
                                    </thead>
                                    <c:forEach var="interview" items="${list_interviews}">
                                        <tr>
                                            <td class="w3-border w3-border-black">${interview.id}</td>
                                            <td class="w3-border w3-border-black">
                                                <fmt:formatDate type="both"
                                                                dateStyle="short"
                                                                timeStyle="short"
                                                                value="${interview.appointedDateTime}"/></td>
                                            <td class="w3-border w3-border-black">${interview.interviewStatus}</td>
                                            <td class="w3-border w3-border-black">
                                                <form action="${pageContext.request.contextPath}/controller"
                                                      method="get">
                                                    <input type="hidden" name="${ParameterName.COMMAND}"
                                                           value="get_interview_info">
                                                    <input type="hidden" name="${ParameterName.INTERVIEW_ID}"
                                                           value="${interview.id}">
                                                    <input type="hidden" name="${ParameterName.USER_ID}"
                                                           value="${interview.userInfoId}">
                                                    <input type="hidden" name="${ParameterName.VACANCY_ID}"
                                                           value="${interview.vacancyId}">
                                                    <button type="submit" class="w3-button w3-green w3-hover-blue">
                                                        <fmt:message key="button.view"/>
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <div class="w3-center">
                                    <h5><b><fmt:message key="text.interviews.page.empty_list"/></b></h5>
                                </div>
                            </c:otherwise>
                        </c:choose>
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