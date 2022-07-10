<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="user_location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%--@elvariable id="temp_user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtag" %>

<%@include file="fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="fragment/head.jsp" %>
    <title><fmt:message key="title.user_info.page"/></title>
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
                <div class="w3-card-4 w3-white">
                    <div class="w3-container w3-center w3-green">
                        <c:choose>
                            <c:when test="${temp_user.role == 'COMPANY'}">
                                <h2><fmt:message key="text.user_info.page.company"/></h2>
                            </c:when>
                            <c:when test="${temp_user.role == 'WORKER'}">
                                <h2><fmt:message key="text.user_info.page.worker"/></h2>
                            </c:when>
                            <c:when test="${temp_user.role == 'ADMIN'}">
                                <h2><fmt:message key="text.user_info.page.admin"/></h2>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="w3-container">
                        <c:choose>
                            <c:when test="${temp_user.role == 'COMPANY'}">
                                <h4><b><fmt:message key="label.user.companyname"/>: </b>${temp_user.userName}</h4>
                            </c:when>
                            <c:when test="${temp_user.role == 'ADMIN' || temp_user.role == 'WORKER'}">
                                <h4><b><fmt:message key="label.user.name"/>: </b>${temp_user.userName}</h4>
                                <h4><b><fmt:message key="label.user.surname"/>: </b>${temp_user.userSurName}</h4>
                            </c:when>
                        </c:choose>
                        <c:if test="${temp_user.role == 'WORKER'}">
                            <h4><b><fmt:message key="label.user.educationstatus"/>: </b>
                                <c:if test="${temp_user.education == 'HIGHER'}">
                                    <fmt:message key="label.user.educationstatus.higher"/>
                                </c:if>
                                <c:if test="${temp_user.education == 'SECONDARY'}">
                                    <fmt:message key="label.user.educationstatus.secondary"/>
                                </c:if>
                                <c:if test="${temp_user.education == 'BASIC'}">
                                    <fmt:message key="label.user.educationstatus.basic"/>
                                </c:if>
                                <c:if test="${temp_user.education == 'NO_EDUCATION'}">
                                    <fmt:message key="label.user.educationstatus.noeducation"/>
                                </c:if>
                                <c:if test="${temp_user.education == 'NOT_SPECIFIED'}">
                                    <fmt:message key="label.user.educationstatus.notspecified"/>
                                </c:if>
                            </h4>
                            <h4><b><fmt:message key="label.user.profession"/>: </b>${temp_user.profession}</h4>
                            <h4><b><fmt:message key="label.user.workingstatus"/>: </b>
                                <c:if test="${temp_user.workingStatus == 'WORK'}">
                                    <fmt:message key="label.user.workingstatus.work"/>
                                </c:if>
                                <c:if test="${temp_user.workingStatus == 'IN_SEARCH'}">
                                    <fmt:message key="label.user.workingstatus.insearch"/>
                                </c:if>
                                <c:if test="${temp_user.workingStatus == 'NO_STATUS'}">
                                    <fmt:message key="label.user.workingstatus.nostatus"/>
                                </c:if>
                            </h4>
                        </c:if>
                        <c:if test="${temp_user.role == 'COMPANY' || temp_user.role == 'WORKER'}">
                            <h4><b><fmt:message key="label.user.description"/>: </b>${temp_user.description}</h4>
                        </c:if>
                        <c:if test="${user.role == 'ADMIN'}">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="change_user_status"/>
                                <input type="hidden" name="${ParameterName.USER_ID}" value="${temp_user.id}"/>
                                <c:if test="${temp_user.userStatus == true}">
                                    <button class="w3-button w3-block w3-green w3-section w3-padding"
                                            type="submit"><fmt:message key="button.block"/></button>
                                </c:if>
                                <c:if test="${temp_user.userStatus == false}">
                                    <button class="w3-button w3-block w3-red w3-section w3-padding"
                                            type="submit"><fmt:message key="button.unblock"/></button>
                                </c:if>
                            </form>
                        </c:if>
                    </div>
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