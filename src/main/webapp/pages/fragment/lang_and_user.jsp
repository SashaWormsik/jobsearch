<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="user" value="${sessionScope.user}"/>
<c:choose>
    <c:when test="${not empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    </c:when>
    <c:when test="${empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.language = 'ru_RU'}" scope="session"/>
    </c:when>
</c:choose>
<fmt:setBundle basename="language"/>
