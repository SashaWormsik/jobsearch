<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="../fragment/head.jsp" %>
    <title><fmt:message key="title.update_info_page"/></title>
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
                <!--LOCATION -->
                <div class="w3-card-4 w3-white w3-margin-top">
                    <form action="${pageContext.request.contextPath}/controller" method="post"
                          class="w3-container" id="location_user">
                        <input type="hidden" name="${ParameterName.COMMAND}" value="update_user_location"/>
                        <input type="hidden" name="${ParameterName.USER_ID}" value="${user.id}"/>
                        <!-- COUNTRY -->
                        <c:if test="${incorrect_location_country == true}">
                            <div class="w3-left w3-red w3-block">
                                <fmt:message key="message.incorrect.country.pattern"/>
                            </div>
                            <br/>
                        </c:if>
                        <label><h4><b><fmt:message key="label.location.country"/>: </b></h4></label>
                        <input class="w3-input w3-border"
                               type="text"
                               name="${ParameterName.LOCATION_COUNTRY}"
                               placeholder="<fmt:message key="label.location.country"/>"
                               pattern="^[A-ZА-Я][а-яa-z]+(\s?'?-?[A-ZА-Я]?[а-яa-z]+)*$"
                               title="<fmt:message key="message.incorrect.country.pattern"/>"
                               value="${location_country}"
                               required/>
                        <!-- CITY -->
                        <c:if test="${incorrect_location_city == true}">
                            <div class="w3-left w3-red w3-block">
                                <fmt:message key="message.incorrect.city.pattern"/>
                            </div>
                            <br/>
                        </c:if>
                        <label><h4><b><fmt:message key="label.location.city"/>: </b></h4></label>
                        <input class="w3-input w3-border"
                               type="text"
                               name="${ParameterName.LOCATION_CITY}"
                               placeholder="<fmt:message key="label.location.city"/>"
                               pattern="^[A-ZА-Я][а-яa-z]+(\s?'?-?[A-ZА-Я]?[а-яa-z]+)*$"
                               title="<fmt:message key="message.incorrect.city.pattern"/>"
                               value="${location_city}"
                               required/>
                        <button class="w3-button w3-block w3-green w3-section w3-padding"
                                type="submit"
                                form="location_user"
                                formmethod="post" formaction="${pageContext.request.contextPath}/controller">
                            <fmt:message key="button.save"/>
                        </button>
                    </form>
                </div>
            </div>
            <!-- COMPANY INFO -->
            <div class="w3-threequarter w3-section" style=" word-break: break-all">
                <div class="w3-card-4 w3-white">
                    <form action="${pageContext.request.contextPath}/controller" method="post"
                          class="w3-container" id="user_info">
                        <input type="hidden" name="${ParameterName.COMMAND}" value="update_user_info"/>
                        <input type="hidden" name="${ParameterName.USER_ID}" value="${user.id}"/>
                        <input type="hidden" name="${ParameterName.USER_ROLE}" value="${user.role}"/>
                        <label><h4><b><fmt:message key="label.user.companyname"/>: </b></h4></label>
                        <!-- COMPANY NAME -->
                        <input class="w3-input w3-border"
                               type="text"
                               name="${ParameterName.USER_NAME}"
                               placeholder="<fmt:message key="label.user.companyname"/>"
                               pattern="[A-Za-zА-Яа-я]+"
                               title="<fmt:message key="message.incorrect.name.pattern"/>"
                               value="${user.userName}"
                               required/>
                        <!-- COMPANY DESCRIPTION -->
                        <label><h4><b><fmt:message key="label.user.description"/>: </b></h4></label>
                        <textarea class="w3-input w3-border"
                                  style="height:300px;"
                                  placeholder="<fmt:message key="label.user.description"/>"
                                  name="${ParameterName.USER_DESCRIPTION}"
                                  rows="0">${user.description}</textarea>

                        <button class="w3-button w3-block w3-green w3-section w3-padding"
                                type="submit"
                                form="user_info"
                                formmethod="post" formaction="${pageContext.request.contextPath}/controller">
                            <fmt:message key="button.save"/>
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