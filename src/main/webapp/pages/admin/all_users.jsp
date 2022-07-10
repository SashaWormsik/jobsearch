<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="user_item" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%@ page import="org.chervyakovsky.jobsearch.controller.ParameterName" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtag" %>

<%@include file="../fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="../fragment/head.jsp" %>
    <title><fmt:message key="title.all_users.page"/></title>
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
                    </div>
                </div>
            </div>

            <div class="w3-threequarter w3-section" style=" word-break: break-all">
                <div class="w3-card-4 w3-white">
                    <div class="w3-container w3-center w3-green w3-margin-bottom">
                        <c:if test="${user_role == 'ADMIN'}">
                            <h2><fmt:message key="text.all_users.page.table.admins"/></h2>
                        </c:if>
                        <c:if test="${user_role == 'COMPANY'}">
                            <h2><fmt:message key="text.all_users.page.table.companies"/></h2>
                        </c:if>
                        <c:if test="${user_role == 'WORKER'}">
                            <h2><fmt:message key="text.all_users.page.table.workers"/></h2>
                        </c:if>
                    </div>
                    <div class="w3-container">

                        <div class="w3-bar w3-margin-bottom">
                            <form action="${pageContext.request.contextPath}/controller" method="get"
                                  class="w3-right w3-border-bottom">
                                <input type="hidden" name="${ParameterName.USER_ROLE}" value="${user_role}">
                                <input type="hidden" name="${ParameterName.COMMAND}" value="search_user">
                                <input class="w3-bar-item w3-white"
                                       name="${ParameterName.USER_SEARCH_QUERY}"
                                       type="text"
                                       placeholder="Search..."/>
                                <button class="w3-button w3-hover-text-blue w3-hover-white w3-circle w3-bar-item"
                                        type="submit">
                                    <i class="fa fa-search"></i>
                                </button>
                            </form>
                        </div>

                        <c:choose>
                            <c:when test="${list_users.size()>0}">
                                <table class="w3-table w3-bordered w3-hoverable w3-centered w3-margin-bottom">
                                    <thead>
                                    <tr class="w3-green">
                                        <th class="w3-border w3-border-black"><fmt:message
                                                key="table.column.users"/></th>
                                        <th class="w3-border w3-border-black"><fmt:message
                                                key="table.column.login"/></th>
                                        <th class="w3-border w3-border-black"><fmt:message
                                                key="table.column.email"/></th>
                                        <th class="w3-border w3-border-black"><fmt:message
                                                key="table.column.status"/></th>
                                        <th class="w3-border w3-border-black"><fmt:message
                                                key="table.column.role"/></th>
                                        <th class="w3-border w3-border-black"><fmt:message
                                                key="table.column.action"/></th>
                                    </tr>
                                    </thead>
                                    <c:forEach var="user_item" items="${list_users}">
                                        <tr>
                                            <td class="w3-border w3-border-black">${user_item.userName} ${user_item.userSurName}</td>
                                            <td class="w3-border w3-border-black">${user_item.login}</td>
                                            <td class="w3-border w3-border-black">${user_item.email}</td>
                                            <td class="w3-border w3-border-black">${user_item.userStatus}</td>
                                            <td class="w3-border w3-border-black">${user_item.role}</td>
                                            <td class="w3-border w3-border-black">
                                                <form method="get"
                                                      action="${pageContext.request.contextPath}/controller">
                                                    <input type="hidden" name="${ParameterName.COMMAND}"
                                                           value="get_user_info">
                                                    <input type="hidden" name="${ParameterName.USER_ID}"
                                                           value="${user_item.id}">
                                                    <button class="w3-button w3-pink w3-hover-black">
                                                        <fmt:message key="button.view"/>
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <div class="w3-center w3-container w3-margin-bottom">
                                    <div class="w3-bar w3-border w3-border-green">
                                        <c:if test="${page != 1 && (not empty page)}">
                                            <a href="${pageContext.request.contextPath}/controller?command=get_all_users&user_role=${user_role}&page=${page - 1}"
                                               class="w3-button">&laquo;</a>
                                        </c:if>
                                        <c:forEach begin="1" end="${page_count}" var="i">
                                            <c:choose>
                                                <c:when test="${page eq i}">
                                                    <a class="w3-button w3-grey w3-disabled">${i}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${pageContext.request.contextPath}/controller?command=get_all_users&user_role=${user_role}&page=${i}"
                                                       class="w3-button">${i}</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:if test="${page lt page_count}">
                                            <a href="${pageContext.request.contextPath}/controller?command=get_all_users&user_role=${user_role}&page=${page + 1}"
                                               class="w3-button">&raquo;</a>
                                        </c:if>
                                    </div>
                                </div>

                            </c:when>
                            <c:otherwise>
                                <div class="w3-center">
                                    <h5><b><fmt:message key="text.all_users.page"/></b></h5>
                                </div>
                            </c:otherwise>
                        </c:choose>
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