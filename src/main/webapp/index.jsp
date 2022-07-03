<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:forward page="/controller?command=search_vacancy&location_country=${location_country}&location_city=${location_city}&vacancy_job_title=${vacancy_job_title}&vacancy_work_experience=${vacancy_work_experience}"/>