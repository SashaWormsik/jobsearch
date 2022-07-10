<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtag" %>

<%@include file="../fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="../fragment/head.jsp" %>
    <title><fmt:message key="title.error.page"/></title>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <%@include file="../fragment/header.jsp" %>
    </header>

    <main class="main">
        <div class="w3-auto w3-padding" style="max-width:600px">
            <div class="w3-white w3-card-4">
                <div class="w3-center w3-container w3-red">
                    <h1>ERROR: <b>${pageContext.errorData.statusCode}</b></h1>
                </div>
                <c:if test="${pageContext.errorData.statusCode == 500}">
                    <div class="w3-center w3-container">
                        <h5><fmt:message key="text.error.page.500"/></h5>
                    </div>
                </c:if>
                <c:if test="${pageContext.errorData.statusCode == 405}">
                    <div class="w3-center w3-container">
                        <h5><fmt:message key="text.error.page.405"/></h5>
                    </div>
                </c:if>
                <c:if test="${pageContext.errorData.statusCode == 404}">
                    <div class="w3-center w3-container">
                        <h5><fmt:message key="text.error.page.404"/></h5>
                    </div>
                </c:if>
                <c:if test="${pageContext.errorData.statusCode == 403}">
                    <div class="w3-center w3-container">
                        <h5><fmt:message key="text.error.page.403"/></h5>
                    </div>
                </c:if>
                <c:if test="${pageContext.errorData.statusCode == 401}">
                    <div class="w3-center w3-container">
                        <h5><fmt:message key="text.error.page.401"/></h5>
                    </div>
                </c:if>
                <c:if test="${pageContext.errorData.statusCode == 400}">
                    <div class="w3-center w3-container">
                        <h5><fmt:message key="text.error.page.400"/></h5>
                    </div>
                </c:if>
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