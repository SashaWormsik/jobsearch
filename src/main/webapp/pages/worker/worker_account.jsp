<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
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
                        <h4>${location.country}, ${location.city}</h4>
                        <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">
                            <fmt:message key="user.account.edit.information.button"/>
                        </button>
                    </div>
                </div>
            </div>

            <div class="w3-threequarter w3-section" style=" word-break: break-all">
                <div class="w3-card-4 w3-white">
                    <div class="w3-container">
                        <h4><b><fmt:message key="label.user.educationstatus"/>: </b>
                            <c:if test="${user.education == 'HIGHER'}">
                                <fmt:message key="label.user.educationstatus.higher"/>
                            </c:if>
                            <c:if test="${user.education == 'SECONDARY'}">
                                <fmt:message key="label.user.educationstatus.secondary"/>
                            </c:if>
                            <c:if test="${user.education == 'BASIC'}">
                                <fmt:message key="label.user.educationstatus.basic"/>
                            </c:if>
                            <c:if test="${user.education == 'NO_EDUCATION'}">
                                <fmt:message key="label.user.educationstatus.noeducation"/>
                            </c:if>
                            <c:if test="${user.education == 'NOT_SPECIFIED'}">
                                <fmt:message key="label.user.educationstatus.notspecified"/>
                            </c:if>
                        </h4>
                        <h4><b><fmt:message key="label.user.profession"/>: </b>${user.profession}</h4>
                        <h4><b><fmt:message key="label.user.workingstatus"/>: </b>
                            <c:if test="${user.workingStatus == 'WORK'}">
                                <fmt:message key="label.user.workingstatus.work"/>
                            </c:if>
                            <c:if test="${user.workingStatus == 'IN_SEARCH'}">
                                <fmt:message key="label.user.workingstatus.insearch"/>
                            </c:if>
                            <c:if test="${user.workingStatus == 'NO_STATUS'}">
                                <fmt:message key="label.user.workingstatus.nostatus"/>
                            </c:if>
                        </h4>
                        <h4><b><fmt:message key="label.user.description"/>: </b>
                            ${user.description}
                        </h4>
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