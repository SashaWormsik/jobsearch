<%--@elvariable id="user" type="org.chervyakovsky.jobsearch.model.entity.UserInfo"--%>
<%--@elvariable id="user_location" type="org.chervyakovsky.jobsearch.model.entity.Location"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtag" %>

<%@include file="../fragment/lang_and_user.jsp" %>

<html>
<head>
    <%@ include file="../fragment/head.jsp" %>
    <title><fmt:message key="title.update_info.page"/></title>
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
                                form="location_user">
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
                        <!-- NAME -->
                        <c:if test="${incorrect_user_name == true}">
                            <div class="w3-left w3-red w3-block">
                                <fmt:message key="message.incorrect.name.pattern"/>
                            </div>
                            <br/>
                        </c:if>
                        <c:if test="${user.role == 'WORKER'}">
                            <label><h4><b><fmt:message key="label.user.name"/>: </b></h4></label>
                        </c:if>
                        <c:if test="${user.role == 'COMPANY'}">
                            <label><h4><b><fmt:message key="label.user.companyname"/>: </b></h4></label>
                        </c:if>
                        <input class="w3-input w3-border"
                               type="text"
                               name="${ParameterName.USER_NAME}"
                                <c:if test="${user.role == 'WORKER'}">
                                    placeholder="<fmt:message key="label.user.name"/>"
                                </c:if>
                                <c:if test="${user.role == 'COMPANY'}">
                                    placeholder="<fmt:message key="label.user.companyname"/>"
                                </c:if>
                               pattern="^[\p{L}][\p{L}`-]?[\p{L}`]{1,20}$"
                               title="<fmt:message key="message.incorrect.name.pattern"/>"
                               value="${user.userName}"
                               required/>
                        <!-- for WORKER -->
                        <c:if test="${user.role == 'WORKER'}">
                            <!-- SURNAME -->
                            <c:if test="${incorrect_user_name == true}">
                                <div class="w3-left w3-red w3-block">
                                    <fmt:message key="message.incorrect.name.pattern"/>
                                </div>
                                <br/>
                            </c:if>
                            <label><h4><b><fmt:message key="label.user.surname"/>: </b></h4></label>
                            <input class="w3-input w3-border"
                                   type="text"
                                   name="${ParameterName.USER_SURNAME}"
                                   placeholder="<fmt:message key="label.user.surname"/>"
                                   pattern="^[\p{L}][\p{L}`-]?[\p{L}`]{1,20}$"
                                   title="<fmt:message key="message.incorrect.name.pattern"/>"
                                   value="${user.userSurName}"
                                   required/>
                            <!-- EDUCATION STATUS -->
                            <label><h4><b><fmt:message key="label.user.educationstatus"/>: </b></h4></label>
                            <select class="w3-select w3-border" id="${ParameterName.USER_EDUCATION_STATUS}"
                                    name="${ParameterName.USER_EDUCATION_STATUS}" required>
                                <option value="HIGHER"
                                        <c:if test="${user.education == 'HIGHER'}">
                                            selected
                                        </c:if>><fmt:message key="label.user.educationstatus.higher"/></option>
                                <option value="SECONDARY"
                                        <c:if test="${user.education == 'SECONDARY'}">
                                            selected
                                        </c:if>><fmt:message key="label.user.educationstatus.secondary"/></option>
                                <option value="BASIC"
                                        <c:if test="${user.education == 'BASIC'}">
                                            selected
                                        </c:if>><fmt:message key="label.user.educationstatus.basic"/></option>
                                <option value="NO_EDUCATION"
                                        <c:if test="${user.education == 'NO_EDUCATION'}">
                                            selected
                                        </c:if>><fmt:message key="label.user.educationstatus.noeducation"/></option>
                                <option value="NOT_SPECIFIED"
                                        <c:if test="${user.education == 'NOT_SPECIFIED'}">
                                            selected
                                        </c:if>><fmt:message key="label.user.educationstatus.notspecified"/></option>
                            </select>
                            <!-- PROFESSION -->
                            <c:if test="${incorrect_profession == true}">
                                <div class="w3-left w3-red w3-block">
                                    <fmt:message key="message.incorrect.profession.pattern"/>
                                </div>
                                <br/>
                            </c:if>
                            <label><h4><b><fmt:message key="label.user.profession"/>: </b></h4></label>
                            <input class="w3-input w3-border"
                                   type="text"
                                   name="${ParameterName.USER_PROFESSION}"
                                   placeholder="<fmt:message key="label.user.profession"/>"
                                   pattern="^([a-zA-ZА-Яа-я]{1})([\sa-zа-я-]{1,20})([a-zа-я]{1})$"
                                   title="<fmt:message key="message.incorrect.profession.pattern"/>"
                                   value="${user.profession}"
                                   required/>
                            <!-- WORK STATUS -->
                            <label><h4><b><fmt:message key="label.user.workingstatus"/>: </b></h4></label>
                            <select class="w3-select w3-border" id="${ParameterName.USER_WORKING_STATUS}"
                                    name="${ParameterName.USER_WORKING_STATUS}" required>
                                <option value="WORK"
                                        <c:if test="${user.workingStatus == 'WORK'}">
                                            selected
                                        </c:if>>
                                    <fmt:message key="label.user.workingstatus.work"/>
                                </option>
                                <option value="IN_SEARCH"
                                        <c:if test="${user.workingStatus == 'IN_SEARCH'}">
                                            selected
                                        </c:if>>
                                    <fmt:message key="label.user.workingstatus.insearch"/>
                                </option>
                                <option value="NO_STATUS"
                                        <c:if test="${user.workingStatus == 'NO_STATUS'}">
                                            selected
                                        </c:if>>
                                    <fmt:message key="label.user.workingstatus.nostatus"/>
                                </option>
                            </select>
                        </c:if>
                        <!-- DESCRIPTION -->
                        <c:if test="${incorrect_description == true}">
                            <div class="w3-left w3-red w3-block">
                                <fmt:message key="message.incorrect.description.pattern"/>
                            </div>
                            <br/>
                        </c:if>
                        <label><h4><b><fmt:message key="label.user.description"/>: </b></h4></label>
                        <textarea class="w3-input w3-border"
                                  style="height:300px;"
                                  placeholder="<fmt:message key="label.user.description"/>"
                                  name="${ParameterName.USER_DESCRIPTION}"
                                  rows="0">${user.description}</textarea>

                        <button class="w3-button w3-block w3-green w3-section w3-padding"
                                type="submit"
                                form="user_info">
                            <fmt:message key="button.save"/>
                        </button>
                    </form>
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